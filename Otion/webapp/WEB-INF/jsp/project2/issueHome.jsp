<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib  uri="http://www.springframework.org/tags/form" prefix="form" %>
    
    <style>
    
/*     td{ */
/* 	height: 90px; */
	
/* 	} */
.selectbox{
	border: 1px solid #ced4da;
	border-radius: 5px;
}
     
    </style>
    
<div class="container-fluid" style=" height: 776px;">
	<h4 style="text-align: right; font-weight: bold;">프로젝트이슈</h4>
	<div id="searchUI" class="d-flex justify-content-center form-inline" style="float: right; margin-bottom: 30px; border: ">
		<form:select path="simpleCondition.searchType" class="mr-2 selectbox" style="text-align: center;">
			<form:option value="" label="전체" />
			<form:option value="title" label="요청명" />
			<form:option value="workReq" label="요청자" />
		</form:select>
		<form:input path="simpleCondition.searchWord" class="mr-2 selectbox" />
		<input type="button" id="searchBtn" class="btn btn-dark" value="검색" />
	</div>
				
					<table class="table table-centered table-nowrap table-hover mb-0" style="text-align: center; ">
						<thead class="table-light">
							<tr>
								<th scope="col" style="width: 50px;">NO</th>
								<th scope="col" style="width: 120px;">요청자</th>
								<th scope="col" style="width: 500px;">요청명</th>
								<th scope="col">업무명</th>
								<th scope="col" style="width: 120px;">이슈일자</th>
							</tr>
						</thead>
						<tbody id="listBody">
							

						</tbody>
						<tfoot>						
							<tr>							
								<td colspan="5" style="border-bottom: 0px;">
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
let searchUI = $("#searchUI");

let pagingArea = $(".pagingArea").on("click", "a", function(event) {
			let page = $(this).data("page");
			searchForm.find("[name=page]").val(page);
			searchForm.submit();
		});


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
						trTags.push.apply(trTags,makeSingleTr(index, work));
					});
						
				} else {
					let trTag = $("<tr>").html(
							$("<td>").attr("colspan", "5").html(
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
		let trList = [];
		let issueList = work.issueList;
		for(var i=0; i<issueList.length; i++){
		let tr = $("<tr>");
		let url = "${cPath}/project/${pjId}/issue/"+issueList[i].issueNum;
		tr.attr("onclick","location.href='"+url+"'").attr("style","cursor:pointer").append(
				$("<td >").html(issueList[i].rnum),
				$("<td >").html(work.workReq),
				$("<td >").html(work.workTitle),
				$("<td class=odd>").html(issueList[i].issueTitle), 
				$("<td >").html(issueList[i].issueDate)
		);
		trList.push(tr);
		}
		
		return trList;

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