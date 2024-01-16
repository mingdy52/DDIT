package kr.or.ddit.proj.work.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.common.vo.SimpleSearchCondition;
import kr.or.ddit.proj.colleague.service.ColleagueService;
import kr.or.ddit.proj.colleague.vo.ColleagueVO;
import kr.or.ddit.proj.issue.vo.IssueVO;
import kr.or.ddit.proj.main.service.ProjectService;
import kr.or.ddit.proj.main.vo.ProjectVO;
import kr.or.ddit.proj.prod.service.ProjectProdService;
import kr.or.ddit.proj.prod.vo.ProjectPaymentVO;
import kr.or.ddit.proj.work.service.WorkService;
import kr.or.ddit.proj.work.vo.WorkVO;
import lombok.extern.slf4j.Slf4j;


/**
 * @author 작성자명
 * @since 2022. 8. 5.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 5.      윤수웅      2022. 8. 5.
 * 2022  9. 1.      고정현      접근 제한 막기
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Slf4j
@Controller
@RequestMapping("/project/{pjId}/work")
public class AllWorkRequestController {

	@Inject
	private WorkService service;
	
	@Inject
	ColleagueService colleagueService;
	
	@Inject
	private ProjectService projectService;
	
	@Inject
	private ProjectProdService prodService;
	
	@ModelAttribute("simpleCondition")
	public SimpleSearchCondition simpleCondition() {
		return new SimpleSearchCondition();
	}
	
	
	@ModelAttribute("workVO")
	public WorkVO workVO() {
		return new WorkVO();
	}
	
	
	@GetMapping
	public String workListHTML(@PathVariable("pjId") String pjId,
			Authentication auth,
			RedirectAttributes res,
			HttpSession session,
			Model model
			) {
		
		if(auth == null) {
			res.addFlashAttribute("message", "로그인하고 이용이 가능합니다");
			return "redirect:/login";
		}
		String memId= auth.getName();
		
		ColleagueVO colleague = new ColleagueVO();
		colleague.setMemId(memId);
		colleague.setPjId(pjId);
		try {
			colleague= colleagueService.retrieveColleague(colleague);
		}catch(Exception e) {			
			res.addFlashAttribute("message","해당 유저는 프로젝트의 팀원이 아닙니다.");
			return "redirect:/project";
		}
		
		ProjectVO project = new ProjectVO();
		project = projectService.retrieveProject(pjId);
		// 해당 프로젝트가 결제 되었는지 확인 유무
		ProjectPaymentVO payVO = prodService.retrievePayment(pjId);
		if (payVO == null) {
			return "project2/projectHome";
		}

		model.addAttribute("project", project);
		model.addAttribute("colleague", colleague);
			
		return "project2/allWorkRequest";
	}
	
	@GetMapping(value="/workList", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	   @ResponseBody
	   public PagingVO<WorkVO> WorkListJSON( @PathVariable("pjId")String pjId){
	      PagingVO<WorkVO> pagingVO = new PagingVO<>(1,2);
	      pagingVO.setDetailCondition(new WorkVO());
	      pagingVO.getDetailCondition().setPjId(pjId);
	      service.selectAllWorkList(pagingVO);
	      log.info("pagingVO**************************"+pagingVO);
	      return pagingVO;
	      
	   }
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<WorkVO> selectWorkListJSON(
		@RequestParam(name="page", required=false, defaultValue="1") int currentPage,
		@PathVariable("pjId")String pjId
		, @ModelAttribute("simpleCondition") SimpleSearchCondition simpleCondition
	) {
		PagingVO<WorkVO> pagingVO = new PagingVO<>(5,8);	
		pagingVO.setDetailCondition(new WorkVO());
		pagingVO.getDetailCondition().setPjId(pjId);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSimpleCondition(simpleCondition);
		service.findAll(pagingVO);
		return pagingVO;
	}
	

	
}
