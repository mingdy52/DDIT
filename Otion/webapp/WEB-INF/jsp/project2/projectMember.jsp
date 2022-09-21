<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 8. 29.      작성자명      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<sec:authentication property="principal.realMember" var="member" />

<div>
<h4 style="text-align: right; font-weight: bold;">인원관리</h4>
<div id="searchUI" class="d-flex justify-content-center form-inline" style="float: right;; margin-bottom: 30px;">
						<form:select path="simpleCondition.searchType" class="form-select" style="width:100px; text-align: center;">
							<form:option value="" label="전체" />
							<form:option value="nickName" label="아이디" />
							<form:option value="role" label="업무역할" />
							<form:option value="about" label="팀원권한" />
						</form:select>
						<form:input path="simpleCondition.searchWord" class="form-control" />
						<input class="btn btn-dark" id="searchBtn" type="button" value="검색">
					</div>


<div  >
	<table class="table table-centered table-nowrap table-hover mb-0" style="text-align: center; ">

		<thead>
			<tr class="table-light">
				<th scope="col">NO</th>
				<th scope="col">팀원이름</th>
				<th scope="col">팀원관한</th>
				<th scope="col">업무역활</th>
				<th scope="col">프로젝트 가입일</th>
				<th scope="col">탈퇴여부</th>
				<th scope="col">수정</th>
				<th scope="col">탈퇴처리</th>


			</tr>
		</thead>
		<tbody id="listBody">

			
		</tbody>
		
	</table>
</div>

<div style="display: none">
	<form id="searchForm">
		<input type="text" name="searchType" /> <input type="text"
			name="searchWord" /> <input type="text" name="page" />
	</form>
</div>

<div style="display: none;">
		<form id="removeFrom" method="post">
		<sec:csrfInput/>
		 <input type="hidden" name="_method" value="PUT"/>
		<input type="text" name="colNum" /> 
	</form>

</div>
</div>

<!-- childModal  -->
	<div class="modal fade" id="memberModify" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="myModalLabel">Modal Heading</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div id="memberBody" class="modal-body">

				<form id="memberForm">			

						<div class="mb-3">
							<label>팀원권한</label> 
							<select name="projAuthCode" class="form-select" required aria-label="select example">
								<option>PM</option>
								<option>정회원</option>
								<option>준회원</option>
							</select>
							
						</div>
						
						<div class="mb-3">
							<label>업무역활</label>
							<select name="workRoleCode" class="form-select" required aria-label="select example">
								<option>PL</option>
								<option>DA</option>
								<option>TA</option>
								<option>UA</option>
								<option>AA</option>
								<option>BA</option>
							</select>
						</div>

					
						<input type="hidden" name="colNum" value="">

					</form>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-light waves-effect"
						data-bs-dismiss="modal">Close</button>
					<button type="button" name="childFormSaveBtn"
						class="btn btn-primary waves-effect waves-light">등록</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>


