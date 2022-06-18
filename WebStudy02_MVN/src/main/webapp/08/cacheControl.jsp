<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>08/cacheControl.jsp</title>
</head>
<body>

<h4> 응답 캐시 제어 헤더 </h4>
<pre>
	Pragma(HTTP/1.0)
	Cache-Control(HTTP/1.1) : 캐시 제어 여부, 캐시 데이터의 속성들을 표현하는 지시자.(public, private, max-age=seconds)
	Expires : 캐시 만료 시점 설정
	
	<%
		// 캐시를 저장하지 않을 때 : no-cache(O), no-store(X):
// 		response.setHeader("Pragma", "no-cache");
// 		response.setHeader("Pragma", "no-store");
// 		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Cac[cache-Control", "no-store");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE,2);
		response.setDateHeader("Expires",cal.getTimeInMillis());
	%>
</pre>

</body>
</html>