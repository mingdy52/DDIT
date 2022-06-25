<%@page import="kr.or.ddit.db.ConnectionFactory"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.vo.DataBasePropertyVO"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>11/jdbcDesc.jsp</title>
</head>
<body>
	<h4>JavaDataBaseConnectivity - JDBC Driver</h4>
	<pre>
		JDBC 단계
		1. 벤더가 제공하는 드라이버를 빌드패스에 추가(pom.xml)
		2. 드라이버 로딩
		3. Connection 수립
		4. Query 객체 생성
		   Statement : 기본 쿼리 객체로 모든 쿼리 객체의 상위.
		   PreparedStatement(선컴파일된 쿼리 객체) : 쿼리 객체 생성시 미리 고정된 쿼리문에 의해 컴파일된 쿼리 객체가 생성.
		   CallableStatement : procedure(반환값 x: out bound procedure)/function 등 일련의 명령 집합객체를 실행할 때.
		   
		5. Query 실행
			DML
		   executeQuery : SELECT - 가져오는 메소드
		   (rowcount) int executeUpdate : INSERT, UPDATE, DELETE - 가져오는게 없는 메소드
		   DCL
		   Create, Alter, Drop - 데이터를 조작하기 위한 구조가 아니기 때문에 execute를 사용하는게 맞지는 않지만 아무거나 사용해도 무방함.
		   ** 클라우드 형태로 한다면 사용할수도? 사용자마다 만들어줘야 하니까!
		   
		6. 자원 해제(close)
			서버의 가용 능력은 무한대로 존재할 수 없기 때문에.-- try~~with
		
	</pre>
<table>
	<%
		List<DataBasePropertyVO> dataList = (List) request.getAttribute("dataList");
		for(DataBasePropertyVO tmp : dataList) {
			%>
			<tr>
				<td><%=tmp.getPropertyName() %></td>
				<td><%=tmp.getPropertyValue() %></td>
				<td><%=tmp.getDescription() %></td>
			</tr>
			<%
		}
	%>
</table>
</body>
</html>