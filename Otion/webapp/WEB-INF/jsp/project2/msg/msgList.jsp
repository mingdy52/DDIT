<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022.08.05    고정현      jsp 연결
* 2022.08.19    고정현      메시지 리스트 조회
* Copyright (c) ${year} by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<style>
.message-list li {
	position: relative;
	display:;
	cursor: default;
	vertical-align: middle;
	-webkit-transition-duration: .3s;
	transition-duration: .3s;
	border-left: 2px solid transparent;
	cursor: default;
}

.card {
	box-shadow: none;
	-webkit-box-shadow: none;
}
</style>
<sec:authentication property="principal.realMember" var="member" />
<!-- Nav tabs -->
<!-- <ul class="nav nav-tabs nav-tabs-custom nav-justified" role="tablist"> -->
<!-- 	<li class="nav-item"><a class="nav-link active" -->
<!-- 		data-bs-toggle="tab" href="#allMessage" role="tab" -->
<!-- 		aria-selected="true"> <span class="d-block d-sm-none"><i -->
<!-- 				class="fas fa-home"></i></span> <span class="d-none d-sm-block">메시지 -->
<!-- 				함</span> -->
<!-- 	</a></li> -->
<!-- </ul> -->

<!-- Tab panes -->
<div class="tab-content p-3 text-muted">
	<div class="tab-pane active" id="allMessage" role="tabpanel">
		<p class="mb-0">
		<div class="container-fluid">

			<!-- end page title -->

			<div class="row">
				<div class="col-12">
					<!-- Left sidebar -->
					<div class="email-leftbar card">
						<button type="button"
							class="btn btn-secondary waves-effect waves-light"
							data-bs-toggle="modal" data-bs-target="#insertMessageModal">
							<i class="mdi mdi-email-edit-outline"></i>메시지작성
						</button>
						<div class="mail-list mt-4" id="msgListBtn">
							<a href="#" class="setActive active" id="send"><i
								class="mdi mdi-email-send-outline me-2 font-size-16"></i>발신 메시지</a>
							<a href="#" class="setActive" id="receive"><i
								class="mdi mdi-email-receive-outline me-2 font-size-16"></i>수신
								메시지<span class="ms-1 float-end"></span></a> <a href="#"
								class="setActive" id="important"><i
								class="mdi mdi-email-alert-outline me-2 font-size-16"></i>중요 메시지
								<span class="ms-1 float-end"></span></a> <a href="#"
								class="setActive" id="delete"><i
								class="mdi mdi-email-off-outline me-2 font-size-16"></i>삭제한 메일</a>
						</div>
						<h6 class="mt-4">프로젝트 팀원</h6>
						<div class="mt-3">
							<c:forEach items="${colleagueList }" var="colleague">
								<c:if test="${colleague.member.memId ne member.memId }">
									<a href="#" class="d-flex" name="colleagueMessage"
										data-id="${colleague.member.memName }"
										style="margin-bottom: 10px;">
										<div
											class="email-link-name bg-soft-primary text-center border-primary me-3">
											<h6 class="text-primary">${colleague.workRoleCode }</h6>
										</div>
										<div class="flex-grow-1 chat-user-box">
											<h6 class="user-title mb-1">${colleague.member.memName }</h6>
											<p class="text-muted mb-0">${colleague.member.memMail }</p>
										</div>
									</a>
								</c:if>
							</c:forEach>
						</div>
					</div>
					<!-- End Left sidebar -->
					<!-- Right Sidebar -->
					<div class="email-rightbar mb-3">

						<div class="card">

							<div class="row">
								<div class="col-xl-4 col-md-12">
									<form class="email-search p-3 " id="searchUI">
										<div class="position-relative">
											<input type="text" class="form-control" id="searchWord"
												name="searchWord" placeholder="Search..."> <span
												class="mdi mdi-magnify font-size-18" id="searchBtn"></span>
										</div>
									</form>
								</div>
								<div class="col-xl-8 col-md-12">
									<div class="btn-toolbar py-3 float-end" role="toolbar">
										<div class="btn-group me-2 mb-2 mb-sm-0">
											<button type="button" id="removeBtn"
												class="btn btn-primary waves-light waves-effect">
												<i class="far fa-trash-alt"></i>
											</button>
										</div>
									</div>
								</div>
							</div>
							<ul class="message-list" id="listBody" style="height: 600px;">
							</ul>
						</div>
						<!-- card -->
						<div class="row" style="align-content: center;">
							<div class="col-5">
								<div class="btn-group float-end pagingArea"></div>
							</div>
						</div>
					</div>
					<!-- end Col-9 -->
				</div>
			</div>
			<!-- End row -->
		</div>
		</p>
	</div>
	<div class="modal fade bs-example-modal-xl" tabindex="-1" role="dialog"
		id="insertMessageModal" aria-labelledby="myExtraLargeModalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-xl">
			<div class="modal-content" style="color: black;">
				<div class="modal-header">
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close" name="messageDel"></button>
				</div>
				<br>
				<h5 class="modal-title" id="myExtraLargeModalLabel"
					style="text-align: center;">메시지작성</h5>
				<form:form action="${cPath }/project/${project.pjId }/msg/form"
					method="post" modelAttribute="messageVO" id="insertMessage">
					<div class="modal-body">
						<div>
							<div style="width: 74%; margin: auto;">
								<div class="row mb-3">
									<div>
										<!-- 									<label for="example-text-input" class="col-sm-2 col-form-label">메시지 제목</label> -->
										<form:input class="form-control" type="text"
											autocomplete="off" placeholder="메시지 제목은 최대 15자까지 입력가능합니다."
											id="example-text-input" path="msgTitle" maxlength="25"
											required="true" />
										<form:errors path="msgTitle" element="span" cssClass="error" />
									</div>
								</div>
								<div class="row mb-3">
									<!-- 									<label class="col-sm-2 col-form-label">팀원 선택</label> -->
									<div>
										<input type="text" name="receiverNum" class="form-control"
											data-bs-toggle="dropdown" aria-expanded="false"
											autocomplete="off" placeholder="팀원을 선택해주세요">
										<div class="dropdown-menu col-12">
											<div class="dropdown-item col-mail col-mail-1">
												<input class="checkbox-wrapper-mail" type="checkbox"
													id="allChk" style="margin-right: 30px; font-size: large;">전체선택
											</div>
											<c:forEach items="${colleagueList }" var="colleague">
												<c:if test="${colleague.member.memId ne member.memId }">
													<div class="dropdown-item col-mail col-mail-1"
														style="font-size: 15px;">
														<input class="checkbox-wrapper-mail" type="checkbox"
															name="chk" style="margin-right: 30px"
															data-id="${colleague.colNum }"
															value="${colleague.member.memName }">${colleague.member.memName }
													</div>
												</c:if>
											</c:forEach>
											<div class="dropdown-item col-mail col-mail-1"
												style="text-align: right;">
												<span>
													<button type="button"
														class="btn btn-secondary btn-sm waves-effect waves-light"
														id="cancelBtn">취소</button>
													<button type="button"
														class="btn btn-dark btn-sm waves-effect waves-light">확인</button>
												</span>
											</div>
										</div>
									</div>
								</div>
								<div class="row mb-3">
									<div>
										<!-- 									<label for="example-text-input" class="col-sm-2 col-form-label">내용</label> -->
										<form:textarea class="form-control" placeholder="메시지 내용 작성"
											id="example-search-input" path="msgContent"
											style="min-height : 300px;"></form:textarea>
										<form:errors path="msgTitle" element="span" cssClass="error" />
									</div>
								</div>

							</div>
						</div>
					</div>
				</form:form>
				<div class="modal-footer">
					<!-- Toogle to second dialog -->
					<button type="button" class="btn btn-light waves-effect"
						data-bs-dismiss="modal" name="messageDel">Close</button>
					<button type="button" class="btn btn-dark waves-effect"
						id="insertMessageBtn">전송</button>
				</div>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<div class="modal fade bs-example-modal-center" tabindex="-1"
	id="messageView" aria-labelledby="mySmallModalLabel"
	style="display: none;" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="table-responsive">
					<table class="table table-borderless mb-0">
						<tr>
							<td id="msgTitle" colspan="3"
								style="font-weight: bold; font-size: 18px; padding-bottom: 0;"></td>
						</tr>
						<tr>
							<td
								style="width: 70px; border-bottom: 1px solid black; padding-top: 5px;">발신자</td>
							<td id="msgWriterId"
								style="border-bottom: 1px solid black; padding-top: 5px;"></td>
							<td id="msgDate"
								style="text-align: right; border-bottom: 1px solid black; padding-top: 5px;"></td>
						</tr>
						<tr>
							<td colspan="3"><div style="min-height: 200px;"
									id="msgContent"></div></td>
						</tr>
						<tr>
							<td scope="row" style="width: 70px;">수신자</td>
							<td colspan="2" id="receiverList"></td>
						</tr>
					</table>
				</div>
			</div>
			<div class="modal-footer">
				<!-- Toogle to second dialog -->
				<button type="button" class="btn btn-light waves-effect"
					data-bs-dismiss="modal">Close</button>
				<button type="button" class="btn btn-dark waves-effect"
					id="replyMessage">답장</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>


