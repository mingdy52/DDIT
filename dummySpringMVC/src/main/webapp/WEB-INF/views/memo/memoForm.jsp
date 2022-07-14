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
	<form:form modelAttribute="memo" method="post" enctype="multipart/form-data">
		<ul>	
			<li>
				<form:input path="writer" placeholder="작성자" />
				<form:errors path="writer" element="span" cssClass="error"/>
				<!-- 		메모라는 객체가 가진 writer 에 대한 검증 결과 -->
			</li>
			<li>
				<form:textarea path="content" placeholder="메모" />
				<form:errors path="content" element="span" cssClass="error"/>
			</li>
			<li>
				<form:input path="profileImage" type="file" placeholder="이미지" />
				<form:errors path="profileImage" element="span" cssClass="error"/>
			</li>
			<li>
				<form:button type="submit" >전송</form:button>
				<form:button type="reset" >취소</form:button>
			</li>
		</ul>
	</form:form>
</body>
</html>