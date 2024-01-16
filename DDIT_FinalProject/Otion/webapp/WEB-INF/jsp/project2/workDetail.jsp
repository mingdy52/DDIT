
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 8. 10.      작성자명      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<sec:authentication property="principal.realMember" var="member" />
<style>
.changeblue {
	background-color: #0B0B61;
	color: white;
	border: 1px solid #E6E6E6;
	padding: 5px 24px 5px 24px;
}

.card {
	box-shadow: 0px;
	-webkit-box-shadow: none;
}

.delBtn {
	padding: 2px;
	color: red;
}

.errorMsg {
	color: red;
	margin-top: 10px;
}

.addmemid2 {
	display: flex;
	float: left;
}

.addmemid1 {
	display: flex;
	float: left;
}

.workStatus {
	padding: 5px 24px 5px 24px;
	border: 1px solid #E6E6E6;
}
</style>
<div>

	<div style="display: flex;">
		<div class="col-xl-8">
			<div class="card">
				<div id="currentWork" class="card-body">
					<c:forEach items="${workList}" var="work">
						<c:if test="${work.workNum eq workNum}">
							<div style="display: none;" class="pjId">${work.pjId}</div>
							<h4 id="writer" style="display: none;" class="card-title">${member.memId}</h4>

							<div class="clickRequest1 mb-3 justify-content-center"
								style="display: flex;">
								<button name="request1" class="workStatus">요청</button>
								<button name="request2" class="workStatus">진행</button>
								<button name="request3" class="workStatus">피드백</button>
								<button name="request4" class="workStatus">완료</button>
								<button name="request5" class="workStatus">보류</button>

							</div>
							<div>
								<div class="workreqValidate"></div>
							</div>
							<form id="currentWorkForm">
								<div>
									<input type="hidden" name="workNum" value="${work.workNum}" />
								</div>
								<div>
									<input type="hidden" name="workReq" value="${work.workReq}" />
								</div>
								<div>
									<input type="hidden" name="workPriority"
										value="${work.workPriority}" />
								</div>

								<div class="mb-3">
									<input id="workReq2" type="hidden" value="${member.memId}">
								</div>

								<div class="mb-3 d-flex">

									<div style="width: 40px;">
										<c:if test="${not empty markVO }">
											<div class="rating-symbol-background ri-bookmark-fill"
												id="workMark"
												style="font-size: 40px; visibility: visible; margin-top: 1px; width: 40px;"></div>
										</c:if>
										<c:if test="${empty markVO }">
											<div class="rating-symbol-background ri-bookmark-line"
												id="workMark"
												style="font-size: 40px; visibility: visible; margin-top: 1px; width: 40px;"></div>
										</c:if>
									</div>
									<div class="mb-3">
										<select name="workStatusCode" class="form-select" required
											aria-label="select example"
											style="font-size: 18px; margin-left: 5px; margin-top: 8px; text-align: center;">
											<option>우선순위</option>
											<option>낮음</option>
											<option>보통</option>
											<option>높음</option>
											<option>긴급</option>
										</select>
										<div>
											<div class="workStatusValidate"></div>
										</div>
										<div style="display: none;" id="workStatusCode">${work.workStatusCode}</div>
									</div>
									<div>
										<input type="text" class="form-control" name="workTitle"
											maxlength="20" value="${work.workTitle}"
											style="width: 700px; margin-left: 20px; font-size: 18px; margin-top: 8px;"
											placeholder="20글자이내로 작성해주세요">
									</div>
								</div>

								<div>
									<input class="form-control memId1" style="display: none;"
										value="" name="memId" />
									<div style="margin-top: 10px; display: inline-block;">
										<c:if test="${work.workReq eq member.memId}">
											<a id="addmem2" class="btn btn-secondary"
												data-bs-toggle="modal" style="float: right;"
												data-bs-target="#memModal">담당자 추가</a>
										</c:if>
										<c:if test="${work.workReq ne member.memId}">
											<div>담당자&nbsp;</div>
										</c:if>
									</div>
								</div>
								<div class="d-flex" id="manager1">
									<c:forEach items="${work.memberList}" var="mem">
										<div class="addmemid1">
											<p>${mem}</p>
											<c:if test="${work.workReq eq member.memId}">
												<a class="remove1 delBtn">삭제</a>
											</c:if>
										</div>
									</c:forEach>

								</div>
								<div class="d-flex" style="width: 100%;">
									<label style="padding: 10px; float: right; margin-left: 49%;">시작일</label>
									<div>
										<input class="form-control" type="date" name="workStart"
											value="${work.workStart}" id="example-date-input">
									</div>
									<label style="padding: 10px;">마감일</label>
									<div>
										<input class="form-control" type="date" name="workEnd"
											value="${work.workEnd}" id="example-date-input">
									</div>
								</div>

								<div class="mb-3">
									<h5>업무내용</h5>
									<div>
										<textarea name="workContent" class="form-control"
											style="width: 86%; margin-left: 28px; min-height: 160px;">${work.workContent}</textarea>
									</div>
								</div>
								<div style="width: 100%;">
									<c:forEach items="${workList}" var="wo">
										<c:if test="${wo.parentWorkNum eq workNum}">
											<c:set value="${wo.workNum}" var="check"></c:set>
										</c:if>
									</c:forEach>
									<c:if test="${not empty check}">
									<h5>✔하위업무목록</h5>
									</c:if>
									<table
										class="table table-centered table-nowrap table-hover mb-0"
										style="width: 90%;">
										<tbody id="childList">
											<c:forEach items="${workList}" var="wo">
												<c:if test="${wo.parentWorkNum eq workNum}">
													<tr>
														<td class="number"></td>
														<td style="display: none;" class="workNum">${wo.workNum}</td>
														<td class="workPriority">${wo.workPriority}</td>
														<td class="workStatusCode">${wo.workStatusCode}</td>
														<td class="workTitle">${wo.workTitle}</td>
														<td class="workStart">${wo.workStart}</td>
														<td class="workEnd">${wo.workEnd}</td>
														<td class="workContent" style="display: none;">${wo.workContent}</td>
														<td style="display: none;" class="memId"><c:forEach
																items="${wo.memberList}" var="me">
        										 				${me}:
        										 			</c:forEach></td>
														<td class="modifyWork" data-bs-toggle='modal'
															data-bs-target='#childModal'>수정</td>
														<td
															onclick="location.href='${cPath}/project/${wo.pjId}/work/${wo.workNum}'">상세보기</td>
														<td class='mdi mdi-close-thick'
															onclick="removeChildWork('${wo.workNum}')"
															style='color: red;'></td>
													</tr>
												</c:if>
											</c:forEach>
										</tbody>
									</table>
								</div>
								<div class="mb-0" style="margin-top: 2%;">
									<div style="text-align: right; padding: 10px; padding-right: 100px; margin-bottom: 30px;">
										<c:if test="${work.parentWorkNum eq null}">
											<a id="childformbtn" class="btn btn-info workButtons"
												data-bs-toggle="modal" data-bs-target="#childModal">하위업무추가</a>
										</c:if>
										<a id="childformbtn" class="btn btn-danger plusIssue"
											data-bs-toggle="modal" data-bs-target="#issueModal">업무이슈추가</a>
										<a id="workCodePen" class="btn btn-danger workSave"
											data-bs-toggle="modal" data-bs-target="#codePenModal">업무
											진행하기</a> <a id="formSubmitBtn"
											class="btn btn-dark waves-effect waves-light me-1 workSave"
											style="margin-right: 100px;">업무저장</a>
											<c:if test="${colleagueVO.projAuthCode eq 'PA01'}">
											<a id="formRemoevBtn"
											class="btn btn-dark waves-effect waves-light me-1"
											style="margin-right: 100px;">삭제</a>
											</c:if>

									</div>
								</div>
							</form>
							<section class="mb-3">
								<div class="card bg-light" style="width: 90%;">
									<div class="card-body ">
										<!-- Comment form-->
										<div id="insertAttatch">
											<i class=" ri-upload-2-fill" style="float: right:;"></i>첨부파일
										</div>
										<div id="replyForm" class="mb-4" style="display: flex;">
											<textarea class="form-control mb-2 mr-2" rows="3"
												placeholder="댓글을 작성하세요" maxlength="150"
												name="blReplyContent" required="required"></textarea>
											<button style="margin-left: 2%; height: 81px; width: 18%;"
												onclick="writeReply('0')"
												class="btn btn-primary waves-effect waves-light">댓글작성</button>
										</div>
										<div>
											<ui id="reply" style="list-style:none;"> </ui>
										</div>
									</div>
								</div>
							</section>
						</c:if>
					</c:forEach>
				</div>

			</div>
		</div>

		<div class="col-xl-4" style="padding-left: 10px;">
			<h4 style="text-align: center; margin-top: 30px;">📣해당업무의 이슈목록</h4>
			<table class="table table-hover mb-0" id="issueTable"
				style="text-align: center;">
				<thead>
					<th>No</th>
					<th>이슈제목</th>
					<th>이슈생성일자</th>
				</thead>

				<tbody id="issueFormBody">
					<c:choose>
						<c:when test="${not empty issue}">
							<c:forEach items="${issue}" var="issue" begin="0" end="4">
								<tr style="cursor: pointer;" onclick="goToIssue('${issue.issueNum}')">
									<td class="num"></td>
									<td>${issue.issueTitle}</td>
									<td style="display: none;">${issue.issueNum}</td>
									<td>${issue.issueDate}</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td class="noContent" colspan="4">해당하는 글이 없습니다.</td>

							</tr>
						</c:otherwise>
					</c:choose>

				</tbody>

			</table>

		</div>

	</div>
	<!-- childModal  -->
	<div class="modal fade" id="childModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="myModalLabel">Modal Heading</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div id="childWork" class="modal-body">

					<div class="clickRequest2 mb-3" style="display: flex;">
						<button name="request1" class="workStatus">요청</button>
						<button name="request2" class="workStatus">진행</button>
						<button name="request3" class="workStatus">피드백</button>
						<button name="request4" class="workStatus">완료</button>
						<button name="request5" class="workStatus">보류</button>

					</div>
					<div class="workreqValidate"></div>
					<form id="childWorkForm">
						<div>
							<input type="hidden" name="workNum" value="" />
						</div>
						<div>
							<input type="hidden" name="workReq" value="" />
						</div>
						<div>
							<input type="hidden" name="workPriority" value="" />
						</div>

						<div class="mb-3">
							<input id="workReq2" type="hidden" value="">
						</div>

						<div class="mb-3">
							<label>제목</label>
							<div>
								<input type="text" name="workTitle" class="form-control"
									maxlength="20" value="" placeholder="20글자이내로 작성해주세요" autocomplete="off">
							</div>
						</div>


						<div>
							<input style="display: none;" class="form-control memId2"
								type="text" value="" name="memId" />
							<div style="margin-top: 10px; display: inline-block;">
								<a id="addmem2" class="btn btn-secondary" data-bs-toggle="modal"
									style="float: right;" data-bs-target="#memModal">담당자 추가</a>
							</div>
						</div>

						<div class="d-flex" id="manager2"></div>

						<div class="mb-3" style="margin-top: 2%;">
							<label>시작일</label>
							<div>
								<input class="form-control" type="date" name="workStart"
									value="" id="example-date-input">
							</div>
						</div>
						<div class="mb-3">
							<label>마감일</label>
							<div>
								<input class="form-control" type="date" name="workEnd" value=""
									id="example-date-input">
							</div>
						</div>

						<div class="mb-3">
							<label>진행률</label> <select name="workStatusCode"
								class="form-select" required aria-label="select example">
								<option>우선순위</option>
								<option>낮음</option>
								<option>보통</option>
								<option>높음</option>
								<option>긴급</option>
							</select>
							<div>
								<div class="workStatusValidate"></div>
							</div>
						</div>
						<div class="mb-3">
							<label>업무내용</label>
							<div>
								<textarea name="workContent" class="form-control">${work.workContent}</textarea>
							</div>
						</div>

					</form>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-light waves-effect"
						data-bs-dismiss="modal">Close</button>
					<button type="button" name="childFormSaveBtn"
						class="btn btn-primary waves-effect waves-light">등록</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>

	<!-- memModal  -->
	<div class="modal fade" id="memModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog"
			style="box-shadow: 0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24);">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="myModalLabel">원하는 담당자를 추가해주세요</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<table class="table table-borderless mb-0"
						style="text-align: center;">
						<c:forEach items="${memberList}" var="member">
							<tr>
								<td style="font-size: 18px;"><p>${member.memId}</p></td>
								<td><button class="btn btn-light waves-effect memadd1">추가</button></td>
							</tr>
						</c:forEach>
					</table>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-light waves-effect"
						data-bs-dismiss="modal">Close</button>

				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>


	<!-- issueModal  -->
	<div class="modal fade" id="issueModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="myModalLabel">업무이슈 작성</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div id="issueBody" class="modal-body">
					<form id="issueForm">
						<input type="hidden" name="workNum" value="" />
						<div class="mb-3">
							<label>제목</label>
							<div>
								<input type="text" name="issueTitle" class="form-control"
									maxlength="20" value="" placeholder="20글자이내로 작성해주세요">
							</div>
						</div>

						<div class="mb-3">
							<label>업무내용</label>
							<div>
								<textarea name="issueContent" class="form-control">${work.workContent}</textarea>
							</div>
						</div>
						<div class="mb-3">
							<input type="file" value="첨부파일" name="issueAttatch"
								accept="image/*" />
						</div>
					</form>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-light waves-effect"
						data-bs-dismiss="modal">Close</button>
					<button type="button"
						class="btn btn-primary waves-effect waves-light issueFormSaveBtn">등록</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<div class="modal fade" id="codePenModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="myModalLabel">업무 하기</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div>
						<p class="codepen" data-height="300"
							data-default-tab="html,result" data-slug-hash="PoeYOay"
							data-editable="true" data-user="kojunghyeon"
							style="height: 300px; box-sizing: border-box; display: flex; align-items: center; justify-content: center; border: 2px solid; margin: 1em 0; padding: 1em;">
							<span>See the Pen <a
								href="https://codepen.io/kojunghyeon/pen/PoeYOay"> Untitled</a>
								by KoJungHyeon (<a href="https://codepen.io/kojunghyeon">@kojunghyeon</a>)
								on <a href="https://codepen.io">CodePen</a>.
							</span>
						</p>
					</div>
					<script async
						src="https://cpwebassets.codepen.io/assets/embed/ei.js"></script>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-light waves-effect"
						data-bs-dismiss="modal">Close</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
