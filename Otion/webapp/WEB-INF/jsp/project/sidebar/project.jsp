<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- LOGO -->
<div class="navbar-brand-box">
	<div id="projList">
		<div class="dropdown">
			<c:if test="${not empty project }">
				<i class="fas fa-angle-down" style="color: white; font-size: 20px;"></i>
				<span class="teamName" id="dropdownMenuButton"
					data-bs-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false">${project.pjName }...</span>
			</c:if>
			<c:if test="${empty project }">
				<span class="teamName" id="dropdownMenuButton"
					data-bs-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false">프로젝트 홈</span>
			</c:if>
			<div class="dropdown-menu" aria-labelledby="dropdownMenuButton"
				style="">
				<a class="dropdown-item" href="${cPath }/">메인 홈페이지 가기</a>
				<c:if test="${not empty projectList }">
					<c:forEach items="${projectList }" var="project">
						<a class="dropdown-item" href="${cPath }/project/${project.pjId}/home">${project.pjName }</a>
					</c:forEach>
				</c:if>
			</div>
		</div>
	</div>
</div>
<div data-simplebar class="sidebar-menu-scroll">
	<!--- Sidemenu -->
	<div id="sidebar-menu">
		<!-- Left Menu Start -->
		<ul class="metismenu list-unstyled" id="side-menu">
			<li id = "newProjectBtn"><a href="#" class="waves-effect waves-light"
				data-bs-toggle="modal" data-bs-target="#newProject"> <i
					class="ri-add-fill"></i> <!--                                     <span class="badge rounded-pill bg-success float-end">3</span> -->
					<span>새 프로젝트 만들기</span>
			</a></li>
		</ul>
		<c:if test="${not empty project }">
			<ul class="metismenu list-unstyled" id="side-menu">
				<li class="menu-title">Menu</li>
				<li><a href="${cPath }/project" class="waves-effect"> <i
						class="ri-home-gear-line"></i> <span>내프로젝트</span>
				</a></li>
				<li><a href="${cPath }/project/${project.pjId}/myWork"
					class="waves-effect"> <i class="ri-edit-line"></i><span
						class="badge rounded-pill bg-success float-end"></span> <span>내
							업무</span>
				</a></li>
				<li><a href="${cPath }/project/${project.pjId}/gantt"
					class="waves-effect"> <i class="ri-bar-chart-horizontal-fill"></i><span
						class=""></span> <span>간트차트</span>
				</a></li>
				<li><a href="${cPath }/project/${project.pjId}/calendar"
					class="waves-effect"> <i class="ri-calendar-2-line"></i><span
						class=""></span> <span>캘린더</span>
				</a></li>
				<li><a href="${cPath }/project/${project.pjId}/folder"
					class="waves-effect"> <i class="ri-file-line"></i><span
						class=""></span> <span>파일함</span>
				</a></li>
				<li><a href="${cPath }/project/${project.pjId}/myWorkMarkList"
					class="waves-effect"> <i class="ri-bookmark-line"></i><span
						class=""></span> <span>내 북마크</span>
				</a></li>
				<li><a href="${cPath }/project/${project.pjId}/myPost"> <i
						class="ri-mac-line"></i><span class=""></span> <span>내 게시글</span></a></li>
				<li><a href="${cPath }/project/${project.pjId}/msg"
					class="waves-effect"> <i class="ri-mail-line"></i><span
						class=""></span> <span>내 메시지</span></a></li>
				<li><a href="${cPath }/project/${project.pjId}/record"
					class="waves-effect"><i class="ri-file-list-3-line"></i><span
						class=""></span> <span>내 파일 기록</span></a></li>
				<li><a href="${cPath }/project/${project.pjId}/chatting"
					class="waves-effect"><i class="ri-chat-3-line"></i><span
						class=""></span> <span>내 채팅방</span></a></li>
			</ul>
		</c:if>
		<c:if test="${empty project }">
			<ul class="metismenu list-unstyled" id="side-menu">
				<li class="menu-title">Menu</li>
				<li><a href="${cPath }/project" class="waves-effect"> <i
						class="ri-home-gear-line"></i><span
						class="badge rounded-pill bg-success float-end"></span> <span>내프로젝트</span>
				</a></li>
			</ul>
		</c:if>


	</div>
	<!-- Sidebar -->
</div>
<script>
	$('#closeBtn').on('click', function () {
	 	$("#newProjectBtn").removeClass("mm-active");
	})
</script>