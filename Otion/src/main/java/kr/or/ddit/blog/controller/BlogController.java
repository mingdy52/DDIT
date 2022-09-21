package kr.or.ddit.blog.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.blog.service.BlogListService;
import kr.or.ddit.blog.vo.MyBlogPostVO;
import kr.or.ddit.common.login.vo.MemberPrincipal;
import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.common.vo.SimpleSearchCondition;
import lombok.extern.slf4j.Slf4j;


/**
 * 
 * @author 이아인
 * @since 2022. 8. 5.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 *      [[개정이력(Modification Information)]]
 *       수정일 수정자 수정내용
 *        -------- --------
 *      ---------------------- 
 *      2022. 8. 5. 이아인 최초작성
 *      2022. 8 . 26 박채록 블로그리스트 
 *       Copyright (c) 2022 by DDIT
 *      All right reserved 메인의 블로그의 트렌딩, 최신순을 띄워준다.
 */
@Controller
@Slf4j
@RequestMapping("/blog")
public class BlogController {
	@Inject
	BlogListService blogService;
	
	public String blogMain() {

		return "blog/trend";
	}

	@GetMapping("trend")
	public String TrendList() {
		return "blog/trend";
	}
	
	

	@GetMapping("latest")
	public String latestList() {
		return "blog/latest";

	}
	
	@GetMapping("search")
	public String search() {
		return "blog/search";
		
	}
	
	@ResponseBody
	@GetMapping(value="latestList", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PagingVO<MyBlogPostVO> getLatestList(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			){
		
			PagingVO<MyBlogPostVO> pagingVO = new PagingVO<>(6, 5);
			pagingVO.setCurrentPage(currentPage);
			blogService.retrieveLatestList(pagingVO);
			
			return pagingVO;
	}
	
	@ResponseBody
	@GetMapping(value="trendList", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<MyBlogPostVO> getTrendList(
			){
		
		List<MyBlogPostVO> trendList = blogService.retrieveTrendList();
		return trendList;
	}
	
	@ResponseBody
	@GetMapping(value="searchList", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PagingVO<MyBlogPostVO> getSearchList(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @RequestParam(value="searchWord", required=false) String searchWord
			){
		
		SimpleSearchCondition searchVO = new SimpleSearchCondition();
		searchVO.setSearchWord(searchWord);
		
		PagingVO<MyBlogPostVO> pagingVO = new PagingVO<>(9, 5);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSimpleCondition(searchVO);
		
		blogService.retrieveSearchList(pagingVO);
		
		return pagingVO;
	}
	

}
