<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 8. 5.     고정현      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- fullcalendar CDN -->
<link
	href='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/main.min.css'
	rel='stylesheet' />
<script
	src='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/main.min.js'></script>
<!-- fullcalendar 언어 CDN -->
<script
	src='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/locales-all.min.js'></script>
<style> /* body 스타일 */
html, body {
	overflow: hidden;
	font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
	font-size: 14px;
} /* 캘린더 위의 해더 스타일(날짜가 있는 부분) */
.fc-header-toolbar {
	padding-top: 1em;
	padding-left: 1em;
	padding-right: 1em;
}

.fc-event, .fc-event-dot {
	background-color: #ffffff;
}
.tdname{
	min-width: 100px;
}
.form-check-input{
	margin-left: 5px;
}

input[type=checkbox]
{
  /* Double-sized Checkboxes */
  -ms-transform: scale(0.8); /* IE */
  -moz-transform: scale(0.8); /* FF */
  -webkit-transform: scale(0.8); /* Safari and Chrome */
  padding: 10px;
}
label{
	padding: 5px;
}
#calendar{
	padding: 2%;
}
</style>
<c:if test="${not empty message }">
	<script>
		swal("${message}", "일정을 확인해주세요", "error");
	</script>
</c:if>
<button type="button"
	class="btn btn-light btn-lg waves-effect waves-light" id="onOff"
	data-id="true" style="display: inline-block; float: right;"><i class="ri-filter-line"></i></button>
<br>
<div id="searchUI" style="display: none;">
	<table class="table table-borderless table-category">
		<tr>
			<th class="gray-bg">일정명</th>
			<td style="width: 600px;"><input
				style="border: 1px solid #DEDEDE; width: 400px;"
				class="form-control" placeholder="Search Here" name="workCalTitle"
				id="workTitle" />
			<th class="gray-bg" style="width: auto;">일정 종류</th>
			<td><input type="checkbox"
				class="obj form-check-input" name="workClass" class="workCalClass"
				value="CAL1"> <label for="out"> 개인 일정</label> <input
				type="checkbox" class="obj form-check-input" name="workClass"
				class="workcalClass" value="CAL2"> <label for="in">프로젝트
					일정</label></td>
		</tr>
		<tr>
			<th class="gray-bg">일정 기간</th>
			<td style="width: 600px;"><input type="date" id="startDay"
				class="form-control" name="workStart"
				style="width: 200px; display: inline-block;"> ~ <input
				type="date" id="endDay" class="form-control" name="workEnd"
				style="width: 200px; display: inline-block;"></td>
			<td colspan="6" style="text-align: right;"><input type="button" id="resetBtn"
				class="btn btn-light waves-effect waves-light"
				value="초기화" style="display: inline-block;">
			<input type="button" id="searchBtn"
				class="btn btn-dark waves-effect waves-light"
				value="검색" style="display: inline-block; margin-right: 20px;"></td>
		</tr>
	</table>
