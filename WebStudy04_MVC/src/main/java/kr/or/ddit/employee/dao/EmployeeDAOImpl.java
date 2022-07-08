package kr.or.ddit.employee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.EmployeeVO;

public class EmployeeDAOImpl implements EmployeeDAO {
	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public List<EmployeeVO> selectEmployeeList(Integer managerId) {

		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			EmployeeDAO mapperProxy = sqlSession.getMapper(EmployeeDAO.class);
			return mapperProxy.selectEmployeeList(managerId);
		}
			
	}

}
