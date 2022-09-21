<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal.realMember" var="member" />
</sec:authorize>
<div class="card">
	<div class="card-body">
		<!-- Nav tabs -->
		<ul class="nav nav-tabs" role="tablist">
			<li class="nav-item"><a class="nav-link active"
				data-bs-toggle="tab" data-id="upload" href="#upload" role="tab"
				aria-selected="false"> <span class="d-block d-sm-none"><i
						class="far fa-user"></i></span> <span class="d-none d-sm-block">업로드</span>
			</a></li>
			<li class="nav-item"><a class="nav-link" data-bs-toggle="tab"
				data-id="download" href="#download" role="tab" aria-selected="false">
					<span class="d-block d-sm-none"><i class="far fa-envelope"></i></span>
					<span class="d-none d-sm-block">다운로드</span>
			</a></li>
			<li class="list-inline-item  d-none d-sm-inline-block">
				<div class="dropdown Drop">
					<button class="btn nav-btn dropdown-toggle" type="button"
						data-bs-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false">
						<i class="mdi mdi-cog-outline font-size-15"></i>
					</button>
					<div class="dropdown-menu dropdown-menu-end">
						<div class="dropdown-item" id="folder" >프로젝트 폴더</div> 
						<div class="dropdown-item" id="git">공유 저장소</div> 
					</div>
				</div>
			</li>
		</ul>
		<form class="email-search p-3 " id="searchUI">
			<div class="position-relative">
				<input type="text" class="form-control" placeholder="Search..."
					data-id="all" name="searchWord"
					onkeyup="if(window.event.keyCode==13){search()}"> <a
					href="#"><span class="mdi mdi-magnify font-size-18"
					id="searchBtn"></span></a>
			</div>
		</form>
		<!-- Tab panes -->
		<div class="tab-content p-3 text-muted">
			<div class="tab-pane active" id="upload" role="tabpanel">
				<ul class="message-list" id="listUploadBody" style="height: 600px;">
				</ul>
				<div class="d-flex justify-content-center pagingArea"></div>
			</div>
			<div class="tab-pane" id="download" role="tabpanel">
				<ul class="message-list" id="listDownloadBody" style="height: 600px;">
				</ul>
				<div class="d-flex justify-content-center pagingArea"></div>
			</div>
		</div>
	</div>
</div>
<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="myLargeModalLabel"></h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="table-responsive">
					<table class="table table-borderless mb-0">
						<tbody id="modalbody">

						</tbody>
					</table>
				</div>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<form:form method="post" id="searchForm">
	<input type="hidden" name="page">
	<input type="hidden" name="searchType" value="upload">
	<input type="hidden" name="searchWord">
	<input type="hidden" name="searchPlace" value="folder">
