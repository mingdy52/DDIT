<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 8. 23.      박채록      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/editor.js/2.25.0/editor.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@editorjs/attaches@latest"></script>
<script src="https://cdn.jsdelivr.net/npm/@editorjs/header@latest"></script>
<script src="https://cdn.jsdelivr.net/npm/@editorjs/underline@latest"></script>
<script src="https://cdn.jsdelivr.net/npm/@editorjs/image@2.3.0"></script>
<script
	src="https://cdn.jsdelivr.net/npm/editorjs-text-color-plugin@1.12.1/dist/bundle.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/editorjs-drag-drop@1.1.5/dist/bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@editorjs/checklist@latest"></script>
<script src="https://cdn.jsdelivr.net/npm/@editorjs/code"></script>
<script src="https://cdn.jsdelivr.net/npm/@editorjs/table"></script>
<script src="https://cdn.jsdelivr.net/npm/editorjs-alert"></script>
<script src="https://cdn.jsdelivr.net/npm/@editorjs/marker"></script>
<script src="https://cdn.jsdelivr.net/npm/@editorjs/raw"></script>
<script
	src="https://cdn.jsdelivr.net/npm/editorjs-html@3.4.0/build/edjsHTML.js"></script>
<script src="${cPath }/resources/js/blog/index.min.js"></script>
<style>
#FormBox {
	width: 70%;
	margin: auto;
	padding: 60px;
}

#tagInput {
	font-size: 20px;
	height: 30px;
	padding: 15px;
	/*     		width: 100%; */
}

#save-button {
	background-color: black;
	font-size: 18px;
	color: white;
	border-radius: 5px;
	padding: 3px 10px 3px 10px;
	float: right;
	width: 100px;
	height: 50px;
}

#editorjs {
	border-bottom: 1px solid gray;
	min-height: 700px;
	color: black;
}

#radio-box{
	float: left;
	width: 100%;
	padding: 20px, 0, 20px, 0;
	
}

.filebox {
	width: 100%;
	padding: 5px;
	float: left;
}

.ce-block__content {
	width: 90%;
}

.d-flex flex-wrap gap-2 {
	display: flex;
	width: 100%;
}

.tags {
	background-color: #F2F2F2;
	color: black;
	margin-left: 3px;
	margin-top: 3px;
	border: 5px solid #F2F2F2;
	font-weight: 500;
	border-radius: 10px;
	display: inline-block;
}

#category {
	width: 180px;
	font-size: 23px;
	margin-bottom: 10px;
}

#tagInput {
	border: 0 solid black;
	float: left;
}

#tagArea {
	float: left;
}

#tagInput:focus {
	outline: 0 solid black;
}

#title {
	border: 0 solid black;
	width: 100%;
	font-size: 30px;
	border-bottom: 1px solid gray;
	margin-bottom: 10px;
	color: black;
}

#title:focus {
	outline: 0 solid black;
}

.category-box {
	margin-bottom: 0;
}
</style>

<div id="FormBox">
	<div class="category-box">
		<c:set value="${category }" var="cate" />
		<select id="category">
			<c:if test="${not empty cate }">
				<option value="" selected disabled hidden>카테고리선택</option>
				<c:forEach items="${cate }" var="ct">
					<option value="${ct.cateNum }">${ct.cateNm }</option>
				</c:forEach>
			</c:if>
			<c:if test="${empty cate }">
				<option value="" selected disabled hidden>카테고리없음</option>
			</c:if>
		</select>
	</div>
	<div class="d-flex flex-wrap gap-2 justify-content-center">
		<input type="text" id="title" placeholder="제목을 입력해주세요">
	</div>
	<div id="editorjs"></div>
	<div>
		<div class="d-flex">
			<div id="tagArea"></div>
			<input type="text" id="tagInput" placeholder="#태그입력..">
		</div>
	</div>
	<br>
	<div id="radio-box">
		<input type="radio" name="blind" value="Y" checked="checked">공개
		<input type="radio" name="blind" value="N">비공개
	</div>


	<form:form id="postForm" modelAttribute="mypost" method="post"
		enctype="multipart/form-data">
		<form:input path="postTitle" type="hidden" />
		<form:input path="postContent" type="hidden" />
		<form:input path="postTag" type="hidden" />
		<form:input path="blogerId" value="${mypost.blogerId}" type="hidden" />
		<form:input path="postPublicYn" type="hidden" />
		<form:input path="postCateNum" type="hidden" />
		<div class="filebox">
			<form:input type="file" path="postFiles" multiple="true"
				id="postfile" />
		</div>
		<form:input class="custom-file-input" path="thumnail" type="hidden" />
		<!-- 		<input type="submit" value="전송"> -->
	</form:form>
	<div style="margin-bottom: 60px;">
		<button id="save-button">작성</button>
	</div>

</div>








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
console.log("Tag 길이 : ", myTags.length)

if(myTags[0].replace("#","")!=""){
	for(i=0; i<myTags.length; i++){
		
		$("#tagArea").append("<span class='tags' data-value="+myTags[i].replace("#","")+"  data-idx="+i+">"+myTags[i]+"<a id='deltag' onclick='DelTag(event)' style='color:#FA5858; padding: 5px;'>x</a></span>")
	}
}


let postCate = $("input[name='postCateNum']").val();

$("option[value="+postCate+"]").attr("selected", "true");

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
	
    let ar = [];
    saveButton.addEventListener('click', () => {
      editor.save().then( savedData => {
         var formData = JSON.stringify(savedData, null, 4);
		let datas = savedData.blocks
		console.log("datas :: ",datas[0]);
		let url = "";
		for(let i=0; i<datas.length; i++){
			console.log(datas[i])
			if(datas[i].type=="image"){
				url =datas[i].data.file.url;
			}
		}

   		let title = $("input[id='title']").val();
//    		let tag = $("input[id='tag']").val();
		let blind = $("input[name='blind']:checked").val();
      	let selectedVal = $("select[id='category'] option:selected").val();
//    		console.log(title);

      	if(title==""){
			alert("제목을 작성해주세요.")
		}else if (selectedVal=="카테고리선택"){
			alert("카테고리를 선택해주세요.")
		}else{
	   		$("input[name='postContent']").attr("value", formData);
	      	$("input[name='postTitle']").attr("value", title);
	      	$("input[name='postTag']").attr("value", arrTag.toString());
	      	$("input[name='postPublicYn").attr("value", blind);
	      	$("input[name='postCateNum").attr("value", selectedVal);
	      	$("input[name='thumnail']").attr("value", url);
	      	$("#postForm").submit();
		}

      })
    })
    
    
    

    
   
    </script>

