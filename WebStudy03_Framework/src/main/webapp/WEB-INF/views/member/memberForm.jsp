<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.Objects"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${not empty message }">
	<script>
		alert("${message}");
	</script>
</c:if>

	<h4>회원 가입 양식</h4>

	<form method="post" enctype="application/x-www-form-urlencoded">
		<table>
			<tr>
				<th>회원아이디</th>
				<td>
					<input type"text" name="memId"  class="form-control" value='${member.memId }'/>
					<span class="error">${errors['memId'] }</span>
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
				<td><input type="text" name="memName"  value="${member.memName }" />
					<span class="error">${errors['memName'] }</span>
				</td>
			</tr>
			<tr>
				<th>주민번호</th>
				<td><input type="text" name="memRegno1"  value="${member.memRegno1 }" />
					<span class="error">${errors['memRegno1'] }</span>
				</td>
			</tr>
			<tr>
				<th>주민번호2</th>
				<td><input type="text" name="memRegno2"  value="${member.memRegno2 }" />
					<span class="error">${errors['memRegno2'] }</span>
				</td>
			</tr>
			<tr>
				<th>생일</th>
				<td><input type="date" name="memBir"  value="${member.memBir }" />
					<span class="error">${errors['memBir'] }</span>
				</td>
			</tr>
			<tr>
				<th>우편번호</th>
				<td><input type="text" name="memZip"  value="${member.memZip }"  />
					<span class="error">${errors['memZip'] }</span>
				</td>
			</tr>
			<tr>
				<th>주소1</th>
				<td><input type="text" name="memAdd1"  value="${member.memAdd1 }"   />
					<span class="error">${errors['memAdd1'] }</span>
				</td>
			</tr>
			<tr>
				<th>주소2</th>
				<td><input type="text" name="memAdd2"  value="${member.memAdd2 }"  />
					<span class="error">${errors['memAdd2'] }</span>
				</td>
			</tr>
			<tr>
				<th>집전화번호</th>
				<td><input type="text" name="memHometel" value="${member.memHometel }"  />
				</td>
			</tr>
			<tr>
				<th>회사번호</th>
				<td><input type="text" name="memComtel" value="${member.memComtel }"  />
				</td>
			</tr>
			<tr>
				<th>휴대폰</th>
				<td><input type="text" name="memHp"  value="${member.memHp }"  />
				</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" name="memMail" value="${member.memMail }"   />
				</td>
			</tr>
			<tr>
				<th>직업</th>
				<td><input type="text" name="memJob" value="${member.memJob }"  />
				</td>
			</tr>
			<tr>
				<th>취미</th>
				<td><input type="text" name="memLike" value="${member.memLike }"  />
				</td>
			</tr>
			<tr>
				<th>기념일</th>
				<td><input type="text" name="memMemorial" value="${member.memMemorial }"  />
				</td>
			</tr>
			<tr>
				<th>기념일자</th>
				<td><input type="date" name="memMemorialday" value="${member.memMemorialday }"  />
				</td>
			</tr>
			<tr>
				<th>마일리지</th>
				<td><input type="number" name="memMileage" value="${member.memMileage }"  />
				</td>
			</tr>
			<tr>
				<th>탈퇴여부</th>
				<td><input type="text" name="memDelete" value="${member.memDelete }"  />
				</td>
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