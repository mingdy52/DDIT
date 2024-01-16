<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 8. 26.      서효림      최초작성
* 2022. 8. 27.		서효림	1차수정
* 2022. 9. 02.      심민경      수정
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
.content-box {
	background-color: #ebebff;
	padding: 40px;
	border-radius: 20px; 
}

.content {
	background-color: #E6E6E6;
	
}

.writer-cir {
	font-weight: bold;
	color: black;
	margin-right: 20px;
}

.expDate{
	text-align:right;
}

.expDate, .answer{
	margin-bottom:20px;
}
#subBtn{
	float:right;
}
#title{
	margin-bottom:10px;
}
</style>

	<a href="#" data-bs-toggle="modal" data-bs-target="#modifyModal">수정</a>
    | <a href="#" data-bs-toggle="modal" data-bs-target="#delDodal">삭제</a>
    
<div class="writer-cir" ><c:out value="${qna.writerId}"/>님의 문의</div>
<div class="content-box content">
	<h4><c:out value="${qna.qnaTitle }"/></h4>
	${qna.qnaContent}
</div>
<div class="expDate">${qna.qnaDate }</div>

<c:if test="${not empty qna.answerId }">
	<div class="writer-cir" >관리자 답변입니다.</div>
	<div class="content-box">
		<h4><c:out value="${qna.answerTitle}"/></h4>
		${qna.answerContent}
	</div>
	<div class="expDate">${qna.answerDate }</div>
</c:if>

<c:if test="${empty qna.answerId && member.roleList[0].roleCode eq 'ROLE_ADMIN'}">
	<div>
		<h2>Answer</h2>
		
		<form:form action="${cPath }/qna/${qna.qnaNum}" method="post" id="answerForm" modelAttribute="qna">
			<sec:csrfInput/>
			<div>
				<form:input path="qnaNum" type="hidden"/>
				<form:input path="writerId" type="hidden"/>
				<form:input path="qnaTitle" type="hidden"/>
				<form:input path="qnaContent" type="hidden"/>
				<form:input class="form-control" id="title" path="answerTitle" placeholder="제목을 입력하세요." autocomplete="off"/>
			</div>
			<div>
				<form:textarea class="form-control answer" id="content" style="min-height: 180px;" path="answerContent" placeholder="내용을 입력하세요." autocomplete="off"/>
			</div>
		</form:form>
		<button type="button" onclick="f_submit()" class="btn btn-primary waves-effect waves-light" id="subBtn">저장</button>
	</div>
</c:if>

<!-- 문의 수정 모달 -->
<div class="modal fade show" id="modifyModal" tabindex="-1"
	style="display: none;" aria-modal="true" role="dialog">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">수정</h5>
			</div>
			<form:form id="modifyForm" method="post" class="form-inline" action="${cPath }/qna/${qna.qnaNum}/form" modelAttribute="qna">
				<sec:csrfInput/>
				<form:input path="qnaNum" type="hidden"/>
				<div class="modal-body">
					<form:input class="form-control" id="modipass" type="password" maxlength="20" path="qnaPass" placeholder="비밀번호를 입력하세요."/>
				</div>
				<div class="modal-footer">
					<button type="button" onclick="modifySubmit()" class="btn btn-primary waves-effect waves-light">수정</button>
					<button type="button" class="btn btn-light waves-effect close" data-bs-dismiss="modal">닫기</button>
				</div>
	  	    </form:form>	
		</div>
	</div>
</div>

<!-- 문의 삭제 모달 -->
<div class="modal fade show" id="delDodal" tabindex="-1"
	style="display: none;" aria-modal="true" role="dialog">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">삭제</h5>
			</div>
			<form:form id="delForm" method="post" class="form-inline" action="${cPath }/qna/${qna.qnaNum}/del" modelAttribute="qna">
				<sec:csrfInput/>
				<form:input path="qnaNum" type="hidden"/>
				<div class="modal-body">
					<form:input class="form-control" id="delpass" type="password" maxlength="20" path="qnaPass" placeholder="비밀번호를 입력하세요."/>
				</div>
				<div class="modal-footer">
					<button type="button" onclick="delSubmit()" class="btn btn-primary waves-effect waves-light">삭제</button>
					<button type="button" class="btn btn-light waves-effect close" data-bs-dismiss="modal">닫기</button>
				</div>
	  	    </form:form>	
		</div>
	</div>
</div>

<script>
function f_submit(){
	if(title.value < 1 || !title.value){
		swal({
			title: "제목을 입력하세요.",
		});
		return;
	}
	
	if(content.value < 1 || !content.value){
		swal({
			title: "내용을 입력하세요.",
		});
		return;
	}
	
	answerForm.submit();
}

function delSubmit(){
	delForm.submit();
}

function modifySubmit(){
	modifyForm.submit();
}
</script>
