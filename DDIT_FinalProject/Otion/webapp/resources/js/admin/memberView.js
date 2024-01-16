/**
 * <pre>
 * 
 * </pre>
 * @author 심민경
 * @since 2022. 8. 10.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2022. 8. 10.      작성자명       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */ 



// 전문가 상품 / 협업툴 사용 결제 내역 페이징
let expPagingArea = document.querySelector(".expPagingArea");
let workPagingArea = document.querySelector(".workPagingArea");
expPagingArea.addEventListener('click', function(event){
	let page = event.target.innerHTML;
	expPayPage.value = page;
	getPayList();
});

workPagingArea.addEventListener('click', function(event){
	let page = event.target.innerHTML;
	workPayPage.value = page;
	getPayList();
});

// 전문가 상품 결제 내역 테이블 생성 함수
let makeExpTr = function(index, exp){
	return $("<tr>").append(
			$("<td>").html(exp.epayNum)
			, $("<td>").html(exp.eprodName)
			, $("<td>").html(exp.amount)
			, $("<td>").html(exp.ecomNm)
			, $("<td>").html(exp.epayYn)
			, $("<td>").html(exp.epayDate)
	);
}

//협업툴 상품 결제 내역 테이블 생성 함수
let makeWorkTr = function(index, work){
	return $("<tr>").append(
			$("<td>").html(work.wpayNum)
			, $("<td>").html(work.cprodName)
			, $("<td>").html(work.wpayAmount)
			, $("<td>").html(work.wcomNm)
			, $("<td>").html(work.wpayYn)
			, $("<td>").html(work.wpayDate)
			, $("<td>").html(work.wpayEnd)
	);
}

// 전문가 상품, 협업툴 결제 내역 목록
function getPayList(){
	let expTbody = $("#expTbody");
	let workTbody = $("#workTbody");
	
	$.ajax({
		url : url+"/payment"
		, data : {
			"expPayPage":expPayPage.value
			, "workPayPage":workPayPage.value
		}
		, method : "get"
		, dataType : "json"
		, success : function(resp, status, jqXHR) {
			let expPagingVO = resp.expPagingVO;
			let expList = expPagingVO.dataList;
			let pagingHTMLBS = expPagingVO.pagingHTMLBS;
			let exptrTags = [];
			
			if(expList&&expList.length>0){
				$.each(expList, function(index, exp){
					exptrTags.push(makeExpTr(index, exp));
				});
				expPagingArea.innerHTML=pagingHTMLBS;
			}else{
				let exptrTag = $("<tr>").html(
								$("<td>").attr("colspan", "6")
										 .html("결제 내역 없음.")
							);
				exptrTags.push(exptrTag);
			}
			expTbody.html(exptrTags);
			
			//
			let workPagingVO = resp.workPagingVO;
			let workList = workPagingVO.dataList;
			let workPagingHTMLBS = workPagingVO.pagingHTMLBS;
			let workTrTags = [];
			
			if(workList&&workList.length>0){
				$.each(workList, function(index, work){
					workTrTags.push(makeWorkTr(index, work));
				});
				workPagingArea.innerHTML=workPagingHTMLBS;
			}else{
				let workTrTag = $("<tr>").html(
								$("<td>").attr("colspan", "8")
										 .html("결제 내역 없음.")
							);
				workTrTags.push(workTrTag);
			}
			workTbody.html(workTrTags);
			
		},  
		error : function(jqXHR, status, error) {
			console.log(jqXHR);
			console.log(status);
			console.log(error);
		}
	});
}

// 상품 환불 내역 페이징
let expRefPagingArea = document.querySelector(".expRefPagingArea");
let workRefPagingArea = document.querySelector(".workRefPagingArea");
expRefPagingArea.addEventListener('click', function(event){
	let page = event.target.innerHTML;
	expRefPage.value = page;
	getRefundList();
});

workRefPagingArea.addEventListener('click', function(event){
	let page = event.target.innerHTML;
	workRefPage.value = page;
	getRefundList();
});

