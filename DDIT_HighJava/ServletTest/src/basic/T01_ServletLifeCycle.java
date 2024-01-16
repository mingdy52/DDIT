package basic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T01_ServletLifeCycle extends HttpServlet {
	
	@Override
	public void init() throws ServletException {
		// 초기화 코드 작성
		System.out.println("init() 호출됨");
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 실제적인 작업 수행이 시작된는 지점(자바의 메인메서드 역할)
		System.out.println("service() 호출됨");
		super.service(req, resp); 
		// get/post에 따라 doGet, doPost 중 하나가 호출되도록 요청
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 메서드 방식이 get인 경우 호출됨
		System.out.println("doGet() 호출됨");
		
//		throw new ServletException("예외 발생~!");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 메서드 방식이 post인 경우 호출됨
		System.out.println("doPost() 호출됨");
	}
	
	@Override
	public void destroy() {
		// 객체 소멸시(컨테이너로부터 서블릿 객체 제거시) 필요한 코드 작성
		System.out.println("destroy() 호출됨");
	}
	
	
}
