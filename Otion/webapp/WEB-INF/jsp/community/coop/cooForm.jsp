<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 8. 5.     고정현      최초작성
* 2022. 9. 3. 	  서효림	  1차 수정
* 2022. 9. 5. 	 서효림	  2차 수정
* 2022. 9. 6. 	 서효림	  3차 수정
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<script src="//cdn.ckeditor.com/4.19.1/standard/ckeditor.js"></script>
<script type="text/javascript" src="/smarteditor/js/HuskyEZCreator.js" charset="utf-8"></script>
<sec:authorize access="isAuthenticated()">
   <sec:authentication property="principal.realMember" var="member" />
</sec:authorize>

<style>
   #cooTitle{
      background: white;
      margin-top: 8px;
   }
   
  #tagInput{
  	 border:0 solid black;
  }
  
 #tagInput:focus {outline:0 solid black;}
 
 #title{

	 width: 100%;
	 font-size: 30px;
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
.form-control{
	background-color: white;
}


.form-control:focus{
	background-color: white;
}
.form-select{
	background-color: white;
}
.form-control:placeholder{
	background-color: #778295;
}
</style>

<c:if test="${not empty message }">
	<script>
	
	swal({
		  title: "${message }",
		  icon: "success",
		});
		
	</script>
	<c:remove var="message" scope="session" />
</c:if>

<form:form modelAttribute="cooboard" method="post" enctype="multipart/form-data" id="cooForm">
	<form:input path="writerId" class="form-control" autocomplete="off" type="hidden" value="${member.memId}"/>
	<table style="color: black;">
		<tr>
			<td>			
				<form:select path="pjId" class="form-select" id="projName" style="height: 62px;">
					<form:option value="">프로젝트명</form:option>
					<c:forEach items="${project }" var="proj">
						<form:option value="${proj.pjId }">${proj.pjName }</form:option>
					</c:forEach>
				</form:select>
				<form:errors path="pjId" element="span" cssClass="error" /></td>
			<td colspan="5" style="width: 80%;">
			<form:input path="cooTitle" class="form-control" autocomplete="off" id="title" placeholder="제목을 입력하세요." style=" margin-top: 10px;"/>
				<form:errors path="cooTitle" element="span" cssClass="error" /></td>
		</tr>
		<tr style="text-align: center;">
			<td>모집인원</td>
			<td style="width: 60px; margin-bottom: 10px;"><select class="form-select cooSelect">
					<option value="3">3명</option>
					<option value="6">6명</option>
					<option value="12">12명</option>
					<option value="20">20명</option>
				</select>
				<form:errors path="cooPeopleNum" element="span" cssClass="error" /></td>
			<td>시작일자</td>
			<td><form:input type="date" path="cooStart" autocomplete="off" id="startDate" class="form-control inputDate"/> </td>
			<td>마감일자</td>
			<td><form:input type="date" path="cooEnd" id="endDate" class="form-control inputDate"/></td>
		</tr>
		<tr>
			<td colspan="6" style="padding-top: 10px;">
				<form:textarea path="cooContent" class="form-control" id="content" placeholder="내용을 입력하세요."/>
				<form:errors path="cooContent" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<td>
				<form:input path="cooPeopleNum" type="hidden" class="form-control" autocomplete="off" id="peopleNum" placeholder="협업 가능 인원 수를 20명 이내로 입력하시오."/>
			</td>
		</tr>
		<tr>
			<td colspan="6">
				<form:input type="file" autocomplete="off" path="cooFiles" multiple="true"/>
			</td>
		</tr>
		<c:if test="${not empty cooboard.attatchList }">
			<tr><td colspan="6">
				<c:forEach items="${cooboard.attatchList }" var="attatches">
					<span>${attatches.originNm }</span><i name="i-update-cancel" onclick="attaDelBtn($(this))" class="ri-close-fill i-btn" 
					data-attnum="${attatches.attatchNum }" data-attorder="${attatches.attatchOrder }"></i> <br>
				</c:forEach>
				</td>
			</tr>
		</c:if>
		<tr>
			<td colspan="6">
				<div id="subBtn"><input type="submit" autocomplete="off" value="등록" class="btn btn-primary" /></div>
			</td>
		</tr>
	</table>

	
	
	
	<!-- 프로젝트를 완료 했는가 -->
<%-- 	<form:input path="cooDoneYn" class="form-control" id="cooYN" placeholder="완료여부(Y/N 중 선택 가능)"/> --%>
<%-- 	<form:errors path="cooDoneYn" element="span" cssClass="error" /> --%>
	<!-- 프로젝트를 완료 했는가 -->
	
	<!-- 어떤 프로젝트를 모집하는가 -->
	<!-- 어떤 프로젝트를 모집하는가 -->
	

</form:form>

<script>
//edit
CKEDITOR.replace('cooContent', {
	filebrowserImageUploadUrl :  "${cPath}/cooboard/image?command=imageUpload&${_csrf.parameterName}=${_csrf.token}"
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
	
	$("#cooForm").append(delNum, delOrder);
}

// 날짜 선택
$("#subBtn").on("click",function(event){
    event.preventDefault();
//     if($("#search").data("name") == "ex"){
       let startDate=document.getElementById('startDate').value;
       let endDate=document.getElementById('endDate').value;
       let projPeople = $(".cooSelect option:selected").val();
//        console.log("codddo",projPeople);
       $("input[name='cooPeopleNum']").attr("value",projPeople );
       if (startDate==''|| endDate==''){
          swal("날짜를 입력해주세요","")
          return false;
       }else if(startDate>endDate){
          swal("협업 날짜를 다시 확인해주세요","")
          return false;
       }
      cooForm.submit();
       
});  

</script>