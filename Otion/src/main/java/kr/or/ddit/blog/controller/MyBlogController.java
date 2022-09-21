package kr.or.ddit.blog.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.parser.ParseException;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.blog.dao.MyBlogDAO;
import kr.or.ddit.blog.service.BlogWriteService;
import kr.or.ddit.blog.service.MyBlogService;
import kr.or.ddit.blog.vo.BlogCateVO;
import kr.or.ddit.blog.vo.BlogHeartVO;
import kr.or.ddit.blog.vo.MyBlogPostVO;
import kr.or.ddit.blog.vo.PostMarkVO;
import kr.or.ddit.blog.vo.ShareVO;
import kr.or.ddit.common.CheckMember;
import kr.or.ddit.common.login.vo.MemberPrincipal;
import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.common.vo.SimpleSearchCondition;
import kr.or.ddit.enumpkg.RoleGroup;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.NotAuthorityException;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author 작성자명
 * @since 2022. 8. 20.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 20.      이아인       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * 자신의 블로그에 접근할 수 있으며 비회원,또 다른 회원이 접근해서도 볼 수 있는 곳이다.
 */
@Slf4j
@Controller
@RequestMapping("/blog/{memId}")
public class MyBlogController {
	@Inject
	MyBlogService blogService;
	
	@Inject
	BlogWriteService writeService;
	
	@Inject
	MyBlogDAO myBlogDAO;
	
	@Inject
	CheckMember check;
	
	@ModelAttribute("blogCateList")
	public List<BlogCateVO> blogCateList(
			@PathVariable("memId") String memId
			, Model model
			) {
		List<BlogCateVO> blogCateList = blogService.retrieveCate(memId);
		model.addAttribute("memId", memId);
		return blogCateList;
	}
	
	public boolean checkMember(String memId, Model model) {
		boolean result = false;
		int cnt =blogService.checkMemId(memId);
		if(cnt==0) {
			model.addAttribute("check", "check");
			result = true;
		}
		return result;
	}
	
	//블로그 메인
	@GetMapping
	public String blogMain(
			@PathVariable("memId") String memId
			, @RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @RequestParam(value="searchWord", required=false) String searchWord
			, Model model 
	      ) throws ParseException {
	   String viewName=null;
	   if(checkMember(memId, model)) {
	      viewName="myblog/check";
	   }else {
			SimpleSearchCondition searchVO = new SimpleSearchCondition();
			searchVO.setSearchWord(searchWord);
			
			PagingVO<MyBlogPostVO> pagingVO = new PagingVO<>(8, 5);
			pagingVO.setCurrentPage(currentPage);
			pagingVO.setSimpleCondition(searchVO);
			
			Map<String, Object> map = new HashMap<>();
			map.put("memId", memId);
			map.put("pagingVO", pagingVO);
			
			RoleGroup result = check.checkBlog(memId);
			if(result.equals(RoleGroup.OWNER)) {
				map.put("owner", "owner");
			} else {
				map.put("owner", "notOwner");
				
			}

			blogService.retrievePostList(map, pagingVO);
			

	 	  model.addAttribute("pagingVO", pagingVO);
	 	  viewName="myblog/blogMain";

	   }
	      return viewName;
	}
	
