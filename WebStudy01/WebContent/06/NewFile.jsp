<%@page import="java.text.DateFormatSymbols"%>
<%@page import="java.util.Calendar"%>
<%@page import="static java.util.Calendar.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Calendar cal = Calendar.getInstance();
	
	String yearParam = request.getParameter("year");	
	String monthParam = request.getParameter("month");
	
	if(yearParam != null && yearParam.matches("[0-9]{4}") 
			&& monthParam != null && monthParam.matches("\\d{1,2}")){
		cal.set(YEAR, Integer.parseInt(yearParam));
		cal.set(MONTH, Integer.parseInt(monthParam));
	}

	int year = cal.get(YEAR);
	int month = cal.get(MONTH);

	cal.set(DAY_OF_MONTH, 1);
	int firstDay = cal.get(DAY_OF_WEEK);
	int offset = firstDay - 1;
	int lastDay = cal.getActualMaximum(DAY_OF_MONTH);
	
	cal.add(MONTH, -1);
	int beforeYear = cal.get(YEAR);
	int beforeMonth = cal.get(MONTH);
	
	cal.add(MONTH, 2);
	int nextYear = cal.get(YEAR);
	int nextMonth = cal.get(MONTH);

	cal.add(MONTH, -1);
	
	DateFormatSymbols dfs = new DateFormatSymbols();
	String[] weekdays = dfs.getWeekdays();
	String[] months = dfs.getMonths();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	table{
		width: 100%;
		height: 500px;
		border-collapse: collapse;
	}
	th, td {
		border: 1px solid black;
	}
</style>
</head>
<body>

<h4>
<a href="#">이전달</a>
<%=String.format("%1$tY, %1$tm", cal) %>
<a href="#">다음달</a>
</h4>
<form>
	<input type="number" value="<%=year %>" name="year" maxlength="4">
	<select name="month">
		<%
			String optPattern = "<option %s value='%s'>%s</option>";
			for(int idx = JANUARY; idx <= DECEMBER; idx++){
				String selected = idx == month ? "selected" : "";
				
				out.println(String.format(optPattern, selected, idx+"", months[idx]));
			}
		%>
	</select>
</form>

<table>
	<thead>
		<tr>
			<%
				String thPattern = "<th>%s</th>";
				for(int idx = SUNDAY; idx <= SATURDAY; idx++){
					out.println(String.format(thPattern, weekdays[idx]));
				}
			%>
		</tr>
	</thead>
	<tbody>
		<%
			int count = 1;
			String tdPattern = "<td>%s</td>"; 
			for(int rows =1; rows <= 6; rows++){
				out.println("<tr>");
				for(int cols = SUNDAY; cols <= SATURDAY; cols++){
					int dayCount = count++ - offset;
					String dayStr = null;
					
					if(dayCount > 0 && dayCount <= lastDay){
						dayStr = Integer.toString(dayCount);
					} else {
						dayStr = "&nbsp;";
					}
					
					out.println(String.format(tdPattern, dayStr));
				}
						
				out.println("</tr>");
				
			}
				
		%>
	</tbody>
</table>
<script type="text/javascript">

	let inputs = $(":input[name]").on("change", function(event){
		this.form.submit();
	});

</script>
</body>
</html>