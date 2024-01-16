/**
 * <pre>
 * 
 * </pre>
 * @author 심민경
 * @since 2022. 9. 7.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2022. 9. 7.      작성자명       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */ 

let delBar = new Chart(document.getElementById("delBar"), {
type: 'bar',
data: {
 labels: ['01월', '02월', '03월', '04월', '05월', '06월', '07월', '08월', '09월', '10월', '11월', '12월'],
 datasets: [{
     label: '탈퇴자 수',
     data: [
    	 delMem01
    	 , delMem02
    	 , delMem03
    	 , delMem04
    	 , delMem05
    	 , delMem06
    	 , delMem07
    	 , delMem08
    	 , delMem09
    	 , delMem10
    	 , delMem11
    	 , delMem12
     ],
     borderColor: "rgba(255, 201, 14, 1)",
     backgroundColor: "rgba(255, 201, 14, 0.5)",
     fill: false,
 	}]
},
options: {
 responsive: true,
 hover: {
     mode: 'nearest',
     intersect: true
 },
 plugins: {
  legend: {
    position: 'top',
  }
 },
 scales : {
 	yAxes : [ {
 		ticks : {
 			min : 0,
 			stepSize : 1,
 			fontSize : 12,
 		}
 	} ]
 }
}
});


let delPie = document.getElementById('delPie').getContext('2d');
let delMemPie = new Chart(delPie, {
	type : 'pie', // 차트의 형태
	data : { // 차트에 들어갈 데이터
		labels : [
		//x 축
		'재가입', '서비스 부족', '이용 불편', '이용 빈도 낮음'
		],
		datasets : [{ //데이터
			label : 'test1', //차트 제목
			fill : false, // 선 안쪽을 채우는지 안채우는지
			data : [ 
				reason1
				, reason2
				, reason3
				, reason4
			],
			backgroundColor : [
			//색상
			'rgba(255, 99, 132, 0.2)', 'rgba(54, 162, 235, 0.2)', 'rgba(255, 153, 51, 0.2)', 'rgba(153, 51, 255, 0.2)',
					 ],
			borderColor : [
			//경계선 색상
			'rgba(255, 99, 132, 1)', 'rgba(54, 162, 235, 1)', 'rgba(255, 153, 51, 1)', 'rgba(153, 51, 255, 1)',
					 ],
			borderWidth : 1
		//경계선 굵기
		}]
	},
	options : {
		scales : {
			yAxes : [ {
				ticks : {
					min : 0,
					stepSize : 6,
					fontSize : 12,
				}
			} ]
		}
	}
});