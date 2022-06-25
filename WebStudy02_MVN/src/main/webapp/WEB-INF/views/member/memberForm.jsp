<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.Objects"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String message = (String) request.getAttribute("message");
	if(StringUtils.isNotBlank(message)){
		%>
			<script>
				alert("<%=message%>");
			</script>
		<%
	}
%>
	<h4>회원 가입 양식</h4>
	<%-- 다른 방식으로 표현가능
		MemberVO member = (MemberVO) request.getAttribute("member");
		if(member==null)
			member = new MemberVO();
		
		Map<String, String> errors = (Map) request.getAttribute("errors");
		if(errors==null)
			errors = new LinkedHashMap<>();
	--%>
	<jsp:useBean id="member" class="kr.or.ddit.vo.MemberVO" scope="request"/> 
	<jsp:useBean id="errors" class="java.util.LinkedHashMap" scope="request"/> 
<!-- 	리퀘스트에서 이 속성값이 있는 찾아보고 있으면 가져옴, 없으면 새로 만들어줌. -->

	<form method="post" enctype="application/x-www-form-urlencoded">
		<table>
			<tr>
				<th>회원아이디</th>
				<td>
					<input type"text" name="memId"  class="form-control" value='<%=Objects.toString(member.getMemId(), "") %>'/>
					<span class="error"><%=errors.get("memId") %></span>
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="text" name="memPass" value="${member.memPass }" />
					<span class="error">${errors['memPass'] }</span>
				</td>
			</tr>
			<tr>
				<th>회원명</th>
				<td><input type="text" name="memName"  /></td>
			</tr>
			<tr>
				<th>주민번호1</th>
				<td><input type="text" name="memRegno1" /></td>
			</tr>
			<tr>
				<th>주민번호2</th>
				<td><input type="text" name="memRegno2" /></td>
			</tr>
			<tr>
				<th>생일</th>
				<td><input type="date" name="memBir"  /></td>
			</tr>
			<tr>
				<th>우편번호</th>
				<td><input type="text" name="memZip"  /></td>
			</tr>
			<tr>
				<th>주소1</th>
				<td><input type="text" name="memAdd1"  /></td>
			</tr>
			<tr>
				<th>주소2</th>
				<td><input type="text" name="memAdd2"  /></td>
			</tr>
			<tr>
				<th>집전화번호</th>
				<td><input type="text" name="memHometel" /></td>
			</tr>
			<tr>
				<th>회사번호</th>
				<td><input type="text" name="memComtel" /></td>
			</tr>
			<tr>
				<th>휴대폰</th>
				<td><input type="text" name="memHp"  /></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" name="memMail"  /></td>
			</tr>
			<tr>
				<th>직업</th>
				<td><input type="text" name="memJob" /></td>
			</tr>
			<tr>
				<th>취미</th>
				<td><input type="text" name="memLike" /></td>
			</tr>
			<tr>
				<th>기념일</th>
				<td><input type="text" name="memMemorial" /></td>
			</tr>
			<tr>
				<th>기념일자</th>
				<td><input type="date" name="memMemorialday" /></td>
			</tr>
			<tr>
				<th>마일리지</th>
				<td><input type="number" name="memMileage" /></td>
			</tr>
			<tr>
				<th>탈퇴여부</th>
				<td><input type="text" name="memDelete" /></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="저장" class="btn btn-primary" />
					<input type="reset" value="취소" class="btn btn-warning"/>
				</td>
			</tr>

		</table>
	</form>
</body>
</html>