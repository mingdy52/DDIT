<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="http://cdn.ckeditor.com/4.19.1/standard/ckeditor.js"></script>

<style>
 .delBtn{
/*  	background-color: black; */
 	margin-left:10px;
 	padding:3px;
 	color: darkgray;
/*  	border-radius: 10px; */
 	float: right;
 }
 
 #aa{
 	padding: 3px;
/*  	list-style-type: '➕'; */
	list-style-type: '✔';
 }
#circle {
  width : 250px;
  height : 250px;
  border-radius: 50%;
}

#Tagchkbox{
	margin: auto;
	width: 70%;
/* 	background-color: #E6E0F8; */
	padding: 30px;
	text-align: center;
	color: black;
	border: 1px solid black;
	min-height: 100px;
	
}

h4{
	color: black;
	text-align: center;
}

#addBtn{
	font-size: 35px;
	color: black;
}

#addBtn:hover{
	color: green;
	transform: rotate( 720deg );
}

#addAss {
  width:68%;
  height: auto;
  font-size: 15px;
/*   border: 0; */
/*   border-radius: 15px; */
/*   outline: none; */
  padding-left: 20px;

}

#presen{
	color: black;
	min-height: 200px;
	margin: auto;
	width: 70%;
}

#myList{
	margin: auto;
	width: 60%;
	padding: 10px;
}

.assinput{
	padding: 15px;
}

li#aa{
	border-bottom: 1px solid black;
}

#cke_presen{
color: black;
	min-height: 200px;
	margin: auto;
	width: 70%;
}
</style>
<div>
	<div style="text-align: center;">
		<img src="${cPath }/resources/profileImages/${expert.profileImg}" id="circle" /><br>
		<h2 style="padding: 10px;">${expert.expertId}님의 프로필</h2>  
	</div>
</div>
<hr>
<h4 style="text-align: center; color: black;">자신에게 해당되는 태그를 선택해주세요.</h4>
		<div id="Tagchkbox">
			<label><input type="checkbox" value="#JAVA">#JAVA</label>
			<label><input type="checkbox" value="#PYTHON">#PYTHON</label>
			<label><input type="checkbox" value="#HTML">#HTML</label>
			<label><input type="checkbox" value="#C">#C</label>
			<label><input type="checkbox" value="#C++">#C++</label>
			<label><input type="checkbox" value="#C#">#C#</label>
			<label><input type="checkbox" value="#ORACLE">#ORACLE</label>
			<label><input type="checkbox" value="#HTML5">#HTML5</label>
			<label><input type="checkbox" value="#JSP">#JSP</label>
			<label><input type="checkbox" value="#React.js">#React.js</label>
			<label><input type="checkbox" value="#jQuery">#jQuery</label>
			<label><input type="checkbox" value="#Android">#Android</label>
			<label><input type="checkbox" value="#CSS">#CSS</label>
			
		</div>
<form:form id="profile" action="${cPath }/iexpert/profileForm" method="POST" modelAttribute="expert" enctype="multipart/form-data">
<table class="table table-borderless mb-0">
	<tr>
		<td><form:input  type="hidden" class="form-control" path="expertId" value="${expert.expertId}"/></td>
	</tr>
	<tr>
		<td><h4>자기소개</h4></td>
	</tr>
	<tr>
		<td><textarea id="presen" class="form-control" name="presentation">${expert.presentation }</textarea></td>
	<tr> 
	<tr>
		<td><h4>LINK</h4></td>
	</tr>
	<tr>
		<td><form:input style="width : 70%; margin: auto; padding: 10px; background-color: white;" class="form-control" path="exLink" value="${expert.exLink }" placeholder="링크" /></td>
	</tr>
	<tr>
		<td><form:input  class="form-control"  path="exTag" id ="TagArr" value="${expert.exTag }" type="hidden"/></td>
	<tr>
		<td colspan="2"><form:input  class="form-control" path="assume" id="assumeArr" type="hidden" value="${expert.assume }"/></td>
	</tr>

