<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 8. 5.      고정현      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<link
	href="//cdn.jsdelivr.net/npm/jquery.fancytree@2.27/dist/skin-win8/ui.fancytree.min.css"
	rel="stylesheet">
<script
	src="//cdn.jsdelivr.net/npm/jquery.fancytree@2.27/dist/jquery.fancytree-all-deps.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<style>
.blogCateList-cate {
	float: left;
/* 	margin-left: 40px; */
	width: 242px;
	height: 250px;
	text-align: center;
}

#folder {
	font-size: 120px;
}

#catebody {
	margin-left: 100px;
}
.downtag{
	padding: 8px;
	font-size: 14px;
	color: black;
}
.ri-file-2-line{
	color: #8ca3bd;
}
.ri-file-2-line:hover{
	color: black;
}
</style>
<c:if test="${not empty upload }">
	<script>
		swal("모든파일을 업로드 했습니다.", "공유저장소 업로드 성공", "success");
	</script>
</c:if>
<h4 style="font-weight: bold; text-align: right; padding-right: 12px;">공유저장소</h4>
<div style="text-align: right;">
	<a id="downBtn" class="downtag"><i class=" ri-download-2-line"></i> 파일다운로드</a>
	<a id="upBtn" class="downtag"><i class=" ri-upload-2-fill"></i> 파일 업로드</a>
	<a class="downtag" type="button" onclick="location.href='${cPath}/project/${project.pjId }/git'">
	<i class="ri-home-gear-line"></i>홈</a>
</div>
<div id="catebody">
	<c:forEach items="${fileList }" var="file">
		<c:if test="${file.isFile eq false }">
			<div class="blogCateList-cate">
				<a href="#" class="ri-folder-5-fill" id="folder"
					data-id="${file.fileName }" data-file="${file.isFile }"></a>
				<p class="blogCateList-cate-name">${file.fileName }</p>
				<p>생성날짜 : ${file.date }</p>
			</div>
		</c:if>
	</c:forEach>
	<c:forEach items="${fileList }" var="file">
		<c:if test="${file.isFile eq true }">
			<div class="blogCateList-cate">
				<a href="/Otion/blog/a-in/cate/테스트중" class="ri-file-2-line"
					id="folder" data-id="${file.fileName }" data-file="${file.isFile }"></a>
				<p class="blogCateList-cate-name">${file.fileName }</p>
				<p>생성날짜 : ${file.date }</p>
			</div>
		</c:if>
	</c:forEach>
</div>

<form method="get" id="fileForm">
	<input type="hidden" name="parent" data-id="">
</form>
<form method="post" enctype="multipart/form-data" action="${cPath }/project/${project.pjId }/git" id="uploadForm">
	<sec:csrfInput/>
	<input type="file" style="display: none;" id="upPath" name="gitUpload" multiple/>
</form>
<script type="text/javascript">
	$("#downBtn").on("click", function() {
		console.log()
		// 		$("#downPath").click();
		$.ajax({
			method : "post",
			data : {
				"${_csrf.parameterName}" : "${_csrf.token}"
			// 				,"url" : url
			},
			dataType : "json",
			success : function(resp) {
				if(resp.message != "실패")
					swal("내려받기에 성공했습니다!", "공유저장소 내려받기 성공", "success");
				else
					swal(resp.message, "공유저장소 내려받기 실패", "error");
			}
		})
	})
	
	$("#upPath").on("change",function(){
		console.log($(this)[0].files);
		$("#uploadForm").submit();
	});
	
	$("#upBtn").on("click",function(){
		$("#upPath").click();
	})

	/* $("#downPath").on("change",function(){
		let selectedFiles = this.files;
		console.log(this.files);
		let url = selectedFiles[0].webkitRelativePath;
		console.log(url);
		if(url){			
			$.ajax({
				method : "post",
				data : {
					"${_csrf.parameterName}" : "${_csrf.token}"
					,"url" : url
				},
				dataType : "json",
				success : function(resp){
					console.log(resp);
				}
			})
		}else{
			alert("파일 내려받기 위한 경로를 지정해주세요.")
		}
	}) */

	let url = "";
	$(document)
			.on(
					"click",
					"#folder",
					function(event) {
						event.preventDefault();
						let isFile = $(this).data("file");
						if (isFile == false) {
							let id = $(this).data("id");
							if (!url) {
								url += id;
							} else {
								url += "/" + id
							}
							console.log("URL" + url);
							$("input[name=parent]").val(url);
							$
									.ajax({
										method : "get",
										data : $("#fileForm").serialize(),
										dataType : "json",
										success : function(resp) {
											let tbody = "";
											let id = $("input[name=parent]")
													.val();
											console.log("id" + id);
											$("#catebody").empty();
											console.log(resp);
											if (resp.length > 0) {
												for (let i = 0; i < resp.length; i++) {
													if (resp[i].isFile == false) {
														tbody += '<div class="blogCateList-cate">';
														tbody += '<a href="#" class="ri-folder-5-fill" id="folder"  data-id="'+ resp[i].fileName +'" data-file = "'+ resp[i].isFile +'"></a>';
														tbody += '<p class="blogCateList-cate-name">'
																+ resp[i].fileName
																+ '</p>';
														tbody += '<p>생성날짜:2022-08-23</p>';
														tbody += '</div>';
													}
												}
												for (let i = 0; i < resp.length; i++) {
													if (resp[i].isFile == true) {
														tbody += '<div class="blogCateList-cate">';
														tbody += '<a href="/Otion/blog/a-in/cate/테스트중" class="ri-file-2-line" id="folder"  data-id="'+ resp[i].fileName +'" data-file = "'+ resp[i].isFile +'"></a>';
														tbody += '<p class="blogCateList-cate-name">'
																+ resp[i].fileName
																+ '</p>';
														tbody += '<p>생성날짜:2022-08-23</p>';
														tbody += '</div>';
													}
												}
												$("#catebody").append(tbody);
											} else {
												tbody += '<div>해당 폴더에는 하위폴더가 없습니다.</div>'
												$("#catebody").append(tbody);
											}
										}
									})
						}
						return false;
					});
</script>
