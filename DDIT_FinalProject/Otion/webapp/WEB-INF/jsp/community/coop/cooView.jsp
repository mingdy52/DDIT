<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 8. 9.      고정현      협업 신청 및 자세하게 보기
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
#content {
	margin: 20px;
	pointer-events: none;
}

#title-box {
	width: 100%;
	float: left;
	font-size: 30px;
	color: black;
	margin-left: 5px;
}

.DoneY{
	background-color: #4f9dff;
	padding: 6px;
	border-radius: 8px; 
	color: white;
}

.DoneN{
	background-color: #eff3f7;
	padding: 6px;
	border-radius: 8px; 
	color: black;
}
</style>
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal.username" var="user_id" />
</sec:authorize>

<div>
	<table class = "table mb-0" style="color: black;">
		<tr style="border-bottom: 0px solid white;">
			<c:if test="${coo.cooDoneYn == 'N' }">
				<td style="width: 100px;"><span class="DoneY">모집중</span></td>
			</c:if>
			<c:if test="${coo.cooDoneYn == 'Y' }">
				<td style="width: 100px;"><span class="DoneN">모집완료</span></td>
			</c:if>
			<td colspan="5"><h4 style="margin-left: 3%;"><c:out value="${coo.cooTitle }" /></h4></td>

			<c:if test="${coo.cooDoneYn == 'N' }">
				<c:if test="${user_id == coo.writerId }">
					<td style="float: right;"><button id="DoneNBtn" class="btn btn-link waves-effect">모집완료로 변경</button></td>
				</c:if>
			</c:if>
			<c:if test="${coo.cooDoneYn == 'Y' }">
				<c:if test="${user_id == coo.writerId }">
				<td style="float: right;"><button id="DoneYBtn" class="btn btn-link waves-effect">모집중으로 변경</button></td>
				</c:if>
			</c:if>
		</tr>
		<tr style="border-bottom: 0px solid white;">
			<td></td>
			<td colspan="3">프로젝트명 ${coo.pjName }</td>
			<td colspan="2">모집인원 ${coo.cooPeopleNum }명</td>
			<td style="width: 200px;">${coo.cooDate }</td>
		</tr>
		<tr style="text-align: center; ">
			<td>모집자</td>
			<td>${coo.writerId }</td>
			<td>시작일자</td>
			<td>${coo.cooStart}</td>
			<td>마감일자</td>
			<td>${coo.cooEnd }</td>
			<td><i class="ri-eye-fill" style="text-align: right; padding-right:5px;">${coo.viewNum }</i></td>
		</tr>
		<tr style="border-bottom: 0px solid white;">
			<td colspan="7" style="height: 300px;">${coo.cooContent }"</td>
		</tr>
		<tr>
			<c:if test="${user_id == coo.writerId or member.roleList[0].roleCode eq 'ROLE_ADMIN' }">
				<td colspan="7" style="text-align: right;"><a href="${cPath }/cooboard/${coo.cooNum }/form" class="btn btn-link waves-effect">수정</a><a href="#" onclick="f_del()" class="btn btn-link waves-effect">삭제</a></td>
			</c:if>
		</tr>
		
	</table>
<!-- 	<div class="card-body" style="border-bottom: 2px solid black"> -->
<!-- 		<div id="title-box"> -->
<!-- 			<div style="margin-left: 3%;"> -->
<%-- 				<c:out value="${coo.cooTitle }" /> --%>
<!-- 			</div> -->

<!-- 		</div> -->
<%-- 		<div style="text-align: right;">by ${coo.writerId }</div> --%>

<%-- 		<div style="text-align: right;">협업일 : ${coo.cooStart} ~ --%>
<%-- 			${coo.cooEnd }</div> --%>
<%-- 		<div style="text-align: right;">조회수 : ${coo.viewNum }</div> --%>

<!-- 		<div style="text-align: right;"> -->
<!-- 			진행여부 : -->
<%-- 			<c:if test="${coo.cooDoneYn == 'N' }"> --%>
<!-- 				<td><span class="DoneY">모집중</span></td> -->
<%-- 				<c:if test="${user_id == coo.writerId }"> --%>
<!-- 				<td><button id="DoneNBtn">모집완료로 변경</button></td> -->
<%-- 				</c:if> --%>
<%-- 			</c:if> --%>
<%-- 			<c:if test="${coo.cooDoneYn == 'Y' }"> --%>
<!-- 				<td><span class="DoneN">모집완료</span></td> -->
<%-- 				<c:if test="${user_id == coo.writerId }"> --%>
<!-- 				<td><button id="DoneYBtn">모집중으로 변경</button></td> -->
<%-- 				</c:if> --%>
<%-- 			</c:if> --%>
<!-- 		</div> -->

