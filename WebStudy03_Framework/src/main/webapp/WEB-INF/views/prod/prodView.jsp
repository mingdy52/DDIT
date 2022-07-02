<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

상품 정보 내에 구매자 정보(아이디, 이름, 연락처, 이메일)를 함께 랜더링함.
해당 구매자의 이름을 클릭하면, 그 사람에 대한 상세페이지로 이동.

상품 거래처 정보 (거래처 아이디, 거래처명, 담당자 이름, 거래처 소재지)
<c:set value="${prod.memberSet }" var="memberSet"></c:set>
<c:if test="${not empty memberSet }">
	<table class="table table-bordered">
				<tr>
					<td>구매자 목록</td>
				</tr>
				<c:forEach items="${memberSet }" var="user">
					<c:url value="/member/memberView.do" var="memberViewUrl">
						<c:param name="who" value="${user.memId}" />
						<c:param name="layout" value="grid" />
					</c:url>
					<tr>
						<td><a href="${memberViewUrl }" >${user.memName}</a></td>
					</tr>
				</c:forEach>
			</table>
</c:if>
<c:if test="${empty memberSet }">
	구매자 없음.
</c:if>

<button type="button" class="btn btn-primary listBtn">목록으로</button>

<c:if test=""><tr><td>구매자 없음.</td></tr></c:if>
	<table class="table table-bordered">
		<tr><td>${prod.prodId}</td></tr>
		<tr><td>${prod.prodName}</td></tr>
		<tr><td>${prod.prodLgu}</td></tr>
		<tr><td>${prod.prodBuyer}</td></tr>
		<tr><td>${prod.prodCost}</td></tr>
		<tr><td>${prod.prodPrice}</td></tr>
		<tr><td>${prod.prodSale}</td></tr>
		<tr><td>${prod.prodOutline}</td></tr>
		<tr><td>${prod.prodDetail}</td></tr>
		<tr><td>${prod.prodImg}</td></tr>
		<tr><td>${prod.prodTotalstock}</td></tr>
		<tr><td>${prod.prodInsdate}</td></tr>
		<tr><td>${prod.prodProperstock}</td></tr>
		<tr><td>${prod.prodSize}</td></tr>
		<tr><td>${prod.prodColor}</td></tr>
		<tr><td>${prod.prodDelivery}</td></tr>
		<tr><td>${prod.prodUnit}</td></tr>
		<tr><td>${prod.prodQtyin}</td></tr>
		<tr><td>${prod.prodQtysale}</td></tr>
		<tr><td>${prod.prodMileage}</td></tr>
		<tr><td>${prod.lprodNm}</td></tr>
	</table>
	
	<table class="table table-bordered">
		상품 거래처 정보 (거래처 아이디, 거래처명, 담당자 이름, 거래처 소재지)
		<tr><td>${prod.buyer.buyerId}</td></tr>
		<tr><td>${prod.buyer.buyerName}</td></tr>
		<tr><td>${prod.buyer.buyerCharger}</td></tr>
		<tr><td>${prod.buyer.buyerAdd1}</td></tr>
		
	</table>

<script>
	$('.listBtn').on('click', function(event){
		location.href='${cPath}/prod/prodList.do';
	});
</script>
