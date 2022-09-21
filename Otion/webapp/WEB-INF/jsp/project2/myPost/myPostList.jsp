<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 8. 5.      고정현      최초작성
* 2022. 8. 24.     고정현    리스트 내역 조회 및 검색 기능
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<style>
.message-list li {
	position: relative;
	display : inline-block;
	width : 700px;
	height : 120px;
	cursor: default;
	vertical-align: middle;
	-webkit-transition-duration: .3s;
	transition-duration: .3s;
	border-left: 2px solid transparent;
	cursor: default;
}

.요청{
	background-color: rgba(255, 99, 132, 0.6);
}
.진행{
	background-color: rgba(54, 162, 235, 0.6);
}
.피드백{
	background-color: rgba(255, 206, 86, 0.6);
}
.완료{
	background-color: rgba(229, 255, 204, 0.6);
}
.보류{
	background-color: rgba(229, 204, 255, 0.6);
}
.rqTitle{
	padding: 8px;
	border-radius: 8px;
	color: black;
	border: 1px solid #E6E6E6;
}
</style>
<sec:authentication property="principal.realMember" var="member" />
<div class="card">
	<div class="card-body">
		<div class="d-flex justify-content-center">
			<form class="email-search p-3 " id="searchUI">
				<div class="position-relative">
					<input type="text" class="form-control" placeholder="Search..." autocomplete="off"
						name="searchName" onkeyup="if(window.event.keyCode==13){search()}">
					<a href="#"><span class="mdi mdi-magnify font-size-18"
						id="searchBtn"></span></a>
				</div>
			</form>
		</div>
	</div>
	<div class="card-body">
                                        <!-- Nav tabs -->
        <ul class="nav nav-tabs nav-tabs-custom nav-justified" role="tablist">
            <li class="nav-item">
                <a class="nav-link active" data-bs-toggle="tab" href="#home1" role="tab" aria-selected="true">
                    <span class="d-block d-sm-none"><i class="fas fa-home"></i></span>
                    <span class="d-none d-sm-block">내일정</span> 
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-bs-toggle="tab" href="#profile1" role="tab" aria-selected="false">
                    <span class="d-block d-sm-none"><i class="far fa-user"></i></span>
                    <span class="d-none d-sm-block">내업무</span> 
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-bs-toggle="tab" href="#messages1" role="tab" aria-selected="false">
                    <span class="d-block d-sm-none"><i class="far fa-envelope"></i></span>
                    <span class="d-none d-sm-block">댓글</span>   
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-bs-toggle="tab" href="#settings1" role="tab">
                    <span class="d-block d-sm-none"><i class="fas fa-cog"></i></span>
                    <span class="d-none d-sm-block">보낸메세지</span>    
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-bs-toggle="tab" href="#settings2" role="tab">
                    <span class="d-block d-sm-none"><i class="fas fa-cog"></i></span>
                    <span class="d-none d-sm-block">받은메세지</span>    
                </a>
            </li>
        </ul>

        <!-- Tab panes -->
		<div class="tab-content p-3 text-muted">
			<div class="tab-pane active" id="home1" role="tabpanel">
				<p class="mb-0">
				<ul class="message-list">
					<c:if test="${not empty calList }">
					<table class="table mb-0">
						<thead class="table-light"  style="text-align: center;">
							<tr>
								<th style="width: 80px;">분류</th>
								<th>일정제목</th>
								<th>일정내용</th>
								<th>시작일</th>
								<th>마감일</th>
							</tr>
						</thead>
						<tbody style="text-align: center;" id="calBody">
						<c:forEach items="${calList }" var="cal">
							<tr>
								<td>
									<h5 class="mb-0" style="vertical-align: middle;">
										<i class="ri-calendar-2-line"></i>
									</h5>
								</td>
								<td>${cal.workCalTitle}</td>
								<td>${cal.workCalContent }</td>
								<td>${cal.workCalStart }</td>
								<td>${cal.workCalEnd }</td>
							</tr>
						</c:forEach>
						</tbody>
						</table>
					</c:if>
				</ul>
				</p>
			</div>
			<div class="tab-pane" id="profile1" role="tabpanel">
				<p class="mb-0">
				<ul class="message-list" >
					<c:if test="${not empty workList }">
							<table class="table mb-0">
							<thead class="table-light" style="text-align: center;">
								<tr>
									<th style="width: 80px;">분류</th>
									<th style="width: 100px;">우선순위</th>
									<th style="width: 300px;">업무명</th>
									<th>업무내용</th>
									<th style="width: 100px;">시작일</th>
									<th style="width: 100px;">마감일</th>
								</tr>
							</thead>
							<tbody style="text-align: center;" id="workBody">
						<c:forEach items="${workList }" var="work">
						  <tr name="work" data-id="${work.workNum }">
								<td>
									<h5 class="mb-0" style="vertical-align: middle;">
										<i class="ri-edit-line"></i>
									</h5>
								</td>
								<td><span class="rqTitle ${work.workPriority}">${work.workPriority}</span></td>
								<td>${work.workTitle}</td>
								<td>${work.workContent }</td>
								<td>${work.workStart }</td>
								<td>${work.workEnd }</td>
							</tr>
						</c:forEach>
						</tbody>
						</table>
					</c:if>
				</ul>
				</p>
			</div>
			<div class="tab-pane" id="messages1" role="tabpanel">
				<p class="mb-0">
				<ul class="message-list">
					<c:if test="${not empty replyList }">
					<table class="table mb-0">
								<thead class="table-light" style="text-align: center;">
									<tr>
										<th style="width: 80px;">분류</th>
										<th>게시글</th>
										<th>댓글번호</th>
										<th>댓글내용</th>
										<th>작성일자</th>
									</tr>
								</thead>
								<tbody style="text-align: center;" id="replyBody">
						<c:forEach items="${replyList }" var="reply">
							<tr name="reply" data-id="${reply.work.workNum }">
								<td>
									<h5 class="mb-0" style="vertical-align: middle;">
										<i class="ri-chat-4-line"></i>
									</h5>
								</td>
								<td>${reply.work.workTitle }</td>
								<td>${reply.woReplyNum }</td>
								<td>${reply.woReplyContent}</td>
								<td>${reply.woReplyDate }</td>
							</tr>
						</c:forEach>
						</tbody>
						</table>
					</c:if>
				</ul>
				</p>
			</div>
			<div class="tab-pane" id="settings1" role="tabpanel">
				<p class="mb-0">
				<ul class="message-list">
					<c:if test="${not empty sendList }">
					<table class="table mb-0">
								<thead class="table-light" style="text-align: center;">
									<tr>
										<th style="width: 80px;">분류</th>
										<th>발신제목</th>
										<th>발신내용</th>
										<th>수신자</th>
										<th>발신일자</th>
									</tr>
								</thead>
								<tbody style="text-align: center;" id="sendBody">
						<c:forEach items="${sendList }" var="send">
							<tr>
								<td>
									<h5 class="mb-0" style="vertical-align: middle;">
										<i class="ri-mail-send-line"></i>
									</h5>
								</td>
								<td>${send.msgTitle}</td>
								<td>${send.msgContent }</td>
								<td>${send.receiveName }</td>
								<td>${send.msgDate }</td>
							</tr>
						</c:forEach>
						</tbody>
						</table>
					</c:if>
				</ul>
				</p>
			</div>
			<div class="tab-pane" id="settings2" role="tabpanel">
				<p class="mb-0">
				<ul class="message-list">
					<c:if test="${not empty receiveList }">
						<table class="table mb-0">
								<thead class="table-light" style="text-align: center;">
									<tr>
										<th style="width: 80px;">분류</th>
										<th>수신제목</th>
										<th>수신내용</th>
										<th>발신자</th>
										<th>수신일자</th>
									</tr>
								</thead>
								<tbody style="text-align: center;"  id="receiveBody">
						<c:forEach items="${receiveList }" var="receive">
							<tr>
								<td>
									<h5 class="mb-0" style="vertical-align: middle;">
										<i class="ri-mail-line"></i>
									</h5>
								</td>
								<td>${receive.message.msgTitle}</td>
								<td>${receive.message.msgContent }</td>
								<td>${receive.sender }</td>
								<td>${receive.message.msgDate }</td>
							</tr>
						</c:forEach>
						</tbody>
						</table>						
					</c:if>
				</ul>
				</p>
			</div>
		</div>

	</div>
