package kr.or.ddit.employee.service;

import java.util.List;
import java.util.stream.Collectors;

import kr.or.ddit.employee.dao.EmployeeDAO;
import kr.or.ddit.employee.dao.EmployeeDAOImpl;
import kr.or.ddit.vo.EmployeeVO;
import kr.or.ddit.vo.fancytree.FancyTreeNode;
import kr.or.ddit.vo.fancytree.FancyTreeNodeEmployeeAdapter;

public class EmployeeServiceImpl implements EmployeeService{
	EmployeeDAO empDAO = new EmployeeDAOImpl();
	
	@Override
	public List<FancyTreeNode<EmployeeVO>> retrieveEmployeeList(Integer managerId) {
		List<EmployeeVO> empList = empDAO.selectEmployeeList(managerId);
		return empList.stream() // 
				.map((emp)->new FancyTreeNodeEmployeeAdapter(emp))
				.collect(Collectors.toList());
	}
}
