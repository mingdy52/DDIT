package kr.or.ddit.buyer.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.buyer.vo.BuyerVO;
import kr.or.ddit.common.vo.PagingVO;

@Controller
@RequestMapping("/buyer")
public class BuyerRetrieveController {
	
	@Inject
	private BuyerService service;
	
	@GetMapping
	public String buyerListForm() {
		return "buyer/buyerList";
	}
	
	@ResponseBody
	@GetMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PagingVO<BuyerVO> buyerList(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			) {
		PagingVO<BuyerVO> pagingVO = new PagingVO<>(3,3);
		pagingVO.setCurrentPage(currentPage);
		List<BuyerVO> buyserList = service.findAll(pagingVO);
		pagingVO.setDataList(buyserList);
		return pagingVO;
	}
	
	@GetMapping("{buyerId}")
	public String  buyerDetail(
			@PathVariable String buyerId
			, Model model
			) {
		BuyerVO buyerVO = service.findBuyer(buyerId);
		model.addAttribute("buyerVO", buyerVO);
		return "buyer/buyerView";
	}
	
}
