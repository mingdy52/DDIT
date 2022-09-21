package kr.or.ddit.proj.workmark.controller;

import java.util.List;

import javax.inject.Inject;

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

import kr.or.ddit.proj.colleague.service.ColleagueService;
import kr.or.ddit.proj.colleague.vo.ColleagueVO;
import kr.or.ddit.proj.main.service.ProjectService;
import kr.or.ddit.proj.main.vo.ProjectVO;
import kr.or.ddit.proj.prod.service.ProjectProdService;
import kr.or.ddit.proj.prod.vo.ProjectPaymentVO;
import kr.or.ddit.proj.work.vo.WorkVO;
import kr.or.ddit.proj.workmark.service.WorkMarkService;
import kr.or.ddit.proj.workmark.vo.WorkMarkVO;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 고정현
 * @since 2022. 8. 5.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 5.      고정현       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Controller
@Slf4j
@RequestMapping("/project/{pjId}")
public class WorkMarkController {
	
	@Inject
	ColleagueService colleagueService;
	
	@Inject
	WorkMarkService markService;
	
	@Inject
	private ProjectService projectService;
	
	@Inject
	private ProjectProdService prodService;
	
	@GetMapping("myWorkMarkList")
	public String getMyWorkMark(
			@PathVariable String pjId,
			Authentication auth,
			Model model,
			RedirectAttributes res
			) {
		if(auth == null) {
			return "redirect:/";
		}
		ColleagueVO colleague = new ColleagueVO();
		colleague.setMemId(auth.getName());
		colleague.setPjId(pjId);
		colleague = colleagueService.retrieveColleague(colleague);
		
		if(colleague == null) {
			res.addFlashAttribute("message", "해당 프로젝트의 회원이 아닙니다.");
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
		
		WorkMarkVO markVO = new WorkMarkVO();
		markVO.setColNum(colleague.getColNum());
		
		List<WorkMarkVO> markList = markService.retrieveWorkMarkList(markVO);
		
		model.addAttribute("markList", markList);
		
		return "project2/myWorkMarkList";
	}
	
	@GetMapping(value="myWorkMarkList",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<WorkMarkVO> search(
			@PathVariable String pjId,
			@ModelAttribute WorkVO workVO,
			Authentication auth,
			RedirectAttributes res
			) {
		ColleagueVO colleague = new ColleagueVO();
		colleague.setMemId(auth.getName());
		colleague.setPjId(pjId);
		colleague = colleagueService.retrieveColleague(colleague);
		
		WorkMarkVO markVO = new WorkMarkVO();
		markVO.setColNum(colleague.getColNum());
		markVO.setWork(workVO);
		
		List<WorkMarkVO> markList = markService.retrieveWorkMarkList(markVO);
		
		return markList;
	}
	
	@PostMapping("work/{workNumber}/workMark")
	public String insertWorkMark(
			@PathVariable String pjId,
			@PathVariable String workNumber,
			@RequestParam String delYn,
			Authentication auth
			) {
		ColleagueVO colleague = new ColleagueVO();
		colleague.setMemId(auth.getName());
		colleague.setPjId(pjId);
		
		colleague = colleagueService.retrieveColleague(colleague);
		
		if(colleague == null) {
			return "redirect:/project";
		}
		
		WorkMarkVO markVO = new WorkMarkVO();
		markVO.setWorkNum(workNumber);
		markVO.setColNum(colleague.getColNum());
		if("Y".equals(delYn)) {
			markService.removeWorkMark(markVO);
		}else {
			markService.createWorkMark(markVO);
		}
		
		return "redirect:/project/{pjId}/work/{workNumber}";
	}
}
