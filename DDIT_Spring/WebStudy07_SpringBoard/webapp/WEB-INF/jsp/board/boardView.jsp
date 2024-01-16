<%@page import="kr.or.ddit.board.vo.BoardVO"%>
<%@page import="kr.or.ddit.common.vo.PagingVO"%>
<%@page import="org.springframework.ui.Model"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h4>상세 페이지</h4>
<table class="table table-bordered">
	<tr>
		<th>글번호</th>
		<td>
			${boardVO.boNo }
		</td>
	</tr>
	<tr>
		<th>제목</th>
		<td>
			${boardVO.boTitle }
		</td>
	</tr>
	<tr>
		<th>작성일</th>
		<td>${boardVO.boDate }</td>
	</tr>
	<tr>
		<th>작성자</th>
		<td>
			${boardVO.boWriter }
		</td>
	</tr>
	<tr>
		<th>아이피</th>
		<td>${boardVO.boIp }</td>
	</tr>
	<tr>
		<th>이메일</th>
		<td>${boardVO.boMail }</td>
	</tr>
	<tr>
		<th>첨부파일</th>
		<td>
			<c:if test="${not empty boardVO.attatchList }">
				<c:forEach items="${boardVO.attatchList }" var="attatches">
					${attatches.attFilename } | 
				</c:forEach>
			</c:if>
			<c:if test="${empty boardVO.attatchList }">
				첨부파일 없음.
			</c:if>
		</td>
	</tr>
	<tr>
	<th>내용</th>
		<td>
			${boardVO.boContent }
		</td>
	</tr>
		<tr>
		<th>신고수</th>
		<td>${boardVO.boRep }</td>
	</tr>
	<tr>
		<th>조회수</th>
		<td>${boardVO.boHit }</td>
	</tr>
	<tr>
		<th>추천수</th>
		<td>${boardVO.boRec }</td>
	</tr>
	<tr>
		<th>부모글</th>
		<td>${boardVO.boParent }</td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="button" value="수정" class="btn btn-primary linkBtn" data-href="${cPath }/board/${boardVO.boNo }/form" />
			<input type="button" value="삭제" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal"/>
		</td>
	</tr>
</table>


<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-scrollable modal-lg" >
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">게시글 삭제</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
		<form action="${cPath }/board/${boardVO.boNo }" method="post" id="delForm">
			<input type="hidden" name="_method" value="delete" />
			<input type="password" name="boPass" class="form-control" placeholder="비밀번호를 입력하세요."/>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-danger" id="delBtn">삭제하기</button>
      </div>
    </div>
  </div>
</div>

<script>
let exampleModal = $('#exampleModal').on('hidden.bs.modal', function(event){
	console.log(event);
	$(event.target).find("input[name=boPass]").empty();
});

$("#delBtn").on("click", function(){
	$("#delForm").submit();
});

</script>