</div>
<div class="modal fade bs-example-modal-xl show" tabindex="-1"
	id="insertCalendar" style="display: none;" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-xl">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">일정 추가</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<form:form method="post" modelAttribute="calendarVO"
				enctype="application/x-www-form-urlencoded" id="insertModal">
				<div class="modal-body">
					<div class="card">
						<div class="card-body">
							<div class="row mb-3">
								<label for="example-search-input"
									class="col-sm-2 col-form-label">일정명</label>
								<div class="col-sm-10">
									<form:input class="form-control" type="text" autocomplete="off"
										path="workCalTitle" placeholder="일정명" />
									<form:errors path="workCalTitle" element="span"
										cssClass="error" />
								</div>
							</div>
							<div class="row mb-3">
								<label for="example-search-input"
									class="col-sm-2 col-form-label">시작일</label>
								<div class="col-sm-10">
									<form:input class="form-control" type="datetime-local"
										path="workCalStart" />
									<form:errors path="workCalStart" element="span"
										cssClass="error" />
								</div>
							</div>
							<div class="row mb-3">
								<label for="example-search-input"
									class="col-sm-2 col-form-label">마감일</label>
								<div class="col-sm-10">
									<form:input class="form-control" type="datetime-local"
										path="workCalEnd" />
									<form:errors path="workCalEnd" element="span" cssClass="error" />
								</div>
							</div>
							<div class="row mb-3">
								<label for="example-search-input"
									class="col-sm-2 col-form-label">일정 내용</label>
								<div class="col-sm-10">
									<form:textarea class="form-control" path="workCalContent"
										placeholder="일정 내용"></form:textarea>
									<form:errors path="workCalContent" element="span"
										cssClass="error" />
								</div>
							</div>
							<div class="row mb-3">
								<label for="example-search-input"
									class="col-sm-2 col-form-label">일정 종류</label>
								<div class="col-sm-10">
									<form:select class="form-select" path="workCalClass"
										required="true">
										<form:option selected="true" value="">일정 종류를 선택하세요</form:option>
										<form:option value="CAL1">개인 일정</form:option>
										<form:option value="CAL2">프로젝트 업무</form:option>
									</form:select>
									<form:errors path="workCalClass" element="span"
										cssClass="error" />
								</div>
							</div>
							<div class="row mb-3">
								<label for="example-search-input"
									class="col-sm-2 col-form-label">일정 색상</label>
								<div class="col-sm-10">
									<form:input type="color" class="form-select"
										path="workCalColor" required="true" />
									<form:errors path="workCalColor" element="span"
										cssClass="error" />
								</div>
							</div>
						</div>
					</div>
				</div>
			</form:form>
			<div class="modal-footer">
				<!-- Toogle to second dialog -->
				<button type="button" class="btn btn-light waves-effect" id="insertCalendarBtn">등록</button>
				<button type="button" class="btn btn-light waves-effect"
					data-bs-dismiss="modal" id="insertCancel">Close</button>
			</div>
		</div>
	</div>
</div>
<div class="modal fade bs-example-modal-xl show" tabindex="-1"
	id="updateCalendar" style="display: none;" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-xl">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">일정 수정</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<form:form method="post" modelAttribute="calendarVO"
				enctype="application/x-www-form-urlencoded" id="updateModal">
				<input type="hidden" name="_method" value="put" />
				<input type="hidden" name="workCalNum" />
				<div class="modal-body">
					<div class="card">
						<div class="card-body">
							<div class="row mb-3">
								<label for="example-search-input"
									class="col-sm-2 col-form-label">일정명</label>
								<div class="col-sm-10">
									<form:input class="form-control" type="text" autocomplete="off"
										path="workCalTitle" placeholder="일정명" />
									<form:errors path="workCalTitle" element="span"
										cssClass="error" />
								</div>
							</div>
							<div class="row mb-3">
								<label for="example-search-input"
									class="col-sm-2 col-form-label">시작일</label>
								<div class="col-sm-10">
									<form:input class="form-control" type="datetime-local"
										path="workCalStart" />
									<form:errors path="workCalStart" element="span"
										cssClass="error" />
								</div>
							</div>
							<div class="row mb-3">
								<label for="example-search-input"
									class="col-sm-2 col-form-label">마감일</label>
								<div class="col-sm-10">
									<form:input class="form-control" type="datetime-local"
										path="workCalEnd" />
									<form:errors path="workCalEnd" element="span" cssClass="error" />
								</div>
							</div>
							<div class="row mb-3">
								<label for="example-search-input"
									class="col-sm-2 col-form-label">일정 내용</label>
								<div class="col-sm-10">
									<form:textarea class="form-control" path="workCalContent"
										placeholder="일정 내용"></form:textarea>
									<form:errors path="workCalContent" element="span"
										cssClass="error" />
								</div>
							</div>
							<div class="row mb-3">
								<label for="example-search-input"
									class="col-sm-2 col-form-label">일정 종류</label>
								<div class="col-sm-10">
									<form:select class="form-select" path="workCalClass"
										required="true">
										<form:option selected="true" value="">일정 종류를 선택하세요</form:option>
										<form:option value="CAL1">개인 일정</form:option>
										<form:option value="CAL2">프로젝트 업무</form:option>
									</form:select>
									<form:errors path="workCalClass" element="span"
										cssClass="error" />
								</div>
							</div>
							<div class="row mb-3">
								<label for="example-search-input"
									class="col-sm-2 col-form-label">일정 색상</label>
								<div class="col-sm-10">
									<form:input type="color" class="form-select"
										path="workCalColor" required="true" />
									<form:errors path="workCalColor" element="span"
										cssClass="error" />
								</div>
							</div>
						</div>
					</div>
				</div>
			</form:form>
			<div class="modal-footer">
				<!-- Toogle to second dialog -->
				<button type="button" class="btn btn-light waves-effect"
					id="updateBtn">등록</button>
				<button type="button" class="btn btn-light waves-effect"
					data-bs-dismiss="modal" id="updateCancel">Close</button>

			</div>
		</div>
	</div>
