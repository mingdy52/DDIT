<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>08/flowControl.jsp</title>
</head>
<body>
<h4>웹어플리케이션에서 페이지 이동</h4>
<pre>
	1. Request Dispatch (요청 분기) : 이동하는 과정에서 원본 요청을 그대로 전달하는 구조.
		1) forward : Model2 구조에서 활용. request 처리자(Controller)와 response 처리자(View)가 분리되는 구조
		2) include(내포) : A와 B가 응답에 대한 책임을 나눠갖는 구조, 하나의 페이지가 여러 view layer 에 의해 형성되는 모듈화 구조(페이지 모듈화)
	
	<%--
// 		request.getRequestDispatcher("/07/sem_registForm.jsp").forward(request, response);
		request.getRequestDispatcher("/07/sem_registForm.jsp").include(request, response);
		// 서버내에서 움직이니까 주소를 저렇게
	--%>
	
	HTTP 의 기본 특성 : Connectless(비연결 특성), Stateless(무상태 특성)
	
	2. Redirect
		1) A 방향으로 요청 발생
		2) A에서 클라이언트로 1) 요청에 대한 응답 전송(302 코드, location 헤더, Body 가 없음.)
		3) location 헤더의 값인 B로 새로운 요청이 발생.
		4) body(message)를 포함한 최종 응답 전송 
	
	<%--
// 		response.sendRedirect("/07/sem_registForm.jsp");
		// 주소를 세팅할 때는 그 주소를 누가 사용하느냐에 따라 형태가 달라짐. 위 주소는 3단계에서 클라이언트가 사용함.
		
		response.sendRedirect(request.getContextPath() + "/07/sem_registForm.jsp");
	--%>
</pre>

</body>
</html>