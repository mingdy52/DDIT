package kr.or.ddit.props.service;

import java.util.List;

import kr.or.ddit.props.dao.DataBasePropertyDAO;
import kr.or.ddit.props.dao.DataBasePropertyDAOImpl;
import kr.or.ddit.vo.DataBasePropertyVO;

public class DataBasePropertyServiceImpl implements DataBasePropertyService {
	
	DataBasePropertyDAO dao = new DataBasePropertyDAOImpl();
	
	@Override
	public List<DataBasePropertyVO> retrieveDataBaseProperties() {
		List<DataBasePropertyVO> dataList = dao.selectDataBaseProperties();
		dataList.stream().forEach((vo) -> {
			System.out.println(vo.getPropertyValue());
		});
		return dataList;
	}

}
