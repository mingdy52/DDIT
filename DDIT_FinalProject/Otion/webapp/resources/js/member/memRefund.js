/**
 * <pre>
 * 
 * </pre>
 * @author 작성자명
 * @since 2022. 8. 31.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2022. 8. 31.     이아인       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
 let pageForm=$("#pageForm");
 let url=pageForm.attr("action");
 let method =pageForm.method;
 let data;

 $("#expertRef").on("click",function(){
		$("#search").data("name", "ex");
		pageForm.submit();
	 });
//날짜 검색
 $(function() {
      //input을 datepicker로 선언
      $("#startDate").datepicker({
          dateFormat: 'yy-mm-dd' //달력 날짜 형태
          ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
          ,showMonthAfterYear:true // 월- 년 순서가아닌 년도 - 월 순서
          ,changeYear: true //option값 년 선택 가능
          ,changeMonth: true //option값  월 선택 가능                
          ,showOn: "both" //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시  
          ,buttonImage: dateImgURL
          ,buttonImageOnly: true //버튼 이미지만 깔끔하게 보이게함
          ,buttonText: "선택" //버튼 호버 텍스트              
          ,yearSuffix: "년" //달력의 년도 부분 뒤 텍스트
          ,monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 텍스트
          ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip
          ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 텍스트
          ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 Tooltip
          ,minDate: "-5Y" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
          ,maxDate: "+5y" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)  
      });                    
      
     /* 초기값을 오늘 날짜로 설정해줘야 합니다.
      $('#startDate').datepicker('setDate', 'today'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)   */
      
      //==============================
   	    $("#endDate").datepicker({
          dateFormat: 'yy-mm-dd' //달력 날짜 형태
          ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
          ,showMonthAfterYear:true // 월- 년 순서가아닌 년도 - 월 순서
          ,changeYear: true //option값 년 선택 가능
          ,changeMonth: true //option값  월 선택 가능                
          ,showOn: "both" //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시  
          ,buttonImage: dateImgURL
          ,buttonImageOnly: true //버튼 이미지만 깔끔하게 보이게함
          ,buttonText: "선택" //버튼 호버 텍스트              
          ,yearSuffix: "년" //달력의 년도 부분 뒤 텍스트
          ,monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 텍스트
          ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip
          ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 텍스트
          ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 Tooltip
          ,minDate: "-5Y" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
          ,maxDate: "+5y" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)  
      });                    
      
     
  });
 //expertRef 페이징처리
 let expTbody = $("#expTbody");
 let expPagingArea = $("#expPagingArea");

 expPagingArea.on("click", "a", function(event){
 		let exPage = $(this).data("page");
 		console.log(exPage);
 		pageForm.find("[name=exPage]").val(exPage);
 		pageForm.submit();
 });

let makeExpTr =function(index,exp){
	 return $("<tr>").append(
		$("<td>").html(exp.rnum)
		, $("<td>").html(exp.refundNum)
		, $("<td>").html(exp.eprodName)
		, $("<td>").html(exp.eprodPrice+"원")
		, $("<td>").html(exp.refundDate)
		, $("<td>").html(exp.comCodeNm)
		, $("<td>").html(exp.ecomNm)
	 );
 }
function exp_tr(){
	$("#expTbody > tr").mouseover(function(){
		//console.log("this:",this);
		this.style.color = "black";
		this.style.backgroundColor="#dcdcdc";
	});

	$("#expTbody > tr").mouseout(function(){
		//console.log("this:",this);	
		this.style.color = "";
		this.style.backgroundColor="white";
	})
}

pageForm.on("submit", function(event){
	data=pageForm.serialize();
	event.preventDefault(); //페이징 누를때 넘어가기전에 ajax가져와야 하니까
	
	$.ajax({
		url : url+"/expRefund",
		data : data,
		method : method,
		dataType : "json"
		,
		success : function(resp, status, jqXHR) {
			let expRefPagingVO = resp.expRefPagingVO;
			console.log(expRefPagingVO);
			let expRefList = expRefPagingVO.dataList;
			let pagingHTMLBS = expRefPagingVO.pagingHTMLBS;
			let expTrTags = [];
			if(expRefList&&expRefList.length>0){
				$.each(expRefList, function(index, exp){
					expTrTags.push(makeExpTr(index, exp));
				});
				expPagingArea.html(pagingHTMLBS);
			}else{
				let expTrTag = $("<tr>").html(
								$("<td>").attr("colspan", "7")
								         .attr("style","text-align:center")
										 .html("환불 내역 없음")
							);
				expTrTags.push(expTrTag);
				expPagingArea.html("");
			}
			expTbody.html(expTrTags);
			exp_tr();
		},  
		error : function(jqXHR, status, error) {
			console.log(jqXHR);
			console.log(status);
			console.log(error);
		}
	});
	return false;
}).submit();

