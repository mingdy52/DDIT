package comm.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import comm.vo.AtchFileVO;

public class AtchFileDAOImpl implements IAtchFileDAO {
	
	private static IAtchFileDAO dao;
	
	private AtchFileDAOImpl() {
		
	}
	
	public static IAtchFileDAO getInstance() {
		if(dao == null) {
			dao = new AtchFileDAOImpl();
		}
		
		return dao;
	}
	
	@Override
	public long insertAtchFile(SqlMapClient smc, AtchFileVO atchFileVO) {
		long cnt = 0;
		
		try {
			cnt = (long) smc.insert("atchFile.insertAtchFile", atchFileVO);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("첨부파일 저장 중 예외 발생", e);
		}
		
		return cnt;
	}

	@Override
	public int insertAtchFileDetail(SqlMapClient smc, AtchFileVO atchFileVO) {
		int cnt = 0;
		
		try {
			Object obj = smc.insert("atchFile.insertAtchFileDetail", atchFileVO);
			
			if(obj == null) {
				cnt = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("첨부파일 저장 중 예외 발생", e);
		}
		
		return cnt;
	}

	@Override
	public List<AtchFileVO> getAtchFileList(SqlMapClient smc, AtchFileVO atchFileVO) {
		
		List<AtchFileVO> atchFileList = new ArrayList<AtchFileVO>();
		
		try {
			atchFileList = smc.queryForList("atchFile.getAtchFileList",atchFileVO);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("첨부파일 목록 조회 중 예외 발생",e);
		}
		
		return atchFileList;
	}

	@Override
	public AtchFileVO getAtchFileDetail(SqlMapClient smc, AtchFileVO atchFileVO) {
		AtchFileVO fileVO = new AtchFileVO();
		
		try {
			fileVO = (AtchFileVO) smc.queryForObject("atchFile.getAtchFileDetail", atchFileVO);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("첨부파일 세부정보 조회 중 예외 발생", e);
		}
		
		return fileVO;
	}

}
