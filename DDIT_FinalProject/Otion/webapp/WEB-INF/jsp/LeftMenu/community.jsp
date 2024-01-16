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
               <h3>Community</h3>
               <ul id="left-menu-list" class="sub-menu">
                  <li><a href="${cPath }/cooboard" class="cooboard">협업게시판</a></li>
                  <li><a href="${cPath }/freeBoard" class="freeBoard">자유게시판</a></li>
               </ul>
<script>
let activeUrl = location.href
let surl = activeUrl.substring(activeUrl.lastIndexOf("/")+1);

$(".sub-menu a").removeClass("active");
$("."+surl).addClass("active");
</script>