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
//자유게시판 눌렀을때 체크버튼 초기화
$("#FreeBoard").on("click",function(){
	$("input[name='check']").prop("checked", false);
	$("#freeCheckAll").prop("checked", false);
			
 });
//자유게시판 전체선택 
const freeCheck = function(){
     //전체 체크박스 클릭
     $("#freeCheckAll").click(function() {
         if ($("#freeCheckAll").prop("checked")) {
             $("input[name='check']").prop("checked", true);
         } else {
             $("input[name='check']").prop("checked", false);
         }
     });
     //전체 체크박스 선택중 체크박스 하나를 풀었을때 "전체" 체크해제
     $("input[name='check']").click(function() {
     	var total = $("input[name=check]").length;
 		var checked = $("input[name=check]:checked").length;
 		/*console.log("total",total);
 		console.log("checked",checked);*/
 		if(total != checked) $("#freeCheckAll").prop("checked", false);
 		else $("#freeCheckAll").prop("checked", true); 
     });	
 }
//자유게시판 삭제 처리
function freeDeleteClick(){
 //삭제 확인창
 swal({
		  title: "삭제하시겠습니까?",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
		})
		.then(function (willDelete){
		  if (willDelete) {
		   //체크박스 선택했는지 안했는지
		   var totalCheck = $("input:checked[Name='check']").is(":checked")
		   if(!totalCheck){
			   swal("삭제 할 게시글을 선택해주세요","");
		  	 return false;
		   }else{
			  var freeBoxArr = []; 
			  $("input:checkbox[name='check']:checked").each(function() {
			  freeBoxArr.push($(this).val());     // 체크된 것만 값을 뽑아서 배열에 push
			  console.log(freeBoxArr);
			})
	
				$.ajax({
				    method : 'POST',
				    url: delURL+"/freeDel",
				    data: {
				    	freeBoxArr : freeBoxArr   
				    },
				    dataType : "json",
				  	//security적용된 상태에서 ajax 요청시 필수
			        beforeSend: function (xhr) {
			            xhr.setRequestHeader(header, token);
			        },
				    success: function(result){
				    	swal(result.msg,""); 
				    	//location.reload(true);
				    	searchForm.submit();
				
				    },
				    error: function(xhr, status, error) {
				    	console.log(error);
				    }  
				 });
      			}
		  }else{
			  return false;
		  }
       });
}
//협업게시판 전체선택 
const coopCheck = function(){
           //전체 체크박스 클릭
           $("#coopCheckAll").click(function() {
               if ($("#coopCheckAll").prop("checked")) {
                   $("input[name='chCoop']").prop("checked", true);
               } else {
                   $("input[name='chCoop']").prop("checked", false);
               }
           });
           //전체 체크박스 선택중 체크박스 하나를 풀었을때 "전체" 체크해제
           $("input[name='chCoop']").click(function() {
           	var total2 = $("input[name=chCoop]").length;
       		var checked2 = $("input[name=chCoop]:checked").length;

       		if(total2 != checked2) $("#coopCheckAll").prop("checked", false);
       		else $("#coopCheckAll").prop("checked", true); 
           });
          
}    
//협업게시판 삭제 처리
function coopDeleteClick(){
	//삭제 확인창
	 swal({
		  title: "삭제하시겠습니까?",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
		})
		.then(function (willDelete){
		  if (willDelete) {
			   //체크박스 선택했는지 안했는지
			   var totalCheck = $("input:checked[Name='chCoop']").is(":checked")
			   if(!totalCheck){
				 swal("삭제 할 게시글을 선택해주세요","");
			  	 return false;
			   }else{
				   var coopBoxArr = []; 
				   $("input:checkbox[name='chCoop']:checked").each(function() {
				   coopBoxArr.push($(this).val());     // 체크된 것만 값을 뽑아서 배열에 push
				   console.log(coopBoxArr);
				  })
	
				  $.ajax({
					   method : 'POST',
					   url: delURL+'/coopDel',
					   data: {
						   coopBoxArr : coopBoxArr    
					   },
					   dataType : "json",
					   beforeSend: function (xhr) {
				            xhr.setRequestHeader(header, token);
				        },
					   success: function(result){
							swal(result.msg,"");
							coopBoList();
					    	
					   },
					   error: function(xhr, status, error) {
					   	console.log(error);
					   }  
					});
		      }
		}else{
			 return false;
		}
	});
}
//댓글 전체선택 
const replyCheck=function(){
           //전체 체크박스 클릭
           $("#reCheckAll").click(function() {
               if ($("#reCheckAll").prop("checked")) {
                   $("input[name='chRep']").prop("checked", true);
               } else {
                   $("input[name='chRep']").prop("checked", false);
               }
           });
           //전체 체크박스 선택중 체크박스 하나를 풀었을때 "전체" 체크해제
           $("input[name='chRep']").click(function() {
           	var total3 = $("input[name=chRep]").length;
       		var checked3 = $("input[name=chRep]:checked").length;
       		if(total3 != checked3) $("#reCheckAll").prop("checked", false);
       		else $("#reCheckAll").prop("checked", true); 
           });
          
}   
//댓글 삭제 처리
function reDeleteClick(){
	//삭제 확인창
	 swal({
		  title: "삭제하시겠습니까?",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
		})
		.then(function (willDelete){
		  if (willDelete) { 
		   //체크박스 선택했는지 안했는지
		   var totalCheck = $("input:checked[Name='chRep']").is(":checked")
		   if(!totalCheck){
			 swal("삭제 할 게시글을 선택해주세요","");
		  	 return false;
		   }else{
			   var reBoxArr = []; 
			   $("input:checkbox[name='chRep']:checked").each(function() {
			   reBoxArr.push($(this).val());     // 체크된 것만 값을 뽑아서 배열에 push
			   console.log(reBoxArr);
			  })
			  $.ajax({
				   method : 'POST',
				   url: delURL+'/reDel',
				   data: {
					   reBoxArr : reBoxArr 
				   },
				   dataType : "json",
				   beforeSend: function (xhr) {
			            xhr.setRequestHeader(header, token);
			       },
				   success: function(result){
						swal(result.msg,"");  //추후 없애주기..
						commList();
				   },
				   error: function(xhr, status, error) {
				   		console.log(error);
				   }  
				});
	      	}
		  }else{
				return false;
		  }
	});
}
//문의게시판 전체선택 
const qnaCheck=function(){
           //전체 체크박스 클릭
           $("#qnaCheckAll").click(function() {
               if ($("#qnaCheckAll").prop("checked")) {
                   $("input[name='chQna']").prop("checked", true);
               } else {
                   $("input[name='chQna']").prop("checked", false);
               }
           });
           //전체 체크박스 선택중 체크박스 하나를 풀었을때 "전체" 체크해제
           console.log("ckck", $("input[name='chQna']"));
           $("input[name='chQna']").click(function() {
           	var total4 = $("input[name=chQna]").length;
       		var checked4 = $("input[name=chQna]:checked").length;
       		if(total4 != checked4){
       			$("#qnaCheckAll").prop("checked", false);
       		}else{ 
       			$("#qnaCheckAll").prop("checked", true); 
       		}
       	});
          
}   
//문의게시판 삭제 처리
function qnaDeleteClick(){
	//삭제 확인창
	swal({
		  title: "삭제하시겠습니까?",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
		})
		.then(function (willDelete){
		  if (willDelete) {  
		   //체크박스 선택했는지 안했는지
		   var totalCheck = $("input:checked[Name='chQna']").is(":checked")
		   if(!totalCheck){
			 swal("삭제 할 게시글을 선택해주세요","");
		  	 return false;
		   }else{
			   var qnaBoxArr = []; 
			   $("input:checkbox[name='chQna']:checked").each(function() {
			   qnaBoxArr.push($(this).val());     // 체크된 것만 값을 뽑아서 배열에 push
			   console.log(qnaBoxArr);
			  })
			  $.ajax({
				   method : 'POST',
				   url: delURL+'/qnaDel',
				   data: {
					   qnaBoxArr : qnaBoxArr 
				   },
				   dataType : "json",
				   beforeSend: function (xhr) {
			            xhr.setRequestHeader(header, token);
			       },
				   success: function(result){
						swal(result.msg,"");  //추후 없애주기..
						qnaList();
				   },
				   error: function(xhr, status, error) {
				   		console.log(error);
				   }  
				});
	         }
		  }else{
				return false;
		  }
	});
}

