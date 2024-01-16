<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 8. 26.      서효림      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<script src="//cdn.ckeditor.com/4.19.1/standard/ckeditor.js"></script>
<sec:authorize access="isAuthenticated()">
   <sec:authentication property="principal.realMember" var="member" />
</sec:authorize>

<style>
   #postTitle{
      background: white;
   }
   
  #tagInput{
  	 border:0 solid black;
  }
  
 #tagInput:focus {outline:0 solid black;}
 
 #title{
	 border:0 solid black;
	 width: 100%;
	 font-size: 30px;
	 border-bottom: 1px solid gray;
 }
 
 #title:focus {outline:0 solid black;}
 
 #emergency{
 	 width:30%;
	 margin-bottom: 10px;
 }
 #content{
	 height:400px;
 }
 
.inputForm{
	margin-bottom: 10px;
} 
 #subBtn{
	 margin-top: 10px;
	 float:right;
 }
 #nickName, #qnaPass{
	 width:30%;
 }
 
 #radioBox{
 	width:100%;
 }
 
</style>
<form:form modelAttribute="qna" method="post" enctype="multipart/form-data" id="qnaForm">
	
    <form:input path="qnaTitle" class="form-control inputForm" id="title" placeholder="제목을 입력하세요." autocomplete="off"/>
	<form:errors path="qnaTitle" element="span" cssClass="error" />
	
	<form:textarea path="qnaContent" class="form-control" id="content" placeholder="내용을 입력하세요."></form:textarea>
	<form:errors path="qnaContent" element="span" cssClass="error" />
	
	<c:if test="${empty member.memNick}">
	* 1~8 자리로 입력하세요.
	</c:if>
	<form:input path="writerId" class="form-control inputForm" id="nickName" type="text" 
			value="${member.memNick}" placeholder="등록할 이름을 입력하세요." autocomplete="off"/>
	<form:errors path="writerId" element="span" cssClass="error" />
	
	<div>
		* 비밀번호는 비밀글 조회 또는 수정/삭제 시 사용됩니다. <br>
		* 4~12 자리로 입력하세요.
		<form:input type="password" id="qnaPass" path="qnaPass" class="form-control" placeholder="비밀번호 입력하세요." />
		<form:errors path="qnaPass" element="span" cssClass="error" />
	</div>
	
	<div id="radioBox">
		<input type="radio" name="qnaPublicYn" value="Y" checked="checked"/>공개
		<input type="radio" name="qnaPublicYn" value="N" />비공개
	</div>
	
	<div id="subBtn">
		<input type="button" onclick="f_submit()" value="작성" class="btn btn-primary"/>
	</div>
</form:form>
<input value="${modify }" type="hidden" id="modify">

<script>

CKEDITOR.replace('qnaContent', {
	filebrowserImageUploadUrl :  "${cPath}/freeBoard/image?command=imageUpload&${_csrf.parameterName}=${_csrf.token}"
	});
CKEDITOR.editorConfig = function( config ) {
	config.entities_latin = false; 
	config.entities_greek = false; 
	config.entities = false; 
	config.basicEntities = false; 
};

let member = "${empty member.memNick}";
if(member === "false"){
	nickName.setAttribute("readonly", true);
}

function attaDelBtn(el){
	el.css("display", "none");
	el.prev().css("display", "none");
	let attatchNum = el.data("attnum");
	let attatchOrder = el.data("attorder");
	
	let delNum = $("<input>").attr("name", "delAttatchNum").attr("type", "hidden").val(attatchNum);
	let delOrder = $("<input>").attr("name", "delAttatchOrder").attr("type", "hidden").val(attatchOrder);
	
	$("#qnaForm").append(delNum, delOrder);
}


function f_submit(){
	if(modify.value=="modify"){
		qnaForm.setAttribute("action", "${cPath }/qna/${qna.qnaNum}/edit")
	}
	qnaForm.submit();
}
</script>
