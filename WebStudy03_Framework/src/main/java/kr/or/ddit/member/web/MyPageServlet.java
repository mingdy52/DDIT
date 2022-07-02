package kr.or.ddit.member.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.DelegatingViewResolver;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/myPage.do")
public class MyPageServlet extends HttpServlet {
	MemberService service = new MemberServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberVO authMember = (MemberVO) req.getSession().getAttribute("authMember");
		String memId = authMember.getMemId();
		MemberVO member = service.retrieveMember(memId);
		req.setAttribute("member", member);
		String viewName = "/member/myPage.tiles";
		new DelegatingViewResolver().viewResolve(viewName, req, resp);
	}
}
