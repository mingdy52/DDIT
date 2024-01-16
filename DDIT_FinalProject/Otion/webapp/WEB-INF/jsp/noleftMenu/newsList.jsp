<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 8. 30.   박채록      최초작성
* 2022. 9. 06.	 이아인	 댓글알림 추가
* Copyright (c) 2022 by DDIT All right reserved
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
<style>
#newsbox{
	background-color: #8851ff;
	width: 70%;
	margin-left: 20%;
}
.newscnt{
	width: 88%;
	margin: auto;
	border-radius: 20px;
	padding : 20px;
	margin-bottom: 20px;
	
}

#nescntbox{
	background-color: #F2F2F2;
	padding: 40px 0 30px 0;
}
</style>
<%--     ${newsList} --%>
<div id="newsbox">
<h3 style="font-weight: bold; color: white; text-align: center; padding: 30px 0 30px 0">MY NEWS</h3>

<div id="nescntbox">
<c:if test="${not empty newsList }">
	<c:forEach items="${newsList }" var="news">
		<div class="card newscnt">
			<c:choose>
				<c:when test="${news.newsMsgCode eq 'NEW05'}">
						<c:set value="${news.newsId }" var="newsId" />
						<c:if test="${fn:substring(newsId,0,4) eq 'BLPO'}">
							<h5 class="title"><a href="${cPath}/blog/${news.receiverId}/post/${news.newsId}">${news.comCodeNm }</a></h5>
						</c:if>
						<c:if test="${fn:substring(newsId,0,4) eq 'FREE'}">
							<h5 class="title"><a href="${cPath}/freeBoard/${news.newsId}">${news.comCodeNm }</a></h5>
						</c:if>
						<c:if test="${fn:substring(newsId,0,4) eq 'COOB'}">
							<h5 class="title"><a href="${cPath}/cooboard/${news.newsId}">${news.comCodeNm }</a></h5>
						</c:if>
						<c:if test="${fn:substring(newsId,0,4) eq 'NOTI'}">
							<h5 class="title"><a href="${cPath}/notice/${news.newsId}">${news.comCodeNm }</a></h5>
						</c:if>
				</c:when>
				<c:when test="${news.newsMsgCode eq 'NEW06'}">
					<c:set value="${news.newsId }" var="newsId" />
						<c:if test="${fn:substring(newsId,0,4) eq 'BLPO'}">
							<h5 class="title"><a href="${cPath}/blog/${news.postId}/post/${news.newsId}">${news.comCodeNm }</a></h5>
						</c:if>
						<c:if test="${fn:substring(newsId,0,4) eq 'FREE'}">
							<h5 class="title"><a href="${cPath}/freeBoard/${news.newsId}">${news.comCodeNm }</a></h5>
						</c:if>
						<c:if test="${fn:substring(newsId,0,4) eq 'COOB'}">
							<h5 class="title"><a href="${cPath}/cooboard/${news.newsId}">${news.comCodeNm }</a></h5>
						</c:if>
						<c:if test="${fn:substring(newsId,0,4) eq 'NOTI'}">
							<h5 class="title"><a href="${cPath}/notice/${news.newsId}">${news.comCodeNm }</a></h5>
						</c:if>
				</c:when>
				<c:otherwise>
				<h5 class="title">${news.comCodeNm }</h5>
				</c:otherwise>
			</c:choose>
			<p>${news.newsDate }</p>
		</div>
	</c:forEach>

</c:if>

<c:if test="${empty newsList }">
    	<div class="card newscnt">
			<h5 class="title">알림이 존재하지 않습니다.</h5>
		</div>
    </c:if>
</div>
</div>



