<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Blog</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta content="Premium Multipurpose Admin & Dashboard Template"
	name="description" />
<meta content="Themesdesign" name="author" />
<!-- App favicon -->
<link rel="shortcut icon" href="assets/images/favicon.ico">
<!-- Bootstrap Css -->
<link href="${cPath}/resources/assets/css/bootstrap.min.css"
	id="bootstrap-style" rel="stylesheet" type="text/css" />
<!-- Icons Css -->
<link href="${cPath}/resources/assets/css/icons.min.css"
	rel="stylesheet" type="text/css" />
<!-- App Css-->
<link href="${cPath}/resources/assets/css/app.min.css" id="app-style"
	rel="stylesheet" type="text/css" />


<!-- ===============================================-->
<!--    Favicons-->
<!-- ===============================================-->
<link rel="apple-touch-icon" sizes="180x180"
	href="${cPath}/resources/ex/assets/img/favicons/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32"
	href="${cPath}/resources/ex/assets/img/favicons/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16"
	href="${cPath}/resources/ex/assets/img/favicons/favicon-16x16.png">
<link rel="shortcut icon" type="image/x-icon"
	href="${cPath}/resources/ex/assets/img/favicons/favicon.ico">
<link rel="manifest" href="${cPath}/assets/img/favicons/manifest.json">
<link href="${cPath}/resources/assets/css/bootstrap.min.css"
	id="bootstrap-style" rel="stylesheet" type="text/css" />
<meta name="msapplication-TileImage"
	content="${cPath}/assets/img/favicons/mstile-150x150.png">
<meta name="theme-color" content="#ffffff">


<!-- ===============================================-->
<!--    Stylesheets-->
<!-- ===============================================-->
<link href="${cPath}/resources/ex/assets/css/theme.css" rel="stylesheet" />
<!--     제이쿼리 -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<style>
#logoName {
	padding-top: 10px;
	padding-left: 40px;
	color: #0138B7;
	font-size: 50px;
	font-weight: bold;
}

#sidebar {
	background-color: white;
	width: 20%;
	float: left;
	padding: 0.5%;
	height: auto;
	/* 	position: fixed; */
	margin-top: 68px;
}

#blogContents {
	background-color: white;
	width: 80%;
	float: right;
	overflow: scroll;
	height: 870px;
	overflow-x: hidden;
	padding-top: 30px;
	/* 	margin-top: 75px; */
	-ms-overflow-style: none; /* IE and Edge */
	scrollbar-width: none; /* Firefox */
}


#blogContents::-webkit-scrollbar {
	display: none; /* Chrome, Safari, Opera*/
}

.blog {
	min-height: 800px;
}

#wirting {
	background-color: white;
	width: 80%;
	padding: 10px;
	margin-left: 3%;
	margin-top: 1%;
	float: left;
}

#logoName {
	padding-top: 10px;
	padding-left: 40px;
	color: #717CFF;
	font-size: 30px;
	font-weight: bold;
	
}

#blogHeader {
	position: fixed;
	width: 100%;
	margin: 0;
	background-color: white;
	clear: both;
	 box-shadow: 0px 7px 29px 0px rgba(100, 100, 180, 0.1);
}

#sidebar-menu{
	width: 300px;
	float: right;
}

@import url(//fonts.googleapis.com/earlyaccess/notosanskr.css);

.notosanskr * { 
 font-family: 'Noto Sans KR', sans-serif;
}

#sidebar-menu ul li a i{
	background-color: white;
	color: black;
}

#sidebar-menu ul li a i:hover{
	background-color: white;
}
</style>
<body>
	<!-- Begin page -->
	<div id="body" class="card" style="z-index: 1;  background-color: white;">
		<header id="blogHeader">
			<tiles:insertAttribute name="OtionHeader" />
		</header>
	</div>
	<div style="padding-top: 30px;  z-index: 2;">
	<div class="blog" id="sidebar">
		<tiles:insertAttribute name="blogList" />
	</div>
	<div class="blog" id="blogContents">
		<div id="wirting">
			<tiles:insertAttribute name="contents" />
		</div>
	</div>
	</div>


	<!-- ===============================================-->
	<!--    End of Main Content-->
	<!-- ===============================================-->




	<!-- ===============================================-->
	<!--    JavaScripts-->
	<!-- ===============================================-->
	<script src="${cPath}/resources/ex/vendors/@popperjs/popper.min.js"></script>
	<script src="${cPath}/resources/ex/vendors/bootstrap/bootstrap.min.js"></script>
	<script src="${cPath}/resources/ex/vendors/is/is.min.js"></script>
	<script
		src="https://polyfill.io/v3/polyfill.min.js?features=window.scroll"></script>
	<script src="${cPath}/resources/ex/assets/js/theme.js"></script>

	<link
		href="https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600;700;800;900&amp;display=swap"
		rel="stylesheet">
</body>

</html>