package kr.or.ddit.proj.gantt.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

import kr.or.ddit.common.comcode.service.ComCodeService;
import kr.or.ddit.common.comcode.vo.ComCodeVO;
import kr.or.ddit.proj.colleague.service.ColleagueService;
import kr.or.ddit.proj.colleague.vo.ColleagueVO;
import kr.or.ddit.proj.gantt.service.ProjectGanttService;
import kr.or.ddit.proj.main.service.ProjectService;
import kr.or.ddit.proj.main.vo.ProjectVO;
import kr.or.ddit.proj.prod.service.ProjectProdService;
import kr.or.ddit.proj.prod.vo.ProjectPaymentVO;
import kr.or.ddit.proj.work.vo.WorkVO;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 윤수웅
 * @since 2022. 8. 5.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 5.      윤수웅       최초작성
 * 2022  8. 28.     고정현       간트 리스트 작성
 * 2022  9. 1.      고정현       간트 접근제한 막기
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Controller
@Slf4j
@RequestMapping("/project/{pjId}/gantt")
public class ProjectGanttController {

	@Inject
	ColleagueService colleagueService;
	
	@Inject
	ProjectGanttService projectGanttService;
	
	@Inject
	ComCodeService comCodeService;
	
	@Inject
	private ProjectService projectService;
	
	@Inject
	private ProjectProdService prodService;
	
	@GetMapping
	public String gantForm(
			@PathVariable String pjId,
			Authentication auth,
			Model model,
			RedirectAttributes res
			) {
		if("none".equals(pjId) || StringUtils.isBlank(pjId)) {
			res.addFlashAttribute("message", "프로젝트를 선택해야 볼 수 있습니다.");
			return "redirect:/project";
		}
		
		if(auth == null) {
			res.addFlashAttribute("message", "로그인 후 이용하실수 있습니다.");
			return "redirect:/";
		}
		String memId = auth.getName();
		
		ColleagueVO colleagueVO = new ColleagueVO();
		colleagueVO.setMemId(memId);
		colleagueVO.setPjId(pjId);
		
		try {
			res.addFlashAttribute("message", "로그인 후 이용하실수 있습니다.");
			colleagueVO = colleagueService.retrieveColleague(colleagueVO);
		}catch(Exception e) {
			return "redirect:/";
		}
		
		ProjectVO project = new ProjectVO();
		project = projectService.retrieveProject(pjId);
		// 해당 프로젝트가 결제 되었는지 확인 유무
		ProjectPaymentVO payVO = prodService.retrievePayment(pjId);
		if (payVO == null) {
			return "project2/projectHome";
		}

		model.addAttribute("project", project);
		
		// 검색 누르면 시작일이 업무상태 피드백
		return "project2/gantt";
	}
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<WorkVO> getGanttList(
			@PathVariable String pjId,
			@ModelAttribute WorkVO work
			){
		log.info("################# {}", work);
		
		String memId = SecurityContextHolder.getContext().getAuthentication().getName();
		if(memId == null) {
			return null;
		}
		
		ColleagueVO colleagueVO = new ColleagueVO();
		colleagueVO.setMemId(memId);
		colleagueVO.setPjId(pjId);
		
		colleagueVO = colleagueService.retrieveColleague(colleagueVO);
		
		if(colleagueVO == null) {
			return null;
		}
		
		List<WorkVO> ganttList = projectGanttService.retrieveGanttList(work);
		
		return ganttList;
	}
	
	@GetMapping(value="view",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public WorkVO getGanttView(
			@ModelAttribute WorkVO work
			){
		log.info("################# {}", work.getWorkNum());
		
		if(StringUtils.isBlank(work.getWorkNum())) {
			// 클릭없이 들어올 경우 되돌려보냄
			return null;
		}
		
		work = projectGanttService.retrieveGanttView(work);
		
		return work;
	}
	
}
