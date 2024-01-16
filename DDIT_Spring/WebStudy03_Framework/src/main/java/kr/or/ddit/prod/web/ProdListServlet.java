package kr.or.ddit.prod.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

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
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;
import kr.or.ddit.vo.SimpleSearchCondition;

@WebServlet("/prod/prodList.do")
public class ProdListServlet extends HttpServlet{
   ProdService service = new ProdServiceImpl();
   OthersDAO othersDAO = new OthersDAOImpl(); //select태그 옵션태그 주기위해
         
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      //추가
//      String searchType = req.getParameter("searchType");
//      String searchWord = req.getParameter("searchWord");
//      SimpleSearchCondition searchVO = new SimpleSearchCondition(searchType, searchWord);
      
//      req.getParameter("prodLgu");
//      req.getParameter("prodBuyer");
//      req.getParameter("prodName");  --> 맵으로 받자
      ProdVO detailCondition = new ProdVO();
      try {
         BeanUtils.populate(detailCondition, req.getParameterMap());
      } catch (IllegalAccessException | InvocationTargetException e) {
         throw new RuntimeException(e);
      }
      String pageParam = req.getParameter("page");
      int currentPage = 1;
      if(StringUtils.isNumeric(pageParam)) { //현재 파라미터 숫자로 되어있는지
         currentPage = Integer.parseInt(pageParam);
      }
      PagingVO<ProdVO> pagingVO = new PagingVO<>(); //파라미터 없으면 기본생성자로만든다. 10과 5
      pagingVO.setCurrentPage(currentPage);
//      pagingVO.setSimpleCondition(searchVO);
      pagingVO.setDetailCondition(detailCondition);
      
      service.retrieveProdList(pagingVO);
      
      req.setAttribute("pagingVO", pagingVO);
      req.setAttribute("lprodList", othersDAO.selectLprodList());
      req.setAttribute("buyerList", othersDAO.selectBuyerList());
      
      
      String viewName = "/prod/prodList.tiles";
      new DelegatingViewResolver().viewResolve(viewName, req, resp);
   }
   
}

















