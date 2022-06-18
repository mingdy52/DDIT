<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	request.setCharacterEncoding("utf-8");
	String message = request.getParameter("message");
	
	if(StringUtils.isBlank(message)){
		message = (String) session.getAttribute("message");
	}
	
	if(StringUtils.isNoneBlank(message)){
		%>
		<script type="text/javascript">
			alert("<%=message%>");
		</script>
		<%
		session.removeAttribute("message"); // Flash Attribute
		
	}
%>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.6.0.min.js"></script>
</head>
<body>
	<h4>웰컴 페이지</h4>
	<%
   MemberVO authMember =(MemberVO)session.getAttribute("authMember");
   if(authMember==null){
   %>
   <a href="<%=request.getContextPath() %>/login/loginForm.jsp">로그인</a>
   <% 
   }else{
      %>
      <%=authMember.getMemId() %>님
      <form method="post" action="${pageContext.request.contextPath}/login/logout.do" id="logoutForm"></form>
      <a href="${pageContext.request.contextPath}/login/logout.do" id="logoutBtn">로그아웃</a>
      <script>
      	$("#logoutBtn").on("click", function(event){
      		event.preventDefault();
      		$(this).prev("form:first").submit();
      		return false;
      	});
      </script>
      <%
   }
%>
</body>
</html>