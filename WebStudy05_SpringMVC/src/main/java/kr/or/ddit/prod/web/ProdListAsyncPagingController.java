package kr.or.ddit.prod.web;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

@Controller
public class ProdListAsyncPagingController{
	@Inject
	ProdService service;
	
	@Inject
	OthersDAO othersDAO;
	
	
	@RequestMapping("/prod/prodList_async.do")
	public String prodListAsync(
			@RequestHeader(value="accept", required=false, defaultValue="text/html") String accept
			, @RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @ModelAttribute("detailCondition") ProdVO detailCondition
			, Model model
	){
		model.addAttribute("lprodList", othersDAO.selectLprodList());
		model.addAttribute("buyerList", othersDAO.selectBuyerList());

		String viewName = null;
		if(StringUtils.containsIgnoreCase(accept, "json")) {
			viewName = processJsonData(currentPage, detailCondition, model);
		}else {
			viewName = processHTML();
		}
		return viewName;
	}
	
	public String processHTML() {
		return "prod/prodList_async";
	}
	
	public String processJsonData(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @ModelAttribute("detailCondition") ProdVO detailCondition
			, Model model
	){
		
		PagingVO<ProdVO> pagingVO = new PagingVO<>(3,2);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setDetailCondition(detailCondition);
		
		service.retrieveProdList(pagingVO);
		
		model.addAttribute("pagingVO", pagingVO);
		
		return  "jsonView";
	}
}