</div>
<div class="modal fade bs-example-modal-center" tabindex="-1"
	id="calendarView" style="display: none; text-align: center;" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-body">
				<h3>――――일정 상세 정보――――</h3>
				<div>
					<table class="table table-borderless mb-0" style="width: auto;text-align: center;">
						<tr>
							<td class="tdname">일정명</td>
							<td id="tdWorkTitle"></td>
						</tr>
						<tr>
							<td class="tdname">일정 내용</td>
							<td id="tdWorkContent"></td>
						</tr>
						<tr>
							<td class="tdname">일정 종류</td>
							<td id="tdWorkCalClass"></td>
						</tr>
						<tr>
							<td class="tdname">일정 색깔</td>
							<td><input type="color" id="tdWorkColor" disabled></td>
						</tr>
						<tr>
							<td class="tdname">시작일</td>
							<td id="tdWorkStart"></td>
						</tr>
						<tr>
							<td class="tdname">마감일</td>
							<td id="tdWorkEnd"></td>
						</tr>
					</table>
				</div>
				<div class="modal-footer">
					<!-- Toogle to second dialog -->
					<button type="button" class="btn btn-light waves-effect"
						id="calendarUpdateBtn">일정 수정</button>
					<button type="button" class="btn btn-light waves-effect"
						id="calendarDeleteBtn">일정 삭제</button>
					<button type="button" class="btn btn-light waves-effect"
						data-bs-dismiss="modal" id="updateCancel">창 닫기</button>
				</div>
			</div>
		</div>
	</div>
</div>
<form:form id="deleteCalendar" method="post"
	enctype="application/x-www-form-urlencoded" modelAttribute="calendarVO">
	<input type="hidden" name="_method" value="delete" />
	<form:input type="hidden" path="workCalNum" />
</form:form>
<form method="get" enctype="application/x-www-form-urlencoded"
	id="searchForm">
	<input type="hidden" name="workCalTitle"> <input type="hidden"
		name="workCalClass"> <input type="hidden" name="workCalStart">
	<input type="hidden" name="workCalEnd">
</form>
<!-- calendar 태그 -->
<div id='calendar-container' style="clear: both;">
	<div id='calendar'></div>
