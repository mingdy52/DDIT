package basic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class T09_SessionListenerTest extends HttpServlet{
	// 리스너: 이벤트에 따른 작업을 하고 싶을 때
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// HttpSession 객체 생성 및 소멸
		HttpSession session = req.getSession();
//		session.invalidate(); // 세션 삭제
		
		session.setAttribute("ATTR1", "속성1");
		session.setAttribute("ATTR1", "속성11");
		session.setAttribute("ATTR2", "속성2");
		session.removeAttribute("ATTR1");
		
		session.invalidate(); // 세션 삭제
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
