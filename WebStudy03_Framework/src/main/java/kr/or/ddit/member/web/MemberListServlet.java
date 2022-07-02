package kr.or.ddit.member.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.commons.JsonMarshallingServlet;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.InternalResourceViewResolver;
import kr.or.ddit.mvc.DelegatingViewResolver;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SimpleSearchCondition;

/**
 * 
 * 회원 목록 조회를 위한 Controller Layer
 *
 */
@WebServlet("/member/memberList.do")
public class MemberListServlet extends HttpServlet {
	MemberService service = new MemberServiceImpl();
	// 인터페이스 기반이기 때문에 처리 소스가 없어도 가능.
	
	private void processHTML(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DelegatingViewResolver resolver = new DelegatingViewResolver();
		String viewName = "/member/memberList.tiles";
		resolver.viewResolve(viewName, req, resp);
	}
	
	private void processJsonData(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String pageParam = req.getParameter("page");
		
		String searchType = req.getParameter("searchType"); // 두개는 필수 파라미터가 아니니까 검증의 대상이 아님.
		String searchWord = req.getParameter("searchWord");
		SimpleSearchCondition searchVO = new SimpleSearchCondition(searchType, searchWord);
		
		int currentPage = 1;
		
		if(StringUtils.isNumeric(pageParam)) {
			currentPage = Integer.parseInt(pageParam);
		}
		
		PagingVO<MemberVO> pagingVO = new PagingVO<>(5, 3);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSimpleCondition(searchVO);
		service.retrieveMemberList(pagingVO);
		
		req.setAttribute("pagingVO", pagingVO);
		
		
		DelegatingViewResolver resolver = new DelegatingViewResolver();
		String viewName = "forward:/jsonView.do";
		resolver.viewResolve(viewName, req, resp);
		
//		req.setAttribute("contents", "/WEB-INF/views/member/memberList.jsp");
//		String view = "/WEB-INF/views/template.jsp";
//		req.getRequestDispatcher(view).forward(req, resp);
		
//		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//		resolver.setPrefix("/WEB-INF/views/");
//		resolver.setSuffix(".jsp");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accept = req.getHeader("accept");
		
		if(StringUtils.containsIgnoreCase(accept, "json")) {
			processJsonData(req, resp);
		} else {
			processHTML(req, resp);
		}
		
	}
	
}
