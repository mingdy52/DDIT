<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 8. 8.      이아인      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<style> 
.col-xl-6{
margin-left: 30px;
width: 1000px;
}
.card-title{
font-weight : bold;
}

label{
font-weight : bold;
}
#upNick{
margin-top: 10px;
margin-bottom: 10px;
margin-left: 10px;
/* height: 38px; */
font-size: 0.8rem;
}
#chBtn{
margin-top: 10px;
margin-bottom: 10px;
margin-left: 10px;
font-size: 0.8rem;
}
#pass_ok1, #pass_ok2{
color:red;
margin-left: 15px;
}
#p_nick{
font-weight : bold;
}

.card{
	border: none;
	box-shadow: none;
}
.accoutTb{
	width: 70%;
	margin: auto;
	font-size: 20px;
	color: black;
}

.accountTb td{
	padding: 5px;
}
input::placeholder{
font-size: 0.9em;
}
</style>

<div class="col-xl-6">
	<div class="card">
		<div class="card-body">

			<h4 style="font-weight: bold;">회원정보</h4>
			<p class="card-title-desc">회원정보 -수정은 별명과 비밀번호만 가능합니다.</p>
			<!--          <form action="" class="custom-validation" novalidate=""> -->
			<table class="table-responsive accoutTb">
				<tr>
					<td style="width: 50%;">*ID</td>
					<td>${member.memId }</td>
				</tr>
				<tr>
					<td>*NAME</td>
					<td>${member.memName }</td>
				</tr>
				<tr>
					<td>*E-MAIL</td>
					<td>${member.memMail }</td>
				</tr>
				<tr>
					<td>*TELL</td>
					<td>${member.memHp }</td>
				</tr>
				<tr>
					<td>*NICKNAME</td>
					<td>${member.memNick }<button id="upNick" 
							class="btn btn-dark btn-rounded btn-sm waves-effect waves-light"
							data-bs-toggle="modal" data-bs-target="#exampleModal">별명수정</button></td>
				</tr>

			</table>
</div>
<!-- 			<div class="mb-3"> -->
				<!--                <label>*회원아이디</label> -->
				<!--                <div> -->
				<%--                ${member.memId } --%>
				<!--                   <input type="text" class="form-control parsley-error" 
<!--                      data-parsley-minlength="6" placeholder="Min 6 chars." -->
				<!--                      data-parsley-id="25" aria-describedby="parsley-id-25"> -->
				<!--                   <ul class="parsley-errors-list filled" id="parsley-id-25" -->
				<!--                      aria-hidden="false"> -->
				<!--                   </ul> -->
				<!--                </div> -->
				<!--             </div> -->
				<!--             <div class="mb-3"> -->
				<!--                <label>*이메일</label> -->
				<!--                <div> -->
				<%--                ${member.memMail } --%>
				<!--                   <input type="text" class="form-control parsley-error" required=""
<!--                      data-parsley-maxlength="6" placeholder="Max 6 chars." -->
				<!--                      data-parsley-id="27" aria-describedby="parsley-id-27"> -->
				<!--                   <ul class="parsley-errors-list filled" id="parsley-id-27" -->
				<!--                      aria-hidden="false"> -->

				<!--                   </ul> -->
				<!--                </div> -->
				<!--             </div> -->
				<!--             <div class="mb-3"> -->
				<!--                <label>*전화번호</label> -->
				<!--                <div> -->
				<%--                ${member.memHp } --%>
				<!--                   <input type="text" class="form-control parsley-error" required=""
<!--                      data-parsley-length="[5,10]" -->
				<!--                      placeholder="Text between 5 - 10 chars length" -->
				<!--                      data-parsley-id="29" aria-describedby="parsley-id-29"> -->
				<!--                   <ul class="parsley-errors-list filled" id="parsley-id-29" -->
				<!--                      aria-hidden="false"> -->

				<!--                   </ul> -->
				<!--                </div> -->
				<!--             </div> -->
				<!--             <div class="mb-3"> -->
				<!--                <label>*별명</label> -->
				<!--                <div> -->
				<%--                ${member.memNick } --%>
				<!--                <button id="upNick" class="btn btn-outline-primary waves-effect waves-light" data-bs-toggle="modal" data-bs-target="#exampleModal">별명 변경하기</button> -->
				<!--                     <input id="nick" type="text" class="form-control parsley-error" required=""
<!--                     data-parsley-min="6" placeholder=""> -->
				<!--                     data-parsley-id="31" aria-describedby="parsley-id-31" -->
				<!--                   <ul class="parsley-errors-list filled" id="parsley-id-31" -->
				<!--                      aria-hidden="true"> -->
				<!--                   </ul> -->
				<!--                </div> -->
				<!--             </div> -->
				<div>
				  <button class="btn btn-link btn-rounded waves-effect" style="float: right; width: 180px; " data-bs-toggle="modal" data-bs-target=".bs-example-modal-center">비밀번호변경</button>
				</div>
				<div>
				<button type="reset" class="btn btn-secondary waves-effect" style="float: right; margin-right: 20px;" data-bs-toggle="modal" data-bs-target="#deleteModal">회원탈퇴</button>
				</div>
				<div class="modal fade bs-example-modal-center" tabindex="-1"
					aria-labelledby="mySmallModalLabel" style="display: none;"
					aria-hidden="true">
					<div class="modal-dialog modal-dialog-centered">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close" id="updateClose"></button>
							</div>
							<div class="modal-body">
								<div class="mb-3">
									<label>변경하실 비밀번호</label> 
									<div>
									<span id="pass_ok1"></span>
									</div>
									<div>
										<input type="password" id="newPass"
											class="form-control parsley-error" required="required"
											placeholder="영문자와 숫자,특수문자를 조합하여 4~8글자로 입력해주세요" />
									</div>
								</div>

								<form action="${cPath}/member/upPass" method="post"
									id="passForm">
									<sec:csrfInput />
									<div class="mb-3">
										<label>비밀번호 확인</label> 
										<span id="pass_ok2"></span>
										<div>
											<input type="password" id="newChPass" name="newChPass"
												class="form-control parsley-error" required="required"
												placeholder="비밀번호를 한번더 입력해주세요" /> <input type="hidden"
												id="checkPass" name="checkPass" value="" />
										</div>
									</div>

									<div class="mb-0">
										<div>
											<Button type="button" id="upPass" style="float: right;"
												class="btn btn-primary waves-effect waves-light me-1">비밀번호
												변경하기</Button>
										</div>
									</div>
								</form>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
			</div>
		</div>




	<!-- 별명 변경 Modal -->
