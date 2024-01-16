<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 8. 13.      이아인      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
#search{
 margin-left: 20px; 
}
#selectDate{
	text-align: center;
}
img{
margin-left:1px;
width: 16px;
hight 15px:
}
</style>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<div id="selectDate">
<form id="dateSearch">
날짜 선택 <input type="text" id="startDate" autocomplete="off"> ~ <input type="text" id="endDate" autocomplete="off">
<button class="btn btn-primary btn-rounded waves-effect waves-light"  id="search" data-name="ex">검색</button>
<button class="btn btn-secondary btn-rounded waves-effect waves-light" type="reset"  id="reset">초기화</button>
</form>
</div>
<br><br><br>
<div class="card-body">
    <!-- Nav tabs -->
    <ul class="nav nav-tabs nav-tabs-custom nav-justified" role="tablist">
        <li class="nav-item">
            <a class="nav-link active" data-bs-toggle="tab" href="#expRefList" role="tab" aria-selected="true" id="expertRef">
                <span class="d-block d-sm-none"><i class="fas fa-home"></i></span>
                <span class="d-none d-sm-block">expert환불내역</span> 
            </a>
        </li>
        <li class="nav-item" onClick="coopRefList()">
            <a class="nav-link" data-bs-toggle="tab" href="#copRefList" role="tab" aria-selected="false" id="coopRef">
                <span class="d-block d-sm-none"><i class="far fa-user"></i></span>
                <span class="d-none d-sm-block" >협업툴환불내역</span> 
            </a>
        </li>
    </ul>
     <!-- Tab panes -->
    <div class="tab-content p-3 text-muted">
        <div class="tab-pane active" id="expRefList" role="tabpanel">
		
			<table class="table">
				<thead>
					<tr>
						<th>NO</th>
						<th>환불번호</th>
						<th>상품명</th>
						<th>환불금액</th>
						<th>환불일시</th>
						<th>환불수단</th>
						<th>환불사유</th>
					</tr>
				</thead>
				<tbody id="expTbody">

				</tbody>
				<tfoot>
					<tr>
						<td colspan="7">
							<div class="d-flex justify-content-center" id="expPagingArea">
							
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
        </div>
        <div class="tab-pane" id="copRefList" role="tabpanel">
        	
        		<table class="table">
				<thead>
					<tr>
						<th>NO</th>
						<th>환불번호</th>
						<th>상품명</th>
						<th>환불금액</th>
						<th>환불일시</th>
						<th>환불수단</th>
						<th>환불사유</th>
					</tr>
				</thead>
				<tbody id="coopTbody">

				</tbody>
				<tfoot>
					<tr>
						<td colspan="9">
							<div class="d-flex justify-content-center" id="coopPagingArea">
							
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
        </div>
 </div> 
</div>
<form id="pageForm" action="${cPath }/member">
   <input type="hidden"  name="exPage" placeholder="page"/>
   <input type="hidden" name="coopPage" placeholder="page"/>
   <input type="hidden" name="startDate" placeholder="start"/>
   <input type="hidden" name="endDate" placeholder="end"/>
</form> 
<script>
let dateImgURL="${cPath}/resources/images/date.png";
</script>
<script src="${cPath }/resources/js/member/memRefund.js"></script>