<%-- 		<div style="text-align: right;">인원수 : ${coo.cooPeopleNum }</div> --%>
<%-- 		<div style="text-align: right;">${coo.cooDate }</div> --%>
		
<%-- 		<div style="text-align: right;">프로젝트명 : ${coo.pjName }</div> --%>
<!-- 	</div> -->
<!-- 	<div id="content"> -->
<!-- 		<p class="card-title-desc" id="postContent"> -->
<%-- 			<c:out value="${coo.cooContent }" /> --%>
<!-- 		</p> -->
<!-- 	</div> -->
</div>


<!-- 협업 신청 버튼은 로그인 안한 사람과 해당 협업글 주인은 안보이게 함. -->
<c:if test="${member.memId != null and member.memId != coo.writerId}">
	<input style="float: right; margin-right: 10px;" type="button" value="신청하기" class="btn btn-outline-primary waves-effect waves-light"
		data-bs-toggle="modal" data-bs-target="#modal" />
</c:if>

<!-- modal -->
<div class="modal fade bs-example-modal-xl show" tabindex="-1"
	id="modal" style="display: none;" aria-modal="true" role="dialog">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">협업신청</h5>
			</div>
			<form method="post" class="form-inline"
				action="${cPath }/cooboard/form/check" enctype="multipart/form-data">
				<sec:csrfInput />
				<input id="cooNum" name="cooNum" type="hidden"
					value="${coo.cooNum }" />

				<div class="modal-body">
					<label class="form-label" for="introduce">간단한 자기소개를 해 주세요.</label>
					<input type="text" class="form-control" autocomplete="off" id="introduce" name="cooFormContent"
					 placeholder="간단한 자기소개를 해 주세요." required="required" oninvalid="intro(this)"> 
					 
					<label class="form-label" for="cooFiles">이력서를 제출해주세요</label> 
					<input type="file" class="form-control" id="cooFile" name="originFile"
						placeholder="파일을 첨부하세요" required="required" oninvalid="myfiles(this)">
				</div>

				<div class="modal-footer">
					<button type="submit" class="btn btn-primary" style="float: right;">신청하기</button>
					<button type="button" class="btn btn-light waves-effect close"
						data-bs-dismiss="modal">닫기</button>
				</div>
			</form>

		</div>
	</div>
</div>
<!-- modal -->

<form method="post" id="updateStateForm">
	<sec:csrfInput/>
	<input type="hidden" name="cooNum" value="${coo.cooNum }">
	<input type="hidden" name="cooDoneYn" value="${coo.cooDoneYn }">
</form>
<!-- 댓글  -->
<jsp:include page="/WEB-INF/jsp/notice/boardReply.jsp" />

<form action="${cPath }/download" id="downForm">
	<input type="hidden" name="attatchNum" id="attatchNum"> <input
		type="hidden" name="attatchOrder" id="attatchOrder">
</form>

<script>
// 
function intro(p_this){
	p_this.setCustomValidity('')
	if(p_this.value.trim()=="")
	p_this.setCustomValidity('자기소개를 작성해 주세요.')
}
//
function myfiles(p_file){
	p_file.setCustomValidity('')
	if(p_file.value.trim()==""){
		p_file.setCustomValidity('파일을 선택하세요.')
	}
}

$("[name=cooFormView]").on("click", function() {
	$("[name=cooFormContent]").val("");
})

function f_addCooForm() {
	$("#insertCooForm").submit();
}

function f_del() {
	swal({
		title : "삭제하시겠습니까?",
		icon : "warning",
		buttons : true,
		dangerMode : true,
	}).then(function(willDelete) {
		if (willDelete) {
			location.href = "${cPath }/cooboard/${coo.cooNum }/del";

		} else {
			return;
		}
	});
}


function attaDelBtn(el){
	el.css("display", "none");
	el.prev().css("display", "none");
	let attatchNum = el.data("attnum");
	let attatchOrder = el.data("attorder");
	
	let delNum = $("<input>").attr("name", "delAttatchNum").attr("type", "hidden").val(attatchNum);
	let delOrder = $("<input>").attr("name", "delAttatchOrder").attr("type", "hidden").val(attatchOrder);
	
	$("#freeBoardForm").append(delNum, delOrder);
}

let  updateStateForm = $("#updateStateForm");
$("#DoneNBtn").on("click", function(){
	$("input[name='cooDoneYn']").attr("value", "Y");
	updateStateForm.submit();
});

$("#DoneYBtn").on("click", function(){
	$("input[name='cooDoneYn']").attr("value", "N");
	updateStateForm.submit();
});
</script>
<script src="${cPath }/resources/js/fileDownload.js"></script>