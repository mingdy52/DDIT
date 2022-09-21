<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자         수정내용
* ----------  ---------  -----------------
* 2022. 8. 23.       심민경         최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
   prefix="sec"%>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/editor.js/2.25.0/editor.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@editorjs/attaches@latest"></script>
    <script src="https://cdn.jsdelivr.net/npm/@editorjs/header@latest"></script> 
    <script src="https://cdn.jsdelivr.net/npm/@editorjs/underline@latest"></script>
    <script src="https://cdn.jsdelivr.net/npm/@editorjs/image@2.3.0"></script>
    <script src="https://cdn.jsdelivr.net/npm/editorjs-text-color-plugin@1.12.1/dist/bundle.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/editorjs-drag-drop@1.1.5/dist/bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@editorjs/checklist@latest"></script>
    <script src="https://cdn.jsdelivr.net/npm/@editorjs/code"></script>
    <script src="https://cdn.jsdelivr.net/npm/@editorjs/table"></script>
    <script src="https://cdn.jsdelivr.net/npm/editorjs-alert"></script>
    <script src="https://cdn.jsdelivr.net/npm/@editorjs/marker"></script>
    <script src="https://cdn.jsdelivr.net/npm/@editorjs/raw"></script>
    <script src="https://cdn.jsdelivr.net/npm/editorjs-html@3.4.0/build/edjsHTML.js"></script>
   <script src="${cPath }/resources/js/blog/index.min.js"></script>
   
   <sec:authorize access="isAuthenticated()">
   <sec:authentication property="principal.realMember" var="member" />
</sec:authorize>

<style>
#replyAttr {
	margin-top: 10px;
	float: right;
}

#content {
	margin: 20px;
	pointer-events: none;
	margin-top: 40px;
}

.arr {
	padding: 5px 3px 5px 3px;
	background-color: #F2F2F2;
	border-radius: 10px;
	color: black;
	border: 2px solid #F2F2F2;
	box-shadow: 2px solid gray;
}

#title-box {
	width: 100%;
	float: left;
	font-size: 30px;
	color: black;
	margin-left: 5px;
}

table {
	text-align: center;
}
#pagingArea{
text-align: center;
}
a{
	color: black;
}
#heartNo {
	pointer-events: none;
	border: none;
	font-size: 18px;
	padding-top: 15px;
}

#bookMark{
	font-size: 50px;
}
</style>

<!-- bookmark 추가여부 -->


<input type="hidden" name="Markchk" value="${Markchk }">
<input type="hidden" name="Heartchk" value="${Heartchk }">
<div>
   <div class="card-body" style="height:90px; border-bottom: 2px solid black">
      <div id="title-box">
      	<div  class="d-flex flex-wrap gap-2 justify-content-left" style="width: 75%; float: left;">
         <div class="rating-symbol" id="bookMark" style="display: inline-block; position: relative;">
            <div class="rating-symbol-background ri-bookmark-line" style="font-size: 40px; visibility: visible; margin-top: 1px;  "></div>
            <div class="rating-symbol-foreground bookmarkhind" style="display: inline-block; position: absolute; overflow: hidden; left: 0px; right: 0px; width: 0px;">
               <span class="ri-bookmark-fill" style="margin-top:1px; color: #D30707; font-size: 40px;"></span>
            </div>
         </div>
          <div style="margin-left: 10px;  margin-top:6px;"><c:out value="${post.postTitle }"/></div>
         </div>
      <div style="width: 25%; float: right; font-size: 15px;">
      <div style="text-align: right;">
            <a href="${cPath }/blog/gogo/cate/${post.cateNm }">${post.cateNm }</a>
            | ${post.postDate }
         <c:if test="${post.postPublicYn eq 'N' }">
            <i class="ri-rotate-lock-line"></i>
         </c:if>
      </div>
      <c:if test="${member['memId'] eq post.blogerId}">
         <div style="text-align: right;">
            <a href="${cPath }/blog/${post.blogerId}/post/${post.postNum}/form">수정</a>
            <c:if test="${post.postPublicYn eq 'Y' }">
            | <a href="${cPath }/blog/${post.blogerId}/post/${post.postNum}/private?lock=N">비공개로 변경</a>
            </c:if>
            <c:if test="${post.postPublicYn eq 'N' }">
            | <a href="${cPath }/blog/${post.blogerId}/post/${post.postNum}/private?lock=Y">공개로 변경</a>
            </c:if>
            | <a href="${cPath }/blog/${post.blogerId}/post/${post.postNum}/del">삭제</a>
         </div>
      </c:if>
      <div style="text-align: right;">
         <i class="ri-eye-fill" style="text-align: right; padding-right:10px;">&nbsp;${post.postView}</i><i class=" ri-hearts-fill" style="text-align: right; padding-right:10px;">&nbsp;${post.postHeart}</i>
      </div>
      </div>
      </div>
   </div>
   <div id="content">
      <p class="card-title-desc" id="postContent" style="display: none"><c:out value="${post.postContent }"/></p>
      <div id="editorjs"></div>
   </div>
   
   	<div style="width: 100%; float: left; border-bottom: 2px solid black; margin-bottom: 20px;">
     <div class="d-flex flex-wrap gap-2 heartBox" style="width: 180px; float: right;">
         <c:if test="${member['memId'] eq post.blogerId}">
            <div style="width: 50px; float: right;">
               <i class="ri-share-box-line" style="font-size:40px;" data-bs-toggle="modal" data-bs-target="#exampleModal" onclick="f_getShareList()"></i>
            </div>
         </c:if>
        <div class="rating-star" id="heartBtn" >
         <!--       *********찜기능************* -->
         <div class="rating-symbol" style="display: inline-block; position: relative; float: right;">
            <div class="rating-symbol-background mdi mdi-heart-outline text-danger " style="font-size: 40px; visibility: visible;"></div>
            <div class="rating-symbol-foreground hearthide" style="display: inline-block; position: absolute; overflow: hidden; left: 0px; right: 0px; width: 0px;">
               <span class="mdi mdi-heart text-danger" style="font-size: 40px;"></span>
            </div>
         </div>
      </div>
       <div><span id="heartNo" style="text-align: right">${heartNo } LIKE</span></div>
     </div>
     </div>
