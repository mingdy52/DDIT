<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 8. 18.      작성자명      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="utf-8">
<title>Register | Medroc - Admin &amp; Dashboard Template</title>
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
<style>
 #idCheck{
 	border: 1px solid #778295;
 	color: black;
 }
 
 #memNickCheck{
	border: 1px solid #778295;
 	color: black;
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
							<div class="position-relative pt-4 py-4 mb-1">
								<h4 class="text-white">회원가입</h4>
								<p class="text-white-50 mb-0">지정된 양식에 맞춰 입력해주세요
							</div>
						</div>
						<div class="card-body position-relative">
							<div>
								<form:form class="form-horizontal" action = "${cPath }/register" modelAttribute="registerVO" id = "registerForm">

									<div class="mb-3">
										<label for="memId">회원아이디 *</label> 
										<form:input type="text"  method="post" data-idcheck="false"
											class="form-control" path="memId" placeholder="아이디를 입력해주세요" autocomplete="off"/>
											<form:errors path="memId" element="span" cssClass="error"/>
											<br>
										<div style="float: right;">
										<span id="idCheckResult"></span>
										<button type="button" id="idCheck" class="btn btn-light btn-sm waves-effect waves-light">ID중복 체크</button>
										</div>
									</div>
									<div class="mb-3">
										<label for="memPass">비밀번호 *</label> <form:input type="password" pattern="(?=.*[a-zA-Z])(?=.*[~!@#$%^*+=-])(?=.*[0-9]).{4,8}$"
											class="form-control" id="memPass" path="memPass" placeholder="비밀번호를 입력해주세요"/>
											<form:errors path="memPass" element="span" cssClass="error"/>
									</div>
									<div class="mb-3">
										<label for="memcheckPass">비밀번호 확인 *</label> <form:input type="password" pattern="(?=.*[a-zA-Z])(?=.*[~!@#$%^*+=-])(?=.*[0-9]).{4,8}$"
											class="form-control" id="memCheckPass" path="memCheckPass" placeholder="비밀번호를 한번더 입력해주세요"/>
											<form:errors path="memCheckPass" element="span" cssClass="error"/>
											<span id="passCheckResult"></span>
									</div>
									<div class="mb-3">
										<label for="memName">이름 *</label> <form:input type="text" 
											class="form-control" path="memName" placeholder="이름을 입력해주세요" autocomplete="off"/>
											<form:errors path="memName" element="span" cssClass="error"/>
									</div>
									<div class="mb-3">
										<label for="memNick">닉네임(기본 설정은 자신의 이름입니다.)</label> <form:input type="text" min="4" max="8" pattern="[가-힣a-zA-Z]{4,8}" data-nickcheck = "false"
											class="form-control" path="memNick" placeholder="한글 영문을 이용하여 4~8 글자의 별명을 입력해주세요." id="memNickForm" autocomplete="off"/>
											<form:errors path="memNick" element="span" cssClass="error"/>
											<br>
											<div style="float: right;">
										<span id="memNickCheckResult"></span>
										<button type="button" id="memNickCheck" class="btn btn-light btn-sm waves-effect waves-light">별명중복 체크</button>
										</div>
									</div>
									<div class="mb-3">
										<label for="memMail">Email *</label> <form:input type="email"
											class="form-control" path="memMail" placeholder="이메일을 입력해주세요" autocomplete="off"/>
											<form:errors path="memMail" element="span" cssClass="error"/>
									</div>

									<div class="mb-3">
										<label for="memHp">전화번호 *</label> <form:input type="text" pattern="[0-1]{3}-[0-9]{4}-[0-9]{4}" minlength="13" maxlength="13"
											class="form-control" path="memHp"  autocomplete="off"
											placeholder="000-0000-0000"/>
											<form:errors path="memHp" element="span" cssClass="error"/>
									</div>
									<div class="mt-4">
										<button class="btn btn-primary w-100 waves-effect waves-light"
											type="submit">회원가입</button>
									</div>

									<div class="mt-5 text-center">
										<p>
											이미 계정이 있으신가요?<a href="${cPath }/login"
												class="fw-bold"> 로그인하기 </a>
										</p>
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

	<script>
	let idCheck = false;
	let passCheck = false;
	let nickCheck = false;
		$("#idCheck").on("click",function(){
			$("#idCheckResult").text("");
			let memId = $("input[name=memId]").val();
			if(!memId){
				idCheck = false;
				$("#idCheckResult").text("아이디를 입력해주세요.").css("color","red");
				return false;
			}
			console.log(memId);
			$.ajax({
				type : 'get',
				data : {'memId' : memId},
				dataType : "json",
				success : function(resp){
					console.log(resp);
					if(resp.length != 0){
						idCheck = false;
						$("#idCheckResult").data("idcheck","false");
						$("#idCheckResult").text("중복된 아이디입니다.").css("color","red");
					}else{
						idCheck = true;
						$("#idCheckResult").data("idcheck","true");
						$("#idCheckResult").text("사용 가능한 아이디입니다.").css("color","black")
					}
				},
				error : function(jqXHR, status, error) {
					console.log(error);
				}
			})
		});
		$("#memNickCheck").on("click",function(){
			$("#memNickCheckResult").text("");
			let memNick = $("input[name=memNick]").val();
			if(!memNick){
				memNickCheck = false;
				$("#memNickCheckResult").text("닉네임을 입력해주세요.").css("color","red");
				return false;
			}
			console.log(memId);
			$.ajax({
				url : "${cPath}/register/nickCheck",
				type : 'get',
				data : {'memNick' : memNick},
				dataType : "json",
				success : function(resp){
					console.log(resp);
					if(resp.length != 0){
						nickCheck = false;
						$("#memNickCheckResult").text("중복된 닉네임입니다.").css("color","red");
					}else{
						nickCheck = true;
						$("#memNickCheckResult").text("사용 가능한 닉네임입니다.").css("color","black");
					}
				},
				error : function(jqXHR, status, error) {
					console.log(error);
				}
			})
		});
		$( document ).ready( function() {
	        $("#memCheckPass").keyup( function() {
	          var a = $('#memPass').val();
	          console.log(a);
	          var b = $('#memCheckPass').val();
	          console.log(b);
	         if(a == b){
	        	 passCheck = true;
	        	 $("#passCheckResult").text("비밀번호가 일치합니다");
	         }else{
	        	 passCheck = false;
	        	 $("#passCheckResult").text("비밀번호가 다릅니다.")
	         }
	        } );
	      } );
		
		$("#registerForm").on("submit",function(){
			if(idCheck == true && passCheck == true && nickCheck == true){
				$("#registerForm").submit();
			}
		})
	</script>

</body>
</html>