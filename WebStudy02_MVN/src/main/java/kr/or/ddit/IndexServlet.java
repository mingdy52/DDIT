package kr.or.ddit;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.DelegatingViewResolver;
import kr.or.ddit.mvc.GridTemplateViewResolver;

@WebServlet("/index.do")
public class IndexServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		req.setAttribute("contents", "/WEB-INF/views/index.jsp");
//		String view = "/WEB-INF/views/template.jsp";
//		req.getRequestDispatcher(view).forward(req, resp);
		String viewName = "/WEB-INF/views/index.jsp"+ GridTemplateViewResolver.GRIDSUFFIX;
		new DelegatingViewResolver().viewResolve(viewName, req, resp);
		
//		resp.sendRedirect(req.getContextPath() + view);
		// view는 클라이언트가 접근하는 경로인데 WEB-INF는 보안폴더 때문에 sendRedirect로 접근할 수 없음.
		// Redirect는 클라이언트가 다시 요청하게 하는 방식이기 때문에.
				
	}

}
