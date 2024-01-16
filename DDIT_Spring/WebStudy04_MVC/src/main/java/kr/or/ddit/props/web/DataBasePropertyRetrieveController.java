package kr.or.ddit.props.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.props.service.DataBasePropertyService;
import kr.or.ddit.props.service.DataBasePropertyServiceImpl;
import kr.or.ddit.vo.DataBasePropertyVO;

@Controller
public class DataBasePropertyRetrieveController{
	
	DataBasePropertyService service = new DataBasePropertyServiceImpl();
	
	@RequestMapping("/11/jdbdDesc.do")
	public String dbPropList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<DataBasePropertyVO> dataList = service.retrieveDataBaseProperties();
		
		req.setAttribute("dataList", dataList);
		
		return "/11/jdbcDesc.tiles";
	}
}




















