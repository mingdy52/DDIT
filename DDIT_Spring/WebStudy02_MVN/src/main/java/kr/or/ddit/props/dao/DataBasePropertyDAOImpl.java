package kr.or.ddit.props.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.DataBasePropertyVO;

public class DataBasePropertyDAOImpl implements DataBasePropertyDAO {

	@Override
	public List<DataBasePropertyVO> selectDataBaseProperties() {
		List<DataBasePropertyVO> dataList = new ArrayList<>();

		// try with resource 구문
		try(
			// Closeable 객체 선언
			Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();
		){
			
			String sql = "SELECT PROPERTY_NAME, PROPERTY_VALUE, DESCRIPTION FROM DATABASE_PROPERTIES";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				DataBasePropertyVO vo = new DataBasePropertyVO();
				dataList.add(vo);
				vo.setPropertyName(rs.getString("PROPERTY_NAME"));
				vo.setPropertyValue(rs.getString("PROPERTY_VALUE"));
				vo.setDescription(rs.getString("DESCRIPTION"));
				
                
			}
			return dataList;
		} catch (SQLException e) {
			throw new RuntimeException(e); // 예외 종류를 checked -> unchecked 로 변경
		}
		
	}

}
