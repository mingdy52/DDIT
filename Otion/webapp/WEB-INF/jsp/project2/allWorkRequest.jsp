<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 8. 5.    윤수웅      최초작성
* Copyright (c) ${year} by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>

.selectbox{
	border: 1px solid #ced4da;
	border-radius: 5px;
}
     

</style>

<div  class="container-fluid" style=" height: 776px;">
	<h4 style="text-align: right; font-weight: bold;">프로잭트업무</h4>
	<div id="searchUI" class="d-flex justify-content-center form-inline" style="float: right; margin-bottom: 30px; border: ">
			<form:select path="simpleCondition.searchType" class="mr-2 selectbox" style="text-align: center;">
				<form:option value="" label="전체" />
				<form:option value="title" label="제목" />
				<form:option value="writer" label="상태" />
				<form:option value="writer" label="긴급도" />
				<form:option value="writer" label="요청자" />
			</form:select>
			<form:input path="simpleCondition.searchWord" class="mr-2 selectbox" />
			<input type="button" id="searchBtn" class="btn btn-dark" value="검색" /> 
	</div>
                 
					<table class="table table-centered table-nowrap table-hover mb-0"   style="text-align: center; ">
						<thead class="table-light">
							<tr>
								<th scope="col" >No</th>
								<th scope="col">긴급도</th>
								<th scope="col">상태</th>
								<th scope="col">요청자</th>
								<th scope="col">제목</th>	
								<th scope="col">시작일</th>
								<th scope="col">종료일</th>
								<th scope="col">하위업무</th>
							</tr>
						</thead>
						<tbody id="listBody">
							

						</tbody>
						<tfoot>						
							<tr>							
								<td colspan="8" style="border-bottom: 0px;">
								 <div class="d-flex justify-content-center pagingArea" style="width:500px; margin-left:auto; margin-right:auto;  margin-top: 30px;"></div>								
								</td>
							</tr>
						</tfoot>
					</table>
			</div>

<div style="display: none">
	<form id="searchForm">
		<input type="text" name="searchType" /> <input type="text"
			name="searchWord" /> <input type="text" name="page" />
	</form>
</div>


<script>
	let listBody = $("#listBody");
	let pagingArea = $(".pagingArea").on("click", "a", function(event) {
				let page = $(this).data("page");
				searchForm.find("[name=page]").val(page);
				searchForm.submit();
			});

	
	let searchUI = $("#searchUI");

	
	
	
	let searchForm = $("#searchForm").ajaxForm(
			{
				dataType : "json" // text, html, json, xml, script -> main type : text, 파일 업로드 처리를 비동기로? (FormData)
				,
				success : function(resp, status, jqXHR) {
					let pagingVO = resp;
					dataList = pagingVO.dataList;
					let trTags = [];
					console.log(dataList);
					if (dataList && dataList.length > 0) {
						$(dataList).each(function(index, work) {
							trTags.push(makeSingleTr(index, work));
						});
						
						
						$.ajax({
							url : "${cPath}/project/PJ001/work/workList",
							method : "get",
							dataType : "json", // text, html, json, xml, script -> main type : text, 파일 업로드 처리를 비동기로? (FormData)
							success : function(resp, status, jqXHR) {
								order=0;
								let pagingVO = resp;
								let workList = pagingVO.dataList;
								let numberList =listBody.find(".workNumber");
								
								for(var i in numberList){
								for(var j in workList){
										if(numberList.eq(i).text()==workList[j].parentWorkNum){
											numberList.eq(i).closest("tr").find(".childWork").text("Y");
											break;
										}else{
											numberList.eq(i).closest("tr").find(".childWork").text("N");
												
										}
								}
							}
							
							},
						error : function(jqXHR, status, error) {
							console.log(jqXHR);
							console.log(status);
							console.log(error);
							}
					});
						
					} else {
						let trTag = $("<tr>").html(
								$("<td>").attr("colspan", "8").attr("style", "").html(
										"조건에 맞는 글이 없음."));
						trTags.push(trTag);
						
					}
					listBody.html(trTags);
					pagingArea.html(pagingVO.pagingHTMLBS);
				},
				error : function(jqXHR, status, error) {
					console.log(jqXHR);
					console.log(status);
					console.log(error);
				}
			}).submit();

	
	
	
	let makeSingleTr = function(index, work) {
			let tr = $("<tr>");
			let url = "${cPath}/project/"+work.pjId+"/work/"+work.workNum;
			tr.attr("onclick","location.href='"+url+"'").attr("style","cursor: pointer").append($("<td>").html(work.rnum), 
					$("<td >").html(work.workStatusCode), 
					$("<td class='workNumber'>").html(work.workNum), 
					$("<td >").html(work.workPriority),
					$("<td >").html(work.workReq), 
					$("<td>").html(work.workTitle),
					$("<td >").html(work.workStart),
					$("<td >").html(work.workEnd),
					$("<td class='childWork'>")
					
					
			);
				
			tr.find(".workNumber").css("display"," none")
			
			return tr;
	
	}

	
	
	let searchBtn = $("#searchBtn").on("click", function(event) {
		searchForm.get(0).reset();
		let inputs = searchUI.find(":input[name]");
		$(inputs).each(function(idx, input) {
			let name = $(this).attr("name");
			let value = $(this).val();
			searchForm.find("[name=" + name + "]").val(value);
		});
		searchForm.submit();
	});

	
		
</script>

