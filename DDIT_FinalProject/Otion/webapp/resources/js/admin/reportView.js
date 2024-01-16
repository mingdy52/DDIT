/**
 * <pre>
 * 
 * </pre>
 * @author 심민경
 * @since 2022. 9. 16.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2022. 9. 16.      작성자명       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */ 

	function addBlack() {
		swal({
			  title: "차단하시겠습니까?",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then(function (willDelete){
			  if (willDelete) {
				reason.value = blackReason.value;
				if (!reason.value) {
					swal({
		    			  title: "차단 사유를 입력하세요.",
		    			});
					return
				}
				if (reason.value.length > 140) {
					swal({
		    			  title: "140자 이내로 작성해주세요.",
		    			});
					return
				}
				
				addBlackForm.submit();
				
			  } else {
				  return;
			  }
		});
	}
	
	function depriveExp() {
		swal({
			  title: "전문가 자격을 박탈하시겠습니까?",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then(function (willDelete){
			  if (willDelete) {
				  addBlackForm.submit();
			
			  } else {
				  return;
			  }
			
			});
	}
	
	function blockBoard() {
		swal({
			  title: "게시글을 차단하시겠습니까?",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then(function (willDelete){
			  if (willDelete) {
				  addBlackForm.submit();
			
			  } else {
				  return;
			  }
			
			});
	}
	
	let pagingArea = $(".pagingArea");
	pagingArea.on('click', function(event) {
		page.value = event.target.innerText;
		searchForm.submit();
	});