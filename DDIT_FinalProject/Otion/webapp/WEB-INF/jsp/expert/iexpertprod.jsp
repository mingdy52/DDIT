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
	
	margin-top: 10px;
}

.Btn{
	background-color: black;
	border: black;
}
</style>
 <div style="margin-bottom: 30px;">
 	<span style="font-size: 30px; color: black;"> EXPERT등록상품 </span>
	<button style="float: right;" class="btn btn-primary waves-effect waves-light Btn" onclick="location.href='${cPath}/iexpert/eprodForm'" >상품등록</button>
</div>
 <c:set value="${pagingVO.dataList}" var="eprod"/>
 <div class="table-responsive">
<table class="table table-hover mb-0" style="text-align: center; color: black;">
 	<thead class="table table-hover mb-0">
 		<tr>
 			<th>NO</th>
 			<th>상품번호</th>
 			<th style="width: 320px;">상품명</th>
 			<th>가격</th>
 			<th>등록일자</th>
 			<th>Up</th>
 			<th>Del</th>
 		</tr>
 	</thead>
 	<tbody>
 	<c:if test="${not empty  eprod}">
 	<c:forEach var="e" items="${eprod }">
 	<tr>
 		<td>${e.rnum}</td>
 		<td class="eNum">${e.eprodNum }</td>
 		<td><a href="${cPath}/expert/prod/${e.eprodNum }">${e.eprodName}</a></td>
 		<td>${e.eprodPrice }원</td>
 		<td>${e.eprodDate }</td>
 		<td><a href="${cPath}/iexpert/prod/${e.eprodNum }">수정</a></td>
 		<td><a class="prodDel">삭제</a></td>
 	</tr>
 	</c:forEach>
 </c:if>
  <c:if test="${ empty  eprod}">
  	<tr>
  	 <td colspan="7">등록된 상품 존재하지 않습니다.</td>
  	</tr>
  </c:if>
 </tbody>
 </table>
 </div>
 
<div class="d-flex flex-wrap gap-2 justify-content-center pagingArea">
${pagingVO.pagingHTMLBS }
</div>

<!-- paging처리나중에 왜냐면 id..로그인.. -->


<form:form id="searchForm" modelAttribute="simpleCondition" action="${cPath }/iexpert/prod" method="get">
	<input type="hidden" name="page" />
	<form:input type="hidden" path="searchType" />
	<form:input type="hidden" path="searchWord" />
</form:form> 

<form:form id="delForm" method="post" modelAttribute="eprod">
	<input type="hidden" name="_method" value="delete">
	<input type="hidden" name="eprodNum"/>
</form:form>


<script>
let searchForm = $("#searchForm");
let pagingArea = $(".pagingArea").on("click", "a", function(event){
	let page = $(this).data("page");
	searchForm.find("[name=page]").val(page);
	searchForm.submit();
});


$(".prodDel").on("click", function(){
	
	let td = $(this).parent().parent().children();
	let eNum = td.eq(1).text();
	console.log(eNum);
	let delForm =$("#delForm");
	let prodDel = confirm("해당 게시글을 삭제하시겠습니까?");
	if(prodDel){
		alert("해당 게시글이 삭제되었습니다.");
		delForm.find("[name='eprodNum']").attr("value", eNum);
		delForm.attr("action", "${cPath}/iexpert/prod/"+eNum);
		delForm.submit();
	}else{
		alert("취소");
	}
});

</script>