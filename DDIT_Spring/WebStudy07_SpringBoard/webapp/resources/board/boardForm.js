/**
 * 
 */
CKEDITOR.replace('boContent', {
	filebrowserImageUploadUrl :  $.CPATH + "/board/image?command=imageUpload"
});


let boardForm = $("#board").on("click", ".delBtn", function(event){
	let attNo = $(this).data("attNo");
	let inputTag = $("<input>").attr({
						type:"number"
						, name:"delAttNos"
					}).val(attNo);
	boardForm.prepend(inputTag);
	$(this).parents("span:first").hide();
});