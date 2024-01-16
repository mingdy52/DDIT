<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 8. 10.      박채록      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="//cdn.ckeditor.com/4.19.1/standard/ckeditor.js"></script>
<style>
ul.ks-cboxtags {
    list-style: none;
/*     padding: 20px; */
}
ul.ks-cboxtags li{
  display: inline;
}
ul.ks-cboxtags li label{
    display: inline-block;
    background-color:#E6E0F8; 
/*     border: 2px solid rgba(139, 139, 139, .3); */
    color: #564ab1;
/*     border-radius: 25px; */
    white-space: nowrap;
    margin: 3px 0px;
    -webkit-touch-callout: none;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
    -webkit-tap-highlight-color: transparent;
    transition: all .2s;
}

ul.ks-cboxtags li label {
    padding: 8px 12px;
    cursor: pointer;
}

ul.ks-cboxtags li label::before {
    display: inline-block;
    font-style: normal;
    font-variant: normal;
    text-rendering: auto;
    -webkit-font-smoothing: antialiased;
    font-family: "Font Awesome 5 Free";
    font-weight: 900;
    font-size: 12px;
    padding: 2px 6px 2px 2px;
    content: "\f067";
    transition: transform .3s ease-in-out;
}

ul.ks-cboxtags li input[type="checkbox"]:checked + label::before {
    content: "\f00c";
    transform: rotate(-360deg);
    transition: transform .3s ease-in-out;
}

ul.ks-cboxtags li input[type="checkbox"]:checked + label {
/*     border: 2px solid #1bdbf8; */
    background-color: #564ab1;
    color: white;
    transition: all .2s;
}

ul.ks-cboxtags li input[type="checkbox"] {
  display: absolute;
}
ul.ks-cboxtags li input[type="checkbox"] {
  position: absolute;
  opacity: 0;
}
ul.ks-cboxtags li input[type="checkbox"]:focus + label {
/*   border: 2px solid #e9a1ff; */
}

input[type='text']{
   background-color: white;
}

.error{
   text-align:  right; 
   color: red;
   background-color: white;
   border: none;
}


</style>
<form:form id="eprodForm" modelAttribute="eprod" method="POST" >
   <table class="table table-borderless mb-0">
      <tr>
         <td><input autocomplete="off" name="eprodName" type="text" class="form-control" placeholder="상품명.." value="${eprod.eprodName}" maxlength="50"/>
            <form:errors path="eprodName"  class="form-control error" /></td>
      </tr>
      <tr>
         <td><input name="eprodTag" type="hidden" class="form-control" placeholder="상품태그" value="${eprod.eprodTag }"></td>
         <td>
         </td>
      </tr>
      <tr>
         <td>
           <div class="d-flex justify-content-center">
              <ul class="ks-cboxtags">
                <li><input name="chkList" type="checkbox" id="checkboxOne" value="#JAVA"><label for="checkboxOne">JAVA</label></li>
                <li><input name="chkList" type="checkbox" id="checkboxTwo" value="#JAVASCRIPT" ><label for="checkboxTwo">JAVASCRIPT</label></li>
                <li><input name="chkList" type="checkbox" id="checkboxThree" value="#C" ><label for="checkboxThree">C</label></li>
                <li><input name="chkList" type="checkbox" id="checkboxFour" value="#C+"><label for="checkboxFour">C+</label></li>
                <li><input name="chkList" type="checkbox" id="checkboxFive" value="#C++"><label for="checkboxFive">C++</label></li>
                <li><input name="chkList" type="checkbox" id="checkboxSix" value="#C#"><label for="checkboxSix">C#</label></li>
                <li><input name="chkList" type="checkbox" id="checkboxSeven" value="#PYTHON"><label for="checkboxSeven">PYTHON</label></li>
                <li><input name="chkList" type="checkbox" id="checkboxEight" value="#HTML"><label for="checkboxEight">HTML</label></li>
                <li><input name="chkList" type="checkbox" id="checkboxNine" value="#ORACLE "><label for="checkboxNine">ORACLE </label></li>
                <li><input name="chkList" type="checkbox" id="checkboxTen" value="#HTML5"><label for="checkboxTen">HTML5</label></li>
                <li><input name="chkList" type="checkbox" id="checkboxEleven" value="#JSP"><label for="checkboxEleven">JSP</label></li>
                <li><input name="chkList" type="checkbox" id="checkboxTwelve" value="#React.js"><label for="checkboxTwelve">React.js</label></li>
                <li><input name="chkList" type="checkbox" id="checkboxThirteen" value="#jQuery"><label for="checkboxThirteen">jQuery</label></li>
                <li><input name="chkList" type="checkbox" id="checkboxFourteen" value="#Android"><label for="checkboxFourteen">Android</label></li>
                <li><input name="chkList" type="checkbox" id="checkboxFifteen" value="#CSS"><label for="checkboxFifteen">CSS</label></li>
                <li><input name="chkList" type="checkbox" id="checkboxSixTeen" value="#Spring"><label for="checkboxSixTeen">Spring</label></li>
                <li><input name="chkList" type="checkbox" id="checkboxSevenTeen" value="#Front-end"><label for="checkboxSevenTeen">Front-end</label></li>
                <li><input name="chkList" type="checkbox" id="checkboxeighteen" value="#Back-end"><label for="checkboxeighteen">Back-end</label></li>
              </ul>
            </div>
            <form:errors path="eprodTag"  class="form-control error" />
         </td>
      </tr>
      <tr>
         <td><textarea name="eprodSummary" placeholder="상품설명을 입력해주세요.." >${eprod.eprodSummary }</textarea></td>
      </tr>
      <tr>
         <td><input autocomplete="off" name="eprodPrice" type="text" class="form-control" placeholder="상품가격" value="${eprod.eprodPrice }">   
            <form:errors path="eprodPrice"  class="form-control error"></form:errors>   
         </td>
      </tr>
      <tr>
         <td colspan="2">
           <div style="float: right;">
            <input class="btn btn-primary waves-effect waves-light" type="button" id="eprodBtn" value="등록"/>
            <input class="btn btn-secondary waves-effect waves-light" type="reset" value="취소"/>
           
           </div>
          </td>
      </tr>

   </table>

</form:form>






<script>

CKEDITOR.replace('eprodSummary', {
   filebrowserImageUploadUrl :  "${cPath}/expert/image?command=imageUpload&${_csrf.parameterName}=${_csrf.token}"
   });
CKEDITOR.editorConfig = function( config ) {
   config.entities_latin = false; 
     config.entities_greek = false; 
     config.entities = false; 
     config.basicEntities = false; 
};

let chkTagArr = [];
let chkedTags = $("input[name='eprodTag']").val();

$("input[name='eprodTag']").each(function(){
   chkTagArr.push($(this).val().split(','));
});

let price = $("input[name='eprodPrice']").val().replace(",","");
$("input[name='eprodPrice']").attr("value",price);


let a = chkTagArr[0];
for(i=0; i<a.length; i++){
   console.log(a[i]);
   $("input[value='"+a[i]+"']").attr("checked", "true");
}
$("#eprodBtn").on("click", function(){
   let chk_Val = [];
   $("input[name='chkList']:checked").each(function(){
      chk_Val.push($(this).val());
   });
   console.log(chk_Val)
   console.log("길이", chk_Val.length);
//    let chkleng = chk_Val.length;
//    if(chkleng==0){
//       alert("하나 이상의 태그를 선택해야합니다.")
//    }else{
      
//    }
   $("input[name='eprodTag']").attr("value", chk_Val);
    $("#eprodForm").submit();
});




</script>