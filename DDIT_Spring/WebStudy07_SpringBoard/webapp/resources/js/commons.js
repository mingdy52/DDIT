/**
 * 
 */
$(document).on("click", ".linkBtn", function(){
	let href = $(this).data("href");
	location.href = href;
})