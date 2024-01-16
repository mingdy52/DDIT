<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 9. 1.      작성자명      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true"%>
<sec:authentication property="principal.realMember" var="member" />
<c:set value="${project.pjId }" var="pjId" />
<div class="container-fluid">
	<div class="d-lg-flex">
		<div class="chat-leftsidebar me-lg-4">
			<div class="card">
				<div class="p-4">
					<div class="search-box chat-search-box pb-4">
						<div class="position-relative">
							<input type="text" class="form-control"
								placeholder="채팅방 이름을 입력해주세요" name="chatTitle"
								onkeyup="if(window.event.keyCode==13){search()}"> <i
								class="mdi mdi-magnify search-icon"></i>
						</div>
					</div>

					<div class="chat-leftsidebar-nav">
						<ul class="nav nav-pills nav-justified">
							<li class="nav-item"><a href="#" data-bs-toggle="modal"
								data-bs-target=".bs-example-modal-lg" class="nav-link active"
								id="insertChatRoom"><span>채팅방 생성</span> </a></li>
						</ul>
						<div class="modal fade bs-example-modal-lg" tabindex="-1"
							aria-labelledby="myExtraLargeModalLabel" style="display: none;"
							aria-hidden="true">
							<div class="modal-dialog modal-lg">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title">채팅방 생성</h5>
										<button type="button" class="btn-close"
											data-bs-dismiss="modal" aria-label="Close"></button>
									</div>
									<div class="modal-body">
										<form method="POST" enctype="multipart/form-data"
											id="insertChatForm">
											<sec:csrfInput />
											<div class="card-body">
												<div class="row mb-3">
													<label for="example-text-input"
														class="col-sm-2 col-form-label">채팅방 이름</label>
													<div class="col-sm-10">
														<input class="form-control" type="text" max="30"
															autocomplete="off" placeholder="채팅방 이름을 입력해주세요"
															name="crName">
													</div>
												</div>
												<div class="row mb-3">
													<label for="example-search-input"
														class="col-sm-2 col-form-label">채팅방 이미지</label>
													<div class="col-sm-10">
														<input type="file" class="form-control" name="crImage"
															accept="image/*" required>
													</div>
												</div>
												<div class="row mb-3">
													<label class="col-sm-2 col-form-label">팀원 선택</label>
													<div class="col-sm-10">
														<input type="hidden" name="crColleague"> <input
															type="text" name="crColleagues" class="form-control"
															data-bs-toggle="dropdown" aria-expanded="false"
															autocomplete="off" placeholder="팀원을 선택해주세요">
														<div class="dropdown-menu col-12">
															<div class="dropdown-item col-mail col-mail-1">
																<input class="checkbox-wrapper-mail" type="checkbox"
																	id="allChk"
																	style="margin-right: 30px; font-size: large;">전체
																선택
															</div>
															<c:forEach items="${colleagueList }" var="colleague">
																<c:if test="${colleague.member.memId ne member.memId }">
																	<div class="dropdown-item col-mail col-mail-1"
																		style="font-size: large;">
																		<input class="checkbox-wrapper-mail" type="checkbox"
																			name="chk" style="margin-right: 30px"
																			data-id="${colleague.colNum }"
																			value="${colleague.member.memName }">${colleague.member.memName }
																	</div>
																</c:if>
															</c:forEach>
															<div class="dropdown-item col-mail col-mail-1">
																<span>
																	<button type="button"
																		class="btn btn-primary btn-rounded waves-effect waves-light">확인</button>
																	<button type="button"
																		class="btn btn-secondary btn-rounded waves-effect waves-light"
																		id="cancelBtn">취소</button>
																</span>
															</div>
														</div>
													</div>
												</div>
											</div>
										</form>
									</div>
									<div class="modal-footer">
										<button type="button" id="insertChat"
											class="btn btn-primary waves-effect waves-light">생성</button>
										<button type="button" class="btn btn-light waves-effect"
											id="modalReset" data-bs-dismiss="modal">닫기</button>
									</div>
								</div>
								<!-- /.modal-content -->
							</div>
							<!-- /.modal-dialog -->
						</div>
						<div class="tab-content py-4">
							<div class="tab-pane show active">
								<div>
									<h5 class="font-size-16 mb-3">Online</h5>
									<ul class="list-unstyled chat-list" id="roomBody">
										<c:if test="${not empty chatList }">
											<c:forEach items="${chatList }" var="chat">
												<li class="active"><a href="#" name="chatRoomList"
													id="${chat.crNum }" data-id="${chat.crNum }"
													data-pjId="${pjId }">
														<div class="d-flex">
															<div class="align-self-center me-3">
																<i class="mdi mdi-circle text-success font-size-10"></i>
															</div>
															<div class="align-self-center me-3">
																<c:if test="${chat.crImg eq null }">
																	<img src="${cPath }/resources/noImg.png"
																		class="rounded-circle avatar-xs" alt="">
																</c:if>
																<img src="${cPath }/resources/chatImage/${chat.crImg }"
																	class="rounded-circle avatar-xs" alt="">
															</div>

															<div class="flex-grow-1 overflow-hidden">
																<p class="text-truncate mb-0">${chat.crName }</p>
																<c:if test="${not empty chat.collList }">
																	<h5 class="text-truncate font-size-14 mb-1">
																		<c:forEach items="${chat.collList }" var="coll">
																			${coll.member.memName }
																	</c:forEach>
																	</h5>
																</c:if>
															</div>
															<div class="font-size-11">${chat.crUpdate }</div>
														</div>
												</a></li>
											</c:forEach>
										</c:if>
										<c:if test="${empty chatList }">
											<li class="active"><a href="#">
													<div class="d-flex">
														<div class="align-self-center me-3">
															<i class="mdi mdi-circle text-success font-size-10"></i>
														</div>
														<div class="flex-grow-1 overflow-hidden">
															<h5 class="text-truncate font-size-14 mb-1"></h5>
															<p class="text-truncate mb-0">채팅방이 없습니다. 생성해주세요</p>
														</div>
														<div class="font-size-11"></div>
													</div>
											</a></li>
										</c:if>
									</ul>
								</div>
							</div>
						</div>
						<div class="tab-content pb-4">
							<div class="tab-pane" id="contact">
								<div class="pe-2" data-simplebar="init"
									style="max-height: 410px;">
									<div class="simplebar-wrapper"
										style="margin: 0px -8px 0px 0px;">
										<div class="simplebar-height-auto-observer-wrapper">
											<div class="simplebar-height-auto-observer"></div>
										</div>
										<div class="simplebar-mask">
											<div class="simplebar-offset"
												style="right: 0px; bottom: 0px;">
												<div class="simplebar-content-wrapper"
													style="height: auto; overflow: hidden;">
													<div class="simplebar-content"
														style="padding: 0px 8px 0px 0px;"></div>
												</div>
											</div>
										</div>
										<div class="simplebar-placeholder"
											style="width: 0px; height: 0px;"></div>
									</div>
									<div class="simplebar-track simplebar-horizontal"
										style="visibility: hidden;">
										<div class="simplebar-scrollbar"
											style="transform: translate3d(0px, 0px, 0px); display: none;"></div>
									</div>
									<div class="simplebar-track simplebar-vertical"
										style="visibility: hidden;">
										<div class="simplebar-scrollbar"
											style="transform: translate3d(0px, 0px, 0px); display: none;"></div>
									</div>
								</div>

							</div>
						</div>
					</div>

				</div>

			</div>
		</div>

		<div class="w-100 user-chat">
			<div class="card">
				<div class="p-4 border-bottom ">
					<div class="row">
						<div class="col-md-4 col-9">
							<h5 class="font-size-15 mb-1 text-truncate" id="roomSend"></h5>
							<p class="text-muted mb-0 text-truncate" id="roomTitle"></p>
						</div>
					</div>
				</div>


				<div class="px-lg-2" id="sendMessage" style="display: none;">
					<div class="chat-conversation p-3 my-4" data-simplebar="init"
						style="max-height: 600px;">
						<div class="simplebar-wrapper" style="margin: -16px;">
							<div class="simplebar-height-auto-observer-wrapper">
								<div class="simplebar-height-auto-observer"></div>
							</div>
							<div class="simplebar-mask">
								<div class="simplebar-offset" style="right: -17px; bottom: 0px;">
									<div class="simplebar-content-wrapper"
										style="height: auto; overflow: hidden scroll;" id="scroll">
										<div class="simplebar-content" style="padding: 16px;">
											<!-- 여기가 채팅방 그려지는데 -->
											<ul class="list-unstyled chat-list" id="chatBody" style="height: 600px;">
											</ul>
										</div>
									</div>
								</div>
							</div>
							<div class="simplebar-placeholder"
								style="width: auto; height: 688px;"></div>
						</div>
						<div class="simplebar-track simplebar-horizontal"
							style="visibility: hidden;">
							<div class="simplebar-scrollbar"
								style="transform: translate3d(0px, 0px, 0px); display: none;"></div>
						</div>
						<div class="simplebar-track simplebar-vertical"
							style="visibility: visible;">
							<div class="simplebar-scrollbar"
								style="height: 523px; transform: translate3d(0px, 0px, 0px); display: block;"></div>
						</div>
					</div>
					<div class="p-3 chat-input-section">
						<div class="row">
							<div class="col">
								<div class="position-relative">
									<input type="text" class="form-control chat-input"
										id="crContent"
										onkeyup="if(window.event.keyCode==13){webSend()}"
										placeholder="Enter Message...">
								</div>
							</div>
							<div class="col-auto">
								<button type="submit"
									class="btn btn-primary btn-rounded chat-send w-md waves-effect waves-light" onclick="webSend()">
									<span class="d-none d-sm-inline-block me-2">Send</span> <i
										class="mdi mdi-send"></i>
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<form id="searchForm">
	<input type="hidden" name="crName">