</div>
<script>
	window.onload = function() {
		document.getElementById("workTitle").onsubmit = function() {
			return false;
		};
	};

	$("#onOff").on("click", function() {
		if ($("#onOff").data("id") == "true") {
			$("#onOff").data("id", "false");
			$("#onOff").html("<i class='ri-filter-line'></i>");
			$("#searchUI").hide();
		} else {
			$("#onOff").data("id", "true");
			$("#onOff").html("<i class='ri-filter-fill'></i>");
			$("#searchUI").show();
		}
	})

	$("input[name=workClass]").on("click", function() {
		if ($(this).prop("checked")){			
			$("input[name=workClass]").prop("checked", false);
			$(this).prop("checked", true);
		}
		console.log($(this).val());
	})

	$("#calendarUpdateBtn").on(
			"click",
			function() {
				var date = new Date();
				date.setDate(date.getDate());
				if (end.toISOString().split("T")[0] < date.toISOString().split(
						"T")[0]) {
					swal("지난 일정을 수정할 수 없습니다.", "일정 추가 실패", "error");
					return;
				}
				$("#calendarView").modal("hide");
				$("#updateCalendar").modal("show");
			})

	$("#calendarDeleteBtn").on("click", function() {
		swal({
			  title: "정말로 삭제 하시겟습니까?",
			  text: "삭제 후에는 일정 복구가 안됩니다.",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then((willDelete) => {
			  if (willDelete) {
				  $("#deleteCalendar").submit();
			  }
			});
	});

	$('#insertCancel').on('click', function(e) {
		$("#insertModal")[0].reset();
	});
	
	$("#insertCalendarBtn").on("click",function(){
		let title = $("#insertModal").find("input[name=workCalTitle]").val();
		let len = 0;
		for(let i=0; i<title.length; i++){
			if(escape(title.charAt(i).length == 6)){
				len++;
			}
			len++;
		}
		if(len > 50){
			swal("일정 제목은 최대 사이즈가 50입니다.", "사이즈 초과", "error");
			return false;
		}else if(!$("#insertModal").find("input[name=workCalTitle]").val()){
			swal("일정 작성 시 모든 칸을 채워주세요", "일정 내용 공백 있음", "error");
			return false;
		}else{
			$("#insertModal").submit();
		}
	})

	$('#updateCancel').on('click', function(e) {
		$("#updateModal")[0].reset();
	});

	$("#updateBtn").on("click", function() {
		let title = $("#updateModal").find("input[name=workCalTitle]").val();
		let len = 0;
		for(let i=0; i<title.length; i++){
			if(escape(title.charAt(i).length == 6)){
				len++;
			}
			len++;
		}
		if(len > 50){
			swal("일정 제목은 최대 사이즈가 50입니다.", "사이즈 초과", "error");
			return false;
		}else if(!$("#updateModal").find("input[name=workCalTitle]").val()){
			swal("일정 작성 시 모든 칸을 채워주세요", "일정 내용 공백 있음", "error");
			return false;
		}else{
			$("#updateModal").submit();
		}
	});

	$("#resetBtn").on("click", function() {
		$("#workTitle").val("");
		$(":input[name=workClass]").prop("checked", false);
		$("#startDay").val("");
		$("#endDay").val("");

		$("#searchForm").find("input[name=workCalTitle]").val("");
		$("#searchForm").find("input[name=workCalClass]").val("");
		$("#searchForm").find("input[name=workCalStart]").val("");
		$("#searchForm").find("input[name=workCalEnd]").val("");
		searchForm.submit();
	})

	$("#searchBtn").on("click", function() {
		let title = $("#workTitle").val();
		let clazz = $("input[name=workClass]:checked").val();
		let start = $("#startDay").val();
		let end = $("#endDay").val();

		$("#searchForm").find("input[name=workCalTitle]").val(title);
		$("#searchForm").find("input[name=workCalClass]").val(clazz);
		$("#searchForm").find("input[name=workCalStart]").val(start);
		$("#searchForm").find("input[name=workCalEnd]").val(end);
		console.log($("#searchForm").serialize());
		searchForm.submit();
	})
	let start = "";
	let end = "";

	let searchForm = $("#searchForm")
			.on(
					"submit",
					function(event) {
						event.preventDefault();
						let data = $(this).serialize();
						$
								.ajax({
									method : "GET",
									data : data,
									dataType : "json",
									success : function(resp) {
										console.log(resp);
										// calendar element 취득     
										var calendarEl = $('#calendar')[0]; // full-calendar 생성하기     
										var calendar = new FullCalendar.Calendar(
												calendarEl,
												{
													height : '700px', // calendar 높이 설정    
													contentHeight : '100px',
													expandRows : true, // 화면에 맞게 높이 재설정        
													slotMinTime : '00:00', // Day 캘린더에서 시작 시간        
													slotMaxTime : '23:59', // Day 캘린더에서 종료 시간  
													fixedWeekCount : false,
													// 해더에 표시할 툴바        
													headerToolbar : {
														left : 'prevYear,prev,next,nextYear today',
														center : 'title',
														right : 'dayGridMonth,timeGridWeek,timeGridDay,listWeek'
													},
													initialView : 'dayGridMonth', // 초기 로드 될때 보이는 캘린더 화면(기본 설정: 달)    
													//initialDate : , // 초기 날짜 설정 (설정하지 않으면 오늘 날짜가 보인다.)        
													navLinks : true, // 날짜를 선택하면 Day 캘린더나 Week 캘린더로 링크        
													editable : true, // 수정 가능?        
													selectable : true, // 달력 일자 드래그 설정가능        
													nowIndicator : true, // 현재 시간 마크       
													dayMaxEvents : 4, // 이벤트가 오버되면 높이 제한 (+ 몇 개식으로 표현)    
													// 									eventOrder : true, // 이벤트 id 순으로 정렬
													locale : 'ko', // 한국어 설정        
													//eventAdd : function(obj) {
													// // 이벤트가 추가되면 발생하는 이벤트    

													// console.log(obj.event._instance.range.start.toISOString());

													// },
													eventChange : function(obj) {
														// 이벤트가 수정되면 발생하는 이벤트          
														console.log(obj);
													},
													eventRemove : function(obj) {
														// 이벤트가 삭제되면 발생하는 이벤트          
														console.log(obj);
													},
													select : function(arg) {
														// 캘린더에서 드래그로 이벤트를 생성할 수 있다.
														var date = new Date();
														date.setDate(date
																.getDate() - 1);
														if (arg.start
																.toISOString()
																.split("T")[0] < date
																.toISOString()
																.split("T")[0]) {
															swal("지난 일정을 수정할 수 없습니다.", "일정 추가 실패", "error");
															return;
														}
														let start = new Date(
																arg.start
																		.toISOString()
																		.split(
																				"T")[0]);
														let end = new Date(
																arg.end
																		.toISOString()
																		.split(
																				"T")[0]);
														// 당일 이벤트인지 확인하기(당일이면 시간 조정, 드래그일 경우에도 시작일 조정해야함)
														if ((end.getTime() - start
																.getTime())
																/ (1000 * 60 * 60 * 24) == 1) {
															start
																	.setDate(start
																			.getDate() + 1);
															$(
																	"input[name=workCalStart]")
																	.val(
																			start
																					.toISOString()
																					.slice(
																							0,
																							-5));
															$(
																	"input[name=workCalEnd]")
																	.val(
																			start
																					.toISOString()
																					.split(
																							"T")[0]
																					+ "T23:59:00");
															$("#insertCalendar")
																	.modal(
																			"show");
														} else {
															start
																	.setDate(start
																			.getDate() + 1);
															console
																	.log(start
																			.toISOString()
																			.slice(
																					0,
																					-5));
															$(
																	"input[name=workCalStart]")
																	.val(
																			start
																					.toISOString()
																					.slice(
																							0,
																							-5));
															$(
																	"input[name=workCalEnd]")
																	.val(
																			end
																					.toISOString()
																					.split(
																							"T")[0]
																					+ "T23:59:00");
															$("#insertCalendar")
																	.modal(
																			"show");
														}

														calendar.unselect()
													},
													eventClick : function(arg) {
														start = arg.event._instance.range.start;
														end = arg.event._instance.range.end;
														$("#updateModal")
																.find(
																		"input[name=workCalNum]")
																.val(
																		arg.event._def.extendedProps.publicId);

														$("#deleteCalendar")
																.find(
																		"input[name=workCalNum]")
																.val(
																		arg.event._def.extendedProps.publicId);
														$("#updateModal")
																.find(
																		"input[name=workCalTitle]")
																.val(
																		arg.event._def.title);
														$("#updateModal")
																.find(
																		"input[name=workCalStart]")
																.val(
																		arg.event._instance.range.start
																				.toISOString()
																				.slice(
																						0,
																						-5));
														$("#updateModal")
																.find(
																		"input[name=workCalEnd]")
																.val(
																		arg.event._instance.range.end
																				.toISOString()
																				.slice(
																						0,
																						-5));
														$("#updateModal")
																.find(
																		"input[name=workCalColor]")
																.val(
																		arg.event._def.ui.backgroundColor)
														$
																.ajax({
																	url : "${cPath}/project/${project.pjId}/calendar/view",
																	type : "GET",
																	data : {
																		"workCalNum" : arg.event._def.extendedProps.publicId
																	},
																	dataType : "json",
																	success : function(
																			resp) {
																		$(
																				"#tdWorkTitle")
																				.text(
																						resp.workCalTitle);
																		$(
																				"#tdWorkContent")
																				.text(
																						resp.workCalContent);
																		if (resp.workCalClass == "CAL1") {
																			$(
																					"#tdWorkCalClass")
																					.text(
																							"개인 일정");
																		} else {
																			$(
																					"#tdWorkCalClass")
																					.text(
																							"프로젝트 일정");
																		}
																		$(
																				"#tdWorkColor")
																				.val(
																						resp.workCalColor);
																		let st = new Date(resp.workCalStart);
																		let dt = new Date(resp.workCalEnd)
																		$(
																				"#tdWorkStart")
																				.text(st.getFullYear() + "년 " +(st.getMonth() + 1) + "월 " + st.getDate() + "일 " + st.getHours() + "시 " + st.getMinutes() + "분");
																		$(
																				"#tdWorkEnd")
																				.text(dt.getFullYear() + "년 " +(dt.getMonth() + 1) + "월 " + dt.getDate() + "일 " + dt.getHours() + "시 " + dt.getMinutes() + "분");
																	}
																})

														$("#calendarView")
																.modal("show");

													},
													eventDrop : function(arg) {
														console.log(arg);
														let start = arg.event._instance.range.start;
														var date = new Date();
														date.setDate(date
																.getDate());
														if (start.toISOString()
																.split("T")[0] < date
																.toISOString()
																.split("T")[0]) {
															swal("지난 일정을 수정할 수 없습니다.", "일정 추가 실패", "error");
															arg.revert();
															return;
														}
														$(
																"input[name=workCalNum]")
																.val(
																		arg.event._def.extendedProps.publicId);
														$("#deleteCalendar")
																.find(
																		"input[name=workCalNum]")
																.val(
																		arg.event._def.extendedProps.publicId);

														$(
																"input[name=workCalTitle]")
																.val(
																		arg.event._def.title);
														$(
																"input[name=workCalStart]")
																.val(
																		arg.event._instance.range.start
																				.toISOString()
																				.slice(
																						0,
																						-5));
														$(
																"input[name=workCalEnd]")
																.val(
																		arg.event._instance.range.end
																				.toISOString()
																				.slice(
																						0,
																						-5));
														$(
																"input[name=workCalColor]")
																.val(
																		arg.event._def.ui.backgroundColor)
														$("#updateCalendar")
																.modal("show");

													},
													// 이벤트        
													events : resp

												});
										// 캘린더 랜더링      
										calendar.render();
									}

								});
						return false;
					}).submit();
</script>