</div>


<input type="file" class="replyAttatch" name="replyAttatch"
	style="display: none;">
<input type="file" class="replyChildAttatch" name="replyAttatch"
	style="display: none;">

<form method="POST" id="removeParentWorkForm" action="${cPath}/project/${pjId}/work/${workNum}/parentdelete">
	<sec:csrfInput />
	<input type="hidden" name="delYn">
</form>


<form method="POST" id="workMarkForm">
	<sec:csrfInput />
	<input type="hidden" name="delYn">
</form>

<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script>
	var header = '${_csrf.headerName}';
	var token = '${_csrf.token}';
	var number = 0;
	var myModalEl = document.getElementById('childModal');
	var issueModalE1 = document.getElementById('issueModal');
	var mem = [];
	mem[0] = [];
	mem[1] = [];
	mem[2] = [];
	var a = [];
	a[0] = [];
	a[1] = [];
	a[2] = [];
	let discrim = [];
	let parentWoReplyNum;
	let inputId = []
	inputId[0] = $("#currentWork").find("input");
	inputId[1] = $("#childWork").find("input");
	firstStart();
	let checkChildReply = 0;


	$("#workMark").on("click", function() {
		console.log("북마크 클릭");
		let url = window.location.href;
		$("#workMarkForm").attr("action", url + "/workMark")
		console.log(url);
		if ($(this).hasClass("ri-bookmark-fill") == true) {
			$(this).removeClass("ri-bookmark-fill");
			$(this).addClass("ri-bookmark-line");
			$("#workMarkForm").find("input[name=delYn]").val("Y");
			// 해당 북마크 해제
		} else {
			// 해당 북마크 등록
			console.log("얘 부터 실행?")
			$(this).addClass("ri-bookmark-fill");
			$(this).removeClass("ri-bookmark-line");
			$("#workMarkForm").find("input[name=delYn]").val("N");
		}
		$("#workMarkForm").submit();
	});

	$("#insertAttatch").on("click", function() {
		$(".replyAttatch").click();
	});

	function firstStart() {
		if ($("#writer").text() != $("#currentWork").find("input[name=workReq]").val()) {
				$("#currentWorkForm").find("input").attr("readonly", "true");
				$("#currentWorkForm").find("textarea").attr("readonly", "true");
	 			$("#currentWork").find(".workStatus").attr("readonly", "true");
 			
 		
 				let listCheck=0;
 			
 				for(var i=0; i<$("#manager1").find("p").length; i++){
 						if($("#manager1").eq(i).find("p").text()==$("#writer").text()){
 							listCheck = 1;
 						}
 					
 					}
 			
	 			if(listCheck==0){
	 				
	 				$("#currentWork").find(".workStatus").attr("disabled", "true");
	 				$("#currentWorkForm").find("select").attr("disabled", "true");
	 				$(".workSave").remove();
	 				$(".plusIssue").remove();
	 			}
	
				$(".addForms").remove();
				$(".workButtons").remove();
				$(".modifyWork").remove();
				$(".mdi").remove();
				$('#addmem2').remove();
				let addmemid1 = $('.addmemid1').html();
	
				$('.addmemid1').html("&nbsp;" + addmemid1).attr("style",
						"display:flex;");
				$(".remove1").remove();
		}
		
		
		let idx = 1;
		$("#childWorkForm").find("input[name=workReq]").val(
				$("#currentWorkForm").find("input[name=workReq]").val());
		$("#currentWorkForm").find("select[name=workStatusCode]").val(
				$("#workStatusCode").text()).prop("selected", true);

		let inputVal = $("#currentWorkForm").find("input[name=workPriority]")
				.val();

		if (inputVal == "요청") {
			$(".clickRequest1").find("button[name=request1]").addClass(
					"changeblue");
		} else if (inputVal == "진행") {
			$(".clickRequest1").find("button[name=request2]").addClass(
					"changeblue");
		} else if (inputVal == "피드백") {
			$(".clickRequest1").find("button[name=request3]").addClass(
					"changeblue");
		} else if (inputVal == "완료") {
			$(".clickRequest1").find("button[name=request4]").addClass(
					"changeblue");
		} else if (inputVal == "보류") {
			$(".clickRequest1").find("button[name=request5]").addClass(
					"changeblue");

		}

		for (var i = 0; i < $("#issueFormBody").find("tr").length; i++) {
			$("#issueFormBody").find("tr").eq(i).find(".num").text(i + 1);
		}

		for (var i = 0; i < $(".addmemid" + idx).length; i++) {
			$(".memId" + idx)
					.val(
							$(".memId" + idx).val()
									+ $(".addmemid" + idx).find("p").eq(i)
											.text() + ":");
			mem[idx].push($(".addmemid" + idx).find("p").eq(i).text());

			a[idx]++;
		}
		for (i = 0; i < $("#childList").find("tr").length; i++) {
			number++;
			$("#childList").find("tr").eq(i).find(".number").text(number);
		}

		$.ajax({
			url : "${cPath}/project/" + $(".pjId").text() + "/work/"
					+ $("#currentWorkForm").find("input[name=workNum]").val()
					+ "/reply",
			data : {
				workNum : $("#currentWorkForm").find("input[name=workNum]")
						.val(),
				pjId : $(".pjId").text()
			},
			dataType : "json" // text, html, json, xml, script -> main type : text, 파일 업로드 처리를 비동기로? (FormData)
			,
			success : function(resp, status, jqXHR) {
				console.log("reply", resp);
				let index = [];
				index = makeReply(resp);
				$("#reply").append(index);
			},
			error : function(jqXHR, status, error) {
				console.log(jqXHR);
				console.log(status);
				console.log(error);
			}
		});

	}

	function makeReply(reply) {
		console.log(reply);
		let index = [];
		for (var i = 0; i < reply.length; i++) {

			let woReplyNum = "";
			let div;
			if (!reply[i].parentWoReplyNum) {
				div = $("<hr><li></li>");
				let attatch = reply[i].attatch;
				woReplyNum = reply[i].woReplyNum
				console.log("woReplyNum", woReplyNum);
				let data = "&nbsp" + reply[i].memId + "&nbsp|&nbsp"
						+ reply[i].woReplyDate;
				if (reply[i].colleague.cooProfile) {
					div
							.append("<div class='parentReply replyDiv'><div class='parentInfo' style='display:flex;'>"
									+ "<img src='${cPath}/resources/colleagueProfile/"+ reply[i].colleague.cooProfile +"'class='rounded-circle avatar-xs'>"
									+ "</img><span class='woReplyNum' style='display:none;'>"
									+ reply[i].woReplyNum
									+ "</span><span style='font-weight:bold; margin-left:2%; margin-top:1%; '>"
									+ data
									+ "</span><span style='cursor: pointer; margin-top:1%; margin-left:2%;' class='addChildReply'>댓글쓰기</span></div><div style='margin-left:7%;'>"
									+ reply[i].woReplyContent + "</div></div>");

				} else {
					div
							.append("<div class='parentReply'><div class='parentInfo' style='display:flex;'>"
									+ "<span class='ri-account-circle-fill fa-2x'>"
									+ "</span><span class='woReplyNum' style='display:none;'>"
									+ reply[i].woReplyNum
									+ "</span><span style='font-weight:bold; margin-left:2%; margin-top:1%; '>"
									+ data
									+ "</span><span style='cursor: pointer; margin-top:1%; margin-left:2%;' class='addChildReply'>댓글쓰기</span></div><div style='margin-left:7%;'>"
									+ reply[i].woReplyContent + "</div></div>");

				}
				if ($("#writer").text() == reply[i].memId) {
					div
							.find(".parentInfo")
							.append(
									"<span style='cursor: pointer; margin-top:1%; margin-left:2%;' class='modifyReply'>수정</span><span style='cursor: pointer; margin-top:1%; margin-left:2%;' class='removeParentReply'>삭제</span>");
				}

				if (attatch != null) {
					div
							.find(".parentReply")
							.append(
									"<br><div class='attatchNum' style='display:none;'>"
											+ attatch.attatchNum
											+ "</div><a style='margin-left:5%;' href='${cPath}/download?attatchNum="
											+ attatch.attatchNum
											+ "&attatchOrder="
											+ attatch.attatchOrder + "'>"
											+ attatch.originNm + "</a>");
				}

			}
			for (var j = 0; j < reply.length; j++) {
				if (woReplyNum == reply[j].parentWoReplyNum) {
					let data = "&nbsp" + reply[j].memId + "&nbsp|&nbsp"
							+ reply[j].woReplyDate;
					let attatch = reply[j].attatch;
					if (reply[i].colleague.cooProfile) {
						div
								.append("<br><li style='margin-left:10%;'><div class='childReply replyDiv'><div class='childInfo' style='display:flex;'><img src='${cPath}/resources/colleagueProfile/"+ reply[i].colleague.cooProfile +"'class='rounded-circle avatar-xs'>"
										+ "</img><span class='woReplyNum' style='display:none;'>"
										+ reply[j].woReplyNum
										+ "</span><span style='font-weight:bold; margin-left:2%; margin-top:1%; '>"
										+ data
										+ "</span></div><div style='margin-left:7%;'>"
										+ reply[j].woReplyContent
										+ "</div></div></li>")
					} else {
						div
								.append("<br><li style='margin-left:10%;'><div class='childReply'><div class='childInfo' style='display:flex;'><span class='ri-account-circle-fill fa-2x'>"
										+ "</span><span class='woReplyNum' style='display:none;'>"
										+ reply[j].woReplyNum
										+ "</span><span style='font-weight:bold; margin-left:2%; margin-top:1%; '>"
										+ data
										+ "</span></div><div style='margin-left:7%;'>"
										+ reply[j].woReplyContent
										+ "</div></div></li>")
					}

					if (attatch != null) {
						div
								.find(".childReply")
								.append(
										"<br><div class='attatchNum' style='display:none;'>"
												+ attatch.attatchNum
												+ "</div><a style='margin-left:10%;' href='${cPath}/download?attatchNum="
												+ attatch.attatchNum
												+ "&attatchOrder="
												+ attatch.attatchOrder + "'>"
												+ attatch.originNm + "</a>");
					}

					if ($("#writer").text() == reply[j].memId) {
						if (reply[i].colleague.cooProfile) {
							div
									.find(".childInfo")
									.html(
											"<img src='${cPath}/resources/colleagueProfile/"+ reply[i].colleague.cooProfile +"'class='rounded-circle avatar-xs'>"
													+ "</img><span class='woReplyNum' style='display:none;'>"
													+ reply[j].woReplyNum
													+ "</span><span style='font-weight:bold; margin-left:2%; margin-top:1%; '>"
													+ data
													+ "</span><span style='cursor: pointer; margin-top:1%; margin-left:2%;' class='modifyReply'>수정</span><span style='cursor: pointer; margin-top:1%; margin-left:2%;' class='removeReply'>삭제</span>");
						} else {

							div
									.find(".childInfo")
									.html(
											"<span class='ri-account-circle-fill fa-2x'></span><span class='woReplyNum' style='display:none;'>"
													+ reply[j].woReplyNum
													+ "</span><span style='font-weight:bold; margin-left:2%; margin-top:1%; '>"
													+ data
													+ "</span><span style='cursor: pointer; margin-top:1%; margin-left:2%;' class='modifyReply'>수정</span><span style='cursor: pointer; margin-top:1%; margin-left:2%;' class='removeReply'>삭제</span>");
						}
					}
				}
			}
			index.push(div);
		}

		return index;

	}

	$("#formRemoevBtn").on("click",function(){
		 swal('삭제하시겠습니까?','',"warning",{
			 buttons: {
			  삭제: true,
			  cancel: "취소",
			 },
			})
			.then((value) => {
					  switch (value) {
				  
				  case "삭제":
				  swal('삭제되었습니다.');
				  $("#removeParentWorkForm").submit();
				  break;
				  
				  default:
					  swal('취소되었습니다.');  
			  }
			  
			});
	
	
		
	});
	
	
	$("#reply").on("click",".removeReply",function() {
				 swal('삭제하시겠습니까?','',"warning",{
					 buttons: {
					  삭제: true,
					  cancel: "취소",
					 },
					})
					.then((value) => {
							  switch (value) {
						  
						  case "삭제":
						  swal('삭제되었습니다.');
							var data;
							var data = new FormData();
							var attatchNum = $(this).closest(".replyDiv").find(
									".attatchNum").text();
							$(".replyChildAttatch").val("");
							if (attatchNum) {
								data.append("attatchNum", attatchNum);
							}
							data.append("woReplyNum", $(this).closest("div").find(
									".woReplyNum").text());

							$.ajax({
								url : "${cPath}/project/"
										+ $(".pjId").text()
										+ "/work/"
										+ $("#currentWorkForm").find(
												"input[name=workNum]").val()
										+ "/reply/delete",
								data : data,
								processData : false,
								contentType : false,
								type : 'POST',
								dataType : "json" // text, html, json, xml, script -> main type : text, 파일 업로드 처리를 비동기로? (FormData)
								,
								beforeSend : function(xhr) {
									xhr.setRequestHeader(header, token);
								},
								success : function(resp, status, jqXHR) {
									let index = [];
									index = makeReply(resp);
									$("#reply").html(index);

								},
								error : function(jqXHR, status, error) {
									console.log(jqXHR);
									console.log(status);
									console.log(error);
								}

							});
						  
						  break;
						  
						  default:
							  swal('취소되었습니다.');  
					  }
					  
					});
			
				
			

			});

	$("#reply").on(
			"click",
			".removeParentReply",
			function() {
					 swal('삭제하시겠습니까?','',"warning",{
						 buttons: {
						  삭제: true,
						  cancel: "취소",
						 },
						})
						.then((value) => {
								  switch (value) {
							  
							  case "삭제":
							  swal('삭제되었습니다.');
								var data;
								$(".replyChildAttatch").val("");
								var data = new FormData();
								var attatchNum = $(this).closest(".replyDiv").find(
										".attatchNum").text();
								if (attatchNum) {
									data.append("attatchNum", attatchNum);
								}
								data.append("woReplyNum", $(this).closest("div").find(
										".woReplyNum").text());
								data.append("parentWoReplyNum", $(this).closest("div")
										.find(".woReplyNum").text())
								$.ajax({
									url : "${cPath}/project/"
											+ $(".pjId").text()
											+ "/work/"
											+ $("#currentWorkForm").find(
													"input[name=workNum]").val()
											+ "/reply/delete",
									data : data,
									processData : false,
									contentType : false,
									type : 'POST',
									dataType : "json" // text, html, json, xml, script -> main type : text, 파일 업로드 처리를 비동기로? (FormData)
									,
									beforeSend : function(xhr) {
										xhr.setRequestHeader(header, token);
									},
									success : function(resp, status, jqXHR) {
										let index = [];
										index = makeReply(resp);
										$("#reply").html(index);

									},
									error : function(jqXHR, status, error) {
										console.log(jqXHR);
										console.log(status);
										console.log(error);
									}

								});
							  
							  break;
							  
							  default:
								  swal('취소되었습니다.');  
						  }
						  
						});

			});

	$("#formSubmitBtn").on("click", function(event) {

		let check = checkInput(0);

		console.log(check);
		if (check == 7) {

			let data = $("#currentWorkForm").serialize();
			$.ajax({
				data : data,
				dataType : "json" // text, html, json, xml, script -> main type : text, 파일 업로드 처리를 비동기로? (FormData)
				,
				success : function(resp, status, jqXHR) {
					swal("수정되었습니다", "", "success");

				},
				error : function(jqXHR, status, error) {
					console.log(jqXHR);
					console.log(status);
					console.log(error);
				}
			});
		}

	});

	$("#reply")
			.on(
					"click",
					".modifyReply",
					function() {
						$(".replyChildAttatch").val("");
						$(".replyAttatch").val("");
						let url = "${cPath}/project/"
								+ $(".pjId").text()
								+ "/work/"
								+ $("#currentWorkForm").find(
										"input[name=workNum]").val()
								+ "/reply/update";
						$("#reply").find("#childReplyForm").remove();
						$(this).closest(".replyDiv").find(".chilAttatch")
								.remove();
						$(".replyChildAttatch").val("");
						$(this)
								.parents("div")
								.eq(1)
								.append(
										"<div class='chilAttatch'><i class=' ri-upload-2-fill insertChildAttatch' style='float: right:;'></i>첨부파일</div>"
												+ "<div id='childReplyForm' class='mb-4' style='display: flex;'></div>")
						$(this)
								.parents("div")
								.eq(1)
								.find("#childReplyForm")
								.append(
										"<textarea style='margin-top:2%;' class='form-control mb-2 mr-2' rows='3' placeholder='댓글을 작성하세요' maxlength='150' name='blReplyContent' required='required'></textarea>");
						$(this)
								.parents("div")
								.eq(1)
								.find("#childReplyForm")
								.append(
										"<button style='margin-top:2%; margin-left:2%; height:81px; width:18%; height:' class='btn btn-primary waves-effect waves-light'>수정</button>"
												+ "<button style='margin-top:2%; margin-left:2%; height:81px; width:18%; height:' class='btn btn-primary waves-effect waves-light cancelModify'>취소</button>");
						$(this)
								.parents("div")
								.eq(1)
								.find("#childReplyForm")
								.find("button")
								.click(
										function() {
											var file = $(".replyChildAttatch")[0].files[0];
											let fileForm = new FormData();
											var attatchNum = $(this).closest(
													".replyDiv").find(
													".attatchNum").text();
											fileForm
													.append(
															"woReplyNum",
															$(this)
																	.parents(
																			"div")
																	.eq(1)
																	.find(
																			".woReplyNum")
																	.text());
											fileForm
													.append(
															"woReplyContent",
															$(this)
																	.closest(
																			"li")
																	.find(
																			"textarea")
																	.val()
																	.replace(
																			/(?:\r\n|\r|\n)/g,
																			'<br />'));
											if (file) {
												fileForm.append("replyAttatch",
														file);
											}
											if (attatchNum) {
												fileForm.append("attatchNum",
														attatchNum);
											}
											$.ajax({
												url : url,
												data : fileForm,
												processData : false,
												contentType : false,
												type : 'POST',
												dataType : "json" // text, html, json, xml, script -> main type : text, 파일 업로드 처리를 비동기로? (FormData)
												,
												beforeSend : function(xhr) {
													xhr.setRequestHeader(
															header, token);
												},
												success : function(resp,
														status, jqXHR) {
													let index = [];
													index = makeReply(resp);
													$("#reply").html(index);

												},
												error : function(jqXHR, status,
														error) {
													console.log(jqXHR);
													console.log(status);
													console.log(error);
												}
											});
										});

					});

	function goToIssue(issueNum) {

		window.location = "${cPath}/project/${pjId}/issue/" + issueNum;

	};

	function checkInput(input) {
		let check = 0;
		let workStatus;
		let memClass;
		if (input == 0) {
			workStatus = $("#currentWork");
			memClass = $(".memId1");
		} else {
			workStatus = $("#childWork");
			memClass = $(".memId2");

		}
		for (var i = 0; i < inputId[input].length; i++) {
			if (!(i == 0 || i == 1 || i == 2 || i == 3 || i == 5)) {
				inputId[input].eq(i).closest("div").find("span").remove();
			}
		}
		workStatus.find(".workreqValidate").closest("div").find("span")
				.remove();
		if (!workStatus.find("input[name=workPriority]").val()) {
			workStatus.find(".workreqValidate").closest("div").append(
					"<span class='errorMsg' >필수 입력값입니다.</span>");
		} else {
			check++;
		}
		workStatus.find("textarea[name=workContent]").closest("div").find(
				"span").remove();
		if (!workStatus.find("textarea[name=workContent]").val()) {
			workStatus.find("textarea[name=workContent]").closest("div")
					.append("<span class='errorMsg' >필수 입력값입니다.</span>");
		} else {
			check++;
		}
		workStatus.find(".workStatusValidate").closest("div").find("span")
				.remove();
		if (workStatus.find("select[name=workStatusCode]").val() == "우선순위") {
			workStatus.find(".workStatusValidate").closest("div").append(
					"<span class='errorMsg'>필수 입력값입니다.</span>");
		} else {
			check++;
		}

		memClass.closest("div").find("span").remove();
		if (!memClass.val()) {
			memClass.closest("div").append(
					"<span class='errorMsg'>필수 입력값입니다.</span>");
		} else {
			check++;
		}

		for (var i = 0; i < inputId[input].length; i++) {
			console.log(inputId[input].eq(i).val());
			if (!(i == 0 || i == 1 || i == 2 || i == 3 || i == 5)) {
				if (!inputId[input].eq(i).val()) {
					inputId[input].eq(i).closest("div").append(
							"<span class='errorMsg' >필수 입력값입니다.</span>");
				} else {
					check++;
				}

			}
		}
		return check;
	};

	$("#reply")
			.on(
					"click",
					".addChildReply",
					function() {
						parentWoReplyNum = $(this).closest(".parentReply")
								.find(".woReplyNum").text();
						$("#reply").find("#childReplyForm").remove();
						$(".chilAttatch").remove();
						$(".insertChildAttatch").val();
						$(this)
								.closest(".parentReply")
								.append(
										"<div class='chilAttatch'><i class=' ri-upload-2-fill insertChildAttatch' style='float: right:;'></i>첨부파일</div>"
												+ "<div id='childReplyForm' class='mb-4' style='display: flex;'></div>")
						$(this)
								.closest(".parentReply")
								.find("#childReplyForm")
								.append(
										"<textarea style='margin-top:2%;' class='form-control mb-2 mr-2' rows='3' placeholder='댓글을 작성하세요' maxlength='150' name='blReplyContent' required='required'></textarea>");
						$(this)
								.closest(".parentReply")
								.find("#childReplyForm")
								.append(
										"<button style='margin-top:2%; margin-left:2%; height:81px; width:18%; height:' class='btn btn-primary waves-effect waves-light replyWriteBtn'>댓글작성</button>"
												+ "<button style='margin-top:2%; margin-left:2%; height:81px; width:18%; height:' class='btn btn-primary waves-effect waves-light cancelModify'>취소</button>");
						$(this).closest(".parentReply").find(".replyWriteBtn")
								.attr("onclick", "writeReply('1')")

					});

	$("#reply").on("click", ".insertChildAttatch", function() {
		$(".replyChildAttatch").click();
	});

	$(".clickRequest1,.clickRequest2")
			.on(
					"click",
					"button",
					function() {
						console.log($(this).closest("div").html());
						if ($(this).closest("div").hasClass("clickRequest1")) {
							$("#currentWorkForm").find(
									"input[name=workPriority]").val(
									$(this).text());
						} else {
							$("#childWorkForm")
									.find("input[name=workPriority]").val(
											$(this).text());
						}
						$(this).closest("div").find("button").removeClass(
								"changeblue");
						$(this).attr("class", "changeblue workStatus");

					});

	$("#reply").on("click", ".cancelModify", function() {

		$("#reply").find("#childReplyForm").remove();
		$(".insertChildAttatch").val("");

		$(".chilAttatch").remove();

	});

	$(document).on(
			"click",
			".remove1,.remove2",
			function() {
				var idx = 0;
				if ($(this).hasClass("remove1")) {
					idx = 1;
				} else if ($(this).hasClass("remove2")) {
					idx = 2;
				}

				$(this).closest("div").remove();
				a[idx]--;
				mem[idx].splice(mem[idx].indexOf($(this).closest("div").find(
						"p").text()), 1);

				$(".memId" + idx).val("");

				for ( var i in mem[idx]) {

					$(".memId" + idx).val(
							$(".memId" + idx).val() + mem[idx][i] + ":");
				}

			});

	$(".memadd1,.memadd2").on(
			"click",
			function() {
				var idx = 0;
				if ($(this).hasClass("memadd1")) {
					idx = 1;
				} else if ($(this).hasClass("memadd2")) {
					idx = 2;
				}

				for (var i = 0; i < a[idx] + 1; i++) {
					console.log($("#manager" + idx).find('div').eq(a[idx] - i)
							.find('p').text());
					console.log($(this).closest("div").find('p').text());
					console.log($("#manager" + idx).find('div').eq(a[idx] - i)
							.find('p').text())
					if ($("#manager" + idx).find('div').eq(a[idx] - i)
							.find('p').text() == $(this).closest("tr")
							.find('p').text()) {
						swal("이미 선택된 사용자 입니다.", "", "error");
						discrim.push("false");
					}
				}

				if (discrim.indexOf("false") == -1) {
					$("#manager" + idx).html(
							$("#manager" + idx).html()
									+ "<div  class='addmemid"+idx+"'><p>"
									+ $(this).closest("tr").find('p').text()
									+ "</p>"
									+ "<a class='delBtn remove"+idx+"'>삭제</a>"
									+ "</div>");

					a[idx]++;
					mem[idx].push($(this).closest("tr").find('p').text());
					swal("추가되었습니다", "", "success");

					for (var i = 0; i < mem[idx].length; i++) {

						$(".memId" + idx).val(
								$(".memId" + idx).val() + mem[idx][i] + ":");

					}
					while (discrim.length > 0) {
						discrim.pop();
					}

				} else {

					while (discrim.length > 0) {
						discrim.pop();
					}
				}

			});

	$("#childformbtn").on("click", function() {
		if ($("#memModal").find(".memadd1")) {
			$(".memadd1").addClass("memadd2");
			$(".memadd1").removeClass("memadd1");
		}
	});

	$("#childList").on(
			"click",
			".modifyWork",
			function() {
				if ($("#memModal").find(".memadd1")) {
					$(".memadd1").addClass("memadd2");
					$(".memadd1").removeClass("memadd1");
				}
				let idx = 2;

				$("#childWorkForm").find("select[name=workStatusCode]").val(
						$(this).closest("tr").find(".workStatusCode").text());
				$("#childWorkForm").find("input[name=workPriority]").val(
						$(this).closest("tr").find(".workPriority").text());
				let inputVal = $("#childWorkForm").find(
						"input[name=workPriority]").val();

				if (inputVal == "요청") {
					$(".clickRequest2").find("button[name=request1]").addClass(
							"changeblue");
				} else if (inputVal == "진행") {
					$(".clickRequest2").find("button[name=request2]").addClass(
							"changeblue");
				} else if (inputVal == "피드백") {
					$(".clickRequest2").find("button[name=request3]").addClass(
							"changeblue");
				} else if (inputVal == "완료") {
					$(".clickRequest2").find("button[name=request4]").addClass(
							"changeblue");
				} else if (inputVal == "보류") {
					$(".clickRequest2").find("button[name=request5]").addClass(
							"changeblue");

				}

				mem[2] = $.trim($(this).closest("tr").find(".memId").text())
						.split(":");
				mem[2].splice(-1, 1);
				for (var i = 0; i < mem[2].length; i++) {
					a[2]++;
					$("#manager" + idx).html(
							$("#manager" + idx).html()
									+ "<div class='addmemid"+idx+"'><p>"
									+ mem[2][i] + "</p>"
									+ "<a class='delBtn remove"+idx+"'>삭제</a>"
									+ "</div>");

				}
				$("#childWorkForm").find(".memId2").val(
						$.trim($(this).closest("tr").find(".memId").text()));
				$("#childWorkForm").find("input[name=workNum]").val(
						$(this).closest("tr").find(".number").text());
				$("#childWorkForm").find("input[name=workTitle]").val(
						$(this).closest("tr").find(".workTitle").text());
				$("#childWorkForm").find("input[name=workStart]").val(
						$(this).closest("tr").find(".workStart").text());
				$("#childWorkForm").find("input[name=workEnd]").val(
						$(this).closest("tr").find(".workEnd").text());
				$("#childWorkForm").find("textarea[name=workContent]").val(
						$(this).closest("tr").find(".workContent").text());

			});

	$("button[name=childFormSaveBtn]")
			.on(
					"click",
					function() {
						let check = checkInput(1);
						console.log(check);
						if (check == 7) {
							swal("하위 업무 등록이 완료되었습니다", "", "success");
							console.log($("#childWorkForm").find(
									"input[name=workNum]").val());
							for (var i = 0; i < $("#childList").find("tr").length; i++) {
								if ($("#childList").find("tr").eq(i).find(
										".number").text() == $("#childWorkForm")
										.find("input[name=workNum]").val()) {
									let num = i;
									$("#childWorkForm").find(
											"input[name=workNum]").val(
											$("#childList").find("tr").eq(i)
													.find(".workNum").text());
									let number = $("#childList").find("tr").eq(
											i).find(".number").text();
									let data = $("#childWorkForm").serialize();
									$
											.ajax({
												data : data,
												dataType : "json" // text, html, json, xml, script -> main type : text, 파일 업로드 처리를 비동기로? (FormData)
												,
												success : function(resp,
														status, jqXHR) {
													$("#childWorkForm")
															.find(
																	"input[name=workNum]")
															.val(number);

													let childNum = $(
															"#childList").find(
															"tr");
													let workNum = $(
															"#childList").find(
															"tr").eq(num).find(
															".workNum").text();

													$("#childList")
															.find("tr")
															.eq(num)
															.html(
																	makeSingleTr(
																			workNum)
																			.html());

													for (i = 0; i < $(
															"#childList").find(
															"tr").length; i++) {

														$("#childList")
																.find("tr")
																.eq(i)
																.find(".number")
																.text(i + 1);
													}
													$("#childModal").modal(
															'hide');

												},
												error : function(jqXHR, status,
														error) {
													console.log(jqXHR);
													console.log(status);
													console.log(error);
												}

											});

								}
							}

							if (!$("#childWorkForm")
									.find("input[name=workNum]").val()) {
								let data = $("#childWorkForm").serialize();
								$
										.ajax({
											url : "${cPath}/project/PJ001/work/"
													+ $("#currentWorkForm")
															.find(
																	"input[name=workNum]")
															.val()
													+ "/addchild",
											data : data,
											dataType : "json" // text, html, json, xml, script -> main type : text, 파일 업로드 처리를 비동기로? (FormData)
											,
											success : function(resp, status,
													jqXHR) {
												console.log(resp.pjId,
														resp.workNum);
												let url = "${cPath}/project/"
														+ resp.pjId + "/work/"
														+ resp.workNum;
												$("#childList")
														.find("tr")
														.eq(
																$("#childList")
																		.find(
																				"tr").length - 1)
														.find(".workDetail")
														.attr(
																"onclick",
																"location.href='"
																		+ url
																		+ "'");
												$("#childList")
														.find("tr")
														.eq(
																$("#childList")
																		.find(
																				"tr").length - 1)
														.find(".workNum").text(
																resp.workNum);

												number++;

												$("#childModal").modal('hide');

												$("#childList")
														.html(
																$("#childList")
																		.html()
																		+ "<tr>"
																		+ makeSingleTr(
																				resp.workNum)
																				.html()
																		+ "</tr>");

												for (i = 0; i < $("#childList")
														.find("tr").length; i++) {

													$("#childList").find("tr")
															.eq(i).find(
																	".number")
															.text(i + 1);
												}

											},
											error : function(jqXHR, status,
													error) {
												console.log(jqXHR);
												console.log(status);
												console.log(error);
											}
										});

							}

						}
					});

	let makeSingleTr = function(numWork) {
		let child = $("#childWorkForm");
		let workPriority = child.find("input[name=workPriority]");
		let workTitle = child.find("input[name=workTitle]");
		let persone = child.find(".memId2");
		let parentWorkNum = $("#currentWorkForm").find("input[name=workNum]");
		let workStart = child.find("input[name=workStart]");
		let workContent = child.find("textarea[name=workContent]");
		let workEnd = child.find("input[name=workEnd]");
		let workStatusCode = child
				.find("select[name=workStatusCode] option:selected");
		let url = "${cPath}/project/" + $(".pjId").text() + "/work/" + numWork;
		let tr = $("<tr>");

		tr
				.append(
						$("<td class='number'>"),
						$("<td style='display: none;' class='workNum'>").html(
								numWork),
						$("<td class='workPriority'>").html(workPriority.val()),
						$("<td class='workStatusCode'>").html(
								workStatusCode.text()),
						$("<td class='workTitle'>").html(workTitle.val()),
						$("<td class='workStart'>").html(workStart.val()),
						$("<td class='workEnd'>").html(workEnd.val()),
						$("<td style='display:none;' class='workContent'>")
								.html(workContent.val()),
						$("<td style='display:none;' class='memId'>").html(
								persone.val()),
						$(
								"<td class='modifyWork' data-bs-toggle='modal' data-bs-target='#childModal'>")
								.html("수정"),
						$("<td class='workDetail'>").html("상세보기").attr(
								"onclick", "location.href='" + url + "'"),
						$(
								"<td class='mdi mdi-close-thick' style='color: red;'>")
								.attr("onclick",
										"removeChildWork('" + numWork + "')")

				);
		$("#childWorkForm").find("input[name=workNum]").val(number);
		//console.log("check1:",workPriority.val())
		//console.log("work:",workPriority);

		return tr;

	}

	function removeChildWork(workNum) {

			 swal('삭제하시겠습니까?','',"warning",{
				 buttons: {
				  삭제: true,
				  cancel: "취소",
				 },
				})
				.then((value) => {
						  switch (value) {
					  
					  case "삭제":
					  swal('삭제되었습니다.');
					  for (i = 0; i < $("#childList").find("tr").length; i++) {

							if ($("#childList").find("tr").eq(i).find(".workNum").text() == workNum) {
								$("#childList").find("tr").eq(i).remove();
							}
							$("#childList").find("tr").eq(i).find(".number").text(i + 1);
						}
						$.ajax({
							url : "${cPath}/project/" + $(".pjId").text() + "/work/"
									+ workNum + "/delete",
							data : {
								workNum : workNum
							},
							type : "POST",
							dataType : "json" // text, html, json, xml, script -> main type : text, 파일 업로드 처리를 비동기로? (FormData)
							,
							beforeSend : function(xhr) {
								xhr.setRequestHeader(header, token);
							},
							success : function(resp, status, jqXHR) {

							},
							error : function(jqXHR, status, error) {
								console.log(jqXHR);
								console.log(status);
								console.log(error);
							}

						});
						
					  
					  break;
					  
					  default:
						  swal('취소되었습니다.');  
				  }
				  
				});

	};

	function writeReply(index) {
		let data;
		let woReplyContent;
		var file;
		let fileForm = new FormData();
		if (index == 0) {
			woReplyContent = $('#replyForm').find('textarea').val().replace(
					/(?:\r\n|\r|\n)/g, '<br />');
			file = $(".replyAttatch")[0].files[0];
			fileForm.append("workNum", $('#currentWorkForm').find(
					'input[name=workNum]').val());
			fileForm.append("workReq", $("#writer").text());
			fileForm.append("pjId", $('.pjId').text());
			fileForm.append("woReplyContent", $('#replyForm').find('textarea')
					.val().replace(/(?:\r\n|\r|\n)/g, '<br />'));
			if (file) {
				fileForm.append("replyAttatch", file);
			}
		} else {
			woReplyContent = $('#childReplyForm').find('textarea').val()
					.replace(/(?:\r\n|\r|\n)/g, '<br />');
			file = $(".replyChildAttatch")[0].files[0];
			fileForm.append("workNum", $('#currentWorkForm').find(
					'input[name=workNum]').val());
			fileForm.append("workReq", $("#writer").text());
			fileForm.append("woReplyContent", $('#childReplyForm').find(
					'textarea').val().replace(/(?:\r\n|\r|\n)/g, '<br />'));
			fileForm.append("pjId", $('.pjId').text());
			fileForm.append("parentWoReplyNum", parentWoReplyNum);
			if (file) {
				insertAttatch
				fileForm.append("replyAttatch", file);
			}

		}

		if (woReplyContent) {
			$.ajax({
				url : "${cPath}/project/"
						+ $(".pjId").text()
						+ "/work/"
						+ $("#currentWorkForm").find("input[name=workNum]")
								.val() + "/reply/add",
				data : fileForm,
				processData : false,
				contentType : false,
				type : 'POST',
				dataType : "json",
				beforeSend : function(xhr) {
					xhr.setRequestHeader(header, token);
				},
				success : function(resp, status, jqXHR) {
					$(".replyAttatch").val("");
					$(".replyChildAttatch").val("");
					let index = [];
					index = makeReply(resp);
					$("#reply").html(index);

				},
				error : function(jqXHR, status, error) {
					console.log(jqXHR);
					console.log(status);
					console.log(error);
				}

			});
		}

	};

	$(".issueFormSaveBtn")
			.on(
					"click",
					function() {
						let checkIssue = 0;
						let issueAttatch = $("#issueForm").find(
								"input[name=issueAttatch]")[0].files[0];
						let issueTitle = $("#issueForm").find(
								"input[name=issueTitle]").val();
						let issueContent = $("#issueForm").find("textarea")
								.val().replace(/(?:\r\n|\r|\n)/g, '<br />');
						let data = new FormData();
						data.append("issueTitle ", issueTitle);
						data.append("issueContent  ", issueContent);
						data.append("issueReq ", $("#writer").text());
						if (issueTitle) {
							checkIssue++;
						} else {
							$("#issueForm")
									.find("input")
									.eq(1)
									.closest("div")
									.append(
											"<div class='issueAlert errorMsg'>필수입력값입니다.</div>")
						}

						if (issueContent) {
							checkIssue++;
						} else {
							$("#issueForm")
									.find("textarea")
									.closest("div")
									.append(
											"<div class='issueAlert errorMsg'>필수입력값입니다.</div>")
						}

						if (issueAttatch) {
							data.append("issueAttatch", issueAttatch);
						}

						$("#issueForm").find("input[name=workNum]").val(
								$("#currentWorkForm").find(
										"input[name=workNum]").val());

						let url = "${cPath}/project/${pjId}/work/${workNum}/issue";

						if (checkIssue == 2) {
							$.ajax({
										url : url,
										data : data,
										processData : false,
										contentType : false,
										type : 'POST',
										dataType : "json"// text, html, json, xml, script -> main type : text, 파일 업로드 처리를 비동기로? (FormData)
										,
										beforeSend : function(xhr) {
											xhr.setRequestHeader(header, token);
										},
										success : function(resp, status, jqXHR) {
											swal("이슈 등록이 완료되었습니다", "",
													"success");
											let tr = $("<tr style='cursor: pointer;'>")
											tr
													.append(
															$("<td class=num>")
																	.html(""),
															$("<td>")
																	.html(
																			resp.issueTitle),
															$(
																	"<td class='issueNum' style='display :none;'>")
																	.html(
																			resp.issueNum),
															$("<td>")
																	.html(
																			resp.issueDate
																					.substr(
																							0,
																							10)));
											tr.attr("onclick", "goToIssue('"
													+ resp.issueNum + "')");
											$("#issueFormBody").find("tr")
													.eq(4).remove();
											$("#issueFormBody").find("tr")
													.find(".noContent")
													.closest("tr").remove();
											let html = $("#issueFormBody")
													.html();
											$("#issueFormBody").html(tr);
											$("#issueFormBody").append(html);
											for (var i = 0; i < $(
													"#issueFormBody")
													.find("tr").length; i++) {
												$("#issueFormBody").find("tr")
														.eq(i).find(".num")
														.text(i + 1);

											}
											$("#issueModal").modal('hide');
										},
										error : function(jqXHR, status, error) {
											console.log(jqXHR);
											console.log(status);
											console.log(error);
										}

									});
						}

					});

	myModalEl
			.addEventListener(
					'hidden.bs.modal',
					function(event) {
						let child = $("#childWorkForm");
						let workPriority = child
								.find("input[name=workPriority]");
						let workTitle = child.find("input[name=workTitle]");
						let persone = child.find(".memId2");
						let parentWorkNum = $("#currentWorkForm").find(
								"input[name=workNum]");
						let workStart = child.find("input[name=workStart]");
						let workContent = child
								.find("textarea[name=workContent]");
						let workEnd = child.find("input[name=workEnd]");
						let workStatusCode = child
								.find("select[name=selectCode] option:selected");
						let tr = $("<tr  data-bs-toggle='modal' data-bs-target='#childModal'>");
						if ($("#memModal").find(".memadd2")) {
							$(".memadd2").addClass("memadd1");
							$(".memadd2").removeClass("memadd2")
						}
						workPriority.val("");
						workTitle.val("");
						workStart.val("");
						workContent.val("");
						workEnd.val("");
						$("#childWorkForm").find("select[name=workStatusCode]")
								.val("선택해주세요");
						child.find(".memId2").val("");
						$(".remove2").closest("div").remove();
						while (mem[2].length > 0) {
							mem[2].pop();
						}
						a[2] = 0;
						$("#childWorkForm").find("input[name=workNum]").val("");
						child.find("select[name=selectCode]").val("선택해주세요");
						$(".clickRequest2").find("button").removeClass(
								"changeblue");
						for (var i = 0; i < inputId[1].length; i++) {
							if (!(i == 0 || i == 1 || i == 2 || i == 5)) {
								inputId[1].eq(i).closest("div").find("span")
										.remove();
							}
						}
						$("#childWork").find(".workreqValidate").closest("div")
								.find("span").remove();
						$("#childWork").find("textarea").closest("div").find(
								"span").remove();
						$("#childWork").find(".workStatusValidate").closest(
								"div").find("span").remove();
						$(".memId2").closest("div").find("span").remove();
					});

	issueModalE1.addEventListener('hidden.bs.modal', function(event) {

		$("#issueBody").find("input").val("");
		$("#issueBody").find("textarea").val("");
		$(".issueAlert").remove();

	});
	
	
</script>

