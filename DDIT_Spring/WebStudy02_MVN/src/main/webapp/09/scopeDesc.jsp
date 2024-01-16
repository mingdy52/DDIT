<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>10/scopeDesc.jsp</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
</head>
<body>
	<h4>Scope(영역)</h4>
	<pre>
		:웹 어플리케이션에서 데이터를 공유하는 방법, 공유할 데이터(attribute)를 저장하는 공간(scope)
		- 뚜렷한 생명주기를 가진 기본 객체들이 자기 생명주기와 동일하게 관리하고 있는 map(name/value).
		setAttribute(name, value), getAttribute(name), removeAttribute(name)
		Enumeration getAttributeNames
		
		1. page scope
			: PageContext 와 생명주기를 갖고, 해당 jsp 페이지 내에서만 사용되는 영역(일반적인 상황에서 거의 사용되지 않고 cumstom 태그에서 주로 사용).
		2. request scope
			: ServletRequest 와 생명주기가 동일. 한 요청에서만 제한적으로 사용되는 영역. 
			  하나의 요청이 생성될 때 생성되고 소멸될 때 소멸함.(응답이 나가면 소멸)
		3. session scope 
			: HttpSession 와 생명주기가 동일하며, 해당 세션의 클라이언트가 사용할 수 있는 영역.
		4. application scope 
			: ServletContext - ServletContext 와 생명주기가 동일한 저장구조로 어플리케이션 전체에 걸쳐 공유되는 영억.
			  scope 중 가장 영역이 넓음.
		
		<%
			pageContext.setAttribute("pageAttr", "페이지 속성");
			request.setAttribute("requestAttr", "요청 속성");
			session.setAttribute("sessionAttr", "세션 속성"); // flash attribute -> flashmap managet
			application.setAttribute("pageAttr", "어플리케이션 속성");
			
// 			pageContext.include("/10/destination.jsp");
// 			pageContext 는 jsp 하나를 말함. 각 페이지는 각각의 pageContext 를 가지고 있음. include 로 페이지를 벗어났기 때문에 pageContext를 사용할 수 없음.

			response.sendRedirect(request.getContextPath() + "/10/destination.jsp");
			
		%>
	</pre>
		
<jsp:include page="/includee/postScript.jsp"></jsp:include>
</body>
</html>