//날짜 검색
$("#search").on("click",function(event){
	   event.preventDefault();
	   if($("#search").data("name") == "ex"){
		   let startDate=document.getElementById('startDate').value;
		   let endDate=document.getElementById('endDate').value;
		   if(startDate==''|| endDate==''){
			   swal("날짜를 입력해주세요","")
			   return false;
		   }else if(startDate>endDate){
			   swal("검색하실 날짜를 다시 확인해주세요","")
			   return false;
		   }else{
			   pageForm.find("[name=startDate]").val(startDate);
			   pageForm.find("[name=endDate]").val(endDate);
			   pageForm.find("[name=coopPage]").val(1);
			   pageForm.find("[name=exPage]").val(1);
			   pageForm.submit();
		   }		
		   
	   }else{
		   let startDate=document.getElementById('startDate').value;
		   let endDate=document.getElementById('endDate').value;
		   if(startDate==''|| endDate==''){
			   swal("날짜를 입력해주세요","")
			   return false;
		   }else if(startDate>endDate){
			   swal("검색하실 날짜를 다시 확인해주세요","")
			   return false;
		   }else{
			   pageForm.find("[name=startDate]").val(startDate);
			   pageForm.find("[name=endDate]").val(endDate);
			   pageForm.find("[name=coopPage]").val(1);
			   pageForm.find("[name=exPage]").val(1);
			   coopRefList()
		   }		
	   }
});	  

//협업툴 환불내역
let coopTbody = $("#coopTbody");
let coopPagingArea =$("#coopPagingArea");

coopPagingArea.on("click","a",function(event){
		let coopPage=$(this).data("page");
		pageForm.find("[name=coopPage]").val(coopPage);
		coopRefList();
	});
	
let makeCoopTr =function(index,coop){
	 return $("<tr>").append(
		$("<td>").html(coop.rnum)
		, $("<td>").html(coop.wpayNum)
		, $("<td>").html(coop.cprodName)
		, $("<td>").html(coop.wpayAmount+"원")
		, $("<td>").html(coop.workRefDate)
		, $("<td>").html(coop.comCodeNm)
		, $("<td>").html(coop.wcomNm)

		
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
 
function coopRefList(){
	 $("#search").data("name", "coop");
	 data = pageForm.serialize();
	 
	 $.ajax({
			url : url+"/coopRefund",
			data : data,
			method : method,
			dataType : "json"
			,
			success : function(resp, status, jqXHR) {
				let coopRefPagingVO = resp.coopRefPagingVO;
				console.log(coopRefPagingVO);
				let coopRefList = coopRefPagingVO.dataList;
				let pagingHTMLBS = coopRefPagingVO.pagingHTMLBS;
				let coopTrTags = [];
				if(coopRefList&&coopRefList.length>0){
					$.each(coopRefList, function(index, coop){
						coopTrTags.push(makeCoopTr(index, coop));
					});
					coopPagingArea.html(pagingHTMLBS);
				}else{
					let coopTrTag = $("<tr>").html(
									$("<td>").attr("colspan", "7")
											 .attr("style","text-align:center")
											 .html("환불 내역 없음")
								);
					coopTrTags.push(coopTrTag);
					coopPagingArea.html("");
				}
				coopTbody.html(coopTrTags);
				coop_tr();
			},  
			error : function(jqXHR, status, error) {
				console.log(jqXHR);
				console.log(status);
				console.log(error);
			}
		});
		
 }
//reset 데이터 초기화
 $("#reset").on("click",function(){
 	$("#pageForm")[0].reset();
 	 location.reload(true);
 });