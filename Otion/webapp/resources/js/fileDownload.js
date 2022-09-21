/**
 * <pre>
 * 
 * </pre>
 * @author 심민경
 * @since 2022. 9. 1.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2022. 9. 1. 심민경       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */ 

let download = $(".download");
download.on('click', function(event){
	let attnum = $(this).data("attnum");
	let attorder = $(this).data("attorder");
	attatchNum.value=attnum;
	attatchOrder.value=attorder;
	downForm.submit();
});