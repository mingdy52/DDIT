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
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<style>
#rate-box fieldset {
	display: inline-block; /* 하위 별점 이미지들이 있는 영역만 자리를 차지함.*/
	border: 0; /* 필드셋 테두리 제거 */
}

#rate-box input[type=radio] {
	display: none; /* 라디오박스 감춤 */
}

#rate-box fieldset legend {
	text-align: left;
}

#accordionFlushExample {
	border: 1px solid;
}

.accordion-button {
	background-color: #f0f1fe;
	height: 30px;
	color: black;
}

.accordion {
	width: 70%;
}

#title {
	width: 50%;
}

.reviewBtn{
	background-color: black;
	color: white;
	padding: 5px;
	border-radius: 4px;
}

.reviewBtn:hover{
	color: gray;
}
</style>

<div class="card-body">

	<!-- Nav tabs -->
	<ul class="nav nav-tabs nav-tabs-custom nav-justified" role="tablist">
		<li class="nav-item"><a class="nav-link active"
			data-bs-toggle="tab" href="#review" role="tab" aria-selected="true">
				<span class="d-block d-sm-none"><i class="fas fa-home"></i></span> <span
				class="d-none d-sm-block">작성가능한 리뷰</span>
		</a></li>
		<li class="nav-item"><a class="nav-link" data-bs-toggle="tab"
			href="#myreview" role="tab" aria-selected="false"> <span
				class="d-block d-sm-none"><i class="far fa-user"></i></span> <span
				class="d-none d-sm-block">내 리 뷰</span>
		</a></li>
	</ul>

	<!-- Tab panes -->
	<div class="tab-content p-3 text-muted">
		<div class="tab-pane active" id="review" role="tabpanel">
			<div>
				<c:set value="${reviewList }" var="reviewList" />
				<table class="table mb-0" style="text-align: center; color: black;">
					<tbody>
						<c:if test="${not empty reviewList }">
							<c:forEach items="${reviewList }" var="review" varStatus="status">
								<tr>
									<td>${status.count }</td>
									<td><a href="${cPath}/myexpert/${review.myEprod }">${review.myEprod }</a></td>
									<td>${review.eprodName }</td>
									<td style="width: 120px;"><a href="${cPath}/myexpert/review/${review.myEprod }" class="reviewBtn">후기작성</a></td>
								</tr>

							</c:forEach>
						</c:if>
						<c:if test="${empty reviewList }">
							<tr style="text-align: center; color: gray;" ><td colspan="4">작성가능한 리뷰가 없습니다.</td></tr>
						</c:if>
					</tbody>

				</table>
			</div>
		</div>
		<div class="tab-pane" id="myreview" role="tabpanel">
			<div >
				<c:set value="${myreview }" var="myreview" />
				<c:if test="${not empty myreview}">
					<c:forEach items="${myreview }" var="my" varStatus="status">
						<div class="card">
							<div class="card-header">
								<%-- 						<div id="title">${my.myEprod }후기</div> --%>
								<div id="rate-box">
									<h5 style="width: 30%; float: left;">${my.myEprod }</h5>
									<fieldset style="float: right">
										<c:forEach begin="1" end="${my.reviewStar }">
											<input type="radio" name="rating" value="1" id="rate5">
											<label for="rate5">⭐</label>
										</c:forEach>
									</fieldset>
								</div>
							</div>
							<div class="card-body">
								<table class="table table-borderless mb-0" >
									<tr style="border-bottom: 1px solid black;">
										<td style="color: black;">${my.reviewTitle }</td>
										<td style="text-align: right;"><a
											class="btn btn-primary btn-sm waves-effect waves-light"
											href="${cPath }/myexpert/myreview/${my.reviewNum}">수정</a>
											<a class="btn btn-secondary btn-sm waves-effect waves-light delBtn"  data-review="${my.reviewNum }">삭제</a></td>
									</tr>
									<tr>
										<td colspan="2">${my.reviewContent }</td>
									</tr>
									<%-- 								${my.reviewWriter }  --%>
									<tr>
										<td id="star" style="display: none">${my.reviewStar }</td>
									</tr>
									<tr style="text-align: right;">
										<td colspan="2">${my.reviewDate }</td>
									</tr>
								</table>
							</div>
						</div>
					</c:forEach>
				</c:if>
				<c:if test="${empty myreview}">
				<table  class="table mb-0">				
					<tr>
						<td style="text-align: center; color: gray;">작성하신 리뷰가 존재하지 않습니다.</td>
					</tr>
				</table>
				</c:if>
			</div>
		</div>


	</div>
</div>
<form id="delform" method="post" action="${cPath }/myexpert/review/delete">
	<sec:csrfInput />
	<input type="hidden" name="reviewNum">
</form>
<script>
// 	$("button").attr("class", "accordion-button");
// 	$("button").attr("aria-expanded", "true");
	$("#flush-collapse1").attr("class", "accordion-collapse collapse show");
	$(".delBtn").on("click", function(){
		
		let delConm = confirm("삭제하시겠습니까? 삭제하시면 이 상품의 리뷰는 재작성이 불가합니다.")
		let reviewNo = $(this).data("review");
		console.log(reviewNo);
		
		if(delConm){
			$("input[name='reviewNum']").attr("value", reviewNo);
			$("#delform").submit();
		}else{
			
		}
	});
</script>