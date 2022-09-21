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
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<style>
.card {
	border: none;
	box-shadow: none;
}

#circle {
	width: 280px;
	height: 280px;
	float: right;
}

.assli {
	list-style-type: '✔ ';
	text-align: left;
	padding: 2px;
	color: black;
	padding-left: 20px;
}

#profile-box {
	background-color: #4F4FE2;
	width: 100%;
	height: 280px;
	box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px 0px;
	border-radius: 5px;
}

.tag-box {
	text-align: center;
	width: 60%;
	/* 	float: left; */
	/* 	height: 50%;  */
	/* 	vertical-align: middle; */
	/* 	padding: 20px; */
	display: inline-flex;
	justify-content: center;
	align-items: center;
}

.tagOne {
	background-color: #E6E0F8;
	color: black;
	font-size: 18px;
	padding: 3px;
/* 	font-weight: ; */
/* 	border-radius: 3px; */
}

.tagOne:hover {
	background-color: white;
	
	font-size: 18px;
	padding: 3px;
/* 	font-weight: bold; */
	border-radius: 3px;
}

.myList {
	width: 48%;
	float: left;
	min-height: 280px;
	padding: 10px;
}

.myEprod {
	width: 100%;
	float: left;
	margin-top: 10px;
	margin-left: 10px;
}

.eprodNm {
	height: 80px;
}

.eprod-box {
	width: 30%;
	margin: 10px;
	float: left;
	box-shadow: rgba(0, 0, 0, 0.16) 0px 1px 4px;
	padding: 10px;
	height: 200px;
}

.tag {
	float: right;
	background-color: #F2F2F2;
	color: #8181F7;
	margin: 3px;
/* 	border: 1px solid gray; */
	padding: 1px;
}

#reportBtn{
	position: absolute;
	right: 165px;
	top: 226px;
	font-size: 40px;
	padding: 10px;
}

#select-box{
	padding: 10px;
	width: 80%;
	background-color: #F2EFFB;
}
</style>

<div class="card-body">
	<div id="profile-box">
		<div style="float: right; width: 30%; ">
			<img src="${cPath }/resources/profileImages/${expert.profileImg}"
				id="circle" />
		</div>
		<div id="reportBtn"><a style="background-color: white; border: 1px solid #C3BCC4;">🚨</a></div>
		<div
			style="margin-bottom: 0; width: 60%; text-align: center; padding-top: 70px;">
			<c:set var="memList" value="${expert.memList }" />
			<c:if test="${not empty memList }">
				<c:forEach items="${memList }" var="mem">
					<p style="color: white; font-size: 50px;">${mem.memNick }님</p>
				</c:forEach>
			</c:if>
		</div>
		<div class="tag-box">
			<div class="d-flex flex-wrap gap-2 justify-content-center" >
				<%-- 				<h4 ">${expert.exTag }</h4> --%>
				<c:forEach items="${TagArr }" var="tag">
					<div class="tagOne">${tag }</div>
				</c:forEach>
			</div>
		</div>
	</div>

	<div class="card"
		style="text-align: center; padding: 30px; width: 70%; margin: auto;">
		<h4 style="font-weight: bold; padding: 10px;">❝소개말❞</h4>
		<h5 style="color: black;">${expert.presentation } </h5>
	</div>

	<div>
		<div class="myList">
			<h4 style="font-weight: 500; padding:3px; border-bottom: 2px solid black">이력&경력</h4>
			<c:set value="${expert.assumeArr}" var="assumeArr" />
			<c:if test="${not empty assumeArr }">
				<c:forEach items="${assumeArr }" var="arr">
					<li class="assli">${arr}</li>
				</c:forEach>
			</c:if>
		</div>
		<div class="myList" style="float: right;">
			<h4 style="border-bottom: 2px solid black; padding:3px; font-weight: 500; color: black; ">연관링크</h4>
			<a href="${expert.exLink }" style="color: black; margin-left: 5px;">${expert.exLink }</a>
		</div>
	</div>
	<div class="myEprod">
		<h3 style=" font-weight: 500;" id="eprodtitle">${expert.expertId }님의 상품</h3>
				<c:set var="eprodList" value="${expert.eprodList }" />
				<c:if test="${not empty eprodList[0].eprodName }">
					<c:forEach items="${eprodList }" var="eprod" varStatus="status">
						<div class="eprod-box">
							<div class="card">
							<div class="eprodNm">
								<h4><a href="${cPath}/expert/prod/${eprod.eprodNum}">${eprod.eprodName }</a></h4>
							</div>
							<h5 style="text-align: right;">${eprod.eprodPrice}원</h5>
							<p style="text-align: right;">${eprod.eprodDate }</p>
						<div class="">
							<c:forEach items="${eprod.eprodTagArr }" end="2" var="tag">
								<span class="tag"> ${tag }</span>
							</c:forEach>
						</div>
					</div>		
						</div>
					</c:forEach>
				</c:if>
				<c:if test="${empty eprodList[0].eprodName }">
					<tr>
						<td colspan="3"><h5>등록된 상품이 존재하지 않습니다.</h5></td>
					</tr>
				</c:if>
	</div>
</div>

<div id="reportModal" class="modal fade" tabindex="-1"
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
				<div style="text-align: center;">
				<h3 style="font-weight: bold;">전문가신고</h3>
				<p>신고사유를 선택해주세요.</p>
					<select id="select-box">
						<c:forEach items="${reportList }" var="report">
							<option>${report.comCodeNm}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-light waves-effect"
					data-bs-dismiss="modal">취소</button>
				<button type="button" id="goReport"
					class="btn btn-primary waves-effect waves-light">신고</button>
			</div>
		</div>
	</div>
	<form method="post" action="${cPath }/expert/report" id="reportForm">
		<sec:csrfInput/>
		<input type="hidden" name="targetId" value="${expert.expertId }"/>
		<input type="hidden" name="repContent"/>
	</form>
</div>



<script>
let eprodleng = $(".eprod-box").length;
$("#eprodtitle").append("("+eprodleng+")")

$("#reportBtn").on("click", function(){
	$("#reportModal").modal('show');
	$("#goReport").on("click",function(){
		let checkedReason =$("#select-box option:selected").text();
		console.log(checkedReason);
		$("input[name='repContent']").attr("value", checkedReason);
		$("#reportForm").submit();
	});
});
</script>


