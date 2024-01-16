<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<script
	src="${cPath }/resources/js/jQuery.validator/jquery.validate.min.js"></script>

<h4>상품 등록</h4>

<form:form modelAttribute="prod" method="post" id="insertForm" enctype="multipart/form-data">
<table class="table table-bordered">
	<tr>
		<th><spring:message code="prod.prodId"/></th>
		<td>
			<form:input type="text" class="form-control" path="prodId" readonly="true"/>
			<form:errors path="prodId" element="span"/>
		</td>
	</tr>
	<tr>
		<th><spring:message code="prod.prodName"/></th>
		<td>
			<form:input type="text" class="form-control" path="prodName" />
			<form:errors path="prodName" element="span"/>
		</td>
	</tr>
	<tr>
		<th><spring:message code="prod.prodLgu" /></th>
		<td>
			<form:select path="prodLgu" class="form-control">
				<form:option value="" label="상품분류" />
				<c:forEach items="${lprodList }" var="lprod">
					<form:option label="${lprod.lprodNm }" value="${lprod.lprodGu }"/>	
				</c:forEach>			
			</form:select>
			<form:errors path="prodLgu" element="span" cssClass="error"/>
		</td>
	</tr>
	<tr>
		<th><spring:message code="prod.prodBuyer" /></th>
		<td>
			<form:select path="prodBuyer" class="form-control">
				<form:option value="" label="상품거래처" />
				<c:forEach items="${buyerList }" var="buyer">
					<form:option value="${buyer.buyerId }" label="${buyer.buyerName }" cssClass="${buyer.buyerLgu }" />
				</c:forEach>			
			</form:select>
			<form:errors path="prodBuyer" element="span" cssClass="error"/>
		</td>
	</tr>
	<tr>
		<th><spring:message code="prod.prodCost"/></th>
		<td>
			<form:input type="number" class="form-control" path="prodCost" />
			<form:errors path="prodCost" element="span"/>
		</td>
	</tr>
	<tr>
		<th><spring:message code="prod.prodPrice"/></th>
		<td>
			<form:input type="number" class="form-control" path="prodPrice" />
			<form:errors path="prodPrice" element="span"/>
		</td>
	</tr>
	<tr>
		<th><spring:message code="prod.prodSale"/></th>
		<td>
			<form:input type="number" class="form-control" path="prodSale" />
			<form:errors path="prodSale" element="span"/>
		</td>
	</tr>
	<tr>
		<th><spring:message code="prod.prodOutline"/></th>
		<td>
			<form:input type="text" class="form-control" path="prodOutline" />
			<form:errors path="prodOutline" element="span"/>
		</td>
	</tr>
	<tr>
		<th><spring:message code="prod.prodDetail"/></th>
		<td>
			<form:input type="text" class="form-control" path="prodDetail" />
			<form:errors path="prodDetail" element="span"/>
		</td>
	</tr>
	<tr>
		<th><spring:message code="prod.prodImg"/></th>
		<td>
			<form:input type="file" class="form-control" path="prodImage" />
			<form:errors path="prodImage" element="span"/>
		</td>
	</tr>
	<tr>
		<th><spring:message code="prod.prodTotalstock"/></th>
		<td>
			<form:input type="number" class="form-control" path="prodTotalstock" />
			<form:errors path="prodTotalstock" element="span"/>
		</td>
	</tr>
	<tr>
		<th><spring:message code="prod.prodInsdate"/></th>
		<td>
			<form:input type="date" class="form-control" path="prodInsdate" />
			<form:errors path="prodInsdate" element="span"/>
		</td>
	</tr>
	<tr>
		<th><spring:message code="prod.prodProperstock"/></th>
		<td>
			<form:input type="number" class="form-control" path="prodProperstock" />
			<form:errors path="prodProperstock" element="span"/>
		</td>
	</tr>
	<tr>
		<th><spring:message code="prod.prodSize"/></th>
		<td>
			<form:input type="text" class="form-control" path="prodSize" />
			<form:errors path="prodSize" element="span"/>
		</td>
	</tr>
	<tr>
		<th><spring:message code="prod.prodColor"/></th>
		<td>
			<form:input type="text" class="form-control" path="prodColor" />
			<form:errors path="prodColor" element="span"/>
		</td>
	</tr>
	<tr>
		<th><spring:message code="prod.prodDelivery"/></th>
		<td>
			<form:input type="text" class="form-control" path="prodDelivery" />
			<form:errors path="prodDelivery" element="span"/>
		</td>
	</tr>
	<tr>
		<th><spring:message code="prod.prodUnit"/></th>
		<td>
			<form:input type="text" class="form-control" path="prodUnit" />
			<form:errors path="prodUnit" element="span"/>
		</td>
	</tr>
	<tr>
		<th><spring:message code="prod.prodQtyin"/></th>
		<td>
			<form:input type="number" class="form-control" path="prodQtyin" />
			<form:errors path="prodQtyin" element="span"/>
		</td>
	</tr>
	<tr>
		<th><spring:message code="prod.prodQtysale"/></th>
		<td>
			<form:input type="number" class="form-control" path="prodQtysale" />
			<form:errors path="prodQtysale" element="span"/>
		</td>
	</tr>
	<tr>
		<th><spring:message code="prod.prodMileage"/></th>
		<td>
			<form:input type="number" class="form-control" path="prodMileage" />
			<form:errors path="prodMileage" element="span"/>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<form:button type="submit" class="btn btn-primary">저장</form:button>
			<form:button type="reset" class="btn btn-danger">취소</form:button>
		</td>
	</tr>


</form:form>

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