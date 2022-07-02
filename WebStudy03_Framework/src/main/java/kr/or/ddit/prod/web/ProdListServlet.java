package kr.or.ddit.prod.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.mvc.DelegatingViewResolver;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;
import kr.or.ddit.vo.SimpleSearchCondition;

@WebServlet("/prod/prodList_async.do")
public class ProdListServlet extends HttpServlet {
	ProdService service = new ProdServiceImpl();
	OthersDAO othersDAO = new OthersDAOImpl();
	
	private void processHTML(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DelegatingViewResolver resolver = new DelegatingViewResolver();
		String viewName = "/prod/prodList_async.tiles";
		resolver.viewResolve(viewName, req, resp);
	}
	
	private void processJsonData(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String pageParam = req.getParameter("page");
		
//		String searchType = req.getParameter("searchType");
//		String searchWord = req.getParameter("searchWord");
//		SimpleSearchCondition searchVO = new SimpleSearchCondition(searchType, searchWord);
		
//		req.getParameter("prodLgu");
//		req.getParameter("prodBuyer");
//		req.getParameter("prodName");
		
		ProdVO detailCondition = new ProdVO(); // 검색 조건을 가지고 있는 애
		
		try {
			BeanUtils.populate(detailCondition, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		
		int currentPage = 1;
		if(StringUtils.isNumeric(pageParam)) {
			// 숫자가 들어오는지 판단해줌
			currentPage = Integer.parseInt(pageParam);
		}
		PagingVO<ProdVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
//		pagingVO.setSimpleCondition(searchVO);
		pagingVO.setDetailCondition(detailCondition);
		service.retrieveProdList(pagingVO);
		
		
		req.setAttribute("pagingVO", pagingVO);
		
		DelegatingViewResolver resolver = new DelegatingViewResolver();
		String viewName = "forward:/jsonView.do";
		resolver.viewResolve(viewName, req, resp);
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("lprodList", othersDAO.selectLprodList());
		req.setAttribute("buyerList", othersDAO.selectBuyerList());
		
		String accept = req.getHeader("accept");
		
		if(StringUtils.containsIgnoreCase(accept, "json")) {
			processJsonData(req, resp);
		} else {
			processHTML(req, resp);
		}
		
	}

}
