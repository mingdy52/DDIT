<%@page import="kr.or.ddit.board.vo.BoardVO"%>
<%@page import="kr.or.ddit.board.vo.PagingVO"%>
<%@page import="org.springframework.ui.Model"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>여기 보드뷰</h4>
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>순번</th>
				<th>글번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
			</tr>
		</thead>
		<tbody id="listBody">
		</tbody>
		<tfoot>
			<tr>
				<td colspan="8">
					<div class="pagingArea">
					
					</div>
					<div id="searchUI" class="d-flex justify-content-center form-inline">
<!-- 						<select path="searchType" class="mr-2"> -->
<%-- 							<option value="" label="${searchAll }" /> --%>
<%-- 							<option value="title" label="${titleMsg }" /> --%>
<%-- 							<option value="writer" label="${writerMsg }" /> --%>
<%-- 							<option value="content" label="${contentMsg }" /> --%>
<!-- 						<select> -->
<!-- 						<input path="searchWord" class="mr-2"/> -->
<!-- 						<button id="searchBtn" class="btn btn-primary">검색</button> -->
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="4">
<!-- 					<button id="insertBtn" class="btn btn-primary">글쓰기</button> -->
<%-- 					<a href='${cPath}/boardInsert' class="btn btn-primary">글쓰기</a> --%>
						<input type="button" value="새글쓰기" class="linkBtn" data-href="${cPath}/board/form"/>
				</td>
			</tr>
		</tfoot>
	</table>
<form id="searchForm">
<!-- 	<input type="text" name="searchType" /> -->
<!-- 	<input type="text" name="searchWord" /> -->
	<input type="text" path="page" />
</form>
</body>
<script>

	$('#insertBtn').on('click', function(event){
		location.href='${pageContext.request.contextPath}/boardInsert';
	});

	let listBody = $('#listBody');
	let searchForm = $('#searchForm');
	let makeBoardTr = function(board){
		return $("<tr>").append(
					$("<td>").html(board.rnum)
					, $("<td>").html(board.boNo)
					, $("<td>").html(
							$("<a>").prop("href", $.CPATH+"/board/"+board.boNo).addClass("linkBtn").text(board.boTitle)
							)
					, $("<td>").html(board.boWriter)
					, $("<td>").html(board.boDate)
				)
				
							board.boTitle
	}
	
	$.ajax({
		url : "${pageContext.request.contextPath}/board",
		method : "get",
		dataType : "json",
		success : function(resp, status, jqXHR) {
			let trTags = [];
			let board = resp.dataList;
			console.log(board)
			if(board && board.length > 0){
				$.each(board, function(index, board){
					let trTag = makeBoardTr(board);
					trTags.push(trTag);
				});
			} else {
				trTags.push($("<tr>").html(
					$("<td>").attr("colspan", "4")
							 . html("작성된 게시글 없음.")
					)
				);
			}
			
			listBody.empty();
			listBody.append(trTags);
			$(".pagingArea").html(resp.pagingHTMLBS)
		},
		error : function(jqXHR, status, error) {
			console.log(jqXHR);
			console.log(status);
			console.log(error);

		}
	});
	
	$(".pagingArea").on("click", "a", function(event){
		let page = $(this).data("page");
		searchForm.find("[name=page]").val(page);
		searchForm.submit();
	});
</script>

</html>