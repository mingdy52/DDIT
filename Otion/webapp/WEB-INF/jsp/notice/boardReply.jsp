<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 9. 2.     이아인      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal.realMember" var="member"/>
</sec:authorize>
<style>
#writeBtn{
 float: right;
}
span,h5{
	display: inline-block;
}
#writeTextArea{
	font-size: 1rem;
	width: 100%;
	resize: none;
}
#oldTextArea{
	width: 100%;
	resize: none;
}
#upReply,#upClose{
float: right;
}
#upReply{
margin-right: 5px;
}
#reReplyBtn,#cancelBtn{
float: right;
display :inline-block;
}
#reReplyBtn{
	margin-right: 6px;
}
</style>
<!-- 댓글 작성-->
<div id="writeReply">
<form id="writeForm" method="post" class="form-inline" action="${cPath }/board/boWrite">
	<sec:csrfInput/>
	<input type="hidden" name="boardNum" value="${boardNum}"/>
	<input type="hidden" name="writerId" value="${member.getMemId()}"/>
	<input type="hidden" name="boardWriter" value="${boardWriter}"/>
	<table class="table table-borderless mb-0" style="margin-top: 10px;">
		<tr>
			<td>
				<div class="input-group">
				<textarea id="writeTextArea" class="form-control mb-2 mr-2" rows="2" placeholder="댓글을 작성하세요" maxlength="150" name="boReplyContent" required="required"></textarea>
				</div>
			</td>
			<td style="width: 100px;">
				<input style="height: 66px;" type="submit" value="댓글작성" id="writeBtn" class="btn btn-primary waves-effect waves-light" />
			</td>
		</tr>
	</table>
</form>
</div>

<!-- 댓글 view -->
<div class="mt-4" style="height: auto;">
	<div class="p-4 rounded" id="replyView">
		
	</div>         
</div>
<!-- 대댓글 -->
<div id="comback">
<div id="replyAttr" style="display:none;">
<form id="reWriteForm" method="post" class="form-inline" action="${cPath }/board/boReWrite">
	<sec:csrfInput/>
	<input type="hidden" name="boardNum" value="${boardNum}"/>
	<input type="hidden" name="writerId" value="${member.getMemId()}"/>
	<input type="hidden" name="ownerWriter" value=""/>
	<input type="hidden" name="parentBoReplyNum" value=""/>
	<table class="table table-borderless mb-0">
		<tr>
			<td colspan="2">
				<div class="input-group">
				<textarea id="reWriteTextArea" class="form-control mb-2 mr-2" rows="2" placeholder="댓글을 작성하세요" maxlength="150" name="boReplyContent" required="required"></textarea>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			    <input type="button" value="취소" id="cancelBtn" class="btn btn-outline-primary waves-effect waves-light" />
				<input type="submit" value="등록" id="reReplyBtn" class="btn btn-outline-primary waves-effect waves-light" />
			</td>
		</tr>
	</table>
</form>
</div>
</div> 
<script>
var header = '${_csrf.headerName}';
var token = '${_csrf.token}';
let replyView = $("#replyView"); //댓글 리스트 뷰
const getURL="${cPath}/board/reply/${boardNum}"; //댓글조회URL
const loginMember = "${member.getMemId()}"; //로그인 회원아이디
const writeURL= $("#writeForm").attr("action"); //댓글작성 URL
const reWriteURL =$("#reWriteForm").attr("action"); //대댓글 작성 URL
const delReplyURL="${cPath}/board/delReply"//댓글삭제 URL
let writeForm = $("#writeForm");
let reWriteForm = $("#reWriteForm");
let upWriteForm;

