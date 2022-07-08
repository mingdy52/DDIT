package kr.or.ddit.prod.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.ProdVO;

@Controller
public class ProdViewController{
	ProdService service = new ProdServiceImpl();
	
	@RequestMapping("/prod/prodView.do")
	public String prodView(@RequestParam("what") String prodId, HttpServletRequest req){
		ProdVO prod = service.retrieveProd(prodId);
		req.setAttribute("prod", prod);
		return "/prod/prodView.tiles";
	}
}















