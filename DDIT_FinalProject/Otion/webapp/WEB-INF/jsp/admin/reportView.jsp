<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자         수정내용
* ----------  ---------  -----------------
* 2022. 8. 17.       심민경         최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<style>
table {
	text-align: center;
}

</style>
<div>
	<c:if test="${targetClass eq 'member' }">
		<div class="input-group mb-3">
			<input id="blackReason" type="text" class="form-control" placeholder="차단 사유를 입력하세요." 
				aria-label="Recipient's username" aria-describedby="basic-addon2" onkeyup="if(window.event.keyCode==13){addBlack()}">
			<button onclick="addBlack()" class="btn btn-primary btn-rounded waves-effect waves-light">블랙리스트 추가</button>
		</div>
	</c:if>
	<c:if test="${targetClass eq 'expert' }">
		<button href="#" class="btn btn-secondary" id="button"
			onclick="depriveExp()">전문가 박탈</button>
	</c:if>
	<c:if test="${targetClass eq 'coo' || targetClass eq 'free'}">
		<button href="#" class="btn btn-secondary" id="button"
			onclick="blockBoard()">게시글 블라인드</button>
	</c:if>
</div>

<div data-simplebar="init" class="sidebar-menu-scroll mm-show">
	<div class="simplebar-wrapper" style="margin: 0px;">
		<div class="simplebar-height-auto-observer-wrapper">
			<div class="simplebar-height-auto-observer"></div>
		</div>
		<div class="simplebar-mask">
			<div class="simplebar-offset" style="right: 0px; bottom: 0px;">
				<div class="simplebar-content-wrapper"
					style="height: auto; overflow: hidden;">
					<div class="simplebar-content" style="padding: 0px;">

						<div id="sidebar-menu" class="mm-active">
							<!-- Left Menu Start -->
							<ul class="metismenu list-unstyled mm-show" id="side-menu">
							<c:set var="reportList" value="${pagingVO.dataList }" />
								<c:forEach items="${reportList }" var="rep">
										<li class="mm-active">
											<a href="javascript: void(0);" class="has-arrow waves-effect" aria-expanded="true"> 
												<i class="ri-alarm-warning-fill"></i>
												<span>${rep.repNum }</span>
											</a>
												
											<ul class="sub-menu mm-collapse mm-show" aria-expanded="true">
												<li>
													신고자 : ${rep.reporterId } 
												</li>
												<li>
													<c:out value="${rep.repContent }"></c:out>
												</li>
											</ul>
										</li>
								</c:forEach>
								</ul>
							<div class="d-flex justify-content-center pagingArea">
								${pagingVO.pagingHTMLBS }
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="simplebar-placeholder" style="width: 0px; height: 0px;"></div>
	</div>
	<div class="simplebar-track simplebar-horizontal"
		style="visibility: hidden;">
		<div class="simplebar-scrollbar"
			style="transform: translate3d(0px, 0px, 0px); display: none;"></div>
	</div>
	<div class="simplebar-track simplebar-vertical"
		style="visibility: hidden;">
		<div class="simplebar-scrollbar"
			style="transform: translate3d(0px, 0px, 0px); display: none; height: 644px;"></div>
	</div>
</div>


<form id="searchForm">
	<sec:csrfInput />
	<input type="hidden" name="page" id="page" /> 
</form>

<form id="addBlackForm" method="post">
	<sec:csrfInput />
	<input type="hidden" name="target" id="target" value="${target }"/> 
	<input type="hidden" name="reason" id="reason" /> 
	<input type="hidden" name="targetClass" id="targetClass" value="${targetClass}" />
</form>
<script src="${cPath }/resources/js/admin/reportView.js"></script>