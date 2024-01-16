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
 * 2022. 8. 31.      이아인       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */ 
let pageForm=$("#pageForm");
 let url=pageForm.attr("action");
 let method =pageForm.method;
 let data;

 $("#expertPay").on("click",function(){
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
           //,buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif" //버튼 이미지 경로
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
           //,buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif" //버튼 이미지 경로
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
   

 //expertPay 페이징처리
 let expTbody = $("#expTbody");
 let expPagingArea = $("#expPagingArea");

 expPagingArea.on("click", "a", function(event){
 		let exPage = $(this).data("page");
 		console.log(exPage);
 		pageForm.find("[name=exPage]").val(exPage);
 		pageForm.submit();
 });

 
 
let makeExpTr =function(index,exp){
	let aTag=$("<a>").attr("href",expertPath+"/"+exp.myEprod).text(exp.eprodName);
	
	 return $("<tr>").append(
		$("<td>").html(exp.rnum)
		, $("<td>").html(exp.epayNum)
		, $("<td>").html(aTag)
		, $("<td>").html(exp.epayDate)
		, $("<td>").html(exp.amount+"원")
		, $("<td>").html(exp.ecomNm)
		, $("<td>").html(exp.epayYn)
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
		url : url+"/memPay",
		data : data,
		method : method,
		dataType : "json"
		,
		success : function(resp, status, jqXHR) {
			let expPagingVO = resp.expPagingVO;
			console.log(expPagingVO);
			let exPayList = expPagingVO.dataList;
			let pagingHTMLBS = expPagingVO.pagingHTMLBS;
			let expTrTags = [];
			if(exPayList&&exPayList.length>0){
				$.each(exPayList, function(index, exp){
					expTrTags.push(makeExpTr(index, exp));
				});
				expPagingArea.html(pagingHTMLBS);
				
			}else{
				let expTrTag = $("<tr>").html(
								$("<td>").attr("colspan", "9")
										 .attr("style","text-align:center")
										 .html("결제 내역 없음")
							);
				expPagingArea.html("");
				expTrTags.push(expTrTag);
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
			   coopPayList();
		   }		
	   }
});



//협업툴 결제내역
let coopTbody = $("#coopTbody");
let coopPagingArea =$("#coopPagingArea");

coopPagingArea.on("click","a",function(event){
		let coopPage=$(this).data("page");
		pageForm.find("[name=coopPage]").val(coopPage);
		coopPayList();
	});

let makeCoopTr =function(index,coop){
	let cooATag=$("<a>").attr("href",coopPath+"/"+coop.pjId+"/home").text(coop.pjName);
	
	 return $("<tr>").append(
		$("<td>").html(coop.rnum)
		, $("<td>").html(coop.wpayNum)
		, $("<td>").html(coop.cprodName)
		, $("<td nowrap>").html(cooATag)
		, $("<td nowrap>").html(coop.wpayAmount+"원")
		, $("<td nowrap>").html(coop.wpayDate)
		, $("<td nowrap>").html(coop.wpayEnd)
		, $("<td>").html(coop.wcomNm)
		, $("<td>").html(coop.wpayYn)
		
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
 
 function coopPayList(){
	 $("#search").data("name", "coop");
	 data = pageForm.serialize();
	 
	 $.ajax({
			url : url+"/coopPay",
			data : data,
			method : method,
			dataType : "json"
			,
			success : function(resp, status, jqXHR) {
				let coopPagingVO = resp.coopPagingVO;
				console.log(coopPagingVO);
				let coopPayList = coopPagingVO.dataList;
				let pagingHTMLBS = coopPagingVO.pagingHTMLBS;
				let coopTrTags = [];
				if(coopPayList&&coopPayList.length>0){
					$.each(coopPayList, function(index, coop){
						coopTrTags.push(makeCoopTr(index, coop));
					});
					coopPagingArea.html(pagingHTMLBS);
				}else{
					let coopTrTag = $("<tr>").html(
									$("<td>").attr("colspan", "9")
											 .attr("style","text-align:center")
											 .html("결제 내역 없음")
								);
					coopPagingArea.html("");
					coopTrTags.push(coopTrTag);
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
	
	
	