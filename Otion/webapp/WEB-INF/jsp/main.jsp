<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<c:set var="cPath" value="<%=request.getContextPath()%>" />
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal.realMember" var="member" />
</sec:authorize>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
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

<!-- ===============================================-->
<!--    Document Title-->
<!-- ===============================================-->
<title>Executive | Landing, Corporate &amp; Business Templatee</title>


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
<!--    Stylesheets-->
<!-- ===============================================-->
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<link href="${cPath}/resources/ex/assets/css/theme.css" rel="stylesheet" />
</head>
<c:if test="${not empty message }">
	<script type="text/javascript">
		alert("${message }");
	</script>
	<c:remove var="message" scope="session" />
</c:if>
<style>
body {
	background-color: white;
}

#top-menu {
	font-size: 40px;
	text-align: center;
	margin-top: 5%;
}

#menu-name {
	margin-left: 4%;
	margin-right: 4%;
	color: #484646;
}

#logoName {
	padding-top: 10px;
	padding-left: 40px;
	color: #717CFF;
	font-size: 30px;
	font-weight: bold;
}

#main-section {
	width: 80%;
	margin-top: 5%;
	margin-left: 10%;
	background-color: lightgray;
}

.main-area {
	margin-top: 3%;
	padding: 20px;
	border-color: red;
	border: 3px;
}
</style>
<body>
	<c:if test="${not empty message }">
		<script>
			swal({
				title : "${message }",
				icon : "success",
			});
		</script>
		<c:remove var="message" scope="session" />
	</c:if>
	<!-- Begin page -->
	<div id="body">
		<header>
			<div class="navbar-header">
				<div>
					<div class="logoName-sector">
						<a id="logoName" href="${cPath}/">O-TION</a>
					</div>
				</div>
				<div class="d-flex">
