package kr.or.ddit.prod.web;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

@Controller
@RequestMapping("/prod/prodList.do")
public class ProdListController{
	@Inject
	ProdService service;
	
	@Inject
	OthersDAO othersDAO;
	
	
	@GetMapping
	public String prodListSync(
							@RequestParam(name="page", required=false, defaultValue="1") int currentPage
							, @ModelAttribute("detailCondition") ProdVO detailCondition
							, Model model
							){
		
		PagingVO<ProdVO> pagingVO = new PagingVO<>(3,2);
		pagingVO.setCurrentPage(currentPage);
//		pagingVO.setSimpleCondition(searchVO);
		pagingVO.setDetailCondition(detailCondition);
		
		service.retrieveProdList(pagingVO);
		
		model.addAttribute("pagingVO", pagingVO);
		model.addAttribute("lprodList", othersDAO.selectLprodList());
		model.addAttribute("buyerList", othersDAO.selectBuyerList());
		
		return "prod/prodList";
	}
}


















