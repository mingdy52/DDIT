<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 8. 5.   심민경      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<style>
.inputDate{
	width:300px;
	display:inline-block;
}
#excelBtn{
	float:right
}
</style>
<div id="searchUI" class="d-flex justify-center-center" >
	<div>
		<a href="#"id="previous">previous</a>
		${year } / ${month+1}
		<a href="#" id="next">next</a>
 
		<div>
			<input type="month" id="viewDate" class="form-control">
		</div>
	</div>
</div>
<div class="card-body">
aa
	<h4>${month+1}월 매출</h4>
	<div class="row text-center">
		<div class="col-4">
			<h5 class="mb-0"><fmt:formatNumber value="${eprodRevenue + workRevenue}" pattern="#,###" /></h5>
			
			<p class="text-muted text-truncate">총매출</p>
		</div>
		<div class="col-4">
			<h5 class="mb-0"><fmt:formatNumber value="${eprodRevenue}" pattern="#,###" /></h5>
			<p class="text-muted text-truncate">전문가 수수료매출</p>
		</div>
		<div class="col-4">
			<h5 class="mb-0"><fmt:formatNumber value="${workRevenue}" pattern="#,###" /></h5>
			<p class="text-muted text-truncate">협업툴매출</p>
		</div>
	</div>

	<canvas id="pie" height="171" width="495"
		style="display: block; width: 496px; height: 171px;"
		class="chartjs-render-monitor"></canvas>

</div>

<div class="card-body">
	<div class="chartjs-size-monitor">
		<div class="chartjs-size-monitor-expand">
			<div class=""></div>
		</div>
		<div class="chartjs-size-monitor-shrink">
			<div class=""></div>
		</div>
	</div>

	<h4>${year }년 월간 매출</h4>

	<canvas id="bar" height="198" width="495"
		style="display: block; width: 496px; height: 198px;"
		class="chartjs-render-monitor"></canvas>

</div>

<form action="${cPath }/admin/revenue" method="post" id="searchForm">
	<sec:csrfInput/>
	<input type="hidden" name="paramY" id="paramY" >
	<input type="hidden" name="paramM" id="paramM" >
	<input type="hidden" name="paramYM" id="paramYM" >
</form>
<form action="${cPath }/admin/revenue/revenueExcel" method="post" id="excelForm" >
	<sec:csrfInput/>
	<div>
		<input type="date" name="startDate" id="startDate" class="form-control inputDate"> ~ <input type="date" name="endDate" id="endDate" class="form-control inputDate">
		<div id="excelBtn">
			<button class="btn btn-primary" onClick="f_excel()" >
				<i class="ri-file-excel-2-line"></i> 엑셀 다운로드
			</button>
		</div>
	</div>
</form>


<script>
	let year = '${year }';
	let previousM = '${month}';
	let nextM = '${month+2}';
	
	previous.addEventListener('click', function(event){
		event.preventDefault();
		
		paramY.value = year;
		paramM.value = previousM;
		searchForm.submit();
	});
	
	next.addEventListener('click', function(event){
		event.preventDefault();
		
		paramY.value = year;
		paramM.value = nextM;
		searchForm.submit();
	});
	
	
    $("#viewDate").on('change', function(event){
		paramYM.value = viewDate.value;
		searchForm.submit();
	});
    
    function f_excel(){
    	excelForm.submit();
    }
    
</script>

<script>
	let eprodRevenue = '${eprodRevenue}';
	let workRevenue = '${workRevenue}';
	
	
	let eprodMonth01 = '${monthRevenue.eprodMonth01}';
	let eprodMonth02 = '${monthRevenue.eprodMonth02}';
	let eprodMonth03 = '${monthRevenue.eprodMonth03}';
	let eprodMonth04 = '${monthRevenue.eprodMonth04}';
	let eprodMonth05 = '${monthRevenue.eprodMonth05}';
	let eprodMonth06 = '${monthRevenue.eprodMonth06}';
	let eprodMonth07 = '${monthRevenue.eprodMonth07}';
	let eprodMonth08 = '${monthRevenue.eprodMonth08}';
	let eprodMonth09 = '${monthRevenue.eprodMonth09}';
	let eprodMonth10 = '${monthRevenue.eprodMonth10}';
	let eprodMonth11 = '${monthRevenue.eprodMonth11}';
	let eprodMonth12 = '${monthRevenue.eprodMonth12}';
	                
	let workMonth01 = '${monthRevenue.workMonth01}';
	let workMonth02 = '${monthRevenue.workMonth02}';
	let workMonth03 = '${monthRevenue.workMonth03}';
	let workMonth04 = '${monthRevenue.workMonth04}';
	let workMonth05 = '${monthRevenue.workMonth05}';
	let workMonth06 = '${monthRevenue.workMonth06}';
	let workMonth07 = '${monthRevenue.workMonth07}';
	let workMonth08 = '${monthRevenue.workMonth08}';
	let workMonth09 = '${monthRevenue.workMonth09}';
	let workMonth10 = '${monthRevenue.workMonth10}';
	let workMonth11 = '${monthRevenue.workMonth11}';
	let workMonth12 = '${monthRevenue.workMonth12}';
	
</script>
<script src="${cPath }/resources/js/admin/revenueChart.js"></script>