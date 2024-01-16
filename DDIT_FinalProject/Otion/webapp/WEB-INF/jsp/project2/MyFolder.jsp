<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 8. 5.      고정현      최초작성
* 2022. 8. 24.     고정현     프로젝트 폴더 리스트 및 추가
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<style>
.card {
	box-shadow: none;
	-webkit-box-shadow: none;
}
</style>

<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal.realMember" var="member" />
</sec:authorize>
<c:if test="${not empty message }">
	<script>
	swal("${message}", "파일 업로드 성공", "success");
	</script>
</c:if>
<div class="row">
	<div class="col-12">
		<!-- Left sidebar -->
		<div class="email-leftbar card">
			<div class="btn-toolbar py-3 float-end" role="toolbar"
				style="margin: auto; display: none;" id="fileBtnList">
				<div class="btn-group me-2 mb-2 mb-sm-0">
					<button type="button"
						class="btn btn-light waves-light waves-effect"
						data-bs-toggle="modal" data-bs-target="#fileUploadModal">
						<i class="fa fa-inbox"></i>
					</button>
					<div class="modal fade bs-example-modal-xl" tabindex="-1"
						id="fileUploadModal" aria-labelledby="myExtraLargeModalLabel"
						style="display: none;" aria-hidden="true">
						<div class="modal-dialog modal-xl">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="myExtraLargeModalLabel">파일 업로드</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<div class="modal-body">
									<div class="card">
										<div class="card-body">
											<h4 class="card-title">Dropzone</h4>
											<div id="fileUpload">
												<form
													action="${cPath }/project/${project.pjId }/folder/upload"
													method="post" class="dropzone dz-clickable"
													enctype="multipart/form-data" id="fileForm">
													<sec:csrfInput />
													<div class="dz-message needsclick"
														style="text-align: center; margin-top: 50px">
														<div class="mb-3">
															<i class="display-4 text-muted ri-upload-cloud-2-line"></i>
															<label class="input-file-button" for="input-file"></label>
															<input type="file" name="fileList" id="input-file"
																style="display: none;" multiple="multiple">
														</div>
														<div id="fileName">
															<h4>파일 업로드하려면 클릭하세요</h4>
														</div>
													</div>
													<input type="hidden" name="wfolderNum"
														value="WFOD2209140002">
												</form>
											</div>

											<div class="text-center mt-4">
												<button type="button" id="fileSubmit"
													class="btn btn-primary waves-effect waves-light">Send
													Files</button>
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- /.modal-content -->
						</div>
						<!-- /.modal-dialog -->
					</div>
					<button type="button" id="downBtn"
						class="btn btn-light waves-light waves-effect"
						value="WFOD2209140002">
						<i class="ri-file-download-line"></i>
					</button>
					<button type="button" id="delBtn"
						class="btn btn-light waves-light waves-effect"
						value="WFOD2209140002">
						<i class="far fa-trash-alt"></i>
					</button>
					<c:if test="${not empty allFolderList }">
						<!-- 				<div class="btn-group me-2 mb-2 mb-sm-0"> -->
						<button type="button"
							class="btn btn-light waves-light waves-effect dropdown-toggle"
							data-bs-toggle="dropdown" aria-expanded="false">폴더삭제</button>
						<div class="dropdown-menu col-12">
							<div class="dropdown-item col-mail col-mail-1">
								<input class="checkbox-wrapper-mail" type="checkbox" id="allChk"
									style="margin-right: 10px; font-size: 14px;">전체 선택
							</div>
							<c:forEach items="${allFolderList }" var="allList">
								<div class="dropdown-item col-mail col-mail-1"
									style="font-size: 14px;">
									<input class="checkbox-wrapper-mail" type="checkbox"
										name="delChk" style="margin-right: 10px;"
										data-id="${allList.wfolderNum }">${allList.wfolderName }
								</div>
							</c:forEach>
							<div class="dropdown-item col-mail col-mail-1">
								<span style="float: right;">
									<button type="button"
										class="btn btn-secondary btn-sm waves-effect waves-light"
										id="cancelBtn">취소</button>
									<button type="button"
										class="btn btn-danger btn-sm waves-effect waves-light"
										id="delFolderBtn">삭제</button>
								</span>
							</div>
						</div>
						<!-- 				</div> -->
					</c:if>
				</div>
			</div>

			<div style="border: 1px solid #ced4da; border-radius: 4px;">
				<div class="accordion accordion-flush" id="accordionFlushExample">
					<div class="accordion-item">
						<c:if test="${not empty folderList }">
							<c:forEach items="${folderList }" var="folder">
								<div class="accordion-item">
									<h2 class="accordion-header" id="flush-headingOne">
										<button class="accordion-button collapsed" type="button"
											data-parent="Y" data-bs-toggle="collapse"
											data-bs-target="#${folder.wfolderNum }"
											data-id="${folder.wfolderNum }" aria-expanded="false"
											aria-controls="flush-collapseOne">
											<i class="ri-folder-line" style="margin-right: 5px;"></i><span
												class=""></span>${folder.wfolderName }
										</button>
									</h2>
									<div id="${folder.wfolderNum }" data-id="folderList"
										class="accordion-collapse collapse"
										aria-labelledby="flush-headingOne"
										data-bs-parent="#flush-headingOne" style=""></div>
								</div>
							</c:forEach>
						</c:if>
						<c:if test="${empty folderList }">
							<div>폴더가 없습니다.</div>
						</c:if>
						<div id="defaultNameForm"></div>
						<h2 class="accordion-header">
							<button type="button" name="insertFolderBtn"
								class="btn waves-effect" data-id="default" style="width: 100%">
								<i class=" fas fa-folder-plus"></i> 폴더추가
							</button>
							<button type="button" name="deleteFolderBtn"
								class="btn waves-effect" data-id="default" style="width: 100%">
								<i class="fas fa-folder-minus"></i> 폴더삭제
							</button>
						</h2>
					</div>
				</div>
			</div>




			<h6 class="mt-4">최근 업로드한 파일</h6>

			<c:if test="${not empty single }">
				<c:forEach items="${single }" var="single" begin="0" end="3">
					<div class="mt-3">
						<a href="#" class="d-flex">
							<div
								class="email-link-name bg-soft-primary text-center border-primary me-3">
								<h6 class="text-primary">${single.downNumber }</h6>
							</div>
							<div class="flex-grow-1 chat-user-box">
								<h6 class="user-title mb-1">${single.originNm }</h6>
								<p class="text-muted mb-0">${single.attatchDate }</p>
							</div>
						</a>
					</div>
				</c:forEach>
			</c:if>
			<c:if test="${empty single }">
			</c:if>
		</div>
		<!-- End Left sidebar -->


		<!-- Right Sidebar -->
		<div class="email-rightbar mb-3">

			<div class="card">

				<div class="row">
					<div class="col-md-12">
						<form class="email-search p-3 " id="searchUI"
							style="float: right; width: 360px;">
							<div class="position-relative">
								<input type="text" class="form-control" placeholder="Search..."
									name="searchWord" data-id="none" autocomplete="off"
									onkeyup="if(window.event.keyCode==13){search()}"> <span
									class="mdi mdi-magnify font-size-18" id="searchBtn"></span>
							</div>
						</form>
					</div>
				</div>
				<div id="noFile" class="col-xl-3 col-lg-4 col-sm-6"
					style="text-align: center; margin: auto; height: 10px;">
					<br>
					<br>
					<br>
					<br>
					<br>
					<br>
					<h2 id="noFileText" style="width: 480px;">
						<i class="ri-folder-forbid-line"></i>폴더를 선택해주세요
					</h2>
				</div>
				<ul class="message-list" id="fileBody" style="height: 630px; overflow: scroll;">
				</ul>
			</div>
		</div>
	</div>
