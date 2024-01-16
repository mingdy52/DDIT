<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<style>
a{
	color: black;
}
a.active{
	color: #717CFF;
}
</style>
<sec:authentication property="principal" var="authMember"/>


<h3>expert</h3>
				<ul class="metismenu list-unstyled font-base active" id="left-menu-list">
					<li><a style="color: black;" href="${cPath}/expert">expert상품</a></li><br>
					<sec:authorize access="hasRole('ROLE_USER')">
					<li><a style="color: black;" href="${cPath}/myexpert" href="javascript: void(0);" class="has-arrow">MY EXPERT</a>
						<ul class="sub-menu" aria-expanded="true" id="left-menu-list">
							<li><a href="${cPath}/myexpert" class="myexpert">이용내역</a></li>
							<li><a href="${cPath}/myexpert/wish" class="wish">관심목록</a></li>
							<li><a href="${cPath}/myexpert/review" class="review">후기</a></li>
						</ul>
					</li>
					<br>
					</sec:authorize>	
					
					<sec:authorize access="hasRole('ROLE_EXPERT')">
					<li>I'M EXPERT
						<ul class="sub-menu" aria-expanded="true" id="left-menu-list">
							<li><a href="${cPath}/iexpert/profile" class="profile">프로필</a></li>
							<li><a href="${cPath}/iexpert/prod" class="prod">상품관리</a></li>
							<li><a href="${cPath}/iexpert/request" class="request">요청내역</a></li>
							<li><a href="${cPath}/iexpert/income" class="income">상품수입</a></li>
						</ul>
					</li>
					</sec:authorize>
				</ul>
<script>
// 	const urlArr = ["myexpert", "wish", "review", "profile", "prod", "request", "income"];
	let url = location.href
	console.log("url",url);
	
	let surl = url.substring(url.lastIndexOf("/")+1);
// 	console.log(urlArr.indexOf(surl));
	
// 	if(urlArr.indexOf(surl)==-1){
// 		console.log("surl_len",surl.length);
// 		console.log("surl_index",url.lastIndexOf("/", url.length-surl.length-2 ));
// 		surl = url.substring(url.lastIndexOf("/", url.length-surl.length-2 )+1, url.lastIndexOf("/"));
// 		console.log("surl1111", surl);
// 	}
	
	$(".sub-menu a").removeClass("active");
	$("."+surl).addClass("active");
	
</script>
