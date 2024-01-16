package kr.or.ddit.proj.issue.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.common.attach.service.AttatchService;
import kr.or.ddit.common.vo.AttatchVO;
import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.common.vo.SimpleSearchCondition;
import kr.or.ddit.proj.colleague.service.ColleagueService;
import kr.or.ddit.proj.colleague.vo.ColleagueVO;
import kr.or.ddit.proj.issue.service.IssueService;
import kr.or.ddit.proj.issue.vo.IssueReplyVO;
import kr.or.ddit.proj.issue.vo.IssueVO;
import kr.or.ddit.proj.main.service.ProjectService;
import kr.or.ddit.proj.main.vo.ProjectVO;
import kr.or.ddit.proj.prod.service.ProjectProdService;
import kr.or.ddit.proj.prod.vo.ProjectPaymentVO;
import kr.or.ddit.proj.work.vo.WorkVO;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.proxy.annotation.Post;

@Controller
@RequestMapping("/project/{pjId}/issue")
@Slf4j
public class ProjIssueController {

	@Inject
	IssueService service;
	@Inject
	ColleagueService colleagueService;
	@Inject
	AttatchService attatchService;
	
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
	public String issueHome(@PathVariable String pjId,
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
		return "project2/issueHome";
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
	
	@GetMapping("/{issueNum}")
	public String issueDetail(@PathVariable("issueNum") String issueNum,@PathVariable("pjId") String pjId ,Model model, Authentication auth) {
		
		if(auth == null) {
			return "redirect:/";
		}
		
		IssueVO issue = new IssueVO();
		issue.setIssueNum(issueNum);
		issue = service.findIssue(issue);
		
		AttatchVO attatch = new AttatchVO();
		attatch.setAttatchPlace(issueNum);
		attatch = attatchService.retrieveAttatch(attatch);
		issue.setAttatch(attatch);
		
		ColleagueVO colleagueVO = new ColleagueVO();
		colleagueVO.setPjId(pjId);
		colleagueVO.setMemId(auth.getName());
		colleagueVO = colleagueService.retrieveColleague(colleagueVO);
		issue.setColleague(colleagueVO);
		
		ProjectVO project = new ProjectVO();
		project = projectService.retrieveProject(pjId);
		// 해당 프로젝트가 결제 되었는지 확인 유무
		ProjectPaymentVO payVO = prodService.retrievePayment(pjId);
		if (payVO == null) {
			return "project2/projectHome";
		}

		model.addAttribute("colleagueVO",colleagueVO);
		model.addAttribute("project", project);
		
		model.addAttribute("issue",issue);
		model.addAttribute("pjId", pjId);
		return "project2/issueDetail";
	}
	
	
	@PostMapping("/{issueNum}/delete")
	   public String deleteIssue( @PathVariable("issueNum")String issueNum ){
	
			IssueVO issue = new IssueVO();
			issue.setIssueNum(issueNum);
			service.deletIssue(issue);
			
			return "redirect:/project/{pjId}/issue";
	   }

	@PostMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	   @ResponseBody
	   public List<IssueReplyVO> issueReplyJSON( @ModelAttribute IssueReplyVO issueReply,Authentication auth ){
		if(ObjectUtils.isNotEmpty(issueReply.getReplyAttatch())) {
		AttatchVO attatch = new AttatchVO(issueReply.getReplyAttatch());
		attatch.setUploaderId(auth.getName());
		attatch.setAttatchOrder(1);
		attatchService.createIssueReplyAttatch(attatch);
	}
		
			
			return service.insertReply(issueReply);
	
		
	   }
	
	   @PostMapping()
	   @ResponseBody
	   public List<IssueReplyVO> deleteIssue( @ModelAttribute IssueReplyVO issueReply,Authentication auth ){
		if(ObjectUtils.isNotEmpty(issueReply.getReplyAttatch())) {
		AttatchVO attatch = new AttatchVO(issueReply.getReplyAttatch());
		attatch.setUploaderId(auth.getName());
		attatch.setAttatchOrder(1);
		attatchService.createIssueReplyAttatch(attatch);
	}
		
			
			return service.insertReply(issueReply);
	
		
	   }
	
	@GetMapping(value="/reply" , produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	   public List<IssueReplyVO>  issueReplyListJSON( @ModelAttribute IssueReplyVO issueReply){
		
		return service.allIssueReplyList(issueReply);
	   }
	
	@GetMapping(value="/reply/adddate" , produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	   public List<IssueReplyVO>  modifyIssueReplyDateJSON( @ModelAttribute IssueReplyVO issueReply){
		
		return service.modifyReply(issueReply);
	   }
	
	@PostMapping(value="/reply/modify" , produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	   public List<IssueReplyVO>  modifyIssueReplyJSON( @ModelAttribute IssueReplyVO issueReply,Authentication auth){
		log.info("issueReply {}",issueReply);
		if(ObjectUtils.isNotEmpty(issueReply.getReplyAttatch())&&StringUtils.isNotEmpty(issueReply.getAttatchNum())) {
		AttatchVO attatch = new AttatchVO(issueReply.getReplyAttatch());
		attatch.setAttatchNum(issueReply.getAttatchNum());
		attatchService.modifyAttatch(attatch);
		}else if(ObjectUtils.isEmpty(issueReply.getReplyAttatch())&&StringUtils.isNotEmpty(issueReply.getAttatchNum())){
			AttatchVO attatch = new AttatchVO();
			attatch.setAttatchNum(issueReply.getAttatchNum());
			attatch.setAttatchOrder(1);
			attatch.setAttatchPlace(issueReply.getIssueReplyNum());
			attatchService.deleteAttatchFile(attatch);
			
		}else if(ObjectUtils.isNotEmpty(issueReply.getReplyAttatch())&&StringUtils.isEmpty(issueReply.getAttatchNum())){
			AttatchVO attatch = new AttatchVO(issueReply.getReplyAttatch());
			attatch.setUploaderId(auth.getName());
			attatch.setAttatchOrder(1);
			attatch.setAttatchPlace(issueReply.getIssueReplyNum());
			attatchService.createAttachNumReplyAttatch(attatch);
			
		}
		
		return service.modifyReply(issueReply);
	
	   }
	
	@GetMapping(value="/reply/remove" , produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	   public List<IssueReplyVO>  removeIssueReplyJSON( @ModelAttribute IssueReplyVO issueReply){
			AttatchVO attatch = new AttatchVO();
			if(StringUtils.isNotBlank(issueReply.getAttatchNum())) {
			attatch.setAttatchNum(issueReply.getAttatchNum());
			attatch.setAttatchOrder(1);
			attatch.setAttatchPlace(issueReply.getIssueReplyNum());
			attatchService.deleteAttatchFile(attatch);}
			return service.removeReply(issueReply);
	   }
	

	
}
