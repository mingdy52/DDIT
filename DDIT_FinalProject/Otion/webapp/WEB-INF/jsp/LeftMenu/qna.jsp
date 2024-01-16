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
               <h3>Notice</h3>
               <ul id="left-menu-list"class="sub-menu">
                  <li><a href="${cPath}/notice"  class="notice">공지사항</a></li>
                  <li><a href="${cPath}/qna"  class="qna">Q&A 작성</a></li>
               </ul>
<script>
let activeUrl = location.href
let surl = activeUrl.substring(activeUrl.lastIndexOf("/")+1);

$(".sub-menu a").removeClass("active");
$("."+surl).addClass("active");
</script>