</div>
<div id="tagArea">
	<c:set value="${TagArr }" var="TagArr"/>
	<c:forEach items="${TagArr }" var="arr">
		<span class="arr">${arr }</span>
	</c:forEach>
</div>
<div>
   <c:if test="${not empty post.attatchList }">
      <div>
         <c:forEach items="${post.attatchList }" var="attatches">
            <a href="#" class="download" data-attnum="${attatches.attatchNum }" data-attorder="${attatches.attatchOrder }">${attatches.originNm }</a> <br>
         </c:forEach>
      </div>
   </c:if>
</div>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-scrollable modal-lg" >
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">공유</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <input class="form-control" placeholder="회원 아이디를 입력하세요." id="receiver" onkeyup="if(window.event.keyCode==13){f_share()}">
        <table class="table table-bordered">
           <thead>
              <th>아이디</th>
              <th>닉네임</th>
              <th>삭제</th>
           </thead>
           <tbody id="shareBody">
           </tbody>
        </table>
           	<div class="d-flex justify-content-center" id="pagingArea">
           	</div>
      <input id="sharePage" name="page" type="hidden">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" onclick="f_share()" class="btn btn-primary">공유하기</button>
      </div>
    </div>
  </div>
</div>
<form id="markForm">
	<sec:csrfInput />
	<input type="hidden" name="postNum" value="${post.postNum}">
<%-- 	<input type="hidden" name="postNum" value="${post.getMemId}"> --%>
</form>

<form action="${cPath }/download" id="downForm">
   <input type="hidden" name="attatchNum" id="attatchNum">
   <input type="hidden" name="attatchOrder" id="attatchOrder">
</form>
<br>

<jsp:include page="blogReply.jsp"/>
<script>
const url = "${cPath}/blog/${post.blogerId}/post/${post.postNum}";
const blogerId = "${post.blogerId}";
</script>
<script src="${cPath }/resources/js/fileDownload.js"></script>
<script src="${cPath }/resources/js/blog/postSubFunction.js"></script>

<script>
 
