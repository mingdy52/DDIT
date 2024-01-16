<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script type="text/javascript"
	src="https://cdn.iamport.kr/js/iamport.payment-1.1.8.js"></script>
<style>
.buyList {
	margin-top: 120px;
	height: 400px;
	width: 350px;
	vertical-align: top;
	padding: 10px;
	display: inline-block;
	height: 400px;
	box-shadow: 0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24);
}

.downassume {
	color: black;
	font-weight: bold;
}

.card {
	box-shadow: 0px;
}

.buyList:hover {
	box-shadow: 0 14px 28px rgba(0, 0, 0, 0.25), 0 10px 10px
		rgba(0, 0, 0, 0.22);
}

.btngoBuy {
	width: 300px;
	text-align: center;
	vertical-align: bottom;
	color: white;
	background-color: black;
	font-size: 20px;
	padding: 10px 24px 10px 24px;
	border-radius: 10px;
}

#datatable td {
	height: 20px;
}

.btngoBuy:hover {
	color: white;
	background-color: black;
	font-size: 20px;
}

.payment {
	text-align: center;
}

.tdname {
	min-width: 100px;
}

.datebox {
	width: 200px;
}
</style>
<sec:authentication property="principal.realMember" var="member" />
<c:if test="${home eq 'Y' }">
	<c:if test="${not empty project }">
		<ul class="nav">
			<li class="nav-mine"><a class="projMenu"
				href="${cPath}/project/${project.pjId }/home"> <span>홈</span>
			</a></li>
			<li class="nav-mine"><a class="projMenu"
				href="${cPath}/project/${project.pjId }/work"> <span>업무</span>
			</a></li>
			<li class="nav-mine"><a
				href="${cPath }/project/${project.pjId }/git" class="projMenu">
					<span>공유 저장소</span>
			</a></li>
			<li class="nav-mine"><a class="projMenu"
				href="${cPath}/project/${project.pjId}/issue/"><span>이슈</span> </a></li>
			<c:if test="${project.pjAdminId eq member.memId}">
				<li class="nav-mine"><a class="projMem"
					href="${cPath}/project/${project.pjId }/colleague"> <span>인원관리</span>
				</a></li>
				<c:if test="${project.pjAdminId eq member.memId }">
					<li class="nav-mine"><a class="projMem"
						data-id="${project.pjId }" name="listBtn" data-bs-toggle="modal"
						data-bs-target="#selectCooForm" href="#"> <span>프로젝트 신청
								관리</span>
					</a></li>
					<c:if test="${empty payVO }">
						<li class="nav-mine"><a class="projMem"
							data-bs-toggle="modal" data-bs-target="#exampleModalFullscreen"
							href="#"> <span>프로젝트 결제</span>
						</a></li>
					</c:if>
				</c:if>
			</c:if>
		</ul>
	</c:if>
	<c:if test="${empty project}">
		<ul class="nav">
			<li class="nav-mine"><a class="projMem"
				data-id="${project.pjId }" name="listBtn" data-bs-toggle="modal"
				data-bs-target="#selectCooForm" href="#"> <span>프로젝트 신청
						관리</span>
			</a></li>
			<li class="nav-mine"><a class="projMem" data-bs-toggle="modal"
				data-bs-target="#exampleModalFullscreen" href="#"> <span>프로젝트
						결제</span>
			</a></li>
		</ul>
	</c:if>
