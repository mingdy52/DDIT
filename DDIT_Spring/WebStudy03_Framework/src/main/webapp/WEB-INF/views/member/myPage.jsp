<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<table class="table table-bordered">
	<tr>
		<th>회원아이디</th>
		<td>${member.memId }</td>
	</tr>
	<tr>
		<th>비밀번호</th>
		<td>${member.memPass }</td>
	</tr>
	<tr>
		<th>회원명</th>
		<td>${member.memName }</td>
	</tr>
	<tr>
		<th>주민번호1</th>
		<td>${member.memRegno1 }</td>
	</tr>
	<tr>
		<th>주민번호2</th>
		<td>${member.memRegno2 }</td>
	</tr>
	<tr>
		<th>생일</th>
		<td>${member.memBir }</td>
	</tr>
	<tr>
		<th>우편번호</th>
		<td>${member.memZip }</td>
	</tr>
	<tr>
		<th>주소1</th>
		<td>${member.memAdd1 }</td>
	</tr>
	<tr>
		<th>주소2</th>
		<td>${member.memAdd2 }</td>
	</tr>
	<tr>
		<th>집전화번호</th>
		<td>${member.memHometel }</td>
	</tr>
	<tr>
		<th>회사번호</th>
		<td>${member.memComtel }</td>
	</tr>
	<tr>
		<th>휴대폰</th>
		<td>${member.memHp }</td>
	</tr>
	<tr>
		<th>이메일</th>
		<td>${member.memMail }</td>
	</tr>
	<tr>
		<th>직업</th>
		<td>${member.memJob }</td>
	</tr>
	<tr>
		<th>취미</th>
		<td>${member.memLike }</td>
	</tr>
	<tr>
		<th>기념일</th>
		<td>${member.memMemorial }</td>
	</tr>
	<tr>
		<th>기념일자</th>
		<td>${member.memMemorialday }</td>
	</tr>
	<tr>
		<th>마일리지</th>
		<td>${member.memMileage }</td>
	</tr>
	<tr>
		<th>탈퇴여부</th>
		<td>${member.memDelete }</td>
	</tr>
	
	<tr>
		<td colspan="2">
			<input type="button" class="btn btn-primary updateBtn" value="수정"/>
			<input type="button" class="btn btn-warning deleteBtn" data-bs-toggle="modal" data-bs-target="#exampleModal" value="탈퇴"/>
		</td>
	</tr>
</table>
			<table>

				<thead>
					<tr>
						<th>구매 기록</th>
					</tr>
					<tr>
						<th>상품명</th>
						<th>분류명</th>
						<th>거래처명</th>
						<th>구매가</th>
						<th>판매가</th>
					</tr>
				</thead>
				<tbody>
	               <c:set var="buyList" value="${member.buyList}"/>
	               <c:if test="${not empty buyList }">
	                  <c:forEach items="${buyList }" var="prod">
	                     <tr>
	                        <td>
	                        
	                        <c:url value="/prod/prodView.do" var="prodViewURL">
<!-- 	                        	url 리다이렉팅 구조. 재작성 구조. 나중에 클라이언트 사이드로 알아서 바뀜 -->
								<c:param name="what" value="${prod['prodId'] }" />
							</c:url>
							
	                        <a href="${prodViewURL }">${prod.prodName }</a>
	                        </td>
	                        <td>${prod.lprodNm }</td>
	                        <td>${prod.buyer.buyerName }</td>
	                        <td>${prod.prodCost }</td>
	                        <td>${prod.prodPrice }</td>
	                     </tr>
	                  </c:forEach>
	               </c:if>
	               <c:if test="${empty buyList }">
	                  <tr>
	                     <td colspan="5"> 구매내역 없음.</td>
	                  </tr>
	               </c:if>
            	</tbody>
			</table>
		</td>
	</tr>
</table>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-scrollable modal-lg" >
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">탈퇴하기</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
        <form action="${cPath }/member/memberDelete.do" method="post">
      	<div class="modal-body">
        	<input type="password" class="form-control" name="password" placeholder="비밀번호를 입력해 주세요."> 
	      </div>
	      <div class="modal-footer">
	        <button type="submit" class="btn btn-warning deleteBtn">탈퇴하기</button>
	        <button type="button" class="btn btn-primary" data-bs-dismiss="modal">생각해보기</button>
    	  </div>
        </form>
    </div>
  </div>
</div>

<script>
	$(".updateBtn").on("click", function(event){
		location.href="${cPath}/member/memberUpdate.do";
	});
	
	$("#exampleModal").on("hidden.bs.modal", function(event){
		$(this).find("form").get(0).reset();
	});
		   
		   //element 꺼낼려고 get(0) 안하면 reset이 되지않느다.


</script>