<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자         수정내용
* ----------  ---------  -----------------
* 2022. 8. 5.   심민경          최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<style>

	table {
		text-align:center;
	}
</style>

<h3>${memberVO.memName } 님의 상세페이지</h3>
<div class="card-body">
    <!-- Nav tabs -->
    <ul class="nav nav-tabs nav-tabs-custom nav-justified" role="tablist">
        <li class="nav-item">
            <a class="nav-link active" data-bs-toggle="tab" href="#info" role="tab" aria-selected="true">
                <span class="d-block d-sm-none"><i class="fas fa-home"></i></span>
                <span class="d-none d-sm-block">상세정보</span> 
            </a>
        </li>
        <li class="nav-item" onClick="getPayList()">
            <a class="nav-link" data-bs-toggle="tab" href="#payment" role="tab" aria-selected="false" id="getPay">
                <span class="d-block d-sm-none"><i class="far fa-user"></i></span>
                <span class="d-none d-sm-block" onClick="getPayList()">결제</span> 
            </a>
        </li>
        <li class="nav-item" onClick="getRefundList()">
            <a class="nav-link" data-bs-toggle="tab" href="#refund" role="tab" id="getRefund">
                <span class="d-block d-sm-none"><i class="far fa-envelope"></i></span>
                <span class="d-none d-sm-block" onClick="getRefundList()">환불</span>   
            </a>
        </li>
        <li class="nav-item" onClick="getReportList()">
            <a class="nav-link" data-bs-toggle="tab" href="#report" role="tab" id="getReport">
                <span class="d-block d-sm-none"><i class="fas fa-cog"></i></span>
                <span class="d-none d-sm-block" onClick="getReportList()">신고</span>    
            </a>
        </li>
    </ul>

    <!-- Tab panes -->
    <div class="tab-content p-3 text-muted">
        <div class="tab-pane active" id="info" role="tabpanel">
			<table class="table table-bordered">
				<tr>
					<th>회원아이디</th>
					<td>${memberVO.memId }</td>
				</tr>
				<tr>
					<th>회원이름</th>
					<td>${memberVO.memName }</td>
				</tr>
				<tr>
					<th>회원닉네임</th>
					<td>${memberVO.memNick }</td>
				</tr>
				<tr>
					<th>회원메일</th>
					<td>${memberVO.memMail }</td>
				</tr>
				<tr>
					<th>회원전화번호</th>
					<td>${memberVO.memHp }</td>
				</tr>
				<tr>
					<th>신고수</th>
					<td>${memberVO.accumRep }</td>
				</tr>
				<tr>
					<th>접근 권한</th>
					<td>
						<c:forEach items="${memberVO.roleList }" var="role">
							${role.roleName } |
						</c:forEach>
					</td>
				</tr>
			</table>
        </div>
        <div class="tab-pane" id="payment" role="tabpanel">
			<h4>전문가 결제내역</h4>
			<table class="table table-hover mb-0">
				<thead>
					<tr>
						<th>결제번호</th>
						<th>상품명</th>
						<th>금액</th>
						<th>결제수단</th>
						<th>결제여부</th>
						<th>결제일시</th>
					</tr>
				</thead>
				<tbody id="expTbody">

				</tbody>
					<tr>
						<td colspan="8">
							<div class="d-flex justify-content-center expPagingArea">
							
							</div>
						</td>
					</tr>
			</table>
			
			<h4>협업툴 결제내역</h4>
			<table class="table table-hover mb-0">
				<thead>
					<tr>
						<th>결제번호</th>
						<th>상품명</th>
						<th>금액</th>
						<th>결제수단</th>
						<th>결제여부</th>
						<th>결제일시</th>
						<th>서비스종료일</th>
					</tr>  
				</thead>  
				<tbody id="workTbody">

				</tbody>
					<tr>
						<td colspan="8">
							<div class="d-flex justify-content-center workPagingArea">
								
							</div>
						</td>
					</tr>
			</table>
        </div>
        <div class="tab-pane" id="refund" role="tabpanel">
        	<h4>전문가 환불내역</h4>
			<table class="table table-hover mb-0">
				<thead>
					<tr>
						<th>환불번호</th>
						<th>상품번호</th>
						<th>상품명</th>
						<th>환불사유</th>
						<th>환불일시</th>
					</tr>  
				</thead>  
				<tbody id="expRefTbody">

				</tbody>
					<tr>
						<td colspan="8">
							<div class="d-flex justify-content-center expRefPagingArea">
								
							</div>
						</td>
					</tr>
			</table>
			
        	<h4>협업툴 환불내역</h4>
			<table class="table table-hover mb-0">
				<thead>
					<tr>
						<th>환불번호</th>
						<th>상품번호</th>
						<th>상품명</th>
						<th>환불사유</th>
						<th>환불일시</th>
					</tr>  
				</thead>  
				<tbody id="wrokRefTbody">

				</tbody>
					<tr>
						<td colspan="8">
							<div class="d-flex justify-content-center workRefPagingArea">
								
							</div>
						</td>
					</tr>
			</table>
        </div>
        <div class="tab-pane" id="report" role="tabpanel">
			<table class="table table-hover mb-0">
				<thead>
					<tr>
						<th>신고번호</th>
						<th>신고자아이디</th>
						<th>신고일자</th>
					</tr>
				</thead>
				<tbody id="repbody">

				</tbody>
				<tfoot>
					<tr>
						<td colspan="8">
							<div class="d-flex justify-content-center repPagingArea">
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
        </div>
    </div>
</div>

	<input type="hidden" name="expPayPage" id="expPayPage"/>
	<input type="hidden" name="workPayPage" id="workPayPage"/>
	<input type="hidden" name="expRefPage" id="expRefPage"/>
	<input type="hidden" name="workRefPage" id="workRefPage"/>
	<input type="hidden" name="repPage" id="repPage"/>
<script>
const url = "${cPath }/admin/member/${memberVO.memId}";
</script>
<script src = "${cPath }/resources/js/admin/memberView.js"></script>
 