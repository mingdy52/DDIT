package kr.or.ddit.proj.colleague.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.annotation.MultipartConfig;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.common.comcode.service.ComCodeService;
import kr.or.ddit.common.comcode.vo.ComCodeVO;
import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.common.vo.SimpleSearchCondition;
import kr.or.ddit.proj.colleague.service.ColleagueService;
import kr.or.ddit.proj.colleague.vo.ColleagueVO;
import kr.or.ddit.proj.main.service.ProjectService;
import kr.or.ddit.proj.main.vo.ProjectVO;
import kr.or.ddit.proj.prod.service.ProjectProdService;
import kr.or.ddit.proj.prod.vo.ProjectPaymentVO;
import kr.or.ddit.proj.work.vo.WorkVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/project/{pjId}/colleague")
@MultipartConfig
public class ColleagueController {

	@Inject
	ColleagueService service;

	@Inject
	WebApplicationContext root;
	
	@Inject
	private ProjectService projectService;
	
	@Inject
	private ComCodeService comCodeService;
	
	@Inject
	private ColleagueService collegueService;
	@Inject
	private ProjectProdService prodService;
	
	@ModelAttribute("simpleCondition")
	public SimpleSearchCondition simpleCondition() {
		return new SimpleSearchCondition();
	}
	
	@ModelAttribute("collegueVO")
	public ColleagueVO colleagueVO() {
		return new ColleagueVO();
	}
	
	
	@GetMapping
	public String colleagueHome(
			@PathVariable String pjId,
			Model model,
			Authentication auth) {
		
		if(auth == null) {
			return "redirect:/";
		}
		
		ColleagueVO colleagueVO = new ColleagueVO();
		colleagueVO.setMemId(auth.getName());
		colleagueVO.setPjId(pjId);
		
		colleagueVO = service.retrieveColleague(colleagueVO);
		
		ProjectVO project = new ProjectVO();
		project = projectService.retrieveProject(pjId);
		// 해당 프로젝트가 결제 되었는지 확인 유무
		ProjectPaymentVO payVO = prodService.retrievePayment(pjId);
		if (payVO == null) {
			return "project2/projectHome";
		}

		model.addAttribute("project", project);
		
		return "project2/projectMember";
	}
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<ColleagueVO> selectWorkListJSON(
		@PathVariable("pjId")String pjId
		, @ModelAttribute("simpleCondition") SimpleSearchCondition simpleCondition
	) {
		PagingVO<ColleagueVO> pagingVO = new PagingVO<>(5,8);	
		pagingVO.setDetailCondition(new ColleagueVO());
		pagingVO.getDetailCondition().setPjId(pjId);
		pagingVO.setSimpleCondition(simpleCondition);
		service.SearchColleagueList(pagingVO);
		return pagingVO;
	}
	
	@GetMapping(value="/modify",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<ColleagueVO> selectCollegueListJSON(
		@PathVariable("pjId")String pjId
		, @ModelAttribute("simpleCondition") SimpleSearchCondition simpleCondition
		, @RequestParam("projAuthCode") String projAuthCode
		, @RequestParam("workRoleCode") String workRoleCode
		, @RequestParam("colNum") String colNum
	) {
		
		ColleagueVO collegue =new ColleagueVO();
		
		List<ComCodeVO> comCodeRole =comCodeService.retrieveComCode("WORK_ROLE");
		for(ComCodeVO role :comCodeRole) {
			if(role.getComCodeNm().equals(workRoleCode)) {
				collegue.setWorkRoleCode(role.getComCode());
			}
			
		}
		List<ComCodeVO> comCodeAuth = comCodeService.retrieveComCode("PROJ_AUTH");
		for(ComCodeVO role :comCodeAuth) {
			if(role.getComCodeNm().equals(projAuthCode)) {
				collegue.setProjAuthCode(role.getComCode());
			}
			
		}
		collegue.setColNum(colNum);
		collegueService.modifyCode(collegue);
		PagingVO<ColleagueVO> pagingVO = new PagingVO<>(5,8);	
		pagingVO.setDetailCondition(new ColleagueVO());
		pagingVO.getDetailCondition().setPjId(pjId);
		pagingVO.setSimpleCondition(simpleCondition);
		service.SearchColleagueList(pagingVO);
		return pagingVO;
	}
	
	@PutMapping
	public String remove(@RequestParam("colNum") String colNum) {
		log.info("colleagueVO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+colNum);
		service.removeColleague(colNum);
		
		return "redirect:/project/{pjId}/colleague";
	}
	
}
