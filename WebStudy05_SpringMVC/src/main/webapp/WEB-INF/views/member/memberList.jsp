<%@page import="java.util.Locale"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>회원아이디</th>
				<th>회원명</th>
				<th>휴대폰</th>
				<th>거주지역</th>
				<th>이메일</th>
				<th>마일리지</th>
			</tr>
		</thead>
		<tbody id="listBody">

		</tbody>
		<tfoot>
			<tr>
				<td colspan="6">
					<div class="d-flex justify-center-center pagingArea">
					
					</div>
					<div id="searchUI" class="d-flex justify-center-center" />
						<select name="searchType">
							<option value="">전체</option>
							<option value="name">이름</option>
							<option value="address">지역</option>
						</select>
						<input type="text" name="searchWord" />
						<input id="searchBtn" type="button" value="검색" class="btn btn-success" />
						
					</div>
				</td>
			</tr>
		
		</tfoot>
	</table>

<form id="searchForm" action="${cPath }/member/memberList.do">
	<input type="text" name="page" />	
	<input type="text" name="searchType"/>	
	<input type="text" name="searchWord"/>	
</form>
	
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-scrollable modal-lg" >
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">회원 상세 정보</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>

<script>
let exampleModal = $('#exampleModal').on('show.bs.modal', function(event){
	console.log(event.currentTarget)
	let url = event.relatedTarget.href;
	console.log(url);
	
	$.ajax({
		url : url,
		dataType : "html",
		success : function(resp, status, jqXHR) {
			exampleModal.find(".modal-body").html(resp)
		},
		error : function(jqXHR, status, error) {
			console.log(jqXHR);
			console.log(status);
			console.log(error);

		}
	});
	
}).on('hidden.bs.modal', function(event){
	console.log(event);
	$(event.target).find(".modal-body").empty();
});


$("[name='searchType']").val("${pagingVO.simpleCondition.searchType}");
$("[name='searchWord']").val("${pagingVO.simpleCondition.searchWord}");

$(".pagingArea").on("click", "a", function(event){
	let page = $(this).data("page");
	searchForm.find("[name=page]").val(page);
	searchForm.submit();
});

let listBody = $("#listBody");
let pagingArea = $(".pagingArea");
let searchUI = $("#searchUI");
<c:url value="/member/memberView.do" var="viewURL">
   <c:param name="who" value="memId"></c:param> //value="memId": 인코딩을 해도 안바뀌는 영어로 쓸것.
</c:url>
const VIEWURL = "${viewURL}";
let makeSingleTr = function(index, member){
   let aTag = $("<a>").attr({
               "href":VIEWURL.replace("memId",member.memId)
               , "data-bs-toggle":"modal"
               , "data-bs-target":"#exampleModal"
            }).text(member.memName);
   return $("<tr>").append(
            $("<td>").html(member.memId)
            ,$("<td>").html(aTag)
            ,$("<td>").html(member.memHp)
            ,$("<td>").html(member.memAdd1)
            ,$("<td>").html(member.memMail)
            ,$("<td>").html(member.memMileage)
         );
   
}
let searchForm = $("#searchForm").on("submit",function(event){
   event.preventDefault();
   let url = this.action;
   let method = this.method;
   let data = $(this).serialize(); 
   
   $.ajax({
      url : url,
      data : data,
      method : method,
      dataType : "json", 
      success : function(resp, status, jqXHR) {
//          console.log(resp.pagingVO.pagingHTMLBS);
         let pagingVO = resp.pagingVO;
         let memberList = resp.pagingVO.dataList;
         let trTags = [];
         
//          $(".pagingArea").empty();
//          $("#ajaxList").empty();

         if(memberList && memberList.length > 0){
            console.log( resp.pagingVO);
            console.log(memberList);
            
            $(memberList).each(function(index,member){
               trTags.push(makeSingleTr(index, member)); //하나하나 넣어준다.
            });

         }else{
            let trTag = $("<tr>").html(
                        $("<td>").attr("colspan","7")
                              .html("회원이 아직 없음.")
                     );
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
   });
   return false;
}).submit();

$("#searchBtn").on("click",function(event){
   searchForm.get(0).reset(); //검색 버튼 누를때마다 기존 조건 초기화 
   let inputs = searchUI.find(":input[name]");
   $(inputs).each(function(idx, input){
      let name = $(this).attr("name");
      let value = $(this).val();
      searchForm.find("[name="+name+"]").val(value);
      
   });
   searchForm.submit();
});

</script>
