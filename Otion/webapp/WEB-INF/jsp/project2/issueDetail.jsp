<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
	<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<sec:authentication property="principal.realMember" var="member" />	
<style>
.circle {
	width: 60px;
	height: 60px;
	border-radius: 50%;
	background-color: gray;
	font-weight: bold;
	font-size: 14px;
	color: white;
	text-align: center;
	margin: auto;
	padding-top: 18px;
}

.form-control {
	background-color: #f1f2ff;
	background-clip: padding-box;
	border: 1px solid #D5D1D6;
	line-height: 1.5;
	display: block;
	appearance: none;
	border-radius: 0.25rem;
	transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out,
		-webkit-box-shadow 0.15s ease-in-out;
	resize: none;
}

.form-control:focus {
	background-color: #f1f2ff;
	background-clip: padding-box;
	border: 1px solid #D5D1D6;
	line-height: 1.5;
	display: block;
	appearance: none;
	border-radius: 0.25rem;
	transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out,
		-webkit-box-shadow 0.15s ease-in-out;
	resize: none;
}
</style>
<sec:authentication property="principal.realMember" var="member" />
<div style="width: 90%; margin: auto;">
<table class="table table-sm m-0">
	<tr>
		<td style="width: 70px;">
		<c:if test="${not empty issue.colleague.cooProfile }">
		<img src="${cPath }/resources/colleagueProfile/${issue.colleague.cooProfile}" class='rounded-circle avatar-xs' style="text-align: center; vertical-align: middle;">
		</c:if>
		<c:if test="${empty issue.colleague.cooProfile }">
		<img src="${cPath }/resources/images/noImg.png" class='rounded-circle avatar-xs' style="text-align: center; vertical-align: middle;">
		</c:if>
		
		</td>
		<td style="width: 80%; padding-top: 18px;"><h3>${issue.issueTitle}</h3></td>
		<td style="padding-top: 18px;"><div>${issue.issueReq}</div><div style="margin-top:5px;">${issue.issueDate}</div></td>
		<td style="display: none;" class="pjId">${pjId}</td>
		<td  style="display: none;" class="issueNum">${issue.issueNum}</td>
	</tr>
	<tr>
		<td colspan="3" style="padding: 30px 0 0 20px; font-size: 20px;">
		 	<c:if test="${not empty issue.attatch.saveNm }">		 	
				<img src="${cPath }/resources/issueImage/${issue.attatch.saveNm}" width="1200" height="600">
		 	</c:if>
			<div style="min-height: 400px;">${issue.issueContent}</div>
		</td>
	</tr>
	<c:if test="${issue.issueReq eq member.memId}">
	<tr>
	<td colspan="5">
      <button id="removeIssue" style="float: right; margin-right: 20px;" class="btn btn-light waves-effect">삭제</button>
      <button style="float: right; margin-right: 10px;" class="btn btn-light waves-effect" >수정</button>
      </td>
	</tr>
	</c:if>
</table>

</div>


     <div>
   
     <section class="mb-3" id="replySection">
							<div class="card bg-light">
								<div class="card-body ">
									<div id="replyForm" style="width: 80%; margin: auto;">
										<table class="table table-borderless mb-0">
											<tr >
												<td style="width: 90%;">
												<div><i class=" ri-upload-2-fill" id="insertAttatch" style="float: right: ;"></i>첨부파일</div>
												<textarea  required="required"  class="form-control mb-2 mr-2" rows="3" placeholder="댓글을 작성하세요" name="blReplyContent"></textarea><br>
												</td>
												<td>
													<br><button type="button" onclick="writeReply('0')" class="btn btn-primary waves-effect waves-light" style="height: 80px; padding: 0 20px 0 20px; ">댓글<br>작성</button></td>
											</tr>




										</table>
									</div>
									
									<div style="width: 80%; margin: auto;">
										<ui id="reply" style="list-style:none;"> </ui>
									</div>
								</div>
							</div>
						</section>
     
     
     </div>

     
 
	<input type="file" name="replyAttatch" style="display: none;">
	


<form method="POST" id="removeIssueForm" action="${cPath}/project/${pjId}/issue/${issueNum}/delete">
	<sec:csrfInput />
</form>


<script>
var header = '${_csrf.headerName}';
var token =  '${_csrf.token}';
ReplyList();


