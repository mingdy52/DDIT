package kr.or.ddit.servlet09;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sample/useTiles.do")
public class SampleUseTilesServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String view = "/sample/sampleView.tiles";
		req.getRequestDispatcher(view).forward(req, resp);
		
	}
	
}
