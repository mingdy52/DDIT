package kr.or.ddit.admin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.admin.dao.MemberManagementDAO;
import kr.or.ddit.admin.service.ReportManagementService;
import kr.or.ddit.common.CheckMember;
import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.common.vo.ReportVO;
import kr.or.ddit.enumpkg.ServiceResult;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author 심민경
 * @since 2022. 8. 17.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 17.  심민경               최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */

@Slf4j
@Controller
@RequestMapping("/admin/report/{targetClass}/{target}")
public class ReportViewController {

	@Inject
	ReportManagementService service;
	@Inject
	private MemberManagementDAO memberDAO;
	@Inject
	private CheckMember check;
	
	@GetMapping
	public String memInfoView(@PathVariable String target
							, @PathVariable String targetClass
							, @RequestParam(value="page", required=false, defaultValue="1") int currentPage
							, Model model){
		String viewName = null;
		MemberVO checkAdmin = check.checkAdmin();
		
		if(checkAdmin != null) {
			PagingVO<ReportVO> pagingVO = new PagingVO<>(8, 5);
			pagingVO.setCurrentPage(currentPage);
			
			Map<String, Object> map = new HashMap<>();
			map.put("target", target);
			map.put("pagingVO", pagingVO);
			
			service.retrieveReport(map, pagingVO);
			
			model.addAttribute("pagingVO", pagingVO);
			model.addAttribute("target", target);
			model.addAttribute("targetClass", targetClass);
			
			viewName = "admin/reportView";
		} else {
			viewName = "redirect:/login";
		}
		
		return viewName;
		
	}
	
	@PostMapping
	public String addBlack(
			@RequestParam(name="target", required=false) String target
			, @RequestParam(name="reason", required=false) String reason
			, @RequestParam(name="targetClass", required=false) String targetClass
			, RedirectAttributes redirectAttributes){
		
		String viewName = null;
		MemberVO checkAdmin = check.checkAdmin();
		
		if(checkAdmin != null) {
			
			if(StringUtils.isBlank(reason) && StringUtils.equals(target, "member")) {
				viewName = String.format("redirect:/admin/report/%s/%s", targetClass, target);
				return viewName;
			} else {
				Map<String,String> map = new HashMap<String, String>();
				map.put("target", target);
				map.put("reason", reason);
				
				ServiceResult result = service.reportProcess(map, targetClass);
				
				switch (result) {
				case OK:
					viewName = "redirect:/admin/report";
					break;
				case FAIL:
					viewName = String.format("redirect:/admin/report/%s/%s", targetClass, target);
					break;
				}
				
			}
			
			
		} else {
			viewName = "redirect:/login";
		}
		
		redirectAttributes.addFlashAttribute("message", "완료되었습니다.");
		
		return viewName;

	}
	
}