$("#replySection").on("click","#insertAttatch", function() {
	$("input[name=replyAttatch]").click();
});


	function ReplyList() {
		let issueNum = $(".issueNum").text();

			
		
		$.ajax({
			url : "${cPath}/project/${pjId}/issue/reply",
			data : data = {
				"issueNum" : issueNum
			},
			dataType : "json" // text, html, json, xml, script -> main type : text, 파일 업로드 처리를 비동기로? (FormData)
			,
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

	};

	
	$("#removeIssue").on("click",function(){
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
				  removeIssueForm.submit();
				  break;
				  
				  default:
					  swal('취소되었습니다.');  
			  }
			  
			});
		
		
	});
	
	

	
	function writeReply(index) {
		let data;
		if(index==0){
		
			let issueNum = $(".issueNum").text();
			
			let solveWay = $("#replyForm").find("textarea").val().replace(/(?:\r\n|\r|\n)/g, '<br />');
			let memId = "${member.memId}";
			let pjId = $(".pjId").text();
		 	var file = $("input[type=file]")[0].files[0];
			 let fileForm = new FormData();
			 
			 fileForm.append("issueNum",issueNum);
			 fileForm.append("solveWay",solveWay);
			 fileForm.append("workReq",memId);
			 fileForm.append("pjId",pjId);
			 if(file){
			 fileForm.append("replyAttatch",file);}
			 	if($("#replyForm").find("textarea").val()!=""){
					$.ajax({
						url : "${cPath}/project/${pjId}/issue",
						data : fileForm,
						processData: false,
					    contentType: false,
						type : 'POST',
						dataType : "json" // text, html, json, xml, script -> main type : text, 파일 업로드 처리를 비동기로? (FormData)
						,
						beforeSend	: function (xhr) {
				            xhr.setRequestHeader(header, token);
				        },
						success : function(resp, status, jqXHR) {
							let index = [];
							index = makeReply(resp);
							$("#reply").html(index);
							$("input[type=file]").val("");
						
			
						},
						error : function(jqXHR, status, error) {
							console.log(jqXHR.responseText);
							console.log(status);
							console.log(error);
						}
			
					});
			 	}
		}else {

			let issueReplyNum =$("#modifyForm").closest("li").find(".issueReplyNum").text();
			let attatchNum;
			if($("#modifyForm").closest("li").find(".attatchNum").text()){
				attatchNum = $("#modifyForm").closest("li").find(".attatchNum").text();
			}
			let solveWay = $("#modifyForm").find("textarea").val().replace(/(?:\r\n|\r|\n)/g, '<br />');
			var file = $("input[type=file]")[0].files[0];
			 let fileForm = new FormData();
			 if(file){
			 fileForm.append("replyAttatch",file);
			 }
			 fileForm.append("issueNum","${issueNum}");
			 fileForm.append("issueReplyNum",issueReplyNum);
			 fileForm.append("solveWay", solveWay);
			if(attatchNum){
				fileForm.append("attatchNum",attatchNum);
			}
			
			console.log("fileForm",fileForm);
			if($("#modifyForm").find("textarea").val()!=""){
				$.ajax({
					url : "${cPath}/project/${pjId}/issue/reply/modify",
					data : fileForm,
					processData: false,
				    contentType: false,
					type : 'POST', // text, html, json, xml, script -> main type : text, 파일 업로드 처리를 비동기로? (FormData)
					dataType : "json",
					success : function(resp, status, jqXHR) {
						let index = [];
						console.log(resp);
						index = makeReply(resp);
						$("#reply").html(index);
						$("input[type=file]").val("");
	
					},
					beforeSend	: function (xhr) {
			            xhr.setRequestHeader(header, token);
			        },
					error : function(jqXHR, status, error) {
						console.log(jqXHR);
						console.log(status);
						console.log(error);
					}
	
				});
			}

		}

	}

	function makeReply(reply) {
		let index = [];
		console.log(reply.length);
		for (var i = 0; i < reply.length; i++) {
			let div = $("<hr><li></li>");

			let attach =reply[i].attach;			
			let data = "&nbsp" + reply[i].workReq ;
				if(reply[i].solveDate!=null){
					data+="&nbsp|&nbsp"+ reply[i].solveDate;
				}

			
				if(reply[i].cooProfile){
					div.append("<div class='parentReply'><div class='parentInfo' style='display:flex;'><img src='${cPath}/resources/colleagueProfile/"+ reply[i].cooProfile +"'class='rounded-circle avatar-xs'>"
					+ "</img><span class='issueReplyNum' style='display:none;'>"
									+ reply[i].issueReplyNum
									+ "</span><span style='font-weight:bold; margin-left:2%; margin-top:1%; '>"
									+ data
									+ "</span><span class='addDate' style='cursor: pointer; margin-top:1%; margin-left:2%;'>채택</span></div><div class='replyContent' style='margin-left:7%;'>"
									+ reply[i].solveWay + "</div></div>");
					
				}else{					
					div.append("<div class='parentReply'><div class='parentInfo' style='display:flex;'><span class='ri-account-circle-fill fa-2x'></span><span class='issueReplyNum' style='display:none;'>"
									+ reply[i].issueReplyNum
									+ "</span><span style='font-weight:bold; margin-left:2%; margin-top:1%; '>"
									+ data
									+ "</span><span class='addDate' style='cursor: pointer; margin-top:1%; margin-left:2%;'>채택</span></div><div class='replyContent' style='margin-left:7%;'>"
									+ reply[i].solveWay + "</div></div>");
				}
				div.find(".addDate").attr("onclick","addDate('"+reply[i].issueReplyNum+"')")
				if(attach!=null){
				div.find(".replyContent").append("<br><br><div class='attatchNum' style='display:none;'>"+attach.attatchNum+"</div><a href='${cPath}/download?attatchNum="+attach.attatchNum+"&attatchOrder="+attach.attatchOrder+"'>"+attach.originNm+"</a>");
				}
				if ("${member.memId}" == reply[i].workReq) {
					div.find(".parentInfo")
							.append(
									"<span style='cursor: pointer; margin-top:1%; margin-left:2%;' class='modifyReply'>수정</span><span style='cursor: pointer; margin-top:1%; margin-left:2%;' class='removeParentReply'>삭제</span>");
					div.find(".parentInfo").find(".removeParentReply").attr("onclick","removeParentReply('"+reply[i].issueReplyNum+"')")
					div.find(".modifyReply").click(function(){
						
						if($("#reply").find("textarea").length==0){		
							
							$(this).closest("li").append("<table  id='modifyForm' class='table table-borderless mb-0'>"
														+ "<tr>"
														+"<td style='width: 85%;'>"
														+"<div><i class=' ri-upload-2-fill' id='insertAttatch' style='float: right: ;''></i>첨부파일</div>"
														+"<textarea  class='form-control' rows='3' placeholder='댓글을 작성하세요' maxlength='150' name='blReplyContent' required='required'></textarea><br>"
														+"</td>"
														+"<td>"
														+"<br><button class='btn btn-primary waves-effect waves-light modifybtn' style='height: 80px; padding: 0 20px 0 20px;'>댓글<br>작성</button></td>"
														+"<td>"
														+"<br><button  class='btn btn-primary waves-effect waves-light cancle' style='height: 80px; padding: 0 20px 0 20px;'>취소</button></td>"
														+"</tr>"
														+"</table>");
							$(this).closest("li").find("#modifyForm").find(".modifybtn").attr("onclick","writeReply('1')")
				}else{
					$("#reply").find("#modifyForm").remove();
					$("input[type=file]").val("");
				}
					}
					);
					
				}
				
				if ("${member.memId}" != "${issue.issueReq}") {  
						
					div.find(".parentInfo").find(".addDate").remove();
				}

				
			
			
		

			if (reply[i].solveDate) {
				div.find(".parentInfo").find(".addDate").remove();
				div.find(".parentInfo").find(".modifyReply").remove();
				div.find(".parentInfo").find(".removeParentReply").remove();

			}

			index.push(div);
		}

		return index;

	}

	function removeParentReply(issueReplyNum) {
		let data;
		let parentReply=$("#reply").find(".parentReply")
		for(var i=0; i<parentReply.length; i++){
			if(parentReply.eq(i).find(".issueReplyNum").text()==issueReplyNum){
				 data = {
						 	"issueNum" : "${issueNum}",
							"issueReplyNum" : issueReplyNum,
							"attatchNum" : parentReply.eq(i).find(".attatchNum").text()
						};
				
				
			}
		}
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
				  $.ajax({
						url : "${cPath}/project/${pjId}/issue/reply/remove",
						data : data,
						dataType : "json" // text, html, json, xml, script -> main type : text, 파일 업로드 처리를 비동기로? (FormData)
						,
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
	
	}

	
	$("#reply").on("click",".cancle",function(){
		
		$("#reply").find("#modifyForm").remove();
	
	});
		

	function addDate(issueReplyNum) {
		
		let data = {
			"issueReplyNum" : issueReplyNum,
			"issueNum" : "${issueNum}"
		};

		$.ajax({
			url : "${cPath}/project/${pjId}/issue/reply/adddate",
			data : data,
			dataType : "json" // text, html, json, xml, script -> main type : text, 파일 업로드 처리를 비동기로? (FormData)
			,
			success : function(resp, status, jqXHR) {
				let index = [];
				index = makeReply(resp);
				$("#reply").html(index);
				 swal('채택되었습니다','',"success");
			},
			error : function(jqXHR, status, error) {
				console.log(jqXHR);
				console.log(status);
				console.log(error);
			}

		});

	}
</script>