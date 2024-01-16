<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 8. 31.      고정현      codepen api 적용
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<style>
.blogCateList-cate {
	float: left;
	width: 220px;
	height: auto;
}

#folder {
	font-size: 120px;
}
.blogCateList-cate-name{
	font-weight: bold;
	color: black;
	height: 24px;
	word-break:break-all;
}
.tab-content{
	text-align: center;
}
#catebody {
	margin-left: 100px;
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

<button type="button" class="btn btn-light waves-effect" id="onOff" data-id="true" style="float: right;"><i class="ri-filter-line"></i></button>
<div id="searchUI" style="display: none;">
<table class="table table-borderless mb-0 table-category" style="vertical-align: middle;margin: auto;">
		<tr>
			<th class="gray-bg">업무명</th>
			<td><input type="text" class="form-control" id="searchWord" style="width: 400px;"
			name="title" placeholder="업무 명으로 검색하세요" onkeyup="if(window.event.keyCode==13){search()}"></td>
			<th class="gray-bg">진행도</th>
			<td colspan="2" style="text-align: left;">
				<input type="checkbox" name="priority" class="obj form-check-input" value="요청중"><label class="form-check-label" for="webdesign">요청중</label>
				<input type="checkbox" name="priority" class="obj form-check-input" value="진행"><label class="form-check-label" for="publisher">진행</label> 
				<input type="checkbox" name="priority" class="obj form-check-input" value="피드백"><label class="form-check-label" for="frontend">피드백</label> 
				<input type="checkbox" name="priority" class="obj form-check-input" value="보류"><label class="form-check-label" for="backend">보류</label> 
				<input type="checkbox" name="priority" class="obj form-check-input" value="완료"><label class="form-check-label" for="planner">완료</label></td>
		</tr>
		<tr>
			<th class="gray-bg">우선순위</th>
			<td style="text-align: left;">
			<input type="checkbox" name="statusCode" class="obj form-check-input" value="낮음"> <label class="form-check-label" for="webdesign">낮음</label> 
				<input type="checkbox" name="statusCode" class="obj form-check-input" value="보통"> <label class="form-check-label" for="publisher">보통</label>
				<input type="checkbox" name="statusCode" class="obj form-check-input" value="높음"> <label class="form-check-label" for="frontend">높음</label>
				<input type="checkbox" name="statusCode" class="obj form-check-input" value="긴급"> <label class="form-check-label" for="frontend">긴급</label>
			<th class="gray-bg">기간</th>
			<td style="text-align: left;"><input class="form-control" type="date" name="start" style="display: inline-block; width: 200px;"> ~ <input class="form-control"
				type="date" name="end" style="display: inline-block; width: 200px;"></td>
		</tr>
		<tr>
			<td colspan="6" style="text-align: right;">
			  <input type="button" id="resetBtn" class="btn btn-light waves-effect waves-light" value="초기화" style="display: inline-block;">
			  <input type="button" id="searchBtn" class="btn btn-dark waves-effect waves-light" value="검색" style="display: inline-block; margin-right: 20px;">
			</td>
		</tr>
	</table>
</div>
<div id="catebody" style="clear: both;">
	<c:forEach items="${myWorkList }" var="work">
		<div class="blogCateList-cate">
			
			<c:if test="${work.workPriority eq '요청' }">
			<a href="${cPath }/project/${project.pjId }/work/${work.workNum}" class="ri-file-4-fill" style="color: aqua;" id="folder" name="work" data-id="${work.workNum }"></a>
			</c:if>
			<c:if test="${work.workPriority eq '진행' }">
			<a href="${cPath }/project/${project.pjId }/work/${work.workNum}" class=" ri-file-3-fill" style="color: #6c6ff5;" id="folder" name="work" data-id="${work.workNum }"></a>
			</c:if>
			<c:if test="${work.workPriority eq '피드백' }">
			<a href="${cPath }/project/${project.pjId }/work/${work.workNum}" class="  ri-file-warning-fill" style="color: #FFBF00;" id="folder" name="work" data-id="${work.workNum }"></a>
			</c:if>
			<c:if test="${work.workPriority eq '완료' }">
			<a href="${cPath }/project/${project.pjId }/work/${work.workNum}" class="mdi mdi-file-check" style="color: #192366;" id="folder" name="work" data-id="${work.workNum }"></a>
			</c:if>
			<c:if test="${work.workPriority eq '보류' }">
			<a href="${cPath }/project/${project.pjId }/work/${work.workNum}" class="ri-file-3-fill" style="color: #E6E6E6;" id="folder" name="work" data-id="${work.workNum }"></a>
			</c:if>
			<p class="blogCateList-cate-name">${work.workTitle }</p>
