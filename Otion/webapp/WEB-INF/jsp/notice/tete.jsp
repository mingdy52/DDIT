<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 8. 1.      홍승조      컨트롤러 연결
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<style>
table {
	margin-left: auto;
	margin-right: auto;
}
	
span {
	margin: 0px !important;
	display:inline !important;
}

.yu_div_css {
	margin: 10px;
}

</style>
<script>
var page = 1;
$(document).ready(function() {
	getRep(page);
});

function deleteCheck() {
	Swal.fire({
		title: '삭제하시겠습니까?',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '확인',
        cancelButtonText: '취소'
    }).then((result) => {
        if (result.isConfirmed) {
            $("#deleteId").submit();
        }
    })
}

</script>         
                  
<sec:authentication property="principal" var="authMember" /> 
<div class="container-fluid">
	<div class="row">
		<div class="col-sm-12">
			<div class="card" id="cardBody">
				<div class="card-header">
					<span>BY ${issue.writerName }, </span> <span id="issueDate">${issue.issueDate }</span>
					<h5>${issue.issueTitle }</h5>
				</div>
					<div class="card-body">
						<div class="form theme-form">
							<div class="row">
								<div class="col-sm-6">
									<div class="mb-3">
										<label>프로젝트 </label> 
											<div class="yu_div_css">${issue.issueProj }</div>
									</div>
								</div>
								<div class="col-sm-6">
									<div class="mb-3">
										<label>담당자</label> 
										<div class="yu_div_css">${issue.chargerName }</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-6">
									<div class="mb-3">
										<label>이슈 일감</label> 
										<div class="yu_div_css">${issue.workTitle }</div>
									</div>
								</div>
								<div class="col-sm-6">
									<div class="mb-3">
										<label>이슈 유형</label>
										<div class="yu_div_css">${issue.issueType }</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-6">
									<div class="mb-3">
										<label>이슈 상태</label> 
										<div class="yu_div_css">${issue.issueState }</div>
									</div>
								</div>
								<div class="col-sm-6">
									<div class="mb-3">
										<label>이슈 중요도</label> 
										<div class="yu_div_css">${issue.issueImport }</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-6">
									<div class="mb-3">
										<label>일감의 관리자</label> 
										<div class="yu_div_css">${issue.workManagerName }</div>
									</div>
								</div>
								<div class="col-sm-6">
									<div class="mb-3">
										<label>일감의 담당자</label> 
										<div class="yu_div_css">${issue.workChargerName }</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col">
									<div class="mb-3">
										<label>이슈 내용</label>
										<div class="yu_div_css">${issue.issueContent }</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm">
									<div class="mb-3">
										<label>첨부파일</label>
											<div class="yu_div_css">
											    <c:if test="${not empty issue.attatchList }">
													<c:forEach items="${issue.attatchList }" var="attatch" varStatus="vs">
														<a href="${cPath}/pms/${issue.issueProj}/issue/${attatch.attId}/attatch">${attatch.attFilename }</a>
														<br>
														<c:if test="${not vs.last }">  </c:if>
													</c:forEach>
												</c:if>
											</div>
									</div>
								</div>
							</div>
							<c:if test="${issue.issueState eq 'RESOLVED' }">
								<div class="row">
									<div class="col">
										<div class="mb-3">
											<label>이슈 해결 내용</label>
											<div class="yu_div_css">${solve.solveContent }</div>
										</div>
									</div>
								</div>
							</c:if>
							
							
							<div class="row">
								<div class="col">
									<div class="text-end">
										<c:if test="${issue.issueState ne 'RESOLVED' }">
											<c:if test="${issue.issueWriter eq authMember.realMember.memId }">
												<button class="btn btn-primary" onclick="location='${cPath }/pms/${projId}/issue/${issue.issueId}/form'">수정</button>
											
												<form:form action="${cPath }/pms/${projId }/issue/${issue.issueId }" method="post" id="deleteId"
													modelAttribute="issue" style="display: inline-block;" 
													
													>
													<input type="hidden" name="_method" value="delete" />
													<input type="button" value="삭제" onclick="deleteCheck()"
															class="btn btn-info" data-href="${cPath }/pms/${projId }/issue/${issue.issueId }" />
													<form:input type="hidden" path="issueId" value="${issue.issueId }" />
												</form:form>
												
											</c:if>
											<c:if test="${issue.issueCharger eq authMember.realMember.memId }">
												<button class="btn btn-danger" onclick="openSolveModal()">해결</button>
											</c:if>
										</c:if>
									</div>
								</div>
							</div>
						</div>
					</div>
			</div>
			<div class="card">
				<div class="card-header">
					<h5>댓글</h5><br>
					<form method="post" id="repInsertFormID">
						<div class="form theme-form">
							<div class="row">
								<div class="col-sm-11">
									<div class="mb-3">
										<sec:csrfInput />
										<input type="hidden" name="repDate" id='currentDateR' />
										<input type="hidden" name="repWriter" value="${authMember.realMember.memId }" />
										<input type="hidden" name="repIssue" value="${issue.issueId }" />
										<textarea placeholder="내용을 입력해주세요." id="repContentID" name="repContent" class="form-control" rows="3" style="resize: none;" required></textarea>
									</div>
								</div>
								<div class="col-sm-1">
									<div class="mb-3">
										<button type="submit" class="btn btn-primary" id="repInBtn">등록</button>
									</div>
								</div>
							</div>
						</div>			
					</form>
				</div>
				<div class="card-body" style="padding-top: 0;">
					<div class="form theme-form" id="replyView">
					
					</div>
				</div>
				<div class="card-foot">
					<div id="pagingID" class="pagingArea pagination justify-content-center pagination-primary">
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- 이슈 해결 -->
<div id="solveModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4>이슈 해결</h4>
                <button type="button" class="btn" onclick="closeModal()" data-dismiss="modal" ><span aria-hidden="true">&times;</span> <span class="sr-only">close</span></button>
            </div>
            <form:form action="${cPath }/pms/${projId }/issue/${issue.issueId }/solve" modelAttribute="issue" method="post" enctype="multipart/form-data">
	            <div id="insertModalBody" class="modal-body">
	            	<div class="row">
						<div class="col-sm-6">
							<div class="mb-3">
								<label>이슈제목</label>
	                    		<input class="form-control" type="text" value="${issue.issueTitle }" disabled="disabled">
	                    		<input class="form-control" type="hidden" name="solveIssue" value="${issue.issueId }">
							</div>
						</div>
						<div class="col-sm-6">
							<div class="mb-3">
								<label>해결일</label>
	                  			<input id="currentDate" class="form-control" type="date" name="solveFdate" readonly="readonly">
							</div>
						</div>
					</div>	
					<div class="row">
						<div class="col-sm-12">
							<div class="mb-3">
								<label>해결내용</label>
	                    		<textarea class="form-control" name="solveContent" rows="20"></textarea>
							</div>
						</div>
					</div>	
	                <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn" onclick="closeModal()" data-dismiss="modal">Cancel</button>
	                <button type="submit" class="btn btn-primary">Save</button>
	            </div>
            </form:form>
        </div>
    </div>
