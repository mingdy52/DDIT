<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<pre>
전송 이후 서버사이드 처리
1. uploader 파라미터는 로깅 프레임워크를 이용하여 콘솔에 출력함.
2. fileSystemResource 이름의 파일 데이터는 d:/contents 폴더에 저장하고, 저장 경로를 확보
3. webResource 이름의 파일 데이터는 /resources/images 폴더에 저장하고, 저장 경로를 확보.
모든 요청 처리가 완료되면, /fileUploadForm 으로 다시 redirect 하되,
처리하는 과정에서 확보한 데이터들을 폼 하단에 출력함.

추가 미션
업로드 처리 후 현재 폼 페이지에 출력되는 데이터(저장경로)를 클릭한 경우,
1. fileSystemResource 는 다운로드 처리
2. webResource 는 브라우저에 해당 파일이 바로 출력되도록 처리함.
단, webResource 는 반드시 이미지 파일만 업로드 할 수 있도록 검증 구조를 적용 할 것.

	<form action="${cPath }/file/upload_3.do" method="post" enctype="multipart/form-data" id="fileForm">
		<input type="text" name="uploader" placeholder="업로더의 이름" />
		<input type="file" name="fileSystemResource" />
		<input type="file" name="webResource" accept="image/*" />
<!-- 		 accept="image/*"  :  이미지 파일만 받겠다. 근데 이렇게 하면 클라이언트가 브라우저에서 소스 고쳐서 이미지 말고도 넘길 수 있음.그래서 클라이언트 사이드에서만 검증하며 안돼 -> 서블릿가자 -->
		<input type="submit" value="업로드" />
	</form>
<div id="printArea">
	<c:forEach items="${savePathList }" var="savePath">
		<c:if test="${fn:startsWith(savePath, 'd:') }" >
			<c:url value="/file/download.do" var="downloadURL">
				<c:param name="file" value="${savePath }"></c:param>
			</c:url>
			<a href="${downloadURL }"> ${savePath } 다운로드 </a><br/>
		</c:if>
		
		<c:if test="${not fn:startsWith(savePath, 'd:') }" >
			<a href="${cPath }${savePath }"> ${savePath } </a><br/>
		</c:if>
	</c:forEach>
	<c:remove var="savePathList"  scope="session"/>
</div>
</pre>

</body>
</html>