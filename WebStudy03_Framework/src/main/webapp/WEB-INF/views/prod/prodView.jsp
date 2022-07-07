<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!-- 상품 정보 내에 구매자 정보(아이디, 이름, 연락처, 이메일)를 함께 랜더링함. -->
<!-- 해당 구매자의 이름을 클릭하면, 그 사람에 대한 상세 페이지로 이동. -->
<!-- 해당 상품의 거래처 정보는 (거래처 아이디, 거래처명, 담당자 이름, 거래처 소재지)를 함께 출력함. -->
<table class="table table-bordered">
	<tr>
		<th>상품코드</th>
		<td>${prod.prodId }</td>
	</tr>
	<tr>
		<th>상품명</th>
		<td>${prod.prodName }</td>
	</tr>
	<tr>
		<th>분류코드</th>
		<td>${prod.prodLgu }</td>
	</tr>
	<tr>
		<th>거래처코드</th>
		<td>${prod.prodBuyer }</td>
	</tr>
	<tr>
		<th>구매가</th>
		<td>${prod.prodCost }</td>
	</tr>
	<tr>
		<th>판매가</th>
		<td>${prod.prodPrice }</td>
	</tr>
	<tr>
		<th>할인가</th>
		<td>${prod.prodSale }</td>
	</tr>
	<tr>
		<th>상품정보요약</th>
		<td>${prod.prodOutline }</td>
	</tr>
	<tr>
		<th>상세정보</th>
		<td>${prod.prodDetail }</td>
	</tr>
	<tr>
		<th>이미지</th>
		<td><img src="${cPath }/resources/prodImages/${prod.prodImg }" /></td>
	</tr>
	<tr>
		<th>총재고</th>
		<td>${prod.prodTotalstock }</td>
	</tr>
	<tr>
		<th>입고일</th>
		<td>${prod.prodInsdate }</td>
	</tr>
	<tr>
		<th>적정재고</th>
		<td>${prod.prodProperstock }</td>
	</tr>
	<tr>
		<th>크기</th>
		<td>${prod.prodSize }</td>
	</tr>
	<tr>
		<th>색상</th>
		<td>${prod.prodColor }</td>
	</tr>
	<tr>
		<th>배송방법</th>
		<td>${prod.prodDelivery }</td>
	</tr>
	<tr>
		<th>단위</th>
		<td>${prod.prodUnit }</td>
	</tr>
	<tr>
		<th>입고량</th>
		<td>${prod.prodQtyin }</td>
	</tr>
	<tr>
		<th>판매량</th>
		<td>${prod.prodQtysale }</td>
	</tr>
	<tr>
		<th>마일리지</th>
		<td>${prod.prodMileage }</td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="button" class="btn btn-primary" value="상품목록" />
		</td>
	</tr>
	<tr>
		<th>구매자목록</th>
		<td>
			<table class="table table-borderd">
				<thead>
					<tr>
						<th>구매자이름</th>
						<th>연락처</th>
						<th>메일계정</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="memberSet" value="${prod.memberSet }"/>
					<c:if test="${not empty memberSet }">
						<c:forEach items="${memberSet }" var="user">
							<c:url value="/member/memberView.do" var="memberViewURL">
								<c:param name="who" value="${user.memId }" />
								<c:param name="layout" value="grid" />
							</c:url>
							<tr>
								<td>
									<a href="${memberViewURL }">${user.memName }</a>
								</td>
								<td>${user.memHp }</td>
								<td>${user.memMail }</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty memberSet }">
						<tr>
							<td colspan="3">구매자 없음.</td>
						</tr>
					</c:if>
				</tbody>
			</table>
		</td>
	</tr>
</table>