</div>
          
<script>
let header = '${_csrf.headerName}';
let token =  '${_csrf.token}'; 

var repInsertForm = $('#repInsertFormID');
var paging = $('#pagingID');
var currentPage = 1;
let url = "${cPath}/pms/${projId}/issue/${issue.issueId}/reply";
let replyView = $("#replyView");

$("#issueDate").text($("#issueDate").text().substring(0, 10));
document.getElementById('currentDate').value = new Date().toISOString().substring(0, 10);
document.getElementById('currentDateR').value = new Date().toISOString().substring(0, 10);


<%-- 모달닫기 --%>
function closeModal() {
	$('#solveModal').modal('hide');
}
<%-- 모달열기 --%>
function openSolveModal() {
	$('#solveModal').modal('show');
}
<%-- form데이터 serialize  --%>
jQuery.fn.serializeObject = function() {
	    var obj = null;
	    try {
	        if (this[0].tagName && this[0].tagName.toUpperCase() == "FORM") {
	            var arr = this.serializeArray();
	            if (arr) {
	                obj = {};
	                jQuery.each(arr, function() {
	                    obj[this.name] = this.value;
	                });
	            }
	        }
	    } catch (e) {
	        alert(e.message);
	    } finally {
	    }
	    return obj;
};


<%-- 댓글 등록  --%>
repInsertForm.on("submit",function(){
	event.preventDefault();
	let data = repInsertForm.serializeObject();
	
	$.ajax({
		type:"POST",
		url:url,
		data:JSON.stringify(data),
		contentType: 'application/json',
		beforeSend : function(xhr){
			xhr.setRequestHeader(header,token);
		},
		success: function(result) {
			$("#repContentID").val("");
 			getRep(currentPage);
		},
		error:function(request, status, err){
			console.log(request);
			console.log(status);
			console.log(err);				
		}
	});
});

<%-- 댓글 페이징  --%>
let pagingArea = $(".pagingArea").on("click", "a", function(event) {
		currentPage = $(this).data("page");
		getRep(currentPage);
});
	
