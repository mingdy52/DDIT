/**
 * <pre>
 * 
 * </pre>
 * @author 심민경
 * @since 2022. 8. 13.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2022. 8. 13.      작성자명       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */ 


let searchForm = $("#searchForm");

// 승인 변경시 데이터 가져오기
$("[name=exApprCode]").on("change", function(event){
	let selectedCode = $(this).val();
	$("[name=apprCode]").val(selectedCode);
	searchForm.submit();
});

// 페이징 처리
$(".pagingArea").on("click", "a", function(event){
	let page = $(this).data("page");
	searchForm.find("[name=page]").val(page);
	searchForm.submit();
});

// 검색
let searchUI = $("#searchUI");
function f_search(){
	searchForm.get(0).reset();
	let inputs = searchUI.find(":input[name]");
	$(inputs).each(function(idx, input){
	   let name = $(this).attr("name");
	   let value = $(this).val();
	   searchForm.find("[name="+name+"]").val(value);
	   
	});
	searchForm.submit();
}