	//게시글 상세보기
	@GetMapping("post/{postNum}") 
	public String mainPostView(
			@PathVariable("memId") String memId
			, @PathVariable("postNum") String postNum
			, Model model
			, Authentication auth
			, HttpServletResponse resp
			) throws IOException {
		

		
		String viewName=null;
		MyBlogPostVO post = blogService.retrievePost(memId, postNum);
		int heartNo = writeService.countHeart(postNum);
		MemberVO member = null;
		
		String postTag = post.getPostTag();
		if(postTag != null) {				
			String[] TagArr = postTag.split(",");
			model.addAttribute("TagArr",TagArr);
		}else {
			model.addAttribute("TagArr","");
		}
		
		if(auth == null) {

			
			model.addAttribute("post", post);

			model.addAttribute("Markchk", "false");
			model.addAttribute("Heartchk", "false");
			model.addAttribute("heartNo", heartNo);
			
			viewName="myblog/postView";
		}else {
			member = ((MemberPrincipal)auth.getPrincipal()).getRealMember();
		}
		
		
		if(checkMember(memId, model)) {
			viewName="myblog/check";
		}else {
			if(member!=null) {
				
				PostMarkVO postmark = new PostMarkVO();
				postmark.setMemId(member.getMemId());
				postmark.setPostNum(postNum);
				BlogHeartVO heart = new BlogHeartVO();
				heart.setMemId(member.getMemId());
				heart.setPostNum(postNum);
				boolean Markchk = writeService.MarkYn(postmark);
				boolean Heartchk = writeService.heartYn(heart);
				
				
				
				model.addAttribute("post", post);
				model.addAttribute("Markchk",Markchk);
				model.addAttribute("Heartchk", Heartchk);
				model.addAttribute("heartNo", heartNo);
				viewName="myblog/postView";
			}

		}
		return viewName;
	}
	
	//공유받은 게시글 보기
	@GetMapping("shareList") 
	public String mainPostView(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @PathVariable("memId") String memId
			, Model model 
			) {
		String viewName=null;
		
		RoleGroup result = check.checkBlog(memId);
		if(result.equals(RoleGroup.OWNER)) {
				
				PagingVO<MyBlogPostVO> pagingVO = new PagingVO<>(8, 5);
				pagingVO.setCurrentPage(currentPage);
				
				Map<String, Object> map = new HashMap<>();
				map.put("memId", memId);
				map.put("pagingVO", pagingVO);
				
				blogService.retrieveShareList(map, pagingVO);
				
				model.addAttribute("pagingVO", pagingVO);
				
				viewName="myblog/shareList";
			
		} else {
			throw new NotAuthorityException();
		}
		

		return viewName;
	}
	
	public boolean checkShareList(String memId, String postNum) {
		Map<String, String> map = new HashMap<>();
		map.put("memId", memId);
		map.put("postNum", postNum);
		
		int checkId = myBlogDAO.checkShareYn(map);
		
		boolean shreYn = true;
		
		if(checkId > 0) {
			shreYn = false;
		}
		
		return shreYn;
	}
	
	//게시글 공유자 추가
	@ResponseBody
	@GetMapping(value="post/{postNum}/share") 
	public void postShare(
			@PathVariable("postNum") String postNum
			, @PathVariable("memId") String memId
			, @RequestParam("receiverId") String receiverId
			) {
		
		RoleGroup result = check.checkBlog(memId);
		
		if(result.equals(RoleGroup.OWNER)) {
			if(checkShareList(receiverId, postNum)) {
				ShareVO share = new ShareVO();
				share.setPostNum(postNum);
				share.setReceiverId(receiverId);
				
				blogService.postShare(share);
			}
		} else {
			throw new NotAuthorityException();
			
		}
		
	}
	
