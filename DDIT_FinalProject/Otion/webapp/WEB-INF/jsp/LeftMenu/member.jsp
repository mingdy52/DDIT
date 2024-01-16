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
    
 <h3>마이페이지</h3>
 <ul id="left-menu-list" class="sub-menu">
         <li><a href="${cPath}/member/mypage" class="mypage">내정보</a></li>
         <li><a href="${cPath}/member/memComm" class="memComm">커뮤니티</a></li>
         <li><a href="${cPath}/member/memPay" class="request">결제내역 </a></li>
	     <li><a href="${cPath}/member/memRefund" class="memRefund">환불내역</a></li>
					
</ul>
<script>
let activeUrl = location.href
let surl = activeUrl.substring(activeUrl.lastIndexOf("/")+1);

$(".sub-menu a").removeClass("active");
$("."+surl).addClass("active");
</script>