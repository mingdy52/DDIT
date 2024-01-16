<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 8. 27.    이아인    최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal.realMember" var="member"/>
</sec:authorize>
<style>
#writeBtn{
 float: right;
 height: 65px;
 margin-left: 5px;
}
span,h5{
	display: inline-block;
}
#reReplyBtn{
	margin-right: 6px;
}
#cancelBtn,#reReplyBtn{
	display :inline-block;
	float: right;
	
}
#writeTextArea{
	font-size: 1rem;
	width: 100%;
	resize: none;
} 
#reWriteTextArea{
	resize: none;
}
#oldTextArea{
	 width: 100%;
	resize: none;
	overflow: hidden;
}
#replyAttr{
 	width: 100%;
/* 	clear:both; */
	float:left;

}
.child{
	clear:both;
	margin: 2px;
	
}


</style>
<div id="writeReply">
<form id="writeForm" method="post" class="form-inline" action="${cPath }/blog/${memId}/reply/write">
	<!--<input type="text" name="postNum" value="${post.postNum }"/>  -->
	<sec:csrfInput/>
	<input type="hidden" name="postNum" value="${post.postNum}"/>
	<input type="hidden" name="blReplyWriter" value="${member.getMemId()}"/>
	<table class="col-md-10" style="margin: auto;">
		<tr>
			<td colspan="2">
				<div class="d-flex justify-content-center">
					<div class="input-group" >
					<textarea id="writeTextArea" class="form-control mb-2 mr-2" rows="2" placeholder="댓글을 작성하세요" maxlength="150" name="blReplyContent" required="required"></textarea>
					</div>
					<input type="submit" value="댓글작성" id="writeBtn" class="btn btn-primary waves-effect waves-light" />
				</div>
			</td>
		</tr>
	</table>
</form>
</div>
<div class="mt-1" style="height: auto; min-height:300px; width: 85%; margin: auto;">
	<div class="p-2 rounded" id="replyView">	
	</div>
</div>
<!-- 댓글 작성 -->

<!-- 대댓글 부분 --> 
<div id="comback">
<div id="replyAttr" style="display:none;">
<form id="reWriteForm" method="post" class="form-inline" action="${cPath }/blog/${memId}/reply/reWrite">
	<sec:csrfInput/>
	<input type="hidden" name="postNum" value="${post.postNum}"/>
	<input type="hidden" name="blReplyWriter" value="${member.getMemId()}"/>
	<input type="hidden" name="ownerWriter" value=""/>
	<input type="hidden" name="parentBlReplyNum" value=""/>
	<table class="col-md-10" style="float: right; width: 96%; margin-bottom: 20px;  margin-top: 20px;">
		<tr>
			<td colspan="2">
				<div class="input-group">
				<textarea id="reWriteTextArea" class="form-control mb-2 mr-2" rows="2" placeholder="댓글을 작성하세요" maxlength="150" name="blReplyContent" required="required"></textarea>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			    <input type="button" value="취소" id="cancelBtn" class="btn btn-light btn-sm waves-effect waves-light" />
				<input type="submit" value="등록" id="reReplyBtn" class="btn btn-primary btn-sm waves-effect waves-light" />
			</td>
		</tr>
	</table>
</form>
</div>
</div>

<!-- 댓글 수정 모달 -->
<div class="modal fade show" id="firstmodal" tabindex="-1"
	style="display: none;" aria-modal="true" role="dialog">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">댓글 수정</h5>
				<!-- <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button> -->
				<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
			</div>
			<form id="upWriteForm"  method="post" class="form-inline" action="${cPath }/blog/${memId}/reply/upWrite">
				<sec:csrfInput/>
				<input type="hidden" name="blReplyNum"/>
				<div class="modal-body">
					<div style="display: flex;">
						<textarea class="form-control" id="oldTextArea" maxlength="150" name="blReplyContent" required="required"></textarea>
						<button type="button" onclick="upSubmit()" class="btn btn-primary waves-effect waves-light" id="modalUp" style="width:105px;">수정</button>
					<!--     <button type="button" class="btn btn-light waves-effect close" data-bs-dismiss="modal" id="modalCl">닫기</button> -->
					</div>
				</div>
				
			
	  	    </form>	
		</div>
	</div>
