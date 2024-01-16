package kr.or.ddit.member.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.GridTemplateViewResolver;
import kr.or.ddit.mvc.InternalResourceViewResolver;
import kr.or.ddit.mvc.DelegatingViewResolver;
import kr.or.ddit.vo.MemberVO;

/**
 * 
 * 회원 목록 조회를 위한 Controller Layer
 *
 */
@WebServlet("/member/memberList.do")
public class MemberListServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberService service = new MemberServiceImpl();
		// 인터페이스 기반이기 때문에 처리 소스가 없어도 가능.
		
		List<MemberVO> memberList = service.retrieveMemberList();
		
		req.setAttribute("memberList", memberList);
		
//		req.setAttribute("contents", "/WEB-INF/views/member/memberList.jsp");
//		String view = "/WEB-INF/views/template.jsp";
//		req.getRequestDispatcher(view).forward(req, resp);
		
//		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//		resolver.setPrefix("/WEB-INF/views/");
//		resolver.setSuffix(".jsp");
		
		DelegatingViewResolver resolver = new DelegatingViewResolver();
		String viewName = "/WEB-INF/views/member/memberList.jsp" + GridTemplateViewResolver.GRIDSUFFIX;
		resolver.viewResolve(viewName, req, resp);
	}
	
}
