/**
 * 게시글 목록 조회(페이징 및 검색 처리)  
 */

let listBody = $("#listBody");
let pagingArea = $(".pagingArea").on("click", "a", function(event){
	let page = $(this).data("page");
	searchForm.find("[name=page]").val(page);
	searchForm.submit();
});
let searchUI = $("#searchUI");

let searchForm = $("#searchForm").ajaxForm({
		dataType : "json" // text, html, json, xml, script -> main type : text, 파일 업로드 처리를 비동기로? (FormData)
		,success : function(resp, status, jqXHR) {
			let pagingVO = resp;
			let boardList = pagingVO.dataList;
			let trTags = [];
			if(boardList && boardList.length>0){
				$(boardList).each(function(index, board){
					trTags.push( makeSingleTr(index, board) );
				});
			}else{
				let trTag = $("<tr>").html(
								$("<td>").attr("colspan", "5")
										 .html("조건에 맞는 글이 없음.")	
							);
				trTags.push(trTag);
			}
			listBody.html(trTags);
			pagingArea.html(pagingVO.pagingHTMLBS);
		},
		error : function(jqXHR, status, error) {
			console.log(jqXHR);
			console.log(status);
			console.log(error);
		}
	}).submit();

let makeSingleTr = function(index, board){
	let aTag = $("<a>").attr("href", $.CPATH+"/board/"+board.boNo)
						.text(board.boTitle);
	return $("<tr>").append(
				$("<td>").html(board.rnum)
				, $("<td>").html(aTag)
				, $("<td>").html(board.boWriter)
				, $("<td>").html(board.boDate)
				, $("<td>").html(board.boHit)
			);
}

let searchBtn = $("#searchBtn").on("click", function(event){
	searchForm.get(0).reset();
	let inputs = searchUI.find(":input[name]");
	$(inputs).each(function(idx, input){
		let name = $(this).attr("name");
		let value = $(this).val();
		searchForm.find("[name="+name+"]").val(value);
	});
	searchForm.submit();
});