</div>
<script>
	var header = '${_csrf.headerName}';
	var token = '${_csrf.token}';
	let replyView = $("#replyView"); //댓글 리스트 뷰
	const loginMember = "${member.getMemId()}"; //로그인 회원아이디
	const urlMember = "${memId}"; //url의 회원아이디
	const writeURL = $("#writeForm").attr("action"); //댓글작성 URL
	const reWriteURL = $("#reWriteForm").attr("action"); //대댓글 작성 URL
	const upWriteURL=$('#upWriteForm').attr("action"); //댓글 수정 URL
	const delReplyURL="${cPath}/blog/${memId}/reply/delReply"//댓글삭제 URL
	const getURL = "${cPath}/blog/${memId}/reply/${post.postNum}"; //댓글리스트 URL
	let writeForm = $("#writeForm");
	let reWriteForm = $("#reWriteForm");
	let upWriteForm=$("#upWriteForm");

	
	
	(replyGet = function(event) {
		$.ajax({
			url : getURL,
			method : "get",
			dataType : "json",
			success : function(resp, status, jqXHR) {
				let replyList = resp.replyList;
				console.log("ckck", resp.replyList);
				let replyTags = [];
				let replyTag = "";
				if (replyList && replyList.length > 0) {
					replyTags.push(toHtml(replyList));
				} else {

					replyTag += "<div style='text-align:center; font-size:20px;'>";
					replyTag += "댓글을 작성해보세요";
					replyTag += "</div>";
					replyTags.push(replyTag);
				}
				replyView.html(replyTags);
				//대댓글 div,form 추가
				$(".a").click(function(e) {
					//e.stopPropagation(); // 이벤트 전파 막기
					let target = e.target;
					let rno = target.getAttribute("data-rno")
					let owner=target.getAttribute("data-owner")
					console.log("target", target)
					let repForm = $("#replyAttr");
					repForm.css("display", "block");
					$('input[name=parentBlReplyNum]').attr('value', rno);
					$('input[name=ownerWriter]').attr('value',owner);
					repForm.appendTo($("div[data-rno=" + rno + "]")); // appendTo -> 이미 존재하는 객체이면 자리 이동
				});
				//대댓글 취소
				$("#cancelBtn").click(function() { //on 사용 주의!
					$("#replyAttr").css("display", "none");
					$("#reWriteTextArea").val('');

				});
				//댓글수정
				$(".upBtn").click(function(e) {
					let uptarget = e.target;
					let uprno = uptarget.getAttribute("data-rno")
					$('input[name=blReplyNum]').attr('value', uprno);
					let oldData=$(this).closest(".parent").find(".oldData").text();
					$('#oldTextArea').val(oldData); 
				});
				//댓글 삭제
				$(".delBtn").click(function(e){
					let deltarget = e.target;
					let delNum=deltarget.getAttribute("data-rno")
					delReply(delNum);
				});
			},
			error : function(jqXHR, status, error) {
				console.log(jqXHR);
				console.log(status);
				console.log(error);
			}
		});
	})();
				

	let toHtml = function(replyList) {
		let tmp = "";
		let length = replyList.length;
		tmp += "<h5 class='font-size-14'>" + length + "개의 댓글</h5>"
		replyList
				.forEach(function(list) {
					let writer = list.blReplyWriter;
					if (list.blReplyNum != list.parentBlReplyNum) { //자식글
						tmp += "<div class='child' style='margin-left:40px;'>"
					} else { //부모글일때
						tmp += "<hr>";
						tmp += " <div style='margin-bottom: 20px;'>"; //class='d-flex border-bottom pb-3'	
					}
					tmp += "<div class='flex-grow-1 parent'>";
					tmp += "<span class='comment-img'>";
					tmp += "<i class='fa fa-user-circle' aria-hidden='true'></i>";
					tmp += "</span>";
					tmp += "<h5 class='font-size-15 mb-3'>&nbsp;작성자:"
							+ list.blReplyWriter + " | " + list.blReplyDate
							+ "</h5>";
					if (loginMember == writer && list.blReplyDelYn=='N' ) { 
						tmp += "<p class='float-sm-end font-size-14 delBtn' style='cursor: pointer;' data-rno="+list['blReplyNum']+">삭제 &nbsp;</p>"
						tmp += "<p class='float-sm-end font-size-14 upBtn' style='cursor: pointer;' data-bs-toggle='modal' data-bs-target='#firstmodal' data-rno="+list['blReplyNum']+">수정 &nbsp;</p>"
					}
					if (list.blReplyNum == list.parentBlReplyNum) {
						tmp += "<p class='float-sm-end font-size-14 a' style='cursor: pointer;' data-rno="+list.blReplyNum +" "+"data-bno="+list.postNum +" "+"data-owner="+list.blReplyWriter+">댓글쓰기&nbsp;</p>"
					}
					tmp += "<div class='oldData' data-rno="+list.blReplyNum+">";
					if(list.blReplyDelYn=='N'){
						tmp += "<p>"+list.blReplyContent+"</p>"; // 서버쪽이 먼저 실행된다(해석할 수 있는 거)
					}else{
						tmp +='삭제된 댓글 입니다.';
					}
					tmp += "</div>";
					tmp += "</div>" //flex-grow-1
					tmp += "</div>" //class='d-flex border-bottom pb-3
					tmp += "<br>"
				})
		return tmp;

	}

