<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 8. 5.      이아인      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<style>
#delboard {
	margin-left: 800px;
}

#delBtn{
	float:  right;
}

#checkTd{
	border-top:none;
}
</style>
 <div class="card-body">
    <!-- Nav tabs -->
    <ul class="nav nav-tabs nav-tabs-custom nav-justified" role="tablist">
        <li class="nav-item">
            <a class="nav-link active" data-bs-toggle="tab" href="#freeBoardList" role="tab" aria-selected="true" id="FreeBoard">
                <span class="d-block d-sm-none"><i class="fas fa-home"></i></span>
                <span class="d-none d-sm-block">자유게시판</span> 
            </a>
        </li>
        <li class="nav-item" onClick="coopBoList()">
            <a class="nav-link" data-bs-toggle="tab" href="#coopBoardList" role="tab" aria-selected="false" id="coopBoard">
                <span class="d-block d-sm-none"><i class="far fa-user"></i></span>
                <span class="d-none d-sm-block" >협업 게시판</span> 
            </a>
        </li>
		<li class="nav-item" onClick="commList()">
			<a class="nav-link" data-bs-toggle="tab"href="#commentList" role="tab" aria-selected="false" id="comment">
				<span class="d-block d-sm-none"><i class="far fa-user"></i></span> <span
				class="d-none d-sm-block" >작성 댓글</span>
		</a></li>
         <li class="nav-item" onClick="qnaList()">
            <a class="nav-link" data-bs-toggle="tab" href="#qnaBoardList" role="tab" aria-selected="false" id="qnaBoard">
                <span class="d-block d-sm-none"><i class="far fa-user"></i></span>
                <span class="d-none d-sm-block" >문의한 글</span> 
            </a>
        </li>
    </ul>
    
     <!-- Tab panes -->
    <div class="tab-content p-3 text-muted">
        <div class="tab-pane active" id="freeBoardList" role="tabpanel">
			<table class="table">
				<thead>
					<tr>
						<th>게시판번호</th>
						<th>제목</th>
						<th>작성일</th>
						<th>조회수</th>
					</tr>
				</thead>
				<tbody id="freeTbody">

				</tbody>
				<tfoot>
					<tr>
						<td colspan="7" style="border-bottom: none;">
							<div class="d-flex justify-content-center" id="freePagingArea">
							</div>
						
						</td>
					</tr>
					<tr>
						<td id="checkTd" colspan="4"><input type="checkbox" class="form-check-input" id="freeCheckAll">&nbsp;전체선택
						<i class=" dripicons-trash" id="delBtn" onclick="freeDeleteClick()"></i>
<!-- 							<button class="btn btn-outline-primary waves-effect waves-light" id="delBtn" onclick="freeDeleteClick()">삭제</button> -->
						</td>
					</tr>
				</tfoot>
			</table>
        </div>
        <div class="tab-pane" id="coopBoardList" role="tabpanel">
			<table class="table ">
				<thead>
					<tr>
						<th>게시판번호</th>
						<th>제목</th>
						<th>작성일</th>
						<th>조회수</th>
					</tr>
				</thead>
				<tbody id="coopTbody">

				</tbody>
				<tfoot>
					<tr>
						<td colspan="7" style="border-bottom: none;">
							<div class="d-flex justify-content-center" id="coopPagingArea">
							
							</div>
						</td>
					</tr>
					<tr>
						<td id="checkTd" colspan="4"><input type="checkbox" class="form-check-input" id="coopCheckAll">&nbsp;전체선택
						<i class=" dripicons-trash" id="delBtn" onclick="coopDeleteClick()"></i>
<!-- 							<button class="btn btn-outline-primary waves-effect waves-light" id="delBtn"onclick="coopDeleteClick()">삭제</button> -->
						</td>
					</tr>
				</tfoot>
			</table>
        </div>
         <div class="tab-pane" id="commentList" role="tabpanel">
        	
        		<table class="table ">
				<thead>
					<tr>
						<th>댓글번호</th>
						<th>댓글</th>
						<th>작성일자</th>
					</tr>
				</thead>
				<tbody id="commentTbody">

				</tbody>
				<tfoot>
					<tr>
						<td colspan="7" style="border-bottom: none;">
							<div class="d-flex justify-content-center" id="commentPagingArea">
							
							</div>
						</td>
					</tr>
					<tr>
						<td id="checkTd" colspan="4"><input type="checkbox" class="form-check-input" id="reCheckAll">&nbsp;전체선택
						<i class=" dripicons-trash" id="delBtn" onclick="reDeleteClick()"></i>
