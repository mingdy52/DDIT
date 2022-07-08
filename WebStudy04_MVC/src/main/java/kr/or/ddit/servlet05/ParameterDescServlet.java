package kr.or.ddit.servlet05;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/05/parameterDesc.do")
@MultipartConfig
public class ParameterDescServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(req.getProtocol()               );
		System.out.println(req.getMethod()                 );
		System.out.println(req.getRequestURI()             );
		System.out.println(req.getRequestURL()             );
		System.out.println(req.getHeaderNames()            );
		System.out.println("파라미터 : "+ req.getParameter("param")       );
		System.out.println("파트 : "+ req.getPart("param")            );
		System.out.println(req.getInputStream().available());
	}
	
}
