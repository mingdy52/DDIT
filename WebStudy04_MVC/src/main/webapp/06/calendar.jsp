<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Calendar cal = Calendar.getInstance();

	int pyear = cal.get(Calendar.YEAR);
	int pmonth = cal.get(Calendar.MONTH);
	
	String paramY = request.getParameter("year");
	String paramM = request.getParameter("month");
	
	int year = pyear;
	int month = pmonth;
	
	if(paramY != null) {
		year = Integer.parseInt(paramY);
	}
	if(paramM != null) {
		month = Integer.parseInt(paramM);
	}
	
	cal.set(year, month-1, 1);
	
	year = cal.get(Calendar.YEAR);
	month = cal.get(Calendar.MONTH);
	
	int endDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	int week = cal.get(Calendar.DAY_OF_WEEK);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style>
	.sun {
		color:red;
	}
	.sat {
		 color:blue;
	}
</style>
</head>
<body>
	<div>
		<a href="calendar.jsp?year=<%=year%>&month=<%=month - 1 %>">previous</a>
		<%=year %>, <%=month+1 %>
		<a href="calendar.jsp?year=<%=year%>&month=<%=month + 1 %>">next</a>
	</div>
	<form>
		year: <input type="text" name="year" value="<%=year %>">
		<laber for="month">month: </laber>
		<select name="month" id="month">
			<option value="1">January</option> 
			<option value="2">February</option>
			<option value="3">March</option>
			<option value="4">April</option>
			<option value="5">May</option>
			<option value="6">June</option>
			<option value="7">July</option>
			<option value="8">August</option>
			<option value="9">September</option>
			<option value="10">October</option>
			<option value="11">November</option>
			<option value="12">December</option>
		</select>
		<input type="submit" value="조회" class="btn btn-primary">
	</form>
	<table class="table table-bordered">
		<tr>
			<td class="sun">Sunday</td>
			<td>Monday</td>
			<td>Tuesday</td>
			<td>Wednesday</td>
			<td>Thursday</td>
			<td>Friday</td>
			<td class="sat">Saturday</td>
		</tr>
		<tr>
			<% for (int i = 1; i < week; i++) { %>
			<td>&nbsp;</td>
			<% } %>
			<% for (int j = 1; j <= endDay; j++) { 
				week++;
				if(week % 7 == 2) {
			%>
		</tr>
		<tr>
			<% } 
				if(week % 7 == 2){ 
			%>
			<td class="sun"><%=j %></td>
			<% } else if(week % 7 == 1) { %>
			<td class="sat"><%=j %></td>
			<% } else { %>
			<td><%=j %></td>
			<%} } %>
		</tr>
		
	</table>
</body>
</html>