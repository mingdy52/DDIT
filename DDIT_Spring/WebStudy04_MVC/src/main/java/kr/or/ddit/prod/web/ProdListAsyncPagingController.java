package kr.or.ddit.prod.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;

import kr.or.ddit.mvc.annotation.resolvers.ModelAtrribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestHeader;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

@Controller
public class ProdListAsyncPagingController{
	ProdService service = new ProdServiceImpl();
	OthersDAO othersDAO = new OthersDAOImpl();
	
	
	@RequestMapping("/prod/prodList_async.do")
	public String prodListAsync(
			@RequestHeader(value="accept", required=false, defaultValue="text/html") String accept
			, @RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @ModelAtrribute("detailCondition") ProdVO detailCondition
			, HttpServletRequest req
	){
		req.setAttribute("lprodList", othersDAO.selectLprodList());
		req.setAttribute("buyerList", othersDAO.selectBuyerList());

//		String accept = req.getHeader("accept");
		String viewName = null;
		if(StringUtils.containsIgnoreCase(accept, "json")) {
			viewName = processJsonData(currentPage, detailCondition, req);
		}else {
			viewName = processHTML();
		}
		return viewName;
	}
	
	public String processHTML() {
		return "/prod/prodList_async.tiles";
	}
	
	public String processJsonData(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @ModelAtrribute("detailCondition") ProdVO detailCondition
			, HttpServletRequest req
	){
		
		PagingVO<ProdVO> pagingVO = new PagingVO<>(3,2);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setDetailCondition(detailCondition);
		
		service.retrieveProdList(pagingVO);
		
		req.setAttribute("pagingVO", pagingVO);
		
		return  "forward:/jsonView.do";
	}
}


















