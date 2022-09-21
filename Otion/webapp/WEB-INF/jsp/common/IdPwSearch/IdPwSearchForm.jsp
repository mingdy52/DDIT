<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 8. 19.      작성자명      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta content="Premium Multipurpose Admin &amp; Dashboard Template"
	name="description">
<meta content="Themesdesign" name="author">
<!-- App favicon -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta content="Premium Multipurpose Admin & Dashboard Template" name="description" />
        <meta content="Themesdesign" name="author" />
    <!-- App favicon -->
        <link rel="shortcut icon" href="assets/images/favicon.ico">
        <!-- Bootstrap Css -->
        <link href="${cPath}/resources/assets/css/bootstrap.min.css" id="bootstrap-style" rel="stylesheet" type="text/css" />
        <!-- Icons Css -->
        <link href="${cPath}/resources/assets/css/icons.min.css" rel="stylesheet" type="text/css" />
        <!-- App Css-->
        <link href="${cPath}/resources/assets/css/app.min.css" id="app-style" rel="stylesheet" type="text/css" />
        <!-- JAVASCRIPT -->
        <script src="${cPath}/resources/assets/libs/jquery/jquery.min.js"></script>
        <script src="${cPath}/resources/assets/libs/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="${cPath}/resources/assets/libs/metismenu/metisMenu.min.js"></script>
        <script src="${cPath}/resources/assets/libs/simplebar/simplebar.min.js"></script>
        <script src="${cPath}/resources/assets/libs/node-waves/waves.min.js"></script>

        <!-- twitter-bootstrap-wizard js -->
        <script src="${cPath}/resources/assets/libs/twitter-bootstrap-wizard/jquery.bootstrap.wizard.min.js"></script>

        <script src="${cPath}/resources/assets/libs/twitter-bootstrap-wizard/prettify.js"></script>

        <!-- form wizard init -->
        <script src="${cPath}/resources/assets/js/pages/form-wizard.init.js"></script>

        <script src="${cPath}/resources/assets/js/app.js"></script>

    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600;700;800;900&amp;display=swap" rel="stylesheet">
  
            <!-- ===============================================-->
    <!--    Favicons-->
    <!-- ===============================================-->
    <link rel="apple-touch-icon" sizes="180x180" href="${cPath}/resources/ex/assets/img/favicons/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="${cPath}/resources/ex/assets/img/favicons/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="${cPath}/resources/ex/assets/img/favicons/favicon-16x16.png">
    <link rel="shortcut icon" type="image/x-icon" href="${cPath}/resources/ex/assets/img/favicons/favicon.ico">
    <link rel="manifest" href="assets/img/favicons/manifest.json">
    <meta name="msapplication-TileImage" content="assets/img/favicons/mstile-150x150.png">
    <meta name="theme-color" content="#ffffff">
        <!-- ===============================================-->
    <link href="${cPath}/resources/ex/assets/css/theme.css" rel="stylesheet" />
	<!--     제이쿼리 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

</head>
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
							<div class="position-relative pt-4 py-5 mb-1">
								<h5 class="text-white">아이디 비밀번호 찾기</h5>
								<p class="text-white-50 mb-0">Reset your Password with
									Medroc.</p>
							</div>
						</div>
						<div class="card-body position-relative">

							<div class="p-4  rounded">

								<div class="alert alert-success text-center mb-4" role="alert">
									아이디 찾기</div>

								<form class="form-horizontal" action="${cPath }/myIdPwSearch/idSearch" method="post" id = "idSearchForm">
									<sec:csrfInput/>
									<div class="mb-3">
										<label for="useremail">이름</label> <input type="text"
											class="form-control" name="memName" placeholder="이름을 입력하세요"/>
									</div>
									<div class="mb-3">
										<label for="useremail">Email</label> <input type="email"
											class="form-control" name="memMail" placeholder="이메일을 입력하세요"/>
									</div>

									<div class="mt-4">
										<button class="btn btn-primary w-100 waves-effect waves-light"
											type="submit" id = "idSearch">찾기</button>
									</div>
								</form>
								<span id="idSearchContent"></span>
								<br>
							</div>
							<br>
							<div class="p-4  rounded">
								<div class="alert alert-primary text-center mb-4" role="alert">
									비밀번호 찾기</div>

								<form class="form-horizontal" action="${cPath }/myIdPwSearch/passSearch" method="post" id = "passSearchForm">
									<sec:csrfInput/>
									<div class="mb-3">
										<label for="useremail">아이디</label> <input type="text"
											class="form-contsodrol" name="memId" placeholder="아이디를 입력하세요"/>
									</div>
									<div class="mb-3">
										<label for="useremail">이름</label> <input type="text"
											class="form-control" name="memName" placeholder="이름을 입력하세요"/>
									</div>
									<div class="mb-3">
										<label for="useremail">Email</label> <input type="email"
											class="form-control" name="memMail" placeholder="이메일을 입력하세요"/>
									</div>

									<div class="mt-4">
										<button class="btn btn-primary w-100 waves-effect waves-light"
											type="submit" id = "passSearch">찾기</button>
									</div>
								</form>
								<span id="passSearchContent"></span>
							</div>
						</div>
					</div>

					<div class="mt-5 text-center">
						<p>
							로그인하러 가시겟습니까 ? <a href="${cPath }/login" class="fw-bold"> 로그인하러 가기</a>
						</p>
						<p>
							©
							<script>
								document.write(new Date().getFullYear())
							</script>
							2022 Medroc. Crafted with <i class="mdi mdi-heart text-danger"></i>
							by Themesdesign
						</p>
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

	<script>
		$("#idSearchForm").on("submit",function(event){
			event.preventDefault();
			let url = this.action;
			let method = this.method;
			let data = $( this ).serialize();
			$.ajax({
				url : url,
				method: method,
				data : data,
				dataType : "text",
				success : function(res){
					$("#idSearchContent").text("아이디는 " + res);
				}
			});
			return false;
		});
		$("#passSearchForm").on("submit",function(event){
			event.preventDefault();
			let url = this.action;
			let method = this.method;
			let data = $( this ).serialize();
			$.ajax({
				url : url,
				method: method,
				data : data,
				dataType : "text",
				success : function(res){
					if(res == "" || res == null)
						$("#passSearchContent").text("해당하는 계정이 존재하지 않습니다. 아이디먼저 찾아주세요");
					else
						$("#passSearchContent").text(res + "로 임시 비밀번호를 보냈습니다.");
				}
			});
			return false;
		});
	</script>

</body>
</html>