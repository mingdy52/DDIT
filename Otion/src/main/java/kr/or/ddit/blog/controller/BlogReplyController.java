package kr.or.ddit.blog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.blog.service.BlogReplyService;
import kr.or.ddit.blog.vo.BlogReplyVO;
import kr.or.ddit.common.login.vo.MemberPrincipal;
import kr.or.ddit.common.validate.InsertGroup;
import kr.or.ddit.common.validate.UpdateGroup;
import kr.or.ddit.common.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/blog")
public class BlogReplyController {
	@Inject
	BlogReplyService replyService;
	
	@ModelAttribute("blogReplyVO")
	public BlogReplyVO getBlogReplyVO() {
		return new BlogReplyVO();
	}
	
	/*@GetMapping("{memId}/reply")
	public String BlogReply(
			Model model
			,@PathVariable String memId) {
		model.addAttribute("memId", memId);
		//model.addAttribute("blogReplyVO", blogReplyVO);
		return "myblog/blogReply";
	}*/
	
	//댓글 조회
	@ResponseBody
	@RequestMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE,value="{memId}/reply/{postNum}",method=RequestMethod.GET)
	public Map<String, Object> ReplyList(
			@PathVariable String postNum
			){
		Map<String, Object> map = new HashMap<>();
		List<BlogReplyVO> replyList =replyService.retrieveReplyList(postNum);
		map.put("replyList",replyList);
		return map;
	}
	//댓글 추가
	@ResponseBody
	@PostMapping(path="{memId}/reply/write",produces = "application/text; charset=utf8")
	public String writeReply(
			@Validated(InsertGroup.class)@RequestBody BlogReplyVO blogReplyVO
			,Errors errors
			,@PathVariable(value="memId")String memId
			){
		log.info("vo:{}",blogReplyVO);
		log.info("chchchchchchch:{}",blogReplyVO.getBlReplyContent());
			if(!errors.hasErrors()) {
				blogReplyVO.setBlogerId(memId);
				String msg=replyService.createReply(blogReplyVO);
				return msg;

			}else {
				return "부적절";
			}
	}
	//대댓글 추가
	@ResponseBody
	@PostMapping(path="{memId}/reply/reWrite",produces = "application/text; charset=utf8")
	//@RequestMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE,value="{memId}/reply/reWrite",method=RequestMethod.POST)
	public String reWriteReply(
			@Validated(InsertGroup.class)@RequestBody BlogReplyVO blogReplyVO
			,Errors errors
			,@PathVariable(value="memId")String memId
			){
		log.info("vo:{}",blogReplyVO);
		log.info("chchchchchchch:{}",blogReplyVO.getBlReplyContent());
		log.info("parent:{}",blogReplyVO.getParentBlReplyNum());
			if(!errors.hasErrors()) {
				blogReplyVO.setBlogerId(memId);
				String msg=replyService.createReReply(blogReplyVO);
				return msg;

			}else {
				return "부적절";
			}
	}
	//댓글 수정
	@ResponseBody
	@PostMapping(path="{memId}/reply/upWrite",produces = "application/text; charset=utf8")
	public String modifyReply(
			@Validated(UpdateGroup.class)@RequestBody BlogReplyVO blogReplyVO
			,Errors errors
			,@PathVariable(value="memId")String memId
			){
		log.info("vo:{}",blogReplyVO);
		log.info("chchchchchchch:{}",blogReplyVO.getBlReplyContent());
		log.info("parent:{}",blogReplyVO.getBlReplyNum());
			if(!errors.hasErrors()) {
				String msg=replyService.modifyReply(blogReplyVO);
				return msg;

			}else {
				return "부적절";
			}
	}
	//댓글 삭제
	@ResponseBody
	@GetMapping(path="{memId}/reply/delReply",produces = "application/text; charset=utf8")
	public String delReply(
			@RequestParam(value="delNum")String delNum
			) {
		
		String msg =replyService.removeReply(delNum);
		return msg;
	}
	
}
