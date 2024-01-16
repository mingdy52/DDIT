<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 8. 19.      박채록      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<script src="//cdn.ckeditor.com/4.19.1/standard/ckeditor.js"></script>  
<style>
#rate-box fieldset{
    display: inline-block; /* 하위 별점 이미지들이 있는 영역만 자리를 차지함.*/
    border: 0; /* 필드셋 테두리 제거 */
    
}
#rate-box input[type=radio]{
    display: none; /* 라디오박스 감춤 */
}
#rate-box label{
    font-size: 35px; /* 이모지 크기 */
    color: transparent; /* 기존 이모지 컬러 제거 */
    text-shadow: 0 0 0 #f0f0f0; /* 새 이모지 색상 부여 */

}
#rate-box label:hover{
    text-shadow: 0 0 0 #FCF40A; /* 마우스 호버 */
}
#rate-box label:hover ~ label{
    text-shadow: 0 0 0 #FCF40A; /* 마우스 호버 뒤에오는 이모지들 */
}
#rate-box fieldset{
    display: inline-block; /* 하위 별점 이미지들이 있는 영역만 자리를 차지함.*/
    direction: rtl; /* 이모지 순서 반전 */
    border: 0; /* 필드셋 테두리 제거 */
}
#rate-box fieldset legend{
    text-align: left;
}
#rate-box input[type=radio]:checked ~ label{
    text-shadow: 0 0 0 #FCF40A; /* 마우스 클릭 체크 */
}

#title{
		 border:0 solid black;
		 width: 100%;
		 font-size: 30px;
		 border-bottom: 1px solid gray;
		 margin-bottom: 10px;
/* 		 background-color: white; */
	 }
	 
#contents{
/* 	width: 100%; */
	min-height: 200px;
}
#title:focus {outline:0 solid black;}
	 
</style> 
<div class="d-flex flex-wrap gap-2 justify-content-center">
<div id="rate-box" >
	<h5 style="text-align: center;">상품은 어떠셨나요?</h5>
	<h6 style="text-align: center;">상품에 대한 별점을 매겨주세요</h6>
    <fieldset>
		
        <input type="radio" name="rating" value="5" id="rate1"><label for="rate1">⭐</label>
        <input type="radio" name="rating" value="4" id="rate2"><label for="rate2">⭐</label>
        <input type="radio" name="rating" value="3" id="rate3"><label for="rate3">⭐</label>
        <input type="radio" name="rating" value="2" id="rate4"><label for="rate4">⭐</label>
        <input type="radio" name="rating" value="1" id="rate5"><label for="rate5">⭐</label>
        <form:errors path="reviewStar"  class="form-control error" />
    </fieldset>
</div>
</div>

<form:form modelAttribute="review" method="post" >
<sec:csrfInput/>
<table class="table table-borderless mb-0">
	<tr>
		<td><form:input path="reviewNum" value="${myreview.reviewNum}" type="hidden"/></td>
	</tr>
	<tr>
		<td><form:input path="reviewTitle" id="title" value="${myreview.reviewTitle }" placeholder="제목을 입력하세요.."/></td>
	</tr>
	<tr>
		<td><form:errors path="reviewTitle"  class="form-control error" /></td>
	</tr>
	<tr>
		<td><form:input path="reviewContent" id="contents" class="form-control"  value="${myreview.reviewContent }" style="width: 100%;"/>
			<form:errors path="reviewContent"  class="form-control error" />
		</td>
	</tr>
	<tr>
		<td><form:input path="reviewStar" class="form-control" value="${myreview.reviewStar }" type="hidden"/></td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="button" onclick="history.back()" style="float: right; margin-left: 2px;" class="btn btn-secondary waves-effect waves-light" value="취소">
			<input type="submit" style="float: right;" class="btn btn-primary waves-effect waves-light" value="작성">
		</td>
	</tr>
</table>
</form:form>
<!-- <div id="rate-box"> -->
<!--     <fieldset> -->
<!--         <legend>이모지 별점</legend> -->
<!--         <input type="radio" name="rating" value="5" id="rate1"><label for="rate1">⭐</label> -->
<!--         <input type="radio" name="rating" value="4" id="rate2"><label for="rate2">⭐</label> -->
<!--         <input type="radio" name="rating" value="3" id="rate3"><label for="rate3">⭐</label> -->
<!--         <input type="radio" name="rating" value="2" id="rate4"><label for="rate4">⭐</label> -->
<!--         <input type="radio" name="rating" value="1" id="rate5"><label for="rate5">⭐</label> -->
<!--     </fieldset> -->
<!-- </div> -->

<script>

// CKEDITOR.replace('reviewContent');

// CKEDITOR.editorConfig = function( config ) {
// 	config.entities_latin = false; 
//   	config.entities_greek = false; 
//   	config.entities = false; 
//   	config.basicEntities = false; 
// };


	$("input[name='rating']").on("click",function(){
		let rate = $(this).val();
		console.log(rate);
		$("input[name='reviewStar']").attr("value", rate);
	});
</script>