<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 8. 5.      박채록      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<style>
	.month{
		background-color: #E6E0F8;
		color: black;
		border-radius: 50%;
		border: 1px solid #E6E0F8;
		width: 38px;
		height: 38px;
		font-size: 12px;
		padding: 5px;
		margin: 5px;
	}
	#searchbox{
		float: right;
	}
	.month:hover{
		background-color: #9F81F7;
		color: white;
	}
	#searchYear{
		margin: 10px;
	}
	
	.income-show{
		text-align: right;
		padding: 20px;
	}
</style>


<div id="searchbox">
	<div id="incomeTitle"></div>
	<a href="${cPath}/iexpert/income" class="btn btn-light waves-effect">전체수입</a>
	<div class="btn-group" role="group">
		<button id="btnGroupVerticalDrop1" type="button"
			class="btn btn-light dropdown-toggle" data-bs-toggle="dropdown"
			aria-haspopup="true" aria-expanded="false">년,월 선택 <i class="mdi mdi-chevron-down"></i>
		</button>
		<div class="dropdown-menu" aria-labelledby="btnGroupVerticalDrop1">
			<table style="text-align: center;" class="monthTB">
				<tr>
					<td colspan="4"><input type="number" value="2022" id="searchYear" style="border: none; margin-bottom: 10px;"></td>
				</tr>
				<tr>
					<td><input type="button" class="month" value="01월"></td>
					<td><input type="button" class="month" value="02월"></td>
					<td><input type="button" class="month" value="03월"></td>
					<td><input type="button" class="month" value="04월"></td>
				</tr>                                                 
				<tr>                                                  
					<td><input type="button" class="month" value="05월"></td>
					<td><input type="button" class="month" value="06월"></td>
					<td><input type="button" class="month" value="07월"></td>
					<td><input type="button" class="month" value="08월"></td>
				</tr>                                                 
				<tr>                                                  
					<td><input type="button" class="month" value="09월"></td>
					<td><input type="button" class="month" value="10월"></td>
					<td><input type="button" class="month" value="11월"></td>
					<td><input type="button" class="month" value="12월"></td>
				</tr>
			</table>
	
		</div>
	</div>
</div>
<h4>${year}년도 매출차트</h4>
<canvas id="myChart" width="1000" height="400" style=" width: 95%; margin: auto;"></canvas>

<h4 style="margin-top: 50px;">${memNick }님의 수입내역</h4>
<c:set value="${revenueList }" var="revenue"/>
<table class="table mb-0" style="text-align: center; width: 95%; margin: auto;">
<thead>
	<tr>
		<th>NO</th>
		<th>상품번호</th>
		<th>상품명</th>
		<th>구매자</th>
		<th>결제금액</th>
		<th>결제일자</th>
	</tr>
</thead>
<tbody>
<c:if test="${not empty revenue }">
<c:forEach items="${revenue }" var="re" varStatus="status">
	<tr>
		<td>${status.count }</td>
		<td>${re.eprodNum }</td>
		<td>${re.eprodName }</td>
		<td>${re.buyerId}</td>
		<td class="income" data-total="${re.accumRevenue }">${re.amount }</td>
		<td>${re.epayDate }</td>

	</tr>
</c:forEach>

</c:if>
<c:if test="${empty revenue }">
<tr>
	<td colspan="6">수입내역이 존재하지 않습니다.</td>
</tr>
</c:if>
</tbody>
</table>

<form method="post" id="exelForm" action="${cPath}/iexpert/expertRevenueExcel">
	<sec:csrfInput/>
	<input type="hidden" name="year" value="2022">
	<input type="button" class="btn btn-link waves-effect" onclick="goExel()" value="수입내역다운">
</form>
<form method="post" id="searchForm">
	<sec:csrfInput/>
	<input type="hidden" name="year">
	<input type="hidden" name="month">
</form>


<div class="income-show"></div>


<script>

function goExel(){
	exelForm.submit();
}
//$("span[value='완료']").css("background-color", "red");
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

console.log("08",eprodMonth08)
	let trleng = $("tbody tr").length;
	console.log (trleng);
	
	let totalIncome = $(".income").last().data("total");
	console.log(totalIncome);
	if(totalIncome==null){
		
	$(".income-show").append("<h3>total : 0 원</h3>");
	}else{
		$(".income-show").append("<h3>total :"+totalIncome+"원</h3>");
	}
	
	$(".month").on("click", function(){
		let month = $(this).val().replace("월","");
		let year = $("#searchYear").val();
		console.log(month);
		$("input[name='year']").attr("value", year);
		$("input[name='month']").attr("value", month);
		$("#searchForm").submit();
	});
	
	
	// 차트를 그럴 영역을 dom요소로 가져온다.
	var chartArea = document.getElementById('myChart').getContext('2d');
	// 차트를 생성한다. 
	var myChart = new Chart(chartArea, {
	    // ①차트의 종류(String)
	    type: 'bar',
	    // ②차트의 데이터(Object)
	    data: {
	        // ③x축에 들어갈 이름들(Array)
	        labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월','8월','9월','10월','11월','12월'],
	        // ④실제 차트에 표시할 데이터들(Array), dataset객체들을 담고 있다.
	        datasets: [{
	            // ⑤dataset의 이름(String)
	            label: '# of Votes',
	            // ⑥dataset값(Array)
	            data: [eprodMonth01, eprodMonth02, eprodMonth03, eprodMonth04, eprodMonth05, eprodMonth06, eprodMonth07, eprodMonth08, eprodMonth09, eprodMonth10, eprodMonth11, eprodMonth12],
	            // ⑦dataset의 배경색(rgba값을 String으로 표현)
	            backgroundColor: 'rgba(255, 99, 132, 0.2)',
	            // ⑧dataset의 선 색(rgba값을 String으로 표현)
	            borderColor: 'rgba(255, 99, 132, 1)',
	            // ⑨dataset의 선 두께(Number)
	            borderWidth: 1
	        }]
	    },
	    // ⑩차트의 설정(Object)
	    options: {
	    	responsive: false,
	        // ⑪축에 관한 설정(Object)
	        scales: {
	            // ⑫y축에 대한 설정(Object)
	        	yAxes : [ {
	    			ticks : {
	    				beginAtZero : true, // 0부터 시작하게 합니다.
	    			}
	    		} ]

	        }
	    }
	});
	
</script>