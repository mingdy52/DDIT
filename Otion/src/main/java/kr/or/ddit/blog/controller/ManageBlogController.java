package kr.or.ddit.blog.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.blog.service.ManageService;
import kr.or.ddit.blog.service.MyBlogService;
import kr.or.ddit.blog.vo.BlogCateVO;
import kr.or.ddit.blog.vo.BlogReplyVO;
import kr.or.ddit.blog.vo.MyBlogPostVO;
import kr.or.ddit.common.login.vo.MemberPrincipal;
import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.common.vo.SimpleSearchCondition;
import lombok.extern.slf4j.Slf4j;
/**
 * 
 * @author 이아인
 * @since 2022. 8. 23.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 23.      이아인       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * 블로그 관리홈 
 */

@Slf4j
@Controller   // @RestController  -> @Controller + @ResponseBody
@RequestMapping("/blog")
public class ManageBlogController {
	
	@Inject
	ManageService manageService;
	
	@GetMapping("{memId}/manage")
	public String manage(
			@PathVariable(value="memId")String memId
			,Model model
			) {
		model.addAttribute("memId", memId);
		return "blogManage/blogManage";
	}
	
	@GetMapping("blogManage/check")
	public String checkPage(
			Model model
			) {
		log.info("여기로~");
		model.addAttribute("check", "check");
		return "blogManage/check";
	}
	
	// 블로그 관리 홈, 맨처음 시작은 카테고리이다.
	@RequestMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE,value="{memId}/blogManage",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> blogManage(@PathVariable("memId") String memId
				,@RequestParam(name ="catePage", required=false, defaultValue="1") int catePage
				,@RequestParam(value="searchWord", required=false) String searchWord
			) {
		String viewName=null;
		log.info("ajax:{}",memId);
		log.info("page:{}",catePage);
		log.info("search:{}",searchWord);
		Map<String,Object> retMap = new HashMap<>();
		/* 아인이 이해 한 걸로, forwarding, redirect, responsebody(다이렉트)
		Map<String,String> retMap = new HashMap<>();
		retMap.put("id", "아인메롱");
		
		List<String> names = new ArrayList<>();
		names.add("민경");
		names.add("아인");
		names.add("초리");
		*/
		if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated() && !SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
			MemberVO mv=((MemberPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRealMember();
		
			if(memId.equals(mv.getMemId())) {
				log.info("ckck: ",viewName);
				PagingVO<BlogCateVO> catePagingVO= new PagingVO<>(6,5);
				catePagingVO.setCurrentPage(catePage);
				SimpleSearchCondition searchVO = new SimpleSearchCondition();
				searchVO.setSearchWord(searchWord);
				
				catePagingVO.setSimpleCondition(searchVO);
				catePagingVO.setDetailCondition(new BlogCateVO());
				catePagingVO.getDetailCondition().setBlogerId(memId);
				manageService.retrieveJsonCate(catePagingVO);
				
				retMap.put("catePagingVO", catePagingVO);
			}else {
				 viewName="blogManage/check";
				 retMap.put("checkurl",viewName);
				 
			}
		}else {
			viewName="redirect:/login";
			retMap.put("loginurl","/login");
		}
		
		return retMap;
		
	}
	
	//카테고리 추가
	@RequestMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE,value="{memId}/create",method=RequestMethod.GET)
	@ResponseBody
	public String createCate(
			@RequestParam(value="inputData",required = false)String inputData
			,@PathVariable("memId") String memId
			) {
		log.info("createNm:{}",inputData);
		String check=null;
		if(StringUtils.isEmpty(inputData)) {
			check="{\"check\": \"누락\"}";
		}else {
			int cnt =manageService.createCateNm(inputData,memId);
			if(cnt>0) {
				check="{\"check\": \"추가\"}";
			}else {
				check="{\"check\": \"중복\"}";	
			}
		}
		return check;
	}
	
