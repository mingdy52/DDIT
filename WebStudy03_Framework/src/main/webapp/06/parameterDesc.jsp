<%@page import="java.util.Map"%>
<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06/parameterDesc.jsp</title>
</head>
<body>
<h4>요청 파라미터 전달 방식과 사용 방법</h4>
<form method="post">
	<input type="text" name="param1" value="value1">
	<input type="text" name="param1" value="value2">
	<input type="number" name="param2" value="234">
	<input type="text" name="param3" value="한글파라미터">
	<input type="submit" value="전송">
</form>

<%

	request.setCharacterEncoding("UTF-8");
	Map<String, String[]> parameterMap = request.getParameterMap();
	if(parameterMap.size() > 0){
		
%>
<pre>
	param1 : <%=Arrays.toString(request.getParameterValues("param1")) %>
	param2 : <%=Integer.parseInt(request.getParameter("param2")) %>
	param3 : <%=request.getParameter("param3") %>
</pre>
<%
	}
%>
</body>
</html>