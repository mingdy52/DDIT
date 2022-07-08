<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script
	src="${cPath }/resources/js/jQuery.validator/jquery.validate.min.js"></script>

<h4>상품 등록</h4>

<form method="post" id="insertForm" enctype="multipart/form-data">
<table class="table table-bordered">
	<tr>
		<th>상품코드</th>
		<td>
			<input type="text" class="form-control" name="prodId"
				 value="${prod.prodId }" readonly/>
			<span class="error">${errors["prodId"] }</span>
<!-- 			<label id="prodName-error" class="error" for="prodName">상품명 필수</label> -->
		</td>
	</tr>
	<tr>
		<th>상품명</th>
		<td>
			<input type="text" class="form-control" name="prodName"
				value="${prod.prodName }" />
			<span class="error">${errors["prodName"] }</span>
		</td>
	</tr>
	<tr>
		<th>상품분류</th>
		<td>
			<select name="prodLgu"  class="form-control">
				<option value>상품분류</option>
				<c:forEach items="${lprodList }" var="lprod">
					<option value="${lprod['lprodGu'] }">${lprod.lprodNm }</option>
				</c:forEach>
			</select>
			<span class="error">${errors["prodLgu"] }</span>
		</td>
	</tr>
	<tr>
		<th>거래처</th>
		<td>
			<select name="prodBuyer"  class="form-control">
				<option value>상품거래처</option>
				<c:forEach items="${buyerList }" var="buyer">
					<option value="${buyer.buyerId }" class="${buyer.buyerLgu }">${buyer.buyerName }</option>
				</c:forEach>
			</select>
			<span class="error">${errors["prodBuyer"] }</span>
		</td>
	</tr>
	<tr>
		<th>구매가</th>
		<td>
			<input type="number" class="form-control" name="prodCost"
				value="${prod.prodCost }" />
			<span class="error">${errors["prodCost"] }</span>
		</td>
	</tr>
	<tr>
		<th>판매가</th>
		<td>
			<input type="number" class="form-control" name="prodPrice"
				value="${prod.prodPrice }" />
			<span class="error">${errors["prodPrice"] }</span>
		</td>
	</tr>
	<tr>
		<th>할인가</th>
		<td>
			<input type="number" class="form-control" name="prodSale"
			value="${prod.prodSale }" />
			<span class="error">${errors["prodSale"] }</span>
		</td>
	</tr>
	<tr>
		<th>상품정보요약</th>
		<td>
			<input type="text" class="form-control" name="prodOutline"
				value="${prod.prodOutline }" />
			<span class="error">${errors["prodOutline"] }</span>
		</td>
	</tr>
	<tr>
		<th>상세정보</th>
		<td>
			<input type="text" class="form-control" name="prodDetail"
				value="${prod.prodDetail }" />
			<span class="error">${errors["prodDetail"] }</span>
		</td>
	</tr>
	<tr>
		<th>이미지</th>
		<td>
			<input type="file" class="form-control" name="prodImage" />
			<span class="error">${errors["prodImg"] }</span>
		</td>
	</tr>
	<tr>
		<th>총재고</th>
		<td>
			<input type="number" class="form-control" name="prodTotalstock" 
				value="${prod.prodTotalstock }" />
			<span class="error">${errors["prodTotalstock"] }</span>
		</td>
	</tr>
	<tr>
		<th>입고일</th>
		<td>
			<input type="date" class="form-control" name="prodInsdate"
				value="${prod.prodInsdate }" />
			<span class="error">${errors["prodInsdate"] }</span>
		</td>
	</tr>
	<tr>
		<th>적정재고</th>
		<td>
			<input type="number" class="form-control" name="prodProperstock" 
				value="${prod.prodProperstock }" />
			<span class="error">${errors["prodProperstock"] }</span>
		</td>
	</tr>
	<tr>
		<th>크기</th>
		<td>
			<input type="text" class="form-control" name="prodSize"
				value="${prod.prodSize }" />
			<span class="error">${errors["prodSize"] }</span>
		</td>
	</tr>
	<tr>
		<th>색상</th>
		<td>
			<input type="text" class="form-control" name="prodColor"
				value="${prod.prodColor }" />
			<span class="error">${errors["prodColor"] }</span>
		</td>
	</tr>
	<tr>
		<th>배송방법</th>
		<td>
			<input type="text" class="form-control" name="prodDelivery"
				value="${prod.prodDelivery }" />
			<span class="error">${errors["prodDelivery"] }</span>
		</td>
	</tr>
	<tr>
		<th>단위</th>
		<td>
			<input type="text" class="form-control" name="prodUnit"
				value="${prod.prodUnit }" />
			<span class="error">${errors["prodUnit"] }</span>
		</td>
	</tr>
	<tr>
		<th>입고량</th>
		<td>
			<input type="number" class="form-control" name="prodQtyin"
				value="${prod.prodQtyin }" />
			<span class="error">${errors["prodQtyin"] }</span>
		</td>
	</tr>
	<tr>
		<th>판매량</th>
		<td>
			<input type="number" class="form-control" name="prodQtysale"
				value="${prod.prodQtysale }" />
			<span class="error">${errors["prodQtysale"] }</span>
		</td>
	</tr>
	<tr>
		<th>마일리지</th>
		<td>
			<input type="number" class="form-control" name="prodMileage"
				value="${prod.prodMileage }" />
			<span class="error">${errors["prodMileage"] }</span>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" class="btn btn-primary" value="저장" />
			<input type="reset" class="btn btn-danger" value="취소" />
		</td>
	</tr>


</form>

<script>

	$("#insertForm").validate({
		rules:{
			prodName:"required"
			, prodLgu:"required"
			, prodBuyer:"required"
		}, messages : {
			prodName:"상품명 필수"
			, prodLgu:"분류 필수"
			, prodBuyer:"거래처 필수"
		}
	});

	let prodBuyerTag = $("[name=prodBuyer]").val("${prod.prodBuyer}");
	$("[name=prodLgu]").on("change", function(event){
		let selectedLgu = $(this).val();
		let options = prodBuyerTag.find("option");
		$.each(options, function(idx, opt){
			if(!selectedLgu||idx==0 || $(this).hasClass(selectedLgu)){
				$(this).show();
			}else{
				$(this).hide();
			}
		});
		
	}).val("${prod.prodLgu}").trigger("change");
</script>
</body>
</html>