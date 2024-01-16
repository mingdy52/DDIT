package kr.or.ddit.login;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;

//@WebServlet("/login/logout.do")
@Controller
public class LogoutServlet {
	
	@RequestMapping(value="/login/logout.do", method=RequestMethod.POST)
	public String logOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false); // 세션이 있으면 가져오지만 없으면 만들지말고 null값 가져와랑
		if(session == null || session.isNew()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
		
		session.invalidate(); // 세션 종료
		
		
		String message = URLEncoder.encode("로그 아웃", "UTF-8");
				
//		message=로그아웃
//		1. 띄어쓰기 불가
//		2. 특수문자 설정 필요
		
		String viewName = String.format("redirect:%s%s", "/?message=", message);
		return viewName;
		
		
	}
	
}
