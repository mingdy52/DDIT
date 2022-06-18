package kr.or.ddit.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

@WebServlet("/login/loginProcess.do")
public class LoginProcessServlet extends HttpServlet {
	
	private boolean authenticate(String id, String password) {
		return id.equals(password);
	}
	
	private boolean validate(String id, String password) {
		return StringUtils.isNoneBlank(id) && StringUtils.isNoneBlank(password);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/*
		1. 검증
			- 통과
				2. 처리(로그인 여부 판단)
					Post-Redirect-Get 패턴
					- 로그인 성공 : welcome 페이지로 이동(redirect) - get
					
					- 실패 : loginForm 으로 이동(forward) - post
					
			- 불통 : loginFrom 으로 이동(forward) - post
			
*/
			
	}
	
}
