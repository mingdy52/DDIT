<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 8. 5.      박채록      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <style>
 	.pagingArea{
 		margin-left: 45%;	
 		margin-top: 5%;
 	}
 </style>
<div id="wish-title"></div>
<c:set value="${pagingVO.dataList }" var="myWish"/>
<table class="table table-hover mb-0" style="text-align: center; color: black;">
<thead>
	<tr>
		<th>NO</th>
		<th>상품번호</th>
		<th>상품명</th>
		<th>가격</th>
	</tr>
</thead>
<c:if test="${not empty myWish}">
	<c:forEach items="${myWish }" var="wish" varStatus="status">
		<tr id="wishList">
			<td>${status.count }</td>
			<td><a href="${cPath}/expert/prod/${wish.eprodNum}">${wish.eprodNum }</td>
			<td>${wish.eprod.eprodName }</td>
			<td>${wish.eprod.eprodPrice }</td>
		</tr>
	</c:forEach>
</c:if>
<c:if test="${empty myWish }">
		<tr>
			<td  colspan="4" style="text-align: center;">wish가 존재하지 않습니다.</td>
		</tr>
</c:if>
</table>


<div class="pagingArea">
	${pagingVO.pagingHTMLBS }
</div>

<form:form id="searchForm" modelAttribute="simpleCondition" action="${cPath }/myexpert/wish" method="get">
	<input type="hidden" name="page" />
	<form:input type="hidden" path="searchType" />
	<form:input type="hidden" path="searchWord" />
</form:form> 

<script>
let wishList = $("#wishList").length;
console.log(wishList);

$(document).ready(function() {
    $('#wish-title').append('<h3>my wish♥('+wishList+')</h3>').css("border-bottom", "1px solid black");
});

let searchForm = $("#searchForm");
let pagingArea = $(".pagingArea").on("click", "a", function(event){
	let page = $(this).data("page");
	searchForm.find("[name=page]").val(page);
	searchForm.submit();
});

</script>