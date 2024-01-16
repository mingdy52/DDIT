package kr.or.ddit.proj.prod.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.proj.prod.service.ProjectProdService;
import kr.or.ddit.proj.prod.vo.ProjectPaymentVO;
import kr.or.ddit.proj.prod.vo.ProjectProdVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("project/{pjId}/prodBuy")
public class ProjectProdController {

	@Inject
	private ProjectProdService prodService;
	
	@PostMapping
	public String buyProjectProd(
			@PathVariable String pjId,
			@ModelAttribute ProjectProdVO prodVO
			) {
		log.info("################# {}", prodVO);
		ProjectPaymentVO payVO= new ProjectPaymentVO();
		payVO.setPjId(pjId);
		payVO.setCprodNum(prodVO.getCprodNum());
		payVO.setWpayAmount(prodVO.getCprodPrice());
		payVO.setWpayMethodCode("PM01");
		
		prodService.createBuyProd(payVO);
		
		
		return "redirect:/project/{pjId}/home";
	}
}