</form>
<form action="${cPath }/project/${pjId}/chatting/profile" method="POST"
	enctype="multipart/form-data" id="profileForm" style="display: none;">
	<sec:csrfInput />
	<input type="file" name="cooImage" accept="image/*">
</form>
<form method="GET" enctype="multipart/form-data" id="chatForm"
	style="display: none;">
	<input type="hidden" name="crNum"> <input type="hidden"
		name="crContentNum"> <input type="file" name="crImage">
</form>
<script
	src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<script>
	window.onload = function() {
		document.getElementById("searchUI").onsubmit = function() {
			return false;
		};
	};

	let memId = "${member.memId}";
	let crNum = "";

	function search() {
		let crName = $("[name=chatTitle]").val();
		$("#searchForm").find("input[name=crName]").val(crName);
		let data = $("#searchForm").serialize();
		$
				.ajax({
					url : "${cPath }/project/${pjId }/chatting/search",
					type : "GET",
					data : data,
					dataType : "json",
					success : function(resp) {
						let tbody = "";
						$("#roomBody").empty();
						console.log(resp);
						for (let i = 0; i < resp.length; i++) {
							let chatMemberName = "";
							for(let j=0; j<resp[i].collList.length; j++){
								chatMemberName += resp[i].collList[j].memName + " ";
							}
							tbody += '<li class="active"><a href="#" name="chatRoomList" id="'+ resp[i].crNum +'" data-id="';
							tbody += resp[i].crNum +'" data-pjId="'+ pjId +'">';
							tbody += '<div class="d-flex"><div class="align-self-center me-3"><i class="mdi mdi-circle text-success font-size-10"></i>';
							tbody += '</div><div class="align-self-center me-3">';
							if (resp[i].crImg) {
								tbody += '<img src="${cPath }/resources/chatImage/'+ resp[i].crImg +'" class="rounded-circle avatar-xs" alt="">';
							} else {
								tbody += '<img src="${cPath }/resources/noImg.png" class="rounded-circle avatar-xs" alt="">';
							}
							tbody += '</div>';
							tbody += '<div class="flex-grow-1 overflow-hidden"><h5 class="text-truncate font-size-14 mb-1"><p class="text-truncate mb-0">'
								+ resp[i].crName + '</p>'
								+ chatMemberName + '</h5></div>';
							tbody += '<div class="font-size-11">'
									+ resp[i].crUpdate + '</div>';
							tbody += '</div></a></li>';
						}
						$("#roomBody").append(tbody);
						$("#chatBody").empty();
					}
				})
	};

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
			$("input[name=crColleague]").val(ids);
			$("input[name=crColleagues]").val(names);
			$("input[name=crColleagues]").data("id", ids);
		} else {
			$("input[name=crColleague]").val("");
			$("input[name=crColleagues]").val("");
			$("input[name=crColleagues]").data("id", "");
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
						$("input[name=crColleague]").val(ids);
						$("input[name=crColleagues]").val(names);
						$("input[name=crColleagues]").data("id", ids);
						if ($(":input[name=chk]").length == $(":input[name=chk]:checked").length) {
							$("#allChk").prop("checked", true);
						} else {
							$("#allChk").prop("checked", false);
						}
					});

	// 	$("#insertChatRoom").on("click", function(event) {
	// 		event.preventDefault();
	// 		return false;
	// 	})

	$("#modalReset").on("click", function() {
		$("#insertChatForm")[0].reset();
	})

	$("#insertChat").on("click", function() {
		$("#insertChatForm").submit();
	})

	let pjId = "";

	$(document).on("click", "[name=chatRoomList]", function(event) {
		event.preventDefault();
		console.log($(this).data("pjid"));
		crNum = $(this).data("id");
		console.log(crNum);
		$("input[name=crContentNum]").val(0);
		pjId = $(this).data("pjid");
		$("#chatForm").find("input[name=crNum]").val(crNum);
		drawChat();
		$("#sendMessage").show();
		return false;
	})

	let textarea = $("#chatBody");
	let webSocket = new WebSocket("wss://192.168.36.19/Otion/websocket");
	webSocket.onerror = function(event) {
		alert(event.data);
	}
	webSocket.onopen = function(event) {
		console.log(event.data);
	}
	webSocket.onmessage = function(event) {
		console.log(event.data);
		drawChat();
	}

	function webSend() {
		let msg = $("#crContent").val();
		console.log(msg);
		let tbody = addSend();
		$("#chatBody").append(tbody);
		$("#scroll").scrollTop($("#scroll")[0].scrollHeight);
		webSocket.send(crNum + "," + memId + "," + msg);
		$("#crContent").val("");
	}

	function addSend(msg) {
		let collMemId = "${colleagueVO.memId}";
		let collProfile = "${colleagueVO.cooProfile}";

		let today = new Date();
		let year = today.getFullYear();
		let month = ('0' + (today.getMonth() + 1)).slice(-2);
		let day = ('0' + today.getDate()).slice(-2);
		let hours = ('0' + today.getHours()).slice(-2);
		let minutes = ('0' + today.getMinutes()).slice(-2);
		// 		let seconds = ('0' + today.getSeconds()).slice(-2); 

		let dateString = year + '-' + month + '-' + day;
		let timeString = hours + ':' + minutes;

		let tbody = "";
		tbody += '<li class="right">';
		tbody += '<div class="conversation-list">';
		tbody += '<div class="d-flex">';
		tbody += '<div class="flex-grow-1 arrow-right me-3">';
		tbody += '<div class="ctext-wrap">';
		tbody += '<div class="conversation-name">' + collMemId + '</div>';
		tbody += '<p>' + $("#crContent").val() + '</p>';
		tbody += '<p class="chat-time mb-0">';
		tbody += '<i class="mdi mdi-clock-outline align-middle me-1"></i>';
		tbody += dateString + "  " + timeString
		tbody += '</p></div></div>';
		if (collProfile == null) {
			tbody += '<img src="${cPath}/resources/images/noImg.png" class="rounded-circle avatar-xs" alt="" name="cooProfile">';
		} else {
			tbody += '<img src="${cPath}/resources/colleagueProfile/'+ collProfile +'"class="rounded-circle avatar-xs" alt="" name="cooProfile">';
		}
		tbody += '</div></div></li>';
		return tbody;
	}

	function drawChat() {
		$.ajax({
			url : "${cPath}/project/" + pjId + "/chatting",
			method : "GET",
			data : $("#chatForm").serialize(),
			dataType : "json",
			success : function(resp) {
				console.log(resp.crColleague);
				$("#roomSend").text(resp.crName)
				$("#roomTitle").empty();
				$("#chatBody").empty();
				let chatMemberName = "";
				for(let i=0; i<resp.collList.length; i++){
					chatMemberName += resp.collList[i].memName;
					if(i != resp.collList.length - 1){
						chatMemberName += ",";
					}
				}
				$("#roomTitle").append(
						'<i class="mdi mdi-circle text-success font-size-10 align-middle me-1"></i>'
								+ chatMemberName);
				let tbody = "";
				let chatList = resp.chatList;
				console.log(resp);
				if (resp.chatList && resp.chatList.length > 0) {
					tbody += drawChatting(chatList);
				} else {
					tbody += '<li><div class="chat-day-title">';
					tbody += '<span class="title">보낸 메시지가 존재하지 않습니다.</span>';
					tbody += '</div></li>';
				}
				$("#chatBody").append(tbody);
				$("#scroll").scrollTop($("#scroll")[0].scrollHeight);
			}
		});

	}
	let myInfo = "";
	function drawChatting(chatList) {
		let tbody = "";
		for (let i = 0; i < chatList.length; i++) {
			if (i == 0) {
				tbody += '<li><div class="chat-day-title">';
				tbody += '<span class="title">' + new Date(chatList[i].crDate)
						+ '</span>';
				tbody += '</div></li>';
			}
			if (chatList[i].memId == memId) {
				myInfo = chatList[i];
				tbody += '<li class="right">';
				tbody += '<div class="conversation-list">';
				tbody += '<div class="d-flex">';
				tbody += '<div class="flex-grow-1 arrow-right me-3">';
				tbody += '<div class="ctext-wrap">';
				tbody += '<div class="conversation-name">' + chatList[i].memId
						+ '</div>';
				tbody += '<p>' + chatList[i].crContent + '</p>';
				tbody += '<p class="chat-time mb-0">';
				tbody += '<i class="mdi mdi-clock-outline align-middle me-1"></i>';
				tbody += chatList[i].crDate;
				tbody += '</p></div></div>';
				if (chatList[i].colleague.cooProfile == null) {
					tbody += '<img src="${cPath}/resources/images/noImg.png" class="rounded-circle avatar-xs" alt="" name="cooProfile">';
				} else {
					tbody += '<img src="${cPath}/resources/colleagueProfile/'+ chatList[i].colleague.cooProfile +'"class="rounded-circle avatar-xs" alt="" name="cooProfile">';
				}
				tbody += '</div></div></li>';
			} else {
				tbody += '<li><div class="conversation-list"><div class="d-flex">';
				if (chatList[i].colleague.cooProfile == null) {
					tbody += '<img src="${cPath}/resources/images/noImg.png"class="rounded-circle avatar-xs" alt="">';
				} else {
					tbody += '<img src="${cPath}/resources/colleagueProfile/'+ chatList[i].colleague.cooProfile +'"class="rounded-circle avatar-xs" alt="">';
				}
				tbody += '<div class="flex-grow-1 arrow-left ms-3">';
				tbody += '<div class="ctext-wrap">';
				tbody += '<div class="conversation-name">' + chatList[i].memId
						+ '</div>';
				tbody += '<p>' + chatList[i].crContent + '</p>';
				tbody += '<p class="chat-time mb-0">';
				tbody += '<i class="mdi mdi-clock-outline align-middle me-1"></i>';
				tbody += chatList[i].crDate;
				tbody += '</p></div></div></div></div></li>';
			}
		}
		return tbody;
	}

	$(document).on("click", "[name=cooProfile]", function() {
		console.log("이미지 변경");
		$("input[name=cooImage]").click();
	});

	$("#profileForm").on("submit", function(event) {
		event.preventDefault();
		let url = this.action;
		let method = this.method;
		let data = $(this).serialize();
		$.ajax({
			url : url,
			method : method,
			data : data,
			dataType : "JSON",
			success : function(resp) {
				drawChat();
			}
		})
		return false;
	});

	function fn_chageProfile() {
		var frm = $("#profileForm")[0];
		let url = frm.action;
		//let method = frm.method;
		//let data = $(frm).serialize();

		var data = new FormData(frm);

		//data.append('attachment', $('[name="cooImage"]').files[0])

		$.ajax({
			url : url,
			type : "post",
			processData : false,
			contentType : false,
			data : data,
			dataType : "JSON",
			success : function(resp) {
				drawChat();
			}
		})
	}

	$("input[name=cooImage]").on("change", function() {
		//$("#profileForm").submit();
		fn_chageProfile();
	})
</script>