<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="bg-primary">
		<pre>
			<%= pageContext.getAttribute("pageAttr")	 %>
			<%= request.getAttribute("requestAttr")		 %>
			<%= session.getAttribute("sessionAttr")		 %>
			<%
				session.removeAttribute("sessionAttr");
			%>
			
			<%= application.getAttribute("pageAttr")	 %>
		</pre>
	</div>
</body>
</html>