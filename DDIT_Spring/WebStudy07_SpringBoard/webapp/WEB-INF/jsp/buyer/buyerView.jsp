<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<h4>거래처 상세</h4>

<table class="table table-bordered">
	<tr>
		<th>거래처코드</th>
		<td>${buyerVO.buyerId}</td>
	</tr>
	<tr>
		<th>거래처명</th>
		<td>${buyerVO.buyerName}</td>
	</tr>
	<tr>
		<th>분류코드</th>
		<td>${buyerVO.buyerLgu}</td>
	</tr>
	<tr>
		<th>거래처 은행</th>
		<td>${buyerVO.buyerBank}</td>
	</tr>
	<tr>
		<th>계좌번호</th>
		<td>${buyerVO.buyerBankno}</td>
	</tr>
	<tr>
		<th>계좌주</th>
		<td>${buyerVO.buyerBankname}</td>
	</tr>
	<tr>
		<th>우편번호</th>
		<td>${buyerVO.buyerZip}</td>
	</tr>
	<tr>
		<th>주소1</th>
		<td>${buyerVO.buyerAdd1}</td>
	</tr>
	<tr>
		<th>주소2</th>
		<td>${buyerVO.buyerAdd2}</td>
	</tr>
	<tr>
		<th>대표번호</th>
		<td>${buyerVO.buyerComtel}</td>
	</tr>
	<tr>
		<th>팩스번호</th>
		<td>${buyerVO.buyerFax}</td>
	</tr>
	<tr>
		<th>거래처 메일</th>
		<td>${buyerVO.buyerMail}</td>
	</tr>
	<tr>
		<th>담당자</th>
		<td>${buyerVO.buyerCharger}</td>
	</tr>
	<tr>
		<th>이게뭐지</th>
		<td>${buyerVO.buyerTelext}</td>
	</tr>
</table>