</form:form>
<script>
	let pagingArea = $(".pagingArea");

	window.onload = function() {
		document.getElementById("searchUI").onsubmit = function() {
			return false;
		};
	};

	$(".dropdown-item").on("click",function(){
		console.log($(this).attr("id"));
		$("#searchForm").find("input[name=page]").val("1");
		$("#searchForm").find("input[name=searchPlace]").val($(this).attr("id"));
		searchForm.submit();
	})
	
	function search() {
		let searchType = $("#searchUI").find("input[name=searchWord]").data(
				"id")
		let searchWord = $("#searchUI").find("input[name=searchWord]").val();
		console.log(searchType)
		console.log(searchWord)
		$("#searchForm").find("input[name=searchType]").val(searchType);
		$("#searchForm").find("input[name=searchWord]").val(searchWord);
		searchForm.submit();
	}

	$(".nav-link").on("click", function() {
		let id = $(this).data("id");
		$("#searchForm").find("input[name=page]").val("1");
		console.log("searchType : " + id);
		if(id == "upload"){
			$("#searchForm").find("input[name=searchPlace]").val("folder");
			$(".Drop").show();
		}else if(id == "download"){
			$("#searchForm").find("input[name=searchPlace]").val("");
			$(".Drop").hide();
		}
		$("#searchUI").find("input[name=searchName]").data("id", id);
		$("#searchForm").find("input[name=searchType]").val(id);
		searchForm.submit();
	});

	let searchForm = $("#searchForm").on("submit", function(event) {
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
				console.log(resp);
				makeTag(resp);
			}
		})
		return false;
	}).submit();

	function makeTag(resp) {
		console.log(resp);
		$("#listUploadBody").empty();
		$("#listDownloadBody").empty();
		$(".pagingArea").empty();
		let tbody = "";
		if (resp) {
			if (resp.simpleCondition.searchType == "upload") {
				for (let j = 0; j < resp.dataList.length; j++) {
					let data = resp.dataList[j];
					if($("#searchForm").find("input[name=searchPlace]").val() == "folder"){						
					tbody += '<li class = "upload" id="'+ data.attatchNum 
						+'" data-id="'+ data.attatchOrder +'" style="height : 80px;" class="accordion-button collapsed" data-bs-toggle="modal" data-bs-target=".bs-example-modal-lg" type="button" data-bs-toggle="collapse"><div class="col-mail col-mail-1" data-bs-target="#1"'
						+	'aria-expanded="false" aria-controls="flush-collapseOne">';
					tbody += '<a href="#" class="title"><div class="d-flex">';
					tbody += '<div class="flex-grow-1 mt-2">';
					tbody += '<h6 class="mb-0">' + data.uploaderId + '</h6>'
					tbody += '<p class="text-muted email-date font-size-13 mb-0">'
							+ data.attatchDate + '</p></div></div></a></div>';
					tbody += '<div class="col-mail col-mail-2"><a href="#" class="subject"><h6 class="mb-0">'
							+ data.originNm + '</h6>';
					tbody += '<p class="text-muted email-desc font-size-13 mb-0"><span class="teaser">내 폴더 업로드</span></p></a>';
					tbody += '<div class="date">' + data.attatchDate
							+ '</div></div></li>';
					}else{
						tbody += '<li class = "upload" id="'+ data.attatchNum 
						+'" data-id="'+ data.attatchOrder +'" style="height : 80px;" class="accordion-button collapsed" data-bs-toggle="modal" data-bs-target=".bs-example-modal-lg" type="button" data-bs-toggle="collapse"><div class="col-mail col-mail-1" data-bs-target="#1"'
						+	'aria-expanded="false" aria-controls="flush-collapseOne">';
					tbody += '<a href="#" class="title"><div class="d-flex">';
					tbody += '<div class="flex-grow-1 mt-2">';
					tbody += '<h6 class="mb-0">' + data.uploaderId + '</h6>'
					tbody += '<p class="text-muted email-date font-size-13 mb-0">'
							+ data.attatchDate + '</p></div></div></a></div>';
					tbody += '<div class="col-mail col-mail-2"><a href="#" class="subject"><h6 class="mb-0">'
							+ data.originNm + '</h6>';
					tbody += '<p class="text-muted email-desc font-size-13 mb-0"><span class="teaser">공유저장소 업로드</span></p></a>';
					tbody += '<div class="date">' + data.attatchDate
							+ '</div></div></li>';
					}
				}
				if(resp.dataList.length > 0){					
					$(".pagingArea").append(resp.pagingHTMLBS);
				}
				$("#listUploadBody").append(tbody);
			} else if (resp.simpleCondition.searchType == "download") {
				for (let j = 0; j < resp.dataList.length; j++) {
					let data = resp.dataList[j];
					tbody += '<li class = "donwload" id="'+ data.downNum
						+'" class="accordion-button collapsed" type="button" data-bs-toggle="collapse"><div class="col-mail col-mail-1" data-bs-target="#1"'
						+	'aria-expanded="false" aria-controls="flush-collapseOne">';
					tbody += '<a href="#" class="title"><div class="d-flex">';
					tbody += '<div class="flex-grow-1 mt-2">';
					tbody += '<h6 class="mb-0">' + data.downloaderId + '</h6>'
					tbody += '<p class="text-muted email-date font-size-13 mb-0">'
							+ data.downDate + '</p></div></div></a></div>';
					tbody += '<div class="col-mail col-mail-2"><a href="#" class="subject"><h6 class="mb-0">'
							+ data.attatch.originNm + '</h6>';
					tbody += '<p class="text-muted email-desc font-size-13 mb-0"><span class="teaser">다운로드</span></p></a>';
					tbody += '<div class="date">' + data.attatch.attatchDate
							+ '</div></div></li>';
				}
				$("#listDownloadBody").append(tbody);
				if(resp.dataList.length > 0){					
					$(".pagingArea").append(resp.pagingHTMLBS);
				}
			}
		}
	}
	$(".pagingArea").on("click", "a", function(){
		console.log($(this).data("page"));
		$("#searchForm").find("input[name=page]").val($(this).data("page"));
		searchForm.submit();
	});

	$(document).on("click", ".upload", function() {
		let id = $(this).attr("id");
		let order = $(this).data("id");
		console.log(id);
		fileView(id, order)
	});

	function fileView(id, order) {
		$
				.ajax({
					url : "${cPath}/project/${pjId}/record/uploadView",
					method : "get",
					data : {
						"attatchNum" : id,
						"attatchOrder" : order
					},
					dataType : "json",
					success : function(resp) {
						$("#modalbody").empty();
						let tbody = "";
						tbody += '<tr><th scope="row">파일 명</th><td>'
						tbody += resp.originNm
						tbody += '</td></tr>'
						tbody += '<tr><th scope="row">파일 업로드 일</th><td>'
						tbody += resp.attatchDate
						tbody += '</td></tr>'
						tbody += '<tr><th scope="row">파일 업로더</th><td>'
						tbody += resp.uploaderId
						tbody += '</td></tr>'
						if (resp.attatchPlace.indexOf("WFFL") != -1) {
							tbody += '<tr><th scope="row">업로드 장소</th><td>프로젝트 폴더</td></tr>'
						}
						tbody += '<tr><th scope="row">파일 사이즈</th><td>'
						tbody += resp.attatchSize
						tbody += '</td></tr>'
						tbody += '<tr><th scope="row">업로드 파일 타입</th><td>'
						tbody += resp.attatchType
						tbody += '</td></tr>'
						tbody += '<tr><th scope="row">다운로드 횟수</th><td>'
						tbody += resp.downNumber
						tbody += '</td></tr>'
						$("#modalbody").append(tbody);
						$(".modal-title").text("업로드 파일 상세 보기");

						console.log(resp);
					}
				})
	}
</script>