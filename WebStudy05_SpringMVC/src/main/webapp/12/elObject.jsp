<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>12/elObject.jsp</title>
</head>
<body>
	<h4>표현 언어 기본객체</h4>
	<%
		pageContext.setAttribute("pageAttr", "페이지 속성");
		request.setAttribute("requestAttr", "페이지 속성");
		session.setAttribute("sessionAttr", "페이지 속성");
		application.setAttribute("applicationAttr", "페이지 속성");
	%>
	<pre>
		1. Scope 객체 : (Map&lt;String, Object&gt;)pageScope, requestScope, sessionScope, applicationScope
			${pageScope.pageAttr } -> ${pageScope["pageAttr"] }
			${sessionScope.sessionAttr } -> ${sessionScope["sessionAttr"] }
			${requestAttr } -> ${requestScope.requestAttr } -> ${requestScope["requestAttr"] }
			
		2. Parameter 객체 (Map) : param(Map<String, String>), paramValues(Map<String, String[]>)
			<%=request.getParameter("paramName") %>, <%=request.getParameterValues("paramName") %>
			${param.paramName }, ${paramValues.paramName[0] }
			
		3. Header 객체(Map) : header(Map<String, String>), headerValues(Map<String, String[]>)
			<%=request.getHeader("accept") %> 
			${header.accept }
				-> ${header['accept'] }
				
			<%=request.getHeader("user-agent") %>
			${header.user-agent }
			${header['user-agent'] }
			
		
		4. Cookie 객체(Map) : cookie(Map<String, Cookie>) -- 쿠키 이름 하나로 밸류 가져옴
			<%
				// 클라이언트 쿠키는 리퀘스트를 타고~
				Cookie[] cookies = request.getCookies();
				Cookie finded = null;
				if(cookies != null) {
					for(Cookie tmp : cookies){
						if("JSESSIONID".equals(tmp.getName())){
							finded = tmp;
							break;
						}
					}
				}
				out.println("JSESSIONID : " + finded.getValue());
			%>
			EL --> ${cookie.JSESSIONID.value } -> ${cookie['JSESSIONID']['value'] } -- 객체 안에 소속된 레퍼런스를 꺼내옴.
			
		5. PageContext
			${pageContext.request.contextPath } -- 내부적으로 getContextPath 로 바뀜
			${cPath }
			
			${pageContext.session.id } -- getId <%= session.getId() %>
	</pre>
</body>
</html>