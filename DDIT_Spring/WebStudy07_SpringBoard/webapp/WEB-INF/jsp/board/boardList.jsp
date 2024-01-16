<%@page import="kr.or.ddit.board.vo.BoardVO"%>
<%@page import="kr.or.ddit.common.vo.PagingVO"%>
<%@page import="org.springframework.ui.Model"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<spring:message code="board.boTitle" var="titleMsg"/>
<spring:message code="board.boWriter" var="writerMsg"/>
<spring:message code="board.boContent" var="contentMsg"/>
<table class="table table-bordered">
	<thead class="thead-dark">
		<tr>
			<th><spring:message code="rnum"/></th>
			<th>${titleMsg }</th>
			<th>${writerMsg }</th>
			<th><spring:message code="board.boDate"/></th>
			<th><spring:message code="board.boHit"/></th>
		</tr>
	</thead>
	<tbody id="listBody">
		
	</tbody>
	<tfoot>
		<tr>
			<td colspan="5">
				<div class="d-flex justify-content-center pagingArea">
				</div>
				<div id="searchUI" class="d-flex justify-content-center form-inline">
					<form:select path="simpleCondition.searchType" class="mr-2">
						<spring:message code='searchAll' var="searchAll"/>
						<form:option value="" label="${searchAll }" />
						<form:option value="title" label="${titleMsg }" />
						<form:option value="writer" label="${writerMsg }" />
						<form:option value="content" label="${contentMsg }" />
					</form:select>
					<form:input path="simpleCondition.searchWord" class="mr-2"/>
					<input type="button" id="searchBtn" class="btn btn-primary" value="<spring:message code='search'/>"/>
					<input type="button" value="새글쓰기" class="btn btn-secondary linkBtn" data-href="${cPath}/board/form"/>
<%-- 					<a class="btn btn-secondary" href="${cPath}/board/form">새글쓰기</a> --%>
<!-- 					<input type="button" class="btn btn-secondary" value="새글쓰기"  -->
<%-- 						onclick="location.href='${cPath}/board/form';" --%>
<!-- 					/> -->
				</div>
			</td>
		</tr>
	</tfoot>
</table>
<div>
<h4>Hidden Form</h4>
<form id="searchForm">
	<input type="text" name="searchType" />
	<input type="text" name="searchWord" />
	<input type="text" name="page" />
</form>
</div>


<script src="${cPath }/resources/js/jquery.form.min.js"></script>
<script src="${cPath }/resources/board/boardList.js"></script>
