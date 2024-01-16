<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h4>거래처 목록</h4>
<table class="table table-bordered">
	<thead>
		<tr>
			<td>순번</td>
			<td>거래처 코드</td>
			<td>거래처명</td>
			<td>분류명</td>
		</tr>
	</thead>
	<tbody id="listBody">
	</tbody>
	<tfoot>
		<tr>
			<td colspan="4">
				<div class="d-flex justify-content-center pagingArea"></div>
			</td>
		</tr>
	</tfoot>
</table>
<form id="searchForm">
	<input name="page" />
</form>

<script>
	
	let mkBuyerTr = function(index, buyer){
		return $("<tr>").append(
					$("<td>").html(buyer.rnum)
					, $("<td>").html(buyer.buyerId)
					, $("<td>").html(
							$("<a>").attr("href", $.CPATH+"/buyer/"+ buyer.buyerId).text(buyer.buyerName)	
						)
					, $("<td>").html(buyer.lprodNm)
		);
	}
	
	let searchForm = $("#searchForm");
	$(".pagingArea").on("click", "a", function(event){
		let page = $(this).data("page");
		searchForm.find("[name=page]").val(page);
		searchForm.submit();
	});
	
	$.ajax({
		dataType : "json",
		success : function(resp, status, jqXHR) {
			let buyer = resp;
			let buyerList = buyer.dataList;
			let trTags = [];
			console.log(buyer)
			if(buyerList && buyerList.length > 0 ) {
				$.each(buyerList, function(index, buyer){
					let trTag = mkBuyerTr(index, buyer);
					trTags.push(trTag);
				});
			} else {
				let trTag = $("<tr>").append(
						$("<td>").attr("colspan", "4").html("거래처 정보 없음.")					
					);
				trTags.push(trTag);
			}
			$("#listBody").html(trTags);
			$(".pagingArea").html(buyer.pagingHTMLBS);
		},
		error : function(jqXHR, status, error) {
			console.log(jqXHR);
			console.log(status);
			console.log(error);

		}
	});
	

	
</script>
