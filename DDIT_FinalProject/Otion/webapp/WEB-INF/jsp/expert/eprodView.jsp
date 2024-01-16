<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 8. 9.      박채록      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%
	Date nowTime = new Date();
	SimpleDateFormat sf = new SimpleDateFormat("yyyy년 MM월 dd일 ");
%>

<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.8.js"></script>
<style>
.rate-box fieldset {
	display: inline-block; /* 하위 별점 이미지들이 있는 영역만 자리를 차지함.*/
	border: 0; /* 필드셋 테두리 제거 */
}

.rate-box input[type=radio] {
	display: none; /* 라디오박스 감춤 */
}

.rate-box fieldset legend {
	text-align: left;
}
#proddetail {
	width: 70%;
	float: left;
}

#priceArea {
	padding-top: 30px;
	padding-bottom: 30px;
	text-align: center;
}

#order {
	width: 25%;
	margin-left: 2%;
	background-color: white;
	float: left;
}

#prodName {
	background-color: #717CFF;
	padding: 30px;
	margin-bottom: 20px;
}

#profileImg {
   width: 100px;
   height: 100px;
   border-radius: 50%;
   background-color: tomato;
}

.tdname{
	min-width: 100px;
}
#prodContents {
	min-height: 500px;
}

.white {
	color: white
}

.modal-body {
	text-align: center;
}

.checkbox_group {
	text-align: left;
	margin-top: 50px;
}

#chk {
	margin-left: 20px;
}

.card-title{
	text-align: center;
	padding: 5px;
	font-size: 30px;
}

#heartBtn{
	width: 50px;
}

.tag{
	padding: 5px;
	background-color: #F2F2F2;
	color:#8181F7;
	margin-top: 8px;
	float: left;
	border-radius:3px;
	margin-right: 5px;
}
</style>

<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal.realMember" var="member" />
	<p id="memId" style="display: none">${member.getMemId() }</p>
	<%-- <sec:authentication property="principal" var="authMember"/> --%>
</sec:authorize>

<input id="WishYn" type="hidden" value="${WishYn}">
<div id="proddetail">
	<div id="prodName" class="card">
		<h3 class="white">${eprod.eprodName }</h3>
		<div class="white">
			<c:forEach items="${eprodTagArr }" var="tag">
				<a class="tag">${tag }</a>
			</c:forEach>
		</div>
		<sec:authorize access="isAuthenticated()">
		<div class="rating-star" id="heartBtn">
			<!-- 		*********찜기능************* -->
			<div class="rating-symbol"
				style="display: inline-block; position: relative;">
				<div
					class="rating-symbol-background mdi mdi-heart-outline text-danger"
					style="visibility: visible;"></div>
				<div class="rating-symbol-foreground"
					style="display: inline-block; position: absolute; overflow: hidden; left: 0px; right: 0px; width: 0px;">
					<span class="mdi mdi-heart text-danger"></span>
				</div>
			</div>
		</div>
		</sec:authorize>
	</div>
	<div id="prodContents">
		<h3>상품설명</h3>
		${eprod.eprodSummary}
	</div>

	<div>
		<h3>상품후기<span id="rvCnt"></span></h3>
		<table class="table mb-0" >
		<c:if test="${not empty rvList }">
		<c:forEach items="${rvList }" var="rv">
			<tr style="background-color: #eff3f7; color: black;">
				<td><div class="rate-box">
						<c:forEach begin="1" end="${rv.reviewStar }">
							<input type="radio" name="rating" value="1" id="rate5">
							<label for="rate5">⭐</label>
						</c:forEach>
					</div>
				</td>
				<td style="text-align: center; width: 180px;" >${rv.reviewWriter }님의 리뷰</td>
				<td style="text-align: center;" >${rv.reviewTitle }</td>
				<td style="text-align: center;">${rv.reviewDate }</td>
			</tr>
			<tr style="text-align: center;">
				<td colspan="4" style="padding: 24px;">${rv.reviewContent }</td>
			</tr>
		</c:forEach>
		</c:if>
		<c:if test="${empty rvList }">
			<tr>
				<td>이 상품의 첫번째 리뷰를 작성해보세요.</td>
			</tr>
		</c:if>
		</table>
	</div>