<!-- 							<button class="btn btn-outline-primary waves-effect waves-light" id="delBtn"onclick="reDeleteClick()">삭제</button> -->
						</td>
					</tr>
				</tfoot>
			</table>
        </div>
         <div class="tab-pane" id="qnaBoardList" role="tabpanel">
        	
        		<table class="table">
				<thead>
					<tr>
						<th>게시판번호</th>
						<th>제목</th>
						<th>작성일</th>
						<th>답변상태</th>
					</tr>
				</thead>
				<tbody id="qnaTbody">

				</tbody>
				<tfoot>
					<tr>
						<td colspan="7" style="border-bottom: none;">
							<div class="d-flex justify-content-center" id="qnaPagingArea">
							
							</div>
						</td>
					</tr>
					<tr>
						<td id="checkTd" colspan="4"><input type="checkbox" class="form-check-input" id="qnaCheckAll">&nbsp;전체선택
							<i class=" dripicons-trash" id="delBtn" onclick="qnaDeleteClick()"></i>
							<!-- <button class="btn btn-outline-primary waves-effect waves-light" id="delBtn" onclick="qnaDeleteClick()">삭제</button> -->
						</td>
					</tr>
				</tfoot>
			</table>
        </div>
 </div> 
</div> 
<form action="${cPath }/member" id="searchForm" method="get">
	<input type="hidden" name="freePage" placeholder="freepage"/>	
	<input type="hidden" name="coopPage" placeholder="cooppage" />
	<input type="hidden" name="commPage" placeholder="commentpage" />
	<input type="hidden" name="qnaPage" placeholder="qnapage" />
</form>

<script>
//아작스 사용을 위한 토큰, 전역변수로 해 놓으면 편함(get방식일때는 x post방식일때 O)
var header = '${_csrf.headerName}';
var token =  '${_csrf.token}';
let delURL="${cPath}/member"; //삭제URL

let searchForm =$("#searchForm");
let url=searchForm.attr("action"); //조회URL
let method=searchForm.method;
let data;


//게시판 페이징 처리
let freeTbody=$('#freeTbody');
let freePagingArea=$('#freePagingArea');

freePagingArea.on("click","a",function(event){
	let freePage =$(this).data("page");
	searchForm.find("[name=freePage]").val(freePage);
	searchForm.submit();
});

let makeBoTr=function(index,free){
	let aTag=$("<a>").attr("href","${cPath}/freeBoard/"+free.freeNum)
													.text(free.freeTitle)
													
	let chTag=$("<input>").attr("type","checkbox");
	chTag.addClass("form-check-input");
	chTag.attr("id","free"+index);
	chTag.attr("name","check");
	chTag.attr("value",free.freeNum);
	let freeNum=$("<td>").html(chTag);
	freeNum.append('&nbsp;'+'&nbsp;'+free.freeNum);												
	return $("<tr>").append(
			freeNum
			//$("<td>").html(free.freeNum)
			,$("<td>").html(aTag)
			,$("<td>").html(free.freeDate)
			,$("<td>").html(free.viewNum)
		);
}
function free_tr(){
	$("#freeTbody > tr").mouseover(function(){
		//console.log("this:",this);
		this.style.color = "black";
		this.style.backgroundColor="#dcdcdc";
	});

	$("#freeTbody > tr").mouseout(function(){
		//console.log("this:",this);	
		this.style.color = "";
		this.style.backgroundColor="white";
	})
}

searchForm.on("submit",function(event){
	data=searchForm.serialize();
	event.preventDefault();
	
	$.ajax({
		url : url+"/freeBo",
		data : data,
		method : method,
		dataType : "json"
		,
		success : function(resp, status, jqXHR) {
			let freePagingVO = resp.freePagingVO;
			console.log(freePagingVO);
			let freeBoardList = freePagingVO.dataList;
			let pagingHTMLBS = freePagingVO.pagingHTMLBS;
			let freeTrTags = [];
			if(freeBoardList&&freeBoardList.length>0){
				$.each(freeBoardList, function(index, free){
					freeTrTags.push(makeBoTr(index, free));
				});
				freePagingArea.html(pagingHTMLBS);
			}else{
				let freeTrTag = $("<tr>").html(
								$("<td>").attr("colspan", "4")
										 .attr("style","text-align:center")
										 .html("작성한 게시글이 없어요")
							);
				freeTrTags.push(freeTrTag);
				freePagingArea.html("");
			}
			freeTbody.html(freeTrTags);
			$("#freeCheckAll").prop("checked", false); 
			free_tr();
			freeCheck();
		},  
		error : function(jqXHR, status, error) {
			console.log(jqXHR);
			console.log(status);
			console.log(error);
		}
	});
	return false;
}).submit();

