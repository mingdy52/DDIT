package kr.or.ddit.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.vo.MemberVO;

@WebServlet("/login/loginProcess.do")
public class LoginProcessServlet extends HttpServlet {
	
	private boolean authenticate(MemberVO member) {
		return member.getMemId().equals(member.getMemPass());
	}
	
	private boolean validate(MemberVO member) {
		return StringUtils.isNoneBlank( member.getMemId()) && StringUtils.isNoneBlank(member.getMemPass());
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if(session.isNew()) { // 이 서블릿은 최초의 요청일 수 없음 (세션이 없을 수 없음) --> 문제 있다~~~~~
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		req.setCharacterEncoding("UTF-8"); // 모든 컨트롤러에서 제일 먼저 들어거야함!
		MemberVO member = new MemberVO();
		
		member.setMemId(req.getParameter("memId"));
		member.setMemPass(req.getParameter("memPass"));
		
		// 1. 검증
		boolean valid = validate(member);
		boolean redirect = false;
		String view = null;
		
		if(valid) {
//			- 통과
//				2. 처리(로그인 여부 판단)
				if(authenticate(member)) {
//					Post-Redirect-Get 패턴
//					- 로그인 성공 : welcome 페이지로 이동(redirect) - get
					session.setAttribute("message", "로그인 성공");
					session.setAttribute("authMember", member);
					redirect = true;
					// redirect로 메시지를 전하려면 redirect보다 생명주기가 긴 session을 이용한다. 또 다른 요청으로 넘어가니까.
					view = "/";
					
				} else {
//					- 실패 : loginForm 으로 이동(forward) - post
					session.setAttribute("message", "로그인 실패");
					redirect = true;
					view = "/login/loginForm.jsp";
					
				}
//					
			
		} else {
//			- 불통 : loginFrom 으로 이동(forward) - post
			session.setAttribute("message", "검증 실패");
			redirect = true;
			view = "/login/loginForm.jsp";
		}
		
		if(redirect) {
			resp.sendRedirect(req.getContextPath() + view);
		} else {
			req.getRequestDispatcher(view).forward(req, resp);
		}
//		1. 기존 요청의 전달 여부
//		2. forw: 서버사이드 redi: 클라이언트 사이트(+ context path)
		
	}
}