</div>

<div id="order">
	<div class="card">
		<div class="card-body">
			<div id="priceArea">
				<h4>${eprod.eprodPrice }원</h4>


				<a>expert 이용안내</a><br> <br>
				<button type="button" style="background-color: navy;" id="purchaseBtn"
					class="btn btn-secondary btn-rounded waves-effect waves-light">구매하기</button>
			</div>
		</div>
	</div>
	<div class="card">
		<div class="card-body">
			<div id="#profileImg">
			  <a href="${cPath }/expert/${eprod.expertId}">
				<img src="${cPath }/resources/profileImages/${eprod.profileImg}"
					class="card-img-top">
			  </a>
				<h5 class="card-title">${eprod.expertId }</h5>
			</div>
		</div>
	</div>

</div>

<div>
	<form:form id="buyForm" method="post" modelAttribute="myExpert">
		<input name="buyerId" class="form-control" type="hidden"
			value="${member.memId }" />
		<input name="eprodNum" class="form-control" type="hidden"
			value="${eprod.eprodNum }" />
		<input name="eprodName" class="form-control" type="hidden"
			value="${eprod.eprodName }" />
		<input name="eprodSummary" class="form-control" type="hidden"
			value="${eprod.eprodSummary }" />
		<input name="eprodPrice" class="form-control" type="hidden"
			value="${eprod.eprodPrice }" />
	</form:form>
</div>
<div id="buyModal" class="modal fade" tabindex="-1"
	aria-labelledby="myModalLabel" style="display: none;"
	aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="display-6 mb-0" id="myModalLabel"></h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<h3>――――구매상품정보――――</h3>
				<div>
					<table class="table table-borderless mb-0">
						<tr>
							<td class="tdname">서비스명</td>
							<td>EXPERT</td>
						</tr>
						<tr>
							<td class="tdname">판매자</td>
							<td>${eprod.expertId }</td>
						</tr>
						<tr>
							<td class="tdname">구매자</td>
							<c:if test="${not empty member }">
							<td>
								${member.getMemId()}</td>
							</c:if>
						</tr>
						<tr>
							<td class="tdname">구매상품</td>
							<td>${eprod.eprodName }</td>
						</tr>
						<tr>
							<td class="tdname">구매일자</td>
							<td><%=sf.format(nowTime)%></td>
						</tr>
					</table>
					<h3>――――――――――――――</h3>
					<h4>결제금액 총 ${eprod.eprodPrice }원</h4>
					<h3>――――――――――――――</h3>
				</div>
				<div class="checkbox_group">
					<input type="checkbox" id="check_all"> <label
						for="check_all"><h5>아래 내용에 전체 동의합니다.</h5></label><br>
					<div id="chk">
						<input type="checkbox" id="check_1" class="normal"> <label
							for="check_1">eXpert 서비스 이용 약관 동의</label> <br> <input
							type="checkbox" id="check_2" class="normal"> <label
							for="check_2">상품 구매 조건 확인 및 구매 동의</label> <br> <input
							type="checkbox" id="check_3" class="normal"> <label
							for="check_3">하단 유의사항의 확인 및 동의</label> <br>
					</div>
				</div>
				<div>
					<hr>
					<h6>
						<p style=" font-weight: bold;">✱✱유의사항</p>
						<ul style="list-style-type: '-'; text-align: left;">
							<li>해당 상품은 상담 과정에서 eXpert에게 개인정보 제공이 필요할 수 있습니다.</li>
							<li>사업자가 아닌 개인 eXpert 상품의 경우 현금영수증 발급이 불가합니다.</li>
							<li>청약철회 등 환불 기준은 상품 상세 페이지의 “청약철회 등 환불 안내”를 참조해 주시기 바랍니다.</li>
						</ul>
					</h6>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-light waves-effect"
					data-bs-dismiss="modal">취소</button>
				<button type="button" id="goBuyBtn"
					class="btn btn-primary waves-effect waves-light">결제하기</button>
			</div>
		</div>
	</div>
</div>


<form id="likeForm">
	<sec:csrfInput />
	<input type="hidden" name="eprodNum" value="${eprod.eprodNum}">
