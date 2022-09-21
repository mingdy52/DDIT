package kr.or.ddit.blog.controller;

import java.util.List;

import javax.inject.Inject;

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

import kr.or.ddit.blog.service.BlogWriteService;
import kr.or.ddit.blog.service.MyBlogService;
import kr.or.ddit.blog.vo.BlogCateVO;
import kr.or.ddit.blog.vo.BlogHeartVO;
import kr.or.ddit.blog.vo.PostMarkVO;
import kr.or.ddit.common.login.vo.MemberPrincipal;
import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.common.vo.SimpleSearchCondition;
import kr.or.ddit.exception.NotAuthorityException;
import kr.or.ddit.expert.service.ExpertService;
import kr.or.ddit.expert.vo.ExpWishVO;

@Controller
public class BookMarkController {

	@Inject
	BlogWriteService blogservice;
	
	@Inject
	MyBlogService MyblogService;
	
//	사이드메뉴
		@ModelAttribute("blogCateList")
		public List<BlogCateVO> blogCateList(
				@PathVariable("memId") String memId
				) {
			MemberVO member = ((MemberPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
					.getRealMember();
			
			List<BlogCateVO> blogCateList =  MyblogService.retrieveCate(memId);
			return blogCateList;
		}
	
	@RequestMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE, value="{memId}/saveMark",method = RequestMethod.POST)
	@ResponseBody
	public String saveMark(@PathVariable("memId") String memId, @RequestParam String postNum) {
		
		MemberVO member = ((MemberPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getRealMember();
		PostMarkVO postmark = new PostMarkVO();
		postmark.setMemId(member.getMemId());
		postmark.setPostNum(postNum);
		
		blogservice.createBookMark(postmark);
		
		
		return "{\"message\": \"추가.\"}";
	}
	
	@RequestMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE, value="{memId}/deleteMark",method = RequestMethod.POST)
	@ResponseBody
	public String deleteMark(@PathVariable("memId") String memId,@RequestParam String postNum) {

		MemberVO member = ((MemberPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getRealMember();
		PostMarkVO postmark = new PostMarkVO();
		postmark.setMemId(member.getMemId());
		postmark.setPostNum(postNum);
		blogservice.deleteBookMark(postmark);
		
		
		return "{\"message\": \"삭제.\"}";
	}
	
	
	@RequestMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE, value="{memId}/savePostHeart",method = RequestMethod.POST)
	@ResponseBody
	public String savePostHeart(@PathVariable("memId") String memId,@RequestParam String postNum) {
		
		MemberVO member = ((MemberPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getRealMember();
		BlogHeartVO heart= new BlogHeartVO();
		heart.setMemId(member.getMemId());
		heart.setPostNum(postNum);
		blogservice.createHeart(heart);
		
		blogservice.modifyHeartNo(postNum);
		
		
		return "{\"message\": \"추가.\"}";
	}
	
	@RequestMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE, value="{memId}/delPostHeart",method = RequestMethod.POST)
	@ResponseBody
	public String deletePostHeart(@PathVariable("memId") String memId,@RequestParam String postNum
								,Model model) {

		MemberVO member = ((MemberPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getRealMember();
		BlogHeartVO heart= new BlogHeartVO();
		heart.setMemId(member.getMemId());
		heart.setPostNum(postNum);
		blogservice.deleteHeart(heart);
		blogservice.modifyHeartNo(postNum);
		return "{\"message\": \"삭제.\"}";
	}
	
	
	@GetMapping("blog/{memId}/bookmark")
	public String postMarkList(
			@PathVariable("memId") String memId
			,@ModelAttribute("simpleCondition") SimpleSearchCondition simpleCondition
			,@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			 , Model model) {
		MemberVO member = ((MemberPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getRealMember();
		
		if(memId.equals(member.getMemId())) {
			PagingVO<PostMarkVO> pagingVO = new PagingVO<>(8,5);
			PostMarkVO postmark = new PostMarkVO();
			postmark.setMemId(memId);
			pagingVO.setCurrentPage(currentPage);
			pagingVO.setSimpleCondition(simpleCondition);
			pagingVO.setDetailCondition(postmark);
			blogservice.retrieveMyMarkList(pagingVO);
			
			model.addAttribute("bookSize", pagingVO.getDataList().size());
			model.addAttribute("pagingVO",pagingVO);
			
		}else {
			throw new NotAuthorityException();
		}

		
		return "myblog/markList";
	}
	
}
