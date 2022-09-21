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
               <h3>blog</h3>
               <ul id="left-menu-list" class="sub-menu">
                  <li><a href="${cPath }/blog/latest" class="latest">최신순</a></li>
                  <li><a href="${cPath }/blog/trend" class="trend">트렌드</a></li>
               </ul>
<script>
let activeUrl = location.href
let surl = activeUrl.substring(activeUrl.lastIndexOf("/")+1);

$(".sub-menu a").removeClass("active");
$("."+surl).addClass("active");
</script>