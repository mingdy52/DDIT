<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자        수정내용
* ----------  ---------  -----------------
* 22.08.05     심민경          최초작성
* Copyright (c) ${year} by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
a{
	color: black;
}
a.active{
	color: #717CFF;
}
</style>      
<div style="min-width:300px;">
	<h3>ADMIN</h3>
	<ul id="left-menu-list" class="sub-menu">
	   <li><a href="${cPath }/admin/member">회원관리</a></li>
	   	<ul id="left-menu-list">
	   		<li><a href="${cPath }/admin/member" class="member">회원조회</a></li>
	   		<li><a href="${cPath }/admin/approve" class="approve">전문가승인</a></li>
	   	</ul>
	   <li><a href="${cPath }/admin/revenue" class="revenue">매출관리</a></li>
	   <li><a href="${cPath }/admin/report" class="report">신고관리</a></li>
	</ul>
</div>
<script>
let activeUrl = location.href
let surl = activeUrl.substring(activeUrl.lastIndexOf("/")+1);

$(".sub-menu a").removeClass("active");
$("."+surl).addClass("active");
</script>