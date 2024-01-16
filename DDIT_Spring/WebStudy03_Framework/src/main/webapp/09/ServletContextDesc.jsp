<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>09/ServletContextDesc</title>
</head>
<body>
	CAC - Context Aware Computing
	
	<h4>ServletContextDesc application</h4>
	ServletConfig(1vs1) - 서블릿이 컨테이너에 의해 운영되는 과정에서 생성되는 메타 정보를 가진 객체
	<pre>
		: 서블릿이 운영되는 어플리케이션과 해당 어플리케이션이 운영되는 서버에 대한 정보를 가진 객체.
		컨텍스트 하나당 하나의 싱글턴 객체가 운영됨.
		
		*** 컨테이너와 대화하는 과정에서 사용.
		1. MIME 데이터 사용
			<%=application.getMimeType("test.jpg") %>
		2. 서버의 정보
			<%=application.getServerInfo() %>
			servlet spc version : <%=application.getMajorVersion() %>.<%=application.getMinorVersion() %>
		3. 로깅(log 기록)
		<%--
			request.getParts()
			이 메소드를 사용하기 위해서 서블릿 버전을 확인.
		--%>
		<%
			application.log("로그 메시지");
		%>
		클라이언트를 위한 메시지가 아님. 서버를 튜닝해야할 때
		4.(****) 웹리소스 확보 : /WebStudy02_MVN/src/main/webapp/resources/images/cat1.jpg -- 실제 파일 시스템 상에서 존재할 때 아래 경로에 존재함.
		(개발 환경) D:\gitRepository\super-verano\WebStudy02_MVN\src\main\webapp\resources\images\cat1.jpg
		(실행 환경) D:\B_Util\eGovFrameDev-3.9.0-64bit\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\WebStudy02_MVN\resources\images\cat01.jpg
				 D:\B_Util\eGovFrameDev-3.9.0-64bit\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0	-- tmp0 톰캣에 의해 관리. 즉, 톰캣을 찾으면 그 아래 리소스를 찾을 수 있음.	
				<%=application.getRealPath("/resources/images/cat1.jpg") %> -- 바뀌지 않는 경로/톰캣 이후의 경로
		<%
			String imageUrl = "/resources/images/cat1.jpg";
			File imageFile = new File(application.getRealPath(imageUrl));
			out.println("파일 크기 : "+ imageFile.length());
		%>
	</pre>
	<h4>servlet Context hashcode : <%=application.hashCode() %></h4>
	<a href="<%=request.getContextPath() %>/desc.do">/desc.do</a>
</body>
</html>