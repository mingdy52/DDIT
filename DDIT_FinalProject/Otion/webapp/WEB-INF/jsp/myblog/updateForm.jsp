<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 8. 23.      박채록      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
   
    <style>
    
       #postTitle{
          background: white;
       }
       
       #editorjs{
          border: 1px solid black;
       }
       
       .ce-block__content{
          width: 90%;
       }
       
         .tags{
       	background-color: #F2F2F2;
       	color: black;
       	margin-left: 3px;
       	margin-top: 3px;
       	border: 5px solid #F2F2F2;
       	font-weight: 500;
        border-radius: 10px;
        display: inline-block;
       
       }
		
	  #tagInput{
	  	 border:0 solid black;
	  }
	  
	 #tagInput:focus {outline:0 solid black;}
	 
	 #title{
		 border:0 solid black;
		 width: 100%;
		 font-size: 30px;
		 border-bottom: 1px solid gray;
		 margin-bottom: 10px;
	 }
	 #title:focus {outline:0 solid black;}
	 
    </style>
    <!-- 추가 플러그인 검색 https://github.com/editor-js 
        요기서 읽고 테스트하면서 기능 확장
    -->
    <button onclick="f_saveTest()"> 쩌장 </button>
    <a id="save-button">저장222</a>
    <input type="text" class="form-control" id="title">
    <div id="editorjs"></div>
<!-- 	<input type="text" class="form-control" id="tag"> -->
		<div>
   		<div class="d-flex flex-wrap gap-2">
   	   		<div id="tagArea"></div>
   			<input type="text" id="tagInput" placeholder="#태그입력..">
   		</div>
   </div>
   	
	<div id="radio-box">
		<input type="radio" name="blind" value="Y" checked="checked">공개
		<input type="radio" name="blind" value="N">비공개
	</div>
	<div>
		<button id="save-button">작성</button>
	</div>


	<form:form id="postForm" modelAttribute="mypost" method="post"  enctype="multipart/form-data">
		<form:input type="hidden" path="postTitle"/>
		<form:input type="hidden" path="postContent"/>
		<form:input type="hidden" path="postTag"/>
		<form:input type="hidden" path="blogerId" value="${mypost.blogerId}"/>
		<form:input type="hidden" path="postPublicYn"/>
		<form:input type="file" path="postFiles" multiple="true"/>
		<input type="submit" value="전송">
	</form:form>
   
	<div>
		<c:if test="${not empty mypost.attatchList }">
			<div>
				<c:forEach items="${mypost.attatchList }" var="attatches">
					<span>${attatches.originNm }</span><i name="i-update-cancel" onclick="attaDelBtn($(this))" class="ri-close-fill i-btn" 
					data-attnum="${attatches.attatchNum }" data-attorder="${attatches.attatchOrder }"></i> <br>
				</c:forEach>
			</div>
		</c:if>
		<c:if test="${empty mypost.attatchList }">
			<div>
				첨부파일 없음.
			</div>
		</c:if>
   	</div>
   	
   <pre id="jsonPrettyPreTag"></pre>


<script>
	let postTagArr = [];
	let Tags = $("input[name='postTag']").val();
	
	console.log(Tags);
	$("input[name='postTag']").each(function(){
		postTagArr.push($(this).val().split(','));
	});
	
	console.log("postTagArr",postTagArr);
	
	let myTags =  postTagArr[0];
	console.log("myTags", myTags[0].replace("#",""));
	for(i=0; i<myTags.length; i++){
		
	$("#tagArea").append("<span class='tags' data-value="+myTags[i].replace("#","")+"  data-idx="+i+">"+myTags[i]+"<a id='deltag' onclick='DelTag(event)' style='color:#FA5858; padding: 5px;'>x</a></span>")
	}
	
	function attaDelBtn(el){
		el.css("display", "none");
		el.prev().css("display", "none");
		let attatchNum = el.data("attnum");
		let attatchOrder = el.data("attorder");
		
		let delNum = $("<input>").attr("name", "delAttatchNum").attr("type", "hidden").val(attatchNum);
		let delOrder = $("<input>").attr("name", "delAttatchOrder").attr("type", "hidden").val(attatchOrder);
		
		$("#postForm").append(delNum, delOrder);
	}
	
    let arrTag = new Array();
    $("#tagInput").on("keyup",function(key){      
    	if(key.keyCode==13) {

    		let tagInput = $("input[id='tagInput']").val();
    		alert(tagInput);
    		let tagleng = $("span[class='tags']").length;
        	console.log("tagleng: "+ tagleng);
        	arrTag.push("#"+tagInput);
        	$("#tagArea").append("<span class='tags' data-value ="+tagInput+" data-idx="+tagleng+">"+"#"+tagInput+"<a id='deltag' onclick='DelTag(event)' style='color:#FA5858; padding: 5px;'>x</a></span>")
        	console.log("추가배열"+arrTag);
        	$("input[id='tagInput']").val('');

    		}
    	});
   
    
    function DelTag(event){
    	alert("태그삭제");
    	let myTarget = $(event.target);
		let targetVal = myTarget.closest("span").data('value');
		let targetIdx = myTarget.closest("span").data('idx');
    	console.log(targetVal);
    	console.log(targetIdx);
    	$("span[data-idx="+targetIdx+"]").remove();
    	arrTag.splice(targetIdx,1);
    	console.log("삭제후배열="+arrTag);
    }
    
</script>  
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
    
    let postContent = $("input[name='postContent']").val();
    
    $("#title").attr("value", $("input[name='postTitle']").val());
    $("#tag").attr("value", $("input[name='postTag']").val());
    
    let ChkPublic = $("input[name='postPublicYn']").val();
    console.log(ChkPublic);
    	
    
    if(ChkPublic=='Y'){
    	$("input[value='Y'][type='radio']").attr("checked", "checked");
    }else{
    	$("input[value='N'][type='radio']").attr("checked", "checked");
    }
    
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
        
        
        
        
    
    const saveButton = document.getElementById('save-button');
    const output = document.getElementById('output');
	

    saveButton.addEventListener('click', () => {
      editor.save().then( savedData => {
         var formData = JSON.stringify(savedData, null, 4);
         console.log(formData);
		
   		let title = $("input[id='title']").val();
   		let tag = $("input[id='tag']").val();
		let blind = $("input[name='blind']:checked").val();
   		console.log(title);
   		$("input[name='postContent']").attr("value", formData);
      	$("input[name='postTitle']").attr("value", title);
      	$("input[name='postTag']").attr("value", arrTag.toString());
      	$("input[name='postPublicYn").attr("value", blind)
      	
      	$("#postForm").submit();
      })
    })
    
    
    

    
   
    </script>

