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
#upSubmit,#upClose{
float: right;
}
#upSubmit{
margin-right: 5px;
}
</style>
<!-- 댓글 작성-->
<div id="writeReply">
<form id="writeForm" method="post" class="form-inline" action="${cPath }/board/reply/NOTI2209020001">
	<sec:csrfInput/>
	<input type="text" name="postNum" value="NOTI2209020001"/>
	<table class="col-md-10">
		<tr>
			<td colspan="2">
				<div class="input-group">
				<textarea id="writeTextArea" class="form-control mb-2 mr-2" rows="2" placeholder="댓글을 작성하세요" maxlength="150" name="blReplyContent" required="required"></textarea>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="3">
				<input type="submit" value="댓글작성" id="writeBtn" class="btn btn-outline-primary waves-effect waves-light" />
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
<form id="reWriteForm" method="post" class="form-inline" action="${cPath }/blog/${memId}/reply/reWrite">
	<sec:csrfInput/>
	<input type="text" name="boardNum" value="NOTI2209020001"/>
	<input type="text" name="writerId" value="${member.getMemId()}"/>
	<input type="text" name="parentBoReplyNum" value=""/>
	<table class="col-md-10">
		<tr>
			<td colspan="2">
				<div class="input-group">
				<textarea id="reWriteTextArea" class="form-control mb-2 mr-2" rows="2" placeholder="댓글을 작성하세요" maxlength="150" name="blReplyContent" required="required"></textarea>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="3">
				<input type="submit" value="등록" id="reReplyBtn" class="btn btn-outline-primary waves-effect waves-light" />
			    <input type="button" value="취소" id="cancelBtn" class="btn btn-outline-primary waves-effect waves-light" />
			</td>
		</tr>
	</table>
</form>
</div>
</div> 
 aass
<script>
let replyView = $("#replyView"); //댓글 리스트 뷰
const getURL=$("#writeForm").attr("action");
const loginMember = "${member.getMemId()}"; //로그인 회원아이디
const user ="${member.getRoleList()[0].roleName}";
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

				replyTag += "<div style='text-align:center;'>";
				replyTag += "댓글을 입력해보세요.";
				replyTag += "</div>";
				replyTags.push(replyTag);
			}
			replyView.html(replyTags);
			//대댓글 div,form 추가
			$(".a").click(function(e) {
				//e.stopPropagation(); // 이벤트 전파 막기
				let target = e.target;
				let rno = target.getAttribute("data-rno")
				console.log("target", target)
				let repForm = $("#replyAttr");
				repForm.css("display", "block");
				$('input[name=parentBoReplyNum]').attr('value', rno);
				repForm.appendTo($("div[data-rno=" + rno + "]")); // appendTo -> 이미 존재하는 객체이면 자리 이동
			});
			//대댓글 취소
			$("#cancelBtn").click(function() { //on 사용 주의!
				$("#replyAttr").css("display", "none");
				$("#reWriteTextArea").val('');

			});
			
			//댓글수정
			$(".upBtn").click(function(e) {
				//alert("수정");
				let uptarget = e.target;
				let uprno = uptarget.getAttribute("data-rno")
				let upText=$(this).closest(".parent").find(".oldData").text();
				$(this).closest(".parent").find(".delBtn").val("취소");
				$(this).closest(".parent").find(".upBtn").hide();
				$(this).closest(".parent").find(".a").hide();
				$(this).closest(".parent").find(".oldData").text("");
				$(this).closest(".parent").find(".oldData").append(upTextarea());
				$('#oldTextArea').val(upText);
			
				//console.log("didi",$(this).closest(".parent").find(".oldData").text());
				/* $('input[name=boReplyNum]').attr('value', uprno);
				let oldData=$(this).closest(".parent").find(".oldData").text();
				$('#oldTextArea').val(oldData);  */
			});
				
			
			
		},
		error : function(jqXHR, status, error) {
			console.log(jqXHR);
			console.log(status);
			console.log(error);
		}
	});
})();

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
					tmp += "<p class='float-sm-end font-size-14 upBtn' style='cursor: pointer;' data-bs-toggle='modal' data-bs-target='#firstmodal' data-rno="+list['boReplyNum']+">수정 &nbsp;</p>"
				}
				if (list.boReplyNum == list.parentBoReplyNum) {
					tmp += "<p class='float-sm-end font-size-14 a' style='cursor: pointer;' data-rno="+list.boReplyNum +" "+"data-bno="+list.boardNum +">댓글쓰기&nbsp;</p>"
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
function upTextarea(){
	let area="";
	area+= "<form id='upWriteForm' method='post' class='form-inline' action='${cPath }/board/upwrite'>";
	area+= "<input type='hidden' name='boReplyNum'/>";
	area+= "<textarea id='oldTextArea' maxlength='150' name='blReplyContent' required='required'></textarea>";
	area+= "<div>";
	area+= "<button type='button' id='upClose' onclick='upClose()' class='btn btn-primary waves-effect waves-light'>"+"취소하기"+"</button>";
	area+= "<button type='button' id='upSubmit' onclick='upSubmit()' class='btn btn-primary waves-effect waves-light'>"+"수정하기"+"</button>";
	area+= "</div>";
	area+= "</form>";
	return area;
}		

$("#upClose").click(function(e){
	alert("di");
});


</script>