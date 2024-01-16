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
 .pagingArea{
	
	margin-top: 10px;
}
/*  	수락대기 */
 	#PG01{
	  background-color: #00BFFF;
 	}
 	
/*  	진행중 */
 	#PG02{
 		background-color: #01DF3A;
 	}
/*  	완료 */
 	#PG03{
 		background-color: #29088A;
 	}
/*  	취소 */
 	#PG04{
 		background-color: #BDBDBD;
 	}

 	.code-btn{
 		width: 80px;
 		text-align: center;
 		padding: 6px;
 		border-radius: 10px;
 		color: white;
 	}
 	.select {
    padding: 15px 10px;
    text-align: center;
}
	.select input[type=radio]{
	    display: none;
	}
	.select input[type=radio]+label{
	    display: inline-block;
	    cursor: pointer;
	/*     height: 24px; */
	/*     width: 90px; */
		padding : 10px;
	    line-height: 24px;
	    text-align: center;
	    font-weight:bold;
	    font-size:15px;
	}
	.select input[type=radio]+label{
	    background-color: #fff;
	    color: #333;
	}
	.select input[type=radio]:checked+label{
	/*     background-color:  */
	    color: #6c6ff5;
	    font-weight: bold ;
	}
 </style>
 <c:set value="${pagingVO.dataList}" var="req"/>
  <div class="select">
     <input type="radio" id="select" name="shop" value="전체"><label for="select">전체</label>
     <input type="radio" id="select2" name="shop" value="수락대기"><label for="select2">수락대기</label>
     <input type="radio" id="select3" name="shop" value="진행중"><label for="select3">진행중</label>
     <input type="radio" id="select4" name="shop" value="완료"><label for="select4">완료</label>
     <input type="radio" id="select5" name="shop" value="취소"><label for="select5">취소</label>
</div>
 <div class="table-responsive">
<table class="table table-hover mb-0" style="text-align: center; color: black;">
 	<thead>
 		<tr>
 			<th>NO</th>
 			<th>구매번호</th>
 			<th>구매자</th>
 			<th width="320px">상품명</th>
 			<th>상품가격</th>
 			<th>요청상태</th>
 			<th>결제일자</th>
 		</tr>
 	</thead>
 	<tbody>
 		<c:if test="${not empty  req}">
 		<c:forEach var="req" items="${req }">
 			<tr>
 				<td id="rnum">${req.rnum }</td>
 				<td id="myEprod">${req.myEprod }</td>
 				<td>${req.buyerId }</td>
 				<td>${req.eprodName }</td>
 				<td>${req.eprodPrice }</td>
 				<td><div id='${req.progressCode }' class="code-btn">${req.comCodeNm }</div></td>
 				<td>${req.epayDate }</td>
 			</tr>
 		
 		</c:forEach>
 		</c:if>
	</tbody>
	
 </table>

</div>
<div class="d-flex flex-wrap gap-2 justify-content-center pagingArea">
${pagingVO.pagingHTMLBS }
</div>




<!-- PG01 수락대기 -->
<!-- PG02 진행중 -->
<!-- PG03 완료 -->
<!-- PG04 취소 -->
<form:form id="searchForm" modelAttribute="simpleCondition" action="${cPath }/iexpert/request" method="get">
	<input type="hidden" name="page" />
	<form:input type="hidden" path="searchType" />
	<form:input type="hidden" path="searchWord" />
</form:form>



<div id="myModal" class="modal fade" tabindex="-1" aria-labelledby="myModalLabel" style="display: none;" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="myModalLabel"></h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<h5 style="text-align: center;"><span style="font-weight: bold;" id="requestId"></span>님의 요청을 수락하시겠습니까?</h5>
			</div>
			<div class="modal-footer">
				<button type="button" id="reject" class="btn btn-light waves-effect"
					data-bs-dismiss="modal">거절</button>
				<button type="button" id="okay"
					class="btn btn-primary waves-effect waves-light">수락</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>


<div id="refundModal" class="modal fade" tabindex="-1" aria-labelledby="myModalLabel" style="display: none;" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="myModalLabel"></h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body" style="text-align: center;">
				<h5>거절 사유를 선택해주세요.</h5>
				<select id="refund-box" class="form-select">
				<option value=""selected disabled hidden >거절사유선택</option>
				<c:forEach items="${comCode }" var="comCode">
					<option value="${comCode.comCode }">${comCode.comCodeNm }</option>
				</c:forEach>
				</select>
			</div>
			<div class="modal-footer">
				<button type="button" id="refundNO" class="btn btn-light waves-effect"
					data-bs-dismiss="modal">거절</button>
				<button type="button" id="refundOK"
					class="btn btn-primary waves-effect waves-light">수락</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
 
<form:form modelAttribute="myExpert" action="${cPath}/iexpert/request" method="post" id="ReqForm">
	<input type="hidden" name="myEprod">
	<input type="hidden" name="progressCode">
	<input type="hidden" name="refundReasonCode">
</form:form>

<script>


let searchForm = $("#searchForm");
let pagingArea = $(".pagingArea").on("click", "a", function(event){
	let page = $(this).data("page");
	searchForm.find("[name=page]").val(page);
	searchForm.submit();
});

$(".select").on("click",function(){
	let radio = $("input[name='shop']:checked").val();
	console.log(radio);
	if(radio=="전체"){
		$("input[name='searchType']").attr("value", "");
		$("input[name='searchWord']").attr("value", "");
	}else{
		
	$("input[name='searchType']").attr("value", "comCodeNm");
	$("input[name='searchWord']").attr("value", radio);
	}
	searchForm.submit();
});

$("tr").on("click", function(){
	let myeprod = $(this).children().eq(1).text();
	let codeName = $(this).children().eq(5).text();
	let requestId = $(this).children().eq(2).text();
	console.log(myeprod+"이랑"+codeName);
	
	$("#requestId").append(requestId);
	
	if(codeName == '수락대기'){
		
		$('#myModal').modal('show');
		
		
		
		$("#okay").on("click", function(){
// 			alert("수락 ");
			$("input[name='myEprod']").attr("value", myeprod);
			$("input[name='progressCode']").attr("value", "PG02");
			$('#myModal').modal('hide');
			$("#ReqForm").submit();
		});
		
		$("#reject").on("click", function(){
// 			alert("거절 ");
			$("#myModal").modal('hide');
			$("#refundModal").modal('show');
		
			$("#refund-box").on("click", function(){
			let refundNm = $("#refund-box option:selected").val();
				console.log(refundNm);
				$("input[name='refundReasonCode']").attr("value", refundNm);
				$("input[name='myEprod']").attr("value", myeprod);
				$("input[name='progressCode']").attr("value", "PG04");
				$('#myModal').modal('hide');	
			});
			
			$("#refundOK").on("click",function(){
				$("#ReqForm").submit();
			});
		});
		
	}else if(codeName == '취소'){
		swal({
			  title: "취소된 상품입니다.",
			  icon: "warning",
			});
	
	}else{
		link="${cPath}/myexpert/"+myeprod;
		location.href = link;
	}
});




</script>