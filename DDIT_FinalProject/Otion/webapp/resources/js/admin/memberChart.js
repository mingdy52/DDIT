/**
 * <pre>
 * 
 * </pre>
 * @author 심민경
 * @since 2022. 8. 12.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2022. 8. 12.      작성자명       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */ 


let doughnut = document.getElementById('doughnut').getContext('2d');
let myChart = new Chart(doughnut, {
    type: 'pie', // 차트의 형태
    data: { // 차트에 들어갈 데이터
        labels: [
            //x 축
            '일반회원', '전문가 회원', '프로젝트회원'
        ],
        datasets: [
            { //데이터
                label: 'test1', //차트 제목
                fill: false, // 선 안쪽을 채우는지 안채우는지
                data: [
                	roleUser,roleExpert,roleProject
                ],
                backgroundColor: [
                    //색상
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                ],
                borderColor: [
                    //경계선 색상
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                ],
                borderWidth: 1 //경계선 굵기
            }
        ]
    },
	options: {
		scales: {
			yAxes: [{
				ticks: {
					min: 0,
					stepSize : 6,
					fontSize : 12,
				}
			}]
		}
	}
});