(replyGet = function(event) {
	$.ajax({
		url : getURL,
		method : "get",
		dataType : "json",
		success : function(resp, status, jqXHR) {
			let boardReplyList = resp.boardReplyList;
			console.log("ckck", resp.boardReplyList);
			let replyTags = [];
			let replyTag = "";
			if (boardReplyList && boardReplyList.length > 0) {
				replyTags.push(toHtml(boardReplyList));
				console.log("reply",replyTags);
			} else {

				replyTag += "<div>";
				replyTag += "댓글을 입력해보세요.";
				replyTag += "</div>";
				replyTags.push(replyTag);
			}
			replyView.html(replyTags);
			//대댓글 div,form 추가
			$(".a").click(function(e) {
				let target = e.target;
				let rno = target.getAttribute("data-rno")
				let owner=target.getAttribute("data-owner")
				console.log("target", target)
				let repForm = $("#replyAttr");
				repForm.css("display", "block");
				$('input[name=parentBoReplyNum]').attr('value', rno);
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
				//swal("수정");
				let uptarget = e.target;
				let uprno = uptarget.getAttribute("data-rno")
				console.log("uprno",uprno);
				let upText=$(this).closest(".parent").find(".oldData").text();
				$(this).closest(".parent").find(".upBtn").hide();
				$(this).closest(".parent").find(".a").hide();
				$(this).closest(".parent").find(".oldData").text("");
				$(this).closest(".parent").find(".oldData").append(upTextarea(uprno));
				$(this).closest(".parent").find('.thisarea').val(upText);
				$(this).closest(".parent").find('input[name=boReplyNum]').attr('value', uprno);
				upWriteForm = $('#upWriteForm');
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

function upTextarea(){
	let area="";
	area+= "<form id='upWriteForm' method='post' class='form-inline updateForm' action='${cPath }/board/upwrite'>";
	area+= "<input type='hidden' name='boReplyNum'/>";
	area+= "<textarea id='oldTextArea' maxlength='150' name='boReplyContent' required='required' class='form-control mb-2 mr-2 thisarea'></textarea>";
	area+= "<div>";
	area+= "<i id='upClose' onclick='upCancel($(this))' class='ri-close-fill i-btn'></i>";
	area+= "<i id='upReply' onclick='upSubmit($(this))' class='ri-check-fill i-btn'></i>";
	area+= "</div>";
	area+= "</form>";
	return area;
}	
//수정 취소할때
function upCancel(el){
	console.log("el",el);
	el.closest('.parent').find('.upBtn').css('display', 'block');
	el.closest('.parent').find('.a').css('display', 'block');
	el.closest('.parent').find('#upWriteForm').css('display', 'none');
	let text = el.closest('.parent').find('.thisarea').val();
	el.closest(".parent").find(".oldData").text(text);
	$("#reWriteTextArea").val('');
	$("#replyAttr").css("display","none");
	$("#replyAttr").appendTo($("#comback"));
	//replyGet();
}


let toHtml = function(boardReplyList) {
	let tmp = "";
	let length = boardReplyList.length;
	tmp += "<h5 class='font-size-14'>" + length + "개의 댓글</h5>"
	boardReplyList
			.forEach(function(list) {
				let writer = list.writerId;
				if (list.boReplyNum != list.parentBoReplyNum) { //자식글
					tmp += "<div class='child' style='margin-left:40px;'>"
				} else { //부모글일때
					tmp += "<hr>";
					tmp += " <div>"; //class='d-flex border-bottom pb-3'	
				}
				tmp += "<div class='flex-grow-1 parent'>";
				tmp += "<span class='comment-img'>";
				tmp += "<i class='fa fa-user-circle' aria-hidden='true'></i>";
				tmp += "</span>";
				tmp += "<h5 class='font-size-15 mb-3'>&nbsp;작성자:"
				if(writer=='heehee'|| writer=='chulH'){
					tmp +="관리자"
				}else{
					tmp += list.writerId
				}
				tmp += " | " + list.boReplyDate
						+ "</h5>";
				if (loginMember == writer && list.delYn=='N' ) { 
					tmp += "<p class='float-sm-end font-size-14 delBtn' style='cursor: pointer;' data-rno="+list['boReplyNum']+">삭제 &nbsp;</p>"
					tmp += "<p class='float-sm-end font-size-14 upBtn' style='cursor: pointer;'  data-rno="+list['boReplyNum']+">수정 &nbsp;</p>"
				}
				if (list.boReplyNum == list.parentBoReplyNum) {
					tmp += "<p class='float-sm-end font-size-14 a' style='cursor: pointer;' data-rno="+list.boReplyNum +" "+"data-bno="+list.boardNum +" "+"data-owner="+list.writerId+">댓글쓰기&nbsp;</p>"
				}
				tmp += "<div class='oldData' data-rno="+list.boReplyNum+">";
				if(list.delYn=='N'){
					tmp += list.boReplyContent; // 서버쪽이 먼저 실행된다(해석할 수 있는 거)
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
	console.log("ck2222",JSON.stringify(rewriteData));
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
function upSubmit(el){
	let newText = el.closest('.parent').find('.thisarea').val();
	console.log("newText",newText);
	
	const upWriteURL =$('#upWriteForm').attr("action"); //댓글 수정 URL
	console.log("url",upWriteURL);
	
	upWriteForm=el.closest('form');
	console.log("form",upWriteForm);
	
	var dataSet = {};
	dataSet['boReplyNum'] = el.closest('.parent').find('input[name="boReplyNum"]').val();
	dataSet['boReplyContent'] = newText;
	//dataSet['_csrf'] = $('#reWriteForm input[name="_csrf"]').val();
	dataSet['_csrf'] = token;
	
	
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
				data : JSON.stringify(dataSet),
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
						swal("입력정보가 누락되었거나 정확하지 않습니다.");
						break;
					case "성공":
						$("#oldTextArea").val('');
						$(".close").trigger("click");
						$("#replyAttr").css("display","none");
						$("#replyAttr").appendTo($("#comback"));
						replyGet();
						break;
					case "실패":
						$("#oldTextArea").val('');
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
</script>