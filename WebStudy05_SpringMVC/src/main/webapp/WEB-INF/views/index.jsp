<%@page import="kr.or.ddit.vo.MemberVOWrapper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<h4>안녕하세요 바보심민경페이지입니다.</h4>
<h4> 현재 인증 객체 : ${pageContext.request.userPrincipal.realMember.memId }, ${pageContext.request.userPrincipal.name }</h4>
<c:if test="${empty authMember }">
	<a href="${cPath}/login/loginForm.jsp">로그인</a>	
	<a href="${cPath}/member/memberInsert.do">회원가입</a>
</c:if>
<c:if test="${not empty authMember }">
	<a href="${cPath }/myPage.do">${authMember.memName }[${authMember.memRole }]</a>님
	<form id="logoutForm" method="post" action="${pageContext.request.contextPath }/login/logout.do"></form> 
	<a id="logoutBtn" href="${pageContext.request.contextPath }/login/logout.do">로그아웃</a>
	
	<img src="data:image/*;base64, ${authMember.base64MemImage }">
	
	<script type="text/javascript">
		$("#logoutBtn").on("click", function(event){
			event.preventDefault();
			$(this).prev("form:first").submit();
			return false;
		});
	</script>
</c:if>
현재 누적 방문자 수 : ${userCount }