<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script>
	let listBody = $("#listBody");
	var memberModalE1 = document.getElementById('memberModify');
	let searchUI = $("#searchUI");

	let searchForm = $("#searchForm").ajaxForm(
			{
				dataType : "json" // text, html, json, xml, script -> main type : text, 파일 업로드 처리를 비동기로? (FormData)
				,
				success : function(resp, status, jqXHR) {
					let pagingVO = resp;
					let dataList = pagingVO.dataList;
					let trTags = [];
					console.log(dataList);
					if (dataList && dataList.length > 0) {
						$(dataList).each(function(index, colleague) {
							trTags.push(makeSingleTr(index, colleague));
						});

					} else {
						let trTag = $("<tr>").html(
								$("<td>").attr("colspan", "7").html(
										"조건에 맞는 글이 없음."));
						trTags.push(trTag);

					}
					listBody.html(trTags);
				},
				error : function(jqXHR, status, error) {
					console.log(jqXHR);
					console.log(status);
					console.log(error);
				}
			}).submit();

	
	let makeSingleTr = function(index, colleague) {
		let tr = $("<tr>");
		tr.append(
				$("<td class=odd>").html(colleague.member.rnum),
				$("<td style='display:none;' class='colNum'>").html(colleague.colNum),
				$("<td >").html(colleague.memId),
				$("<td name='projAuthCode'>"),
				$("<td name='workRoleCode'>").html(colleague.workRoleCode),
				$("<td>").html(colleague.colDate.substring(0, 10)),
				$("<td >").html(colleague.colResignYn),
				$("<td  class='modifyBtn'  style='cursor:pointer;' >").html("수정"),
				$("<td class='mdi mdi-close-thick' style='color: red;' name='remove'>")

		);
		
		if(colleague.projAuthCode=="PA01"){
			tr.find("td[name=projAuthCode]").html("PM");
		}else if(colleague.projAuthCode=="PA02"){
			tr.find("td[name=projAuthCode]").html("정회원");
		}else{
			tr.find("td[name=projAuthCode]").html("준회원");
		}
			
		
		
		return tr;

	}
	
	$("button[name=childFormSaveBtn]").on("click",function(){
		data= $("#memberForm").serialize();
		$.ajax({
			url : "${cPath}/project/${pjId}/colleague/modify",
			data : data,
			dataType : "json" // text, html, json, xml, script -> main type : text, 파일 업로드 처리를 비동기로? (FormData)
			,
			success : function(resp, status, jqXHR) {
				let pagingVO = resp;
				let dataList = pagingVO.dataList;
				let trTags = [];
				console.log(dataList);
				if (dataList && dataList.length > 0) {
					$(dataList).each(function(index, colleague) {
						trTags.push(makeSingleTr(index, colleague));
					});

				} else {
					let trTag = $("<tr>").html(
							$("<td>").attr("colspan", "7").html(
									"조건에 맞는 글이 없음."));
					trTags.push(trTag);

				}
				listBody.html(trTags);
				$("#memberModify").modal('hide');
				swal("수정되었습니다", "", "success");
			},
			error : function(jqXHR, status, error) {
				console.log(jqXHR);
				console.log(status);
				console.log(error);
			}
		});
		
		
	});
	
	$("#listBody").on("click",".modifyBtn",function(){
		$("select[name=projAuthCode]").val($(this).closest("tr").find("td[name=projAuthCode]").text()).prop("selected", true);
		$("select[name=workRoleCode]").val($(this).closest("tr").find("td[name=workRoleCode]").text()).prop("selected", true);
		$("input[name=colNum]").val($(this).closest("tr").find(".colNum").text());
		$("#memberModify").modal('show');
	});

	
	$("#listBody").on("click","td[name=remove]",function(){
		
		 swal('탈퇴처리 하시겠습니까?','',"warning",{
			 buttons: {
			  승인: true,
			  cancel: "취소",
			 },
			})
			.then((value) => {
					  switch (value) {
				  
				  case "승인":
				  swal('승인이 완료되었습니다.');
				  $("#removeFrom").find("input[name=colNum]").val($(this).closest("tr").find(".colNum").text());
				  console.log($(this).closest("tr").find(".colNum").text());
				  $("#removeFrom").submit();
				  break;
				  
				  default:
					  swal('취소되었습니다.');  
			  }
			  
			});
		
	});
	
	
	
	let searchBtn = $("#searchBtn").on("click", function(event) {
		searchForm.get(0).reset();
		let inputs = searchUI.find(":input[name]");
		$(inputs).each(function(idx, input) {
			let name = $(this).attr("name");
			let value = $(this).val();
			console.log(value);
			if(value=="PM"){
				value="PA01";
			}else if(value=="정회원"){
				value="PA02";
			}else if(value=="준회원"){
				value="PA03";			
				}
			else if(value=="PL"){
				value="WR01";
			}else if(value=="DA"){
				value="WR02";
			}else if(value=="TA"){
				value="WR03";			
				}
			else if(value=="UA"){
				value="WR04";			
				}
			else if(value=="AA"){
				value="WR05";			
				}
			else if(value=="BA"){
				value="WR06";			
				}
			searchForm.find("[name=" + name + "]").val(value);
		});
		searchForm.submit();
	});
	
	memberModalE1.addEventListener('hidden.bs.modal', function(event) {

		$("select[name=projAuthCode]").val("");
		$("select[name=workRoleCode]").val("");

	});
</script>