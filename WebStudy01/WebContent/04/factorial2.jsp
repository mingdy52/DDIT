<%@page import="java.util.Objects"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.6.0.min.js"></script>
<!-- <script type="text/javascript"> -->
<!-- // 	$(document).ready(function(){ -->
<!-- // 	let facForm = $("form[name]"); -->
<!-- // 	console.log(facForm); -->
		
<!-- // 	}); -->
<!-- // 	$(function(){ -->
<!-- // 		ready: 코드 위치와 상관 없이 모든 코드가 준비되면 실행(인터프리터 언어기 때문). -->
<!-- // 		스크립트는 가독성 + , 안정적이기 위해 바디 아래쪽에 넣는게 좋음 -->
<!-- // 	}); 위 함수와 같은 기능 -->
<!-- </script> -->
<meta charset="UTF-8">
<title>04/factorial.jsp</title>
</head>
<body>
<h4>Factorial 연산 처리</h4>
<!-- 1. Model1 -> Model2 -> ajax -> XML/JSON(Marshalling) -->

<pre>

10!
스크립트릿 기호만으로 반복 곱하기 연산 수행.
<form action="<%=request.getContextPath()%>/04/factorial.do" name="facForm" method="get">
 
 <input type="number" name="number" min="0" value="" />
 <input type="text" name="dummy" value="asas"/>
 <input type="submit" value="="/>
 
</form>


<div id="resultArea"></div>

<script type="text/javascript">
	let resultArea = $("#resultArea"); //변수로 받아놓는게 좋음
	let facForm = $("form[name]").on("submit",function(event){
	event.preventDefault();

		// form 의 submit 이벤트의 기본 특성은 동기 요청
		// 모든 언어의 이벤트에는 이벤트를 줄 타켓이 들어있다.
		console.log(event.target);
        console.log(this); // 위 이벤트와 같음
// 		$(this)-> jQuery 객체화

		let action = this.action; //$(this).attr("action");
		let method = this.method; 
		let data = $(this).serialize(); // Query String 생성 ex) param1 = value1&param2 = value2
		// jquery ajax로 호출하기 전에 serialize를 해주면 form 안에 값들을 한 번에 전송 가능한 data로 만들 수 있어 많은 data를 보낼 때 유용하다.
		console.log(data)
// 		alert(data) // number=10&dummy=asas

		$.ajax({
			url : action,
			method : method,
			data : data,
			dataType : "html", // 응답데이터의 형태 - request headers에 나타남.
			success : function(resp/*html*/, status, jqXHR) {
				// {"expression":"6! = 720"}
				resultArea.html(resp); // html
// 				resultArea.html(resp.expression); // json
	// 			alert(resp.test)
	
			},
			error : function(jqXHR, status, error) {
				console.log(jqXHR);
				console.log(status);
				console.log(error);
		
			}
		});
		return false;
	});
	console.log(facForm);
</script>
</body>
</html>