</c:if>
<%-- 협업 결제 모달 창 --%>
<div id="exampleModalFullscreen" class="modal fade" tabindex="-1"
	aria-hidden="true">
	<div class="modal-dialog modal-fullscreen">
		<div class="modal-content">
			<div class="modal-header">
				<!-- 				<h3 class="modal-title" id="exampleModalFullscreenLabel">협업 툴 -->
				<!-- 					구매</h3> -->
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body payment">
				<h3>proJect 기능</h3>
				<br>
				<br>
				<br>
				<ul style="text-align: center; align-items: center;">
					<li
						style="display: inline-block; margin: 0px 20px; font-size: 15px;">
						<div>
							<div>
								<span><svg viewBox="0 0 16 16" class="thinCheck"
										style="width: 14px; height: 14px; display: inline; fill: inherit; flex-shrink: 0; margin-top: 2px; color: blue;">
																<path fill="currentColor"
											d="M6.385 14.162c.362 0 .642-.15.84-.444L13.652 3.71c.144-.226.205-.417.205-.602 0-.485-.341-.82-.833-.82-.335 0-.54.123-.746.444l-5.926 9.4-3.042-3.903c-.205-.267-.417-.376-.718-.376-.492 0-.848.348-.848.827 0 .212.075.417.253.629l3.541 4.416c.24.3.492.437.848.437z"></path></svg>
								</span><span
									class="global-text global-text-font-family-sans global-text-weight-regular global-text-color-dark global-text-size-body"
									_msthash="6196099" _msttexthash="64675546">간트 차트를 통한 업무
									조회</span>
							</div>
						</div>
					</li>
					<li
						style="display: inline-block; margin: 0px 20px; font-size: 15px;">
						<div>
							<div>
								<span><svg viewBox="0 0 16 16" class="thinCheck"
										style="width: 14px; height: 14px; display: inline; fill: inherit; flex-shrink: 0; margin-top: 2px; color: blue;">
																<path fill="currentColor"
											d="M6.385 14.162c.362 0 .642-.15.84-.444L13.652 3.71c.144-.226.205-.417.205-.602 0-.485-.341-.82-.833-.82-.335 0-.54.123-.746.444l-5.926 9.4-3.042-3.903c-.205-.267-.417-.376-.718-.376-.492 0-.848.348-.848.827 0 .212.075.417.253.629l3.541 4.416c.24.3.492.437.848.437z"></path></svg>
								</span><span
									class="global-text global-text-font-family-sans global-text-weight-regular global-text-color-dark global-text-size-body"
									_msthash="6196099" _msttexthash="64675546">캘린더를 통한 일정 관리</span>
							</div>
						</div>
					</li>
					<li
						style="display: inline-block; margin: 0px 20px; font-size: 15px;">
						<div>
							<div>
								<span><svg viewBox="0 0 16 16" class="thinCheck"
										style="width: 14px; height: 14px; display: inline; fill: inherit; flex-shrink: 0; margin-top: 2px; color: blue;">
																<path fill="currentColor"
											d="M6.385 14.162c.362 0 .642-.15.84-.444L13.652 3.71c.144-.226.205-.417.205-.602 0-.485-.341-.82-.833-.82-.335 0-.54.123-.746.444l-5.926 9.4-3.042-3.903c-.205-.267-.417-.376-.718-.376-.492 0-.848.348-.848.827 0 .212.075.417.253.629l3.541 4.416c.24.3.492.437.848.437z"></path></svg>
								</span><span
									class="global-text global-text-font-family-sans global-text-weight-regular global-text-color-dark global-text-size-body"
									_msthash="6196099" _msttexthash="64675546">GitHub를 이용한
									공유 저장소</span>
							</div>
						</div>
					</li>
					<li
						style="display: inline-block; margin: 0px 20px; font-size: 15px;">
						<div>
							<div>
								<span><svg viewBox="0 0 16 16" class="thinCheck"
										style="width: 14px; height: 14px; display: inline; fill: inherit; flex-shrink: 0; margin-top: 2px; color: blue;">
																<path fill="currentColor"
											d="M6.385 14.162c.362 0 .642-.15.84-.444L13.652 3.71c.144-.226.205-.417.205-.602 0-.485-.341-.82-.833-.82-.335 0-.54.123-.746.444l-5.926 9.4-3.042-3.903c-.205-.267-.417-.376-.718-.376-.492 0-.848.348-.848.827 0 .212.075.417.253.629l3.541 4.416c.24.3.492.437.848.437z"></path></svg>
								</span><span
									class="global-text global-text-font-family-sans global-text-weight-regular global-text-color-dark global-text-size-body"
									_msthash="6196099" _msttexthash="64675546">CodePen을 이용한
									실시간 업무 처리</span>
							</div>
						</div>
					</li>
				</ul>
				<div class="d-flex flex-wrap gap-5 justify-content-center">
					<c:forEach items="${projectProdList }" var="prod">
						<div class="buyList">
							<div>
								<div>
									<section>
										<div>
											<div>
												<div>
													<i class=" fas fa-users" style="font-size: 72px;"></i>
												</div>
												<div>
													<h5 style="color: black; font-size: 40px;">${prod.cprodName }</h5>
												</div>
											</div>
											<hr style="color: gray;">
											<!-- 										<div> -->
											<!-- 											<p style="font-size: 20px;">소규모로 프로젝트를 하고 싶은 당신을 위해</p> -->
											<!-- 										</div> -->
										</div>
										<div class="jsx-3158339386 body">
											<div class="jsx-3158339386 vertical-margin">
												<div class="jsx-f9142e2b4be520ea price-wrap">
													<div class="jsx-f9142e2b4be520ea price"
														style="margin-top: 30px;">
														<h2>${prod.cprodPrice }(${prod.cprodPeopleNum }인)</h2>
														<h4 style="color: gray;">* 1달 기준(1인당 2천원)</h4>
													</div>
												</div>
											</div>
										</div>
									</section>
									<div style="margin-top: 50px;">
										<a class="btngoBuy" name="buyBtn" data-id="${prod.cprodNum }"
											data-title="${prod.cprodName }"
											data-price="${prod.cprodPrice }"
											data-count="${prod.cprodPeopleNum }">결제하기</a>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary waves-effect"
					data-bs-dismiss="modal">Close</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<div id="buyModal" class="modal fade" tabindex="-1"
	aria-labelledby="myModalLabel" style="display: none;"
	aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="display-6 mb-0" id="myModalLabel"></h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body" style="text-align: center; color: black;">
				<h3>――――협업상품정보――――</h3>
				<div>
					<table class="table table-borderless mb-0">
						<tr>
							<td class="tdname">서비스명</td>
							<td>프로젝트 협업 툴 상품</td>
						</tr>
						<tr>
							<td class="tdname">구매자</td>
							<td>${member.memId }</td>
						</tr>
						<tr>
							<td class="tdname">구매상품</td>
							<td id="buyTitle"></td>
						</tr>
					</table>
					<h3>――――――――――――――</h3>
					<h4 id="buyPrice"></h4>
					<h3>――――――――――――――</h3>
				</div>
				<div class="checkbox_group">
					<input type="checkbox" id="check_all"> <label
						for="check_all"><h5>아래 내용에 전체 동의합니다.</h5></label><br>
					<div id="chk">
						<input type="checkbox" name="check" class="normal"> <label
							for="check_1">협업툴 서비스 이용 약관 동의</label> <br> <input
							type="checkbox" name="check" class="normal"> <label
							for="check_2">상품 인원수 및 가격 구매 동의</label> <br> <input
							type="checkbox" name="check" class="normal"> <label
							for="check_3">하단 유의사항의 확인 및 동의</label> <br>
					</div>
				</div>
				<div>
					<hr>
					<h6>
						<p style="font-weight: bold;">✱✱유의사항</p>
						<ul style="list-style-type: '-'; text-align: left;">
							<li>해당 상품은 구매 과정에서 사이트 관리자에게 정보가 제공될 수 있습니다.</li>
							<li>해당 협업툴 구매의 경우 현금 영수증 발급이 불가합니다.</li>
							<li>청약철회 등 환불 기준은 상품 상세 페이지의 “청약철회 등 환불 안내”를 참조해 주시기 바랍니다.</li>
						</ul>
					</h6>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-light waves-effect"
					data-bs-dismiss="modal"
					onclick="$('#exampleModalFullscreen').modal('show')">취소</button>
				<button type="button" id="goBuyBtn"
					class="btn btn-primary waves-effect waves-light">결제하기</button>
			</div>
		</div>
	</div>
