<%@page import="java.util.Objects"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
// 	전역 변수, 전역 메소드 : 공통 사용(공유)
// 	A, B -> Scope
	
	private long factorial(int num){
		if (num < 0) throw new IllegalArgumentException("음수에 대해서는 연산 불가");
		if(num == 0) {
			return 1;
		} else {
			return num * factorial(num-1);
		}
}
%>
<%
	String param = request.getParameter("number");
%>
<!DOCTYPE html>
<html>
<head>
<script 
src="https://code.jquery.com/jquery-3.6.0.min.js" 
integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" 
crossorigin="anonymous">
</script>
<meta charset="UTF-8">
<title>04/factorial.jsp</title>
</head>
<body>
<h4>Factorial 연산 처리</h4>
<!-- 1. Model1 -> Model2 -> ajax -> XML/JSON(Marshalling) -->

<pre>

10!
스크립트릿 기호만으로 반복 곱하기 연산 수행.
<form action="<%=request.getContextPath() %>/04/factorial.do">
<!-- <form action="" method="" enctype="application/x-www-form-urlencoded"> -->
<!-- action 파라미터를 넘겨받아 처리할 대상. 명령어_요청URL. 생략하면 브라우저는 데이터를 어떻게든 넘겨야 하기 때문에 상단의 주소에 데이터를 넘긴다. -->
<!-- method 파라미터 전송 방법 & 요청의 목적 default : get -->
<!-- enctype 요청 데이터의 표현 방식 -->
<%-- 	<input type="number" name="number" value="<%=param %>" min="0"/> --%>
	<input type="text" name="number" value="<%=Objects.toString(param, "") %>" min="0"/>
	타입을 텍스트로 할 경우 null 뜸. 
	<input type="submit" value="=" min="0"/>
</form>
<%
	// 클라이언트가 보내는 모든 데이터는 검증이 필요하다.
	if(param != null && param.matches("\\d+")){ 
		int num = Integer.parseInt(param);
		long result = factorial(num);
		
	
%>
	<h4><%=num %> factorial = <%=result %></h4>
	<div id="d1"></div>
<%
	} else if(param != null && !param.matches("\\d+")) {
		response.sendError(HttpServletResponse.SC_BAD_REQUEST, "팩토리얼 연산은 양수와 숫자만으로 처리");
		return;
	}
%>	
</pre>

<script>
var xhr = new XMLHttpRequest(); 
//XMLHttpRequest (XHR) 객체는 서버와 상호작용할 때 사용합니다. XHR을 사용하면 페이지의 새로고침 없이도 URL에서 데이터를 가져올 수 있습니다. 
xhr.onload = function(){
	// 브라우저가 서버로부터 응답을 받을 때 발생하는 이벤트. 이벤트가 발생하면 함수가 호출된다. 
	// 익명 함수는 xhr 객체의 status 속성 값을 검사해 서버의 응답이 정상인지 확인한다.
	if(xhr.status == 200) {
		document.getElementById('#d1').innerHTML = xhr.responseTest
	}
};
xhr.open('GET', '<%=request.getContextPath() %>/04/factorial.do', true);
// 세 개의 매개변수(HTTP 메서드/요청 처리할 페이지의 URL/요청이 비동기로 처리될 것인지 지정하는 불리언 값)를 정의한다.
xhr.send();
// 준비된 요청을 추가 정보와 함께 전달한다.
</script>
</body>
</html>