<!-- 					<div class="dropdown d-none d-lg-inline-block ms-1"> -->
<!-- 						<button type="button" -->
<!-- 							class="btn header-item noti-icon waves-effect" -->
<!-- 							style="padding: 20px;"> -->
<!-- 							<i class="dripicons-brightness-medium"></i> -->
<!-- 						</button> -->
<!-- 					</div> -->
					<div class="dropdown d-inline-block">
						<sec:authorize access="isAnonymous()">
							<button type="button"
								class="btn header-item noti-icon right-bar-toggle waves-effect"
								style="padding: 20px;" onclick="location.href='${cPath}/login'">
								<i class="fas fa-lock"></i>
							</button>
						</sec:authorize>

						<!-- 로그인 중인 사용자 -->
						<sec:authorize access="isAuthenticated()">
							<button type="button"
								class="btn header-item noti-icon right-bar-toggle waves-effect"
								style="padding: 20px;" onclick="location.href='${cPath}/logout'">
								<i class="fas fa-lock"></i>
							</button>
						</sec:authorize>
					</div>
					<!--                         알림 -->
					<div class="dropdown d-inline-block">
						<button type="button"
							class="btn header-item noti-icon waves-effect"
							id="page-header-notifications-dropdown" data-bs-toggle="dropdown"
							aria-expanded="false" style="padding: 20px;">
							<i class="far fa-bell"></i>
							<!--                                 <span class="noti-dot"></span> -->
						</button>
						<sec:authorize access="isAuthenticated()">
							<div class="dropdown-menu dropdown-menu-lg dropdown-menu-end p-0"
								aria-labelledby="page-header-notifications-dropdown">
								<div
									class="dropdown-menu dropdown-menu-lg dropdown-menu-end p-0 show"
									aria-labelledby="page-header-notifications-dropdown"
									style="position: absolute; inset: 0px auto auto 0px; margin: 0px; transform: translate(-274px, 72px);"
									data-popper-placement="bottom-end">
									<div class="p-3">
										<div class="row align-items-center">
											<div class="col">
												<h6 class="m-0">알림</h6>
											</div>
										</div>
									</div>
									<div data-simplebar="init" style="max-height: 230px;">
										<div class="simplebar-wrapper" style="margin: 0px;">
											<div class="simplebar-height-auto-observer-wrapper">
												<div class="simplebar-height-auto-observer"></div>
											</div>
											<div class="simplebar-mask">
												<div class="simplebar-offset"
													style="right: -17px; bottom: 0px;">
													<div class="simplebar-content-wrapper"
														style="height: auto; overflow: hidden scroll;">
														<div class="simplebar-content mynews"
															style="padding: 0px;">
															<!-- 											 <a href="" class="text-reset notification-item"> -->
															<!-- 												<div class="d-flex align-items-center"> -->
															<!-- 													<img src="assets/images/users/avatar-6.jpg" -->
															<!-- 														class="me-3 rounded-circle avatar-xs" alt="user-pic"> -->
															<!-- 													<div class="flex-grow-1 text-truncate"> -->
															<!-- 														<h6 class="mt-0 mb-1"> -->
															<!-- 															Jonathon Joseph <span class="mb-1 text-muted fw-normal">Friend -->
															<!-- 																of mine occidental.</span> -->
															<!-- 														</h6> -->
															<!-- 														<p class="mb-0 font-size-12"> -->
															<!-- 															<i class="mdi mdi-clock-outline"></i> 5 min ago -->
															<!-- 														</p> -->
															<!-- 													</div> -->
															<!-- 												</div> -->
															</a>
														</div>
													</div>
												</div>
											</div>
											<div class="simplebar-placeholder"
												style="width: auto; height: 336px;"></div>
										</div>
										<div class="simplebar-track simplebar-horizontal"
											style="visibility: hidden;">
											<div class="simplebar-scrollbar"
												style="transform: translate3d(0px, 0px, 0px); display: none;"></div>
										</div>
										<div class="simplebar-track simplebar-vertical"
											style="visibility: visible;">
											<div class="simplebar-scrollbar"
												style="transform: translate3d(0px, 0px, 0px); display: block; height: 157px;"></div>
										</div>
									</div>
									<div class="p-2 border-top">
										<div class="d-grid">
											<a class="btn btn-sm btn-link font-size-14 text-center"
												href="${cPath}/news"> <i
												class="mdi mdi-arrow-right-circle me-1"></i> View More..
											</a>
										</div>
									</div>
								</div>
							</div>
						</sec:authorize>
						<div class="dropdown d-inline-block user-dropdown">

							<button type="button"
								class="btn header-item noti-icon waves-effect"
								id="page-header-search-dropdown" data-bs-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false"
								style="padding: 20px;">
								<i class=" fas fa-user"></i>
							</button>
							<sec:authorize access="isAuthenticated()">
								<div class="dropdown-menu dropdown-menu-lg dropdown-menu-end">
									<div class="p-3">
										<div class="row align-items-center">
											<div class="col">
												<h6 class="m-0">
													<sec:authentication property="principal.realMember.memNick" />
												</h6>
											</div>
											<div class="col-auto">
												<a href="#!" class="small"> Available</a>
											</div>
										</div>
									</div>
									<divdata-simplebar> <!-- item--> <a
										href="${cPath}/member/mypage"
										class="text-reset notification-item">
										<div class="d-flex align-items-center">
											<div class="avatar-xs me-3 mt-1">
												<span
													class="avatar-title bg-soft-secondary rounded-circle font-size-16">
													<i class="ri-user-line text-secondary font-size-16"></i>
												</span>
											</div>
											<div class="flex-grow-1 text-primary">
												<h6 class="mb-1">MY PAGE</h6>
											</div>
										</div>
									</a> <!-- item--> <a href="${cPath}/blog/${member.memId}"
										class="text-reset notification-item">
										<div class="d-flex align-items-center">
											<div class="avatar-xs me-3 mt-1">
												<span
													class="avatar-title bg-soft-primary rounded-circle font-size-16">
													<i class=" fas fa-pen text-primary font-size-16"></i>
												</span>
											</div>
											<div class="flex-grow-1 text-truncate">
												<h6 class="mb-1">MY BLOG</h6>
											</div>
										</div>
									</a> <!-- item--> <a href="${cPath }/project"
										class="text-reset notification-item">
										<div class="d-flex align-items-center">
											<div class="avatar-xs me-3 mt-1">
												<span
													class="avatar-title bg-soft-secondary rounded-circle font-size-16">
													<i class=" fas fa-folder text-secondary"></i>
												</span>
											</div>
											<div class="flex-grow-1 text-truncate">
												<h6 class="mb-1">
													MY PROJECT <span class="badge bg-success float-end mt-1"></span>
												</h6>

											</div>
										</div>
									</a> <!-- item--> <a href="${cPath }/myexpert"
										class="text-reset notification-item">
										<div class="d-flex align-items-center">
											<div class="avatar-xs me-3 mt-1">
												<span
													class="avatar-title bg-soft-primary rounded-circle font-size-16">
													<i class="mdi mdi-comment-question text-primary"></i>
												</span>
											</div>
											<div class="flex-grow-1 text-truncate">
												<h6 class="mb-1">MY EXPERT</h6>
												<!--                                             <p class="mb-0 font-size-12">Control your privacy parameters..</p> -->
											</div>
										</div>
									</a> <sec:authorize access="hasRole('ROLE_EXPERT')">
										<!-- item-->
										<a href="${cPath }/iexpert/profile"
											class="text-reset notification-item">
											<div class="d-flex align-items-center">
												<div class="avatar-xs me-3 mt-1">
													<span
														class="avatar-title bg-soft-secondary rounded-circle font-size-16">
														<i class="fas fa-user-graduate text-secondary"></i>
													</span>
												</div>
												<div class="flex-grow-1 text-truncate">
													<h6 class="mb-1">
														IEXPERT <span class="badge bg-success float-end mt-1"></span>
													</h6>

												</div>
											</div>
										</a>
									</sec:authorize> <sec:authorize access="hasRole('ROLE_ADMIN')">
										<!-- item-->
										<a href="${cPath }/admin/member"
											class="text-reset notification-item">
											<div class="d-flex align-items-center">
												<div class="avatar-xs me-3 mt-1">
													<span
														class="avatar-title bg-soft-secondary rounded-circle font-size-16">
														<i class="ri-settings-2-line text-secondary"></i>
													</span>
												</div>
												<div class="flex-grow-1 text-truncate">
													<h6 class="mb-1">
														MY ADMIN <span class="badge bg-success float-end mt-1"></span>
													</h6>

												</div>
											</div>
										</a>
									</sec:authorize> </divdata-simplebar>
								</div>
							</sec:authorize>
						</div>
					</div>
				</div>
			</div>
	</div>
	</header>
	</div>
	<div>
		<header class="navbar navbar-expand-lg navbar-light"
			data-navbar-on-scroll="data-navbar-on-scroll">

			<ul class="navbar-nav mx-auto pt-2 pt-lg-0 font-base">
				<li class="nav-item px-2"><a class="nav-link fw-medium active"
					aria-current="page" href="${cPath }/">HOME</a></li>
				<li class="nav-item px-2"><a class="nav-link"
					href="${cPath}/notice">NOTICE</a></li>
				<li class="nav-item px-2"><a class="nav-link"
					href="${cPath}/cooboard">COMMNUNITY </a></li>
				<li class="nav-item px-2"><a class="nav-link"
					href="${cPath}/blog/latest">BLOG </a></li>
				<li class="nav-item px-2"><a class="nav-link"
					href="${cPath}/expert">EXPERT </a></li>
			</ul>
	</div>
	</div>
	</header>

	<section class="py-0" id="home">
		<div class="bg-holder d-none d-md-block"
			style="background-image: url(assets/img/gallery/hero.png); background-position: right bottom; background-size: contain; margin-top: 5.625rem;">
		</div>
		<!--/.bg-holder-->

		<div class="bg-holder d-block d-md-none"
			style="background-image: url(assets/img/illustrations/hero-bg.png); background-position: right top; background-size: contain; margin-top: 5.625rem;">
		</div>
		<!--/.bg-holder-->

	</section>


	<div class="container">
		<div class="row align-items-center min-vh-md-75 mt-7">
			<div class="col-md-7 col-lg-6 py-6 text-sm-start text-center">
				<h1
					class="mt-6 mb-sm-4 display-4 fw-light lh-sm fs-4 fs-lg-6 fs-xxl-7">
					Welcome to <span class="text-primary">O-TION</span><br
						class="d-block d-lg-none d-xl-block" />이용해보세요.
				</h1>
				<p class="mb-5 fs-1 lh-lg"></p>
				<a class="btn btn-lg btn-primary hover-top btn-glow"
					href="${cPath}/register">Apply Online <svg
						class="bi bi-arrow-right-short ms-2"
						xmlns="http://www.w3.org/2000/svg" width="25" height="25"
						fill="currentColor" viewBox="0 0 16 16">
                  <path fill-rule="evenodd"
							d="M4 8a.5.5 0 0 1 .5-.5h5.793L8.146 5.354a.5.5 0 1 1 .708-.708l3 3a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708-.708L10.293 8.5H4.5A.5.5 0 0 1 4 8z"></path>
                </svg>
				</a>
				<div class="mt-5 mt-xl-6">
					<ul class="list-unstyled list-inline mb-0">
						<li class="list-inline-item me-3"><a
							class="text-decoration-none" href="#!"> <svg
									class="bi bi-facebook text-facebook"
									xmlns="http://www.w3.org/2000/svg" width="20" height="20"
									fill="currentColor" viewBox="0 0 16 16">
                        <path
										d="M16 8.049c0-4.446-3.582-8.05-8-8.05C3.58 0-.002 3.603-.002 8.05c0 4.017 2.926 7.347 6.75 7.951v-5.625h-2.03V8.05H6.75V6.275c0-2.017 1.195-3.131 3.022-3.131.876 0 1.791.157 1.791.157v1.98h-1.009c-.993 0-1.303.621-1.303 1.258v1.51h2.218l-.354 2.326H9.25V16c3.824-.604 6.75-3.934 6.75-7.951z"></path>
                      </svg></a></li>
						<li class="list-inline-item me-3"><a href="#!"> <svg
									class="bi bi-twitter text-twitter"
									xmlns="http://www.w3.org/2000/svg" width="20" height="20"
									fill="currentColor" viewBox="0 0 16 16">
                        <path
										d="M5.026 15c6.038 0 9.341-5.003 9.341-9.334 0-.14 0-.282-.006-.422A6.685 6.685 0 0 0 16 3.542a6.658 6.658 0 0 1-1.889.518 3.301 3.301 0 0 0 1.447-1.817 6.533 6.533 0 0 1-2.087.793A3.286 3.286 0 0 0 7.875 6.03a9.325 9.325 0 0 1-6.767-3.429 3.289 3.289 0 0 0 1.018 4.382A3.323 3.323 0 0 1 .64 6.575v.045a3.288 3.288 0 0 0 2.632 3.218 3.203 3.203 0 0 1-.865.115 3.23 3.23 0 0 1-.614-.057 3.283 3.283 0 0 0 3.067 2.277A6.588 6.588 0 0 1 .78 13.58a6.32 6.32 0 0 1-.78-.045A9.344 9.344 0 0 0 5.026 15z"></path>
                      </svg></a></li>
						<li class="list-inline-item me-3"><a href="#!"> <svg
									class="bi bi-instagram text-instagram text-youtube"
									xmlns="http://www.w3.org/2000/svg" width="20" height="20"
									fill="currentColor" viewBox="0 0 16 16">
                        <path
										d="M8 0C5.829 0 5.556.01 4.703.048 3.85.088 3.269.222 2.76.42a3.917 3.917 0 0 0-1.417.923A3.927 3.927 0 0 0 .42 2.76C.222 3.268.087 3.85.048 4.7.01 5.555 0 5.827 0 8.001c0 2.172.01 2.444.048 3.297.04.852.174 1.433.372 1.942.205.526.478.972.923 1.417.444.445.89.719 1.416.923.51.198 1.09.333 1.942.372C5.555 15.99 5.827 16 8 16s2.444-.01 3.298-.048c.851-.04 1.434-.174 1.943-.372a3.916 3.916 0 0 0 1.416-.923c.445-.445.718-.891.923-1.417.197-.509.332-1.09.372-1.942C15.99 10.445 16 10.173 16 8s-.01-2.445-.048-3.299c-.04-.851-.175-1.433-.372-1.941a3.926 3.926 0 0 0-.923-1.417A3.911 3.911 0 0 0 13.24.42c-.51-.198-1.092-.333-1.943-.372C10.443.01 10.172 0 7.998 0h.003zm-.717 1.442h.718c2.136 0 2.389.007 3.232.046.78.035 1.204.166 1.486.275.373.145.64.319.92.599.28.28.453.546.598.92.11.281.24.705.275 1.485.039.843.047 1.096.047 3.231s-.008 2.389-.047 3.232c-.035.78-.166 1.203-.275 1.485a2.47 2.47 0 0 1-.599.919c-.28.28-.546.453-.92.598-.28.11-.704.24-1.485.276-.843.038-1.096.047-3.232.047s-2.39-.009-3.233-.047c-.78-.036-1.203-.166-1.485-.276a2.478 2.478 0 0 1-.92-.598 2.48 2.48 0 0 1-.6-.92c-.109-.281-.24-.705-.275-1.485-.038-.843-.046-1.096-.046-3.233 0-2.136.008-2.388.046-3.231.036-.78.166-1.204.276-1.486.145-.373.319-.64.599-.92.28-.28.546-.453.92-.598.282-.11.705-.24 1.485-.276.738-.034 1.024-.044 2.515-.045v.002zm4.988 1.328a.96.96 0 1 0 0 1.92.96.96 0 0 0 0-1.92zm-4.27 1.122a4.109 4.109 0 1 0 0 8.217 4.109 4.109 0 0 0 0-8.217zm0 1.441a2.667 2.667 0 1 1 0 5.334 2.667 2.667 0 0 1 0-5.334z"> </path>
                      </svg></a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	</section>


	<!-- ============================================-->
	<!-- <section> begin ============================-->
	<section id="services">

		<div class="container">
			<div class="row">
				<div class="col-lg-7 mx-auto text-center mb-4">
					<h5 class="fw-light fs-3 fs-lg-5 lh-sm mb-3">TREND</h5>
					<p class="mb-5">OTION에서 가장 트렌드한 게시글을 보고 소통해요.</p>
				</div>
			</div>
			<div class="row flex-center h-100">
				<div class="col-xl-9">
					<div class="row justify-content-center">
						<c:forEach items="${blogList }" var="trend" begin="0" end="5">
							<div class="col-md-4 mb-4">
								<div
									class="card h-100 shadow px-4 px-md-2 px-lg-3 card-span pt-4">
									<div class="text-center text-md-start card-hover">
										<img class="icons" src="${trend.thumnail }"
											style="margin-left: 30px; width: 220px; height: 220px;"
											alt="services" />
										<div class="card-body">
											<h6 class="fw-light fs-1 heading-color">${trend.postTitle }</h6>
											<a class="btn btn-lg ps-0 pe-3 text-primary"
												href="${cPath}/blog/${trend.blogerId }/post/${trend.postNum}"
												role="button">Learn more <svg
													class="bi bi-arrow-right-short"
													xmlns="http://www.w3.org/2000/svg" width="25" height="25"
													fill="currentColor" viewBox="0 0 16 16">
                            <path fill-rule="evenodd"
														d="M4 8a.5.5 0 0 1 .5-.5h5.793L8.146 5.354a.5.5 0 1 1 .708-.708l3 3a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708-.708L10.293 8.5H4.5A.5.5 0 0 1 4 8z"></path>
                          </svg></a>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>

						<!-- 						<div class="col-md-4 mb-4"> -->
						<!-- 							<div -->
						<!-- 								class="card h-100 shadow px-4 px-md-2 px-lg-3 card-span pt-4"> -->
						<!-- 								<div class="text-center text-md-start card-hover"> -->
						<!-- 									<img class="ps-3 icons" src="assets/img/icons/admission.png" -->
						<!-- 										height="60" alt="services" /> -->
						<!-- 									<div class="card-body"> -->
						<!-- 										<h6 class="fw-light fs-1 heading-color">University -->
						<!-- 											Admissions</h6> -->
						<!-- 										<p class="mt-3 mb-md-0 mb-lg-3">Maiores voluptas -->
						<!-- 											laboriosam non dolorum perferendis fuga repellat aut. -->
						<!-- 											Blanditiis quos in minus. Voluptatum</p> -->
						<!-- 										<a class="btn btn-lg ps-0 pe-3 text-primary" href="#" -->
						<!-- 											role="button">Learn more <svg -->
						<!-- 												class="bi bi-arrow-right-short" -->
						<!-- 												xmlns="http://www.w3.org/2000/svg" width="25" height="25" -->
						<!-- 												fill="currentColor" viewBox="0 0 16 16"> -->
						<!--                             <path fill-rule="evenodd" -->
						<!-- 													d="M4 8a.5.5 0 0 1 .5-.5h5.793L8.146 5.354a.5.5 0 1 1 .708-.708l3 3a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708-.708L10.293 8.5H4.5A.5.5 0 0 1 4 8z"></path> -->
						<!--                           </svg></a> -->
						<!-- 									</div> -->
						<!-- 								</div> -->
						<!-- 							</div> -->
						<!-- 						</div> -->
					</div>
				</div>
			</div>
		</div>
		<!-- end of .container-->

	</section>
	<!-- <section> close ============================-->
	<!-- ============================================-->




	<!-- ============================================-->
	<!-- <section> begin ============================-->
	<!-- <section> close ============================-->
	<!-- ============================================-->




	<!-- ============================================-->
	<!-- <section> begin ============================-->
	<section id="events">

		<div class="container">
			<div class="row">
				<div class="col-lg-7 mx-auto text-center mb-4">
					<h5 class="fw-light fs-3 fs-lg-5 lh-sm mb-3">Project with me</h5>
					<p class="mb-3">협업할 친구들을 찾아보아요</p>
					<c:set value="${cooList }" var="coo" />
				</div>
			</div>
			<div class="row flex-center h-100">
				<div class="col-xl-9">
					<div class="carousel slide" id="carouselEvents"
						data-bs-ride="carousel">
						<div class="carousel-inner">
							<div class="carousel-item active" data-bs-interval="10000">
								<div class="row h-100 justify-content-center">
									<div class="col-md-6 mb-4">
										<div class="card h-100 shadow px-2 px-lg-3 card-span pt-4">
											<div class="text-center text-md-start card-hover">
												<div class="card-body">
													<div class="d-flex align-items-center">
														<span
															class="badge bg-soft-primary text-primary fs--1 fw-light p-3 rounded-1"><span
															class="fw-medium fs-1 mb-2">${coo[0].cooDate }</span></span>
														<h6 class="fw-light fs-1 fs-lg-2 text-start ms-3">${coo[0].cooTitle }</h6>
													</div>
													<p class="mt-4 mb-md-0 mb-lg-3 fw-light lh-base text-start">${coo[0].cooContent}</p>
													<div class="d-flex flex-between-center">
														<div class="d-flex align-items-center">
															<a class="btn btn-lg ps-0 text-primary fw-light fs--1"
																href="#" role="button">Learn more <svg
																	class="bi bi-arrow-right-short"
																	xmlns="http://www.w3.org/2000/svg" width="25"
																	height="25" fill="currentColor" viewBox="0 0 16 16">
                                      <path fill-rule="evenodd"
																		d="M4 8a.5.5 0 0 1 .5-.5h5.793L8.146 5.354a.5.5 0 1 1 .708-.708l3 3a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708-.708L10.293 8.5H4.5A.5.5 0 0 1 4 8z"></path>
                                    </svg></a>
														</div>
														<div class="d-flex align-items-center">
															<svg class="bi bi-alarm me-2"
																xmlns="http://www.w3.org/2000/svg" width="16"
																height="16" fill="currentColor" viewBox="0 0 16 16">
                                    <path
																	d="M8.5 5.5a.5.5 0 0 0-1 0v3.362l-1.429 2.38a.5.5 0 1 0 .858.515l1.5-2.5A.5.5 0 0 0 8.5 9V5.5z"></path>
                                    <path
																	d="M6.5 0a.5.5 0 0 0 0 1H7v1.07a7.001 7.001 0 0 0-3.273 12.474l-.602.602a.5.5 0 0 0 .707.708l.746-.746A6.97 6.97 0 0 0 8 16a6.97 6.97 0 0 0 3.422-.892l.746.746a.5.5 0 0 0 .707-.708l-.601-.602A7.001 7.001 0 0 0 9 2.07V1h.5a.5.5 0 0 0 0-1h-3zm1.038 3.018a6.093 6.093 0 0 1 .924 0 6 6 0 1 1-.924 0zM0 3.5c0 .753.333 1.429.86 1.887A8.035 8.035 0 0 1 4.387 1.86 2.5 2.5 0 0 0 0 3.5zM13.5 1c-.753 0-1.429.333-1.887.86a8.035 8.035 0 0 1 3.527 3.527A2.5 2.5 0 0 0 13.5 1z"></path>
                                  </svg>
															<p class="mb-0 fw-light text-dark fs--1">${coo[0].cooStart}
																~ ${coo[0].cooEnd}</p>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-6 mb-4">
										<div class="card h-100 shadow px-2 px-lg-3 card-span pt-4">
											<div class="text-center text-md-start card-hover">
												<div class="card-body">
													<div class="d-flex align-items-center">
														<span
															class="badge bg-soft-primary text-primary fs--1 fw-light p-3 rounded-1"><span
															class="fw-medium fs-1 mb-2">${coo[1].cooDate }</span></span>
														<h6 class="fw-light fs-1 fs-lg-2 text-start ms-3">${coo[1].cooTitle }</h6>
													</div>
													<p class="mt-4 mb-md-0 mb-lg-3 fw-light lh-base text-start">${coo[1].cooContent}</p>
													<div class="d-flex flex-between-center">
														<div class="d-flex align-items-center">
															<a class="btn btn-lg ps-0 text-primary fw-light fs--1"
																href="#" role="button">Learn more <svg
																	class="bi bi-arrow-right-short"
																	xmlns="http://www.w3.org/2000/svg" width="25"
																	height="25" fill="currentColor" viewBox="0 0 16 16">
                                      <path fill-rule="evenodd"
																		d="M4 8a.5.5 0 0 1 .5-.5h5.793L8.146 5.354a.5.5 0 1 1 .708-.708l3 3a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708-.708L10.293 8.5H4.5A.5.5 0 0 1 4 8z"></path>
                                    </svg></a>
														</div>
														<div class="d-flex align-items-center">
															<svg class="bi bi-alarm me-2"
																xmlns="http://www.w3.org/2000/svg" width="16"
																height="16" fill="currentColor" viewBox="0 0 16 16">
                                    <path
																	d="M8.5 5.5a.5.5 0 0 0-1 0v3.362l-1.429 2.38a.5.5 0 1 0 .858.515l1.5-2.5A.5.5 0 0 0 8.5 9V5.5z"></path>
                                    <path
																	d="M6.5 0a.5.5 0 0 0 0 1H7v1.07a7.001 7.001 0 0 0-3.273 12.474l-.602.602a.5.5 0 0 0 .707.708l.746-.746A6.97 6.97 0 0 0 8 16a6.97 6.97 0 0 0 3.422-.892l.746.746a.5.5 0 0 0 .707-.708l-.601-.602A7.001 7.001 0 0 0 9 2.07V1h.5a.5.5 0 0 0 0-1h-3zm1.038 3.018a6.093 6.093 0 0 1 .924 0 6 6 0 1 1-.924 0zM0 3.5c0 .753.333 1.429.86 1.887A8.035 8.035 0 0 1 4.387 1.86 2.5 2.5 0 0 0 0 3.5zM13.5 1c-.753 0-1.429.333-1.887.86a8.035 8.035 0 0 1 3.527 3.527A2.5 2.5 0 0 0 13.5 1z"></path>
                                  </svg>
															<p class="mb-0 fw-light text-dark fs--1">${coo[1].cooStart}
																~ ${coo[1].cooEnd}</p>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="carousel-item" data-bs-interval="5000">
								<div class="row h-100 justify-content-center">
									<div class="col-md-6 mb-4">
										<div class="card h-100 shadow px-2 px-lg-3 card-span pt-4">
											<div class="text-center text-md-start card-hover">
												<div class="card-body">
													<div class="d-flex align-items-center">
														<span
															class="badge bg-soft-primary text-primary fs--1 fw-light p-3 rounded-1"><span
															class="fw-medium fs-1 mb-2">${coo[2].cooDate }</span></span>
														<h6 class="fw-light fs-1 fs-lg-2 text-start ms-3">${coo[2].cooTitle }</h6>
													</div>
													<p class="mt-4 mb-md-0 mb-lg-3 fw-light lh-base text-start">${coo[2].cooContent}</p>
													<div class="d-flex flex-between-center">
														<div class="d-flex align-items-center">
															<a class="btn btn-lg ps-0 text-primary fw-light fs--1"
																href="#" role="button">Learn more <svg
																	class="bi bi-arrow-right-short"
																	xmlns="http://www.w3.org/2000/svg" width="25"
																	height="25" fill="currentColor" viewBox="0 0 16 16">
                                      <path fill-rule="evenodd"
																		d="M4 8a.5.5 0 0 1 .5-.5h5.793L8.146 5.354a.5.5 0 1 1 .708-.708l3 3a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708-.708L10.293 8.5H4.5A.5.5 0 0 1 4 8z"></path>
                                    </svg></a>
														</div>
														<div class="d-flex align-items-center">
															<svg class="bi bi-alarm me-2"
																xmlns="http://www.w3.org/2000/svg" width="16"
																height="16" fill="currentColor" viewBox="0 0 16 16">
                                    <path
																	d="M8.5 5.5a.5.5 0 0 0-1 0v3.362l-1.429 2.38a.5.5 0 1 0 .858.515l1.5-2.5A.5.5 0 0 0 8.5 9V5.5z"></path>
                                    <path
																	d="M6.5 0a.5.5 0 0 0 0 1H7v1.07a7.001 7.001 0 0 0-3.273 12.474l-.602.602a.5.5 0 0 0 .707.708l.746-.746A6.97 6.97 0 0 0 8 16a6.97 6.97 0 0 0 3.422-.892l.746.746a.5.5 0 0 0 .707-.708l-.601-.602A7.001 7.001 0 0 0 9 2.07V1h.5a.5.5 0 0 0 0-1h-3zm1.038 3.018a6.093 6.093 0 0 1 .924 0 6 6 0 1 1-.924 0zM0 3.5c0 .753.333 1.429.86 1.887A8.035 8.035 0 0 1 4.387 1.86 2.5 2.5 0 0 0 0 3.5zM13.5 1c-.753 0-1.429.333-1.887.86a8.035 8.035 0 0 1 3.527 3.527A2.5 2.5 0 0 0 13.5 1z"></path>
                                  </svg>
															<p class="mb-0 fw-light text-dark fs--1">${coo[1].cooStart}
																~ ${coo[1].cooEnd}</p>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-6 mb-4">
										<div class="card h-100 shadow px-2 px-lg-3 card-span pt-4">
											<div class="text-center text-md-start card-hover">
												<div class="card-body">
													<div class="d-flex align-items-center">
														<span
															class="badge bg-soft-primary text-primary fs--1 fw-light p-3 rounded-1"><span
															class="fw-medium fs-1 mb-2">${coo[3].cooDate }</span></span>
														<h6 class="fw-light fs-1 fs-lg-2 text-start ms-3">${coo[3].cooTitle }</h6>
													</div>
													<p class="mt-4 mb-md-0 mb-lg-3 fw-light lh-base text-start">${coo[3].cooContent }</p>
													<div class="d-flex flex-between-center">
														<div class="d-flex align-items-center">
															<a class="btn btn-lg ps-0 text-primary fw-light fs--1"
																href="#" role="button">Learn more <svg
																	class="bi bi-arrow-right-short"
																	xmlns="http://www.w3.org/2000/svg" width="25"
																	height="25" fill="currentColor" viewBox="0 0 16 16">
                                      <path fill-rule="evenodd"
																		d="M4 8a.5.5 0 0 1 .5-.5h5.793L8.146 5.354a.5.5 0 1 1 .708-.708l3 3a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708-.708L10.293 8.5H4.5A.5.5 0 0 1 4 8z"></path>
                                    </svg></a>
														</div>
														<div class="d-flex align-items-center">
															<svg class="bi bi-alarm me-2"
																xmlns="http://www.w3.org/2000/svg" width="16"
																height="16" fill="currentColor" viewBox="0 0 16 16">
                                    <path
																	d="M8.5 5.5a.5.5 0 0 0-1 0v3.362l-1.429 2.38a.5.5 0 1 0 .858.515l1.5-2.5A.5.5 0 0 0 8.5 9V5.5z"></path>
                                    <path
																	d="M6.5 0a.5.5 0 0 0 0 1H7v1.07a7.001 7.001 0 0 0-3.273 12.474l-.602.602a.5.5 0 0 0 .707.708l.746-.746A6.97 6.97 0 0 0 8 16a6.97 6.97 0 0 0 3.422-.892l.746.746a.5.5 0 0 0 .707-.708l-.601-.602A7.001 7.001 0 0 0 9 2.07V1h.5a.5.5 0 0 0 0-1h-3zm1.038 3.018a6.093 6.093 0 0 1 .924 0 6 6 0 1 1-.924 0zM0 3.5c0 .753.333 1.429.86 1.887A8.035 8.035 0 0 1 4.387 1.86 2.5 2.5 0 0 0 0 3.5zM13.5 1c-.753 0-1.429.333-1.887.86a8.035 8.035 0 0 1 3.527 3.527A2.5 2.5 0 0 0 13.5 1z"></path>
                                  </svg>
															<p class="mb-0 fw-light text-dark fs--1">${coo[2].cooStart}
																~ ${coo[2].cooEnd}</p>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="carousel-item" data-bs-interval="3000">
								<div class="row h-100 justify-content-center">
									<div class="col-md-6 mb-4">
										<div class="card h-100 shadow px-2 px-lg-3 card-span pt-4">
											<div class="text-center text-md-start card-hover">
												<div class="card-body">
													<div class="d-flex align-items-center">
														<span
															class="badge bg-soft-primary text-primary fs--1 fw-light p-3 rounded-1"><span
															class="fw-medium fs-1 mb-2">${coo[4].cooDate }</span></span>
														<h6 class="fw-light fs-1 fs-lg-2 text-start ms-3">${coo[4].cooTitle }</h6>
													</div>
													<p class="mt-4 mb-md-0 mb-lg-3 fw-light lh-base text-start">${coo[4].cooContent}</p>
													<div class="d-flex flex-between-center">
														<div class="d-flex align-items-center">
															<a class="btn btn-lg ps-0 text-primary fw-light fs--1"
																href="#" role="button">Learn more <svg
																	class="bi bi-arrow-right-short"
																	xmlns="http://www.w3.org/2000/svg" width="25"
																	height="25" fill="currentColor" viewBox="0 0 16 16">
                                      <path fill-rule="evenodd"
																		d="M4 8a.5.5 0 0 1 .5-.5h5.793L8.146 5.354a.5.5 0 1 1 .708-.708l3 3a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708-.708L10.293 8.5H4.5A.5.5 0 0 1 4 8z"></path>
                                    </svg></a>
														</div>
														<div class="d-flex align-items-center">
															<svg class="bi bi-alarm me-2"
																xmlns="http://www.w3.org/2000/svg" width="16"
																height="16" fill="currentColor" viewBox="0 0 16 16">
                                    <path
																	d="M8.5 5.5a.5.5 0 0 0-1 0v3.362l-1.429 2.38a.5.5 0 1 0 .858.515l1.5-2.5A.5.5 0 0 0 8.5 9V5.5z"></path>
                                    <path
																	d="M6.5 0a.5.5 0 0 0 0 1H7v1.07a7.001 7.001 0 0 0-3.273 12.474l-.602.602a.5.5 0 0 0 .707.708l.746-.746A6.97 6.97 0 0 0 8 16a6.97 6.97 0 0 0 3.422-.892l.746.746a.5.5 0 0 0 .707-.708l-.601-.602A7.001 7.001 0 0 0 9 2.07V1h.5a.5.5 0 0 0 0-1h-3zm1.038 3.018a6.093 6.093 0 0 1 .924 0 6 6 0 1 1-.924 0zM0 3.5c0 .753.333 1.429.86 1.887A8.035 8.035 0 0 1 4.387 1.86 2.5 2.5 0 0 0 0 3.5zM13.5 1c-.753 0-1.429.333-1.887.86a8.035 8.035 0 0 1 3.527 3.527A2.5 2.5 0 0 0 13.5 1z"></path>
                                  </svg>
															<p class="mb-0 fw-light text-dark fs--1">${coo[3].cooStart}
																~ ${coo[3].cooEnd}</p>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-6 mb-4">
										<div class="card h-100 shadow px-2 px-lg-3 card-span pt-4">
											<div class="text-center text-md-start card-hover">
												<div class="card-body">
													<div class="d-flex align-items-center">
														<span
															class="badge bg-soft-primary text-primary fs--1 fw-light p-3 rounded-1"><span
															class="fw-medium fs-1 mb-2">${coo[5].cooDate }</span></span>
														<h6 class="fw-light fs-1 fs-lg-2 text-start ms-3">Macquarie
															University, Sydney, Australia</h6>
													</div>
													<p class="mt-4 mb-md-0 mb-lg-3 fw-light lh-base text-start">Maiores
														voluptas laboriosam non dolorum perferendis fuga repellat
														aut. Blanditiis quos in minus. Voluptatum quia quia
														voluptas voluptatem vero ex possimus.</p>
													<div class="d-flex flex-between-center">
														<div class="d-flex align-items-center">
															<a class="btn btn-lg ps-0 text-primary fw-light fs--1"
																href="#" role="button">Learn more <svg
																	class="bi bi-arrow-right-short"
																	xmlns="http://www.w3.org/2000/svg" width="25"
																	height="25" fill="currentColor" viewBox="0 0 16 16">
                                      <path fill-rule="evenodd"
																		d="M4 8a.5.5 0 0 1 .5-.5h5.793L8.146 5.354a.5.5 0 1 1 .708-.708l3 3a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708-.708L10.293 8.5H4.5A.5.5 0 0 1 4 8z"></path>
                                    </svg></a>
														</div>
														<div class="d-flex align-items-center">
															<svg class="bi bi-alarm me-2"
																xmlns="http://www.w3.org/2000/svg" width="16"
																height="16" fill="currentColor" viewBox="0 0 16 16">
                                    <path
																	d="M8.5 5.5a.5.5 0 0 0-1 0v3.362l-1.429 2.38a.5.5 0 1 0 .858.515l1.5-2.5A.5.5 0 0 0 8.5 9V5.5z"></path>
                                    <path
																	d="M6.5 0a.5.5 0 0 0 0 1H7v1.07a7.001 7.001 0 0 0-3.273 12.474l-.602.602a.5.5 0 0 0 .707.708l.746-.746A6.97 6.97 0 0 0 8 16a6.97 6.97 0 0 0 3.422-.892l.746.746a.5.5 0 0 0 .707-.708l-.601-.602A7.001 7.001 0 0 0 9 2.07V1h.5a.5.5 0 0 0 0-1h-3zm1.038 3.018a6.093 6.093 0 0 1 .924 0 6 6 0 1 1-.924 0zM0 3.5c0 .753.333 1.429.86 1.887A8.035 8.035 0 0 1 4.387 1.86 2.5 2.5 0 0 0 0 3.5zM13.5 1c-.753 0-1.429.333-1.887.86a8.035 8.035 0 0 1 3.527 3.527A2.5 2.5 0 0 0 13.5 1z"></path>
                                  </svg>
															<p class="mb-0 fw-light text-dark fs--1">${coo[5].cooStart}
																~ ${coo[5].cooEnd}</p>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="carousel-item">
								<div class="row h-100 justify-content-center">
									<div class="col-md-6 mb-4">
										<div class="card h-100 shadow px-2 px-lg-3 card-span pt-4">
											<div class="text-center text-md-start card-hover">
												<div class="card-body">
													<div class="d-flex align-items-center">
														<span
															class="badge bg-soft-primary text-primary fs--1 fw-light p-3 rounded-1"><span
															class="fw-medium fs-1 mb-2">${coo[6].cooDate }</span></span>
														<h6 class="fw-light fs-1 fs-lg-2 text-start ms-3">${coo[6].cooTitle }</h6>
													</div>
													<p class="mt-4 mb-md-0 mb-lg-3 fw-light lh-base text-start">${coo[6].cooContent }</p>
													<div class="d-flex flex-between-center">
														<div class="d-flex align-items-center">
															<a class="btn btn-lg ps-0 text-primary fw-light fs--1"
																href="#" role="button">Learn more <svg
																	class="bi bi-arrow-right-short"
																	xmlns="http://www.w3.org/2000/svg" width="25"
																	height="25" fill="currentColor" viewBox="0 0 16 16">
                                      <path fill-rule="evenodd"
																		d="M4 8a.5.5 0 0 1 .5-.5h5.793L8.146 5.354a.5.5 0 1 1 .708-.708l3 3a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708-.708L10.293 8.5H4.5A.5.5 0 0 1 4 8z"></path>
                                    </svg></a>
														</div>
														<div class="d-flex align-items-center">
															<svg class="bi bi-alarm me-2"
																xmlns="http://www.w3.org/2000/svg" width="16"
																height="16" fill="currentColor" viewBox="0 0 16 16">
                                    <path
																	d="M8.5 5.5a.5.5 0 0 0-1 0v3.362l-1.429 2.38a.5.5 0 1 0 .858.515l1.5-2.5A.5.5 0 0 0 8.5 9V5.5z"></path>
                                    <path
																	d="M6.5 0a.5.5 0 0 0 0 1H7v1.07a7.001 7.001 0 0 0-3.273 12.474l-.602.602a.5.5 0 0 0 .707.708l.746-.746A6.97 6.97 0 0 0 8 16a6.97 6.97 0 0 0 3.422-.892l.746.746a.5.5 0 0 0 .707-.708l-.601-.602A7.001 7.001 0 0 0 9 2.07V1h.5a.5.5 0 0 0 0-1h-3zm1.038 3.018a6.093 6.093 0 0 1 .924 0 6 6 0 1 1-.924 0zM0 3.5c0 .753.333 1.429.86 1.887A8.035 8.035 0 0 1 4.387 1.86 2.5 2.5 0 0 0 0 3.5zM13.5 1c-.753 0-1.429.333-1.887.86a8.035 8.035 0 0 1 3.527 3.527A2.5 2.5 0 0 0 13.5 1z"></path>
                                  </svg>
															<p class="mb-0 fw-light text-dark fs--1">${coo[6].cooStart}
																~ ${coo[6].cooEnd}</p>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-6 mb-4">
										<div class="card h-100 shadow px-2 px-lg-3 card-span pt-4">
											<div class="text-center text-md-start card-hover">
												<div class="card-body">
													<div class="d-flex align-items-center">
														<span
															class="badge bg-soft-primary text-primary fs--1 fw-light p-3 rounded-1"><span
															class="fw-medium fs-1 mb-2">${coo[3].cooDate }</span></span>
														<h6 class="fw-light fs-1 fs-lg-2 text-start ms-3">${coo[3].cooTitle }</h6>
													</div>
													<p class="mt-4 mb-md-0 mb-lg-3 fw-light lh-base text-start">${coo[3].cooContent }</p>
													<div class="d-flex flex-between-center">
														<div class="d-flex align-items-center">
															<a class="btn btn-lg ps-0 text-primary fw-light fs--1"
																href="#" role="button">Learn more <svg
																	class="bi bi-arrow-right-short"
																	xmlns="http://www.w3.org/2000/svg" width="25"
																	height="25" fill="currentColor" viewBox="0 0 16 16">
                                      <path fill-rule="evenodd"
																		d="M4 8a.5.5 0 0 1 .5-.5h5.793L8.146 5.354a.5.5 0 1 1 .708-.708l3 3a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708-.708L10.293 8.5H4.5A.5.5 0 0 1 4 8z"></path>
                                    </svg></a>
														</div>
														<div class="d-flex align-items-center">
															<svg class="bi bi-alarm me-2"
																xmlns="http://www.w3.org/2000/svg" width="16"
																height="16" fill="currentColor" viewBox="0 0 16 16">
                                    <path
																	d="M8.5 5.5a.5.5 0 0 0-1 0v3.362l-1.429 2.38a.5.5 0 1 0 .858.515l1.5-2.5A.5.5 0 0 0 8.5 9V5.5z"></path>
                                    <path
																	d="M6.5 0a.5.5 0 0 0 0 1H7v1.07a7.001 7.001 0 0 0-3.273 12.474l-.602.602a.5.5 0 0 0 .707.708l.746-.746A6.97 6.97 0 0 0 8 16a6.97 6.97 0 0 0 3.422-.892l.746.746a.5.5 0 0 0 .707-.708l-.601-.602A7.001 7.001 0 0 0 9 2.07V1h.5a.5.5 0 0 0 0-1h-3zm1.038 3.018a6.093 6.093 0 0 1 .924 0 6 6 0 1 1-.924 0zM0 3.5c0 .753.333 1.429.86 1.887A8.035 8.035 0 0 1 4.387 1.86 2.5 2.5 0 0 0 0 3.5zM13.5 1c-.753 0-1.429.333-1.887.86a8.035 8.035 0 0 1 3.527 3.527A2.5 2.5 0 0 0 13.5 1z"></path>
                                  </svg>
															<p class="mb-0 fw-light text-dark fs--1">${coo[2].cooStart}
																~ ${coo[2].cooEnd}</p>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row mt-3 flex-center">
							<div class="col-auto position-relative z-index-2">
								<ol class="carousel-indicators">
									<li class="active mx-2" data-bs-target="#carouselEvents"
										data-bs-slide-to="0"></li>
									<li class="mx-2" data-bs-target="#carouselEvents"
										data-bs-slide-to="1"></li>
									<li class="mx-2" data-bs-target="#carouselEvents"
										data-bs-slide-to="2"></li>
									<li class="mx-2" data-bs-target="#carouselEvents"
										data-bs-slide-to="3"></li>
								</ol>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- end of .container-->

	</section>
	<!-- <section> close ============================-->
	<!-- ============================================-->




	<!-- ============================================-->
	<!-- <section> begin ============================-->
	<section id="country">
		<c:set value="${eprodList}" var="ep"></c:set>
		<div class="container">
			<div class="row">
				<div class="col-lg-7 mx-auto text-center my-5">
					<h5 class="fw-light fs-3 fs-lg-5 lh-sm mb-3">eXpert</h5>
					<p class="mb-3">궁금한 것들을 전문가에게 질문해보아요.</p>
				</div>
			</div>
			<div class="row flex-center h-100">
				<div class="col-xl-9">
					<div class="carousel slide" id="carouselCountries"
						data-bs-ride="carousel">
						<div class="carousel-inner">
							<div class="carousel-item active" data-bs-interval="10000">
								<div class="row h-100 justify-content-center">
									<div class="col-md-4 mb-4">
										<div class="card h-100 shadow card-span bg-100"
											style="text-align: center; color: black; font-weight: bold;">
											<div class="card-body">
												<h5
													style="text-align: center; color: black; margin-top: 15%;"
													" class="fs-lg-1 ">${ep[0].eprodName }</h5>
												<a class="btn btn-lg ps-0 pe-3 text-primary"
													href="${cPath}/expert/prod/${ep[0].eprodNum}" role="button">View
													more <path fill-rule="evenodd"
														d="M4 8a.5.5 0 0 1 .5-.5h5.793L8.146 5.354a.5.5 0 1 1 .708-.708l3 3a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708-.708L10.293 8.5H4.5A.5.5 0 0 1 4 8z"></path>
													</svg>
												</a>
											</div>
										</div>
									</div>
									<div class="col-md-4 mb-4">
										<div class="card h-100 shadow card-span bg-100"
											style="text-align: center; color: black; font-weight: bold;">
											<div class="card-body">
												<h5
													style="text-align: center; color: black; margin-top: 15%;"
													" class="fs-lg-1 ">${ep[1].eprodName }</h5>
												<a class="btn btn-lg ps-0 pe-3 text-primary"
													href="${cPath}/expert/prod/${ep[1].eprodNum}" role="button">View
													more <path fill-rule="evenodd"
														d="M4 8a.5.5 0 0 1 .5-.5h5.793L8.146 5.354a.5.5 0 1 1 .708-.708l3 3a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708-.708L10.293 8.5H4.5A.5.5 0 0 1 4 8z"></path>
													</svg>
												</a>
											</div>
										</div>
									</div>
									<div class="col-md-4 mb-4">
										<div class="card h-100 shadow card-span bg-100"
											style="text-align: center; color: black; font-weight: bold;">
											<div class="card-body">
												<h5
													style="text-align: center; color: black; margin-top: 15%;"
													" class="fs-lg-1 ">${ep[2].eprodName }</h5>
												<a class="btn btn-lg ps-0 pe-3 text-primary"
													href="${cPath}/expert/prod/${ep[2].eprodNum}" role="button">View
													more <path fill-rule="evenodd"
														d="M4 8a.5.5 0 0 1 .5-.5h5.793L8.146 5.354a.5.5 0 1 1 .708-.708l3 3a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708-.708L10.293 8.5H4.5A.5.5 0 0 1 4 8z"></path>
													</svg>
												</a>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="carousel-item" data-bs-interval="5000">
								<div class="row h-100 justify-content-center">
									<div class="col-md-4 mb-4">
										<div class="card h-100 shadow card-span bg-100"
											style="text-align: center; color: black;">
											<div class="card-body">
												<h5
													style="text-align: center; color: black; margin-top: 15%;"
													" class="fs-lg-1 ">${ep[3].eprodName }</h5>
												<a class="btn btn-lg ps-0 pe-3 text-primary"
													href="${cPath}/expert/prod/${ep[3].eprodNum}" role="button">View
													more <path fill-rule="evenodd"
														d="M4 8a.5.5 0 0 1 .5-.5h5.793L8.146 5.354a.5.5 0 1 1 .708-.708l3 3a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708-.708L10.293 8.5H4.5A.5.5 0 0 1 4 8z"></path>
													</svg>
												</a>
											</div>
										</div>
									</div>
									<div class="col-md-4 mb-4">
										<div class="card h-100 shadow card-span bg-100"
											style="text-align: center; color: black;">
											<div class="card-body">
												<h5
													style="text-align: center; color: black; margin-top: 15%;"
													" class="fs-lg-1 ">${ep[4].eprodName }</h5>
												<a class="btn btn-lg ps-0 pe-3 text-primary"
													href="${cPath}/expert/prod/${ep[4].eprodNum}" role="button">View
													more <path fill-rule="evenodd"
														d="M4 8a.5.5 0 0 1 .5-.5h5.793L8.146 5.354a.5.5 0 1 1 .708-.708l3 3a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708-.708L10.293 8.5H4.5A.5.5 0 0 1 4 8z"></path>
													</svg>
												</a>
											</div>
										</div>
									</div>
									<div class="col-md-4 mb-4">
										<div class="card h-100 shadow card-span bg-100"
											style="text-align: center; color: black;">
											<div class="card-body">
												<h5
													style="text-align: center; color: black; margin-top: 15%;"
													" class="fs-lg-1 ">${ep[5].eprodName }</h5>
												<a class="btn btn-lg ps-0 pe-3 text-primary"
													href="${cPath}/expert/prod/${ep[5].eprodNum}" role="button">View
													more <path fill-rule="evenodd"
														d="M4 8a.5.5 0 0 1 .5-.5h5.793L8.146 5.354a.5.5 0 1 1 .708-.708l3 3a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708-.708L10.293 8.5H4.5A.5.5 0 0 1 4 8z"></path>
													</svg>
												</a>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="carousel-item" data-bs-interval="3000">
								<div class="row h-100 justify-content-center">
									<div class="col-md-4 mb-4">
										<div class="card h-100 shadow card-span bg-100"
											style="text-align: center; color: black; font-weight: bold;">
											<div class="card-body">
												<h5
													style="text-align: center; color: black; margin-top: 15%;"
													" class="fs-lg-1 ">${ep[6].eprodName }</h5>
												<a class="btn btn-lg ps-0 pe-3 text-primary"
													href="${cPath}/expert/prod/${ep[6].eprodNum}" role="button">View
													more <path fill-rule="evenodd"
														d="M4 8a.5.5 0 0 1 .5-.5h5.793L8.146 5.354a.5.5 0 1 1 .708-.708l3 3a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708-.708L10.293 8.5H4.5A.5.5 0 0 1 4 8z"></path>
													</svg>
												</a>
											</div>
										</div>
									</div>
									<div class="col-md-4 mb-4">
										<div class="card h-100 shadow card-span bg-100"
											style="text-align: center; color: black; font-weight: bold;">
											<div class="card-body">
												<h5
													style="text-align: center; color: black; margin-top: 15%;"
													" class="fs-lg-1 ">${ep[7].eprodName }</h5>
												<a class="btn btn-lg ps-0 pe-3 text-primary"
													href="${cPath}/expert/prod/${ep[7].eprodNum}" role="button">View
													more <path fill-rule="evenodd"
														d="M4 8a.5.5 0 0 1 .5-.5h5.793L8.146 5.354a.5.5 0 1 1 .708-.708l3 3a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708-.708L10.293 8.5H4.5A.5.5 0 0 1 4 8z"></path>
													</svg>
												</a>
											</div>
										</div>
									</div>
									<div class="col-md-4 mb-4">
										<div class="card h-100 shadow card-span bg-100"
											style="text-align: center; color: black; font-weight: bold;">
											<div class="card-body">
												<h5
													style="text-align: center; color: black; margin-top: 15%;"
													" class="fs-lg-1 ">${ep[8].eprodName }</h5>
												<a class="btn btn-lg ps-0 pe-3 text-primary"
													href="${cPath}/expert/prod/${ep[8].eprodNum}" role="button">View
													more <path fill-rule="evenodd"
														d="M4 8a.5.5 0 0 1 .5-.5h5.793L8.146 5.354a.5.5 0 1 1 .708-.708l3 3a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708-.708L10.293 8.5H4.5A.5.5 0 0 1 4 8z"></path>
													</svg>
												</a>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="carousel-item">
								<div class="row h-100 justify-content-center">
									<div class="col-md-4 mb-4">
										<div class="card h-100 shadow card-span bg-100"
											style="text-align: center; color: black; font-weight: bold;">
											<div class="card-body">
												<h5
													style="text-align: center; color: black; margin-top: 15%;"
													" class="fs-lg-1 ">${ep[9].eprodName }</h5>
												<a class="btn btn-lg ps-0 pe-3 text-primary"
													href="${cPath}/expert/prod/${ep[9].eprodNum}" role="button">View
													more <path fill-rule="evenodd"
														d="M4 8a.5.5 0 0 1 .5-.5h5.793L8.146 5.354a.5.5 0 1 1 .708-.708l3 3a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708-.708L10.293 8.5H4.5A.5.5 0 0 1 4 8z"></path>
													</svg>
												</a>
											</div>
										</div>
									</div>
									<div class="col-md-4 mb-4">
										<div class="card h-100 shadow card-span bg-100"
											style="text-align: center; color: black; font-weight: bold;">
											<div class="card-body">
												<h5
													style="text-align: center; color: black; margin-top: 15%;"
													" class="fs-lg-1 ">${ep[10].eprodName }</h5>
												<a class="btn btn-lg ps-0 pe-3 text-primary"
													href="${cPath}/expert/prod/${ep[10].eprodNum}"
													role="button">View more <path fill-rule="evenodd"
														d="M4 8a.5.5 0 0 1 .5-.5h5.793L8.146 5.354a.5.5 0 1 1 .708-.708l3 3a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708-.708L10.293 8.5H4.5A.5.5 0 0 1 4 8z"></path>
													</svg></a>
											</div>
										</div>
									</div>
									<div class="col-md-4 mb-4">
										<div class="card h-100 shadow card-span bg-100"
											style="text-align: center; color: black; font-weight: bold;">
											<div class="card-body">
												<h5
													style="text-align: center; color: black; margin-top: 15%;"
													" class="fs-lg-1 ">${ep[11].eprodName }</h5>
												<a class="btn btn-lg ps-0 pe-3 text-primary"
													href="${cPath}/expert/prod/${ep[11].eprodNum}"
													role="button">View more <path fill-rule="evenodd"
														d="M4 8a.5.5 0 0 1 .5-.5h5.793L8.146 5.354a.5.5 0 1 1 .708-.708l3 3a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708-.708L10.293 8.5H4.5A.5.5 0 0 1 4 8z"></path>
													</svg></a>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row mt-3 mb-7 flex-center">
							<div class="col-auto position-relative z-index-2">
								<ol class="carousel-indicators">
									<li class="active mx-2" data-bs-target="#carouselCountries"
										data-bs-slide-to="0"></li>
									<li class="mx-2" data-bs-target="#carouselCountries"
										data-bs-slide-to="1"></li>
									<li class="mx-2" data-bs-target="#carouselCountries"
										data-bs-slide-to="2"></li>
									<li class="mx-2" data-bs-target="#carouselCountries"
										data-bs-slide-to="3"></li>
								</ol>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- end of .container-->

	</section>
	<!-- <section> close ============================-->
	<!-- ============================================-->




	<!-- ============================================-->
	<!-- <section> begin ============================-->
	<section id="testimonial">

		<div class="container">
			<div class="row">
				<div class="col-lg-7 mx-auto text-center my-5">
					<h5 class="fw-light fs-3 fs-lg-5 lh-sm mb-4">공지사항</h5>
				</div>
			</div>
			<c:set value="${noticeList }" var="notice" />
			<div class="row flex-center h-100">
				<div class="col-xl-9">
					<div class="carousel slide" id="carouselTestimonials"
						data-bs-ride="carousel">
						<div class="carousel-inner">
							<div class="carousel-item active" data-bs-interval="10000">
								<div class="row h-100 justify-content-center">
									<div class="col-md-6 mb-4">
										<div class="card h-100 shadow card-span p-3">
											<div class="card-body">
												<div class="d-flex align-items-center">
													<div class="flex-1 ms-4">
														<h6 class="fw-light fs-lg-1 mb-1">${notice[0].notiTitle }</h6>
														<p class="fw-normal mb-0 text-800">${notice[0].notiRank }</p>
													</div>
												</div>
												<p class="mb-0 mt-4 fw-light lh-lg">${notice[0].notiContent }</p>
											</div>
										</div>
									</div>
									<div class="col-md-6 mb-4">
										<div class="card h-100 shadow card-span p-3">
											<div class="card-body">
												<div class="d-flex align-items-center">
													<div class="flex-1 ms-4">
														<h6 class="fw-light fs-lg-1 mb-1">${notice[1].notiTitle }</h6>
														<p class="fw-normal mb-0 text-800">${notice[1].notiRank }</p>
													</div>
												</div>
												<p class="mb-0 mt-4 fw-light lh-lg">${notice[1].notiContent }</p>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="carousel-item" data-bs-interval="5000">
								<div class="row h-100 justify-content-center">
									<div class="col-md-6 mb-4">
										<div class="card h-100 shadow card-span p-3">
											<div class="card-body">
												<div class="d-flex align-items-center">
													<div class="flex-1 ms-4">
														<h6 class="fw-light fs-lg-1 mb-1">${notice[2].notiTitle }</h6>
														<p class="fw-normal mb-0 text-800">${notice[2].notiRank }</p>
													</div>
												</div>
												<p class="mb-0 mt-4 fw-light lh-lg">${notice[2].notiContent }</p>
											</div>
										</div>
									</div>
									<div class="col-md-6 mb-4">
										<div class="card h-100 shadow card-span p-3">
											<div class="card-body">
												<div class="d-flex align-items-center">
													<div class="flex-1 ms-4">
														<h6 class="fw-light fs-lg-1 mb-1">${notice[3].notiTitle }</h6>
														<p class="fw-normal mb-0 text-800">${notice[3].notiRank }</p>
													</div>
												</div>
												<p class="mb-0 mt-4 fw-light lh-lg">${notice[3].notiContent }</p>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="carousel-item" data-bs-interval="3000">
								<div class="row h-100 justify-content-center">
									<div class="col-md-6 mb-4">
										<div class="card h-100 shadow card-span p-3">
											<div class="card-body">
												<div class="d-flex align-items-center">
													<div class="flex-1 ms-4">
														<h6 class="fw-light fs-lg-1 mb-1">${notice[4].notiTitle }</h6>
														<p class="fw-normal mb-0 text-800">${notice[4].notiRank }</p>
													</div>
												</div>
												<p class="mb-0 mt-4 fw-light lh-lg">${notice[4].notiContent }</p>
											</div>
										</div>
									</div>
									<div class="col-md-6 mb-4">
										<div class="card h-100 shadow card-span p-3">
											<div class="card-body">
												<div class="d-flex align-items-center">
													<div class="flex-1 ms-4">
														<h6 class="fw-light fs-lg-1 mb-1">${notice[5].notiTitle }</h6>
														<p class="fw-normal mb-0 text-800">${notice[5].notiRank }</p>
													</div>
												</div>
												<p class="mb-0 mt-4 fw-light lh-lg">${notice[5].notiContent }</p>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="carousel-item">
								<div class="row h-100 justify-content-center">
									<div class="col-md-6 mb-4">
										<div class="card h-100 shadow card-span p-3">
											<div class="card-body">
												<div class="d-flex align-items-center">
													<div class="flex-1 ms-4">
														<h6 class="fw-light fs-lg-1 mb-1">${notice[6].notiTitle }</h6>
														<p class="fw-normal mb-0 text-800">${notice[6].notiRank }</p>
													</div>
												</div>
												<p class="mb-0 mt-4 fw-light lh-lg">${notice[6].notiContent }</p>
											</div>
										</div>
									</div>
									<div class="col-md-6 mb-4">
										<div class="card h-100 shadow card-span p-3">
											<div class="card-body">
												<div class="d-flex align-items-center">
													<div class="flex-1 ms-4">
														<h6 class="fw-light fs-lg-1 mb-1">${notice[7].notiTitle }</h6>
														<p class="fw-normal mb-0 text-800">${notice[7].notiRank }</p>
													</div>
												</div>
												<p class="mb-0 mt-4 fw-light lh-lg">${notice[7].notiContent }</p>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row mt-5 flex-center">
							<div class="col-auto position-relative z-index-2">
								<ol class="carousel-indicators">
									<li class="active mx-2" data-bs-target="#carouselTestimonials"
										data-bs-slide-to="0"></li>
									<li class="mx-2" data-bs-target="#carouselTestimonials"
										data-bs-slide-to="1"></li>
									<li class="mx-2" data-bs-target="#carouselTestimonials"
										data-bs-slide-to="2"></li>
									<li class="mx-2" data-bs-target="#carouselTestimonials"
										data-bs-slide-to="3"></li>
								</ol>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- end of .container-->

	</section>
	<!-- <section> close ============================-->
	<!-- ============================================-->







	<!-- ============================================-->
	<!-- <section> begin ============================-->
	<section class="py-0 bg-primary">

		<div class="container">
			<div class="row justify-content-between pb-2 pt-8">
				<div class="col-12 col-lg-auto mb-5 mb-lg-0">
					<a class="d-flex align-items-center fw-semi-bold fs-3" href="#">
						<img class="me-3"
						src="${cPath}/resources/ex/assets/img/gallery/footer-logo.png"
						alt="..." />
					</a>
					<p class="my-3 text-100 fw-light">
						Concord Royal Court (3rd floor)<br />Dhanmondi, Dhaka 1209,
						Bangladesh.
					</p>
				</div>
				<div class="col-auto mb-3">
					<ul class="list-unstyled mb-md-4 mb-lg-0">
						<li class="mb-3"><a
							class="text-100 fw-light text-decoration-none" href="#!">About
								Us</a></li>
						<li class="mb-3"><a
							class="text-100 fw-light text-decoration-none" href="#!">공지사항</a></li>
						<li class="mb-3"><a
							class="text-100 fw-light text-decoration-none" href="#!">블로그
								이용안내</a></li>
						<li class="mb-3"><a
							class="text-100 fw-light text-decoration-none" href="#!">협업툴
								이용안내</a></li>
					</ul>
				</div>
				<div class="col-auto mb-3">
					<ul class="list-unstyled mb-md-4 mb-lg-0">
						<li class="mb-3"><a
							class="text-100 fw-light text-decoration-none" href="#!">EXPERT
								이용안내</a></li>
						<li class="mb-3"><a
							class="text-100 fw-light text-decoration-none" href="#!">전문가
								신청</a></li>
						<li class="mb-3"><a
							class="text-100 fw-light text-decoration-none" href="#!">문의</a></li>
						<li class="mb-3"><a
							class="text-100 fw-light text-decoration-none" href="#!">서비스
								이용안내</a></li>
					</ul>
				</div>
				<div class="col-auto mb-4 d-flex align-items-end">
					<ul class="list-unstyled list-inline mb-0">
						<li class="list-inline-item me-3"><a
							class="text-decoration-none" href="#!"> <svg
									class="bi bi-facebook" xmlns="http://www.w3.org/2000/svg"
									width="20" height="20" fill="#ffffff" viewBox="0 0 16 16">
                      <path
										d="M16 8.049c0-4.446-3.582-8.05-8-8.05C3.58 0-.002 3.603-.002 8.05c0 4.017 2.926 7.347 6.75 7.951v-5.625h-2.03V8.05H6.75V6.275c0-2.017 1.195-3.131 3.022-3.131.876 0 1.791.157 1.791.157v1.98h-1.009c-.993 0-1.303.621-1.303 1.258v1.51h2.218l-.354 2.326H9.25V16c3.824-.604 6.75-3.934 6.75-7.951z"> </path>
                    </svg></a></li>
						<li class="list-inline-item me-3"><a href="#!"> <svg
									class="bi bi-twitter" xmlns="http://www.w3.org/2000/svg"
									width="20" height="20" fill="#ffffff" viewBox="0 0 16 16">
                      <path
										d="M5.026 15c6.038 0 9.341-5.003 9.341-9.334 0-.14 0-.282-.006-.422A6.685 6.685 0 0 0 16 3.542a6.658 6.658 0 0 1-1.889.518 3.301 3.301 0 0 0 1.447-1.817 6.533 6.533 0 0 1-2.087.793A3.286 3.286 0 0 0 7.875 6.03a9.325 9.325 0 0 1-6.767-3.429 3.289 3.289 0 0 0 1.018 4.382A3.323 3.323 0 0 1 .64 6.575v.045a3.288 3.288 0 0 0 2.632 3.218 3.203 3.203 0 0 1-.865.115 3.23 3.23 0 0 1-.614-.057 3.283 3.283 0 0 0 3.067 2.277A6.588 6.588 0 0 1 .78 13.58a6.32 6.32 0 0 1-.78-.045A9.344 9.344 0 0 0 5.026 15z"></path>
                    </svg></a></li>
						<li class="list-inline-item me-3"><a href="#!"> <svg
									class="bi bi-instagram" xmlns="http://www.w3.org/2000/svg"
									width="20" height="20" fill="#ffffff" viewBox="0 0 16 16">
                      <path
										d="M8 0C5.829 0 5.556.01 4.703.048 3.85.088 3.269.222 2.76.42a3.917 3.917 0 0 0-1.417.923A3.927 3.927 0 0 0 .42 2.76C.222 3.268.087 3.85.048 4.7.01 5.555 0 5.827 0 8.001c0 2.172.01 2.444.048 3.297.04.852.174 1.433.372 1.942.205.526.478.972.923 1.417.444.445.89.719 1.416.923.51.198 1.09.333 1.942.372C5.555 15.99 5.827 16 8 16s2.444-.01 3.298-.048c.851-.04 1.434-.174 1.943-.372a3.916 3.916 0 0 0 1.416-.923c.445-.445.718-.891.923-1.417.197-.509.332-1.09.372-1.942C15.99 10.445 16 10.173 16 8s-.01-2.445-.048-3.299c-.04-.851-.175-1.433-.372-1.941a3.926 3.926 0 0 0-.923-1.417A3.911 3.911 0 0 0 13.24.42c-.51-.198-1.092-.333-1.943-.372C10.443.01 10.172 0 7.998 0h.003zm-.717 1.442h.718c2.136 0 2.389.007 3.232.046.78.035 1.204.166 1.486.275.373.145.64.319.92.599.28.28.453.546.598.92.11.281.24.705.275 1.485.039.843.047 1.096.047 3.231s-.008 2.389-.047 3.232c-.035.78-.166 1.203-.275 1.485a2.47 2.47 0 0 1-.599.919c-.28.28-.546.453-.92.598-.28.11-.704.24-1.485.276-.843.038-1.096.047-3.232.047s-2.39-.009-3.233-.047c-.78-.036-1.203-.166-1.485-.276a2.478 2.478 0 0 1-.92-.598 2.48 2.48 0 0 1-.6-.92c-.109-.281-.24-.705-.275-1.485-.038-.843-.046-1.096-.046-3.233 0-2.136.008-2.388.046-3.231.036-.78.166-1.204.276-1.486.145-.373.319-.64.599-.92.28-.28.546-.453.92-.598.282-.11.705-.24 1.485-.276.738-.034 1.024-.044 2.515-.045v.002zm4.988 1.328a.96.96 0 1 0 0 1.92.96.96 0 0 0 0-1.92zm-4.27 1.122a4.109 4.109 0 1 0 0 8.217 4.109 4.109 0 0 0 0-8.217zm0 1.441a2.667 2.667 0 1 1 0 5.334 2.667 2.667 0 0 1 0-5.334z"> </path>
                    </svg></a></li>
					</ul>
				</div>
			</div>
			<div class="row">
				<div class="col-auto mb-2">
					<p class="mb-0 fs--1 my-2 text-100">
						&copy; This template is made with&nbsp;
						<svg class="bi bi-suit-heart-fill"
							xmlns="http://www.w3.org/2000/svg" width="16" height="16"
							fill="#2b2b2b" viewBox="0 0 16 16">
                  <path
								d="M4 1c2.21 0 4 1.755 4 3.92C8 2.755 9.79 1 12 1s4 1.755 4 3.92c0 3.263-3.234 4.414-7.608 9.608a.513.513 0 0 1-.784 0C3.234 9.334 0 8.183 0 4.92 0 2.755 1.79 1 4 1z"></path>
                </svg>
						&nbsp;by&nbsp;<a class="text-100" href="https://themewagon.com/"
							target="_blank">ThemeWagon </a>
					</p>
				</div>
			</div>
		</div>
		<!-- end of .container-->

	</section>
	<!-- <section> close ============================-->
	<!-- ============================================-->


	</main>
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

	<!-- Channel Plugin Scripts -->
	<script>
		(function() {
			var w = window;
			if (w.ChannelIO) {
				return (window.console.error || window.console.log || function() {
				})('ChannelIO script included twice.');
			}
			var ch = function() {
				ch.c(arguments);
			};
			ch.q = [];
			ch.c = function(args) {
				ch.q.push(args);
			};
			w.ChannelIO = ch;
			function l() {
				if (w.ChannelIOInitialized) {
					return;
				}
				w.ChannelIOInitialized = true;
				var s = document.createElement('script');
				s.type = 'text/javascript';
				s.async = true;
				s.src = 'https://cdn.channel.io/plugin/ch-plugin-web.js';
				s.charset = 'UTF-8';
				var x = document.getElementsByTagName('script')[0];
				x.parentNode.insertBefore(s, x);
			}
			if (document.readyState === 'complete') {
				l();
			} else if (window.attachEvent) {
				window.attachEvent('onload', l);
			} else {
				window.addEventListener('DOMContentLoaded', l, false);
				window.addEventListener('load', l, false);
			}
		})();
		ChannelIO('boot', {
			"pluginKey" : "d35b9f12-1f06-48bc-98ed-c420fe902997"
		});
	</script>
	<!-- End Channel Plugin -->


	<script>
		// <a href="" class="text-reset notification-item">
		// <div class="d-flex align-items-center">
		// 	<img src="assets/images/users/avatar-6.jpg"
