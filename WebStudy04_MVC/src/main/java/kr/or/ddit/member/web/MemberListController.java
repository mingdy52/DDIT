package kr.or.ddit.member.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.RequestHeader;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SimpleSearchCondition;

/**
 * 회원 목록 조회를 위한 Controller Layer
 *
 */
@Controller
public class MemberListController{
	private static final Logger log = LoggerFactory.getLogger(MemberListController.class); 
	
	MemberService service = new MemberServiceImpl();
	private String processHTML(){
		return  "/member/memberList.tiles";
	}
	private String processJsonData(
			@RequestParam(value="searchType", required=false) String searchType
			, @RequestParam(value="searchWord", required=false) String searchWord
			, @RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, HttpServletRequest req
	) {
		SimpleSearchCondition searchVO = new SimpleSearchCondition(searchType, searchWord);
		log.info("searchType : {}, searchWord : {}", searchType, searchWord);
		
		PagingVO<MemberVO> pagingVO = new PagingVO<>(5, 3);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSimpleCondition(searchVO);
		
		service.retrieveMemberList(pagingVO);
		
		req.setAttribute("pagingVO", pagingVO);
		
		
		return  "forward:/jsonView.do";
	}
	
	@RequestMapping(value="/member/memberList.do", method=RequestMethod.GET)
	public String memberList(
			@RequestHeader("accept") String accept
			, @RequestParam(value="searchType", required=false) String searchType
			, @RequestParam(value="searchWord", required=false) String searchWord
			, @RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, HttpServletRequest req
	){
//		String accept = req.getHeader("accept");
		String viewName = null;
		if(StringUtils.containsIgnoreCase(accept, "json")) {
			viewName = processJsonData(searchType, searchWord, currentPage, req);
		}else {
			viewName = processHTML();
		}
		return viewName;
	}
}















