/**
 * <pre>
 * 
 * </pre>
 * @author 심민경
 * @since 2022. 8. 19.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2022. 8. 19. 심민경       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */ 

	
	let pie = document.getElementById('pie').getContext('2d');
	let myPie = new Chart(pie, {
		type : 'pie', // 차트의 형태
		data : { // 차트에 들어갈 데이터
			labels : [
			//x 축
			'수수료 매출', '협업툴 매출'
			],
			datasets : [ { //데이터
				label : 'test1', //차트 제목
				fill : false, // 선 안쪽을 채우는지 안채우는지
				data : [ eprodRevenue, workRevenue ],
				backgroundColor : [
				//색상
				'rgba(255, 99, 132, 0.2)', 'rgba(54, 162, 235, 0.2)',
						 ],
				borderColor : [
				//경계선 색상
				'rgba(255, 99, 132, 1)', 'rgba(54, 162, 235, 1)',
						 ],
				borderWidth : 1
			//경계선 굵기
			} ]
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
	
	let myBar = new Chart(document.getElementById("bar"), {
	    type: 'bar',
	    data: {
	        labels: ['01월', '02월', '03월', '04월', '05월', '06월', '07월', '08월', '09월', '10월', '11월', '12월'],
	        datasets: [{
	            label: '수수료매출',
	            data: [
	                eprodMonth01
	                , eprodMonth02
	                , eprodMonth03
	                , eprodMonth04
	                , eprodMonth05
	                , eprodMonth06
	                , eprodMonth07
	                , eprodMonth08
	                , eprodMonth09
	                , eprodMonth10
	                , eprodMonth11
	                , eprodMonth12
	            ],
	            borderColor: "rgba(255, 201, 14, 1)",
	            backgroundColor: "rgba(255, 201, 14, 0.5)",
	            fill: false,
	        }, {
	            label: '협업툴매출',
	            data: [
	                workMonth01
	                , workMonth02
	                , workMonth03
	                , workMonth04
	                , workMonth05
	                , workMonth06
	                , workMonth07
	                , workMonth08
	                , workMonth09
	                , workMonth10
	                , workMonth11
	                , workMonth12
	            ],
	            borderColor: "rgba(154, 248, 53, 1)",
	            backgroundColor: "rgba(154, 248, 53, 0.5)",
	            fill: false,
	        }]
	    },
	    options: {
	        responsive: true,
	        title: {
	            display: true,
	            text: '월별 매출'
	        },
	        hover: {
	            mode: 'nearest',
	            intersect: true
	        },
	        scales: {
				yAxes: [{
					ticks: {
						min: 0,
						stepSize : 10000,
						fontSize : 14,
					}
				}]
			}
	    }
	    
	});