// 		class="me-3 rounded-circle avatar-xs" alt="user-pic">
		// 	<div class="flex-grow-1 text-truncate">
		// 		<h6 class="mt-0 mb-1">
		// 			Jonathon Joseph <span class="mb-1 text-muted fw-normal">Friend
		// 				of mine occidental.</span>
		// 		</h6>
		// 		<p class="mb-0 font-size-12">
		// 			<i class="mdi mdi-clock-outline"></i> 5 min ago
		// 		</p>
		// 	</div>
		// </div>

		let mynews = $(".mynews");

		$("#readBtn").on("click", function(event) {
			event.preventDefault();
			$.ajax({
				url : "${cPath}/readAllNews",
				method : "GET",
				dataType : "text",
				success : function(resp) {
					console.log(resp);
				}
			})
			return false;
		});

		let makeNews = function(index, news) {

			return $("<a>")
					.attr("class", "text-reset notification-item")
					.append(
							$("<div>")
									.attr("class", "d-flex align-items-center")
									.attr("data-value", news.newsNum)
									.append(
											$("<img>")
													.attr("class",
															"me-3 rounded-circle avatar-xs")
													.attr("src",
															"${cPath}/resources/images/bell.png"),
											$("<div>")
													.attr("class",
															"flex-grow-1 text-truncate")
													.append(
															$("<h6>")
																	.html(
																			news.comCodeNm)
																	.append(
																			$(
																					"span")
																					.attr(
																							"class",
																							"mb-1 text-muted fw-normal")),
															$("<p>")
																	.attr(
																			"class",
																			"mb-0 font-size-12")
																	.html(
																			news.newsDate))));
		}

		$(".NewsBtn").on("click", function() {

			$.ajax({
				url : "${cPath}/myNews",
				method : "get",
				dataType : "json",
				success : function(resp, status, jqXHR) {
					console.log(resp)
					newTag = []
					$.each(resp, function(index, news) {
						newTag.push(makeNews(index, news));
					});
					mynews.html(newTag);
				},
				error : function(jqXHR, status, error) {

				}
			});
		});
	</script>
</body>

</html>