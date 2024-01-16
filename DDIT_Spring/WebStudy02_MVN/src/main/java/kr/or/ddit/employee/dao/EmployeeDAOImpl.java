package kr.or.ddit.employee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.EmployeeVO;

public class EmployeeDAOImpl implements EmployeeDAO {

	@Override
	public List<EmployeeVO> selectEmployeeList(Integer managerId) {
		StringBuffer sql = new StringBuffer();
                                                                                                 
		sql.append(" SELECT                                                                      ");
		sql.append("     EMPLOYEE_ID,    FIRST_NAME,    LAST_NAME,                               ");
		sql.append("     EMAIL,    PHONE_NUMBER,    TO_CHAR(HIRE_DATE, 'YYYY-MM-DD') HIRE_DATE,  ");
		sql.append("     JOB_ID,    SALARY,    COMMISSION_PCT,                                   ");
		sql.append("     MANAGER_ID,    DEPARTMENT_ID,    EMP_NAME,                              ");
		sql.append("     RETIRE_DATE                                                             ");
		sql.append( " , (                                          ");
		sql.append( " 	    SELECT COUNT(EMPLOYEE_ID)              ");
		sql.append( " 	    FROM HR.EMPLOYEES B                    ");
		sql.append( " 	    WHERE A.EMPLOYEE_ID = B.MANAGER_ID     ");
		sql.append( " 	) CHILDCOUNT                               ");
		sql.append(" FROM    HR.EMPLOYEES  A ");
		if(managerId==null)
			sql.append(" WHERE MANAGER_ID IS NULL ");
		else
			sql.append(" WHERE MANAGER_ID = ? ");
		List<EmployeeVO> empList = new ArrayList<>();
		
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql.toString());) {

			if(managerId!=null)
				stmt.setInt(1, managerId);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				EmployeeVO employee = new EmployeeVO();
				empList.add(employee);
				employee.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
				employee.setFirstName(rs.getString("FIRST_NAME"));
				employee.setLastName(rs.getString("LAST_NAME"));
				employee.setEmail(rs.getString("EMAIL"));
				employee.setPhoneNumber(rs.getString("PHONE_NUMBER"));
				employee.setHireDate(rs.getString("HIRE_DATE"));
				employee.setJobId(rs.getString("JOB_ID"));
				employee.setSalary(rs.getInt("SALARY"));
				employee.setCommissionPct(rs.getInt("COMMISSION_PCT"));
				employee.setManagerId(rs.getInt("MANAGER_ID"));
				employee.setDepartmentId(rs.getInt("DEPARTMENT_ID"));
				employee.setEmpName(rs.getString("EMP_NAME"));
				employee.setRetireDate(rs.getString("RETIRE_DATE"));
				
				//=========================================
				employee.setChildCount(rs.getInt("CHILDCOUNT"));
			}
			return empList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
