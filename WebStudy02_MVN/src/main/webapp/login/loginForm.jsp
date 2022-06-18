<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login/loginForm.jsp</title>
<%
	String message = (String)session.getAttribute("message");
	if(StringUtils.isNoneBlank(message)){
		%>
<%-- 		${message} --> jstl --%>
		<script type="text/javascript">
			alert("<%=message%>");
		</script>
		<%
	}
%>
</head>
<body>
	<form action="${pageContext.request.contextPath }/login/loginProcess.do" method="post">
		<ul>
			<li><input type="text" name="memId" placeholder="아이디"><li>
			<li><input type="text" name="memPass"placeholder="비밀번호"><li>
			<li><input type="submit" value="로그인"><li>
		</ul>
	</form>
</body>
</html>