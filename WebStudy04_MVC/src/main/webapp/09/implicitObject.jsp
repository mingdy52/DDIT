<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>

	<h4>기본객체</h4>
	<pre class="bg-primary">
		:개발자가 직접 선언하거나 생성하지 않고, 서블릿 소스가 파싱되는 단계에서 자동 생성되는 객체.
		
		1. request(HttpServletRequest) : 요청에 대한 모든 정보를 가진 객체
		2. response(HttpServletResponse) : 응답에 대한 모든 정보를 가진 객체
		3. out(JSPWriter, PrintWriter) : 응답데이터를 버퍼에 기록(response Body)
										 버퍼를 제어하거나 버퍼의 정보를 확인할 때 사용.
			<a href="bufferDesc.jsp">bufferDesc.jsp</a>
		4. session(HttpSession) : 한 클라이언트가 사용하는 하나의 브라우저를 대상으로 생성된 세션에 대한 모든 정보를 가진 객체.
			<a href="sessionDesc.jsp">sessionDesc.jsp</a>
		5. application(ServletContext) : 서블릿 컨테이너와 커뮤니케이션하기 위해 사용되는 객체
										 서버와 현재 실행중인 어플리케이션에 대한 정보를 가진 객체
			<a href="ServletContextDesc.jsp">ServletContextDesc.jsp</a>
		6. config(ServletConfig) : 서블릿에 대한 메타 정보를 가진 객체
		7. page(Object) : 현재 JSP 페이지에 대한 인스턴스의 참조(필요하다면 차라리 this 를 써라)
		8. exception(Throwable) : 발생한 예외 정보를 가진 객체(isErrorPage 가 설정된 에러 처리 페이지에서 사용) 일반적인 페이지에서 사용하지 않음.
		9. ***pageContext(PageContext) : 현재 JSP 페이지에 대한 모든 객체를 소유한 객체.
		
<%-- 		<%= %> --%>
<%-- 		${ } :  자바 객체는 가져올 수 없지만 예외로 PageContext는 가져올 수 있다.  --%>
		
	</pre>
