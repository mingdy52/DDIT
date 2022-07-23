<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<script src="${cPath }/resources/js/ckeditor/ckeditor.js"></script>

<form:form modelAttribute="board" method="post" action="${cPath }/board" enctype="multipart/form-data">
<table class="table table-bordered">
<tr>
			<th>제목</th>
			<td>
				<form:input path="boTitle" class="form-control" placeholder="제목" />
				<form:errors path="boTitle" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>
				<form:input path="boWriter" class="form-control" placeholder="작성자" />
				<form:errors path="boWriter" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td>
				<form:input path="boPass" class="form-control" placeholder="비밀번호" />
				<form:errors path="boPass" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>
				<form:input path="boMail" class="form-control" placeholder="이메일" />
				<form:errors path="boMail" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th>아이피</th>
			<td>
				<form:input path="boIp" class="form-control" placeholder="아이피" value="${pageContext.request.remoteAddr }"/>
				<form:errors path="boIp" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
				<form:input type="file" path="boFiles" multiple="true"/>
				<form:errors path="boFiles" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
				<form:textarea path="boContent" class="form-control" placeholder="내용" />
				<form:errors path="boContent" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="저장" class="btn btn-primary"/>
				<input type="reset" value="취소" class="btn btn-danger"/>
			</td>
		</tr>
	</table>
</form:form>
<script src="${cPath}/resources/board/boardForm.js"></script>