//댓글 삭제 
let delReply=function(delNum){
	swal({
		  title: "삭제하시겠습니까?",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
		})
		.then(function (willDelete){
		  if (willDelete) {
			$.ajax({
				method : 'GET',
			    url: delReplyURL,
			    data: {
			    	"delNum" : delNum
			    },
			    dataType : "text",
			    success: function(resp){
			    	if(resp=='성공'){
				    	swal({
			    			  title: "삭제완료!",
			    			  icon: "success",
			    		});
			    		
			    	}else{
			    		swal({
			    			  title: "삭제 실패, 다시시도해주세요!"
			    			 
			    		});
			    	}
			    	$("#replyAttr").css("display","none");
					$("#replyAttr").appendTo($("#comback"));
			    	replyGet();
			    },
			    error: function(xhr, status, error) {
			    	console.log(error);
			    }  
			 });
			
		  } else {
			  return false;
		  }
	});
}
<%-- form데이터 serialize  --%>
jQuery.fn.serializeObject = function() {
	       var obj = null;
	       try {
	           if (this[0].tagName && this[0].tagName.toUpperCase() == "FORM") {
	               var arr = this.serializeArray();
	               if (arr) {
	                   obj = {};
	                   jQuery.each(arr, function() {
	                       obj[this.name] = this.value;
	                   });
	               }
	           }
	       } catch (e) {
	          swal(e.message);
	       } finally {
	       }
	       return obj;
	};

	//댓글작성
	writeForm.on("submit",function(){
		event.preventDefault();
		let writeText=$("#writeTextArea").val();
		let writeData=writeForm.serializeObject();
		if(!loginMember){
			swal("로그인 된 회원만 작성가능합니다.","");
			$("#writeTextArea").val('');
			return false;
		}else{
			 if(writeText.indexOf("<script>") != -1 || writeText.indexOf("<SCRIPT>") != -1){
				swal("지원하지 않는 문자입니다.","");
				return false;
			}else{ 
				$.ajax({
					url : writeURL,
					data : JSON.stringify(writeData),
					method : 'POST',
					dataType : "text",
					contentType : "application/json;charset=utf-8",
					beforeSend: function (xhr) {
				            xhr.setRequestHeader(header, token);
				    },
					success : function(resp, status, jqXHR) {
						switch (resp) {
						case "부적절":
							swal("입력정보가 누락되었거나 정확하지 않습니다.");
							$("#writeTextArea").val('');
							break;
						case "성공":
							//swal("댓글 작성 성공","");
							$("#writeTextArea").val('');
							$("#replyAttr").css("display","none");
							$("#replyAttr").appendTo($("#comback"));
							replyGet();
							break;
						case "실패":
							swal("댓글작성 실패, 다시 시도해주세요","")
							$("#writeTextArea").val('');
							break;
						}
						
					},
					error : function(jqXHR, status, error) {
						console.log(jqXHR);
						console.log(status);
						console.log(error);
					}
				});
			}
		}
	});
 
