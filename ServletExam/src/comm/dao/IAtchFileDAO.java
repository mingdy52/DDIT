package comm.dao;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import comm.vo.AtchFileVO;

public interface IAtchFileDAO {
	
	/**
	 * 첨부파일 저장
	 * @param atchFileVO
	 * @return
	 */
	public long insertAtchFile(SqlMapClient smc, AtchFileVO atchFileVO);
	
	/**
	 * 첨부파일 세부정보 저장
	 * @param atchFileVO
	 * @return
	 */
	public int insertAtchFileDetail(SqlMapClient smc, AtchFileVO atchFileVO);
	
	/**
	 * 첨부파일목록 조회하기
	 * @param atchFileVO
	 * @return
	 */
	public List<AtchFileVO> getAtchFileList(SqlMapClient smc, AtchFileVO atchFileVO);
	
	/**
	 * 첨부파일 세부정보 조회 메서드
	 * @param atchFileVO 검색할 첨부파일 정보(아이디 및 순번)
	 * @return
	 */
	public AtchFileVO getAtchFileDetail(SqlMapClient smc, AtchFileVO atchFileVO);
}
