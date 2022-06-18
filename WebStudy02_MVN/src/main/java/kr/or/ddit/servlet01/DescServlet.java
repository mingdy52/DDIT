package kr.or.ddit.servlet01;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Alt+Shift+J
 * Servlet
 * 웹에서 발생하는 요청을 받고 그에 대한 응답을 생성할 수 있는 어플리케이션의 조건을 모아놓은 명세서.
 * 서블릿 개발 단계
 * 1. HttpServlet 을 상송받은 소스 정의
 * 2. 컴파일(-d) : /WEB_INF/classes(어플리케이션의 classpath) 에 클래스를 배포
 * 3. WAS에 서블릿 등록(web.xml -ver2.5, @WebServlet- ver3.0)
 * 	 -servlet
 * 		-> servlet -name(required)
 *      -> servlet-class(required)
 *      load -on -startup, init-parm (optional)
 * 4. 서블릿 매핑(web.xml-ver2.5, @WebServlet)
 * 	 -servlet-mapping
 * 		-> servlet-name
 * 		-> url-pattern
 * 5. WAS restart (auto-reload 설정시 생략 가능) ->처음시작할때 (web.xml)읽으므로 재가동해줘야함
 * 
 *   **WAS(Web Application Server) -Web Container, Servlet Container
 *     container ?? 컴포넌트(컨테이너안의 물건이라 생각)의 생명주기 관리자.
 *     Servlet Container ?? servlet 의 생명주기 관리자. -> 생명주기 관리내에서 callback 을 호출.
 *     $("#button").on("click",function(){}); ->콜백함수 JQuery내부에서 호출되고 있음 
 *     callback ?? 특정 컨텍스트 내에서 어떤 이벤트가 발생했을 때 (특정 조건이 만들어졌을 때), 시스템 내부에서 자동으로 호출되는 구조.
 *     생성 - init
 *     요청 - service .doXXX
 *     소멸 - destroy
 */
//@WebServlet(value="/desc.do", loadOnStartup=1, initParams= {@WebInitParam(name="param1",value="value1")})
public class DescServlet extends HttpServlet{
	
	public DescServlet() {   //Alt+Shift+S+밑에서 두번째
		super();
		System.out.println(this.getClass().getName()+"생성");
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.println(this.getClass().getName()+"초기화");
		String initValue = config.getInitParameter("param1");
		System.out.println("param1의 값 -"+initValue);
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("요청 처리 시작");
		super.service(req, resp);   //http 요청의 method (7개)를 파악하고, 해당 callback을 호출
		System.out.println("요청 처리 종료");
	}
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().println("Desc Servlet");
		System.out.println("do Get 실행");
		
	}
	 
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
		System.out.println(this.getClass().getName()+"소멸"); 
	}

}
