<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
#search-input {
	width: 50%;
	float: left;
	text-align: center;
	margin-left: 20%;
}

#searchBtn {
	float: left;
}

#tagBtn {
	border-radius: 5px;
	border:1px solid #F2F2F2;
	background-color: #F2F2F2;
	color: black;
}

#profileImg {
	width: 120px;
	height: 120px;
	border-radius: 50%;
	background-color: tomato;
	
}

.img-box{
	line-height: 150px;
}
.sp{
	margin:1px;
    background-color: #F2F2F2;
	color: #8181F7;
}
.expert-box {
	width: 30%;
	text-align: center;
	padding: 10px;
	float: left;
	background-color: #F5EFFB;
	margin: 5px;
	height: 280px;
	color: black;
}

.eprod-box {
	width: 30%;
	padding: 10px;
	float: left;
	background-color: white;
	margin: 5px;
	height: 280px;
	display: inline-block;
	border: 1px solid #f1f5f7;
	
}

#tagBtn:hover{
	background-color: #E6E0F8;
}

/* .sum-box{ */
/* 	padding: 10px; */
/* 	border-bottom: 1px solid gray; */
/* } */

</style>

<div class="search-box">
   <div class="card-body">
      <div id="searchUI" class="card-body">
            <div id="searchUI" class="d-flex justify-content-center">
               <select name="searchType" style="border: 1px solid #ced4da">
                  <option value>전체</option>
                  <option value="eprodName">상품명</option>
                  <option value="expertId">전문가</option>
               </select>
               <input type="text" name="searchWord" placeholder="검색어를 입력해주세요.." style="border: 1px solid #ced4da; width: 300px; padding: 3px; border-radius: 3px;" />
               <button id="searchBtn" style="margin-left: 2px;" class="btn btn-primary waves-effect waves-light"><i class="ri-search-line"></i></button>
            </div>
      </div>

      <div class="card-body">
      	 <h4 style="text-align: center; padding: 8px;">expert 인기태그</h4>
         <div class="d-flex flex-wrap gap-2 justify-content-center" >
            <button type="button"
               class="btn btn-primary btn-rounded waves-effect waves-light"
               id="tagBtn" value="#JAVA">#JAVA</button>
            <button type="button"
               class="btn btn-primary btn-rounded waves-effect waves-light"
               id="tagBtn" value="#HTML">#HTML</button>
            <button type="button"
               class="btn btn-primary btn-rounded waves-effect waves-light"
               id="tagBtn" value="#PYTHON">#PYTHON</button>
            <button type="button"
               class="btn btn-primary btn-rounded waves-effect waves-light"
               id="tagBtn" value="#C">#C</button>
            <button type="button"
               class="btn btn-primary btn-rounded waves-effect waves-light"
               id="tagBtn" value="#C++">#C++</button>
         </div>
      </div>
   </div>
</div>

<div class="card-body">
   <!-- Nav tabs -->
   <ul class="nav nav-tabs nav-tabs-custom nav-justified" role="tablist">
      <li class="nav-item">
      <a class="nav-link active" data-bs-toggle="tab" href="#prodlist" role="tab" aria-selected="true" id="getEprodList"> 
      <span class="d-block d-sm-none"><i class="far fa-envelope"></i></span> 
      <span class="d-none d-sm-block">상품</span>
      </a></li>
      <li class="nav-item">
      <a class="nav-link" data-bs-toggle="tab" href="#expertlist" role="tab" aria-selected="false" id="getExpertList"> 
      <span class="d-block d-sm-none"><i class="fas fa-cog"></i></span> 
      <span class="d-none d-sm-block">전문가</span>
      </a></li>
   </ul>

   <!-- Tab panes -->
   <div class="tab-content p-3 text-muted">
      <div class="tab-pane active" id="prodlist" role="tabpanel">
         <table class="table table-borderless mb-0">
            <tbody>
               <div style="margin-left: 30px" id="eprodbody"></div>
            </tbody>
            <tfoot>
               <tr>
                  <td>
                     <div class="d-flex justify-content-center" id="eprodPagingArea">
                     
                     </div>
                  </td>
               </tr>
            </tfoot>
         </table>
      </div>
      
<!-- **************************전문가조회******************************** -->
      <div class="tab-pane" id="expertlist" role="tabpanel">
         <table  class="table table-borderless mb-0" >
            <tbody>
               <div style="margin-left: 30px" id="expertbody"></div>
            </tbody>
            <tfoot>
               <tr>
                  <td>
                     <div class="d-flex justify-content-center" id="expertPagingArea">
                     
                     </div>
                  </td>
               </tr>
            </tfoot>
         </table>
      </div>
   </div>
</div>


<form id="searchForm" action="${cPath }/expert">
   <input type="hidden" name="eprodPage">
   <input type="hidden" name="expertPage">
   <input type="hidden" name="searchType"/>
   <input type="hidden" name="searchWord"/>
</form>

<script>


$("[name='searchType']").val("${eprodpagingVO.simpleCondition.searchType}");
$("[name='searchWord']").val("${eprodpagingVO.simpleCondition.searchWord}");




let eprodbody =$("#eprodbody");
let eprodPagingArea =$("#eprodPagingArea")
let searchUI = $("#searchUI");



eprodPagingArea.on("click", "a", function(event){
   let eprodPage= $(this).data("page");
   searchForm.find("[name=eprodPage]").val(eprodPage);
   searchForm.submit();
});


