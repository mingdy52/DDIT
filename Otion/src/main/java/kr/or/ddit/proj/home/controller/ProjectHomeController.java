package kr.or.ddit.proj.home.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.common.comcode.service.ComCodeService;
import kr.or.ddit.common.comcode.vo.ComCodeVO;
import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.proj.colleague.service.ColleagueService;
import kr.or.ddit.proj.colleague.vo.ColleagueVO;
import kr.or.ddit.proj.main.service.ProjectService;
import kr.or.ddit.proj.main.vo.ProjectVO;
import kr.or.ddit.proj.prod.service.ProjectProdService;
import kr.or.ddit.proj.prod.vo.ProjectPaymentVO;
import kr.or.ddit.proj.prod.vo.ProjectProdVO;
import kr.or.ddit.proj.work.service.WorkService;
import kr.or.ddit.proj.work.vo.WorkVO;

@Controller
@RequestMapping("/project/{pjId}/home")
public class ProjectHomeController {

	@Inject
	private WorkService service;
	
	@Inject
	private ColleagueService colleagueService;
	
	@Inject
	private ProjectService projectService;
	
	@Inject
	private ProjectProdService prodService;
	
	@Inject
	private ComCodeService comCodeService;
	
	@GetMapping
	public String goToProjectHome(
			@PathVariable String pjId,
			HttpSession session,
			Authentication auth,
			RedirectAttributes res,
			Model model) {
		session.removeAttribute("project");
		session.setAttribute("home", "Y");
		if(auth == null) {
			res.addFlashAttribute("message", "로그인 후 이용하실수 있습니다.");
			return "redirect:/";
		}
		String memId = auth.getName();
		
		List<ComCodeVO> comCodeVO = comCodeService.retrieveComCode("WORK_ROLE");
		session.setAttribute("comCodeVO", comCodeVO);
		
		ColleagueVO colleagueVO = new ColleagueVO();
		colleagueVO.setMemId(memId);
		colleagueVO.setPjId(pjId);
		
		try {
			colleagueVO = colleagueService.retrieveColleague(colleagueVO);
		}catch(Exception e) {
			res.addFlashAttribute("message", "해당 프로젝트의 팀원이 아닙니다.");
			return "redirect:/project";
		}
		
		ProjectVO project = projectService.retrieveProject(pjId);
		
		if(project == null) {
			res.addFlashAttribute("message", "해당 프로젝트는 존재하지 않습니다.");
			return "redirect:/project";
		}
		// 상품 리스트
		List<ProjectProdVO> projectProdList = prodService.retrieveProdList();
		session.setAttribute("projectProdList", projectProdList);
		
		// 해당 프로젝트가 결제 되었는지 확인 유무
		ProjectPaymentVO payVO = prodService.retrievePayment(pjId);
		if(payVO == null) {
			return "project2/projectHome";
		}
		session.setAttribute("payVO", payVO);
		
		PagingVO<WorkVO> paging = new PagingVO<>();
		paging.setDetailCondition(new WorkVO());
		paging.getDetailCondition().setPjId(pjId);
		
		List<WorkVO> work = service.selectAllWorkList(paging);
		
		int ws01 = 0,ws02 = 0, ws03 = 0, ws04 = 0, ws05 = 0;
		for(WorkVO single : work) {
			if(single.getWorkPriority().equals("요청")) {
				ws01++;
			}else if(single.getWorkPriority().equals("진행")) {
				ws02++;
			}else if(single.getWorkPriority().equals("피드백")) {
				ws03++;
			}else if(single.getWorkPriority().equals("완료")) {
				ws04++;
			}else {
				ws05++;
			}
		}
		List<Integer> wsList = new ArrayList<>();
		wsList.add(ws01);
		wsList.add(ws02);
		wsList.add(ws03);
		wsList.add(ws04);
		wsList.add(ws05);
		
		model.addAttribute("workList", service.selectAllWorkList(paging));
		model.addAttribute("wsList", wsList);
		model.addAttribute("project", project);
		session.setAttribute("memberList", service.selectAllMemList(pjId));
		return "project2/projectHome";
	}
	
	
}
