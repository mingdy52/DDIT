<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
</head>
<body>
	<table>
		<thead>
			<tr>
				<th>메모코드</th>
				<th>작성자</th>
				<th>프로필이미지(?)</th>
				<th>내용</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${not empty memoList }">
					<c:forEach items="${memoList }" var="memo">
						<tr class="memoTr" >
							<td>${memo.code}</td>
							<td>${memo.writer }</td>
<!-- 							data:[<media type>][;base64],<data> -->
							<td>
								<c:if test="${not empty memo.profileImg }">
									<img src="data:image/*;base64, ${memo.base64ProfileImage }">
								</c:if>
							</td>
							<td>${memo.content  }</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="4">작성된 메모 없음.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="4"><a href="/ddit/memo/write.do">작성</a></td>
			</tr>
		</tfoot>
	</table>
	<form id="updateForm" action="${pageContext.request.contextPath }/memo/memoUpdate.do" method="get">
		<input type="text" name="code">
	</form>
<script type="text/javascript">
	let updateForm = $("#updateForm");
	$(".memoTr").on("click", function(event){
		let code = $(this).find("td:first").text();
		updateForm.find("[name=code]").val(code);
		updateForm.submit();
	});
</script>
</body>
</html>