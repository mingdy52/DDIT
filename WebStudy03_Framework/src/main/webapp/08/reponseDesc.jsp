<%@ page language="java" contentType="text/plain; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>08/responseDesc.jsp</title>
</head>
<body>
<h4> HTTP 응답 패키징 방식(HttpServletResponse 캡슐화) </h4>
<pre>
	response 구조
	1. Response Line : Protocol/version, status Code
		** Status Code : 요청 처리 성공/실패에 대한 상태 코드
		1) 100 : ing... Websocket, WebRTC(real time process 사용됨.)
		2) 200 : OK
		3) 300 : 요청이 처리되기 위해서는 클라이언트의 추가 액션이 필요함. 300 
			304(Not Modified) : cache 연관, 클라이언트가 캐싱한 데이터가 서버에서 변경된 적이 없는 최신 데이터임. -- 캐시 저장소를 뒤져봐라!
			302/307(Moved) : location(new address) 헤더와 함께 사용됨. - Redirect. 
			-- 300번대는  바뀐 자원의 위치를 알려주는 요청도 발생한다. 즉, 한 번에 한 쌍의 요청이 발생한다.
		4) 400 : fail(client side) - Bad Request
			404(NotFound) : url 오류
			405(Method Not Allowed) : method callback 이 구현되지 않은 경우
			401/403(access control) : 권한 오류
			406(unsupported media type) : 서비스 미디어 타입
		5) 500 : fail(server side) - Internal Server Error
		
	2. Response Header : 서버와 응답에 대한 meta data (name/value)
		1) content mime type 설정 : Content-Type
			- setHeader("Content-Type", "application/json")
			- setContentType(MIME)
			- page 지시자의 contentType 속성(JSP spec)
		2) cache control : Cache-Control, Expires [Pragma]
			<a href="cacheControl.jsp">cacheControl.jsp</a>
		3) auto request : Refresh
			<a href="refresh.jsp">refresh.jsp</a>
		4) flow control : Location
			<a href="flowControl.jsp">flowControl.jsp</a>
	3. Response Body(Content Body)
	<%
// 		HttpServletResponse.SC_BAD_REQUEST : 요청 검증에서 주로 사용됨.
// 		HttpServletResponse.SC_INTERNAL_SERVER_ERROR : 검증 통과 후 요청 처리에 문제가 발생시 사용됨.
// 	여기 까지 Line
// 		response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
// 		response.setStatus(HttpServletResponse.SC_CONTINUE);
// 		response.setXXX
// 	여기 까지 Header
// 		response.getWriter().write(buf)
// 		out(JSPWriter)
// 	body 설정
	%>
</pre>

</body>
</html>