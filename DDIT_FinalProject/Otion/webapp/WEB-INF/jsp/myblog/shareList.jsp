<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자         수정내용
* ----------  ---------  -----------------
* 2022. 8. 29.       심민경         최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal.realMember" var="member" />
</sec:authorize>

<style>
p{
	text-align: center;
}

#thumbnail{
	height:180px;
	width:180px;
	margin-top: 0;
}	

#first {
    float: left;
    width:20%;
    height:150px;
    margin: 10px;
    box-sizing: border-box;
}

#second{
    float: left;
    width:70%;
    height:150px;
    margin: 10px;
    box-sizing: border-box;
}
#write{
    margin: 5px;
    float: right;
}

.card-title-desc{
    text-align:left;
}
.card{
	border: none;
	box-shadow: none;
}
.card-body{
	border-bottom: 1px solid #848484;
	height: 250px;
}

#mark-title{
padding-top:30px; 
width: 100%; 
 border-bottom: 3px solid black;
}
#pst{
	color: #2E2E2E;
	font-size: 18px;
}
</style>
<div id="mark-title">
<h3>공유받은 게시글</h3>
</div>
<c:set var="postList" value="${pagingVO.dataList }" />
<c:if test="${not empty postList }">
	<c:forEach items="${postList }" var="post">
	    <div class="card">
	        <div class="card-body" id="parent" style="min-width:1000px;">
	            <div class="row" id="first" style="min-width:220px;">
	                <a href="${cPath }/blog/${post.blogerId }/post/${post.postNum }"><img id="thumbnail" class="img-thumbnail" alt="200x200" src="${cPath }/resources/images/noImg.png" data-holder-rendered="true"></a>
	            </div>
	            <div id="second" style="min-width:500px;">
	            	<a href="${cPath }/blog/${post.blogerId }/post/${post.postNum }"><h4>${post.postTitle }</h4></a>
	            	<div>
		            	<p class="card-title-desc">작성자 ${post.blogerId }   ${post.postDate }</p>
	            	</div>
		            	<c:if test="${post.postPublicYn eq 'N' }">
		            		<i class="ri-rotate-lock-line"></i>
		            	</c:if>
	            	<div>
				        <p class="card-title-desc" id="pst">${post.jsonContents[0].data.text }<br>${post.jsonContents[1].data.text }</p>
	            	</div>
	            </div>
	        </div>
	    </div>
	</c:forEach>
</c:if>
<c:if test="${empty postList }">
공유받은 게시글이 없습니다.
</c:if>
<c:if test="${not empty postList }">
	<div class="d-flex justify-content-center pagingArea">
		${pagingVO.pagingHTMLBS }
	</div>
</c:if>

<form id="searchForm">
	<input type="hidden" name="page" id="page">
	<input type="hidden" name="searchWord" id="searchWord" >
</form>

<script>

let pagingArea = $(".pagingArea");
pagingArea.on('click', function(event) {
	page.value = event.target.innerText;
	searchWord.value = inputData.value;
	searchForm.submit();
});


</script>