package kr.or.ddit;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.DelegatingViewResolver;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;

//@WebServlet("/index.do")
@Controller
public class IndexController {
	
	@RequestMapping("/")
	public String indexPage() {
		String viewName = "/index.tiles";
		return viewName;
				
	}

}
