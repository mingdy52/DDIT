<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	Enumeration<String> nameAttr =  request.getAttributeNames();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<pre>
	등록 완료된 학생의 이름 :
	<%
		while(nameAttr.hasMoreElements()) {
			String name = nameAttr.nextElement();
			String[] value = request.getParameterValues(name);
	%>
		<%=name %> : <%=value %>
	<%
		}
	%>
</pre>
</body>
</html>