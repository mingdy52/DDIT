/**
 * 
 */
$.fn.formDataToJson = function(){
	let array = $(this).serializeArray();
	let resultObj = {};
	$.each(array, function(idx, obj){
		resultObj[obj.name] = obj.value
	});
	return JSON.stringify(resultObj);
}

$.fn.settingFormData = function(obj){
	let inputs = this.find(":input[name]");
	inputs.each(function(idx, input){
		let name = this.name
		if(obj[name])
			$(this).val(obj[name]);
		
	});
	return this;
}