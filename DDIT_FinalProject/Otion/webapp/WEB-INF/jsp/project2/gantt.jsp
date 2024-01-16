<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 8. 5.    윤수웅      최초작성
* 2022. 8. 28.   고정현     간트 리스트 조회
* Copyright (c) ${year} by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<style>
.chart_container {
	overflow-x: scroll;
	overflow-y: scroll;
	max-height: 650px;
}

.chart_container { overflow: overlay; }

.chart_container::-webkit-scrollbar {

    width: 1vw;

}

.chart_container::-webkit-scrollbar-thumb {

    background-color: hsla(0, 0%, 42%, 0.2);
    border-radius: 100px;

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
.form-check-label{
	padding: 5px;
}
</style>

<button type="button"
	class="btn btn-light btn-lg waves-effect waves-light" id="onOff"
	data-id="true" style="display: inline-block; float: right;"><i class="ri-filter-line"></i></button>
<br>

<div id="searchUI" style="display: none;">
	<table class="table table-borderless table-category" style="vertical-align: middle;margin: auto;">
		<tr>
			<th class="gray-bg">업무명</th>
			<td><input type="text" class="form-control" id="searchWord" style="width: 400px;"
			name="title" placeholder="업무 명으로 검색하세요" onkeyup="if(window.event.keyCode==13){search()}"></td>
			<th class="gray-bg">진행도</th>
			<td colspan="2">
				<input type="checkbox" name="priority" class="obj form-check-input" value="요청중"><label class="form-check-label" for="webdesign">요청중</label>
				<input type="checkbox" name="priority" class="obj form-check-input" value="진행"><label class="form-check-label" for="publisher">진행</label> 
				<input type="checkbox" name="priority" class="obj form-check-input" value="피드백"><label class="form-check-label" for="frontend">피드백</label> 
				<input type="checkbox" name="priority" class="obj form-check-input" value="보류"><label class="form-check-label" for="backend">보류</label> 
				<input type="checkbox" name="priority" class="obj form-check-input" value="완료"><label class="form-check-label" for="planner">완료</label></td>
		</tr>
		<tr>
			<th class="gray-bg">우선순위</th>
			<td>
			<input type="checkbox" name="statusCode" class="obj form-check-input" value="낮음"> <label class="form-check-label" for="webdesign">낮음</label> 
				<input type="checkbox" name="statusCode" class="obj form-check-input" value="보통"> <label class="form-check-label" for="publisher">보통</label>
				<input type="checkbox" name="statusCode" class="obj form-check-input" value="높음"> <label class="form-check-label" for="frontend">높음</label>
				<input type="checkbox" name="statusCode" class="obj form-check-input" value="긴급"> <label class="form-check-label" for="frontend">긴급</label>
			<th class="gray-bg">기간</th>
			<td><input class="form-control" type="date" name="start" style="display: inline-block; width: 200px;"> ~ <input class="form-control"
				type="date" name="end" style="display: inline-block; width: 200px;"></td>
		</tr>
		<tr>
			<td colspan="6" style="text-align: right;">
			<input type="button" id="resetBtn"
				class="btn btn-light waves-effect waves-light"
				value="초기화" style="display: inline-block;">
			<input type="button" id="searchBtn"
				class="btn btn-dark waves-effect waves-light"
				value="검색" style="display: inline-block; margin-right: 20px;">
			</td>
		</tr>
	</table>
</div>
<br>
<br>
<div class="chart_container" style="width: auto; clear: both;">
	<div id="chart_div"></div>
</div>
<form method="get" id="searchForm">
	<input type="hidden" name="workTitle"> <input type="hidden"
		name="workPriority"> <input type="hidden"
		name="workStatusCode"> <input type="hidden" name="workStart">
	<input type="hidden" name="workEnd">
</form>
<form action="${cPath }/project/${project.pjId }/work" method="get" id="workViewForm">
</form>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
	window.onload = function() {
		document.getElementById("searchUI").onsubmit = function() {
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

	$("#resetBtn").on("click", function() {
		$("[name=title]").val("");
		$("[name=priority]").prop("checked", false);
		$("[name=statusCode]").prop("checked", false);
		$("[name=start]").val("");
		$("[name=end]").val("");

		$("#searchForm").find("input[name=workTitle]").val("");
		$("#searchForm").find("input[name=workPriority]").val("");
		$("#searchForm").find("input[name=workStatusCode]").val("");
		$("#searchForm").find("input[name=workStart]").val("");
		$("#searchForm").find("input[name=workEnd]").val("");
		searchForm.submit();
	});

	$("#searchBtn").on("click", function() {
		let title = $("[name=title]").val();
		let priority = $("[name=priority]:checked").val();
		let statusCode = $("[name=statusCode]:checked").val();
		let start = $("[name=start]").val();
		let end = $("[name=end]").val();

		$("#searchForm").find("input[name=workTitle]").val(title);
		$("#searchForm").find("input[name=workPriority]").val(priority);
		$("#searchForm").find("input[name=workStatusCode]").val(statusCode);
		$("#searchForm").find("input[name=workStart]").val(start);
		$("#searchForm").find("input[name=workEnd]").val(end);

		console.log($("#searchForm").serialize());
		searchForm.submit();

	});

	$("input[name=priority]").on(
			"click",
			function() {
				if ($(this).prop("checked") == true){					
					$(":input[name=priority]").prop("checked", false);
					$(this).prop("checked", true);
				}
				console.log($(this).val());
			})

	$("input[name=statusCode]").on(
			"click",
			function() {
				if ($(this).prop("checked")){					
					$("input[name=statusCode]").prop("checked", false);
					$(this).prop("checked", true);
				}
				console.log($(this).val());
			})

	function search() {
		searchForm.submit();
	}

	let arr = [];

	google.charts.load('current', {
		'packages' : [ 'gantt' ]
	});

	let searchForm = $("#searchForm").on("submit", function(event) {
		event.preventDefault();
		let data = $("#searchForm").serialize();
		$.ajax({
			method : "get",
			data : data,
			dataType : "json",
			success : function(resp) {
				arr = resp;
				console.log(arr);
				if (arr.length > 0) {
					google.charts.setOnLoadCallback(drawChart);
				} else {
					$("#chart_div").text("해당 조건에 맞는 업무는 존재하지 않습니다.");
				}
			}
		})
		return false;
	}).submit();

	function daysToMilliseconds(days) {
		return days * 24 * 60 * 60 * 1000;
	}

	function drawChart() {

		var data = new google.visualization.DataTable();
		data.addColumn('string', 'Task ID');
		data.addColumn('string', 'Task Name');
		data.addColumn('string', 'Resource');
		data.addColumn('date', 'Start Date');
		data.addColumn('date', 'End Date');
		data.addColumn('number', 'Duration');
		data.addColumn('number', 'Percent Complete');
		data.addColumn('string', 'Dependencies');

		// 최상위를 잡고 최상위 기준으로 하위에 있는 애들은 startDate가 없고 대신 기간설정하여 시작일 설정하는 개념?
		let search = false;
		for (let i = 0; i < $("#searchForm").serialize().split("&").length; i++) {
			let data = $("#searchForm").serialize().split("&")[i];
			if (data.split("=")[1] && data.split("=")[0] != "_csrf") {
				console.log("해당 값이 안비어있어요")
				console.log(data);
				search = true;
				break;
			} else {
				console.log("해당 값이 비어있어요")
			}
		}

		let workNum = [];
		for (let i = 0; i < arr.length; i++) {
			workNum.push(arr[i].workNum);
		}
		let length = 0;
		if (search) {
			length = arr.length;
			for (let i = 0; i < arr.length; i++) {
				if (!(workNum.indexOf(arr[i].parentWorkNum) != -1)) {
					if (arr[i].workStatusCode == "낮음") {
						data.addRows([ [ arr[i].workNum, arr[i].workTitle,
								"dodgerblue", new Date(arr[i].workStart),
								new Date(arr[i].workEnd), null,
								arr[i].duration, null ] ]);
					} else if (arr[i].workStatusCode == "보통") {
						data.addRows([ [ arr[i].workNum, arr[i].workTitle,
								"transparent", new Date(arr[i].workStart),
								new Date(arr[i].workEnd), null,
								arr[i].duration, null ] ]);
					} else if (arr[i].workStatusCode == "높음") {
						data.addRows([ [ arr[i].workNum, arr[i].workTitle,
								"autumn", new Date(arr[i].workStart),
								new Date(arr[i].workEnd), null,
								arr[i].duration, null ] ]);
					} else {
						data.addRows([ [ arr[i].workNum, arr[i].workTitle,
								"winter", new Date(arr[i].workStart),
								new Date(arr[i].workEnd), null,
								arr[i].duration, null ] ]);
					}
				} else {
					if (arr[i].workStatusCode == "낮음") {
						data.addRows([ [
								arr[i].workNum,
								arr[i].workTitle,
								"spring",
								null,
								new Date(arr[i].workEnd),
								(new Date(arr[i].workEnd).getTime() - new Date(
										arr[i].workStart).getTime()),
								arr[i].duration, arr[i].parentWorkNum ] ]);
					} else if (arr[i].workStatusCode == "보통") {
						data.addRows([ [
								arr[i].workNum,
								arr[i].workTitle,
								"summer",
								null,
								new Date(arr[i].workEnd),
								(new Date(arr[i].workEnd).getTime() - new Date(
										arr[i].workStart).getTime()),
								arr[i].duration, arr[i].parentWorkNum ] ]);
					} else if (arr[i].workStatusCode == "높음") {
						data.addRows([ [
								arr[i].workNum,
								arr[i].workTitle,
								"autumn",
								null,
								new Date(arr[i].workEnd),
								(new Date(arr[i].workEnd).getTime() - new Date(
										arr[i].workStart).getTime()),
								arr[i].duration, arr[i].parentWorkNum ] ]);
					} else {
						data.addRows([ [
								arr[i].workNum,
								arr[i].workTitle,
								"winter",
								null,
								new Date(arr[i].workEnd),
								(new Date(arr[i].workEnd).getTime() - new Date(
										arr[i].workStart).getTime()),
								arr[i].duration, arr[i].parentWorkNum ] ]);
					}
				}
			}
		} else {
			length = arr.length;
			for (let i = 0; i < arr.length; i++) {
				if (!arr[i].parentWorkNum) {
					if (arr[i].workStatusCode == "낮음") {
						data.addRows([ [ arr[i].workNum, arr[i].workTitle,
								"spring", new Date(arr[i].workStart),
								new Date(arr[i].workEnd), null,
								arr[i].duration, null ] ]);
					} else if (arr[i].workStatusCode == "보통") {
						data.addRows([ [ arr[i].workNum, arr[i].workTitle,
								"summer", new Date(arr[i].workStart),
								new Date(arr[i].workEnd), null,
								arr[i].duration, null ] ]);
					} else if (arr[i].workStatusCode == "높음") {
						data.addRows([ [ arr[i].workNum, arr[i].workTitle,
								"autumn", new Date(arr[i].workStart),
								new Date(arr[i].workEnd), null,
								arr[i].duration, null ] ]);
					} else {
						data.addRows([ [ arr[i].workNum, arr[i].workTitle,
								"winter", new Date(arr[i].workStart),
								new Date(arr[i].workEnd), null,
								arr[i].duration, null ] ]);
					}

				} else {
					if (arr[i].workStatusCode == "낮음") {
						data.addRows([ [
								arr[i].workNum,
								arr[i].workTitle,
								"spring",
								null,
								new Date(arr[i].workEnd),
								(new Date(arr[i].workEnd).getTime() - new Date(
										arr[i].workStart).getTime()),
								arr[i].duration, arr[i].parentWorkNum ] ]);
					} else if (arr[i].workStatusCode == "보통") {
						data.addRows([ [
								arr[i].workNum,
								arr[i].workTitle,
								"summer",
								null,
								new Date(arr[i].workEnd),
								(new Date(arr[i].workEnd).getTime() - new Date(
										arr[i].workStart).getTime()),
								arr[i].duration, arr[i].parentWorkNum ] ]);
					} else if (arr[i].workStatusCode == "높음") {
						data.addRows([ [
								arr[i].workNum,
								arr[i].workTitle,
								"autumn",
								null,
								new Date(arr[i].workEnd),
								(new Date(arr[i].workEnd).getTime() - new Date(
										arr[i].workStart).getTime()),
								arr[i].duration, arr[i].parentWorkNum ] ]);
					} else {
						data.addRows([ [
								arr[i].workNum,
								arr[i].workTitle,
								"winter",
								null,
								new Date(arr[i].workEnd),
								(new Date(arr[i].workEnd).getTime() - new Date(
										arr[i].workStart).getTime()),
								arr[i].duration, arr[i].parentWorkNum ] ]);
					}
				}
			}
		}
		// 		data.addRows([
		// 				[ 'Research', 'Find sources', null, new Date(2015, 0, 1),
		// 						new Date(2015, 0, 5), null, 100, null ],
		// 				[ 'Write', 'Write paper', 'write', null, new Date(2015, 0, 9),
		// 						daysToMilliseconds(3), 25, 'Research,Outline' ],
		// 				[ 'Cite', 'Create bibliography', 'write', null,
		// 						new Date(2015, 0, 7), daysToMilliseconds(1), 20,
		// 						'Research' ],
		// 				[ 'Complete', 'Hand in paper', 'complete', null,
		// 						new Date(2015, 0, 10), daysToMilliseconds(1), 0,
		// 						'Cite,Write' ],
		// 				[ 'Outline', 'Outline paper', 'write', null,
		// 						new Date(2015, 0, 6), daysToMilliseconds(1), 100,
		// 						'Research' ] ]);

		var options = {
			height : 30 + (42 * length)
		};

		var chart = new google.visualization.Gantt(document
				.getElementById('chart_div'));

		chart.draw(data, options);

		google.visualization.events.addListener(chart, 'select', selectHandler);

		function selectHandler() {
			var selections = chart.getSelection();
			if (selections.length == 0) {
			} else {
				var selection = selections[0];
				console.info(selection);
				// 누르면 해당 하는 값을 기반으로 해당 업무를 가져와야함(숫자는 테이블 컬럼의 순서라고 생각하면 됨)
				// 	            alert('You selected ' + (selection.row == null ? 'something' : data.getValue(selection.row,0)));
				let action = $("#workViewForm").attr("action");
				window.location.href = action + "/" + data.getValue(selection.row,0);
			}
		}
	}
</script>