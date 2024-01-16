package basic;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T07_ServletContextText extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		  	서블릿 컨텍스트 객체에 대하여
		  	1. 서블릿 프로그램이 컨테이너와 정보를 주고받기 위한 메서드를 제공한다.
		  	ex) 파일의 MIME TYPE 정보 가져오기, 요청 정보 보내기, 로깅 등
		  	
		  	2. 웹애플리케이션당 1개씩 생성된다.
		  	
		  	3. 서블릿 컨텍스트 객체는 서블릿이 초기화 될 때 ServletConfig 객체를 통해서 얻을 수 있다.
		  	
		 */
		ServletContext ctx = req.getServletContext();
		
		System.out.println("서블릿 컨텍스트의 기본 경로: " + ctx.getContextPath());
		System.out.println("서버 정보: " + ctx.getServerInfo());
		System.out.println("서블릿 API 메이저 버전 정보: " + ctx.getMajorVersion());
		System.out.println("서블릿 API 마이너 버전 정보: " + ctx.getMinorVersion());
		System.out.println("배포기술자에 기술된 컨텍스트명: " + ctx.getServletContextName());
		System.out.println("모든 등록된 서블릿 목록 맵: " + ctx.getServletRegistrations().toString());
		System.out.println("파일에 대한 MIME타입 정보: " + ctx.getMimeType("a.mp3"));
		System.out.println("파일시스템상의 실제 경로: " + ctx.getRealPath("/"));
		
		// 로깅 작업(로그 파일)
		ctx.log("서블릿 컨텍스트를 이용한 로깅 작업 중 입니다.");
		
		// 속성값 설정
		ctx.setAttribute("attr1", "속성1");
		
		// 속성값 변경
		ctx.setAttribute("attr1", "속성2");
		
		System.out.println("attr1: " + ctx.getAttribute("attr1"));
		
		// 속성값 지우기
		ctx.removeAttribute("attr1");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
