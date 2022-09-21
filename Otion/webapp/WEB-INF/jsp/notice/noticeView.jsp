<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 8. 29.      서효림      최초작성
* 2022. 9. 01.      심민경      수정
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

<div>
	<div class="card-body" style="border-bottom: 2px solid black">
		<div id="title-box">
			<div style="margin-left: 3%;"><c:out value="${notice.notiTitle }"/></div>
			
		</div>
		<div style="text-align: right;">
				by 관리자
		</div>
		<c:if test="${member.roleList[0].roleCode eq 'ROLE_ADMIN' }">
			<div style="text-align: right;">
				<a href="${cPath }/notice/${notice.notiNum }/form">수정</a>
				| <a href="#" onclick="f_del()">삭제</a>
			</div>
		</c:if>
		<div style="text-align: right;">
			조회수 : ${notice.viewNum }
		</div>
		<div style="text-align: right;">
				${notice.notiDate }
		</div>
	</div>
	<div id="content">
		<p class="card-title-desc" id="postContent">${notice.notiContent }</p>
	</div>
</div>

<div>
	<c:if test="${not empty notice.attatchList }">
		<div>
			<c:forEach items="${notice.attatchList }" var="attatches">
				<a href="#" class="download" data-attnum="${attatches.attatchNum }" data-attorder="${attatches.attatchOrder }">${attatches.originNm }</a> <br>
			</c:forEach>
		</div>
	</c:if>
</div>
<hr>
<!-- 댓글  -->
<jsp:include page="boardReply.jsp"/>
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
			location.href="${cPath }/notice/${notice.notiNum }/del";
			
		  } else {
			  return;
		  }
	});
	
}

</script>
<script src="${cPath }/resources/js/fileDownload.js"></script>

