package kr.or.ddit.props.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.props.dao.DataBasePropertyDAO;
import kr.or.ddit.props.dao.DataBasePropertyDAOImpl;
import kr.or.ddit.props.service.DataBasePropertyService;
import kr.or.ddit.props.service.DataBasePropertyServiceImpl;
import kr.or.ddit.vo.DataBasePropertyVO;
@WebServlet("/11/jdbcDesc.do")
public class DataBasePropertyRetrieveServlet extends HttpServlet {
	
	DataBasePropertyService service = new DataBasePropertyServiceImpl();
	// 인터페이스에 의존하면 구현체가 존재하지 않아도 괜찮다. 어떤 구현체든 가능하다. -> 실행코드의 캡슐화
	// 캡슐화  1. 데이터 보호 -> protected/private, 2. 실행코드 감추기 -> 인터페이스
	// 인터페이스 분리로 다형성 가능.
	// 기존의 코드는 건드리지 않고 상속을 통해 사용 - OCP
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<DataBasePropertyVO> dataList = service.retrieveDataBaseProperties();
		
		req.setAttribute("dataList", dataList);
		
		req.setAttribute("contents", "/WEB-INF/views/11/jdbcDesc.jsp");
		
		String view = "/WEB-INF/views/template.jsp";
		req.getRequestDispatcher(view).forward(req, resp);
	}

}
