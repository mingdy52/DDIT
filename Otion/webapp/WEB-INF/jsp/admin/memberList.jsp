<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 8. 12.   심민경      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
<style>
table {
	text-align: center;
}

#selbox {
	float: left;
	width: 23%;
	height: 30px;
	margin-left: 10px;
}
</style>

<div class="input-group mb-3" id="searchUI">
	<div class="form-floating" id="selbox">
		<select class="form-select" id="floatingSelect"
			aria-label="Floating label select example"
			style="height: 42px; padding-top: 10px; float: none;">
			<option value="">검색분류</option>
			<option value="all">통합</option>
			<option value="id">아이디</option>
			<option value="name">이름</option>
			<option value="nick">닉네임</option>
			<option value="mail">메일</option>
		</select>
	</div>
	<input id="inputData" type="text" class="form-control"
		placeholder="검색어를 입력하세요." aria-label="Recipient's username"
		aria-describedby="basic-addon2"
		onkeyup="if(window.event.keyCode==13){f_search()}" autocomplete="off"/>
	<button onclick="f_search()"
		class="btn btn-primary btn-rounded waves-effect waves-light">검색</button>
</div>

<h3>회원 조회</h3>
<div class="card-body">
	<!-- Nav tabs -->
	<ul class="nav nav-tabs nav-tabs-custom nav-justified" role="tablist">
		<li class="nav-item"><a class="nav-link active"
			data-bs-toggle="tab" href="#all" role="tab" aria-selected="true"
			id="getAllList"> <span class="d-block d-sm-none"><i
					class="fas fa-home"></i></span> <span class="d-none d-sm-block">전체
					회원</span>
		</a></li>
		<li class="nav-item" onClick="getExpList()"><a class="nav-link"
			data-bs-toggle="tab" href="#expert" role="tab" aria-selected="false"
			id="getExpList"> <span class="d-block d-sm-none"><i
					class="far fa-user"></i></span> <span class="d-none d-sm-block">전문가
					회원</span>
		</a></li>
		<li class="nav-item" onClick="getProjList()"><a class="nav-link"
			data-bs-toggle="tab" href="#project" role="tab" id="getProjList">
				<span class="d-block d-sm-none"><i class="far fa-envelope"></i></span>
				<span class="d-none d-sm-block">프로젝트 회원</span>
		</a></li>
		<li class="nav-item" onClick="getBlackList()"><a class="nav-link"
			data-bs-toggle="tab" href="#black" role="tab" id="getBlackList">
				<span class="d-block d-sm-none"><i class="far fa-envelope"></i></span>
				<span class="d-none d-sm-block">블랙리스트</span>
		</a></li>
	</ul>

	<!-- Tab panes -->
	<div class="tab-content p-3 text-muted" style="min-width: 700px;">
		<div class="tab-pane active" id="all" role="tabpanel">
			<table class="table">
				<thead>
					<th>회원아이디</th>
					<th>회원이름</th>
					<th>회원닉네임</th>
					<th>회원메일</th>
					<th>회원전화번호</th>
				</thead>
				<tbody id="allTbody" class="tbody">

				</tbody>
				<tfoot>
					<tr>
						<td colspan="7">
							<div class="d-flex justify-content-center allPagingArea"></div>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
		<div class="tab-pane" id="expert" role="tabpanel">
			<table class="table">
				<thead>
					<th>회원아이디</th>
					<th>회원이름</th>
					<th>회원닉네임</th>
					<th>회원메일</th>
					<th>회원전화번호</th>
				</thead>
				<tbody id="expTbody" class="tbody">

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
		<div class="tab-pane" id="project" role="tabpanel">
			<table class="table">
				<thead>
					<th>회원아이디</th>
					<th>회원이름</th>
					<th>회원닉네임</th>
					<th>회원메일</th>
					<th>회원전화번호</th>
				</thead>
				<tbody id="projTbody" class="tbody">

				</tbody>
				<tfoot>
					<tr>
						<td colspan="7">
							<div class="d-flex justify-content-center projPagingArea">

							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
		<div class="tab-pane" id="black" role="tabpanel">
			<table class="table">
				<thead>
					<th>회원아이디</th>
					<th>회원이름</th>
					<th>차단사유</th>
					<th>차단일</th>
				</thead>
				<tbody id="blackTbody" class="tbody">
				</tbody>
				<tfoot>
					<tr>
						<td colspan="7">
							<div class="d-flex justify-content-center blackPagingArea">

							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