</table>
	<div>
	<h4>이력 & 경력</h4>
	<div id="myList">
	<ul id="ul_list">
		<c:forEach items="${expert.assume }" var="ass" varStatus="status">
			<li id="aa" data-idx = ${status.index } data-ass=${ass} >${ass }<span class=" waves-effect delBtn" data-ass="${ass}" }>삭제</span></li>
		</c:forEach>

	</ul>
	</div>
	<div class="d-flex justify-content-center assinput">
	<input type="text" autocomplete="off" placeholder="이력을 작성해주세요.." id="addAss"  class="form-control" style="background-color: white;"><span class="waves-effect waves-light"id="addBtn">+</span></div>
	</div>
	
	<input type="submit" class="btn btn-primary waves-effect waves-light" value="제출" style="float: right; ">

</form:form>

<script>

CKEDITOR.replace('presentation', {
	filebrowserImageUploadUrl :  "${cPath}/expert/image?command=imageUpload&${_csrf.parameterName}=${_csrf.token}"
	});
CKEDITOR.editorConfig = function( config ) {
	config.entities_latin = false; 
  	config.entities_greek = false; 
  	config.entities = false; 
  	config.basicEntities = false; 
};
$(document).ready(function() {
	   var asslist = new Array();
	  
	   $("li[id='aa']").each(function(index, item){
		   asslist.push($(item).attr('data-ass'));
// 	       console.log(index);
// 	       console.log(item);
	   });
	   
	   console.log(asslist);
	   
	   $("#addBtn").on("click", function(){

			   let addAss = $("#addAss").val();
				asslist.push(addAss)
// 				console.log(addAss);
// 				console.log(asslist);
				var ul_list = $("#ul_list"); //ul_list선언
				let addidx = asslist.length -1;
// 				console.log(addidx)  
				ul_list.append("<li id='aa'data-ass='"+addAss+"' data-idx ="+addidx+">"+addAss+"<span class='waves-effect delBtn' data-ass='"+addAss+"' }>삭제</span></li>"); //ul_list안쪽에 li추가
		 	    $('#assumeArr').attr('value', asslist);

			
		   $('#addAss').val('');
			
		});
	   
	   
	   let proflieDel = $("#profile").on("click", ".delBtn", function(){
			let ass = $(this).data("ass");
			let parent = $(this).parent();
			let idx = parent.data("idx");
// 			console.log(idx);
// 			alert(ass);
			$(this).parent("li").hide();

			asslist.splice(idx,1);

// 			console.log(asslist);
			$('#assumeArr').attr('value', asslist);
		});
	   
	   
	});



Taglength = $("input:checkbox").length;
Tagval = $("input:checkbox").val();

console.log("Taglength : "+Taglength);
console.log("Tagval : "+Tagval);


exTag = $("input[name='exTag']").val();
console.log("exTag :: "+exTag);
if(exTag.indexOf("#JAVA")!=-1){
	$("input[value='#JAVA']").attr("checked", 'true');
} 

if(exTag.indexOf("#PYTHON")!=-1){
	$("input[value='#PYTHON']").attr("checked", 'true');
} 

if(exTag.indexOf("#C")!=-1){
	$("input[value='#C']").attr("checked", 'true');
} 

if(exTag.indexOf("#HTML")!=-1){
	$("input[value='#HTML']").attr("checked", 'true');
} 

if(exTag.indexOf("#C++")!=-1){
	$("input[value='#C++']").attr("checked", 'true');
} 

if(exTag.indexOf("#ORACLE")!=-1){
	$("input[value='#ORACLE']").attr("checked", 'true');
} 

     
if(exTag.indexOf("#HTML5")!=-1){
	$("input[value='#HTML5']").attr("checked", 'true');
} 
if(exTag.indexOf("#JSP")!=-1){
	$("input[value='#JSP']").attr("checked", 'true');
} 
if(exTag.indexOf("#React.js")!=-1){
	$("input[value='#React.js']").attr("checked", 'true');
} 
if(exTag.indexOf("#jQuery")!=-1){
	$("input[value='#jQuery']").attr("checked", 'true');
} 
if(exTag.indexOf("#Android")!=-1){
	$("input[value='#Android']").attr("checked", 'true');
} 
if(exTag.indexOf("#CSS")!=-1){
	$("input[value='#CSS']").attr("checked", 'true');
} 







$("#Tagchkbox").on("click", function(){
	let TagArr = new Array();

	$('input:checkbox').each(function (index){
		if($(this).is(":checked")==true){
	    	let tag = $(this).val();
			console.log($(this).val());
			TagArr.push(tag);
	    	$("input[name='exTag']").attr("value", TagArr);
	    }
	});
	
});




	
</script>