</div>
<form:form action="${cPath }/download" method="get" id="downFile"
	name="downFile">
	<input type="hidden" name="attatchNum">
	<input type="hidden" name="attatchOrder">
	<input type="hidden" name="attatchPlace">
</form:form>
<form:form action="${cPath }/project/${project.pjId }/folder/delFile"
	method="post" id="deleteFile">
	<input type="hidden" name="_method" value="delete">
	<input type="hidden" name="attatchNum">
	<input type="hidden" name="attatchOrder">
	<input type="hidden" name="attatchPlace">
	<input type="hidden" name="id">
</form:form>
<form:form action="${cPath }/project/${project.pjId }/folder/delFolder"
	method="post" id="deleteFolder">
	<input type="hidden" name="_method" value="delete">
	<input type="hidden" name="wfolderNum">
</form:form>
<form:form method="post" id="insertFolderForm">
	<input type="hidden" name="id">
	<input type="hidden" name="colNum">
	<input type="hidden" name="folderName">
</form:form>
<iframe width=0 height=0 name='hiddenframe' style='display: none;'></iframe>
<script>
	let memName = "${member.memName}";
	let colNum = "${myColleague.colNum}";
	let count = 1;

	$("#delFolderBtn").on("click", function() {
		let wfolderNum = [];
		$("input[name=delChk]:checked").each(function() {
			wfolderNum.push($(this).data("id"));
		});
		$("#deleteFolder").find("input[name=wfolderNum]").val(wfolderNum);
		$("#deleteFolder").submit();
	});

	$("input[name=delChk]").click(function() {
		var total = $("input[name=delChk]").length;
		var checked = $("input[name=delChk]:checked").length;

		if (total != checked)
			$("#allChk").prop("checked", false);
		else
			$("#allChk").prop("checked", true);
	});

	$("#allChk").click(function() {
		if ($("#allChk").is(":checked"))
			$("input[name=delChk]").prop("checked", true);
		else
			$("input[name=delChk]").prop("checked", false);
	});

	// 업로드 클릭
	$("#fileUpload").on("click", function() {
		$("#input-file").click();
	});

	$("#input-file").on("change", function() {
		let files = $("#input-file")[0].files;
		let tbody = "업로드 된 파일 내역 : ";
		for (let i = 0; i < files.length; i++) {
			tbody += files[i].name
			if (i != files.length - 1) {
				tbody += ",";
			}
		}
		$("#fileName").text(tbody);
	})

	$("#fileSubmit").on("click", function() {
		if(!$("#input-file").val()){
			swal("파일을 선택해주세요", "파일 업로드 실패", "error");
			return false;
		}
		$("#fileForm").submit();
	});

	// enter누르면 생성되는 거
	function insertForm() {
		let id = $("input[name=wfolderName]").data("id");
		$("#insertFolderForm").find("input[name=id]").val(id);
		$("#insertFolderForm").find("input[name=colNum]").val(colNum);
		let folderName = "";
		for (let i = 0; i < $("input[name=wfolderName]").length; i++) {
			folderName += ($("input[name=wfolderName]")[i].value);
			if (i != $("input[name=wfolderName]").length - 1) {
				folderName += ",";
			}
		}
		$("#insertFolderForm").find("input[name=folderName]").val(folderName);
		$("#insertFolderForm").submit();
	}
	// 해당 추가 버튼 누르면 해당 위치에 폴더 추가되기
	$(document)
			.on(
					"click",
					"[name=insertFolderBtn]",
					function() {
						let newfolder = ''
						console.log($(this).data("id"));
						let id = $(this).data("id");
						newfolder += '<div class="email-search p-3 "><div class="position-relative">';
						newfolder += '<input type="text" name="wfolderName" class="form-control" data-id="'
								+ id
								+ '" autocomplete="off" placeholder = "폴더 이름을 적어주세요" onkeyup="if(window.event.keyCode==13){insertForm()}">';
						newfolder += '</div></div>';

						if (id == "default") {
							$("#defaultNameForm").append(newfolder);
						} else {
							$("[name='nameForm" + id + "']").append(newfolder);
						}
					});
	$(document).on("click", "[name=deleteFolderBtn]", function() {
		let newfolder = ''
		console.log($(this).data("id"));
		let id = $(this).data("id");
		if (id == "default") {
			$("#defaultNameForm").empty();
		} else {
			$("[name=nameForm" + id + "]").empty();
		}
	});

	window.onload = function() {
		document.getElementById("searchUI").onsubmit = function() {
			return false;
		};
	};

	function search(event) {
		let id = $("input[name=searchWord]").data("id")
		myFileList(id);
	}

	$("#searchBtn").on("click", function() {
		let id = $("input[name=searchWord]").data("id")
		if (id == 'none') {
			swal("폴더를 선택해야 검색이 가능합니다.", "왼쪽 폴더를 클릭", "error");
			return;
		}
		myFileList(id);
	});
	// 선택한 아코디언에 대한 파일 리스트 가져오기
	function myFileList(id) {
		console.log(id);
		let tbody = "";
		$
				.ajax({
					url : "${cPath}/project/PJ003/folder/fileList",
					method : "get",
					data : {
						"id" : id,
						"searchWord" : $("input[name=searchWord]").val()
					},
					dataType : "json",
					success : function(resp) {
						$("#fileBody").empty();
						$("input[name=searchWord]").val("");
						if (resp.length > 0) {
							$("#noFile").hide();
							for (let i = 0; i < resp.length; i++) {
								for (let j = 0; j < resp[i].attatchVO.length; j++) {
									let attatch = resp[i].attatchVO[j];
									tbody += '<li style="height : 75px;"><div class="col-mail col-mail-1"><input class="checkbox-wrapper-mail" type="checkbox" name="chk" data-num="'
									+ attatch.attatchNum +'" data-order="'+ attatch.attatchOrder +'" data-place="'+ attatch.attatchPlace +'"><a href="#" class="title">'
									tbody += '<div class="d-flex"><div class="flex-grow-1 mt-2"><h6 class="mb-0"> '
									tbody += memName
									tbody += '</h6><p class="text-muted email-date font-size-13 mb-0">'
									tbody += attatch.attatchDate
									tbody += '</p></div></div></a></div><div class="col-mail col-mail-2" style=" vertical-align: middle;"><h6 class="mb-0">'
									tbody += attatch.originNm
									tbody += '</h6>'
										+ '<p class="text-muted email-desc font-size-13 mb-0"> 파일 업로드 경로 - <span class="teaser">'
										+ attatch.filePath.split("\\")[1] + '</span>';
									tbody += '</p> </a><div class="date"><a href=""> 파일 용량 - '
										+ attatch.attatchSize
										+ '</a></div></div></li> '
								}
							}
						} else {
							$("#noFileText").text("업로드 된 파일이 없습니다.");
							$("#noFile").show();
						}
						$("#fileBody").append(tbody);
					}
				})
	}

	// 폴더 리스트 가져오기
	function myfolderList(id) {
		let tbody = "";
		console.log(id);
		$
				.ajax({
					method : "get",
					data : {
						"id" : id,
						"colNum" : colNum
					},
					dataType : "json",
					success : function(resp) {
						$("#" + id).empty();
						if (resp.length > 0) {
							for (let i = 0; i < resp.length; i++) {
								tbody += '<div class="accordion-item" style="margin-left : '+ 10 * count +'px;"><h2 class="accordion-header" id="flush-headingOne">';
								tbody += '<button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"data-bs-target="#'; 
								tbody += resp[i].wfolderNum;
								tbody += '" data-id="' + resp[i].wfolderNum + '" aria-expanded="false"aria-controls="flush-collapseOne">';
								tbody += '<i class="ri-folder-line" style="margin-right:5px;"></i> <span class=""></span>';
								tbody += resp[i].wfolderName
								tbody += '</button></h2></div>';
								tbody += '<div id="'
								tbody += resp[i].wfolderNum;
								tbody += '"data-id="folderList" class="accordion-collapse collapse" aria-labelledby="flush-headingOne" data-bs-parent="#flush-headingOne" style=""></div>';
							}
							tbody += '<div name="nameForm'+ id  +'"></div>';
							$("#" + id).append(tbody);
						} else {
							tbody += '<div style="margin-left : '+ 14 * count +'px; padding:10px;">하위폴더가 없습니다.</div>'
							tbody += '<div name="nameForm'+ id  +'"></div>';
							$("#" + id).append(tbody);
						}
					}
				})
	}

	let beforeId = [];
	let id = "";
	$(document)
			.on(
					"click",
					".accordion-button",
					function() {
						if ($(this).hasClass("collapsed") == false) {
							if ($(this).data("parent") == "Y") {
								beforeId.length = 0;
								id = "";
								count = 1;
								$
										.each(
												$(".accordion-button"),
												function(idx, data) {
													let dataId = $(data).data("id");
													if ($(data).hasClass("collapsed") == false) {
														$(data).addClass("collapsed");
														$(".accordion-item").find("#"+ dataId).empty();
													}
												});
								$(this).removeClass("collapsed");
							} else {
								if (id) {
									beforeId.push(id);
									count++;
								}
							}
							id = $(this).data("id");
						} else {
							if (beforeId.length > 0) {
								id = beforeId[beforeId.length - 1];
								beforeId.length = beforeId.length - 1;
								count--;
							} else {
								id = 'default';
								count = 1;
							}

						}
						console.log("이전 아이디 배열  : " + beforeId);
						console.log("현재 폴더 아이디 : " + id);
						if(id == "default"){
							$("#fileBtnList").hide();
						}else{
							$("#fileBtnList").show();
						}
						
						$("[name=wfolderNum]").val(id);
						$("#delBtn").val(id);
						$("#downBtn").val(id);
						$("[name=insertFolderBtn]").data("id", id);
						$("[name=deleteFolderBtn]").data("id", id);
						console.log("추가 버튼 data id 값 : "
								+ $("[name=insertFolderBtn]").data("id"));
						console.log("삭제 버튼 data id 값 : "
								+ $("[name=insertFolderBtn]").data("id"));
						$("input[name=searchWord]").data("id", id);
						console.log($("[name=wfolderNum]").val());
						myfolderList(id);
						myFileList(id);
					});

	$("#delBtn").on("click", function() {
		if (!$("#delBtn").val()) {
			swal("폴더를 선택해야 삭제가 가능합니다.", "왼쪽 폴더를 클릭", "error");
			return;
		}
		let num = [];
		let order = [];
		let place = [];
		swal({
			  title: "정말로 삭제하시겠습니까?",
			  text: "삭제 후에는 해당 파일 복구가 안됩니다.",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then((willDelete) => {
			  if (willDelete) {
				  $("input[name=chk]:checked").each(function() {
						num.push($(this).data("num"));
						order.push($(this).data("order"));
						place.push($(this).data("place"));
					});
					if (num.length == 0) {
						swal("파일을 선택해야 삭제가 가능합니다.", "파일을 선택하세요", "error");
						return;
					}
					$("#deleteFile").find("input[name=attatchNum]").val(num);
					$("#deleteFile").find("input[name=attatchOrder]").val(order);
					$("#deleteFile").find("input[name=attatchPlace]").val(place);
					$("#deleteFile").find("input[name=id]").val(id);
					$.ajax({
						url : "${cPath }/project/${project.pjId }/folder/delFile",
						type : "POST",
						data : $("#deleteFile").serialize(),
						dataType : "json",
						success : function(resp){
							myFileList(id);
							swal("파일 삭제가 완료 되었습니다", "파일 삭제", "success");
						}
					})
			  }
			});
	});
	// 파일 다운로드 로직
	$("#downBtn")
			.on(
					"click",
					function() {
						if (!$("#downBtn").val()) {
							alert("폴더를 선택해주세요");
							return;
						}
						let num = [];
						let order = [];
						let place = [];
						swal({
							  title: "정말로 다운로드 하시겠습니까?",
							  text: "해당 파일을 다운로드를 합니다",
							  icon: "warning",
							  buttons: true,
							  dangerMode: true,
							})
							.then((willDelete) => {
								$("input[name=chk]:checked").each(function() {
									num.push($(this).data("num"));
									order.push($(this).data("order"));
									place.push($(this).data("place"));
								});
								if (num.length == 0) {
									alert("파일을 선택해주세요");
									return;
								}
								console.log("num의 배열 :" + num);
								console.log("order의 배열 :" + order);
								console.log("place의 배열 :" + place);
								for (let i = 0; i < num.length; i++) {
									setTimeout(
											function() {
												$("#downFile").find(
														"input[name=attatchNum]")
														.val(num[i]);
												$("#downFile").find(
														"input[name=attatchOrder]")
														.val(order[i]);
												$("#downFile").find(
														"input[name=attatchPlace]")
														.val(place[i]);
												setDownRecord();
												window
														.open($("#downFile").attr(
																"action")
																+ '?attatchNum='
																+ num[i]
																+ '&attatchOrder='
																+ order[i]
																+ '&attatchPlace='
																+ place[i],
																"hiddenframe",
																"width=0, height=0, top=0, statusbar=no, scrollbars=no, toolbar=no")

											}, (i + 1) * 1000);
								}

							});
					})

	function setDownRecord() {
		let data = $("#downFile").serialize();
		$.ajax({
			url : "${cPath}/project/${pjId}/record",
			method : "get",
			data : data,
			dataType : "json",
			success : function(resp) {

			}

		})
	}
</script>