<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 8. 9.      고정현      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<security:authorize access="isAuthenticated()">
   <security:authentication property="principal.realMember" var="member" />
</security:authorize>

<style>
table{
	text-align:center;
}

.linkBtn{
	margin-bottom:20px;
}

.DoneY{
	background-color: #4f9dff;
	width: 250px;
	padding: 6px;
	border-radius: 8px; 
	color: white;
}

.DoneN{
	background-color: #eff3f7;
	width: 250px;
	padding: 6px;
	border-radius: 8px; 
	color: black;
}
</style>

<div style="margin-bottom: 30px;">
	<div id="searchCooBoard" class="d-flex justify-content-center form-inline">
		<form:select path="simpleCondition.searchType" id="searchType"
			class="mr-2">
			<form:option value="" label="전체" />
			<form:option value="title" label="제목" />
			<form:option value="writer" label="작성자" />
			<form:option value="content" label="내용" />
		</form:select>
		<form:input path="simpleCondition.searchWord" id="searchWord"
			class="mr-2" autocomplete="off"/>
		<button id="searchBtn" style="margin-left: 2px;" class="btn btn-primary waves-effect waves-light"><i class="ri-search-line"></i></button>
	</div>

</div>
<div class="col-sm-12">
	<table id="datatable" class="table table-hover mb-0" style=" color: black; text-align: center;">
		<thead class="table-light" style="color: black;">
			<tr role="row">
				<th style="width: 60px;" >NO</th>
				<th>제목</th>
				<th style="width: 80px;">모집자</th>
				<th style="width: 200px;">진행유무</th>
				<th style="width: 100px;">모집인원</th>
				<th  style="width: 100px;">조회수</th>
			</tr>
		</thead>


		<tbody>
			<c:if test="${not empty cooBoardList }">
				<c:forEach items="${cooBoardList }" var="coo">
					<tr class="odd">
						<td>${coo.rnum }</td>
						<td align="left" onclick="location.href='${cPath }/cooboard/${coo.cooNum}'">${coo.cooTitle }</td>
						<td>${coo.writerId }</td>
						<c:if test="${coo.cooDoneYn == 'N' }">						
							<td><span class="DoneY">모집중</span></td>
						</c:if>
						<c:if test="${coo.cooDoneYn == 'Y' }">						
							<td><span class="DoneN">모집완료</span></td>
						</c:if>
						<td>${coo.cooPeopleNum }명</td>
						<td>${coo.viewNum }</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
		<!-- 		2022.09.01 검색 기능 넣는중 -->
		<!-- 		2022.09.01 검색 기능 넣는중 -->
	</table>
	
	
	<div style="padding: 10px;">
		<c:if test="${member.memId != null }">
			<p align="right" >
				<input type="button" class="btn btn-primary linkBtn" value="글쓰기"  onclick="location.href='${cPath}/cooboard/form'" />
			</p>
		</c:if>
	</div>
	
	<div class="d-flex justify-content-center pagingArea">
					${pagingVO.pagingHTMLBS }
	</div>
	
<form id="searchForm" action="${cPath }/cooboard" >	
	<input type="hidden" name="searchType" />
	<input type="hidden" name="searchWord" />
	<input type="hidden" name="page" >
</form>	
</div>

<script src="${cPath }/resources/js/admin/memberList.js"></script>
<script>
//	페이징
let searchForm = $("#searchForm");
$(".pagination").on("click", "a", function(event){
		let page = $(this).data("page");
		searchForm.find("[name=page]").val(page);
		searchForm.find("[name=searchType]").val($("#searchType").val())
		searchForm.find("[name=searchWord]").val($("#searchWord").val())
		searchForm.submit();
	});
// 	검색
let searchBtn = $("#searchBtn").on("click", function(event){
	searchForm.find("[name=searchType]").val($("#searchType").val())
	searchForm.find("[name=searchWord]").val($("#searchWord").val())
	searchForm.submit();
});
</script>