package kr.or.ddit.employee.dao;

import java.util.List;

import kr.or.ddit.vo.EmployeeVO;

public interface EmployeeDAO {
	public List<EmployeeVO> selectEmployeeList(Integer managerId);
}
