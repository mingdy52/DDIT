package kr.or.ddit.admin.controller;

import java.io.IOException;
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
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.admin.dao.MemberManagementDAO;
import kr.or.ddit.admin.service.MemberManagementService;
import kr.or.ddit.admin.vo.DelMemVO;
import kr.or.ddit.common.CheckMember;
import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.common.vo.SimpleSearchCondition;
import kr.or.ddit.enumpkg.RoleGroup;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author 심민경
 * @since 2022. 8. 16.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 16.  심민경               최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */

@Slf4j
@Controller
@RequestMapping("/admin/member")
public class MemberListController {
	
	@Inject
	private MemberManagementService service;
	@Inject
	private MemberManagementDAO memberDAO;
	@Inject
	private CheckMember check;
	
	@RequestMapping
	public String processHTML(Model model){
		String viewName = null;
		MemberVO checkAdmin = check.checkAdmin();
		
		if(checkAdmin != null) {
			MemberVO roleNum = memberDAO.memberNumForChart();
        	model.addAttribute("roleNum", roleNum);
        	
        	Calendar cal = Calendar.getInstance();
			String year = cal.get(Calendar.YEAR)+"";
			DelMemVO delMemVO = memberDAO.getDelStatistics(year);
			model.addAttribute("delMemVO", delMemVO);
			
			viewName = "admin/memberList";
		} else {
			viewName = "redirect:/login";
		}
		
		return viewName;
		
	}
	
	// 회원 목록
	@ResponseBody
	@RequestMapping(value="allMember", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PagingVO<MemberVO> getMemList(
			@RequestParam(name="allPage", required=false, defaultValue="1") int currentPage
			, @RequestParam(value="searchType", required=false) String searchType
			, @RequestParam(value="searchWord", required=false) String searchWord
			, HttpServletResponse resp
			) throws IOException {
		PagingVO<MemberVO> pagingVO = null;
		MemberVO checkAdmin = check.checkAdmin();
		
		if(checkAdmin != null) {
			RoleGroup role = RoleGroup.ROLE_USER;
			pagingVO = listJsonData(currentPage, role, searchType, searchWord);
		} else {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
		
		return pagingVO;
	}
	
	// 전문가 목록
	@ResponseBody
	@GetMapping(value="exp", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PagingVO<MemberVO> getExpMemList(
			@RequestParam(name="exPage", required=false, defaultValue="1") int currentPage
			, @RequestParam(value="searchType", required=false) String searchType
			, @RequestParam(value="searchWord", required=false) String searchWord
			, Model model
			, HttpServletResponse resp
			) throws IOException {
		
		PagingVO<MemberVO> pagingVO = null;
		MemberVO checkAdmin = check.checkAdmin();
		
		if(checkAdmin != null) {
			RoleGroup role = RoleGroup.ROLE_EXPERT;
			pagingVO = listJsonData(currentPage, role, searchType, searchWord);
		} else {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
		
		return pagingVO;
		
	}
	
	// 프로젝트 회원 목록
	@ResponseBody
	@GetMapping(value="proj", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PagingVO<MemberVO> getPjMemList(
			@RequestParam(name="pjPage", required=false, defaultValue="1") int currentPage
			, @RequestParam(value="searchType", required=false) String searchType
			, @RequestParam(value="searchWord", required=false) String searchWord
			, Model model
			, HttpServletResponse resp
			) throws IOException {
		
		PagingVO<MemberVO> pagingVO = null;
		MemberVO checkAdmin = check.checkAdmin();
		
		if(checkAdmin != null) {
			RoleGroup role = RoleGroup.ROLE_PROJECT;
        	pagingVO = listJsonData(currentPage, role, searchType, searchWord);
		} else {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
		
		return pagingVO;
	}
	
	// 블랙리스트
	@ResponseBody
	@GetMapping(value="black", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PagingVO<MemberVO> getBlackList(
			@RequestParam(name="blackPage", required=false, defaultValue="1") int currentPage
			, @RequestParam(value="searchType", required=false) String searchType
			, @RequestParam(value="searchWord", required=false) String searchWord
			, Model model
			, HttpServletResponse resp
			) throws IOException {
		
		PagingVO<MemberVO> pagingVO = null;
		MemberVO checkAdmin = check.checkAdmin();
		
		if(checkAdmin != null) {
			RoleGroup role = RoleGroup.BLACK;
			pagingVO = listJsonData(currentPage, role, searchType, searchWord);
		} else {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
		
		return pagingVO;
	}
	
	// 회원 목록 가져오는 공통로직
	public PagingVO<MemberVO> listJsonData(
			int currentPage
			, RoleGroup role
			, String searchType
			, String searchWord
			){
		
		PagingVO<MemberVO> pagingVO = new PagingVO<>(8, 5);
		pagingVO.setCurrentPage(currentPage);
		
		SimpleSearchCondition searchVO = null;
		if(StringUtils.isNotBlank(searchType)) {
			searchVO = new SimpleSearchCondition();
			searchVO.setSearchType(searchType);
			searchVO.setSearchWord(searchWord);
			pagingVO.setSimpleCondition(searchVO);
		}
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("pagingVO", pagingVO);
		
		if(StringUtils.equals(role.toString(), "BLACK")) {
			service.retrieveBlackList(map, pagingVO);
		} else {
			map.put("roleGoup", role);
			service.retrieveRoleMemberList(map, pagingVO);
		}
		
		return  pagingVO;
	}
	
	// 회원 목록 엑셀 다운로드
	@GetMapping("/excel")
	public void downloadExcel(
			HttpServletResponse resp
			) throws IOException {
		
		MemberVO checkAdmin = check.checkAdmin();
		
		if(checkAdmin != null) {
			Workbook workbook = new HSSFWorkbook();
			Sheet userSheet = workbook.createSheet("일반회원");
			Sheet expertSheet = workbook.createSheet("전문가회원");
			Sheet projectSheet = workbook.createSheet("프로젝트회원");
			
			mkHead(userSheet, "ROLE_USER");
			mkHead(expertSheet, "ROLE_EXPERT");
			mkHead(projectSheet, "ROLE_EXPERT");
			
			resp.setContentType("ms-vnd/excel");
			resp.setHeader("Content-Disposition", "attachment;filename=memberList.xlsx");
			
			try {
				workbook.write(resp.getOutputStream());
				workbook.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		} else {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
	}
	
	public void mkHead(Sheet sheet, String role) {
		List<MemberVO> userList = memberDAO.selectMemberList(role);
		
		int rowNo = 0;
	    Row headerRow = sheet.createRow(rowNo++);
        headerRow.createCell(0).setCellValue("회원아이디");
        headerRow.createCell(1).setCellValue("이름");
        headerRow.createCell(2).setCellValue("닉네임");
        headerRow.createCell(3).setCellValue("메일");
        headerRow.createCell(4).setCellValue("전회번호");
        headerRow.createCell(5).setCellValue("누적신고수");
        
        for (MemberVO member : userList) {
            Row row = sheet.createRow(rowNo++);
            row.createCell(0).setCellValue(member.getMemId());
            row.createCell(1).setCellValue(member.getMemName());
            row.createCell(2).setCellValue(member.getMemNick());
            row.createCell(3).setCellValue(member.getMemMail());
            row.createCell(4).setCellValue(member.getMemHp());
            row.createCell(5).setCellValue(member.getAccumRep());
        }
	}
}