</div>
<div id="exel">
	<a class="btn btn-primary" href="member/excel"> <i
		class="ri-file-excel-2-line"></i> 엑셀 다운로드
	</a>
</div>

<div class="card">
	<div class="card-body">

		<div class="row text-center" style="min-width: 700px;">
			<div class="col-4">
				<h5 class="mb-0">${roleNum.roleUser }</h5>
				<p class="text-muted text-truncate">전체회원</p>
			</div>
			<div class="col-4">
				<h5 class="mb-0">${roleNum.roleExpert }</h5>
				<p class="text-muted text-truncate">전문가 회원</p>
			</div>
			<div class="col-4">
				<h5 class="mb-0">${roleNum.roleProject }</h5>
				<p class="text-muted text-truncate">프로젝트회원</p>
			</div>
			<canvas id="doughnut" height="432" width="826"
				style="display: block; width: 496px; height: 171px;"
				class="chartjs-render-monitor">
			</canvas>
		</div>
	</div>
</div>

<div class="row">
	<div class="col-xl-6">
		<div class="card">
			<div class="card-body">
				<div class="row text-center">
					<div class="col-4">
						<h5 class="mb-0">${delMemVO.total}</h5>
						<p class="text-muted text-truncate">총탈퇴회원수</p>
					</div>
					<div class="col-4">
						<h5 class="mb-0">${delMemVO.reason1}</h5>
						<p class="text-muted text-truncate">아이디변경을 위해 탈퇴 후 재가입</p>
					</div>
					<div class="col-4">
						<h5 class="mb-0">${delMemVO.reason2}</h5>
						<p class="text-muted text-truncate">이용할 만한 서비스 부족</p>
					</div>
					<div class="col-4">
						<h5 class="mb-0">${delMemVO.reason3}</h5>
						<p class="text-muted text-truncate">사이트 이용 불편</p>
					</div>
					<div class="col-4">
						<h5 class="mb-0">${delMemVO.reason4}</h5>
						<p class="text-muted text-truncate">이용빈도 낮음</p>
					</div>
				</div>

				<canvas id="delPie" height="560" width="826"
					style="display: block; width: 496px; height: 198px;"
					class="chartjs-render-monitor">
				</canvas>
			</div>
		</div>
	</div>
	<div class="col-xl-6">
		<div class="card">
			<div class="card-body">
				<div class="chartjs-size-monitor">
					<div class="chartjs-size-monitor-expand">
						<div class=""></div>
					</div>
					<div class="chartjs-size-monitor-shrink">
						<div class=""></div>
					</div>
				</div>

				<h4>올해 월별 탈퇴자 수</h4>

				<canvas id="delBar" height="432" width="495"
					style="display: block; width: 496px; height: 198px;"
					class="chartjs-render-monitor">
				</canvas>
			</div>
		</div>
		<!-- end col -->
	</div>
</div>







<input type="hidden" name="allPage" id="allPage" /> 
<input type="hidden" name="exPage" id="exPage" /> 
<input type="hidden" name="pjPage" id="pjPage" />
<input type="hidden" name="blackPage" id="blackPage" />
	<script>
		let roleUser = "${roleNum.roleUser }";
		let roleExpert = "${roleNum.roleExpert }";
		let roleProject = "${roleNum.roleProject }";

		let reason1 = '${delMemVO.reason1}';
		let reason2 = '${delMemVO.reason2}';
		let reason3 = '${delMemVO.reason3}';
		let reason4 = '${delMemVO.reason4}';

		let delMem01 = '${delMemVO.delMem01}';
		let delMem02 = '${delMemVO.delMem02}';
		let delMem03 = '${delMemVO.delMem03}';
		let delMem04 = '${delMemVO.delMem04}';
		let delMem05 = '${delMemVO.delMem05}';
		let delMem06 = '${delMemVO.delMem06}';
		let delMem07 = '${delMemVO.delMem07}';
		let delMem08 = '${delMemVO.delMem08}';
		let delMem09 = '${delMemVO.delMem09}';
		let delMem10 = '${delMemVO.delMem10}';
		let delMem11 = '${delMemVO.delMem11}';
		let delMem12 = '${delMemVO.delMem12}';
	</script>
	<script src="${cPath }/resources/js/admin/memberChart.js"></script>
	<script src="${cPath }/resources/js/admin/memberList.js"></script>
	<script src="${cPath }/resources/js/admin/delMember.js"></script>