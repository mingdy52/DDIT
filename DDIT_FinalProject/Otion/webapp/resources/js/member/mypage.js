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
//비밀번호 입력 & 탈퇴 사유 선택했는지 확인하고 submit!!
$("#remove").on("click",function(){
	
	var delPass=$("#delPass").val().trim();
	var delform = document.querySelector("#delForm");
	var delReason = document.querySelector("#delReason").value;
	console.log(delPass);
	if(delPass==''){
		swal("비밀번호를 입력해주세요","");
		return false;
		
	}else if(delReason==''){
		swal("탈퇴사유를 선택해주세요 ","");
		return false;
	}else{
		delform.submit();	
	}
	
});

//모달창 닫으면 기존비밀번호와 셀렉트박스 지워주기 
$("#delClose").on("click",function(){
	    $("#delPass").val('');
	    ﻿$('select').find('option:first').prop('selected', true);

	   
});

//비밀번호 확인 & 유효성 검사
$('#upPass').on('click', function(){
	let newPass=$("#newPass").val().trim();
	//let regPass = /^(?!(?:[0-9]+)$)([a-zA-Z]|[0-9a-zA-Z]){4,}$/;
	let regPass = /^(?=.*[a-zA-Z])(?=.*[~!@#$%^*+=-])(?=.*[0-9]).{4,8}$/;
 	  if(newPass.length>0){
 	  	if(!regPass.test(newPass)){
 	  		//alert("4~8글자의 영문자를 입력해주세요");
 	  		$("#pass_ok1").text("4~8글자의 영문자와 숫자,특수문자를 조합해주세요");
 	  		return false;
 	  	}else{
 	  		$("#pass_ok1").text("");
 	  	}
 	  }
	let newChPass=$("#newChPass").val().trim();
	
	$('#upPassModal').off();
	if(!(newPass.length>0 && newChPass.length>0)){
		swal("비밀번호를 입력해주세요","");	
	}else{
			
			if(newPass != newChPass){
				//alert("비밀번호가 불일치 합니다.");
				$("#pass_ok2").text("비밀번호가 불일치 합니다.");
				$('#upPassModal').off();
			}else{
				$("#pass_ok2").text("");
				$('#upPassModal').modal('show')
			}
		}
		
	});	
	

//비밀번호 변경버튼 누르면 form제출
$("#passBtn").on("click",function(){
	let password=$("#myPass").val().trim();
	if(password==''){
		swal("비밀번호를 입력해주세요","");
		return false;
	}else{
		var v_form = document.querySelector("#passForm");
		let password=$("#myPass").val().trim();
		$("#checkPass").val(password);
		v_form.submit();	
	}
});	

//모달창 닫으면 기존비밀번호 지워주기 
$("#passClose").on("click",function(){
	    $("#myPass").val('');
	   
});
	
//닉네임 유효성 검사
function userNickValid(p_userNick){
	  //validation - nickName
	  let regNick = /^[가-힣a-zA-Z]{4,8}$/;
	  
	  if(regNick.test(p_userNick)){
	  	return true;
	  }
	  return false;
}

//document.querySelector('#chBtn').addEventListener('click',function(){
   $("#chBtn").on("click",function(){
   //요청 데이터
      var nick = document.querySelector("#nick")
      var nickVal=nick.value.trim();
      
      if(!userNickValid(nickVal)){
    	  swal("4~8글자의 한글,영문만 입력,공백입력 불가능입니다.");
    	 nick.focus(); // 마우스 커서 다시 텍스트박스에 가져다 주깅
         return false;
      }
       
   //let url="${cPath}/member/nickCheck"; jsp에 선언
      $.ajax({
          url:url,
          method:'post', 
          data:{nick:nickVal},
	      //security적용된 상태에서 ajax 요청시 필수
          beforeSend: function (xhr) {
              xhr.setRequestHeader(header, token);
          },
          success:function(cnt){ //컨트롤러에서 넘어온 cnt값을 받는다 
               if(cnt == 0){ //cnt가 1이 아니면(=0일 경우) -> 사용 가능한 별명
                /*  alert("사용가능한 별명입니다."); */
                 $("#nick_ok").text("사용가능한 별명입니다."); 
                 check=true;
              } else { // cnt가 1일 경우 -> 이미 존재하는 별명
                /*  alert("사용불가능한 별명입니다."); */
                 $("#nick_ok").text("사용불가능한 별명입니다."); 
                  
             }
          },
          error:function(){
        	  swal("다시한번 입력해주세요","");
          }
      });
   
});
   //중복체크 버튼 안눌렀을때 폼 넘어가지않게 하기
   var v_form= document.querySelector("#upForm");
   var check = false;
   $("#upForm").on('submit', function(e){
      if(!check){
    	  swal("중복체크를 해주세요","")
         event.preventDefault();
      }else {
        /*  alert("중복체크 했네용"); */
         v_form.submit();
      }
   });
   
   //모달창 닫으면 아까 적어준 별명 지워주기 
   $("#btn_close").on("click",function(){
	    $("#upForm")[0].reset();
	    $("#nick_ok").empty();
	    check=false;
   });
   
   //별명 변경에서 중복체크하고 다시 수정했을때 중복체크하라는 alert창 띄워주기
   $("#nick").change(function(){
	   check=false;
   });
   
   //비밀번호 변경 모달 sapn과 내용 지워주기
   $("#updateClose").on("click",function(){
	   $("#newPass").val("");
	   $("#newChPass").val("");
	   $("#pass_ok1").text("");
	   $("#pass_ok2").text("");
   });
	  