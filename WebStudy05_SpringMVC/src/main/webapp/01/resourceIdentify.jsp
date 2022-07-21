<%@page import="java.net.URL"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>01/resourceIdentify.jsp</title>
</head>
<body>

<h4>리소스의 식별 방법</h4>
<pre>
리소스 저장 위치에 따라 파일을 확보하는 방법이 다라짐 -> ImageStreamingServlet
1. 파일 시스템 리소스 : 파일 시스템 상의 절대 경로(D:\contents\cat1.jpg)
2. class path 리소스  : class path 이후의 절대 경로
	(/kr/or/ddit/images/cat1.jpg)
3. web 리소스 : 서버에 의해 사용됨, 서버에 의해 경로가 결정, URL 갖고 있음.
	(http://IP[domain]:port/WebStudy01/resources/images/cat1.jpg)
	
	** web resource 식별 방법(URL)
	URI (Unified Resource Identifier)
		URL (Unified Resource Locator) - 자원의 위치로 가져옴. 유효성 가능. URI와 같은 의미로 사용
	URC (Unified Resource Content) - 유효성 확보 불가. ex) yes24. 책의 속성(저자, 제목...)- 폐기
	URN (Unified Resource Naming) - 모든 이름이 등록되어 있어야 하며 중복성을 해결할 수 없다. - 폐기
	
	** URL 표현 방법
	http://IP[domain]:port/contextPath/depth.../resourceName
	
	http://localhost:80/WebStudy01/resources/images/cat1.jpg
	
	리소스 위치 표현 방식
	1. 상대 주소: 현재 브라우저가 가지고 있는 URL을 기준으로 상대적 경로 표기.
	2. 절대 주소: 자원의 위치에서 최상위 경로부터 전체 경로가 표기. (이미 인지하고 있는 위치는 표현하지 않는다.) 
		1.) client side: <%=request.getContextPath() %> /resources/images/cat1.jpg
			반드시! contextPath부터 시작되는 경로 형태
		2.) server side: /resources/images/cat1.jpg
			서버사이드에서는 반드시 절대경로만 사용할 것!
			contextPath 이후의 경로 형태
			
		<%
// 			String path = request.getContextPath() + "/resources/images/cat1.jpg";
			String path = "/resources/images/cat1.jpg";
			URL url = application.getResource(path);
			out.print(url);
// 			톰캣 안의 WebStudy01안에서 실행
		%>
</pre>

client side
<img src="../resources/images/cat1.jpg" />
<img src="http://localhost:80/WebStudy01/resources/images/cat1.jpg" />
<img src="/WebStudy01/resources/images/cat1.jpg" />
<img src="<%=request.getContextPath() %>/resources/images/cat1.jpg" />
	
</body>
</html>