//협업게시판조회
let coopTbody=$("#coopTbody");
let coopPagingArea=$("#coopPagingArea");

coopPagingArea.on("click","a",function(event){
	let coopPage=$(this).data("page");
	searchForm.find("[name=coopPage]").val(coopPage);
	coopBoList();
	});

let makeCoopTr =function(index,coop){
	let aTag=$("<a>").attr("href","${cPath}/cooboard/"+coop.cooNum)
											.text(coop.cooTitle);
	let chTag=$("<input>").attr("type","checkbox");
	chTag.addClass("form-check-input");
	chTag.attr("id","coop"+index);
	chTag.attr("name","chCoop");
	chTag.attr("value",coop.cooNum);
	let cooNum=$("<td>").html(chTag);
	cooNum.append('&nbsp;'+'&nbsp;'+coop.cooNum);
	return $("<tr>").append(
//		$("<td>").html(chTag)
			cooNum
//		,$("<td>").append(coop.cooNum)
		,$("<td>").html(aTag)
		,$("<td>").html(coop.cooDate)
		,$("<td>").html(coop.viewNum)
);
}

function coop_tr(){
	$("#coopTbody > tr").mouseover(function(){
		//console.log("this:",this);
		this.style.color = "black";
		this.style.backgroundColor="#dcdcdc";
	});

	$("#coopTbody > tr").mouseout(function(){
		//console.log("this:",this);	
		this.style.color = "";
		this.style.backgroundColor="white";
	})
}

function coopBoList(){
	data=searchForm.serialize();
	$.ajax({
		url : url+"/coopBo",
		data : data,
		method : method,
		dataType : "json"
		,
		success : function(resp, status, jqXHR) {
			let coopPagingVO = resp.coopPagingVO;
			console.log(coopPagingVO);
			let coopBoardList = coopPagingVO.dataList;
			let pagingHTMLBS = coopPagingVO.pagingHTMLBS;
			let coopTrTags = [];
			if(coopBoardList&&coopBoardList.length>0){
				$.each(coopBoardList, function(index, coop){
					coopTrTags.push(makeCoopTr(index, coop));
				});
				coopPagingArea.html(pagingHTMLBS);
			}else{
				let coopTrTag = $("<tr>").html(
								$("<td>").attr("colspan", "4")
										 .attr("style","text-align:center")
										 .html("작성한 게시글이 없어요")
							);
				coopTrTags.push(coopTrTag);
				coopPagingArea.html("");
			}
			coopTbody.html(coopTrTags);
			$("#coopCheckAll").prop("checked", false); 
			coop_tr();
			coopCheck();
		},  
		error : function(jqXHR, status, error) {
			console.log(jqXHR);
			console.log(status);
			console.log(error);
		}
	});
}

//댓글 조회
let commentTbody = $("#commentTbody");
let commentPagingArea =$("#commentPagingArea");

commentPagingArea.on("click","a",function(event){
	let commPage=$(this).data("page");
	searchForm.find("[name=commPage]").val(commPage);
	commList();
	});

let makeCommTr=function(index,comm){
	aTag=function(){
		if(comm.boardNum.substr(0, 4)==='FREE'){
			return $("<a>").attr("href","${cPath}/freeBoard/"+comm.boardNum)
			.text(comm.boReplyContent);
		}else if(comm.boardNum.substr(0, 4)==='COBO'){
			return $("<a>").attr("href","${cPath}/cooboard/"+comm.boardNum)
			.text(comm.boReplyContent);
		}else if(comm.boardNum.substr(0, 4)==='NOTI'){
			return $("<a>").attr("href","${cPath}/notice/"+comm.boardNum)
			.text(comm.boReplyContent);
		}
	}											 
												 
	let chTag=$("<input>").attr("type","checkbox");
	chTag.addClass("form-check-input");
	chTag.attr("id","comm"+index);
	chTag.attr("name","chRep");
	chTag.attr("value",comm.boReplyNum);
	let commNum=$("<td>").html(chTag);
	commNum.append('&nbsp;'+'&nbsp;'+comm.boReplyNum);												
	return $("<tr>").append(
			commNum	
			,$("<td>").html(aTag)
			,$("<td>").html(comm.boReplyDate)
		);
		
	
}
function comm_tr(){
	$("#commentTbody > tr").mouseover(function(){
		//console.log("this:",this);
		this.style.color = "black";
		this.style.backgroundColor="#dcdcdc";
	});

	$("#commentTbody > tr").mouseout(function(){
		//console.log("this:",this);	
		this.style.color = "";
		this.style.backgroundColor="white";
	})
}

