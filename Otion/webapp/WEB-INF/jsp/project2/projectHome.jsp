<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 8. 5.    윤수웅      최초작성
* Copyright (c) ${year} by DDIT All right reserved
 --%>
<style>
.changeblue {
	background-color: #0B0B61;
	color: white;
	border: 1px solid #E6E6E6;
	padding: 5px 24px 5px 24px;
}

element.style {
	display: block;
	width: 1000px;
	height: 500px;

}

.errorMsg{
	color: red;
	margin-top: 5px;

}
.delBtn{
	padding: 2px;
	color: red;
}

.workStatus{
	padding: 5px 24px 5px 24px;
	border: 1px solid #E6E6E6;
}
.addmemid2{
	display: flex;
	float: left;
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<style>
.workH3 {
	margin-left: 10px;
	vertical-align: middle;
}
</style>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
<sec:authentication property="principal.realMember" var="member" />
<c:if test="${not empty project }">
	<div class="card-body row text-center">
		<div style="display: inline;">
			<h1 style="font-size: 30px; ">${project.pjName}의 프로젝트 홈입니다.</h1>
			<div id="memId"style="display: none;">${member.memId}</div>
		</div>

		<div class="card" style="padding: 15px; background-color: #eff2f7; width: 90%; margin: auto;">
			<div class="row text-center">
				<canvas id="doughnut" height="432" width="826"
					style="display: block; width: 740px; height: 400px; float:left;"
					class="chartjs-render-monitor">
				</canvas>
				<div style="float: left; width: 30%;">
					<div id="report-box">
						<table class="table table-borderless mb-0" style="font-size: 16px; margin-top: 72px;">
							<tr>
								<td><span class="rqTitle" style="background-color:rgba(255, 99, 132, 0.6) ">요청</span></td>
								<td id="ws01"></td>
							</tr>
							<tr>
								<td><span class="rqTitle" style="background-color:rgba(54, 162, 235, 0.6) ">진행</span></td>
								<td id="ws02"></td>
							</tr>
							<tr>
								<td><span class="rqTitle" style="background-color:rgba(255, 206, 86, 0.6) ">피드백</span></td>
								<td id="ws03"></td>
							</tr>
							<tr>
								<td><span class="rqTitle" style="background-color:rgba(229, 255, 204, 0.6) ">완료</span></td>
								<td id="ws04"></td>
							</tr>
							<tr>
								<td><span class="rqTitle" style="background-color:rgba(229, 204, 255, 0.6)">보류</span></td>
								<td id="ws05"></td>
							</tr>
						</table>
					</div>
				</div>
				<a class="btn btn-dark waves-effect waves-light" style="width: 120px;  float: left; height: 40px; margin-left: 60px;" data-bs-toggle="modal"
				data-bs-target="#childModal" style="float: right; vertical-align: middle;">업무작성</a>
			</div>
		</div>
	</div>

  <div style="width: 88%; margin: auto;">
	<div class="card"
		style="width: 50%; padding:8px; margin-top: 5%; margin-left: auto; margin-right: auto; float: left;"
		class="table-responsive">
		<h4 class="workH3" style="text-align: center;">나의 업무</h4>
		<table
			class="card table table-centered table-nowrap table-hover mb-0 text-center"
			id="myWorkTable">
			<thead>
				<tr>
					<th>업무 상태</th>
					<th>긴급도</th>
					<th>작성자</th>
					<th style="width: 190px;">업무 제목</th>
					<th>업무 시작일</th>
					<th>업무 종료일</th>
				</tr>
			</thead>
			<tbody id="myWorkBody">
				<c:forEach items="${workList}" var="work">
				<c:if test="${work.workPriority ne '완료'}">
					<c:forEach items="${work.memberList}" var="mem">
						<c:if test="${mem eq member.memId}">
							<tr class="goWork" style="cursor: pointer;">
								<td class='workNum' style="display: none;">${work.workNum}</td>
								<td><span class="rqTitle ${work.workPriority}">${work.workPriority}</span></td>
								<td style="width: 67.2px;">${work.workStatusCode}</td>
								<td style="width: 67.2px;">${work.workReq}</td>
								<td  style="width: 190px;">${work.workTitle}</td>
								<td>${work.workStart}</td>
								<td>${work.workEnd}</td>
							</tr>
						</c:if>
					</c:forEach>
					</c:if>
				</c:forEach>
			</tbody>

		</table>
	</div>
	<div class="card"
		style="width: 50%;  padding:8px; margin-top: 5%; margin-left: auto; margin-right: auto; float: right;"
		class="table-responsive">
		<h4 class="workH3"  style="text-align: center;">요청업무</h4>
		<table
			class="table table-centered table-nowrap table-hover mb-0 text-center"
			id="receiveWorkTable">
			<thead>
				<tr>
					<th>업무 상태</th>
					<th>긴급도</th>
					<th>업무작성자</th>
					<th>업무 제목</th>
					<th>업무 시작일</th>
					<th>업무 종료일</th>


				</tr>
			</thead>
			<tbody id="receiveWorkBody">
				<c:forEach items="${workList}" var="work">
				<c:if test="${work.workPriority ne '완료'}">
					<c:if test="${member.memId eq work.workReq}">
					
							<tr class="goWork" style="cursor: pointer;">
								<td class='workNum' style="display: none;">${work.workNum}</td>
								<td><span class="rqTitle ${work.workPriority}">${work.workPriority}</span></td>
								<td>${work.workStatusCode}</td>
								<td>${work.workReq}</td>
								<td>${work.workTitle}</td>
								<td>${work.workStart}</td>
								<td>${work.workEnd}</td>
							</tr>
						
					</c:if>
				</c:if>
				</c:forEach>
			</tbody>

		</table>
	</div>
	</div>
</c:if>
<c:if test="${empty project }">
	<div>프로젝트 결제를 하셔야 기능이용이 가능합니다.</div>
</c:if>

<!-- childModal  -->
<div class="modal fade" id="childModal" tabindex="-1"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header"  style="padding-left: 40%;">
				<h5 class="modal-title" id="myModalLabel">업무작성양식</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div id="childWork" class="modal-body">

				<div class="clickRequest2 mb-3 justify-content-center" style="display: flex;">
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
						<input type="hidden" name="workReq" value="${member.memId}" />
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
								maxlength="20" value="" placeholder="20글자이내로 작성해주세요">
						</div>
					</div>
				<div>
					<input style="display: none; " class="form-control memId2" type="text"value="" name="memId" />
					<div style="margin-top: 10px; display: inline-block; ">
						<a id="addmem2" class="btn btn-secondary" data-bs-toggle="modal" style="float: right;" data-bs-target="#memModal">담당자 추가</a> 
					</div>
					</div>
					<div class="d-flex" id="manager2" >
					
					</div>
				
					<div class="mb-3" style="margin-top: 2%;">
						<label>시작일</label>
						<div>
							<input class="form-control" type="date" name="workStart" value=""
								id="example-date-input">
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
							<option>선택해주세요</option>
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
					class="btn btn-dark waves-effect waves-light">등록</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>



<!-- memModal  -->
<div class="modal fade" id="memModal" tabindex="-1"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" style="  box-shadow: 0 1px 3px rgba(0,0,0,0.12), 0 1px 2px rgba(0,0,0,0.24);">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="myModalLabel" >원하는 담당자를 추가해주세요</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
			<table class="table table-borderless mb-0" style="text-align: center;">
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

<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script>
	var mem = [];
	var myModalEl = document.getElementById('childModal');
	mem[2] = [];
	var a = [];
	let inputId = []
	inputId[0] = [];
	inputId[1] = $("#childWork").find("input");
	a[2] = [];
	let discrim = [];

	let workList = "${wsList}";
	workList = workList.replace("[", "").replace("]", "").split(",");

	let ws01 = workList[0]
	let ws02 = workList[1]
	let ws03 = workList[2]
	let ws04 = workList[3]
	let ws05 = workList[4]
	
	
	$("#ws01").append(ws01);
	$("#ws02").append(ws02);
	$("#ws03").append(ws03);
	$("#ws04").append(ws04);
	$("#ws05").append(ws05);
	// 차트 그리기
	let doughnut = document.getElementById('doughnut').getContext('2d');
	let myChart = new Chart(doughnut, {
		type : 'doughnut', // 차트의 형태
		data : { // 차트에 들어갈 데이터
			labels : [
			//x 축
			'요청', '진행', '피드백', '완료', '보류' ],
			datasets : [ { //데이터
				label : 'test1', //차트 제목
				fill : false, // 선 안쪽을 채우는지 안채우는지
				data : [ ws01, ws02, ws03, ws04, ws05 ],
				backgroundColor : [
				//색상
				'rgba(255, 99, 132, 0.6)', 'rgba(54, 162, 235, 0.6)',
						'rgba(255, 206, 86, 0.6)', 'rgba(229, 255, 204, 0.6)',
						'rgba(229, 204, 255, 0.6)' ],
				borderColor : [
				//경계선 색상
				'rgba(255, 99, 132, 1)', 'rgba(54, 162, 235, 1)',
						'rgba(255, 206, 86, 1)', 'rgba(229, 255, 204, 1)',
						'rgba(229, 204, 255, 1)' ],
				borderWidth : 1
			//경계선 굵기
			} ]
		},
		options : {
			responsive : false,
			scales : {
				yAxes : [ {
					ticks : {
						min : 0,
						stepSize : 6,
						fontSize : 12,
					}
				} ]
			}
		}
	});

	function setNum(index) {

		if (index == 0) {
			for (var i = 0; i < $("#myWorkBody").find("tr").length; i++) {

				$("#myWorkBody").find("tr").eq(i).find(".num").text(i + 1);

			}
		} else {
			for (var i = 0; i < $("#receiveWorkBody").find("tr").length; i++) {

				$("#receiveWorkBody").find("tr").eq(i).find(".num").text(i + 1);

			}

		}

	}



	$(".clickRequest2")
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

	$(".memadd1").on(
			"click",
			function() {
				idx = 2;
				for (var i = 0; i < a[idx] + 1; i++) {
					console.log($("#manager" + idx).find('div').eq(a[idx] - i).find('p').text());
					console.log($(this).closest("tr").find('p').text());
						console.log(a[idx]);
					console.log($("#manager" + idx).find('div').eq(a[idx] - i).find('p').text())
					if ($("#manager" + idx).find('div').eq(a[idx] - i).find('p').text() == $(this).closest("tr").find('p').text()) {
						swal("이미 선택된 사용자 입니다.", "", "error");

						discrim.push("false");
					}
				}

				if (discrim.indexOf("false") == -1) {
					$("#manager" + idx).html(
							$("#manager" + idx).html()
									+ "<div class='addmemid"+idx+"'><p>"
									+ $(this).closest("tr").find('p').text()
									+ "</p>"
									+ "<a class='delBtn remove"+idx+"'>삭제</a>"
									+ "</div>");
					a[idx]++;
					mem[idx].push($(this).closest("tr").find('p').text());
					swal("등록되었습니다.", "", "success");

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

	$(document).on(
			"click",
			".remove2",
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

	$(".goWork").on(
			"click",
			function() {
				let url = "${cPath}/project/${pjId}/work/"
						+ $(this).find(".workNum").text();
				window.location.href = url;

			});

	$("button[name=childFormSaveBtn]").on("click",function() {
		
						let check = checkInput(1);
						console.log(check);
						if (check == 7) {
							
							let data = $("#childWorkForm").serialize();
							$.ajax({
										url : "${cPath}/project/PJ001/work/000"
												+ "/addchild",
										data : data,
										dataType : "json" // text, html, json, xml, script -> main type : text, 파일 업로드 처리를 비동기로? (FormData)
										,
										success : function(resp, status, jqXHR) {
											let checking = 0;
											console.log(resp);
											if (resp.workReq == $("#memId").text()) {
												if(resp.workPriority!="완료"){
													let tr = $("<tr class='goWork' style='cursor: pointer;'>");
													tr.append(
																	$("<td class='workNum' style='display: none;'>").html(resp.workNum),
																	$("<td>").html("<span class='rqTitle "+resp.workPriority+"'>"+resp.workPriority+"</span>"),
																	$("<td>").html(resp.workStatusCode),
																	$("<td>").html(resp.workReq),
																	$("<td>").html(resp.workTitle),
																	$("<td>").html(resp.workStart),
																	$("<td>").html(resp.workEnd));
													
													tr.click(function() {
																let url = "${cPath}/project/${pjId}/work/"
																		+ $(this).find(".workNum").text();
																window.location.href = url;	
	
															});
													console.log("tr", tr.html());
													$("#receiveWorkBody").append(tr);
												}
											}

											for (var i = 0; i < resp.memberList.length; i++) {
												if(resp.workPriority!="완료"){
													if ($("#memId").text() == resp.memberList[i]) {
														checking = 1;
													}
												}
											}
											if (checking == 1) {
												let tr = $("<tr class='goWork' style='cursor: pointer;'>");
												tr.append(	$("<td class='workNum' style='display: none;'>").html(resp.workNum),
																$("<td>").html("<span class='rqTitle "+resp.workPriority+"'>"+resp.workPriority+"</span>"),
																$("<td>").html(resp.workStatusCode),
																$("<td>").html(resp.workReq),
																$("<td>").html(resp.workTitle),
																$("<td>").html(resp.workStart),
																$("<td>").html(resp.workEnd));
												tr.click(function() {
															let url = "${cPath}/project/${pjId}/work/"
																	+ $(this).find(".workNum").text();
															window.location.href = url;
														});
												$("#myWorkBody").append(tr)

											}
											$("#childModal").modal('hide');
											ws01 = resp.workList[0];
											ws02 = resp.workList[1];
											ws03 = resp.workList[2];
											ws04 = resp.workList[3];
											ws05 = resp.workList[4];
											
											let doughnut = document.getElementById('doughnut').getContext('2d');
											let myChart = new Chart(doughnut, {
												type : 'doughnut', // 차트의 형태
												data : { // 차트에 들어갈 데이터
													labels : [
													//x 축
													'요청', '진행', '피드백', '완료', '보류' ],
													datasets : [ { //데이터
														label : 'test1', //차트 제목
														fill : false, // 선 안쪽을 채우는지 안채우는지
														data : [ ws01, ws02, ws03, ws04, ws05 ],
														backgroundColor : [
														//색상
														'rgba(255, 99, 132, 0.6)', 'rgba(54, 162, 235, 0.6)',
																'rgba(255, 206, 86, 0.6)', 'rgba(229, 255, 204, 0.6)',
																'rgba(229, 204, 255, 0.6)' ],
														borderColor : [
														//경계선 색상
														'rgba(255, 99, 132, 1)', 'rgba(54, 162, 235, 1)',
																'rgba(255, 206, 86, 1)', 'rgba(229, 255, 204, 1)',
																'rgba(229, 204, 255, 1)' ],
														borderWidth : 1
													//경계선 굵기
													} ]
												},
												options : {
													responsive : false,
													scales : {
														yAxes : [ {
															ticks : {
																min : 0,
																stepSize : 6,
																fontSize : 12,
															}
														} ]
													}
												}
											});
											swal("등록되었습니다.", "", "success");

										},
										error : function(jqXHR, status, error) {
											console.log(jqXHR);
											console.log(status);
											console.log(error);
										}
									});

						}

					});

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
					"<span class='errorMsg'>필수 입력값입니다.</span>");
		} else {
			check++;
		}
		workStatus.find("textarea[name=workContent]").closest("div").find(
				"span").remove();
		if (!workStatus.find("textarea[name=workContent]").val()) {
			workStatus.find("textarea[name=workContent]").closest("div")
					.append("<span class='errorMsg'>필수 입력값입니다.</span>");
		} else {
			check++;
		}
		workStatus.find(".workStatusValidate").closest("div").find("span")
				.remove();
		if (workStatus.find("select[name=workStatusCode]").val() == "선택해주세요") {
			workStatus.find(".workStatusValidate").closest("div").append(
					"<span class='errorMsg'>필수 입력값입니다.</span>");
		} else {
			check++;
		}

		memClass.closest("div").find("span").remove();
		if (!memClass.val()) {
			memClass.closest("div").append("<span class='errorMsg'>필수 입력값입니다.</span>");
		} else {
			check++;
		}

		for (var i = 0; i < inputId[input].length; i++) {
			console.log(inputId[input].eq(i).val());
			if (!(i == 0 || i == 1 || i == 2 || i == 3 || i == 5)) {
				if (!inputId[input].eq(i).val()) {
					inputId[input].eq(i).closest("div").append(
							"<span class='errorMsg'>필수 입력값입니다.</span>");
				} else {
					check++;
				}

			}
		}
		return check;
	};

	myModalEl.addEventListener(
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
							if (!(i == 0 || i == 1 || i == 2 || i == 3 || i == 5)) {
								inputId[1].eq(i).closest("div").find("span")
										.remove();
							}
						}
						$("#childWork").find(".workreqValidate").closest("div")
								.find("span").remove();
						$("#childWork").find("textarea").closest(
						"div").find("span").remove();
						$("#childWork").find(".workStatusValidate").closest(
								"div").find("span").remove();
						$(".memId2").closest("div").find("span").remove();
					});
	
	
	

</script>