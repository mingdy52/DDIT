package kr.or.ddit.prod.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.ProdVO;

@Controller
@RequestMapping("/prod/prodUpdate.do")
public class ProdUpdateController{
	@Inject
	ProdService service;
	@Inject
	OthersDAO othersDAO;
	
	@ModelAttribute("lprodList")
	public List<Map<String, Object>> lprodList() {
		return othersDAO.selectLprodList();
	}
	
	@ModelAttribute("buyerList")
	public  List<BuyerVO> buyerList() {
		return othersDAO.selectBuyerList();
	}
	
	@GetMapping
	public String prodForm(@RequestParam(name="what") String prodId, Model model) {
		ProdVO prod = service.retrieveProd(prodId);
		model.addAttribute("prod", prod);
		return "prod/prodForm";
	}
	
	@PostMapping
	public String updateProcess(
			@RequestPart("prodImage") MultipartFile imageFile
			, @Validated(UpdateGroup.class) @ModelAttribute("prod") ProdVO prod
			, Errors errors
			, HttpServletRequest req
	) throws IOException{
		prod.setProdImage(imageFile);
		
		boolean valid = !errors.hasErrors();
		String viewName = null;
		if(valid) {
			ServiceResult result = service.modifyProd(prod);
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





































