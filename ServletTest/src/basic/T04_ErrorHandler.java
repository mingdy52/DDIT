package basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.basic.BasicTreeUI.TreeHomeAction;

public class T04_ErrorHandler extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("name", new String("홍길동"));
		
		System.out.println("name: " + req.getAttribute("name"));
		
		// 서블릿 예외정보 가져오기
		Throwable throwable = (Throwable) req.getAttribute(
				"javax.servlet.error.exception"); // 예외 객체
		Integer statusCode = (Integer) req.getAttribute(
				"javax.servlet.error.status_code"); // 에러 상태코드
		String message = (String) req.getAttribute(
				"javax.servlet.error.message"); // 에러 메시지
		String servletName = (String) req.getAttribute(
				"javax.servlet.error.servlet_name"); // 에러 발생한 서블릿 이름
		
		if(servletName == null) {
			servletName = "알 수 없는 서블릿 이름";
		}
		
		String requestURI = (String) req.getAttribute(
				"javax.servlet.error.request_uri"); // 에러 발생 url 정보
		
		if(requestURI == null) {
			requestURI = "알 수 없는 URI";
		}
		
		///////////////////////////////////////////////////////
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		String title = "에러/예외 정보";
		
		out.println("<!doctype html><html><head><title>" + title 
				+ "</title></head><body>");
		
		if(throwable == null && statusCode == null) {
			out.println("<h2>에러정보 없음</h2>");
		} else {
			out.println("<h2>예외/에러 정보</h2>");
			out.print("상태코드: " + statusCode + "<br><br>");
			out.print("에러(예외) 메시지: " + message + "<br><br>");
			out.print("서블릿 이름: " + servletName + "<br><br>");
			out.print("요청URI: " + requestURI + "<br><br>");
		}
		
		if(statusCode != null) {
			out.println("예외타입: " + throwable.getClass().getName() + "<br><br>");
			out.println("예외(에러) 메시지: " + throwable.getMessage());
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