<div class="modal fade show" id="exampleModal" tabindex="-1"
	style="display: none;" aria-modal="true" role="dialog">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">별명 변경</h5>
			</div>
			 <form:form modelAttribute="member" action="${cPath}/member/upNick" method="post" id="upForm">
    		<div class="modal-body">
	    	<p id="p_nick">
	         *4~8 한글,영문의 별명 으로 입력해주세요*<br>
	         *중복체크는 필수입니다*<br>
	         *공백 입력 불가능*
	         </p>
	         <form:input type="text" id="nick" path="memNick" class="form-control" placeholder="4~8 한글,영문의 별명 으로 입력해주세요"/>
	         <button id="chBtn" type="button" class="btn btn-outline-primary waves-effect waves-light">중복체크</button>
	         <span id="nick_ok"></span>
		    </div>
		    <div class="modal-footer">
		      <button type="submit" class="btn btn-primary" id="update">변경하기</button>
		      <button id="btn_close" type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
		    </div>
   			</form:form>
		</div>
	</div>
</div>

<!--별명 수정 전 modal  -->
<%-- <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
<div class="modal-dialog">
  <div class="modal-content">
    <div class="modal-header">
      <h5 class="modal-title" id="exampleModalLabel">별명 변경</h5>
    </div>
    <form method="post" id="upForm" action="${cPath}/member/upNick" >
    <form:form modelAttribute="member" action="${cPath}/member/upNick" method="post" id="upForm">
    <div class="modal-body">
    	<p id="p_nick">
         *4~8 한글,영문의 별명 으로 입력해주세요*<br>
         *중복체크는 필수입니다*<br>
         *공백 입력 불가능*
         </p>
         <form:input type="text" id="nick" path="memNick" class="form-control" placeholder="4~8 한글,영문의 별명 으로 입력해주세요"/>
         <button id="chBtn" type="button" class="btn btn-outline-primary waves-effect waves-light">중복체크</button>
         <span id="nick_ok"></span>
    </div>
    <div class="modal-footer">
      <button type="submit" class="btn btn-primary" id="update">변경하기</button>
      <button id="btn_close" type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
    </div>
   </form:form>
  </div>
</div>
</div> --%>

<!-- 비밀번호 변경 Modal data-bs-toggle="modal" data-bs-target="#upPassModal" -->
<div class="modal fade show" id="upPassModal" tabindex="-1"
	style="display: none; text-align: center;" aria-modal="true" role="dialog">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">비밀번호 변경</h5>
			</div>
			<div class="modal-body">
	      		*비밀번호를 변경하시려면 기존 비밀번호를 입력해주세요*
	        	<input type="password" name="password" id="myPass"class="form-control"/>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-primary" id="passBtn">변경하기</button>
	        <button type="button" class="btn btn-secondary" id="passClose" data-bs-dismiss="modal">닫기</button>
	      </div>
		</div>
	</div>
</div>

<!-- 탈퇴Modal -->
<div class="modal fade show" id="deleteModal" tabindex="-1"
	style="display: none; text-align: center;" aria-modal="true" role="dialog">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
			</div>
		 <form action="${cPath}/member/delMem" method="post" id="delForm" name="delForm">
	     <sec:csrfInput/>
	      <div class="modal-body">
	      		<h5>*탈퇴를 하시려면 기존 비밀번호를 입력해주세요*</h5>
	        	<input type="password" name="delPass" id="delPass"class="form-control"/>
		      <div><br>
						<h5 style="text-align: center;">*탈퇴사유를 선택해주세요* </h5>
						<select id="delReason" name="delReason" class="" style="width:100%; text-align: center; background-color: #f1f2ff; height: 50px; border: 1px solid #D5D1D6">
							<option value="">===필수선택===</option>
							<option value="reason1">아이디변경을 위해 탈퇴 후 재가입</option>
							<option value="reason2">이용할 만한 서비스 부족</option>
							<option value="reason3">사이트 이용 불편</option>
							<option value="reason4">이용빈도 낮음</option>
						</select>
					</div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-primary" id="remove">탈퇴하기</button>
	        <button type="button" class="btn btn-secondary" id="delClose" data-bs-dismiss="modal">닫기</button>
	      </div>
	    </form> 
		</div>
	</div>
</div>


	
<script>
//아작스 사용을 위한 토큰, 전역변수로 해 놓으면 편함
var header = '${_csrf.headerName}';
var token =  '${_csrf.token}';
let url="${cPath}/member/nickCheck";
</script>

<script src="${cPath }/resources/js/member/mypage.js"></script>