</div>
<%-- 모달창 추후에 뺄수도 있음 --%>
<div class="modal fade bs-example-modal-xl show" tabindex="-1"
	id="selectCooForm" style="display: none;" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-xl">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="">
					<div class="" id="collBody">
						<h5 class="modal-title"
							style="font-weight: bold; text-align: right; padding-right: 24px; margin-bottom: 20px;">협업
							신청 내역</h5>
						<table id="datatable" class="table mb-0"
							style="text-align: center;">
							<thead>
								<tr role="row">
									<th>신청자ID</th>
									<th>신청자이름</th>
									<th>자기소개</th>
									<th><i class=" ri-download-2-line"></i>이력서</th>
									<th>승인</th>
									<th>거절</th>
								</tr>
							</thead>
							<tbody id="cooFormBody">

							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<!-- Toogle to second dialog -->
				<!-- 				<button type="button" class="btn btn-light waves-effect" -->
				<!-- 					data-bs-dismiss="modal">Close</button> -->
			</div>
		</div>
	</div>
</div>
<div class="modal fade bs-example-modal-xl show" tabindex="-1"
	id="correctColleague" style="display: none;" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-xl">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">팀원 역할 부여</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<form method="post" enctype="application/x-www-form-urlencoded"
				id="cooFormStatus">
				<sec:csrfInput />
				<div class="modal-body">
					<div class="card">
						<div class="card-body">
							<div class="row mb-3">
								<input type="hidden" name="_method" value="put" /> <input
									type="hidden" name="pjId"> <label
									for="example-text-input" class="col-sm-2 col-form-label">신청자
									아이디</label>
								<div class="col-sm-10">
									<input class="form-control" type="text" name="memId"
										placeholder="신청자 아이디" readonly="readonly">
								</div>
							</div>
							<div class="row mb-3">
								<label for="example-search-input"
									class="col-sm-2 col-form-label">업무 역할</label>
								<div class="col-sm-10">
									<select class="form-select" name="workRoleCode">
										<option selected="">업무 역할을 정해주세요</option>
										<c:forEach items="${comCodeVO }" var="comCodeVO">
											<option value="${comCodeVO.comCode }">${comCodeVO.comCodeNm }</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
			<div class="modal-footer">
				<!-- Toogle to second dialog -->
				<button type="button" class="btn btn-light waves-effect"
					onclick="f_addColleague()">등록</button>
				<button type="button" class="btn btn-light waves-effect"
					data-bs-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