<form:form id="searchForm" method="POST"
	action="${cPath }/project/${project.pjId}/msg">
	<input type="hidden" name="page">
	<input type="hidden" name="searchType" value="send" />
	<input type="hidden" name="searchWord" />
</form:form>
<form:form id="removeMessage" method="POST">
	<input type="hidden" name="_method" value="delete">
	<input type="hidden" name="msgNum">
	<input type="hidden" name="who">
</form:form>
<script>
	// 팀원 누르면 메시지 작성 여부 묻기
	$("a[name=colleagueMessage]").on("click", function() {
		if (confirm("해당 팀원에게 메시지를 작성하시겠습니까?")) {
			let id = $(this).data("id");
			$("#insertMessageModal").find("input[name=receiverNum]").val(id);
			$("#insertMessageModal").modal("show");
		}
	})

	// 메시지 작성창 닫았을 때 내용 지우기
	$("button[name=messageDel]").on("click", function() {
		$("#insertMessage")[0].reset();
	});

	$(".setActive").on("click", function() {
		searchUI.find("[name=searchWord]").val("");
		searchForm.find("[name=page]").val("");
		searchForm.find("[name=searchWord]").val("");
		$(".setActive").removeClass("active");
		$(this).addClass("active");
	});

	$("#removeBtn").on("click", function() {
		let msgNumArr = [];
		let whoArr = [];
		$("input[name=removeChk]:checked").each(function() {
			console.log($(this).val());
			console.log($(this).data("id"));
			whoArr.push($(this).data("id"));
			msgNumArr.push($(this).val());
			console.log(msgNumArr);
			console.log(whoArr);
		});

		$("input[name=msgNum]").val(msgNumArr);
		$("input[name=who]").val(whoArr);

		if (whoArr.length > 0 && msgNumArr.length > 0) {
			$("#removeMessage").submit();
		}
	});

	$("#allChk").click(function() {

		if ($("#allChk").is(":checked") == true) {
			$(":input[name=chk]").prop("checked", true);
			let ids = "";
			let names = "";
			console.log($(":input[name=chk]").length);
			$(":input[name=chk]:checked").each(function(idx, data) {
				let id = $(this).data("id");
				ids += id;
				let name = $(this).val();
				names += name;
				if (idx != $(":input[name=chk]:checked").length - 1) {
					ids += ",";
					names += ",";
				}
			});
			$("input[name=receiverNum]").val(names);
			$("input[name=receiverNum]").data("id", ids);
		} else {
			$("input[name=receiverNum]").val("");
			$("input[name=receiverNum]").data("id", "");
			$("input[name=chk]").prop("checked", false);
		}

	});

	$("input[name=chk]")
			.on(
					"click",
					function() {
						let ids = "";
						let names = "";
						console.log($(":input[name=chk]").length);
						$(":input[name=chk]:checked")
								.each(
										function(idx, data) {
											let id = $(this).data("id");
											ids += id;
											let name = $(this).val();
											names += name;
											if (idx != $(":input[name=chk]:checked").length - 1) {
												ids += ",";
												names += ",";
											}
										});
						$("input[name=receiverNum]").val(names);
						$("input[name=receiverNum]").data("id", ids);
						if ($(":input[name=chk]").length == $(":input[name=chk]:checked").length) {
							$("#allChk").prop("checked", true);
						} else {
							$("#allChk").prop("checked", false);
						}
					});

	// 답장 누르면 메시지 작성 창 띄우기
	$("#replyMessage").on("click", function() {
		$("#messageView").modal("hide");
		$("#insertMessageModal").modal("show");
	});

	// 중요메시지 체크
	$(document).on("click", ".star-toggle", function() {
		console.log("별클릭");
		let msgNum = "";
		let who = "";
		let del = "";
		if ($(this).hasClass("fa")) {
			$(this).removeClass("fa");
			$(this).addClass("far");
			console.log($(this).data("id"));
			msgNum = $(this).data("id");
			who = $(this).data("who");
			del = true;
		} else if ($(this).hasClass("far")) {
			$(this).removeClass("far");
			$(this).addClass("fa");
			console.log($(this).data("id"));
			msgNum = $(this).data("id");
			who = $(this).data("who");
			del = false;
		}
		$.ajax({
			url : "${cPath}/project/${project.pjId}/msg/important",
			method : "get",
			data : {
				"msgNum" : msgNum,
				"who" : who,
				"del" : del
			},
			dataType : "json",
			success : function(resp) {
				console.log(resp);
			}
		})
	});

	// 보낸 메시지 내용
	$(".setActive").on("click", function() {
		let val = $(this).attr("id");
		$("input[name='searchType']").val(val);
		searchForm.submit();
	})

	// 메시지 작성 완료 버튼
	$("#insertMessageBtn").on("click", function() {
		$("#insertMessage").submit();
	});

	// 해당 모달에 메시지 상세보기
	$(document).on("click", "li", function() {
		// 메시지 내용 가져오기
		$("#receiverList").empty();
		let msgNum = $("li input[name=removeChk]").val();
		let type = $(this).data("who");
		console.log(type);
		$.ajax({
			url : "${cPath}/project/${project.pjId}/msg",
			type : 'GET',
			data : {
				"msgNum" : msgNum,
				"type" : type
			},
			dataType : "json",
			success : function(resp) {
				console.log(resp);
				if(type == "send"){
					$("#msgTitle").text(resp.message.msgTitle);
					$("#msgWriterId").text(resp.message.sendName);
					$("#msgContent").text(resp.message.msgContent);
					$("#msgDate").text(resp.message.msgDate);
					$("#receiverList").text(resp.message.receiveName);
				}else if(type == "receive"){
					$("#msgTitle").text(resp.message.msgTitle);
					$("#msgWriterId").text(resp.sender);
					$("#msgContent").text(resp.message.msgContent);
					$("#msgDate").text(resp.message.msgDate);
					$("#receiverList").text(resp.receiver);
				}
			}
		})

	});

	let listBody = $("#listBody");
	let tbody = "";
	let pagingArea = $(".pagingArea");
	let searchUI = $("#searchUI");
	let memId = "${member.memId}";
	let messageList = [];
	console.log(memId);

	let searchForm = $("#searchForm").on(
			"submit",
			function(event) {
				event.preventDefault();
				let method = this.method;
				let data = $(this).serialize();
				$.ajax({
					method : method,
					data : data,
					dataType : "json",
					success : function(resp) {
						let pagingVO = resp;
						console.log(resp);
						$("#listBody").empty();
						tbody = "";
						let searchType = $("#searchForm").find(
								"input[name=searchType]").val();
						console.log(searchType);
						// 해당 객체가 존재하면 true
						if (searchType == "send") {
							console.log(resp);
							tbody = makeSend(resp);
						} else if (searchType == "receive") {
							console.log(resp);
							tbody = makeReceive(resp);
						} else if (searchType == "important") {
							console.log("중요 : " + resp);
							tbody = makeImportant(resp);
						} else if (searchType == "delete") {
							console.log(resp);
							tbody = makeDelete(resp);
						}
						if (resp.dataList.length > 0)
							pagingArea.html(pagingVO.pagingHTMLBS);
						$("#listBody").append(tbody);
					}
				});
				return false;
			}).submit();

	function makeDelete(resp) {
		let tbody = "";
		let dataList = resp.dataList;
		if (dataList && dataList.length > 0) {
			//$.each(memberList,)
			$(dataList)
					.each(
							function(idx, data) {
								messageList.push(data);
								console.log(data);
								let receive = data.receiverList[0];
								if ((data.msgWriterId == memId && data.msgDelYn == 'Y')) {
									tbody += "<li data-id='"+ data.msgNum +"' data-who='send'>";
									tbody += "<div class='col-mail col-mail-1'>";
									tbody += "<input type='checkbox' name='removeChk' class='remove checkbox-wrapper-mail' data-id='sender' value='"+ data.msgNum +"'>";
									tbody += "<a href='#' class='title'><div class='d-flex'><div class='flex-grow-1 mt-2'>"
											+ "<h6 class='mb-0'></h6><p class='text-muted email-date font-size-13 mb-0'>"
											+ data.msgDate
											+ "</p></div></div></a>";
									// 받은 내역이 아닐경우
									if (data.msgImportant == 'Y')
										tbody += '<span class="star-toggle fa fa-star" data-id = "'+ data.msgNum +'" data-who = "receiver"></span></div>';
									else {
										tbody += '<span class="star-toggle far fa-star" data-id = "'+ data.msgNum +'" data-who = "receiver"></span></div>';
									}
									tbody += '<div class="col-mail col-mail-2"  data-bs-toggle="modal" data-bs-target=".bs-example-modal-center"><a href="#" class="subject"><h6 class="mb-0">'
											+ data.msgTitle
											+ '</h6>'
											+ '<p class="text-muted email-desc font-size-13 mb-0"> Hello –<span class="teaser">'
											+ data.msgContent + '</span>';
									tbody += '</p> </a><div class="date"><a href="">'
											+ data.msgDate
											+ '</a></div></div></li> ';
								} else if (receive.receiverNum == memId
										&& receive.msgRecDel == 'Y') {
									tbody += "<li data-id='"+ data.msgNum +"' data-who='receive'>";
									tbody += "<div class='col-mail col-mail-1'>";
									tbody += "<input type='checkbox' name='removeChk' class='remove checkbox-wrapper-mail' data-id='sender' value='"+ data.msgNum +"'>";
									tbody += "<a href='#' class='title'><div class='d-flex'><div class='flex-grow-1 mt-2'>"
											+ "<h6 class='mb-0'></h6><p class='text-muted email-date font-size-13 mb-0'>"
											+ data.msgDate
											+ "</p></div></div></a>";
									if (data.receiverList.length > 0) {
										let count = 0;
										for (let i = 0; i < data.receiverList.length; i++) {
											let receive = data.receiverList[i];
											if (receive.receiverNum == memId) {
												if (receive.msgRecIm == 'Y')
													tbody += '<span class="star-toggle fa fa-star" data-id = "'+ data.msgNum +'" data-who = "receiver"></span></div>';
												else {
													tbody += '<span class="star-toggle far fa-star" data-id = "'+ data.msgNum +'" data-who = "receiver"></span></div>';
												}
												break;
											}
											count++;
										}
									}
									tbody += '<div class="col-mail col-mail-2"  data-bs-toggle="modal" data-bs-target=".bs-example-modal-center"><a href="#" class="subject"><h6 class="mb-0">'
											+ data.msgTitle
											+ '</h6>'
											+ '<p class="text-muted email-desc font-size-13 mb-0"> Hello –<span class="teaser">'
											+ data.msgContent + '</span>';
									tbody += '</p> </a><div class="date"><a href="">'
											+ data.msgDate
											+ '</a></div></div></li> ';
								}
							});
			return tbody;
		} else {
			tbody += '<li>';
			tbody += '<div id="noFile" class="col-xl-3 col-lg-4 col-sm-6" style="text-align: center; margin: 0 auto; height: 10px;">';
			tbody += '<i class="ri-chat-off-line" style="font-size: 300px;"></i><br>';
			tbody += '<h2 id="noFileText" style="width: 350px;">메시지가 없습니다.</h2>';
			tbody += '</div>';
			tbody += '</li>';
		}
	}

	function makeImportant(resp) {
		let dataList = resp.dataList;
		if (dataList && dataList.length > 0) {
			//$.each(memberList,)
			$(dataList)
					.each(
							function(idx, data) {
								messageList.push(data);
								console.log(data);
								let receive = data.receiverList[0];
								if ((data.msgWriterId == memId
										&& data.msgImportant == 'Y' && data.msgDelYn == 'N')) {
									tbody += "<li data-id='"+ data.msgNum +"' data-who='send'>";
									tbody += "<div class='col-mail col-mail-1'>";
									tbody += "<input type='checkbox' name='removeChk' class='remove checkbox-wrapper-mail' data-id='receiver' value='"+ data.msgNum +"'>";
									tbody += "<a href='#' class='title'><div class='d-flex'><div class='flex-grow-1 mt-2'>"
											+ "<h6 class='mb-0'></h6><p class='text-muted email-date font-size-13 mb-0'>"
											+ data.msgDate
											+ "</p></div></div></a>"
									// 해당 메시지가 자신이 보낸 메시지임
									if (data.msgWriterId == memId
											&& data.msgImportant == 'Y') {
										tbody += '<span class="star-toggle fa fa-star" data-id = "'+ data.receiverNum +'" data-who = "receiver"></span></div>';
									} else {
										tbody += '<span class="star-toggle far fa-star" data-id = "'+ data.receiverNum +'" data-who = "receiver"></span></div>';
									}
									tbody += '<div class="col-mail col-mail-2"  data-bs-toggle="modal" data-bs-target=".bs-example-modal-center"><a href="#" class="subject"><h6 class="mb-0">'
											+ data.msgTitle
											+ '</h6>'
											+ '<p class="text-muted email-desc font-size-13 mb-0"> Hello –<span class="teaser">'
											+ data.msgContent + '</span>';
									if (data.readDate) {
										tbody += '</p> </a><div class="date"><a href="">'
												+ data.readDate
												+ '</a></div></div></li> ';
									}
								} else if (receive.receiverNum == memId
										&& receive.msgRecIm == 'Y'
										&& receive.msgRecDel == 'N') {
									tbody += "<li data-id='"+ data.msgNum +"' data-who='receive'>";
									tbody += "<div class='col-mail col-mail-1'>";
									tbody += "<input type='checkbox' name='removeChk' class='remove checkbox-wrapper-mail' data-id='receiver' value='"+ data.msgNum +"'>";
									tbody += "<a href='#' class='title'><div class='d-flex'><div class='flex-grow-1 mt-2'>"
											+ "<h6 class='mb-0'></h6><p class='text-muted email-date font-size-13 mb-0'>"
											+ data.msgDate
											+ "</p></div></div></a>"
									// 자신이 받은 메시지 임
									if (data.receiverList) {
										if (receive.receiverNum == memId
												&& receive.msgRecIm == 'Y')
											tbody += '<span class="star-toggle far fa-star" data-id = "'+ receive.msgNum +'" data-who = "receiver"></span></div>';
										else {
											tbody += '<span class="star-toggle fa fa-star" data-id = "'+ receive.msgNum +'" data-who = "receiver"></span></div>';
										}
									}
									tbody += '<div class="col-mail col-mail-2"  data-bs-toggle="modal" data-bs-target=".bs-example-modal-center"><a href="#" class="subject"><h6 class="mb-0">'
											+ data.msgTitle
											+ '</h6>'
											+ '<p class="text-muted email-desc font-size-13 mb-0"> Hello –<span class="teaser">'
											+ data.msgContent + '</span>';
									if (data.readDate) {
										tbody += '</p> </a><div class="date"><a href="">'
												+ data.readDate
												+ '</a></div></div></li> ';
									}
								}
							});
		} else {
			tbody += '<li>';
			tbody += '<div id="noFile" class="col-xl-3 col-lg-4 col-sm-6" style="text-align: center; margin: 0 auto; height: 10px;">';
			tbody += '<i class="ri-chat-off-line" style="font-size: 300px;"></i><br>';
			tbody += '<h2 id="noFileText" style="width: 350px;">메시지가 없습니다.</h2>';
			tbody += '</div>';
			tbody += '</li>';
		}
		return tbody;
	}

	function makeReceive(resp) {
		let dataList = resp.dataList;
		if (dataList && dataList.length > 0) {
			//$.each(memberList,)
			$(dataList)
					.each(
							function(idx, data) {
								messageList.push(data);
								console.log(data);
								if (data.msgRecDel == 'N') {
									tbody += "<li data-id='"+ data.msgNum +"' data-who='receive'>";
									tbody += "<div class='col-mail col-mail-1'>";
									tbody += "<input type='checkbox' name='removeChk' class='remove checkbox-wrapper-mail' data-id='receiver' value='"+ data.msgNum +"'>";
									tbody += "<a href='#' class='title'><div class='d-flex'><div class='flex-grow-1 mt-2'>"
											+ "<h6 class='mb-0'></h6><p class='text-muted email-date font-size-13 mb-0'>"
											+ data.message.msgDate
											+ "</p></div></div></a>"
									if (data.receiverNum == memId
											&& data.msgRecIm == 'Y')
										tbody += '<span class="star-toggle fa fa-star" data-id = "'+ data.msgNum +'" data-who = "receiver"></span></div>';
									else {
										tbody += '<span class="star-toggle far fa-star" data-id = "'+ data.msgNum +'" data-who = "receiver"></span></div>';
									}
									tbody += '<div class="col-mail col-mail-2"  data-bs-toggle="modal" data-bs-target=".bs-example-modal-center"><a href="#" class="subject"><h6 class="mb-0">'
											+ data.message.msgTitle
											+ '</h6>'
											+ '<p class="text-muted email-desc font-size-13 mb-0"> Hello –<span class="teaser">'
											+ data.message.msgContent
											+ '</span>';
									if (data.readDate) {
										tbody += '</p> </a><div class="date"><a href="">'
												+ data.readDate
												+ '</a></div></div></li> ';
									}
								} else {
									tbody += '<li>';
									tbody += '<div id="noFile" class="col-xl-3 col-lg-4 col-sm-6" style="text-align: center; margin: 0 auto; height: 10px;">';
									tbody += '<i class="ri-chat-off-line" style="font-size: 300px;"></i><br>';
									tbody += '<h2 id="noFileText" style="width: 350px;">메시지가 없습니다.</h2>';
									tbody += '</div>';
									tbody += '</li>';
								}
							});
		} else if (dataList.length == 0) {
			tbody += '<li>';
			tbody += '<div id="noFile" class="col-xl-3 col-lg-4 col-sm-6" style="text-align: center; margin: 0 auto; height: 10px;">';
			tbody += '<i class="ri-chat-off-line" style="font-size: 300px;"></i><br>';
			tbody += '<h2 id="noFileText" style="width: 350px;">메시지가 없습니다.</h2>';
			tbody += '</div>';
			tbody += '</li>';
		}

		return tbody;
	}

	function makeSend(resp) {
		let dataList = resp.dataList;
		if (dataList && dataList.length > 0) {
			//$.each(memberList,)
			$(dataList)
					.each(
							function(idx, data) {
								messageList.push(data);
								console.log(data);
								tbody += "<li data-id='"+ data.msgNum +"' data-who='send'>";
								tbody += "<div class='col-mail col-mail-1'>";
								tbody += "<input type='checkbox' name='removeChk' class='remove checkbox-wrapper-mail' data-id='sender' value='"+ data.msgNum +"'>";
								tbody += "<a href='#' class='title'><div class='d-flex'><div class='flex-grow-1 mt-2'>"
										+ "<h6 class='mb-0'></h6><p class='text-muted email-date font-size-13 mb-0'>"
										+ data.msgDate + "</p></div></div></a>";
								if (data.msgImportant == 'N'
										|| !data.msgImportant)
									tbody += '<span class="star-toggle far fa-star" data-id = "'+ data.msgNum +'" data-who = "sender"></span></div>';
								else if (data.msgImportant == 'Y') {
									tbody += '<span class="star-toggle fa fa-star" data-id = "'+ data.msgNum +'" data-who = "sender"></span></div>';
								}
								tbody += '<div class="col-mail col-mail-2"  data-bs-toggle="modal" data-bs-target=".bs-example-modal-center"><a href="#" class="subject"><h6 class="mb-0">'
										+ data.msgTitle
										+ '</h6>'
										+ '<p class="text-muted email-desc font-size-13 mb-0"><span class="teaser">'
										+ data.msgContent + '</span>';
								tbody += '</p> </a><div class="date"><a href=""><i class="mdi mdi-link-variant me-2 font-size-18"></i>'
										+ data.msgDate
										+ '</a></div></div></li> ';
							});
		} else if (dataList.length == 0) {
			tbody += '<li>';
			tbody += '<div id="noFile" class="col-xl-3 col-lg-4 col-sm-6" style="text-align: center; margin: 0 auto; height: 10px;">';
			tbody += '<i class="ri-chat-off-line" style="font-size: 300px;"></i><br>';
			tbody += '<h2 id="noFileText" style="width: 350px;">메시지가 없습니다.</h2>';
			tbody += '</div>';
			tbody += '</li>';
		}

		return tbody;
	}

	$(".pagingArea").on("click", "a", function(event) {
		// this == event.target
		let page = $(this).data("page");
		// jsp는 얼마든지 바꿔도 상관없음
		searchForm.find("[name=page]").val(page);
		searchForm.submit();
	})

	$("#searchBtn").on("click", function(event) {
		let inputs = searchUI.find(":input[name]");
		searchForm.find("[name=searchWord]").val($(inputs).val());
		searchForm.submit();
	});
</script>