<%-- 			(${work.workPriority }) --%>
			<p>시작일 ${work.workStart }<br>종료일 ${work.workEnd }</p>
		</div>
	</c:forEach>
</div>
<form method="get" id="searchForm">
	<input type="hidden" name="workTitle"> <input type="hidden"
		name="workPriority"> <input type="hidden"
		name="workStatusCode"> <input type="hidden" name="workStart">
	<input type="hidden" name="workEnd">
</form>
<br><br>
<script>

	$("#onOff").on("click",function(){
		if($("#onOff").data("id") == "true"){
			$("#onOff").data("id", "false");
			$("#onOff").html("<i class='ri-filter-line'></i>");
			$("#searchUI").hide();
		}else{
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
	
	$("input[name=priority]").on("click",function(){
		if($(this).prop("checked")){			
			$("input[name=priority]").prop("checked", false);
			$(this).prop("checked", true);
		}
			console.log($(this).val());
	})
	
	$("input[name=statusCode]").on("click",function(){
		if($(this).prop("checked")){			
			$("input[name=statusCode]").prop("checked", false);
			$(this).prop("checked", true);
		}
			console.log($(this).val());
	})

	let searchForm = $("#searchForm").on("submit", function(event) {
		event.preventDefault();
		let data = $(this).serialize();
		$.ajax({
			method : "get",
			data : data,
			dataType : "json",
			success : function(resp) {
				console.log(resp);
				$("#catebody").empty();
				let tbody = "";
				if(resp.length > 0){
					for(let i=0; i<resp.length; i++){					
						tbody += '<div class="blogCateList-cate">';
						if(resp.workPriority == "요청"){
							tbody += '<a href="${cPath }/project/${project.pjId}/work/' + resp[i].workNum +'" class="ri-file-4-fill" style="color: aqua;" id="folder" name="work"data-id="'+ resp[i].workNum +'"></a>';
						}else if(resp.workPriority == "진행"){
							tbody += '<a href="${cPath }/project/${project.pjId}/work/' + resp[i].workNum +'" class="ri-file-3-fill" style="color: #6c6ff5;" id="folder" name="work"data-id="'+ resp[i].workNum +'"></a>';
						}else if(resp.workPriority == "피드백"){
							tbody += '<a href="${cPath }/project/${project.pjId}/work/' + resp[i].workNum +'" class="ri-file-warning-fill" style="color: #FFBF00;" id="folder" name="work"data-id="'+ resp[i].workNum +'"></a>';
						}else if(resp.workPriority == "완료"){
							tbody += '<a href="${cPath }/project/${project.pjId}/work/' + resp[i].workNum +'" class="mdi mdi-file-check" style="color: #192366;" id="folder" name="work"data-id="'+ resp[i].workNum +'"></a>';
						}else{
							tbody += '<a href="${cPath }/project/${project.pjId}/work/' + resp[i].workNum +'" class="ri-file-3-fill" style="color: #E6E6E6;" id="folder" name="work"data-id="'+ resp[i].workNum +'"></a>';
						}
						tbody += '<p class="blogCateList-cate-name">'+ resp[i].workTitle +'</p>';
						tbody += '<p>'+ resp[i].workStart +'~' + resp[i].workEnd +'</p>';
						tbody += '</div>';
					}
				}else{
					tbody += '<div class="blogCateList-cate">';
					tbody += '<div class="ri-file-4-fill" id="folder"></div>';
					tbody += '<p class="blogCateList-cate-name">해당 조건에 맞는 업무가 없습니다.</p>';
					tbody += '</div>';
				}
				$("#catebody").append(tbody);
				console.log(resp);
			}
		})
		return false;
	})
</script>