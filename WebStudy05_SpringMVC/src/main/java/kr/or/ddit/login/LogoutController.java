package kr.or.ddit.login;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogoutController{
	@RequestMapping(value="/login/logout.do", method=RequestMethod.POST)
	public String doPost(HttpSession session) throws UnsupportedEncodingException {
		session.invalidate();
		String message = URLEncoder.encode("로그 아웃", "UTF-8");
		return String.format("redirect:/?message=%s", message);
//		resp.sendRedirect(String.format("%s%s%s", req.getContextPath(), "/?message=", message));
	}
}













