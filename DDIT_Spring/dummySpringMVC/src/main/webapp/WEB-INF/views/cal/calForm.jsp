<%@page import="kr.or.ddit.cal.OperatorType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
</head>
<body>
	<input type="radio" name="dataType" data-type="html" checked/>HTML
	<input type="radio" name="dataType" data-type="json" />JSON
	<form method="post">
		<input type="number" name="operand1" required value="${calVO.operand1 }"/>
		<select name="operator" required>
			<option value>연산자</option>
			<c:forEach items="<%=OperatorType.values() %>" var="single">
				<option value="${single.name()}" ${single eq calVO.operator ? "selected" : "" }>${single.sign}</option>
			</c:forEach>
		</select>
		<input type="number" name="operand2" required value="${calVO.operand2 }"/>
		<input type="submit" value="=" />
	</form>
	<div id="resultArea">
	${calVO.expression }
	</div>
</body>

<script type="text/javascript">
	$("form:first").on("submit", function(event){
		event.preventDefault();
		
		let resultArea = $("#resultArea")
		
		let url = this.action;
		let method = this.method;
		let data = $(this).serialize();
		let dataType = $("[name='dataType']:checked").data("type");
		$.ajax({
			url : url,
			method : method,
			data : data,
			dataType : dataType,
			success : function(resp){
				
					$("#resultArea").html(resp.expression);
				
					$("#resultArea").html(resp);
					
					$("input[name=operand1]").val(resp.operand1);
					$("input[name=operand1]").val(resp.operand1);
					$("select[name=operator]").val(resp.operator);
				
			},
			error : function(jqXHR, status, error) {
				console.log(jqXHR);
				console.log(status);
				console.log(error);
			}
		
		});
		return false;
		
	});
</script>
</html>