const editor = new EditorJS({
    autofocus: true,
    onReady: () => {
    new DragDrop(editor);
    },
    tools: {
       alert: Alert,
        attaches: {
            class: AttachesTool,
            config: {
                endpoint: 'upload URL'
            }
        },
        checklist: {
            class: Checklist,
            inlineToolbar: true,
          },
          raw: RawTool,
         table: {
              class: Table,
          },
         Marker: {
              class: Marker,
              shortcut: 'CMD+SHIFT+M',
          },
        code: CodeTool,
        codeBox: {
            class: CodeBox,
            config: {
              themeURL: 'https://cdn.jsdelivr.net/gh/highlightjs/cdn-release@9.18.1/build/styles/dracula.min.css', // Optional
              themeName: 'atom-one-dark', // Optional
              useDefaultTheme: 'light' // Optional. This also determines the background color of the language select drop-down
            }
         },
        header: Header,
        underline: Underline,
        image: {
            class: ImageTool,
            config: {
                endpoints: {
                    byFile: '${cPath}/blog/uploadFile?${_csrf.parameterName}=${_csrf.token}'
                    
                }
            }
        },
        Color: {
            class: ColorPlugin,
            config: {
                colorCollections: ['#EC7878','#9C27B0','#673AB7','#3F51B5','#0070FF','#03A9F4','#00BCD4','#4CAF50','#8BC34A','#CDDC39', '#FFF'],
                defaultColor: '#FF1300',
                type: 'text', 
            }     
        },
        Marker: {
            class: ColorPlugin,
            config: {
                defaultColor: '#FFBF00',
                type: 'marker', 
            }       
        }
    }
});
let postContent = $("#postContent").text();
let postJs = JSON.parse(postContent).blocks;
console.log(postJs);    
    $("#load").on("click", function(){
       editor.render({
            "time": 1661322242128,
            "blocks": postJs,
            "version": "2.25.0"
        })
    });

    editor.isReady
    .then(() => {
       editor.render({
            "time": 1661322242128,
            "blocks": postJs,
            "version": "2.25.0"
        })
    })
    .catch((reason) => {
      console.log(`Editor.js initialization failed because of ${reason}`)
    });
    
    
    
   let Markchk = $("input[name='Markchk']").val();
   console.log(Markchk);
   if (Markchk == "true") {
      $(".bookmarkhind").css("overflow", "visible");
   }
   
   let Heartchk = $("input[name='Heartchk']").val();
   console.log(Heartchk);
   if (Heartchk == "true") {
      $(".hearthide").css("overflow", "visible");
   }
   


$("#bookMark").on("click",function(){
   let bookMark = $("div.bookmarkhind").css("overflow");
   console.log("bookMark : "+bookMark);
   if (bookMark == "hidden") {
      $.ajax({
         url : "${cPath}/{memId}/saveMark",
         method : "post",
         dataType : "json",
         data : $('#markForm').serialize(),
         success : function(resp, status, jqXHR) {
            console.log(resp)
//             alert("'좋아요'가 반영되었습니다!");
            $("input[name='Markchk']").attr("value", "true");
            $(".bookmarkhind").css("overflow", "visible");

         },
         error : function(jqXHR, status, error) {
//             alert("실패");
            console.log(jqXHR);
            console.log(status);
            console.log(error);
            $(".bookmarkhind").css("overflow", "hidden");
         }

      });
      
   }else if(bookMark == "visible"){
      
      $.ajax({
         url : "${cPath}/{memId}/deleteMark",
         method : "post",
         dataType : "json",
         data : $('#markForm').serialize(),
         success : function(resp, status, jqXHR) {
            console.log(resp)
            
//             alert("'삭제'가 반영되었습니다!");
            $("input[name='Markchk']").attr("value", "false");
            $(".bookmarkhind").css("overflow", "hidden");
         },
         error : function(jqXHR, status, error) {
//             alert("실패");
            console.log(jqXHR);
            console.log(status);
            console.log(error);
            $(".bookmarkhind").css("overflow", "hidden");
         }
      });   
   }
});
    
    $("#heartBtn").on("click", function(){
       let heartMark = $("div.hearthide").css("overflow");
       console.log("heartMark : "+heartMark);
       
       console.log("데이터 ", $('#markForm').serialize());
       
       if(heartMark=="hidden"){
          $.ajax({
             url : "${cPath}/{memId}/savePostHeart",
             method : "post",
             dataType : "json",
             data : $('#markForm').serialize(),
             success : function(resp, status, jqXHR) {
                console.log(resp)
//                 alert("'좋아요'가 반영되었습니다!");
                $("input[name='Heartchk']").attr("value", "true");
                $(".hearthide").css("overflow", "visible");
                let heartNo = $("#heartNo").text();
                console.log("heartNo",heartNo);
                let plustht = parseInt(heartNo)+1
               $("#heartNo").html(plustht+" LIKE" );
             },
             error : function(jqXHR, status, error) {
                alert("실패");
                console.log(jqXHR);
                console.log(status);
                console.log(error);
                $(".hearthide").css("overflow", "hidden");
             }

          });
       }else if(heartMark=="visible"){
          $.ajax({
             url : "${cPath}/{memId}/delPostHeart",
             method : "post",
             dataType : "json",
             data : $('#markForm').serialize(),
             success : function(resp, status, jqXHR) {
                console.log(resp)
//                 alert("'삭제'가 반영되었습니다!");
                $("input[name='Heartchk']").attr("value", "false");
                $(".hearthide").css("overflow", "hidden");
                let heartNo = $("#heartNo").text();
                console.log("heartNo",heartNo);
                let minumsht = parseInt(heartNo)-1;
                
                $("#heartNo").html(minumsht+" LIKE" );
             },
             error : function(jqXHR, status, error) {
//                 alert("실패");
                console.log(jqXHR);
                console.log(status);
                console.log(error);
                $(".hearthide").css("overflow", "hidden");
             }
          });   
       }
    });
</script>
