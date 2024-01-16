<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<table class="table table-bordered">
		<thead>
			<tr>
				<td>일련번호</td>
				<td>상품명</td>
				<td>분류명</td>
				<td>거래처명</td>
				<td>입고일</td>
				<td>구매가</td>
				<td>반매가</td>
				<td>마일리지</td>
			</tr>
		</thead>
		<tbody id="listBody">

		</tbody>
		<tfoot>
			<tr>
				<td colspan="8">
					<div class="pagingArea">
<%-- 						${pagingVO.pagingHTMLBS } --%>
					</div>
					<div id="searchUI" class="d-flex justify-center-center" />
					
						<select name="prodLgu">
							<option value>상품분류</option>
							<c:if test="${not empty lprodList }">
								<c:forEach items="${lprodList }" var="lprod">
									<option value="${lprod['lprodGu'] }">${lprod.lprodNm }</option>
								</c:forEach>
							</c:if>
							<c:if test="${empty lprodList }">
									<option value>분류 없음.</option>
							</c:if>
						</select>
						
						<select name="prodBuyer">
							<option value>상품거래처</option>
							<c:if test="${not empty buyerList }">
								<c:forEach items="${buyerList }" var="buyer">
									<option value="${buyer.buyerId }" class="${buyer.buyerLgu }">${buyer.buyerName }</option>
								</c:forEach>
							</c:if>
							<c:if test="${empty buyerList }">
									<option value>거래처 없음.</option>
							</c:if>
						</select>
						
						<input type="text" name="prodName" placeholder="상품명"/>
						<input id="searchBtn" type="button" value="검색" class="btn btn-success" />
					</div>
				</td>
			</tr>
		</tfoot>
	</table>
	
	<form action="${cPath }/prod/prodList_async.do" id="searchForm">
		<input type="text" name="page" placeholder="page"/>	
		<input type="text" name="prodLgu" placeholder="상품분류"/>
		<input type="text" name="prodBuyer" placeholder="거래처"/>
		<input type="text" name="prodName" placeholder="상품명"/>
	</form>

<script>
		$("[name=prodLgu]").on("change", function(event){
			let selectedLgu = $(this).val();
			let options = $(this).siblings("[name=prodBuyer]").find("option");
			$.each(options, function(idx, opt){
				if(idx == 0 || $(this).hasClass(selectedLgu)){
					$(this).show();
				} else {
					$(this).hide();
				}
			});
// 			prodBuyerTag.find("option:first");
			options[0]
			
		}).val("${pagingVO.detailCondition.prodLgu}").trigger("change");
		$("[name=prodBuyer]").val("${pagingVO.detailCondition.prodBuyer}");
		$("[name=prodName]").val("${pagingVO.detailCondition.prodName}");
	
		$(".pagingArea").on("click", "a", function(event){
			let page = $(this).data("page");
			searchForm.find("[name=page]").val(page);
			searchForm.submit();
		});
						

		let listBody = $("#listBody");
		let pagingArea = $(".pagingArea");
		let searchUI = $("#searchUI");
		
		<c:url value="/prod/prodList_async.do" var="viewURL">
		   <c:param name="what" value="prodId" /> 
		</c:url>
		   
		const VIEWURL = "${viewURL}"; // 상수
		let makeSingleTr = function(index, prod){
		   let aTag = $("<a>").attr({
		               "href":VIEWURL.replace("prodId",prod.prodId)
		            }).text(prod.prodName);
		   return $("<tr>").append(
		            $("<td>").html(prod.rnum)
		            ,$("<td>").html(aTag)
		            ,$("<td>").html(prod.lprodNm)
		            ,$("<td>").html(prod.buyer.buyerName)
		            ,$("<td>").html(prod.prodInsdate)
		            ,$("<td>").html(prod.prodCost)
		            ,$("<td>").html(prod.prodPrice)
		            ,$("<td>").html(prod.prodMileage)
		         );
		   
		}
		
		let searchForm = $("#searchForm").on("submit", function(event){
			event.preventDefault();
			let url = this.action;
			let method = this.method;
			let data = $(this).serialize();
			
			$.ajax({
				url : url,
				method : method,
				data : data,
				dataType : "json",
				success : function(resp, status, jqXHR) {
					let prodList = resp.pagingVO.dataList;
					let pagingVO = resp.pagingVO;
					let trTags = [];
					console.log(prodList);
					console.log(pagingVO);
					
					if(prodList && prodList.length > 0){
						 $(prodList).each(function(index, prod){
				               trTags.push(makeSingleTr(index, prod));
				            });

				         }else{
				            let trTag = $("<tr>").html(
				                        $("<td>").attr("colspan","8")
				                              .html("없음.")
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
			});
			return false;
		}).submit();
		
		
		
		$("#searchBtn").on("click", function(event){
			let inputs = searchUI.find(":input[name]");
			
			$(inputs).each(function(idx, inputs){
				let name = $(this).attr("name");
				let value = $(this).val();
				searchForm.find("[name="+name+"]").val(value);
			});
			
			searchForm.submit();
			
		});
	</script>