//상품 환불 내역 테이블 생성 함수
let makeExpRefTr = function(index, expRef){
	return $("<tr>").append(
			$("<td>").html(expRef.refundNum)
			, $("<td>").html(expRef.eprodNum)
			, $("<td>").html(expRef.eprodName)
			, $("<td>").html(expRef.ecomNm)
			, $("<td>").html(expRef.refundDate)
	);
}
		
let makeWorkRefTr = function(index, workRef){
	return $("<tr>").append(
			$("<td>").html(workRef.wpayNum)
			, $("<td>").html(workRef.cprodNum)
			, $("<td>").html(workRef.cprodName)
			, $("<td>").html(workRef.wcomNm)
			, $("<td>").html(workRef.workRefDate)
	);
}

// 상품 환불 목록 가져오기
function getRefundList(){
	let expRefTbody = $("#expRefTbody");
	let wrokRefTbody = $("#wrokRefTbody");
	
	$.ajax({
		url : url+"/refund"
		, data : {
			"expRefPage":expRefPage.value
			, "workRefPage":workRefPage.value
		}
		, method : "get"
		, dataType : "json"
		, success : function(resp, status, jqXHR) {
			let expRefPagingVO = resp.expRefPagingVO;
			let expRefList = expRefPagingVO.dataList;
			let expRefPagingHTMLBS = expRefPagingVO.pagingHTMLBS;
			let expRefTrTags = [];
			
			if(expRefList&&expRefList.length>0){
				$.each(expRefList, function(index, expRef){
					expRefTrTags.push(makeExpRefTr(index, expRef));
				});
				expRefPagingArea.innerHTML=expRefPagingHTMLBS;
			}else{
				let expRefTrTag = $("<tr>").html(
								$("<td>").attr("colspan", "6")
										 .html("환불 내역 없음.")
							);
				expRefTrTags.push(expRefTrTag);
			}
			expRefTbody.html(expRefTrTags);
			
			//
			
			let workRefPagingVO = resp.workRefPagingVO;
			let workRefList = workRefPagingVO.dataList;
			let workRefPagingHTMLBS = workRefPagingVO.pagingHTMLBS;
			let workRefTrTags = [];
			
			if(workRefList&&workRefList.length>0){
				$.each(workRefList, function(index, workRef){
					workRefTrTags.push(makeWorkRefTr(index, workRef));
				});
				workRefPagingArea.innerHTML=workRefPagingHTMLBS;
			}else{
				let workRefTrTag = $("<tr>").html(
								$("<td>").attr("colspan", "6")
										 .html("환불 내역 없음.")
							);
				workRefTrTags.push(workRefTrTag);
			}
			wrokRefTbody.html(workRefTrTags);
			
		},  
		error : function(jqXHR, status, error) {
			console.log(jqXHR);
			console.log(status);
			console.log(error);
		}
	});
}

// 신고 목록 페이징
let repPagingArea = document.querySelector(".repPagingArea");
repPagingArea.addEventListener('click', function(event){
	let page = event.target.innerHTML;
	repPage.value = page;
	getReportList();
});
	
// 신고 목록 테이블 생성 함수
let makeRepTr = function(index, report){
	return $("<tr>").append(
			$("<td>").html(report.repNum)
			, $("<td>").html(report.reporterId)
			, $("<td>").html(report.repDate)
	);
}

// 신고 목록 가져오기
function getReportList(){
	let repbody = $("#repbody");
		
		$.ajax({
			url : url+"/report"
			, data : {
				"repPage":repPage.value
			}
			, method : "get"
			, dataType : "json"
			, success : function(resp, status, jqXHR) {
				let pagingVO = resp;
				let repList = pagingVO.dataList;
				let pagingHTMLBS = pagingVO.pagingHTMLBS;
				let trTags = [];
				if(repList&&repList.length>0){
					$.each(repList, function(index, report){
						trTags.push(makeRepTr(index, report));
					});
					
					repPagingArea.innerHTML=pagingHTMLBS;
					
				}else{
					let trTag = $("<tr>").html(
									$("<td>").attr("colspan", "5")
											 .html("신고 내역 없음.")
								);
					trTags.push(trTag);
				}
				repbody.html(trTags);
			},  
			error : function(jqXHR, status, error) {
				console.log(jqXHR);
				console.log(status);
				console.log(error);
			}
		});
}
 