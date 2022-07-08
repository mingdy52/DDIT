<%@page import="kr.or.ddit.enumpkg.OsKind"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/userAgent.jsp</title>
</head>
<body>
	<%
		String pattern = "당신의 OS는 \"%s\" 입니다.";
		String userAgent = request.getHeader("user-agent");
// 		userAgent = userAgent.toUpperCase();
		
		String os = OsKind.findOsName(userAgent);
		
// 		Map<String, String> osMap = new HashMap<>();
// 		osMap.put("WINDOWS", "윈도우");
// 		osMap.put("ANDROID", "안드로이드");
// 		osMap.put("IPHONE", "아이폰");
// 		osMap.put("UNKNOWN", "식별불가 OS");
		
// 		os = OsKind.UNKNOWN.getOs();
		
// 		for(String key : osMap.keySet()){
// 		for(OsKind osName : OsKind.values()){
// 			os = osName.getOs();
// 			if (os != null){
// 				break;
// 			}
// 		}
		
		/*
		if (userAgent.contains("WINDOWS")) {
			os = "윈도우";
			
		} else if (userAgent.contains("ANDROID")) {
			os = "안드로이드";
			
		} else if (userAgent.contains("IPHONE")) {
			os = "아이폰";
			
		} else {
			os = "식별불가 OS";
		}
		*/
		
		String message = String.format(pattern, os);
		request.setAttribute("userAgent", userAgent);
	%>
	<%=OsKind.IPHONE %>
	
<pre>
	클라이언트의 시스템을 파악하고, 해당 OS에 대한 정보를 alert 창으로 랜더링.
	(당신의 OS는 "windows"입니다.)
</pre>
<script>
	alert('<%=message%>');
</script>
</body>
</html>