<%-- 모달창 추후에 뺄수도 있음 --%>
<div class="modal fade bs-example-modal-xl show" tabindex="-1"
	id="newProject" style="display: none;" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-xl">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title"></h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<form>
				<div class="modal-body" id="createProject">
					<div class="card">
						<div style="width: 70%; padding-top: 30px; margin: auto;">
							<div class="row mb-3">
								<!-- 								<label for="example-text-input" class="col-sm-2 col-form-label">프로젝트 -->
								<!-- 									명</label> -->
								<div class="">
									<h5>프로젝트명</h5>
									<input class="form-control" type="text" name="pjName" max="13"
										required="required" placeholder="프로젝트 명">
								</div>
							</div>
							<div class="row mb-3">
								<!-- 								<label for="example-search-input" class="col-sm-2 col-form-label">총 인원수</label> -->
								<div class="">
									<h5>인원수</h5>
									<select class="form-select" name="pjPnum">
										<option selected="">인원수를 정해주세요</option>
										<option value="3">3인</option>
										<option value="6">6인</option>
										<option value="12">12인</option>
										<option value="20">20인</option>
									</select>
								</div>
							</div>
							<div class="row mb-3">
								<div class="d-flex flex-wrap gap-2 ">
									<label for="example-date-input" class="col-form-label"><h5>시작일</h5></label>
									<label for="example-date-input" class="col-form-label"
										style="margin-left: 270px;"><h5>마감일</h5></label>
								</div>
								<div class="d-flex flex-wrap gap-2 ">
									<input class="datebox form-control" type="date" name="pjStart"
										style="width: 300px;"> <input
										class="datebox form-control" type="date" name="pjEnd"
										style="margin-left: 20px; width: 300px;">
								</div>
							</div>
							<div class="row mb-3">
								<div>
									<h5>프로젝트목표</h5>
									<input class="form-control" type="text" name="pjObjTitle">
								</div>
							</div>

							<div class="row mb-3">
								<div>
									<h5>프로젝트목표개요</h5>
									<textarea class="form-control" name="pjObjSummary"></textarea>
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
			<div class="modal-footer">
				<!-- Toogle to second dialog -->
				<button type="button" class="btn btn-light waves-effect"
					data-bs-dismiss="modal" id="closeBtn">취소</button>
				<button class="btn btn-primary" onclick="f_addProject()">생성</button>
			</div>

		</div>
	</div>
