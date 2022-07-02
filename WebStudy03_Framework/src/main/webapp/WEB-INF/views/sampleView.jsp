<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	Sample View
	상대 경로 : ../../resources/images/cat1.jpg -- 오류 Context Path 분실
	상대 경로 : ./resources/images/cat1.jpg - 위치의 기준점은 브라우저가 잡는다. 
	<img src="./resources/images/cat1.jpg" />
	
	절대 경로 : http://localhost:80/WebStudy01/resources/images/cat1.jpg
	<img src="http://localhost:80/WebStudy01/resources/images/cat1.jpg" />
</body>
</html>