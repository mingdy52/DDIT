/**
 * <pre>
 * 
 * </pre>
 * @author 심민경
 * @since 2022. 8. 31.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2022. 8. 31. 심민경       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */ 

let VIEWURL = $.CPATH+"/admin/report/target";
const url = $.CPATH+"/admin/report";

// 회원 신고 리스트 페이징	
let memPagingArea = document.querySelector(".memPagingArea");
memPagingArea.addEventListener('click', function(event){
	let page = event.target.innerHTML;
	memPage.value = page;
	getMemberList();
});



// 회원 신고 리스트 테이블 만들기 함수
function makeMemTr(index, mem, aTag){
	return $("<tr>").append(
				$("<td>").html(mem.rnum)
				, $("<td>").html(aTag)
				, $("<td>").html(mem.memName)
				, $("<td>").html(mem.memNick)
				, $("<td>").html(mem.memMail)
				, $("<td>").html(mem.accumRep)
			);
}

// 회원 신고 리스트 가져오기
(getMemberList = function(event){
	let memTbody = $("#memTbody");
	
	$.ajax({
		url : url + "/member"
		, data : {
			"memPage":memPage.value
		}
		, method : "get"
		, dataType : "json"
		, success : function(resp, status, jqXHR) {
			let memList = resp.dataList;
			let pagingHTMLBS = resp.pagingHTMLBS;
			let trTags = [];
			
			if(memList&&memList.length>0){
				$.each(memList, function(index, mem){
					let aTag = $("<a>").attr("href", VIEWURL.replace("target", "member/"+mem.memId))
					   .html(mem.memId);
					trTags.push(makeMemTr(index, mem, aTag));
				});
				memPagingArea.innerHTML=pagingHTMLBS;
			}else{
				let trTag = $("<tr>").html(
								$("<td>").attr("colspan", "5")
										 .html("신고내역이 없습니다.")
							);
				trTags.push(trTag);
			}
			
			memTbody.html(trTags);
			
		},  
		error : function(jqXHR, status, error) {
			console.log(jqXHR);
			console.log(status);
			console.log(error);
		}
	});
})();

//전문가 회원 신고 리스트 페이징	
let expPagingArea = document.querySelector(".expPagingArea");
expPagingArea.addEventListener('click', function(event){
	let page = event.target.innerHTML;
	exPage.value = page;
	getExpList();
});

//전문가 회원 신고 리스트 가져오기
function getExpList(){
	let expTbody = $("#expTbody");
	
	$.ajax({
		url : url + "/exp",
		data : {
			"exPage":exPage.value
		}, 
		method : "get",
		dataType : "json"
		,
		success : function(resp, status, jqXHR) {
			let expList = resp.dataList;
			let pagingHTMLBS = resp.pagingHTMLBS;
			let trTags = [];
			if(expList&&expList.length>0){
				$.each(expList, function(index, mem){
					let aTag = $("<a>").attr("href", VIEWURL.replace("target", "expert/"+mem.memId))
					   .html(mem.memId);
					trTags.push(makeMemTr(index, mem, aTag));
				});
				expPagingArea.innerHTML=pagingHTMLBS;
			}else{
				let trTag = $("<tr>").html(
								$("<td>").attr("colspan", "6")
										 .html("신고내역이 없습니다.")
							);
				trTags.push(trTag);
			}
			expTbody.html(trTags);
		},  
		error : function(jqXHR, status, error) {
			console.log(jqXHR);
			console.log(status);
			console.log(error);
		}
	});
}

//협업게시판 신고 리스트 페이징	
let cooPagingArea = document.querySelector(".cooPagingArea");
cooPagingArea.addEventListener('click', function(event){
	let page = event.target.innerHTML;
	cooPage.value = page;
	getCooList();
});

//협업게시판 신고 리스트 테이블 만들기 함수
let makeCooTr = function(index, coo){
  	let aTag = $("<a>").attr("href", VIEWURL.replace("target", "coo/"+coo.cooNum))
  					   .html(coo.cooNum);
	return $("<tr>").append(
				$("<td>").html(coo.rnum)
				, $("<td>").html(aTag)
				, $("<td>").html(coo.cooTitle)
				, $("<td>").html(coo.writerId)
				, $("<td>").html(coo.cooDate)
				, $("<td>").html(coo.repNum)
			);
}

//협업게시판 신고 리스트 가져오기
function getCooList(){
	let cooTbody = $("#cooTbody");
	
	$.ajax({
		url : url + "/coo",
		data : {
			"cooPage":cooPage.value
		}, 
		method : "get",
		dataType : "json"
		,
		success : function(resp, status, jqXHR) {
			let cooList = resp.dataList;
			let pagingHTMLBS = resp.pagingHTMLBS;
			let trTags = [];
			if(cooList&&cooList.length>0){
				$.each(cooList, function(index, coo){
					trTags.push(makeCooTr(index, coo));
				});
				cooPagingArea.innerHTML=pagingHTMLBS;
			}else{
				let trTag = $("<tr>").html(
								$("<td>").attr("colspan", "6")
										 .html("신고내역이 없습니다.")
							);
				trTags.push(trTag);
			}
			cooTbody.html(trTags);
		},  
		error : function(jqXHR, status, error) {
			console.log(jqXHR);
			console.log(status);
			console.log(error);
		}
	});
}

//자유게시판 신고 리스트 페이징	
let freePagingArea = document.querySelector(".freePagingArea");
freePagingArea.addEventListener('click', function(event){
	let page = event.target.innerHTML;
	freePage.value = page;
	getfreeList();
});

//자유게시판 신고 리스트 테이블 만들기 함수
function makeFreeTr(index, free){
  	let aTag = $("<a>").attr("href", VIEWURL.replace("target", "free/"+free.freeNum))
  					   .html(free.freeNum);
	return $("<tr>").append(
				$("<td>").html(free.rnum)
				, $("<td>").html(aTag)
				, $("<td>").html(free.freeTitle)
				, $("<td>").html(free.writerId)
				, $("<td>").html(free.freeDate)
				, $("<td>").html(free.repNum)
			);
}

//자유게시판 신고 리스트 가져오기
function getfreeList(){
	let freeTbody = $("#freeTbody");
	
	$.ajax({
		url : url + "/free",
		data : {
			"freePage":freePage.value
		}, 
		method : "get",
		dataType : "json"
		,
		success : function(resp, status, jqXHR) {
			let freeList = resp.dataList;
			let pagingHTMLBS = resp.pagingHTMLBS;
			let trTags = [];
			if(freeList&&freeList.length>0){
				$.each(freeList, function(index, free){
					trTags.push(makeFreeTr(index, free));
				});
				freePagingArea.innerHTML=pagingHTMLBS;
				
			}else{
				let trTag = $("<tr>").html(
								$("<td>").attr("colspan", "6")
										 .html("신고내역이 없습니다.")
							);
				trTags.push(trTag);
			}
			freeTbody.html(trTags);
		},  
		error : function(jqXHR, status, error) {
			console.log(jqXHR);
			console.log(status);
			console.log(error);
		}
	});
}