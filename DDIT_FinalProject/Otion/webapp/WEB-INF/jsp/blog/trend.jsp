<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 8. 5.   심민경      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="trendData"></div>

<script>
const trendtUrl = "${cPath }/blog/trendList";
let trendData = document.querySelector('.trendData');

(trendBox= function() {
	const xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() { 
	  if (xhr.readyState === xhr.DONE) { 
	    if (xhr.status === 200 || xhr.status === 201) {
	      let postList = xhr.response; // 다음페이지의 정보
// 	      console.log("postList", postList);
	   // infinite에 list를 더해주기
	   	  for(let i = 0; i < postList.length; i++) {
	   		trendData.append(mkList(postList[i]));
	   	  }
// 	      console.log("fullContent", fullContent);
	      oneTime = false; // oneTime을 다시 false로 돌려서 madeBox를 불러올 수 있게 해주기
	      
	    } else {
	      console.error(xhr.response);
	    }
	  }
	};
	xhr.open('GET', trendtUrl); // 다음페이지의 정보를 get
	xhr.send();
	xhr.responseType = "json";
	
})();

</script>
<script src="${cPath }/resources/js/blog/blogFunction.js"></script>