</form>

<script>

let eprodPrice =$("input[name='eprodPrice']").val().replace(",","");
console.log("eprodPrice",eprodPrice);
$("input[name='eprodPrice']").attr("value", eprodPrice);
	//체크박스 전체 선택
	$(".checkbox_group").on(
			"click",
			"#check_all",
			function() {
				$(this).parents(".checkbox_group").find('input').prop(
						"checked", $(this).is(":checked"));
			});

	// 체크박스 개별 선택
	$(".checkbox_group").on("click", ".normal", function() {
		var is_checked = true;

		$(".checkbox_group .normal").each(function() {
			is_checked = is_checked && $(this).is(":checked");
		});

		$("#check_all").prop("checked", is_checked);
	});

	let memId= $("#memId").text();
	//let memId= $("#memId").val();
	 console.log("memId", memId);
	$("#purchaseBtn").on("click", function(){
		if(memId==""){
			alert("로그인 후 이용가능")
			location.href="${cPath}/login";
		}else{
			$("#buyModal").modal("show");
		}
	});
	
	$("#goBuyBtn").on("click", function() {
		var okchk = $("input[type='checkbox']");
		if (okchk.length == okchk.filter(":checked").length) {

				   IMP.init("imp68429276"); // 예: imp00000000
		              IMP.request_pay({ // param     
		                   pg: "inicis",
		                   pay_method: "card",
		                   merchant_uid: "Otion"+ new Date().getTime(),
		                   name: "Expert",
		                   amount: 100,
		                   buyer_email: "zzmm6992@daum.net",
		                   buyer_name: "아인",
		                   buyer_tel: ""
		                      
		               }, function (rsp) { // callback
		                   if (rsp.success) {
		                      console.log('빌링키 발급 성공',rsp)
		                      swal({
									  title: "결제가 완료 되었습니다.",
									  icon: "success",
									});
			        			let eprodNum = $("input[name='eprodNum']").val();
			        			$("#buyForm").attr("action", "${cPath}/expert/prod/" + eprodNum)
			        			console.log("${cPath}/expert/prod/" + eprodNum);
			        			$("#buyForm").submit();
			        			$("#buyModal").modal.hide();
		                } else {
		                    var msg = '결제에 실패하였습니다.';
		                    msg += '에러내용 : ' + rsp.error_msg;
		                    alert(msg);
		                    return false;
		                }
		        			
		            });

		} else {
			alert('모든 항목을 동의 한 후 구매가 가능합니다.');
		}
		
		
		
	});
	

	
	



	let WishYn = $("#WishYn").val();
	console.log(WishYn);
	if (WishYn == "true") {
		$(".rating-symbol-foreground").css("overflow", "visible");
	}
	
	
	$("#heartBtn").on("click", function() {
		let heartCol = $("div.rating-symbol-foreground").css("overflow");
		console.log("heartCol : "+heartCol);
		if (heartCol == "hidden") {
			$.ajax({
				url : "${cPath}/saveWish",
				method : "post",
				dataType : "json",
				data : $('#likeForm').serialize(),
				success : function(resp, status, jqXHR) {
					console.log(resp)
					$("#WishYn").attr("value", "true");
					$(".rating-symbol-foreground").css("overflow", "visible");
				},
				error : function(jqXHR, status, error) {
					alert("실패");
					console.log(jqXHR);
					console.log(status);
					console.log(error);
					$(".rating-symbol-foreground").css("overflow", "hidden");
				}
	
			});
			
		}else if(heartCol == "visible"){
			
			$.ajax({
				url : "${cPath}/deleteWish",
				method : "post",
				dataType : "json",
				data : $('#likeForm').serialize(),
				success : function(resp, status, jqXHR) {
					console.log(resp);
					$(".rating-symbol-foreground").css("overflow", "hidden");
				},
				error : function(jqXHR, status, error) {
					alert("실패");
					console.log(jqXHR);
					console.log(status);
					console.log(error);
					$(".rating-symbol-foreground").css("overflow", "hidden");
				}
			});	
		}
	});
	
	let rateCnt = $(".rate-box").length;
	$("#rvCnt").append("("+rateCnt+")");
</script>