<c:url value="/expert/prod/eprodNum" var="viewURL">
</c:url>
const VIEWURL = "${viewURL}"
let makeEprod = function(index, eprod){
   let aTag= $("<a>").css("color","white").css( "font-size", "20px").attr({
      "href":VIEWURL.replace("eprodNum", eprod.eprodNum)
      }).text(eprod.eprodName);

   return $("<div>").attr("class","card h-280 shadow px-4 px-md-2 px-lg-3 card-span pt-4 eprod-box").append(
            $("<div>").css("background-color", "#717CFF").css("height", "100px").css("padding", "10px").html(aTag)
//             , $("<div>").attr("class", "sum-box").html(eprod.eprodSummary)
            , $("<div>").html(eprod.expertId).css("text-align", "right").css("color", "black")
            , $("<div>").html(eprod.eprodDate).css("text-align", "right").css("color", "black")
            , $("<h4>").html(eprod.eprodPrice+"원").css("padding", "10px")
            , $("<div>").append($("<span>").attr("class", "sp").text(eprod.eprodTagArr[1])
            					,$("<span>").attr("class", "sp").text(eprod.eprodTagArr[2]))
   );
}

let searchForm =$("#searchForm").on("submit" ,function(evnet){
//    event.preventDefault();
   let url = this.action
   let method = this.method;
   let data = $(this).serialize();
   console.log("url", url);
   console.log("data",data);
   $.ajax({
      url : url,
      data : data,
      method : method,
      dataType : "json",
      success: function(resp, status, jqXHR){
         let pagingVO = resp.eprodpagingVO
         let eprodList = pagingVO.dataList;
         let pagingHTMLBS = pagingVO.pagingHTMLBS;
         let eprodTrTags = [];
         if(eprodList&&eprodList.length>0){
            $.each(eprodList, function(index, eprod){
               eprodTrTags.push(makeEprod(index, eprod));
            });
         }else{
            let eprodTrTag =$("<div>").html(
                        $("<div>").html("상품이 존재하지 않습니다.").css("text-align", "center")
            
            );
            eprodTrTags.push(eprodTrTag);
         }
         eprodbody.html(eprodTrTags);
         eprodPagingArea.html(pagingHTMLBS);
      },
      error : function(jqXHR, status, error) {
         console.log(jqXHR);
         console.log(status);
         console.log(error);
      }
   });
   return false;
}).submit();

$("button[id='tagBtn']").on("click", function(){
	searchForm.get(0).reset();
   	let tagValue = this.value;
   	console.log(tagValue);
   	$("input[name='searchWord']").attr('value', tagValue);
   	$("input[name='searchType']").attr('value', "eprodTag");
    searchForm.submit();
   })
   
   
$("#searchBtn").on("click", function(event){
   //모든발생은 searchForm에서 발생 
   searchForm.get(0).reset();//한번클리어 제이쿼리는 초기화 기능없음 -> html만 있어서 get(0).reset를 해줘야한다.
   let inputs = searchUI.find(":input[name]");
   $(inputs).each(function(idx, input){
      let name = $(this).attr("name");//search type, searchword가져옴
      let value = $(this).val();
      searchForm.find("[name="+name+"]").val(value);//value 넣음
   });
   searchForm.submit();
});



//    let getEprodList = $("#getEprodList").on("click",function(){

//    });
   
   
   let getExpertList = $("#getExpertList").on("click",function(){
      let expertbody =$("#expertbody");
      let expertPagingArea =$("#expertPagingArea")
      
      expertPagingArea.on("click", "a", function(event){
         let expertPage= $(this).data("page");
         searchForm.find("[name=expertPage]").val(expertPage);
         searchForm.submit();
      });
      
      
      
      <c:url value="/expert/expertId" var="expertURL">
      </c:url>
      const EXPERTURL = "${expertURL}"   
      let makeExpert = function(index, exp){
         let eTag = $("<a>").attr({
          "href":EXPERTURL.replace("expertId", exp.expertId)
          }).css("color", "black").text(exp.expertId);

         let imgTag = $("<img>").attr("src", "/Otion/resources/profileImages/"+exp.profileImg).attr("id", "profileImg")
         
         return $("<div>").attr("class", "card h-280 shadow px-4 px-md-2 px-lg-3 card-span pt-4  expert-box").attr("onclick", "location.href='${cPath}/expert/"+exp.expertId+"'").append(
                  $("<div>").attr("class", "img-box").html(imgTag)
                  ,$("<div>").html(eTag).css("text-align", "center").append("전문가")
                  ,$("<div>").html(exp.exTag)
         );
      }
   
      let searchForm =$("#searchForm").on("submit" ,function(evnet){
         event.preventDefault();
         let url = this.action+"/exp";
         let method = this.method;
         let data = $(this).serialize();
         console.log("url", url);
         console.log("data",data);
         $.ajax({
            url : url,
            data : data,
            method : method,
            dataType : "json",
            success: function(resp, status, jqXHR){
               let pagingVO = resp.expertpagingVO
               let expertList = pagingVO.dataList;
               let pagingHTMLBS = pagingVO.pagingHTMLBS;
               let expertTrTags = [];
               if(expertList&&expertList.length>0){
                  $.each(expertList, function(index, exp){
                     expertTrTags.push(makeExpert(index, exp));
                  });
               }else{
                  let expertTrTag = $("<div>").html(
                          $("<div>").html("전문가가 존재하지 않습니다.").css("text-align", "center")
                  );
                  expertTrTags.push(expertTrTag);
               }
               expertbody.html(expertTrTags);
               expertPagingArea.html(pagingHTMLBS);
            },
            error : function(jqXHR, status, error) {
               console.log(jqXHR);
               console.log(status);
               console.log(error);
            }
         });
         return false;
      }).submit();
   });
   

	$(".sp").on("click", function(){
		alert("gggggggggggg");
	});

</script>
