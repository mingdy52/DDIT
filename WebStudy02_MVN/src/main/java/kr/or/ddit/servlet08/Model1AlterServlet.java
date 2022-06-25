package kr.or.ddit.servlet08;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.mvc.DelegatingViewResolver;
import kr.or.ddit.mvc.GridTemplateViewResolver;

@WebServlet("/model1/service.do")
public class Model1AlterServlet extends HttpServlet {
	
	/**
	 * 
	 * enum 은 그 자체로 immutable object 의미를 포함함.
	 *
	 */
	public static enum ServiceType {
		
		FACTORIAL("/04/factorial2.jsp"), CALENDAR("/06/sem_calendar.jsp");
		
		private String jspPath;

		private ServiceType(String jspPath) {
			this.jspPath = jspPath;
		}
		
		public String getJspPath() {
			return jspPath;
		}
		
		
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String cmdParam = req.getParameter("command");
		
		if(StringUtils.isBlank(cmdParam)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "필수 파라미터 누락");
			return;
		} 
		
		try {
			ServiceType command = ServiceType.valueOf(cmdParam);
			String contents = command.getJspPath();
//			req.setAttribute("contents", contents);
//			String view = "/WEB-INF/views/template.jsp";
//			req.getRequestDispatcher(view).forward(req, resp);
			new DelegatingViewResolver().viewResolve(contents + GridTemplateViewResolver.GRIDSUFFIX, req, resp);
		} catch (IllegalArgumentException e) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, "제공하지 않는 서비스");
		}
		
		
		
		
	}
	
	
}

