package basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T03_ServletParameterTest extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 요청 객체로부터 파라미터 데이터를 가져오는 방법
		// - getParameter(): 파라미터값이 한개인 경우에 가져올 때 사용함.
		// - getParameterValues(): 파라미터값이 여러개인 경우에 가져올 때 사용함. ex)checkbox
		// - getParameterNames(): request에 존재하는 모든 파라미터 정보를 가져올 때 사용함
		
		// post방식으로 넘어오는 body데이터를 인코딩 처리함. get방식은 톰캣의 URIEncoding 설정을 이용하여 처리함
		// 반드시 request에서 값을 가져오기 전에 먼저 설정해야 적용됨.
//		req.setCharacterEncoding("UTF-8");
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String gender = req.getParameter("gender");
		String hobby = req.getParameter("hobby");
		String rlgn = req.getParameter("rlgn");
		String[] food = req.getParameterValues("food");
		
		// 사용자가 던져준 매개값을 봄
		/////////////////////////////////////////////////////
		// 사용자가 확인할 수 있도록 함
		
		resp.setContentType("text/html");
//		resp.setCharacterEncoding("UTF-8");
		
		PrintWriter pw = resp.getWriter();
		pw.print("<html>");
		pw.print("<body>");
		pw.print("<p>username: " + username + "</p>");
		pw.print("<p>password: " + password + "</p>");
		pw.print("<p>gender: " + gender + "</p>");
		pw.print("<p>hobby: " + hobby + "</p>");
		pw.print("<p>rlgn: " + rlgn + "</p>");
		
		if(food != null) {
			for (String s : food) {
				pw.print("<p>food : " + s + "</p>");
			}
		}
		
		Enumeration<String> params = req.getParameterNames();
		
		while(params.hasMoreElements()) {
			String param = params.nextElement();
			pw.print("<p>파라미터 이름: " + param + "</p>");
		}
		
		pw.print("</body>");
		pw.print("</html>");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
