<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<script src="${cPath }/resources/js/jquery-3.6.0.min.js"></script>
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal.realMember" var="member" />
</sec:authorize>
<div class="navbar-header">
	<div class="d-flex">
		<!-- LOGO -->
		<div class="navbar-brand-box"></div>
	</div>

	<div class="d-flex">
		<div class="dropdown d-none d-lg-inline-block ms-1">
			<button type="button" class="btn header-item noti-icon waves-effect"
				style="padding: 20px;">
				<i class="dripicons-brightness-medium"></i>
			</button>
		</div>
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
			<button type="button" class="btn header-item noti-icon waves-effect NewsBtn"
				id="page-header-notifications-dropdown" data-bs-toggle="dropdown"
				aria-expanded="false" style="padding: 20px;">
				<i class="far fa-bell"></i>
				<!--                                 <span class="noti-dot"></span> -->
			</button>
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
								<h6 class="m-0">Notifications</h6>
							</div>
							<div class="col-auto">
								<a href="#!" class="small" id="readBtn"> View All</a>
							</div>
						</div>
					</div>
					<div data-simplebar="init" style="max-height: 230px;">
						<div class="simplebar-wrapper" style="margin: 0px;">
							<div class="simplebar-height-auto-observer-wrapper">
								<div class="simplebar-height-auto-observer"></div>
							</div>
							<div class="simplebar-mask">
								<div class="simplebar-offset" style="right: -17px; bottom: 0px;">
									<div class="simplebar-content-wrapper"
										style="height: auto; overflow: hidden scroll;">
										<div class="simplebar-content" style="padding: 0px;"></div>
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

			<div class="dropdown d-inline-block user-dropdown">

				<button type="button" class="btn header-item noti-icon waves-effect"
					id="page-header-search-dropdown" data-bs-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false" style="padding: 20px;">
					<i class=" fas fa-user"></i>
				</button>
				<div class="dropdown-menu dropdown-menu-lg dropdown-menu-end">
					<div class="p-3">
						<div class="row align-items-center">
							<div class="col">
								<h6 class="m-0">${member.memName }</h6>
							</div>
							<div class="col-auto">
								<a href="#!" class="small"> Available</a>
							</div>
						</div>
					</div>
					<divdata-simplebar"> <!-- item--> <a
						href="${cPath}/member/mypage" class="text-reset notification-item">
						<div class="d-flex align-items-center">
							<div class="avatar-xs me-3 mt-1">
								<span
									class="avatar-title bg-soft-primary rounded-circle font-size-16">
									<i class="ri-user-line text-primary font-size-16"></i>
								</span>
							</div>
							<div class="flex-grow-1 text-truncate">
								<h6 class="mb-1">MY PAGE</h6>

							</div>
						</div>
					</a> <!-- item--> <a href="${cPath}/blog/${member.memId}"
						class="text-reset notification-item">
						<div class="d-flex align-items-center">
							<div class="avatar-xs me-3 mt-1">
								<span
									class="avatar-title bg-soft-warning rounded-circle font-size-16">
									<i class="ri-wallet-2-line text-warning font-size-16"></i>
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
									<i class="ri-settings-2-line text-secondary"></i>
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
									<i class="ri-lock-unlock-line text-primary"></i>
								</span>
							</div>
							<div class="flex-grow-1 text-truncate">
								<h6 class="mb-1">MY EXPERT</h6>
								<!--                                             <p class="mb-0 font-size-12">Control your privacy parameters..</p> -->
							</div>
						</div>
					</a>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- ajaxform  -->
<script src="${cPath}/resources/js/jquery.form.min.js"></script>
<script>
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
																.attr("class",
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