<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* ${date}      서효림      최초작성
* Copyright (c) ${year} by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!doctype html>
<html lang="en">
<head>
<title>Main</title>
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
<!-- JAVASCRIPT -->
<script src="${cPath}/resources/assets/libs/jquery/jquery.min.js"></script>
<script
	src="${cPath}/resources/assets/libs/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="${cPath}/resources/assets/libs/metismenu/metisMenu.min.js"></script>
<script src="${cPath}/resources/assets/libs/simplebar/simplebar.min.js"></script>
<script src="${cPath}/resources/assets/libs/node-waves/waves.min.js"></script>

<!-- twitter-bootstrap-wizard js -->
<script
	src="${cPath}/resources/assets/libs/twitter-bootstrap-wizard/jquery.bootstrap.wizard.min.js"></script>

<script
	src="${cPath}/resources/assets/libs/twitter-bootstrap-wizard/prettify.js"></script>

<!-- form wizard init -->
<script src="${cPath}/resources/assets/js/pages/form-wizard.init.js"></script>

<script src="${cPath}/resources/assets/js/app.js"></script>

<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600;700;800;900&amp;display=swap"
	rel="stylesheet">

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
<link rel="manifest" href="assets/img/favicons/manifest.json">
<meta name="msapplication-TileImage"
	content="assets/img/favicons/mstile-150x150.png">
<meta name="theme-color" content="#ffffff">
<!-- ===============================================-->
<link href="${cPath}/resources/ex/assets/css/theme.css" rel="stylesheet" />
<!--     제이쿼리 -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<meta charset="utf-8" />
<title>Login | Medroc - Admin & Dashboard Template</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta content="Premium Multipurpose Admin & Dashboard Template"
	name="description" />
<meta content="Themesdesign" name="author" />
<!-- App favicon -->
<link rel="shortcut icon" href="assets/images/favicon.ico">

<!-- Bootstrap Css -->
<link href="assets/css/bootstrap.min.css" id="bootstrap-style"
	rel="stylesheet" type="text/css" />
<!-- Icons Css -->
<link href="assets/css/icons.min.css" rel="stylesheet" type="text/css" />
<!-- App Css-->
<link href="assets/css/app.min.css" id="app-style" rel="stylesheet"
	type="text/css" />
<c:if test="${not empty message }">
	<script>
		alert("${message}");
	</script>
</c:if>
</head>
<style>
.loginformbox {
	background-color: white;
	padding: 30px;
}
</style>
<body
	class="authentication-bg d-flex align-items-center min-vh-100 py-5">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="text-center">
					<a href="index.html" class="d-block auth-logo"> <img
						src="assets/images/logo-dark.png" alt="" height="24"
						class="logo logo-dark mx-auto"> <img
						src="assets/images/logo-light.png" alt="" height="24"
						class="logo logo-light mx-auto">
					</a>
				</div>
			</div>
		</div>

		<div class="row justify-content-center">
			<div class="col-md-8 col-lg-6 col-xl-5">
				<div class="p-4">
					<div class="card overflow-hidden mt-2">
						<div class="auth-logo text-center bg-primary position-relative">
							<div class="img-overlay"></div>
							<div class="position-relative py-5 mb-1">
								<h3 class="text-white">O-TION</h3>
							</div>
							<div class="position-relative pt-4 py-5 mb-1 loginformbox">
								<form:form class="form-horizontal" action="${cPath }/login"
									method="post" modelAttribute="memberVO">
									<sec:csrfInput />
									<div class="mb-3">
										<!-- 										<label for="ID" class="form-label">ID</label> -->
										<form:input type="text" class="form-control" path="memId"
											placeholder="ID" autocomplete="off" />
									</div>

									<div class="mb-3">
										<!-- 										<label for="Password">Password</label> -->
										<form:input type="Password" class="form-control"
											path="memPass" placeholder="Password" />
										<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION }">
											<span class="error">${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</span>
										</c:if>
									</div>
									<div class="mt-3">
										<button class="btn btn-primary waves-effect waves-light"
											type="submit">LOGIN</button>
									</div>

									<div class="mt-4 text-center">
										<a href="${cPath }/myIdPwSearch" class="text-muted"><i
											class="mdi mdi-lock me-1"></i>아이디, 비밀번호 찾기</a>
											<br><a href="${cPath }/register" class="text-muted"><i class="mdi mdi-account-edit"></i> 회원가입</a>
									</div>
									<div class="mt-5 text-center">
										
									</div>
								</form:form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>

	<!-- JAVASCRIPT -->
	<script src="assets/libs/jquery/jquery.min.js"></script>
	<script src="assets/libs/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="assets/libs/metismenu/metisMenu.min.js"></script>
	<script src="assets/libs/simplebar/simplebar.min.js"></script>
	<script src="assets/libs/node-waves/waves.min.js"></script>

	<script src="assets/js/app.js"></script>

</body>
</html>
