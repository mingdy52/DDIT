<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>09/sessionDesc.jsp</title>
</head>
<body>
	<h4>HttpSession session</h4>
	<pre>
		: 클라이언트의 한 세션과 관련된 모든 정보를 가진 객체
		
		세션??
			WEB-연관된 작업을 수행하고 있는 한 타임의 시간(!).
			DB-두 피어 사이에 데이터를 주고받기 위해 수립된 통로(!).
			
		세션 생성
			: 클라이언트로부터 최초의 요청 발생시
			생성 시점 : <%=new Date(session.getCreationTime()) %>
			아이디 : <%=session.getId() %>
			마지막 요청 시간 : <%=new Date(session.getLastAccessedTime()) %>
			<%=session.getMaxInactiveInterval() %>s
			<%--
				global 설정으로 session-config 사용
				session.setMaxInactiveInterval(2*60);
			--%>
<%-- 			<%=session.getMaxInactiveInterval() %>s --%>
			
			** 세션 유지 방법(session id)
			session tracking mode
			1) Cookie : 양 피어가 서로를 식별하기 위한 식별자(session ID)를 쿠키의 형태로 저장하는 구조
						<%=session.getId() %>, ${cookie.JSESSIONID.value }
			2) URL <a href="sessionDesc.jsp;jsessionid=<%=session.getId() %>">세션유지</a>
				: 쿠키가 허용되지 않는 환경에서 제한적으로 사용한다. 보안에 취약함. 
				  매트릭스 변수 중 세션 파라미터를 통해 세션 아이디를 유지하는 구조(보안 취약성)
				  
			3) SSL (Secure Socket Layout) : 오고가는 모든 데이터 암호화
				: session protocol 을 사용하는 경우 제한적으로 사용됨(암호화 -> openSSL 에서 확인)
				
		세션 소멸
			1) 명시적 로그아웃 : 로그아웃하면 즉시 사라짐
				<% session.invalidate(); %>
			2) Timeout 설정
				: 세션 생성 후 다음 요청이 발생할때 timeout 이내 시간 안에 요청이 발생하면, 세션이 유지되는 구조.
			3) 브라우저 종료
			4) 쿠키 삭제 --> 즉시 만료가 아닌 timeout 체킹 이후 만료.
	</pre>
</body>
</html>