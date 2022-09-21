package kr.or.ddit.proj.work.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.logging.log4j.util.PerformanceSensitive;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.common.vo.SimpleSearchCondition;
import kr.or.ddit.proj.colleague.service.ColleagueService;
import kr.or.ddit.proj.colleague.vo.ColleagueVO;
import kr.or.ddit.proj.issue.service.IssueService;
import kr.or.ddit.proj.issue.vo.IssueVO;
import kr.or.ddit.proj.main.service.ProjectService;
import kr.or.ddit.proj.main.vo.ProjectVO;
import kr.or.ddit.proj.prod.service.ProjectProdService;
import kr.or.ddit.proj.prod.vo.ProjectPaymentVO;
import kr.or.ddit.proj.work.service.WorkService;
import kr.or.ddit.proj.work.vo.WorkVO;
import kr.or.ddit.proj.workmark.service.WorkMarkService;
import kr.or.ddit.proj.workmark.vo.WorkMarkVO;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 작성자명
 * @since 2022. 8. 5.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 5.     윤수웅            2022. 8. 5.
 * Copyright (c) 2022 by DDIT All right reserved
 *      </pre>
 */

@Slf4j
@Controller
@RequestMapping("/project/{pjId}/work/{workNumber}")
public class WorkDetailController {

	@Inject
	private WorkService service;
	
	@Inject
	private IssueService issueService;

	@Inject
	private ColleagueService colleagueService;
	
	@Inject
	private WorkMarkService markService;
	
	@Inject
	private ProjectService projectService;
	
	@Inject
	private ProjectProdService prodService;
	


	@ModelAttribute("workVO")
	public WorkVO workVO() {
		return new WorkVO();
	}

	@ModelAttribute("issueVO")
	public IssueVO issueVO() {
		return new IssueVO();
	}

	@GetMapping
	public String getWorkDetail(@PathVariable("pjId") String pjId, @PathVariable("workNumber") String workNum,
			Model model, @ModelAttribute("workVO") WorkVO work, Authentication auth) {

		if (auth == null) {
			return "redirect:/";
		}
		
		ColleagueVO colleagueVO = new ColleagueVO();
		colleagueVO.setMemId(auth.getName());
		colleagueVO.setPjId(pjId);
		
		colleagueVO = colleagueService.retrieveColleague(colleagueVO);
		
		ProjectVO project = new ProjectVO();
		project = projectService.retrieveProject(pjId);
		// 해당 프로젝트가 결제 되었는지 확인 유무
		ProjectPaymentVO payVO = prodService.retrievePayment(pjId);
		if (payVO == null) {
			return "project2/projectHome";
		}

		model.addAttribute("project", project);
		
		
		WorkMarkVO markVO = new WorkMarkVO();
		markVO.setWorkNum(workNum);
		markVO.setColNum(colleagueVO.getColNum());
		markVO = markService.retrieveWorkMark(markVO);

		model.addAttribute("markVO", markVO);
		model.addAttribute("colleagueVO", colleagueVO);
		
		PagingVO<WorkVO> paging = new PagingVO<>(1, 2);
		paging.setDetailCondition(work);
		paging.getDetailCondition().setPjId(pjId);
		model.addAttribute("workList", service.selectAllWorkList(paging));
		log.info("pagingVO$$$$$$$$$$$$$$$$$$$$" + paging);
		model.addAttribute("workNum", workNum);
		IssueVO issue = new IssueVO();
		issue.setWorkNum(workNum);
		model.addAttribute("issue", issueService.findAllIssue(issue));
		return "project2/workDetail";
	}

	@PutMapping
	public String modify(@ModelAttribute WorkVO work) {
		service.modifyWork(work);
		return "redirect:/project/{pjId}/work/{workNum}";

	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public WorkVO selectWorkListJSON(@ModelAttribute WorkVO work, @PathVariable("pjId") String pjId) {
		log.info("workVO+++++++++++@@@@@" + work);
		work.setPjId(pjId);
		service.modifyWork(work);
		return work;

	}

	@GetMapping(value = "/addchild", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public WorkVO WorkListJSON(@ModelAttribute WorkVO work, @PathVariable("pjId") String pjId,
			@PathVariable("workNumber") String workNum) {
		work.setPjId(pjId);
		if (!workNum.equals("000")) {
			work.setParentWorkNum(workNum);
		}
		service.insertWork(work);
		
		PagingVO<WorkVO> paging = new PagingVO<>();
		paging.setDetailCondition(new WorkVO());
		paging.getDetailCondition().setPjId(pjId);
		
		List<WorkVO> workList = service.selectAllWorkList(paging);
		
		int ws01 = 0,ws02 = 0, ws03 = 0, ws04 = 0, ws05 = 0;
		for(WorkVO single : workList) {
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
		work.setWorkList(wsList);
		
		return work;
	}

	@PostMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public int deleteWork(@ModelAttribute WorkVO work, @PathVariable("pjId") String pjId) {
		work.setPjId(pjId);
		return service.deleteWork(work);
	}
	
	@PostMapping("/parentdelete")
	public String deleteParentWork(@ModelAttribute WorkVO work ,@PathVariable("workNumber") String workNum, @PathVariable("pjId") String pjId) {
		work.setWorkNum(workNum);
		log.info("workVO@#!@#!@#!@!@!@!@"+work);
		service.deleteParentWork(work);
		return "redirect:/project/{pjId}/work";
	}

}
