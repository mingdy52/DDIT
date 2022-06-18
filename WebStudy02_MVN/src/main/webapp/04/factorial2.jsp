<%@page import="java.util.Objects"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.6.0.min.js"></script>
<meta charset="UTF-8">
<title>04/factorial.jsp</title>
</head>
<body>
<h4>Factorial 연산 처리</h4>
<!-- 1. Model1 -> Model2 -> ajax -> XML/JSON(Marshalling) -->

<pre>

10!
스크립트릿 기호만으로 반복 곱하기 연산 수행.
<input type="radio" name="datatype" value="html">HTML

<input type="radio" name="datatype" value="json">JSON

<input type="radio" name="datatype" value="xml">XML

<form action="<%=request.getContextPath()%>/04/factorial.do" name="facForm" method="get">
 
 <input type="number" name="number" min="0" value="" />
 <input type="text" name="dummy" value="asas"/>
 <input type="submit" value="="/>
 
</form>


<div id="resultArea"></div>

<script type="text/javascript">
	let resultArea = $("#resultArea"); //변수로 받아놓는게 좋음
	let successMap={
			json:function(resp){
				resultArea.html(resp.expression)
			},
			xml:function(resp){
				console.log("----------XML----------");
				console.log(resp);
				let expr = $(resp).find("expression")
				resultArea.html(expr)
			},
			html:function(resp){
				resultArea.html(resp)
			}
	}
	let facForm = $("form[name]").on("submit",function(event){
	event.preventDefault();

		console.log(event.target);
        console.log(this); // 위 이벤트와 같음

		let action = this.action; //$(this).attr("action");
		let method = this.method; 
		let data = $(this).serialize(); 
		console.log(data)
		
		let settings = {
				url : action,
				method : method,
				data : data,
				error : function(jqXHR, status, error) {
					console.log(jqXHR);
					console.log(status);
					console.log(error);
			
				}
			}
		settings.dataType = $("[name='datatype']:checked").val();
		console.log("datatype :" + settings.dataType);
		settings.success = successMap[settings.dataType];
		$.ajax(settings);
		return false;
	});
	console.log(facForm);
</script>
</body>
</html>