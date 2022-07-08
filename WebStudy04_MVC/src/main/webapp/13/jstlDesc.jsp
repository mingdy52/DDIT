<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>13/jstlDesc.jsp</title>
</head>
<body>
	<h4>JSTL(Jsp Standard Tag Library) : EL 과 함게 사용 (JSTL안에서 사용되는 모든 값은 속성임)</h4>
	<pre>
		: 커스텀 태그들 중 널리 쓰이는 것들을 모아놓은 라이브러리.
		1. core : 제어문에서 사용
			조건문
				- 단일 조건문 : if
				- 다중 조건문 : choose~when~otherwise
			반복문
				- 일반 반복문 : foreach(begin, step, end) -> for(int i = 0; i < 10; i++)
				- 향상된 반복문 : foreach(items, var) -> for(element 참조 블럭 변수 : collection)
			속성 제어 : set, remove
				- 속성 생성 : set (속성명 - var, 속성값 - value, 영역 - scope)
				- 속성 제거 : remove (속성명 - var, 영역 - scope)
			기타 : url(url rewriting), import, out
			<c:url value="/02/first.jsp" var="newURL">
<%-- 				클라이언트 사이드, 쿼리 스트링, 세션 파라미터를 경우에 따라 붙여준다.  --%>
				<c:param name="name1" value="value1"/>
				<c:param name="name2" value="value2"/>
			</c:url>
			${newURL }
				
		2. fmt : parsing, formatting
			<c:set var="today" value="<%=new Date() %>"></c:set>
			<fmt:formatDate value="${today }" dateStyle="long" timeStyle="long" type="both" var="todayStr"/>
			포맷팅 후 : ${todayStr }
			<fmt:parseDate value="${todayStr }" dateStyle="long" timeStyle="long" type="both" var="today2"/>
			파싱 후 : ${today2 }, ${today2.time } --> getTime()
			<%-- type= date, time, both --%>
			
		3. function : ${fn:replace("abc", "b", "B") }, ${"abc".replace("b", "B") }
	</pre>
	
<c:if test="${empty sample }">
	"sample 속성 없음."
</c:if> 

<%-- else 없음. 쓰려면 if 한번 더% --%>
<c:if test="${not empty sample }">
	"sample 속성 있음."
</c:if> 

<c:choose>
	<c:when test="${empty sample }">
		"sample 속성 없음."
	</c:when>
	
<%-- 	else --%>
	<c:otherwise>
		"sample 속성 있음.
	</c:otherwise>
</c:choose>

<%-- 증가밖에 안됌, 스텝이 1이면 생략가능, var는 변수가 아니고 속성이기 때문에 EL을 사용해야함. --%>
<c:forEach begin="1" step="1" end="10" var="i">
	${i }
<%-- 	i는 스코프 사용 안함. 이럴 경우 페이지 스코프로 처리됌 	 --%>
</c:forEach>

<%--
	pageContext.setAttribute("array", new String[]{"value1", "value2"});
--%>
<%-- 스코프 지정이 없으면 페이지가 기본 --%>
<c:set var="array" value='<%=new String[]{"value1", "value2"} %>' scope="page"/>

<c:forEach items="${array }" var="element">
	${element }
</c:forEach>

<c:remove var="array" scope="page"/>
<c:if test="${not empty array }">
	배열 있음
</c:if>
<c:if test="${empty array }">
	배열 없음
</c:if>

<c:import url="https://www.naver.com" var="naver"></c:import>
<%-- include와 같음. 다른 서비스를 가져와서 중계하고 싶을 때. --%>
\${naver } <%-- 지금 네이버는 페이지 스코프에 저장되어 있음 --%>

<c:out value="${naver }" escapeXml="false"></c:out>
<%-- escapeXml = true 브라우저의 꺽세문자를 다 이스케이프 시킴 --%>
</body>
</html>