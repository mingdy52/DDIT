<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 8. 23.      이아인      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal.realMember" var="member" />
</sec:authorize>
<c:choose>
	<c:when test="${check eq 'check'}">
		<div id="sidebar-menu">
			<ul class="metismenu list-unstyled" id="side-menu">
				<li class="menu-title">Menu</li>

				<li><a href="${cPath}/blog/blogManage/check"
					class="waves-effect"> <i class="ri-home-gear-line"></i><span
						class="badge rounded-pill bg-success float-end"></span> <span>블로그관리홈</span>
				</a></li>
			</ul>
		</div>
	</c:when>
	<c:otherwise>

		<!--- Sidemenu -->
		<div id="sidebar-menu">
			<!--  Left Menu Start -->
			<ul class="metismenu list-unstyled" id="side-menu">
				<li class="menu-title">Menu</li>

				<li><a href="${cPath}/blog/${member.getMemId()}" class="waves-effect">
						<i class="ri-home-gear-line"></i><span
						class="badge rounded-pill bg-success float-end"></span> <span>블로그홈</span>
				</a></li>

				<li><a href="${cPath}/blog/${member.getMemId()}/manage"
					href="#multiCollapseExample1" aria-expanded="false"
					aria-controls="multiCollapseExample1"> <i class=" ri-menu-line"></i>
						<span>카테고리관리</span>
				</a></li>


				<li><a href="${cPath}/blog/${member.getMemId()}/postManage"
					class=" waves-effect"> <i class="ri-file-list-line"></i> <span>글관리</span>
				</a></li>


				<li><a href="${cPath}/blog/${member.getMemId()}/replyManage"
					class=" waves-effect"> <i class=" ri-wechat-2-line"></i> <span>댓글관리</span>
				</a></li>

			</ul>
		</div>





	</c:otherwise>
</c:choose>
