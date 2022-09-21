package kr.or.ddit.proj.work.controller;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.common.vo.WorkSearchVO;
import kr.or.ddit.proj.colleague.service.ColleagueService;
import kr.or.ddit.proj.colleague.vo.ColleagueVO;
import kr.or.ddit.proj.main.service.ProjectService;
import kr.or.ddit.proj.main.vo.ProjectVO;
import kr.or.ddit.proj.prod.service.ProjectProdService;
import kr.or.ddit.proj.prod.vo.ProjectPaymentVO;
import kr.or.ddit.proj.work.service.WorkService;
import kr.or.ddit.proj.work.vo.WorkVO;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 작성자명
 * @since 2022. 8. 31.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 31.      고정현       내 업무 리스트 가져오기
 * 2022. 9. 1.       고정현      접근 제한 막기
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Controller
@Slf4j
@RequestMapping("project/{pjId}/myWork")
public class MyWorkController {
	
	@Inject
	ColleagueService colleagueSerivce;
	
	@Inject
	WorkService workService;
	
	@Inject
	private ProjectService projectService;
	
	@Inject
	private ProjectProdService prodService;
	
	@GetMapping
	public String getMyWorkList(
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
			res.addFlashAttribute("message", "로그인 하지않은 사용자는 이용할 수 없습니다.");
			return "redirect:/";
		}
		String memId = auth.getName();
		log.info("#############{}", memId);
		ColleagueVO colleague = new ColleagueVO();
		colleague.setMemId(memId);
		colleague.setPjId(pjId);
		try {
			colleague = colleagueSerivce.retrieveColleague(colleague);
		}catch(Exception e) {
			res.addFlashAttribute("message", "해당 사용자는 프로젝트의 팀원이 아닙니다.");
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
		
		
		List<WorkVO> myWorkList = workService.retriverMyWork(colleague);
		
		model.addAttribute("myWorkList", myWorkList);
		
		return "project2/myWork";
	}
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<WorkVO> searchMyWorkList(
			@ModelAttribute WorkSearchVO workSearchVO,
			@PathVariable String pjId,
			Authentication auth
			){
		log.info("################{}",workSearchVO);
		
		String memId= auth.getName();
		
		ColleagueVO colleague = new ColleagueVO();
		colleague.setPjId(pjId);
		colleague.setMemId(memId);
		colleague = colleagueSerivce.retrieveColleague(colleague);
		
		log.info("###############{}", colleague);
		if(colleague == null) {
			return null;
		}
		colleague.setWorkSearchVO(workSearchVO);
		
		List<WorkVO> myWorkList = workService.retriverMyWork(colleague);
		
		
		return myWorkList;
	}
}
