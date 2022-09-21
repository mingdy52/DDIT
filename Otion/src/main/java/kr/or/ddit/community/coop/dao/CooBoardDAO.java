package kr.or.ddit.community.coop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.community.coop.vo.CooBoardVO;

@Mapper
public interface CooBoardDAO {
	
	public List<CooBoardVO> selectCooBoardList(PagingVO<CooBoardVO> pagingVO);
	
	public CooBoardVO selectCooBoard(String cooNum);
	
	public int selectCoo(PagingVO<CooBoardVO> pagingVO);
	
	public int incrementHitCoo(String cooNum);
	
	public int insertCooBoard(CooBoardVO cooBoard);
	
	public int updateCooBoard(CooBoardVO cooBoardVO);
	
	public int deleteCooBoard(String cooNum);
	
	/**
	 * 협업 모집중/모집완료변경 
	 * @param coo
	 */
	public int updateCoostate(CooBoardVO coo);
	
}
