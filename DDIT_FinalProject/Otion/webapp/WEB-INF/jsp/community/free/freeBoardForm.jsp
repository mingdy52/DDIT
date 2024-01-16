<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 8. 5.      서효림      최초작성
* 2022. 8. 18.		서효림	1차수정
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<script type="text/javascript" src="/smarteditor/js/HuskyEZCreator.js" charset="utf-8"></script>
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
	 margin-bottom: 10px;
 }
 
 #title:focus {outline:0 solid black;}
 
 #emergency{
 	 width:30%;
	 margin-bottom: 10px;
 }
 #content{
	 margin-bottom: 10px;
	 height:400px;
 }
 #subBtn{
	 margin-top: 10px;
	 float:right;
 }
 .form-control {
 	background-color: white;
 }

</style>

<form:form modelAttribute="freeBoard" method="post" enctype="multipart/form-data" id="freeBoardForm">
	<form:input path="writerId" class="form-control" autocomplete="off" type="hidden" value="${member.memId}"/>
	
    <form:input path="freeTitle" class="form-control" autocomplete="off" id="title" placeholder="제목을 입력하세요."/>
	<form:errors path="freeTitle" element="span" cssClass="error" />
	
	<form:textarea path = "freeContent" class="form-control" id="content" placeholder="내용을 입력하세요."></form:textarea>
	
	<form:errors path="freeContent" element="span" cssClass="error" />
	<form:input type="file" autocomplete="off" path="freeFiles" multiple="true"/>
	
	<div id="subBtn">
		<input type="submit" value="저장" class="btn btn-primary"/>
	</div>
	
	<div>
		<c:if test="${not empty freeBoard.attatchList }">
			<div>
				<c:forEach items="${freeBoard.attatchList }" var="attatches">
					<span>${attatches.originNm }</span><i name="i-update-cancel" onclick="attaDelBtn($(this))" class="ri-close-fill i-btn" 
					data-attnum="${attatches.attatchNum }" data-attorder="${attatches.attatchOrder }"></i> <br>
				</c:forEach>
			</div>
		</c:if>
   	</div>
	
</form:form>

<script>

// edit
CKEDITOR.replace('freeContent', {
	filebrowserImageUploadUrl :  "${cPath}/freeBoard/image?command=imageUpload&${_csrf.parameterName}=${_csrf.token}"
	});
CKEDITOR.editorConfig = function( config ) {
	config.entities_latin = false; 
  	config.entities_greek = false; 
  	config.entities = false; 
  	config.basicEntities = false; 
};

function attaDelBtn(el){
	el.css("display", "none");
	el.prev().css("display", "none");
	let attatchNum = el.data("attnum");
	let attatchOrder = el.data("attorder");
	
	let delNum = $("<input>").attr("name", "delAttatchNum").attr("type", "hidden").val(attatchNum);
	let delOrder = $("<input>").attr("name", "delAttatchOrder").attr("type", "hidden").val(attatchOrder);
	
	$("#freeBoardForm").append(delNum, delOrder);
}
</script>