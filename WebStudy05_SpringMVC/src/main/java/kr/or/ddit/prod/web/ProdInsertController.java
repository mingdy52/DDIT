package kr.or.ddit.prod.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.ProdVO;

@Controller
@RequestMapping("/prod/prodInsert.do")
public class ProdInsertController{
	@Inject
	ProdService service;
	
	@Inject
	OthersDAO othersDAO;
	
	@ModelAttribute("prod")
	public ProdVO prod() {
		return new ProdVO();
	}
	
	@ModelAttribute("lprodList")
	private List<Map<String,Object>> lprodList() {
		return othersDAO.selectLprodList();
	}
	
	@ModelAttribute("buyerList")
	private List<BuyerVO> buyerList() {
		return othersDAO.selectBuyerList();
	}

	
	@GetMapping
	public String prodForm(HttpServletRequest req){
		return "prod/prodForm";
	}
	
	@PostMapping
	public String insertProcess(
								@Validated(InsertGroup.class) @ModelAttribute("prod") ProdVO prod
								, Errors error
								, HttpServletRequest req) throws IllegalStateException, IOException {
		
		boolean valid = !error.hasErrors();
		String viewName = null;
		if(valid) {
			ServiceResult result = service.createProd(prod);
			if(ServiceResult.OK.equals(result)) {
				viewName = "redirect:/prod/prodView.do?what="+prod.getProdId();
			}else {
				req.setAttribute("message", "서버 오류");
				viewName = "prod/prodForm";
			}
		}else {
			viewName = "prod/prodForm";
		}
		return viewName;
	}
}





































