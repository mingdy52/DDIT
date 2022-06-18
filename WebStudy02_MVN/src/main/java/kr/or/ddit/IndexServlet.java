package kr.or.ddit;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/index.do")
public class IndexServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String view = "/WEB-INF/views/index.jsp";
		req.getRequestDispatcher(view).forward(req, resp);
		
//		resp.sendRedirect(req.getContextPath() + view);
		// view는 클라이언트가 접근하는 경로인데 WEB-INF 때문에 sendRedirect는 사용할 수 없음.
				
	}

}
