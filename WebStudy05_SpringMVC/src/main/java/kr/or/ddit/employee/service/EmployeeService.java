package kr.or.ddit.employee.service;

import java.util.List;

import kr.or.ddit.vo.EmployeeVO;
import kr.or.ddit.vo.fancytree.FancyTreeNode;

public interface EmployeeService {
//	public List<EmployeeVO> retrieveEmployeeList();
	public List<FancyTreeNode<EmployeeVO>> retrieveEmployeeList(Integer managerId);
}
