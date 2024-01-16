<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 8. 18.      박채록      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<script src="http://cdn.ckeditor.com/4.19.1/standard/ckeditor.js"></script>
<link href="${cPath }/resources/assets/libs/sweetalert2/sweetalert2.min.css" rel="stylesheet" type="text/css">

<style>

.d-flex{
	color: black;
	font-size: 20px;
	text-align: center;
}
#state{
	
}
.content-box {
	background-color: #ebebff;
	padding: 40px;
	border-radius: 20px; 
}



.writer-cir {
	/* 	  border-radius: 50%; */
	/* 	  background-color: #8ca3bd; */
	/* 	  width: 50px; */
	/* 	  height: 50px; */
	font-weight: bold;
	color: black;
	/* 	  text-align: center; */
	margin-right: 20px;
	/* 	  border-bottom: 1px solid black; */
}

#noQNA{
	text-align: center;
	font-size: 20px;
}

.usebox {
	border: 1px solid black;
}

#getRequest{
	float: right;
}

#request-box{
	border: none;
	box-shadow: none;
}



</style>


<sec:authentication property="principal" var="authMember"/>
<sec:authorize access="hasRole('ROLE_EXPERT')">
	<h2  id="iexpert" style="display: none">전문가</h2>
</sec:authorize>
<%-- ${authMember.realMember.memId } --%>
<h4 style="text-align: center;">${prodName }</h4>
<!-- 현재페이지 보고있는 주체자  -->
<c:set value="${detailList }" var="exp"/>

<div class="d-flex flex-wrap gap-2 justify-content-center" style="font-weight: bold">p r o g r e s s - <div id="state">${exp[0].comCodeNm}</div><div id="nullbox"></div></div><br>
<div>
	<div class="progress">
		<div class="progress-bar" role="progressbar" style="width: 25%;"
			aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"></div>
	</div>
</div>
<div class="card infoComplete" style="margin-top: 60px;">
  <div class="card-body" style="background-color: #F5EFFB; text-align: center; ">
  	<h4>주의사항</h4>
  	<div>
  	<p>① 상담/수업이 시작된 후에는, ’진행중’ 대화 목록에서 시작하신 상담/수업을 확인할 수 있습니다.</p>
	<p>② 정해진 상담/수업 시간이 종료되면 ‘상세정보’화면 하단에 완료하기 버튼이 노출됩니다.</p><p>[완료하기]버튼을 클릭해 상담을 종료할 수 있습니다.</p>
	<p style="color: red;">상품의 경우 제공할 상담 시간의 50%이상이 경과된 경우에만 완료 처리 가능합니다.</p>
	<p>③ 상담/수업이 완료된 후에는, ‘완료’ 대화 목록에서 완료하신 상담/수업을 확인할 수 있습니다.</p>
  	</div>
	<button type="button" class="btn btn-primary waves-effect waves-light goComBtn">완료하기</button>
  </div>
</div>
<br>
<table class="table table-borderless mb-0">
<c:if test="${not empty exp }">
	<c:forEach items="${exp }" var="e" >
		<tr>
			<td><div class="writer-cir" >${e.myexpWriter }</div></td>
		</tr>
		<tr> 
			<td><div class="content-box" data-order="${e.myexpOrder }">${e.myexpContent }</div></td>
		</tr>
		<tr>
			<td colspan="2" style="text-align: right"><div class="expDate">${e.myexpDate }</div></td>
		</tr>
	</c:forEach>

</c:if>
<c:if test="${empty exp }">
<div id="noQNA">EXPERT를 이용중입니다. <br>첫 질문을 작성해주세요!</div><br>
</c:if>
</table>
<br>
<br>
<div class="card" id="request-box">
<h2 style="text-align: center;"> eXpert Answer by use </h2><br>

<input id="myEprod" type="hidden" value="${myEprod}">
<form action="${cPath }/myexpert/${myEprod}" id="reqForm" method="post" >
	<sec:csrfInput/>
	<textarea class="form-control" style="width: 80%; min-height: 180px;" name="myexpContent"></textarea>
	<div id="getRequest">
		<input class="btn btn-primary waves-effect waves-light" type="submit" style="margin: 10px;" value="작성">
	</div>
</form>
</div>

<!-- <hr> -->

<form:form id="comForm" action="${cPath}/myexpert/${myEprod }" method="post">
	<sec:csrfInput/>
	<input type="hidden" name="_method" value="put">
	<input type="hidden" name="progressCode" value="PG03" >
</form:form>


