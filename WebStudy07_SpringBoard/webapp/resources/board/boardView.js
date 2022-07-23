/**
 * 
 */
$("#exampleModal").on("hidden.bs.modal", function(){
	$(this).find("form").get(0).reset();
}).on("shown.bs.modal", function(){
	$(this).find("input[name=boPass]").focus();
});