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
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<style>
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
 		margin: auto;
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

.pagingArea{
	text-align: center;
	margin-top: 20px;

}


</style>
    <h3>my expert</h3>
 <div class="select">
     <input type="radio" id="select" name="shop" value="전체"><label for="select">전체</label>
     <input type="radio" id="select2" name="shop" value="수락대기"><label for="select2">수락대기</label>
     <input type="radio" id="select3" name="shop" value="진행중"><label for="select3">진행중</label>
     <input type="radio" id="select4" name="shop" value="완료"><label for="select4">완료</label>
     <input type="radio" id="select5" name="shop" value="취소"><label for="select5">취소</label>
</div>
    <c:set value="${pagingVO.dataList }" var="myexpert"/>
    <table class="table table-hover mb-0" style="text-align: center; color: black;">
    	<thead>
    		<tr>
    			<th>NO</th>
    			<th>상품번호</th>
    			<th>상풍명</th>
    			<th>가격</th>
    			<th>결제일자</th>
    			<th>진행상태</th>
    		</tr>
    	</thead>
    	<tbody>
    <c:if test="${not empty myexpert }">
    <c:forEach items="${myexpert }" var="me" >
    	<tr>
    		<td>${me.rnum }</td>
    		<td>${me.myEprod}</td>
    		<td>${me.eprodName}</td>
    		<td>${me.eprodPrice}</td>
    		<td>${me.epayDate}</td>
    		<td ><div id='${me.progressCode }' class="code-btn">${me.comCodeNm }</div></td>
    	</tr>
    </c:forEach>
    	<tr>
    	
    	</tr>
    </c:if>
    <c:if test="${empty myexpert }">
    	<tr>
    		<td colspan="6">이용 중인 expert가 없습니다.</td>
    	</tr>
    </c:if>
    </tbody>
    </table>
<div class="d-flex justify-content-center pagingArea">
	${pagingVO.pagingHTMLBS } 
</div>
<div style="text-align: right;">
 <button type="button" class="btn btn-link" data-bs-toggle="modal" data-bs-target=".bs-example-modal-center">전문가신청</button>
</div>
<form:form id="searchForm" modelAttribute="simpleCondition" action="${cPath }/myexpert" method="get">
	<input type="hidden" name="page" />
	<form:input type="hidden" path="searchType" />
	<form:input type="hidden" path="searchWord" />
</form:form>

<form id="refundForm" method="post">
	<sec:csrfInput/>
	<input type="hidden" name="myEprod" />
</form>

<div class="modal fade bs-example-modal-center" tabindex="-1"
	aria-labelledby="mySmallModalLabel" style="display: none;"
	aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title"></h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body" style="text-align: center;">
				<button class="btn btn-primary waves-effect waves-light"  onclick="location.href='${cPath}/expert/form'"><i class="mdi mdi-arrow-right-thick"></i>전문가신청하러가기</button>
				<br><br>
				<button  class="btn btn-secondary waves-effect waves-light" onclick="location.href='${cPath}/expert/formdetails'"><i class="mdi mdi-arrow-right-thick"></i>전문가신청내역보러가기</button>
				<br><br>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<script>
	let searchForm = $("#searchForm");
	let pagingArea = $(".pagingArea").on("click", "a", function(event) {
		let page = $(this).data("page");
		searchForm.find("[name=page]").val(page);
		searchForm.submit();
	});

	$(".select").on("click", function() {
		let radio = $("input[name='shop']:checked").val();
		console.log(radio);
		if (radio == "전체") {
			$("input[name='searchType']").attr("value", "");
			$("input[name='searchWord']").attr("value", "");
		} else {

			$("input[name='searchType']").attr("value", "comCodeNm");
			$("input[name='searchWord']").attr("value", radio);
		}
		searchForm.submit();
	});

	$("tr").on("click", function() {
		let state = $(this).children().eq(5).text();
		let myEprod = $(this).children().eq(1).text();
		console.log(state);

		if (state == '취소') {
			$("input[name='myEprod']").attr("value", myEprod);

			let data = $("#refundForm").serialize();
			$.ajax({
				url : "${cPath}/refundWhy",
				method : "POST",
				data : data,
				dataType : "json",
				success : function(resp, status, jqXHR) {
					console.log("사유", resp.refundWhy);
					let refundWhyNm = resp.refundWhy
					swal("취소된 상품", "사유 : " + refundWhyNm);
				},
				error : function(jqXHR, status, error) {
					console.log(jqXHR);
					console.log(status);
					console.log(error);
				}
			});

		} else if (state == '수락대기') {
			swal("수락대기상품", "전문가 승낙 후 이용이 가능합니다.");
		} else {
			location.href = "${cPath}/myexpert/" + myEprod;
		}
	});
</script>
