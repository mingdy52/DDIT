<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>회원아이디</th>
				<th>회원명</th>
				<th>휴대폰</th>
				<th>거주지역</th>
				<th>이메일</th>
				<th>마일리지</th>
			</tr>
		</thead>
		<tbody>
<%
	List<MemberVO> memberList = (List) request.getAttribute("memberList");
	
	if(memberList.size() > 0) {
		for(MemberVO member : memberList){
			%>
			<tr>
				<td><%=member.getMemId() %></td>
				<td><%=member.getMemName() %></td>
				<td><%=member.getMemHp() %></td>
				<td><%=member.getMemAdd1() %></td>
				<td><%=member.getMemMail() %></td>
				<td><%=member.getMemMileage() %></td>
			</tr>
			<%
		}
	} else {
		%>
		<tr>
			<td colspan="6">회원이 아직 없음.</td>
		</tr>
		<%
	}
%>
		</tbody>
	</table>