<%-- 댓글 리스트  --%>
//서버사이드에서 하는 일이 항상 클라이언트 사이드에서 하는 일 보다 먼저 실행됨!
var v_memId = "${authMember.realMember.memId}";
function getRep(page){
	let urlPage = url + "?page=" + page;
	$.ajax({
		type:"GET",
		url: urlPage,
		dataType:'json',
		success: function(result) {
			<%-- 댓글 영역 지우기  --%>
			replyView.children().remove();
 			paging.children().remove();
			
			if(result["replyList"].length == 0){
// 				replyView.html("댓글이 없습니다.");
				console.log("댓글 없음");
				
			}else{
				result["replyList"].forEach((rep,idx)=>{
					let repWriter = rep['repWriter'];
					let repNo = rep['repNo'];
					let repContent = rep['repContent'];
					
					console.log("repWriter: ", repWriter);
					console.log("memId: ","${authMember.realMember.memId}");
					
					replyView.append(
						$("<div>").attr("class","row").append(
								
							$("<div>").attr("class","col-sm-11").append(
								$("<div>").attr({
									"class": "mb-3",
									"id": `mbcon_\${repNo}`,	
									}).append(
									$("<span>").text(rep["repWriterName"].substring(0,10) + " / " + rep["repDate"].substring(0,10))
									, $("<textarea>").attr({
										"id": `con_\${repNo}`,
										"name": "repContent",
										"class": "form-control",
										"row": "3",
										"required": "required",
										"readonly": "readonly",
										"style": "background-color: white; resize: none; box-shadow: none;"
									}).val(rep["repContent"])
								)//mb
							)//col
							
							,$("<div>").attr({
								"class": "col-sm-1", 
								"style": "padding: 0;  padding-left:20px;"
							}).append(
								$("<div>").attr({
									"class": "mb-3",
									"id": `mbbtn_\${repNo}`,	
									}).append(function(p_inx,p_htmlStr){
										if(v_memId == repWriter){
											return [	
											$("<button>").attr({
												"type":"button",
				                            	"id": `repModBtn_\${repNo}`,
				                                "class": "btn btn-danger btn-sm",
				                                "data-rno":rep["repNo"],
				                                "onclick": `repModForm(\${repNo},'\${repContent}')`,
				                                "style": "margin:1px; margin-top:20px; padding:4px; padding-right:26px; padding-left:26px;"
											}).html("수정"),
											$("<button>").attr({
												"type":"button",
							                    "id": `repDelBtn_\${repNo}`,
							                    "class": "btn btn-info btn-sm",
							                    "data-rno":rep["repNo"],
							                    "onclick": `repDelForm(\${repNo})`,
							                    "style": "margin:1px; padding:4px; padding-right:26px; padding-left:26px;"
											}).html("삭제")
										    ];
										   }
									}
								)//mb
							) //col	
						)//row
					)
					
				});
				<%-- 페이징  --%>
				paging.append(
					result["pagingVO"]["pagingHTMLBS"]			
				)		
				<%-- 페이징 버튼 누르면 하단으로 가도록  --%>
				let pageLink = $(".page-link");
				for(let i=0; i<pageLink.length; i++){
					paging.children().children().children().eq(i).children().attr("href","#pagingID");
				}
			}
			
			
	},
		error:function(request, status, err){
			console.log(request);
			console.log(status);
			console.log(err);				
		}
	});
}

<%-- 댓글 수정폼  --%>
function repModForm(repNo,repContent) {
	let contentId = "con_" + repNo;
	let mbcontentId = "mbcon_" + repNo;
	let repModBtn = "repModBtn_" + repNo;
	let repDelBtn = "repDelBtn_" + repNo;
	let mbbtn = "mbbtn_" + repNo;
	
	$(`#\${contentId}`).css("display","none");
	$(`#\${mbcontentId}`).append(
		$("<textarea>").attr({
			"id": `conMod_\${repNo}`,
			"class": "form-control",
			"row": "3",
			"required": "required",
			"style": "border: solid 2px #1E90FF;"
		}).val(repContent)
	)
	$(`#\${repModBtn}`).css("display","none");
	$(`#\${repDelBtn}`).css("display","none");
	$(`#\${mbbtn}`).append(
		$("<button>").attr({
			"type":"button",
            "class": "btn btn-danger",
            "onclick": `repMod(\${repNo})`,
            "style": "margin:1px; margin-top:30px; padding:4px; padding-right:26px; padding-left:26px;"
		}).html("수정")
	)
	
}



<%-- 댓글 수정  --%>
function repMod(repNo) {
	let conMod = "conMod_" + repNo;
	let repContent = $(`#\${conMod}`).val();
	let repModData = {
			repNo : repNo,
			repContent : repContent
		}
	
	$.ajax({
		type: "PUT",
		url: url,
		data: JSON.stringify(repModData),
		contentType : "application/json;charset=utf-8",
		beforeSend : function(xhr) { 
			xhr.setRequestHeader(header,token);
		},
		success: function (result) {
 			console.log(result)
 			getRep(currentPage);
		},
		error: function (e) { 
			console.log("ERROR : ", e);
		}
	});
	
}

<%-- 댓글 삭제  --%>
function repDelForm(repNo) {
	Swal.fire({
		title: '삭제하시겠습니까?',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '확인',
        cancelButtonText: '취소'
    }).then((result) => {
        if (result.isConfirmed) {
        	$.ajax({
        		type: "DELETE",
        		url: url,
        		data: JSON.stringify({repNo:repNo}),
        		contentType : "application/json;charset=utf-8",
        		beforeSend : function(xhr) { 
        			xhr.setRequestHeader(header,token);
        		},
        		success: function (result) {
         			console.log(result)
         			getRep(1);
        		},
        		error: function (e) { 
        			console.log("ERROR : ", e);
        		}
        	});
        }
    })
}


</script>