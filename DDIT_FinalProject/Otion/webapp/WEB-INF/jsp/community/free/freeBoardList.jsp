<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<security:authorize access="isAuthenticated()">
   <security:authentication property="principal.realMember" var="member" />
</security:authorize>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 8. 16.      서효림      최초작성
* 2022. 8. 31. 		서효림	1차수정, 페이징
* 2022. 9. 01.		서효림	2차수정, 검색기능
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<style>
table{
	text-align:center;
}

.linkBtn{
	margin-bottom:20px;
}
</style>

<div style="margin-bottom: 30px;">
	<div id="searchFreeBoard" class="d-flex justify-content-center form-inline">
		<form:select path="simpleCondition.searchType" id="searchType"
			class="mr-2">
			<form:option value="" label="전체" />
			<form:option value="title" label="제목" />
			<form:option value="writer" label="작성자" />
			<form:option value="content" label="내용" />
		</form:select>
		<form:input path="simpleCondition.searchWord" id="searchWord"
			class="mr-2" autocomplete="off" />
		<button id="searchBtn" style="margin-left: 2px;" class="btn btn-primary waves-effect waves-light"><i class="ri-search-line"></i></button>
	</div>

</div>

<div class="col-sm-12">
	<table id="datatable" class="table table-hover mb-0" style=" color: black; text-align: center;">
			<thead class="table-light" style="color: black;">
				<tr role="row">
					<th style="width: 60px;" >NO</th>
					<th>제목</th>
					<th style="width: 80px;">작성자</th>
					<th style="width: 200px;">날짜</th>
					<th  style="width: 100px;">조회수</th>
				</tr>
			</thead>

		<tbody>
			<c:if test="${not empty pagingVO.dataList }">
				<c:forEach items="${pagingVO.dataList}" var="freeBoard" >
                <tr>
					<td>${freeBoard.rnum }</td>
					<td align="left"><a href="${cPath}/freeBoard/${freeBoard.freeNum }">${freeBoard.freeTitle }</a></td>
					<td>${freeBoard.writerId }</td>
					<td>${freeBoard.freeDate }</td>
					<td>${freeBoard.viewNum }</td>
				</tr>
				</c:forEach>
			</c:if>
		</tbody>
		<!-- 		2022.09.01 -->
	
	</table>
	<div style="padding: 10px;">
		<c:if test="${member.memId != null }">
			<p align="right" >
				<input type="button" class="btn btn-primary linkBtn" value="글쓰기" onclick="location.href='${cPath}/freeBoard/form'"/> 
			</p>
		</c:if>
	</div>
	
<c:if test="${not empty pagingVO.dataList}">
	<div class="d-flex justify-content-center pagingArea">
					${pagingVO.pagingHTMLBS }
	</div>
</c:if>
<form id="searchForm" action="${cPath }/freeBoard" >
	<input type="hidden" name="searchType" />
	<input type="hidden" name="searchWord" />
	<input type="hidden" name="page" >
</form>

</div>

<script>
let searchForm = $("#searchForm");
$(".pagination").on("click", "a", function(event){
		let page = $(this).data("page");
		searchForm.find("[name=page]").val(page);
		searchForm.find("[name=searchType]").val($("#searchType").val())
		searchForm.find("[name=searchWord]").val($("#searchWord").val())
		searchForm.submit();
	});
	
let searchBtn = $("#searchBtn").on("click", function(event){
	searchForm.find("[name=searchType]").val($("#searchType").val())
	searchForm.find("[name=searchWord]").val($("#searchWord").val())
	searchForm.submit();
});


</script>

<!-- 철희쌤 이벤트 -->
<!-- <script> -->
<!--  	//TR에 마우스 올렸을 때 -->
<!--  	function f_mover(p_this){ -->
<!--  		//console.log(p_this); -->
<!--  		p_this.style.backgroundColor = "black"; -->
<!--  		p_this.style.color = "pink"; -->
<!--  	} -->

<!--  	//TR에서 마우스가 벗어났을 때 -->
<!--  	function f_mout(p_this){ -->
<!--  		//console.log(p_this); -->
<!--  		p_this.style.backgroundColor = "white"; -->
<!--  		p_this.style.color = "black"; -->
<!--  	} -->
<!-- </script> -->