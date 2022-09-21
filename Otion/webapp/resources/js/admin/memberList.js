/**
 * <pre>
 * @author 심민경
 * @since 2022. 8. 10.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일           수정자       수정내용
 * --------     --------    ----------------------
 * 2022. 8. 10.   심민경       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */ 
	

	let VIEWURL = $.CPATH+"/admin/member/memId";
	const url = $.CPATH+"/admin/member";
	
	// 검색
	function f_search(){
		if(floatingSelect.value == ""){
			swal({
    			title: "검색 분류를 선택하세요.",
    		});
		}
		allPage.value = "";
		exPage.value = "";
		pjPage.value = "";
		getList();
		getExpList();
		getProjList();
	}
		
	// 전체 회원 페이징	
	let allTbody = $("#allTbody");
	
	let allPagingArea = document.querySelector(".allPagingArea");
	allPagingArea.addEventListener('click', function(event){
		let page = event.target.innerHTML;
		allPage.value = page;
		getList();
	});
	
	// 회원 정보 테이블 만들기 함수
	function makeMemTr(index, mem){
	  	let aTag = $("<a>").attr("href", VIEWURL.replace("memId", mem.memId))
	  					   .html(mem.memId);
		return $("<tr>").append(
					$("<td>").html(aTag)
					, $("<td>").html(mem.memName)
					, $("<td>").html(mem.memNick)
					, $("<td>").html(mem.memMail)
					, $("<td>").html(mem.memHp)
				);
	}
	
	function mouseOver(){
		   $(".tbody > tr").mouseover(function(){
		      //console.log("this:",this);
		      this.style.backgroundColor="#dcdcdc";
		   });

		   $(".tbody > tr").mouseout(function(){
		      //console.log("this:",this);   
		      this.style.backgroundColor="white";
		   })
	}
	
	// 전체 회원 리스트 가져오기
	(getList = function(event){
		$.ajax({
			url : url + "/allMember"
			, data : {
				"allPage":allPage.value
				, "searchType":floatingSelect.value
				, "searchWord":inputData.value
			}
			, method : "get"
			, dataType : "json"
			, success : function(resp, status, jqXHR) {
				let pagingVO = resp;
				let memList = pagingVO.dataList;
				let pagingHTMLBS = pagingVO.pagingHTMLBS;
				let trTags = [];
				if(memList&&memList.length>0){
					$.each(memList, function(index, mem){
						trTags.push(makeMemTr(index, mem));
					});
					
				}else{
					let trTag = $("<tr>").html(
									$("<td>").attr("colspan", "5")
											 .html("회원이 없습니다.")
								);
					trTags.push(trTag);
				}
				
				allTbody.html(trTags);
				allPagingArea.innerHTML=pagingHTMLBS;
				mouseOver();
			},  
			error : function(jqXHR, status, error) {
				console.log(jqXHR);
				console.log(status);
				console.log(error);
			}
		});
	})();
	
	// 전문가 회원 페이징
	let expPagingArea = document.querySelector(".expPagingArea");
	expPagingArea.addEventListener('click', function(event){
		let page = event.target.innerHTML;
		exPage.value = page;
		getExpList();
	});
	
	// 전문가 회원 목록 가져오기
	function getExpList(){
		let expTbody = $("#expTbody");
			
		$.ajax({
			url : url + "/exp"
			, data : {
				"exPage":exPage.value
				, "searchType":floatingSelect.value
				, "searchWord":inputData.value
			}
			, method : "get"
			, dataType : "json"
			, success : function(resp, status, jqXHR) {
				let pagingVO = resp;
				let memList = pagingVO.dataList;
				let pagingHTMLBS = pagingVO.pagingHTMLBS;
				let trTags = [];
				if(memList&&memList.length>0){
					$.each(memList, function(index, mem){
						trTags.push(makeMemTr(index, mem));
					});
				}else{
					let trTag = $("<tr>").html(
									$("<td>").attr("colspan", "5")
											 .html("회원이 없습니다.")
								);
					trTags.push(trTag);
				}
				expTbody.html(trTags);
				expPagingArea.innerHTML=pagingHTMLBS;
				mouseOver();
			},  
			error : function(jqXHR, status, error) {
				console.log(jqXHR);
				console.log(status);
				console.log(error);
			}
		});
	}

	// 프로젝트 회원 페이징
	let projPagingArea = document.querySelector(".projPagingArea");
	projPagingArea.addEventListener('click', function(event){
		let page = event.target.innerHTML;
		pjPage.value = page;
		getProjList();
	});

	// 프로젝트 회원 목록
	function getProjList(){
		let projTbody =$("#projTbody");
		
		$.ajax({
			url : url + "/proj"
			, data : {
				"pjPage":pjPage.value
				, "searchType":floatingSelect.value
				, "searchWord":inputData.value
			}
			, method : "get"
			, dataType : "json"
			, success : function(resp, status, jqXHR) {
				let pagingVO = resp;
				let memList = pagingVO.dataList;
				let pagingHTMLBS = pagingVO.pagingHTMLBS;
				let trTags = [];
				if(memList&&memList.length>0){
					$.each(memList, function(index, mem){
						trTags.push(makeMemTr(index, mem));
					});
				}else{
					let trTag = $("<tr>").html(
									$("<td>").attr("colspan", "5")
											 .html("회원이 없습니다.")
								);
					trTags.push(trTag);
				}
				projTbody.html(trTags);
				projPagingArea.innerHTML=pagingHTMLBS;
				mouseOver();
			},  
			error : function(jqXHR, status, error) {
				console.log(jqXHR);
				console.log(status);
				console.log(error);
			}
		});
	}
	
	// 블랙리스트 테이블 만들기 함수
	function makeBlackTr(index, mem){
	  	let aTag = $("<a>").attr("href", VIEWURL.replace("memId", mem.memId))
	  					   .html(mem.memId);
		return $("<tr>").append(
					$("<td>").html(aTag)
					, $("<td>").html(mem.memName)
					, $("<td>").html(mem.blackContent)
					, $("<td>").html(mem.blackDate)
				);
	}
	
	let blackPagingArea = document.querySelector(".blackPagingArea");
	blackPagingArea.addEventListener('click', function(event){
		let page = event.target.innerHTML;
		blackPage.value = page;
		getBlackList();
	});

	
	// 블랙리스트
	function getBlackList(){
		let blackTbody =$("#blackTbody");
		
		$.ajax({
			url : url + "/black"
			, data : {
				"blackPage":blackPage.value
				, "searchType":floatingSelect.value
				, "searchWord":inputData.value
			}
			, method : "get"
			, dataType : "json"
			, success : function(resp, status, jqXHR) {
				let pagingVO = resp;
				let memList = pagingVO.dataList;
				let pagingHTMLBS = pagingVO.pagingHTMLBS;
				let trTags = [];
				if(memList&&memList.length>0){
					$.each(memList, function(index, mem){
						trTags.push(makeBlackTr(index, mem));
					});
				}else{
					let trTag = $("<tr>").html(
									$("<td>").attr("colspan", "5")
											 .html("회원이 없습니다.")
								);
					trTags.push(trTag);
				}
				blackTbody.html(trTags);
				projPagingArea.innerHTML=pagingHTMLBS;
				mouseOver();
			},  
			error : function(jqXHR, status, error) {
				console.log(jqXHR);
				console.log(status);
				console.log(error);
			}
		});
	}