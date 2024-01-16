<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 8. 22.      서효림      최초작성
* 2022. 9. 02.      심민경      수정
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<sec:authorize access="isAuthenticated()">
   <sec:authentication property="principal.realMember" var="member" />
</sec:authorize>
<style>
table{
	text-align:center;
}

.linkBtn{
	margin-bottom:20px;
}
</style>

<div style="margin-bottom: 30px;">
	<div id="searchFreeBoard" class="d-flex justify-content-center form-inline">
		<form:select path="simpleCondition.searchType" id="searchType"
			class="mr-2">
			<form:option value="" label="전체" />
			<form:option value="title" label="제목" />
			<form:option value="writer" label="작성자" />
			<form:option value="content" label="내용" />
		</form:select>
		<form:input path="simpleCondition.searchWord" id="searchWord"
			class="mr-2" autocomplete="off"/>
		<button id="searchBtn" style="margin-left: 2px;" class="btn btn-primary waves-effect waves-light"><i class="ri-search-line"></i></button>
	</div>

</div>

<select class="form-select selBox" id="floatingSelect">
	<option value="ALL">전체</option>
	<option value="N"  ${answerYN eq 'N' ? 'selected="selected"' : '' }>답변대기</option>
	<option value="Y"  ${answerYN eq 'Y' ? 'selected="selected"' : '' }>답변완료</option>
</select>

<div class="col-sm-12">
	<table id="datatable" class="table table-hover mb-0" style=" color: black; text-align: center;">
		<thead class="table-light" style="color: black;">
			<tr role="row">
					<th style="width: 60px;" >NO</th>
					<th>제목</th>
					<th style="width: 100px;">작성자</th>
					<th  style="width: 100px;">조회수</th>
					<th  style="width: 100px;">날짜</th>
					<th  style="width: 100px;">답변상태</th>
				</tr>
			</thead>
			
		<tbody>
			<c:if test="${not empty pagingVO.dataList}">
				<c:forEach items="${pagingVO.dataList}" var="qnaList" varStatus="status">
				<tr>
					<td>${qnaList.rnum}</td>
					<td align="left">
					<c:if test="${qnaList.qnaPublicYn  == 'N' && member.roleList[0].roleCode != 'ROLE_ADMIN'}">
						<a href="#" onclick="f_check($(this))" data-qnanum="${qnaList.qnaNum}" data-bs-toggle="modal" data-bs-target="#modal">
							<i class="ri-rotate-lock-line"></i>
							<c:out value="${qnaList.qnaTitle}"/> 
						</a>
					</c:if>
					<c:if test="${qnaList.qnaPublicYn  == 'Y' || member.roleList[0].roleCode == 'ROLE_ADMIN'}">
						<a href="${cPath }/qna/${qnaList.qnaNum}">
							<c:out value="${qnaList.qnaTitle}"/> 
						</a>
					</c:if>
					</td>
					<td><c:out value="${qnaList.writerId}"/></td>
					<td>${qnaList.viewNum}</td>
					<td>${qnaList.qnaDate}</td>
					<td class="nameColor">${qnaList.answerYn}</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>

		
	</table>
	
	<div style="padding: 10px;">
		<c:if test="${member.memId != null }">
			<p align="right" >
				<input type="button" value="글쓰기" class="btn btn-secondary linkBtn" onclick="location.href='${cPath}/qna/form'"/>  
			</p>
		</c:if>
	</div>
	
	<div class="d-flex justify-content-center pagingArea">
					${pagingVO.pagingHTMLBS }
	</div>
</div>

<!-- 비밀글 비밀번호 체크 모달 -->
<div class="modal fade show" id="modal" tabindex="-1"
	style="display: none;" aria-modal="true" role="dialog">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">비밀글</h5>
			</div>
			<form method="post" class="form-inline" action="${cPath }/qna/check">
				<sec:csrfInput/>
				<input id="qnaNum" name="qnaNum" type="hidden"/>
				<div class="modal-body">
					<input class="form-control" id="modipass" type="password" maxlength="20" name="qnaPass" placeholder="비밀번호를 입력하세요."/>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary waves-effect waves-light">확인</button>
					<button type="button" class="btn btn-light waves-effect close" data-bs-dismiss="modal">닫기</button>
				</div>
	  	    </form>	
		</div>
	</div>
</div>

<input type="hidden" value="${answerYN}" id="answer">

<form id="searchForm" action="${cPath }/qna" >
	<input type="hidden" name="searchType" />
	<input type="hidden" name="searchWord" />
	<input type="hidden" name="answerYN" id="answerYN"/>
	<input type="hidden" name="page" >
</form>

<script>
let searchForm = $("#searchForm")
$(".pagingArea").on("click", "a", function(event){
		let page = $(this).data("page");
		searchForm.find("[name=page]").val(page);
		searchForm.find("[name=searchType]").val($("#searchType").val())
		searchForm.find("[name=searchWord]").val($("#searchWord").val())
		answerYN.value=answer.value;
		searchForm.submit();
});

let searchBtn = $("#searchBtn").on("click", function(event){
	searchForm.find("[name=searchType]").val($("#searchType").val())
	searchForm.find("[name=searchWord]").val($("#searchWord").val())
	searchForm.submit();
});

$(".selBox").on("change", function(event){
	answerYN.value=floatingSelect.value;
	searchForm.submit();
});

function f_check(el){
	let aQnaNum = el.data("qnanum");
	qnaNum.value = aQnaNum;
}

var datatable = document.getElementById('datatable'); // 테이블아이디 가져오기
var conN = datatable.getElementsByTagName("tr"); // 태그네임 <tr>들 가져오기
var lengN = conN.length; // <tr>들의 갯수 (반복문 용도 변수)

for(var t=0; t<lengN; t++) {
   var contents = document.getElementsByClassName('nameColor')[t];
   var changeClr = document.getElementsByClassName('nameColor')[t].innerHTML.trim(); // nameColor값의 내용들. trim()은 공백이 생겨서 잘라버림

   if (changeClr.includes('답변완료')) {
      contents.style.color="gray";
   } else if (changeClr.includes('답변대기')) {
      contents.style.color="red";
   } 
}

</script>

