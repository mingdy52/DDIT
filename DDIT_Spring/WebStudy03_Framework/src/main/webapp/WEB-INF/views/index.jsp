<%@page import="kr.or.ddit.vo.MemberVOWrapper"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



	<h4>웰컴 페이지</h4>
	<h4>현재 인증 객체: ${pageContext.request.userPrincipal.realMember.memId},${pageContext.request.userPrincipal.name }</h4>
	<c:choose>
		<c:when test="${empty authMember }">
		   <a href="${cPath }/login/loginForm.jsp">로그인</a>
		   <a href="${cPath }/member/memberInsert.do">회원가입</a>
		</c:when>
		<c:otherwise>
			<a href="${cPath }/myPage.do">${authMember['memName'] }[${authMember['memRole'] }]</a>님
	      <form method="post" action="${pageContext.request.contextPath}/login/logout.do" id="logoutForm"></form>
	      <a href="${pageContext.request.contextPath}/login/logout.do" id="logoutBtn">로그아웃</a>
	      <script>
	      	$("#logoutBtn").on("click", function(event){
	      		event.preventDefault();
	      		$(this).prev("form:first").submit();
	      		return false;
	      	});
	      </script>
		</c:otherwise>
	
	</c:choose>

	<div>누적 방문자 수 : ${userCount }</div>
