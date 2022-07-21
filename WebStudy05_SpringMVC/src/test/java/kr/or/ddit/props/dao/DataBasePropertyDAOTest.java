package kr.or.ddit.props.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import kr.or.ddit.vo.DataBasePropertyVO;

public class DataBasePropertyDAOTest {
	
	DataBasePropertyDAO dao = new DataBasePropertyDAOImpl();
	
	@Test
	public void testSelectDataBaseProperties() {
		List<DataBasePropertyVO> databaseList = dao.selectDataBaseProperties();
		System.out.println(databaseList);
		assertNotNull(databaseList);
		assertNotEquals(0, databaseList.size());
	}

}
