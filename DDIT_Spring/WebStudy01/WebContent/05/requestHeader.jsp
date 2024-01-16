<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/requestHeader.jsp</title>
</head>
<body>
<h4> 요청 헤더의 종류 </h4>
<table>
	<thead>
		<tr>
			<th>헤더이름</th>
			<th>헤더값</th>
		</tr>
	</thead>
	<tbody>
	<%
		String pattenr = "<tr><td>%s</td><td>%s</td></tr>";
		Enumeration<String> headerNames = request.getHeaderNames();
		while(headerNames.hasMoreElements()){
			String name = (String) headerNames.nextElement();
			String value = request.getHeader(name);
		
			out.println(String.format(pattenr, name, value));
	 } 
	
	%>
	</tbody>
</table>
<pre>
	요청 헤더 : 클라이언트와 요청에 대한 부가 정보를 key/value 형태로 표현한 데이터.
</pre>
</body>
</html>