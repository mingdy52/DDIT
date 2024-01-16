<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="kr.or.ddit.db.ConnectionFactory"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
데이터베이스로부터 a001을 조회하고, 한번 브라우저에 출력
<%
	long start = System.currentTimeMillis();
	String sql = "SELECT * FROM MEMBER WHERE MEM_ID = 'a001'";
	for(int i = 1; i <= 100; i++){
		try(
				
			Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();
				
		){
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				out.println(rs.getString("MEM_NAME"));
			}
		}
		
	}
	
	long end = System.currentTimeMillis();
%>
소요시간 : <%=end - start %>ms
</body>
</html>