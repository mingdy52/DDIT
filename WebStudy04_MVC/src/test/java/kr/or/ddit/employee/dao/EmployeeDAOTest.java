package kr.or.ddit.employee.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import kr.or.ddit.vo.EmployeeVO;

public class EmployeeDAOTest {
	
	EmployeeDAO dao = new EmployeeDAOImpl();
	
	@Test
	public void testSelectEmployeeList() {
		List<EmployeeVO> employeeList = dao.selectEmployeeList(100);
		System.out.println(employeeList);
		assertNotNull(employeeList);
		assertNotEquals(0, employeeList.size());
	}

}
