package kr.or.ddit.member.web;


import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SimpleSearchCondition;

/**
 * 회원 목록 조회를 위한 Controller Layer
 *
 */
@Controller
@RequestMapping(value="/member/memberList.do", method=RequestMethod.GET)
public class MemberListController{
	private static final Logger log = LoggerFactory.getLogger(MemberListController.class); 
	
	@Inject
	MemberService service;
	
	@RequestMapping
	public String processHTML(){
		return  "member/memberList";
	}
	
	@RequestMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String processJsonData(
			@RequestParam(value="searchType", required=false) String searchType
			, @RequestParam(value="searchWord", required=false) String searchWord
			, @RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, Model model
	) {
		SimpleSearchCondition searchVO = new SimpleSearchCondition(searchType, searchWord);
		log.info("searchType : {}, searchWord : {}", searchType, searchWord);
		
		PagingVO<MemberVO> pagingVO = new PagingVO<>(5, 3);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSimpleCondition(searchVO);
		
		service.retrieveMemberList(pagingVO);
		
		model.addAttribute("pagingVO", pagingVO);
		
		
		return  "jsonView";
	}
	

}















