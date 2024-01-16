<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자         수정내용
* ----------  ---------  -----------------
* 2022. 8. 13.       심민경         최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<style>
	.next {
		margin-left: 5px;
	}

	table {
		text-align:center;
	}
</style>

<h3>신청정보</h3>
<div class="card-body">
	<div class="table-responsive">
		<table class="table table-hover mb-0">
			<tbody>
				<tr>
					<th>신청번호</th>
					<td>${expFormVO.exFormNum }</td>
					<th>신청자 아이디</th>
					<td>${expFormVO.applicantId }</td>
					<th>승인상태</th>
					<td>${expFormVO.comCodeNm }</td>
				</tr>
			</tbody>
		</table>
	</div>

</div>

<h5>이력서</h5>
<div class="card-body">
	<div class="tab-pane active" id="basic-info">
		<c:url value="/download/${attatchVO.attatchNum }" var="download"></c:url>
		<a href="${download }">${attatchVO.originNm }</a>
	</div>
</div>

<h5>자기소개</h5>
<div class="card-body">
	<div id="addproduct-nav-pills-wizard" class="twitter-bs-wizard">
		<div class="tab-content twitter-bs-wizard-tab-content">
			<div class="tab-pane active" id="basic-info">
				<p class="card-title-desc"><c:out value="${expFormVO.exFormContent }"/> </p>
			</div>
		</div>
	</div>
</div>

<c:if test="${expFormVO.exApprCode == 'A01' or expFormVO.exApprCode =='A02'}">
	<div class="card-body">
	    <form:form id="resultForm" method="post" modelAttribute="news">
	        <div>
	        	<a class="btn btn-primary"  onClick="f_approve()">승인</a>
	        	<a class="btn btn-primary" onClick="f_hold()">보류</a>
	        	<a class="btn btn-secondary"  data-bs-toggle="modal" data-bs-target="#exampleModal">거절</a>
	            <form:input type="hidden" id="result" path="result"/>
	            <form:input type="hidden" id="newsMsgCode" path="newsMsgCode"/>
	            <form:input type="hidden" path="receiverId" value="${expFormVO.applicantId }"/>
	            <input type="hidden" name="exFormNum" value="${expFormVO.exFormNum }"/>
	        </div>
	    </form:form>
	</div>
</c:if>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-scrollable modal-lg" >
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">승인 거절</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
      	<select id="refuseCode" name="newsMsgCode" class="form-select">
      		<option value="ER01">자격 미달입니다. 자격 조건을 다시 확인해주세요.</option>
      		<option value="ER02">서류가 누락되었습니다.</option>
      	</select>
      </div>
      <div class="modal-footer">
        <button class="btn btn-danger" onClick="f_refuse()">거절</button>
        <button class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
      </div>
    </div>
  </div>
</div>

<script>
const approve = "approve";
const refuse = "refuse";
const hold = "hold";

let refuseCode = document.querySelector("#refuseCode");
let apprCode = document.querySelector("#apprCode");
let newsMsgCode = document.querySelector("#newsMsgCode");
let result = document.querySelector("#result");
let resultForm = document.querySelector("#resultForm");

function f_approve(){
    if (confirm("전문가 승인을 승인하시겠습니까?")) {
		result.value = approve;
		newsMsgCode.value = "NEW01";
		resultForm.submit();
    }
}
function f_refuse(){
	result.value = refuse;
	newsMsgCode.value = refuseCode.value;
	resultForm.submit();
}
function f_hold(){
    if (confirm("전문가 승인을 보류하시겠습니까?")) {
		result.value = hold;
		newsMsgCode.value = "NEW02";
		resultForm.submit();
    }
}
</script>
