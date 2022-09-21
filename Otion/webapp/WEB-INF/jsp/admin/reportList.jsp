<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 8. 5.      작성자명      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<style>
	table {
		text-align:center;
	}
</style>
<h3>신고 조회</h3>

<div class="card-body">
	<!-- Nav tabs -->
	<ul class="nav nav-tabs nav-tabs-custom nav-justified" role="tablist">
		<li class="nav-item">
			<a class="nav-link active"
				data-bs-toggle="tab" href="#member" role="tab" aria-selected="true" id="getAllList"> 
				<span class="d-block d-sm-none"> 
					<i class="fas fa-home"></i>
				</span> <span class="d-none d-sm-block">회원</span>
			</a>
		</li>
		<li class="nav-item" onClick="getExpList()">
			<a class="nav-link" data-bs-toggle="tab" href="#expert" role="tab" 
				aria-selected="true" id="getAllList"> 
				<span class="d-block d-sm-none"> 
					<i class="fas fa-home"></i>
				</span> <span class="d-none d-sm-block">전문가</span>
			</a>
		</li>
		<li class="nav-item" onClick="getCooList()"><a class="nav-link" data-bs-toggle="tab"
			href="#cooBoard" role="tab" aria-selected="false">
				<span class="d-block d-sm-none"> <i class="far fa-user"></i>
			</span> <span class="d-none d-sm-block">협업 게시판</span>
		</a></li>
		<li class="nav-item" onClick="getfreeList()">
			<a class="nav-link" data-bs-toggle="tab"
				href="#freeBoard" role="tab"> 
				<span class="d-block d-sm-none"> <i class="far fa-envelope"></i></span> 
				<span class="d-none d-sm-block">자유 게시판</span>
			</a>
		</li>
	</ul>

	<!-- Tab panes -->
	<div class="tab-content p-3 text-muted">
		<div class="tab-pane active" id="member" role="tabpanel">
			<table class="table table-hover mb-0">
				<thead>
					<th>순번</th>
					<th>회원아이디</th>
					<th>회원이름</th>
					<th>회원닉네임</th>
					<th>회원메일</th>
					<th>신고수</th>
				</thead>
				<tbody id="memTbody">

				</tbody>
				<tfoot>
					<tr>
						<td colspan="7">
							<div class="d-flex justify-content-center memPagingArea"></div>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
		<div class="tab-pane" id="expert" role="tabpanel">
			<table class="table table-hover mb-0">
				<thead>
					<th>순번</th>
					<th>전문가아이디</th>
					<th>전문가이름</th>
					<th>전문가닉네임</th>
					<th>전문가메일</th>
					<th>신고수</th>
				</thead>
				<tbody id="expTbody">

				</tbody>
				<tfoot>
					<tr>
						<td colspan="7">
							<div class="d-flex justify-content-center expPagingArea"></div>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
		<div class="tab-pane" id="cooBoard" role="tabpanel">
			<table class="table table-hover mb-0">
				<thead>
					<th>순번</th>
					<th>게시글번호</th>
					<th>게시글제목</th>
					<th>작성자</th>
					<th>작성일자</th>
					<th>신고수</th>
				</thead>
				<tbody id="cooTbody">

				</tbody>
				<tfoot>
					<tr>
						<td colspan="7">
							<div class="d-flex justify-content-center cooPagingArea"></div>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
		<div class="tab-pane" id="freeBoard" role="tabpanel">
			<table class="table table-hover mb-0">
				<thead>
					<th>순번</th>
					<th>게시글번호</th>
					<th>게시글제목</th>
					<th>작성자</th>
					<th>작성일자</th>
					<th>신고수</th>
				</thead>
				<tbody id="freeTbody">

				</tbody>
				<tfoot>
					<tr>
						<td colspan="7">
							<div class="d-flex justify-content-center freePagingArea">

							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
</div>

<input type="hidden" id="memPage"  name="memPage" /> 
<input type="hidden" id="exPage"   name="exPage" /> 
<input type="hidden" id="cooPage"  name="cooPage" /> 
<input type="hidden" id="freePage" name="freePage" />

<script src="${cPath }/resources/js/admin/reportList.js"></script>