<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 8. 16.      서효림      최초작성
* 2022. 8. 18       서효림		1차 수정
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<sec:authorize access="isAuthenticated()">
   <sec:authentication property="principal.realMember" var="member" />
</sec:authorize>

<style>

#content{
   margin:20px;
   pointer-events : none;
}

#title-box{
   width: 100%;
   float: left;
   font-size: 30px;
   color: black;
   margin-left: 5px;
}

</style>

<%-- ${freeBoard} --%>
<%-- <c:if test="${not empty LOGIN_USER }"> --%>
<!-- <button type="button" class="btn btn-outline-primary waves-effect waves-light" -->
<!-- data-bs-toggle="modal" data-bs-target="#insertColleague" name = "freeBoardFormView">자유게시판</button> -->
<%-- </c:if> --%>
<sec:authorize access="isAuthenticated()">
                    <sec:authentication property="principal.username" var="user_id" />
<%--                     <div id="user_id">안녕하세요. ${user_id }</div> --%>
</sec:authorize>
<table class = "table mb-0" style="color: black;">
	<tr style="border-bottom: 1px solid white;">
		<td style="width: 500px;"><h4 style="margin-left: 3%;"><c:out value="${freeBoard.freeTitle }"/></h4></td>
		<td style="float: right;">${freeBoard.freeDate }</td>
	</tr>
	<tr>
		<td></td>
		<td style="text-align: right;">작성자 ${freeBoard.writerId } &nbsp;&nbsp;&nbsp; <i class="ri-eye-fill" style="text-align: right; padding-right:5px;"> ${freeBoard.viewNum }</i></td>
	</tr>
	<tr>
		<td colspan="2" style="border-bottom: 0px solid white; height: 300px;">${freeBoard.freeContent }</td>
	</tr>
	<tr>
		<c:if test="${user_id == freeBoard.writerId or member.roleList[0].roleCode eq 'ROLE_ADMIN' }">
 			<td style="text-align: right;" colspan="3">
				<a href="${cPath }/freeBoard/${freeBoard.freeNum }/form" class="btn btn-link waves-effect">수정</a>
				<a href="#" onclick="f_del()" class="btn btn-link waves-effect">삭제</a>
			</td>
		</c:if>
	</tr>
</table>
<!-- <div> -->
<!-- 	<div class="card-body" style="border-bottom: 2px solid black"> -->
<!-- 		<div id="title-box"> -->
<%-- 			<div style="margin-left: 3%;"><c:out value="${freeBoard.freeTitle }"/></div> --%>
			
<!-- 		</div> -->
<!-- 		<div style="text-align: right;"> -->
<%-- 				by ${freeBoard.writerId } --%>
<!-- 		</div> -->
<%-- <%-- 		<c:if test="${member.roleList[0].roleCode eq 'ROLE_ADMIN' }"> --%>
<%--  		<c:if test="${user_id == freeBoard.writerId or member.roleList[0].roleCode eq 'ROLE_ADMIN' }"> --%>
<!--  			<div style="text-align: right;"> -->
<%-- 				<a href="${cPath }/freeBoard/${freeBoard.freeNum }/form">수정</a> --%>
<!-- 				<a href="#" onclick="f_del()">삭제</a> -->
<!-- 			</div> -->
<%-- 		</c:if> --%>
<!-- 		<div style="text-align: right;"> -->
<%-- 			조회수 : ${freeBoard.viewNum } --%>
<!-- 		</div> -->
<!-- 		<div style="text-align: right;"> -->
<%-- 				${freeBoard.freeDate } --%>
<!-- 		</div> -->
<!-- 	</div> -->
<!-- 	<div id="content"> -->
<%-- 		<p class="card-title-desc" id="postContent"><c:out value="${freeBoard.freeContent }"/></p> --%>
<!-- 	</div> -->
<!-- </div> -->

<div>
	<c:if test="${not empty freeBoard.attatchList }">
		<div>
			<c:forEach items="${freeBoard.attatchList }" var="attatches">
				<a href="#" class="download" data-attnum="${attatches.attatchNum }" data-attorder="${attatches.attatchOrder }">${attatches.originNm }</a> <br>
			</c:forEach>
		</div>
	</c:if>
</div>
<!-- 댓글  -->
<jsp:include page="/WEB-INF/jsp/notice/boardReply.jsp"/>

<form action="${cPath }/download" id="downForm">
   <input type="hidden" name="attatchNum" id="attatchNum">
   <input type="hidden" name="attatchOrder" id="attatchOrder">
</form>

<script>
function f_del(){
	
	swal({
		  title: "삭제하시겠습니까?",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
		})
		.then(function (willDelete){
		  if (willDelete) {
			location.href="${cPath }/freeBoard/${freeBoard.freeNum }/del";
			
		  } else {
			  return;
		  }
	});
	
}

</script>
<script src="${cPath }/resources/js/fileDownload.js"></script>