</div>
<form:form method="post" id="cancelForm">
	<input type="hidden" name="_method" value="put">
	<input type="hidden" name="pjId">
	<input type="hidden" name="memId">
</form:form>
<form:form method="get" id="downForm" action="${cPath}/download">
	<input type="hidden" name="attatchNum">
	<input type="hidden" name="attatchOrder">
</form:form>
<form:form method="post" id="projectBuy"
	action="${cPath }/project/${project.pjId }/prodBuy">
	<input type="hidden" name="cprodNum">
	<input type="hidden" name="cprodPrice">
</form:form>

<form id="form" action="${cPath }/project" method="post">
	<sec:csrfInput />
	<input type="text" name="pjName" hidden> <input type="text"
		name="pjPnum" hidden> <input type="date" name="pjStart" hidden>
	<input type="date" name="pjEnd" hidden> <input type="text"
		name="pjObjTitle" hidden>
	<textarea name="pjObjSummary" hidden></textarea>
</form>
<script>
	//승인 날짜 정하기
	var date = new Date().toISOString().substring(0, 10);
	document.querySelector("[name=pjStart]").value = date;
	var form = $("#form");
	// 프로젝트에 추가
	function f_addProject() {
		var inputs = $("#createProject :input");
		for (var i = 0; i < inputs.length; i++) {
			var name = inputs[i].name;
			var value = inputs[i].value;
			form.find("[name=" + name + "]").val(value);
		}
		form.submit();
	};
	let pNum = "${project.pjPnum}";

	$("#check_all").on("click", function() {
		if ($(this).prop("checked") == true)
			$(":input[name=check]").prop("checked", true);
		else
			$(":input[name=check]").prop("checked", false);
	});

	// 체크박스 개별 선택
	$("input[name=check]").on("click", function() {
		if ($("input[name=check]:checked").length == 3) {
			$("#check_all").prop("checked", true);
		} else
			$("#check_all").prop("checked", false);

	});

	$("[name=buyBtn]").on("click", function() {
		let title = $(this).data("title");
		let price = $(this).data("price");
		let id = $(this).data("id");
		let count = $(this).data("count");
		if (count < pNum) {
			alert("해당 프로젝트 총인원수 보다 작은 제품은 구매불가합니다");
			return;
		}
		$("#check_all").prop("checked", false);
		$(":input[name=check]").prop("checked", false);
		$("#projectBuy").find("input[name=cprodNum]").val(id);
		$("#projectBuy").find("input[name=cprodPrice]").val(price);
		$("#buyTitle").text(title);
		$("#buyPrice").text("결제금액 총" + price + " 원");
		$("#exampleModalFullscreen").modal("hide");
		$("#buyModal").modal("show");
	});

	$("#goBuyBtn").on("click", function() {
		var okchk = $("input[name='check']");
		if (okchk.length == $("input[name=check]:checked").length) {
			IMP.init("imp68429276"); // 예: imp00000000
			IMP.request_pay({ // param     
				pg : "inicis",
				pay_method : "card",
				merchant_uid : "Otion" + new Date().getTime(),
				name : "Expert",
				amount : 100,
				buyer_email : "zzmm6992@daum.net",
				buyer_name : "아인",
				buyer_tel : ""

			}, function(rsp) { // callback
				if (rsp.success) {
					console.log('빌링키 발급 성공', rsp)
					alert('결제가 완료 되었습니다.');
					alert("구매고고");
					$("#buyModal").modal("hide");
					$("#projectBuy").submit();
				} else {
					var msg = '결제에 실패하였습니다.';
					msg += '에러내용 : ' + rsp.error_msg;
					alert(msg);
					return false;
				}

			});

		} else {
			alert('모든 항목을 동의 한 후 구매가 가능합니다.');
		}

	});

	// 협업 신청 내역 받아오기
	$("[name=listBtn]")
			.on(
					"click",
					function() {
						let pjId = $(this).data("id");
						let url = "${cPath}/project"
						console.log(pjId);
						$
								.ajax({
									url : url,
									data : {
										"pjId" : pjId
									},
									method : "get",
									dataType : "json",// text, html, json, xml, script -> main type : text, 파일 업로드 처리를 비동기로? (Formdata)
									success : function(resp, status, jqXHR) {
										let tbody = "";
										$("#cooFormBody").empty();
										cooFormList = resp.cooFormList;
										console.log(resp);
										for (let i = 0; i < cooFormList.length; i++) {
											if (cooFormList[i].cooApprCode == 'A01') {
												tbody += "<tr>";
												tbody += "<td>"
														+ cooFormList[i].applicantId
														+ "</td>"
												tbody += "<td>"
														+ cooFormList[i].memName
														+ "</td>"
												tbody += "<td>"
														+ cooFormList[i].cooFormContent
														+ "</td>"
												tbody += "<td><a class='downassume' name='download' data-num='"+ cooFormList[i].attatch.attatchNum +"' data-order='"+ cooFormList[i].attatch.attatchOrder +"'>"
														+ cooFormList[i].attatch.originNm
														+ "</a></td>"
												tbody += "<td><button type='button' class='btn btn-dark waves-effect' name='cooForm' data-coonum='"+ cooFormList[i].cooFormNum +"' data-status='correct'"
		 			+ "data-bs-toggle='modal' data-bs-target='#correctColleague' data-appId='"+ cooFormList[i].applicantId +"' data-pjid ='"+ resp.pjId +"'>승인</button></td>"
														+ "<td><button type='button' class='btn btn-secondary waves-effect' name='cancelForm' data-coonum='"+ cooFormList[i].cooFormNum +"' data-status='cancel' data-appId='"+ cooFormList[i].applicantId +"' data-pjid ='"+ resp.pjId +"'>거절</button></td>"
												tbody += "</tr>";
											}
										}
										if (tbody == "") {
											tbody = "<tr><td colspan=5>해당프로젝트에 대한 신청이 없습니다</td></tr>"
										}
										$("#cooFormBody").append(tbody);

									},
									error : function(jqXHR, status, error) {
										tbody = "<tr><td colspan=5>해당프로젝트에 대한 신청이 없습니다</td></tr>"
										$("#cooFormBody").append(tbody);
									}
								})
					});
	let cooFormList;

	// 신청 첨부파일 다운
	$(document).on(
			"click",
			"[name=download]",
			function() {
				$("#downForm").find("input[name=attatchNum]").val(
						$(this).data("num"));
				$("#downForm").find("input[name=attatchOrder]").val(
						$(this).data("order"));
				$("#downForm").submit();
			});

	// 승인
	$(document).on("click", "[name=cooForm]", function() {
		$("#selectCooForm").modal('hide');
		console.log($(this).data("cooNum"));
		console.log($(this).data("status"));
		let cooNum = $(this).data("coonum");
		let status = $(this).data("status");
		$("input[name=memId]").val($(this).data("appid"));
		$("input[name=pjId]").val($(this).data("pjid"));
		let action = "${cPath}/cooboard/form/" + cooNum + "/" + status;
		$("#cooFormStatus").attr("action", action);
	});

	// 거절
	$(document).on("click", "[name=cancelForm]", function() {
		let cooNum = $(this).data("coonum");
		let status = $(this).data("status");
		$("input[name=memId]").val($(this).data("appid"));
		$("input[name=pjId]").val($(this).data("pjid"));
		let action = "${cPath}/cooboard/form/" + cooNum + "/" + status;
		$("#cancelForm").attr("action", action).submit();
	});

	// 승인
	function f_addColleague() {
		$("#cooFormStatus").submit();
	}
</script>