	//게시글 공유자 목록
	@ResponseBody
	@GetMapping(value="post/{postNum}/shareList", produces=MediaType.APPLICATION_JSON_UTF8_VALUE) 
	public PagingVO<ShareVO> postShareList(
			@PathVariable("postNum") String postNum
			, @PathVariable("memId") String memId
			, @RequestParam(value="page", required=false, defaultValue="1") int currentPage
			) {
		
		PagingVO<ShareVO> pagingVO = null;

		RoleGroup result = check.checkBlog(memId);
		if(result.equals(RoleGroup.OWNER)) {
			pagingVO = new PagingVO<>(8, 5);
			pagingVO.setCurrentPage(currentPage);
			blogService.postShareMemberList(postNum, pagingVO);
		} else {
			throw new NotAuthorityException();
		}
		
		return pagingVO;
	}
	
	
	//블로그 카테고리
	@GetMapping("cate/{cateNm}") 
	public String blogCate(
			@PathVariable("memId") String memId
			, @PathVariable("cateNm") String cateNm
			, @RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @RequestParam(value="searchWord", required=false) String searchWord
			, Model model 
			) throws ParseException {
		String viewName=null;
		if(checkMember(memId, model)) {
			viewName="myblog/check";
		}else {
			
			SimpleSearchCondition searchVO = new SimpleSearchCondition();
			searchVO.setSearchWord(searchWord);
			
			PagingVO<MyBlogPostVO> pagingVO = new PagingVO<>(8, 5);
			pagingVO.setCurrentPage(currentPage);
			pagingVO.setSimpleCondition(searchVO);
			
			Map<String, Object> map = new HashMap<>();
			map.put("memId", memId);
			map.put("pagingVO", pagingVO);
			map.put("cateNm", cateNm);
			
			blogService.retrievePostList(map, pagingVO);
			
			model.addAttribute("pagingVO", pagingVO);
			viewName="myblog/blogMain";
		}
		return viewName;
	}
	

	
	//게시글 비공개
	@GetMapping("post/{postNum}/private") 
	public String privatePost (
			@PathVariable("memId") String memId
			, @PathVariable("postNum") String postNum
			, @RequestParam("lock") String lock
			, Model model 
			, RedirectAttributes redirectAttributes 
			) {
		
		String viewName=null;

		RoleGroup result = check.checkBlog(memId);
		switch (result) {
		case OWNER:
			Map<String, String> map = new HashMap<>();
			map.put("postNum", postNum);
			map.put("lock", lock);
			
			ServiceResult postPrivate = blogService.postPrivate(map);
			String message = null;
			
			if(postPrivate.equals(ServiceResult.OK)) {
				message = "변경되었습니다.";
				viewName = String.format("redirect:/blog/%s/post/%s", memId, postNum);
			} else {
				message = "다시 시도해주세요.";
				viewName = String.format("redirect:/blog/%s/post/%s", memId, postNum);
			}
			
			redirectAttributes.addFlashAttribute("message", message);
			break;
		case NOTOWNER:
			throw new NotAuthorityException();
			
		case GUEST:
			viewName = "redirect:/login";
			break;
			
		}
		
		return viewName;
	}
	
	//게시글 삭제
	@GetMapping("post/{postNum}/del") 
	public String delPost (
			@PathVariable("memId") String memId
			, @PathVariable("postNum") String postNum
			, Model model 
			, RedirectAttributes redirectAttributes 
			) {
		String viewName=null;
		
		RoleGroup result = check.checkBlog(memId);
		switch (result) {
		case OWNER:
			
			if(checkMember(memId, model)) {
				viewName="myblog/check";
			}else {
				
				ServiceResult delPost = blogService.delPost(memId, postNum);
				String message = null;
				
				if(delPost.equals(ServiceResult.OK)) {
					message = "삭제되었습니다.";
					viewName = String.format("redirect:/blog/%s", memId);
				} else {
					message = "다시 시도해주세요.";
					viewName = String.format("redirect:/blog/%s/%s", memId, postNum);
				}
				
				redirectAttributes.addFlashAttribute("message", message);
			}
			
			break;
		case NOTOWNER:
			throw new NotAuthorityException();
			
		case GUEST:
			viewName = "redirect:/login";
			break;
			
		}
		
		return viewName;
	}
	
	//공유 삭제
	@ResponseBody
	@GetMapping("post/{postNum}/delShare") 
	public void delShare (
			@PathVariable("postNum") String postNum
			, @PathVariable("memId") String memId
			, @RequestParam("receiverId") String receiverId
			) {
		
		RoleGroup result = check.checkBlog(memId);
		if(result.equals(RoleGroup.OWNER)) {
			Map<String, String> map = new HashMap<>();
			map.put("receiverId", receiverId);
			map.put("postNum", postNum);
			
			blogService.delShare(map);
		} else {
			throw new NotAuthorityException();
		}
		
	}
	
	
	
}