function commList(){
	data=searchForm.serialize();
	$.ajax({
		url : url+"/reply",
		data : data,
		method : method,
		dataType : "json"
		,
		success : function(resp, status, jqXHR) {
			let commPagingVO = resp.commPagingVO;
			console.log(commPagingVO);
			let commentList = commPagingVO.dataList;
			let pagingHTMLBS = commPagingVO.pagingHTMLBS;
			let commTrTags = [];
			if(commentList&&commentList.length>0){
				$.each(commentList, function(index, comm){
					commTrTags.push(makeCommTr(index, comm));
				});
				commentPagingArea.html(pagingHTMLBS);
			}else{
				let commTrTag = $("<tr>").html(
								$("<td>").attr("colspan", "3")
										 .attr("style","text-align:center")
										 .html("작성한 댓글이 없어요")
							);
				commTrTags.push(commTrTag);
				commentPagingArea.html("");
			}
			commentTbody.html(commTrTags);
			$("#reCheckAll").prop("checked", false);
			comm_tr();
			replyCheck();
		},  
		error : function(jqXHR, status, error) {
			console.log(jqXHR);
			console.log(status);
			console.log(error);
		}
	});
}
//문의게시판 조회
let qnaTbody =$("#qnaTbody");
let qnaPagingArea=$("#qnaPagingArea");

qnaPagingArea.on("click","a",function(event){
	let qnaPage=$(this).data("page");
	searchForm.find("[name= qnaPage]").val(qnaPage);
	qnaList();
	});

let makeQnaTr =function(index,qna){
	let aTag=$("<a>").attr("href","${cPath}/qna/"+qna.qnaNum)
											.text(qna.qnaTitle);
	let chTag=$("<input>").attr("type","checkbox");
	chTag.addClass("form-check-input");
	chTag.attr("id","qna"+index);
	chTag.attr("name","chQna");
	chTag.attr("value",qna.qnaNum);
	let qnaNum=$("<td>").html(chTag);
	qnaNum.append('&nbsp;'+'&nbsp;'+qna.qnaNum);
	
	let answer=function(){
		if(!qna.answerId){
			return "답변대기"
		}else{
			return "답변완료"
		}
	}
	return $("<tr>").append(
			qnaNum
		,$("<td>").html(aTag)
		,$("<td>").html(qna.qnaDate)
		,$("<td>").html(answer) //답변상태
);
}
function qna_tr(){
	$("#qnaTbody > tr").mouseover(function(){
		//console.log("this:",this);
		this.style.color = "black";
		this.style.backgroundColor="#dcdcdc";
	});

	$("#qnaTbody > tr").mouseout(function(){
		//console.log("this:",this);	
		this.style.color = "";
		this.style.backgroundColor="white";
	})
}

function qnaList(){
	data=searchForm.serialize();
	$.ajax({
		url : url+"/qna",
		data : data,
		method : method,
		dataType : "json"
		,
		success : function(resp, status, jqXHR) {
			let qnaPagingVO = resp.qnaPagingVO;
			console.log(qnaPagingVO);
			let qnaList = qnaPagingVO.dataList;
			let pagingHTMLBS = qnaPagingVO.pagingHTMLBS;
			let qnaTrTags = [];
			if(qnaList&&qnaList.length>0){
				$.each(qnaList, function(index, qna){
					qnaTrTags.push(makeQnaTr(index, qna));
				});
				qnaPagingArea.html(pagingHTMLBS);	
			}else{
				let qnaTrTag = $("<tr>").html(
								$("<td>").attr("colspan", "4")
								         .attr("style","text-align:center")
										 .html("작성한 게시글이 없어요")
							);
				qnaPagingArea.html("");
				qnaTrTags.push(qnaTrTag);
			}
			qnaTbody.html(qnaTrTags);
			$("#qnaCheckAll").prop("checked", false); 
			qna_tr();
			qnaCheck();
		},  
		error : function(jqXHR, status, error) {
			console.log(jqXHR);
			console.log(status);
			console.log(error);
		}
	});
}


</script>
<script src="${cPath }/resources/js/member/memComm.js"></script>