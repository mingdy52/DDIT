package kr.or.ddit.login;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;

@Controller
public class LogoutController{
	@RequestMapping(value="/login/logout.do", method=RequestMethod.POST)
	public String doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if(session==null || session.isNew()) {
			resp.sendError(400);
			return null;
		}
		session.invalidate();
		String message = URLEncoder.encode("로그 아웃", "UTF-8");
		return String.format("redirect:/?message=%s", message);
//		resp.sendRedirect(String.format("%s%s%s", req.getContextPath(), "/?message=", message));
	}
}













