<%@page import="java.util.TimeZone"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.Calendar"%>

<!-- 1.7 이상부터 사용가능  -->
<%@page import="static java.util.Calendar.*"%>

<%@page import="java.text.DateFormatSymbols"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	
	request.setCharacterEncoding("UTF-8"); // 모든 요청에서 가장 첫번째 처리.
	
	String yearParam = request.getParameter("year");
	String monthParam = request.getParameter("month");
	String languageCode = request.getParameter("language");
	
	Calendar cal = Calendar.getInstance();
	Locale currentLocale =  request.getLocale(); // accept-language 의 최우선 로케일이 선택됨.
	
	if(yearParam != null && yearParam.matches("[0-9]{4}")
			&& monthParam!= null && monthParam.matches("\\d{1,2}")){
		cal.set(YEAR, Integer.parseInt(yearParam));
		cal.set(MONTH, Integer.parseInt(monthParam));
	}
	if(languageCode != null && !languageCode.isEmpty()){
		currentLocale = Locale.forLanguageTag(languageCode);
	}
		
	int year = cal.get(YEAR);
	int month = cal.get(MONTH);
	
	// 해당 1일의 요일 설정
	cal.set(DAY_OF_MONTH /*현재 월의 날짜*/, 1);
	int firstDay = cal.get(DAY_OF_WEEK /*현재 요일(일요일:1 ,토요일: 7)*/); // 요일 받아오기
	int offset = firstDay - 1;
	int lastDay = cal.getActualMaximum(DAY_OF_MONTH)/*현재 객체의 특정 필드의 최대 값을 반환한다.*/;
	
	cal.add(MONTH, -1);
	int beforeYear = cal.get(YEAR);
	int beforeMonth = cal.get(MONTH);
	
	cal.add(MONTH, 2);
	int nextYear = cal.get(YEAR);
	int nextMonth = cal.get(MONTH);
	
	cal.add(MONTH, -1);
	
	DateFormatSymbols dfs = new DateFormatSymbols(currentLocale);
	String[] weekDays = dfs.getWeekdays();
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
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.6.0.min.js">

</script>
</head>
<body>
<h4>
<a href="#" class="changeA" data-year="<%=beforeYear%>"  data-month="<%=beforeMonth%>">이전달</a>
<%=String.format("%1$tY, %1$tm", cal) %>
<a href="#" class="changeA" data-year="<%=nextYear%>"  data-month="<%=nextMonth%>">다음달</a>
</h4>
<form method="post">
	<input type="number" name="year" maxlength="4" value="<%=year%>"/>
	<select name="month">
		<%
			String optPattern = "<option %s value='%s'>%s</option>";
			for(int idx = JANUARY; idx <= DECEMBER; idx++){
				
				String selected = idx == month ?  "selected" : "";
					
				
				out.println(String.format(optPattern, selected, idx+"", months[idx]));
			}
		%>
	</select>
	<select name="language">
		<%
			Locale[] locales = Locale.getAvailableLocales();
			for(Locale tmp : locales){
				String langCode = tmp.toLanguageTag();
				String dl = tmp.getDisplayLanguage(tmp);
				String selected = tmp.equals(currentLocale) ?  "selected" : "";
									// 객체의 상태비교 
									
				if(dl !=null && !dl.isEmpty()){
					out.println(String.format(optPattern, selected, langCode, dl));
					
				}
					
			}
			
			
		%>
	
	</select>
	<input type="text" name="param" value="testValue">
</form>
	<table>
		<thead>
			<tr>
				<%	
					String thPattern = "<th>%s</th>";
					for(int idx = SUNDAY; idx <= SATURDAY; idx++){
						out.println(String.format(thPattern, weekDays[idx]));
					}
				%>
			</tr>
		</thead>
		<tbody>
				<%
					String tdPattern = "<td>%s</td>";
					int count = 1;
					for(int row = 1; row <= 6; row++){
						out.print("<tr>");
						for(int col = SUNDAY; col <= SATURDAY; col++){
							int dayCount = count++ - offset;
							String dayStr = null;
							if(dayCount > 0 && dayCount <= lastDay){
								dayStr = Integer.toString(dayCount);
								
							} else {
								dayStr = "&nbsp;";
							}
								out.println(String.format(tdPattern, dayStr));
						}
						out.print("</tr>");
					}
				%>
		</tbody>
	</table>
	
<script type="text/javascript">
	$(".changeA").on("click", function(event){
		event.preventDefault();
		let year = $(this).data('year') 
		let month = $(this).data('month')
		$("[name='year']").val(year);
		$("[name='month']").val(month);
		$(inputs[0]).trigger("change");
		return false;
	});
	
	let inputs = $(":input[name]").on("change", function(event){
		this.form.submit();
	});
	
</script>
</body>
</html>