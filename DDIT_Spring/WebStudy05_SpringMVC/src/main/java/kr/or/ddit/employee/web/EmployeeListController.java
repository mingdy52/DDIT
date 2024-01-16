package kr.or.ddit.employee.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.employee.service.EmployeeService;
import kr.or.ddit.employee.service.EmployeeServiceImpl;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.vo.EmployeeVO;
import kr.or.ddit.vo.fancytree.FancyTreeNode;

@Controller
public class EmployeeListController{
	EmployeeService service = new EmployeeServiceImpl();
	
	@RequestMapping("/employee/employeeList.do")
	public String emplist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accept = req.getHeader("Accept");
		String viewName = null;
		if(StringUtils.containsIgnoreCase(accept, "json")) {
			String managerIdParam = req.getParameter("managerId");
			Integer managerId = null;
			if(StringUtils.isNotBlank(managerIdParam)) {
				managerId = new Integer(managerIdParam);
			}
			
			List<FancyTreeNode<EmployeeVO>> nodeList = service.retrieveEmployeeList(managerId);
			req.setAttribute("dataList", nodeList);
			
			// ========================
			viewName = "forward:/jsonView.do";
			// ========================
		}else {
			viewName = "/employee/employeeList.tiles";
		}
		return viewName;
	}
}















