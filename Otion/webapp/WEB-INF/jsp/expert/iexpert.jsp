<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 8. 5.      박채록      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<style>
#circle {
	width: 250px;
	height: 250px;
	border-radius: 50%;
}

.card-body {
	text-align: center;
	color: black;
}

.assli {
	list-style-type: '✔ ';
	text-align: left;
	padding: 2px;
	font-size: 18px;
}

.s-title {
	float: left;
	font-size: 20px;
	border-bottom: 1px solid black;
	width: 100%;
	text-align: left;
}
.modal-title{
	text-align: center;
	font-weight: bold;
}
</style>
 <div class="card-body">
	<div>
		<img src="${cPath }/resources/profileImages/${expert.profileImg}" id="circle" /><br>
		<a class="btn btn-link" data-bs-toggle="modal" data-bs-target="#imgModal">사진변경</a>
		<c:set var="memList" value="${expert.memList }" />
				<c:if test="${not empty memList }">
					<c:forEach items="${memList }" var="mem">
						<h2>${mem.memNick }님의 profile</h2>  
<%-- 						${expert.expertId}          --%>
				    </c:forEach>
				</c:if>
	</div>
	
	<div>
		<h4> ${expert.exTag }</h4>
		<p style="padding: 5px; margin-left:25%; width:50%;">${expert.presentation }</p><br>
	</div>
	<div style="width: 100%; min-height: 200px;">
	<div style="width: 45%; float: left; margin-right: 2%; margin-left: 2%;" >
		<p class="s-title">경력&이력</p>
		<c:set value="${expert.assumeArr}" var="assumeArr" />
		<c:if test="${not empty assumeArr }">
			<c:forEach items="${assumeArr }" var="arr">
				<li class="assli">${arr}</li>
			</c:forEach>
		</c:if>
		<c:if test="${empty assumeArr }">
			<li class="assli">작성된 이력이 없습니다.</li>
		</c:if>
	</div>
	<div style="width: 45%; float: left;">
		<p class="s-title">연관링크</p>
		<a href="${expert.exLink }" style="font-size: 18px; color: black; text-align: right;">${expert.exLink }</a>
	</div>
	</div>
	<div style="float: right;">
	<button class="btn btn-secondary waves-effect waves-light" onclick="location.href='${cPath}/iexpert/profileForm'">수정</button>
	<button type="button" class="btn btn-primary waves-effect waves-light" data-bs-toggle="modal" data-bs-target="#myModal">전문가해지</button>
	<!-- <button id="authBtn">인증</button> -->
	</div>
</div>




<form:form id="delForm" action="${cPath}/iexpert/profile" method="post" modelAttribute="expert">
	<input type="hidden" name="_method" value="delete">
	<input type="hidden" name="expertId" value="${expert.expertId }">
</form:form>


<%-- <form method="get" action="https://testapi.openbanking.or.kr/oauth/2.0/authorize" target="_blank"> --%>
<!--   <input type="hidden" name="response_type" value="code"/> -->
<!--   <input type="hidden" name="client_id" value="74a071a0-ed82-49ae-b0c5-fc3ab80a5eaa"/> -->
<!--   <input type="hidden" name="redirect_uri" value="http://localhost/Otion/iexpert/profile"/> -->
<!--   <input type="hidden" name="scope" value="login inquiry transfer"/> -->
<!--   <input type="hidden" name="state" value="b80BLsfigm9OokPTjy03elbJqRHOfGSY"/> -->
<!--   <input type="hidden" name="auth_type" value="0"/> -->
<!--   <input type="submit" value="requestAuth"/> -->
<%-- </form> --%>



<%-- <form method="post" action="https://testapi.openbanking.or.kr/oauth/2.0/authorize" target="_blank"> --%>
<!--   <input type="hidden" name="response_type" value="code"/> -->
<!--   <input type="hidden" name="client_id" value="74a071a0-ed82-49ae-b0c5-fc3ab80a5eaa"/> -->
<!--   <input type="hidden" name="redirect_uri" value="http://localhost/Otion/iexpert/profile"/> -->
<!--   <input type="hidden" name="scope" value="login inquiry transfer"/> -->
<!--   <input type="hidden" name="state" value="b80BLsfigm9OokPTjy03elbJqRHOfGSY"/> -->
<!--   <input type="hidden" name="auth_type" value="0"/> -->
<!--   <input type="submit" value=""/> -->
<%-- </form> --%>

<div id="myModal" class="modal fade" tabindex="-1" aria-labelledby="myModalLabel" style="display: none;" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<h5 style="text-align: center; font-weight: bold; margin-bottom: 30px;">전문가해지동의서</h5>
				<div class="checkbox_group" style="color: black;">
					<input type="checkbox" id="check_1" class="normal"> 
					<label for="check_1">정산을 받지 않고 해지할 수 없으므로 탈퇴 예정이라면 </label><label>상품 판매 중지 및 정산을 받으시기 바랍니다.</label> <br> 
					
					<input type="checkbox" id="check_2" class="normal"> <label for="check_2">해지 시 그간의 판매실적, 후기 등 모든 정보가 삭제됩니다.</label> <br> 
					<input type="checkbox" id="check_3" class="normal"> 
					<label for="check_3">해지 후 정산 정보 확인이 불가합니다.</label> <br> 
					<input type="checkbox" id="check_all"><label for="check_all" style="font-weight: bold;">전체 동의</label>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-light waves-effect" data-bs-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary waves-effect waves-light" id="expDel">탈퇴하기</button>
			</div>
		</div>
	</div>
</div>

<div id="imgModal" class="modal fade" tabindex="-1" aria-labelledby="myModalLabel" style="display: none;" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="myModalLabel">프로필이미지를 선택해주세요</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<form:form method="post" action="${cPath}/iexpert/profile/img"  id="imgForm" modelAttribute="expert" enctype="multipart/form-data">
					<input name="profileImage" type="file" class="form-control" />
				</form:form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-light waves-effect"
					data-bs-dismiss="modal">Close</button>
				<button type="submit"
					class="btn btn-primary waves-effect waves-light" id="imgBtn">수정하기</button>
			</div>
		</div>
	</div>
</div>
<script>


//체크박스 전체 선택
$(".checkbox_group").on("click", "#check_all", function () {
    $(this).parents(".checkbox_group").find('input').prop("checked", $(this).is(":checked"));
});

// 체크박스 개별 선택
$(".checkbox_group").on("click", ".normal", function() {
    var is_checked = true;

    $(".checkbox_group .normal").each(function(){
        is_checked = is_checked && $(this).is(":checked");
    });

    $("#check_all").prop("checked", is_checked);
});


$("#expDel").on("click", function(){
	var okchk = $("input[type='checkbox']");
	if (okchk.length == okchk.filter(":checked").length) {
		$('#myModal').modal('hide')
		swal({
		  title: "전문가해지완료",
		  icon: "success",
		});
		delForm.submit();
	}else{
		swal({
			  title: "모든 항목을 동의해주세요.",
			  icon: "warning",
			});
	}

});

$("#imgBtn").on("click", function(){
	$("#imgForm").submit();
	$("#imgModal").modal('hide');
})
</script>
	