<!-- Modal -->
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static"
	data-bs-keyboard="false" tabindex="-1"
	aria-labelledby="staticBackdropLabel" style="display: none;"
	aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="staticBackdropLabel"></h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body" style="text-align: center">
				<h4 style="font-weight: bold;">이용중인 eXpert를 완료하시겠습니까?</h4>
				<p>완료하신 후에는 상품이용이 불가합니다.</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-light waves-effect"
					data-bs-dismiss="modal">취소</button>
				<button type="button" id="comBtn" class="btn btn-primary waves-effect waves-light">완료</button>
			</div>
		</div>
	</div>
</div>



<script>
	let buyerId = "${buyerId}";
	let expertId = "${expertId}";
	let memberId = "${member.getMemId()}";
	let state = $("#state").text();
	console.log("state:" +state);
	
	let onUseEven = $(".writer-cir:even");
	onUseEven.append("님의 질문");
	
	let onUseOdd = $(".writer-cir:odd").css("text-align", "right");
	onUseOdd.append("님의 답변");
	
	$(".content-box:even").css("width","70%").css("float", "left").css("background-color", "#E6E6E6");
	$(".content-box:odd").css("width","70%").css("float", "right").css("background-color", "#717CFF").css("color", "white");
	$(".expDate:even").css("margin-right","30%")
	
	
	if(state=='완료'){
		$("#request-box").css("display","none");
		$(".progress-bar").css("width", "100%");
		$(".progress-bar").append("100%");
		$(".infoComplete").css("display", "none");
	}else if(state==''){
		
		$("#nullbox").append("진행중");
		$(".progress-bar").css("width", "10%");
		$(".progress-bar").append("10%");
		
		let iexpert = $("#iexpert").text();
		console.log(iexpert)
		
		if(memberId == expertId){
			$("#request-box").css("display","");
			//요청한번했으면 박스 숨기기 
			let reqNumber = $(".content-box").length
// 			console.log(reqNumber%2)
			$(".infoComplete").css("display", "none");
			if(reqNumber%2==0){
				$("#request-box").css("display","none");
			}else{
				$("#request-box").css("display","");
			}
			
		}else{
			$("#request-box").css("display","none");
			//요청한번했으면 박스 숨기기 
			let reqNumber = $(".content-box").length
			console.log(reqNumber%2)
			
			if(reqNumber%2==1){
				$("#request-box").css("display","none");
			}else{
				$("#request-box").css("display","");
			}
			
		}
		
	}else{
		
		$(".progress-bar").css("width", "50%");
		$(".progress-bar").append("50%");
		
		let myEprod = $("input[id='myEprod']").val();
		console.log(myEprod);
		let url = "${cPath}/myexpert/" + myEprod;
		console.log(url);

		$("#reqForm").attr("action", url)

		let iexpert = $("#iexpert").text();
		console.log(iexpert)
		
		if(memberId == expertId){
			$("#request-box").css("display","");
			//요청한번했으면 박스 숨기기 
			let reqNumber = $(".content-box").length
			console.log(reqNumber%2)
			$(".infoComplete").css("display", "none");
			if(reqNumber%2==0){
				$("#request-box").css("display","none");
			}else{
				$("#request-box").css("display","");
			}
			
		}else {
			$("#request-box").css("display","none");
			//요청한번했으면 박스 숨기기 
			let reqNumber = $(".content-box").length
			console.log(reqNumber%2)
			
			if(reqNumber%2==1){
				$("#request-box").css("display","none");
			}else{
				$("#request-box").css("display","");
			}
			
		}
		
		

	
	
	let nullbox = $("#nullbox").text();
	let progressPurcent = $(".progress-bar").text();

	if (nullbox == '진행중' && progressPurcent == '10%') {
		$(".goComBtn").on("click", function() {
			alert("완료할수없습니다.");

		});
	} else {
		$(".goComBtn").on("click", function() {
			$("#staticBackdrop").modal('show');
		});
		
	}
	
	
	$("#comBtn").on("click", function(){
		
		$("#staticBackdrop").modal('hide');
			$("#comForm").submit();
	});
}
	CKEDITOR
			.replace(
					'myexpContent',
					{
						filebrowserImageUploadUrl : "${cPath}/expert/image?command=imageUpload&${_csrf.parameterName}=${_csrf.token}"
					});
	CKEDITOR.editorConfig = function(config) {
		config.entities_latin = false;
		config.entities_greek = false;
		config.entities = false;
		config.basicEntities = false;
	};
</script>
