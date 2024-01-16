<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
오늘 날짜 : <%=String.format("%1$ty %1$tm %1$td", Calendar.getInstance()) %>

이 JSP가 동작하기 위한 서블릿 찾기~!

</body>
</html>