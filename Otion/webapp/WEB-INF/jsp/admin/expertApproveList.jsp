<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 8. 5.   심민경      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>	

<style>
	#selbox{
		float: left;
		width: 23%;
		height: 30px;
	}
	
	#srch{
		float: rigth;
		width: 70%;
		margin-left: 10px;
		height: 30px;
	}
	
	#searchUI{
		margin-bottom: 10px;
	}
	

	table {
		text-align:center;
	}

</style>
<div id="searchUI" class="d-flex justify-center-center" >
	<div class="form-floating" id="selbox">
		<select class="form-select" id="floatingSelect" aria-label="Floating label select example" name="exApprCode" 
						style="height: 42px; padding-top: 10px; float: none; ">
			<option value="A01"  ${apprCode == 'A01' ? 'selected="selected"' : '' }>접수</option>
			<option value="A02"  ${apprCode == 'A02' ? 'selected="selected"' : '' }>검토중</option>
			<option value="A03"  ${apprCode == 'A03' ? 'selected="selected"' : '' }>승인완료</option>
			<option value="A04"  ${apprCode == 'A04' ? 'selected="selected"' : '' }>승인거절</option>
		</select>
<!-- 		<label for="floatingSelect">승인여부</label> -->
	</div>
	
	<div class="input-group mb-3" id="srch">
		<input type="text" class="form-control" placeholder="검색어를 입력하세요."
			aria-label="Recipient's username" aria-describedby="basic-addon2" name="searchWord" 
			onkeyup="if(window.event.keyCode==13){f_search()}" autocomplete="off">
		<button onClick="f_search()" class="btn btn-primary btn-rounded waves-effect waves-light">검색</button>
	</div>
</div>
<table class="table table-hover mb-0">
	<thead>
		<tr>
			<td>순번</td>
			<td>전문가신청번호</td>
			<td>신청자 아이디</td>
			<td>승인상태</td>
		</tr>
	</thead>
	<tbody>
	<c:set var="expFormList" value="${pagingVO.dataList }" />
	<c:if test="${not empty expFormList }">
	<c:forEach items="${expFormList }" var="expForm">
		<tr>
			<td>${expForm.rnum }</td>
			<c:url value="/admin/approve/${expForm.exFormNum }" var="expFormView"></c:url>
			<td><a href="${expFormView }">${expForm.exFormNum }</a></td>
			<td> ${expForm.applicantId }</td>
			<td>${expForm.comCodeNm }</td>
		</tr>
		
	</c:forEach>
	</c:if>
	<c:if test="${empty expFormList }">
		<tr>
			<td colspan="8">신청 회원이 없습니다.</td>
		</tr>
	</c:if>
	</tbody>
<%-- 	<c:if test="${expFormList.size != 0 }"> --%>
		<tfoot>
			<tr>
				<td colspan="8">
					<div class="d-flex justify-content-center pagingArea">
						${pagingVO.pagingHTMLBS }
					</div>
				</td>
			</tr>
		</tfoot>
<%-- 	</c:if> --%>
</table>

<form action="${cPath }/admin/approve" id="searchForm" method="get">
	<sec:csrfInput/>
	<input type="hidden" name="page" id="page" placeholder="page"/>	
	<input type="hidden" name="apprCode" id="apprCode" placeholder="승인여부" value="${apprCode}" />
	<input type="hidden" name="searchWord" placeholder="검색"/>
</form>

<script>
	$("[name='searchWord']").val("${pagingVO.simpleCondition.searchWord}");
</script>
<script src="${cPath }/resources/js/admin/expAppvList.js"></script>
