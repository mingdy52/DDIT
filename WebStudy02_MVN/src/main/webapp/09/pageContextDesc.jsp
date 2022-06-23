<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.page"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>09/pageContext.jsp</title>

<jsp:include page="/includee/preScript.jsp" />

<%
	pageContext.include("/includee/preScript.jsp");
	// include 사용법
%>
</head>
<body>
	<h4> PageContext pageContext </h4>
	<pre>
		: 기본객체 중 가장 먼저 생성되며, 나머지 기본 객체에 대한 레퍼런스를 가진 객체
		
		1. 기본객체 확보(EL), ${pageContext } EL 안에서 유일하게 사용할 수 있는 객체
		2. attribute 관리 기능(setAttribute, getAttribute, removeAttribute) : 4개의 기본객체가 가진 scope 를 참조할 수 있는 기능.
			<%
				request.setAttribute("reqAttr", "요청 속성");
				request.getAttribute("reqAttr");
				
				pageContext.setAttribute("reqAttr", "요청 속성", pageContext.REQUEST_SCOPE);
				pageContext.getAttribute("reqAttr", pageContext.REQUEST_SCOPE);
			%>
		3. 에러 데이터 확보
			1) 로컬처리 방식 - 모든 에러 페이지에 태그를 넣어줘야하기 때문에 잘 사용하지 않음.
			2) 글로벌 처리 방식(web.xml)
			<%
				ErrorData ed = pageContext.getErrorData(); // 에러가 발생했을 때의 데이터를 가짐, 에러를 처리하는 jsp가 있다면 거기서 사용
				int sc = ed.getStatusCode(); // 0이 나옴.
				out.println(sc);
			%>
		4. 흐름제어 (dispatch) - pageContext 안에 request가 있기 때문에 가능
			<%
// 				request.getRequestDispatcher("/09/implicitObject.jsp").include(request, response);
		
// 				pageContext.forward("/09/implicitObject.jsp");
// 				pageContext.include("/09/implicitObject.jsp");
			%>
			<jsp:include page="/09/implicitObject.jsp" />
			custom tag 사용 방법 : <prefix:태그명 속성명="속성값" />
		추가 코드
	</pre>
	
	<jsp:include page="/includee/postScript.jsp" />
</body>
</html>