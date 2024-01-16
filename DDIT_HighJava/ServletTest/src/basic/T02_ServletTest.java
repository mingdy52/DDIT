package basic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T02_ServletTest extends HttpServlet {
/*
 *  서블릿 동작 방식에 대하여
 *  1. 사용자(클라이언트)가 URL을 클릭하면 HTTP Request를 Servlet Container로 전송(요청)한다.
 *  2. 컨테이너는 web.xml에 정의된 url 패턴을 확인하여 어느 서블릿을 통해 처리해야 할지를 검색한다.
 *  	(로딩이 안된 경우에는 로딩함. 로딩시 init()메서드 호출됨.)
 *  3. 서블릿 컨테이너는 요청을 처리할 개별 스레드 객체를 생성하여 해당 서블릿 객체의 service()를 호출한다.
 *  	(HttpServletRequest 및 Http ServletResponse객체를 생성하여 파라미터로 넘겨준다.)
 *  4. service() 메서드는 메서드 타입을 체크하여 적절한 메서드를 호출한다.
 *  	(doGet, doPost, doPut, doDelete 등.)
 *  5. 요청 처리가 완료되면, HttpServletRequest 및 ServletResponse 객체는 소멸한다.
 *  6. 컨테이너로부터 서블릿이 제거되는 경우에 destroy()가 호출된다.
 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Request 객체의 메서드 확인하기
		System.out.println("getCharacterEncoding() : " + req.getCharacterEncoding());
		System.out.println("getContentLength() : " + req.getContentLength());
		System.out.println("getQueryString() : " + req.getQueryString());
		System.out.println("getProtocol() : " + req.getProtocol());
		System.out.println("getMethod() : " + req.getMethod());
		System.out.println("getRequestURI() : " + req.getRequestURI());
		System.out.println("getRequestedSessionId() : " + req.getRequestedSessionId());
		System.out.println("getRequestDispatcher() : " + req.getRequestDispatcher("/"));
		System.out.println("getHeaderNames() : " + req.getHeaderNames());
		System.out.println("getRemoteAddr() : " + req.getRemoteAddr());
		System.out.println("getRemotePort() : " + req.getRemotePort());
		
		// 
	}
	
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// TODO Auto-generated method stub
			super.doPost(req, resp);
		}
}
