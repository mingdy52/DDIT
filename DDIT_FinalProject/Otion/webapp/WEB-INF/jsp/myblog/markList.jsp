<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 8. 24.      박채록      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div id="mark-title" style="padding-top:30px;"><h3>나의 북마크(${bookSize})</h3></div>
<c:set value="${pagingVO.dataList }" var="mark"/>
<table class="table table-hover mb-0">
	<thead>
		<tr style="text-align: center;">
			<th>NO</th>
			<th>글제목</th>
			<th>작성자</th>
			<th>하트수</th>
			<th>조회수</th>
		</tr>
	</thead>
	<tbody>
	<c:if test="${not empty mark }">
		<c:forEach items="${mark }" var="m">
		<tr id="markList" style="text-align: center;">
			<td>${m.rnum}</td>
			<td><a href="${cPath}/blog/${m.blogpost.blogerId}/post/${m.postNum}">${m.blogpost.postTitle }</a></td>
			<td>${m.blogpost.blogerId}</td>	
			<td>${m.blogpost.postHeart }</td>
			<td>${m.blogpost.postView }</td>
		</tr>
		</c:forEach>
	</c:if>
	<c:if test="${empty mark }">
		<tr class="markList">
			<td>북마크가 존재하지 않습니다.</td>
		</tr>
	</c:if>
	</tbody>
</table>
<div class="d-flex justify-content-center pagingArea" style="margin-top: 10px;">
	${pagingVO.pagingHTMLBS }
</div>

<form:form id="searchForm" modelAttribute="simpleCondition" action="${cPath }/blog/${memId }/bookmark" method="get">
	<input type="hidden" name="page" />
	<form:input type="hidden" path="searchType" />
	<form:input type="hidden" path="searchWord" />
</form:form> 

<script>
	let searchForm = $("#searchForm");
	let pagingArea = $(".pagingArea").on("click", "a", function(event){
		let page = $(this).data("page");
		searchForm.find("[name=page]").val(page);
		searchForm.submit();
	});




</script>