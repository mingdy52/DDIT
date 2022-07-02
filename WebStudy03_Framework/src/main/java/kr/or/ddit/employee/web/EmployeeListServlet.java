package kr.or.ddit.employee.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.employee.service.EmployeeService;
import kr.or.ddit.employee.service.EmployeeServiceImpl;
import kr.or.ddit.mvc.DelegatingViewResolver;
import kr.or.ddit.vo.EmployeeVO;
import kr.or.ddit.vo.fancytree.FancyTreeNode;

@WebServlet("/employee/employeeList.do")
public class EmployeeListServlet extends HttpServlet{
	EmployeeService service = new EmployeeServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accept = req.getHeader("Accept");
		if(StringUtils.containsIgnoreCase(accept, "json")) {
			String managerIdParam = req.getParameter("managerId");
			Integer managerId = null;
			if(StringUtils.isNotBlank(managerIdParam)) {
				managerId = new Integer(managerIdParam);
			}
			
			List<FancyTreeNode<EmployeeVO>> nodeList = service.retrieveEmployeeList(managerId);
			req.setAttribute("dataList", nodeList);
			
			// ========================
			req.getRequestDispatcher("/jsonView.do").forward(req, resp);
			// ========================
		}else {
			String viewName = "/employee/employeeList.tiles";
			new DelegatingViewResolver().viewResolve(viewName, req, resp);
		}
	}
}















