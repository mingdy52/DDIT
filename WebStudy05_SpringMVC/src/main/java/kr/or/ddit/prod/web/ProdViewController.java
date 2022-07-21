package kr.or.ddit.prod.web;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.vo.ProdVO;

@Controller
@RequestMapping("/prod/prodView.do")
public class ProdViewController{
	@Inject
	ProdService service;
	
	@GetMapping
	public String prodView(@RequestParam("what") String prodId, Model model){
		ProdVO prod = service.retrieveProd(prodId);
		model.addAttribute("prod", prod);
		return "prod/prodView";
	}
}