//대댓글작성
reWriteForm.on("submit",function(){
	let reWriteText=$("#reWriteTextArea").val();
	//alert(reWriteText);
	let rewriteData=reWriteForm.serializeObject();
	event.preventDefault();
	if(!loginMember){
		swal("로그인 된 회원만 작성가능합니다.","");
		$("#reWriteTextArea").val('');
		return false;
	}else{
		 if(reWriteText.indexOf("<script>") != -1 || reWriteText.indexOf("<SCRIPT>") != -1){
			swal("지원하지 않는 문자입니다.","");
			return false;
		}else{ 
			$.ajax({
				url : reWriteURL,
				data : JSON.stringify(rewriteData),
				method : 'POST',
				dataType : "text",
				contentType : "application/json;charset=utf-8",
				beforeSend: function (xhr) {
			            xhr.setRequestHeader(header, token);
			    },
				success : function(resp, status, jqXHR) {
					switch (resp) {
					case "부적절":
						$("#reWriteTextArea").val('');
						swal("입력정보가 누락되었거나 정확하지 않습니다.");
						break;
					case "성공":
						//swal("대댓글 작성 성공","");
						$("#reWriteTextArea").val('');
						$("#replyAttr").css("display","none");
						$("#replyAttr").appendTo($("#comback"));
						replyGet();
						break;
					case "실패":
						$("#reWriteTextArea").val('');
						swal("댓글작성 실패, 다시 시도해주세요","")
						break;
					}
					
				},
				error : function(jqXHR, status, error) {
					console.log(jqXHR);
					console.log(status);
					console.log(error);
				}
			});
		}
	}
});

//댓글 수정
let upSubmit=function(e){
	let newText=$("#oldTextArea").val();
	let upWriteData=upWriteForm.serializeObject();
	if(!loginMember){
		swal("로그인 된 회원만 작성가능합니다.","");
		$("#oldTextArea").val('');
		return false;
	}else{
		 if(newText.indexOf("<script>") != -1 || newText.indexOf("<SCRIPT>") != -1){
			swal("지원하지 않는 문자입니다.","");
			return false;
		}else{ 
			$.ajax({
				url : upWriteURL,
				data : JSON.stringify(upWriteData),
				method : 'POST',
				dataType : "text",
				contentType : "application/json;charset=utf-8",
				beforeSend: function (xhr) {
			            xhr.setRequestHeader(header, token);
			    },
				success : function(resp, status, jqXHR) {
					switch (resp) {
					case "부적절":
						$("#oldTextArea").val('');
						$('#firstmodal').modal("hide");
						swal("입력정보가 누락되었거나 정확하지 않습니다.");
						break;
					case "성공":
						$("#oldTextArea").val('');
						$(".close").trigger("click");
						$("#replyAttr").css("display","none");
						$("#replyAttr").appendTo($("#comback"));
						$('#firstmodal').modal("hide");
						replyGet();
						break;
					case "실패":
						$("#oldTextArea").val('');
						$('#firstmodal').modal("hide");
						swal("댓글작성 실패, 다시 시도해주세요","")
						break;
					}
					
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


</script>


