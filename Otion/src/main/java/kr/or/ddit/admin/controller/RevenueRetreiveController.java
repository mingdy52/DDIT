package kr.or.ddit.admin.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.admin.dao.MemberManagementDAO;
import kr.or.ddit.admin.dao.RevenueMangementDAO;
import kr.or.ddit.admin.vo.RevenueVO;
import kr.or.ddit.common.CheckMember;
import kr.or.ddit.common.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author 심민경
 * @since 2022. 8. 5.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                      수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 5.      심민경           최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */

@Slf4j
@Controller
@RequestMapping("/admin/revenue")
public class RevenueRetreiveController {
	
	@Inject
	RevenueMangementDAO revDAO;
	@Inject
	private MemberManagementDAO memberDAO;
	@Inject
	private CheckMember check;
	
	@GetMapping
	public String getRevenue(
			Model model
			) {
		String viewName = null;
		MemberVO checkAdmin = check.checkAdmin();
		
		if(checkAdmin != null) {
			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH);
			
			String strYear = Integer.toString(year);
			String strMonth = "0" + Integer.toString(month+1);
			getData(strYear, strMonth, model);
			
			model.addAttribute("year", year);
			model.addAttribute("month", month);
			
			viewName = "admin/revenue";
		} else {
			viewName = "redirect:/login";
		}

		return viewName;
	}
	
	@PostMapping
	public String getRevenue(
			@RequestParam(name="paramY", required=false) String paramY
			, @RequestParam(name="paramM", required=false) String paramM
			, @RequestParam(name="paramYM", required=false) String paramYM
			, Model model
			) {
		
		String viewName = null;
		int year = 0;
		int month = 0;
		MemberVO checkAdmin = check.checkAdmin();
		
		if(checkAdmin != null) {
			if(StringUtils.isNotBlank(paramY)) {
				year = Integer.parseInt(paramY);
				month = Integer.parseInt(paramM);
			}
			
			if(StringUtils.isNotBlank(paramYM)) {
				String param = paramYM.toString();
				paramY = paramYM.substring(0, 4);
				paramM = paramYM.substring(5, 7);
				year = Integer.parseInt(paramY);
				month = Integer.parseInt(paramM);
				paramM = "0" + Integer.toString(month);
			}
			
			Calendar cal = Calendar.getInstance();
			cal.set(year, month-1, 1);
			year = cal.get(Calendar.YEAR);
			month = cal.get(Calendar.MONTH);
			
			paramY = Integer.toString(year);
			paramM = "0" + Integer.toString(month+1);
			
			getData(paramY, paramM, model);
			
			model.addAttribute("year", year);
			model.addAttribute("month", month);
			
			viewName = "admin/revenue";
		} else {
			viewName = "redirect:/login";
		}
		
		return viewName;
	}
	
	public void getData(String year, String month, Model model) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("year", year);
		map.put("month", month);
		
		int eprodRevenue = revDAO.selectEprodRevenue(map);
		int workRevenue = revDAO.selectWorkRevenue(map);
		RevenueVO monthRevenue = revDAO.selectMonthRevenue(map);
		
		model.addAttribute("eprodRevenue", eprodRevenue);
		model.addAttribute("workRevenue", workRevenue);
		model.addAttribute("monthRevenue", monthRevenue);
	}
	
	@PostMapping("/revenueExcel")
	public void downloadExcel(
			@RequestParam(name="startDate") String startDate
			, @RequestParam(name="endDate") String endDate
			, HttpServletResponse resp
			, Model model
			) {
		String viewName = null;
		MemberVO checkAdmin = check.checkAdmin();
		
		if(checkAdmin != null) {
			String now = LocalDate.now().toString();
			if(StringUtils.isBlank(startDate)) startDate = now;
			if(StringUtils.isBlank(endDate)) endDate = now;
			
			Workbook workbook = new HSSFWorkbook();
			Sheet exeprtSheet = workbook.createSheet("수수료 매출");
			Sheet projectSheet = workbook.createSheet("협업툴 매출");
			
			Map<String, String> map = new HashMap<>();
			map.put("startDate", startDate);
			map.put("endDate", endDate);
			
			List<RevenueVO> expertRevenue = revDAO.selectExpertTermRevenue(map);
			List<RevenueVO> projectRevenue = revDAO.selectProjcetTermRevenue(map);
			
			int expRowNo = 0;
			Row expHeaderRow = exeprtSheet.createRow(expRowNo++);
			expHeaderRow.createCell(0).setCellValue("일자");
			expHeaderRow.createCell(1).setCellValue("수수료");
			expHeaderRow.createCell(2).setCellValue("누적 수수료");
			
			for (RevenueVO rev : expertRevenue) {
				Row row = exeprtSheet.createRow(expRowNo++);
				row.createCell(0).setCellValue(rev.getEpayDate());
				row.createCell(1).setCellValue(rev.getEprodRevenue());
				row.createCell(2).setCellValue(rev.getAccumRevenue());
			}
			
			int projectRowNo = 0;
			Row prjectHeaderRow = projectSheet.createRow(projectRowNo++);
			prjectHeaderRow.createCell(0).setCellValue("일자");
			prjectHeaderRow.createCell(1).setCellValue("매출");
			prjectHeaderRow.createCell(2).setCellValue("누적 매출");
			
			for (RevenueVO rev : projectRevenue) {
				Row row = projectSheet.createRow(projectRowNo++);
				row.createCell(0).setCellValue(rev.getWpayDate());
				row.createCell(1).setCellValue(rev.getWorkRevenue());
				row.createCell(2).setCellValue(rev.getAccumRevenue());
			}
			
			resp.setContentType("ms-vnd/excel");
			resp.setHeader("Content-Disposition", "attachment;filename=revenue.xlsx");
			
			try {
				workbook.write(resp.getOutputStream());
				workbook.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		} else {
			viewName = "redirect:/login";
		}
		
	}
	
}