	//카레고리 수정
	@RequestMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE,value="{memId}/upName",method=RequestMethod.GET)
	@ResponseBody
	public String updateCate(
			@RequestParam(value="updateNm[]") List<String> updateNm
			,@PathVariable("memId") String memId
			) {

		log.info("카테넘:{}",updateNm);
		Map<String, String> updateMap =new HashMap<>();
		updateMap.put("upName",updateNm.get(0));
		updateMap.put("cateNum",updateNm.get(1));
		updateMap.put("memId",memId);
		String msg=null;
		if(updateNm.size()!=2) {
			 msg="{\"msg\": \"입력값이 누락되었습니다.\"}";
		}else {
			int cnt =manageService.modifyCateNm(updateMap);
			if(cnt>0) {
				 msg="{\"msg\": \"수정되었습니다.\"}";
			}else {
				 msg="{\"msg\": \"카테고리가 이미 존재합니다.\"}";	
			}
		}
		return msg;
	}
	
	
	//카테고리 삭제
	@RequestMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE,value="{memId}/delNum",method=RequestMethod.GET)
	@ResponseBody
	public String deleteCate(
			@RequestParam(value="delNum")String delNum
			) {
		log.info("델넘:{}",delNum);
		String msg=null;
		int cnt=manageService.removeCateNm(delNum);
		if(cnt>0) {
			 msg="{\"msg\": \"삭제되었습니다.\"}";
		}else {
			 msg="{\"msg\": \"삭제실패. 다시시도해주세요\"}";
		}
		return msg;
	}
	
	
	//게시글 관리
	@GetMapping("{memId}/postManage")
	public String postManage(
			Model model
			,@PathVariable(value="memId")String memId
			) {
		model.addAttribute("memId", memId);
		return "blogManage/postManage";
	}
	//게시글 불러오기
	@RequestMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE,value="{memId}/getPost",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> postList(
			@PathVariable(value="memId")String memId
			,@RequestParam(name ="postPage", required=false, defaultValue="1") int postPage
			,@RequestParam(value="searchWord", required=false) String searchWord
			,@RequestParam(value="searchType", required=false) String searchType
			) {
		String viewName=null;
		Map<String,Object> retMap = new HashMap<>();
		if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated() && !SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
			MemberVO mv=((MemberPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRealMember();
		
			if(memId.equals(mv.getMemId())) {
				PagingVO<MyBlogPostVO> postPagingVO= new PagingVO<>(3,5);
				postPagingVO.setCurrentPage(postPage);
				postPagingVO.setDetailCondition(new MyBlogPostVO());
				postPagingVO.getDetailCondition().setBlogerId(mv.getMemId());
				SimpleSearchCondition searchVO = new SimpleSearchCondition();
				searchVO.setSearchType(searchType);
				searchVO.setSearchWord(searchWord);
				postPagingVO.setSimpleCondition(searchVO);
			
				manageService.retrievePostList(postPagingVO);
				retMap.put("postPagingVO", postPagingVO);
			}else {
				 viewName="blogManage/check";
				 retMap.put("checkurl",viewName);
				 
			}
		}else {
			viewName="redirect:/login";
			retMap.put("loginurl","/login");
		}
		
		return retMap;
		
	}
	//게시글 삭제
	@RequestMapping(value="{memId}/delPost",method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String postDelete(
			@RequestParam(value="postBoxArr[]") List<String> postBoxArr
			) {
		MyBlogPostVO myblogPostVO=new MyBlogPostVO();
		myblogPostVO.setPostNumList(postBoxArr);
		String msg=manageService.removePost(myblogPostVO);
		return msg;
	}
	
	//댓글관리
	@GetMapping("{memId}/replyManage")
	public String replyManage(
			Model model
			,@PathVariable(value="memId")String memId
			) {
		model.addAttribute("memId", memId);
		return "blogManage/replyManage";
	}
	//댓글 리스트
	@RequestMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE,value="{memId}/getReply",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> replyList(
			@PathVariable(value="memId")String memId
			,@RequestParam(name ="replyPage", required=false, defaultValue="1") int replyPage
			,@RequestParam(value="searchWord", required=false) String searchWord
			,@RequestParam(value="searchType", required=false) String searchType
			) {
		String viewName=null;
		log.info("ajax:{}",memId);
		log.info("page:{}",replyPage);
		log.info("search:{}",searchWord);
		log.info("searchType:{}",searchType);
		Map<String,Object> retMap = new HashMap<>();
		if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated() && !SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
			MemberVO mv=((MemberPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRealMember();
		
			if(memId.equals(mv.getMemId())) {
				PagingVO<BlogReplyVO> replyPagingVO= new PagingVO<>(3,5);
				replyPagingVO.setCurrentPage(replyPage);
				replyPagingVO.setDetailCondition(new BlogReplyVO());
				replyPagingVO.getDetailCondition().setBlReplyWriter(mv.getMemId());
				SimpleSearchCondition searchVO = new SimpleSearchCondition();
				searchVO.setSearchType(searchType);
				searchVO.setSearchWord(searchWord);
				replyPagingVO.setSimpleCondition(searchVO);
			
				manageService.retrieveReplyList(replyPagingVO);
				retMap.put("replyPagingVO", replyPagingVO);
			}else {
				 viewName="blogManage/check";
				 retMap.put("checkurl",viewName);
				 
			}
		}else {
			viewName="redirect:/login";
			retMap.put("loginurl","/login");
		}
		
		return retMap;
	}
	//댓글삭제
	@RequestMapping(value="{memId}/delReply",method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String replyDelete(
			@RequestParam(value="replyBoxArr[]") List<String> replyBoxArr
			) {
		BlogReplyVO blogReplyVO =new BlogReplyVO();
		blogReplyVO.setBlReplyNumList(replyBoxArr);
		String msg=manageService.removeReply(blogReplyVO);
		return msg;
	}
}