</div>
<!-- card -->
<div class="row">
	<div class="col-5"
		style="align-content: center; vertical-align: middle;">
		<div class="btn-group float-end pagingArea"></div>
	</div>
</div>
<form:form id="searchForm" method="POST"
	action="${cPath }/project/${project.pjId}/myPost">
	<input type="hidden" name="page" />
	<input type="hidden" name="searchWord" />
</form:form>
<script>
	// 해당 searchForm의 기본 이벤트 제거
	window.onload = function() {
		document.getElementById("searchUI").onsubmit = function() {
			return false;
		};
	};
	
	$(document).on("click", "#calBody", function(){
		window.location.href = "${cPath}/project/${project.pjId}/calendar";
	})
	
	$(document).on("click", "#sendBody", function(){
		window.location.href = "${cPath}/project/${project.pjId}/msg";
	})
	
	$(document).on("click", "#receiveBody", function(){
		window.location.href = "${cPath}/project/${project.pjId}/msg";
	})
	
	$(document).on("click", "[name=work]", function(){
		console.log($(this).data("id"));
		let workNum = $(this).data("id");
		window.location.href = "${cPath}/project/${project.pjId}/work/" + workNum;
	})
	
	$(document).on("click", "[name=reply]", function(){
		let workNum = $(this).data("id");
		window.location.href = "${cPath}/project/${project.pjId}/work/" + workNum;
	})

	$(document).on("click", "li", function() {
		console.log($(this).attr("id"));
	})

	let listBody = $("#listBody");
	let searchUI = $("#searchUI");
	let memName = "${member.memName}";
	let messageList = [];
	console.log(memName);

	let searchForm = $("#searchForm").on(
			"submit",
			function(event) {
				event.preventDefault();
				let url = this.action;
				let method = this.method;
				let data = $(this).serialize();
				$.ajax({
					url : url,
					method : method,
					data : data,
					dataType : "json",
					success : function(resp) {
						$("#calBody").empty();
						$("#sendBody").empty();
						$("#receiveBody").empty();
						$("#workBody").empty();
						$("#replyBody").empty();
						
						$("#calBody").append(makeCalendar(resp.calList));
						$("#sendBody").append(makeSend(resp.sendList));
						$("#receiveBody").append(makeReceiver(resp.receiveList));
						$("#workBody").append(makeWork(resp.workList));
						$("#replyBody").append(makeReply(resp.replyList));
					}
				});
				return false;
			});

	function search() {
		let inputs = $("input[name=searchName]").val();
		$("#searchForm input[name=searchWord]").val(inputs);
		searchForm.submit();
	}

	$("#searchBtn").on("click", function(event) {
		let inputs = $("input[name=searchName]").val();
		$("#searchForm input[name=searchWord]").val(inputs);
		searchForm.submit();
	});

	function makeReply(resp) {
		let tbody = "";
		let replyList = resp;
		if (replyList && replyList.length > 0) {
			for (let i = 0; i < replyList.length; i++) {
				tbody += '<tr name="reply"data-id='+ replyList[i].work.workNum +'><td><h5 class="mb-0" style="vertical-align: middle;"><i class="ri-chat-4-line"></i></h5></td>';
				tbody += '<td>'+ replyList[i].work.workTitle +'</td>';
				tbody += '<td>'+ replyList[i].woReplyNum +'</td>';
				tbody += '<td>'+ replyList[i].woReplyContent +'</td>';
				tbody += '<td>'+ replyList[i].woReplyDate +'</td></tr>';
			}
		} else {
			tbody += '<tr><td><h5 class="mb-0" style="vertical-align: middle;"><i class="ri-chat-4-line"></i></h5></td>';
			tbody += '<td colspan = "4">댓글이 존재하지 않습니다.</td></tr>';
		}
		return tbody;
	}

	function makeWork(resp) {
		let tbody = "";
		let workList = resp;
		if (workList.length > 0) {
			for (let i = 0; i < workList.length; i++) {
				tbody += '<tr name="work" data-id='+ workList[i].workNum +'><td><h5 class="mb-0" style="vertical-align: middle;"><i class="ri-edit-line"></i></h5></td>';
				tbody += '<td><span class="rqTitle '+ workList[i].workPriority +'">'+ workList[i].workPriority +'</span></td>';
				tbody += '<td>'+ workList[i].workTitle +'</td>';
				tbody += '<td>'+ workList[i].workContent +'</td>';
				tbody += '<td>'+ workList[i].workStart +'</td>';
				tbody += '<td>'+ workList[i].workEnd +'</td></tr>';
			}

		} else {
			tbody += '<tr><td><h5 class="mb-0" style="vertical-align: middle;"><i class="ri-edit-line"></i></h5></td>';
			tbody += '<td colspan = "4">업무가 존재하지 않습니다.</td></tr>';
		}
		return tbody;
	}

	function makeReceiver(resp) {
		let tbody = "";
		let receiveList = resp;
		if (receiveList.length > 0) {
			for (let i = 0; i < receiveList.length; i++) {
				tbody += '<tr><td><h5 class="mb-0" style="vertical-align: middle;"><i class="ri-mail-line"></i></h5></td>';
				tbody += '<td>'+receiveList[i].message.msgTitle +'</td>';
				tbody += '<td>'+ receiveList[i].message.msgContent +'</td>';
				tbody += '<td>'+ receiveList[i].sender +'</td>';
				tbody += '<td>'+ receiveList[i].message.msgDate +'</td></tr>';
			}
		} else {
			tbody += '<tr><td><h5 class="mb-0" style="vertical-align: middle;"><i class="ri-mail-line"></i></h5></td>';
			tbody += '<td colspan = "4">받은 메시지가 존재하지 않습니다.</td></tr>';
		}
		return tbody;
	}

	function makeSend(resp) {
		let tbody = "";
		let sendList = resp;
		if (sendList.length > 0) {
			for (let i = 0; i < sendList.length; i++) {
				tbody += '<tr><td><h5 class="mb-0" style="vertical-align: middle;"><i class="ri-mail-send-line"></i></h5></td>';
				tbody += '<td>'+ sendList[i].msgTitle +'</td>';
				tbody += '<td>'+ sendList[i].msgContent +'</td>';
				tbody += '<td>'+ sendList[i].receiveName +'</td>';
				tbody += '<td>'+ sendList[i].msgDate +'</td></tr>';
			}
		} else {
			tbody += '<tr><td><h5 class="mb-0" style="vertical-align: middle;"><i class="ri-mail-send-line"></i></h5></td>';
			tbody += '<td colspan = "4">보낸 메시지가 존재하지 않습니다.</td></tr>';
		}
		return tbody;
	}

	function makeCalendar(resp) {
		let tbody = "";
		let calendar = resp;
		if (calendar.length > 0) {
			for (let i = 0; i < calendar.length; i++) {
				tbody += '<tr><td><h5 class="mb-0" style="vertical-align: middle;"><i class="ri-calendar-2-line"></i></h5></td>';
				tbody += '<td>'+ calendar[i].workCalTitle +'</td>';
				tbody += '<td>'+ calendar[i].workCalContent+'</td>';
				tbody += '<td>'+ calendar[i].workCalStart+'</td>';
				tbody += '<td>'+ calendar[i].workCalEnd+'</td></tr>';
			}

		} else {
			tbody += '<tr><td><h5 class="mb-0" style="vertical-align: middle;"><i class="ri-calendar-2-line"></i></h5></td>';
			tbody += '<td colspan = "4">일정이 존재하지 않습니다.</td></tr>';
		}
		return tbody;
	}

	$(document)
			.on(
					"click",
					".calendar",
					function() {
						console.log($(this).attr("id"));
						$
								.ajax({
									url : "${cPath}/project/${project.pjId}/calendar/view",
									method : "get",
									data : {
										"workCalNum" : $(this).attr("id")
									},
									dataType : "json",
									success : function(resp) {
										console.log(resp);
										$("#modalbody").empty();
										let tbody = "";
										tbody += '<tr><th scope="row">일정 명</th><td>'
										tbody += resp.workCalTitle
										tbody += '</td></tr>'
										tbody += '<tr><th scope="row">일정 시작일</th><td>'
										tbody += resp.workCalStart
										tbody += '</td></tr>'
										tbody += '<tr><th scope="row">일정 마감일</th><td>'
										tbody += resp.workCalEnd
										tbody += '</td></tr>'
										if (resp.workCalClass == 'CAL1') {
											tbody += '<tr><th scope="row">일정 종류</th><td>개인 일정</td></tr>'
										} else {
											tbody += '<tr><th scope="row">일정 종류</th><td>프로젝트 일정</td></tr>'
										}
										tbody += '<tr><th scope="row">일정 색깔</th><td><input type="color" value="'+ resp.workCalColor +'" disabled >'
										tbody += '</td></tr>'
										$("#modalbody").append(tbody);
										$(".modal-title").text("업로드 파일 상세 보기");
									}
								})
					})
</script>