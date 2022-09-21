<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 8. 25.      이아인      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
#delBtn{
	float:  right;
}
#searchUI{
	width: 50%;
	margin: auto;
}

#searchBtn{
	padding: 10px;
	padding-right: 24px;
	padding-left: 15px;
}
a{
color: black;
}
#searchType{
	width: 100px;
	background-position: right 0px center;
	padding: 5px;
}
#delBtn{
	font-size: 18px;
}
</style>

<h2>댓글관리</h2>
<hr>
<div id="searchUI" class="d-flex justify-content-center">
	<select id="searchType" name="searchType" class="custom-select custom-select-sm form-control form-control-sm form-select form-select-sm">
		<option value="">전체</option>
		<option value="postTitle">게시글</option>
		<option value="blReplyContent">내용</option>
	</select> 
	<input type="text" name="searchWord" placeholder="검색어" class="form-control" autocomplete="off" onkeyup="if(window.event.keyCode==13){f_search()}"> 
	<input onclick="f_search()" id="searchBtn" type="button" value="검색" class="btn btn-primary btn-rounded waves-effect waves-light">
</div>

<div class="card-body">
	<br>
	<div class="table-responsive">
		<table class="table mb-0" style="color: black;">

			<thead>
				<tr>
					<th><input type="checkbox" class="form-check-input" id="CheckAll"></th>
					<th>작성한 댓글</th>
					<th><i id="delBtn" class=" dripicons-trash" onclick="replyDeleteClick()"></i></th>
				</tr>
			</thead>
			<tbody id="replyTbody">
				
			</tbody>
			<tfoot>
				<tr>
					<td colspan="8"  style="border-bottom: none;">
						<div class="d-flex justify-content-center" id="replyPagingArea">
						</div>

					</td>
				</tr>
			</tfoot>
		</table>
	</div>

</div>

<form id="searchForm" action="${cPath }/blog/${memId}/getReply">
   <input type="hidden" name="replyPage" placeholder="page">
   <input type="hidden" name="searchType" placeholder="searchType"/>
   <input type="hidden" name="searchWord" placeholder="searchWord"/>
</form>


<script>
let replyTbody=$("#replyTbody");
let replyPagingArea=$("#replyPagingArea");
let searchUI = $("#searchUI");
let searchForm=$("#searchForm");
const selectURL=searchForm.attr("action");
const loginURL="${cPath}/login";
const checkURL="${cPath}/blog/blogManage/check";
const delURL="${cPath}/blog/${memId}/delReply"

$("[name='searchType']").val("${replypagingVO.simpleCondition.searchType}");
$("[name='searchWord']").val("${replypagingVO.simpleCondition.searchWord}");

replyPagingArea.on("click","a",function(event){
	let replyPage=$(this).data("page");
	searchForm.find("[name=replyPage]").val(replyPage);
	searchForm.submit();
});

function f_search(event){
  searchForm.get(0).reset();
   let inputs = searchUI.find(":input[name]");
   $(inputs).each(function(idx, input){
      let name = $(this).attr("name");//search type, searchword가져옴
      let value = $(this).val();
      searchForm.find("[name="+name+"]").val(value);//value 넣음
   });
   searchForm.submit();	
}
let makeReplyTr=function(index,reply){
	let inputTag=$("<input>").attr("type","checkbox");
				 inputTag.addClass("form-check-input");
				 inputTag.attr("id",reply.blReplyNum);
				 inputTag.attr("name","check");
				 inputTag.attr("value",reply.blReplyNum);
	let thTag=$("<th>").append(inputTag);
	let aContentTag=$("<a>").attr("href","${cPath}/blog/"+reply.blogerId+"/post/"+reply.postNum).text(reply.blReplyContent);
	let aTitleTag=$("<a>").text(reply.postTitle);
	let innerTag=$("<h5>").append(aContentTag);	 
				          
	let tdTag=$("<td>").attr("colspan","2").append(innerTag, aTitleTag).append("   "+reply.blReplyDate);		
	return $("<tr>").append(
			thTag
			,tdTag
	);
}

function reply_tr(){
	$("#replyTbody > tr").mouseover(function(){
		//console.log("this:",this);
		this.style.color = "black";
		this.style.backgroundColor="#dcdcdc";
	});

	$("#replyTbody > tr").mouseout(function(){
		//console.log("this:",this);	
		this.style.color = "";
		this.style.backgroundColor="white";
	})
}
searchForm.on("submit",function(event){
	let data=searchForm.serialize();
	event.preventDefault();
	
	$.ajax({
		url : selectURL,
		data : data,
		method : 'GET',
		dataType : "json",
		success : function(resp, status, jqXHR) {
			console.log("ck:",resp.replyPagingVO);
			let key=Object.keys(resp);
			if(key=="loginurl"){
				location.href = loginURL;
				
			}else if(key=="checkurl"){
				location.href = checkURL;
			}else{
				let replyPagingVO = resp.replyPagingVO;
				console.log(replyPagingVO);
				let replyList = replyPagingVO.dataList;
				console.log("야야",replyList);
				let pagingHTMLBS = replyPagingVO.pagingHTMLBS;
				let replyTrTags = [];
				if(replyList&&replyList.length>0){
					$.each(replyList, function(index, reply){
						replyTrTags.push(makeReplyTr(index, reply));
					});
					replyPagingArea.html(pagingHTMLBS);
				}else{
					let replyTrTag = $("<tr>").html(
									$("<td>").attr("colspan", "4")
							 				.html("작성한 댓글이 없어요")
								);
					replyPagingArea.html("");
					replyTrTags.push(replyTrTag);
				}
				replyTbody.html(replyTrTags);
				$("#CheckAll").prop("checked", false); 
				reply_tr();
				replyCheck();

			}
		},
		error : function(jqXHR, status, error) {
			console.log(jqXHR);
			console.log(status);
			console.log(error);
		}
	});
	return false;
}).submit();

</script>

<script src="${cPath }/resources/js/blog/replyManage.js"></script>


