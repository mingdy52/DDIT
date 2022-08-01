package dao;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import vo.BoardHwVO;

public interface ImBoardHwDAO {
	
	public List<BoardHwVO> displayAll(SqlMapClient smc);
	
	public int writeNew(SqlMapClient smc, BoardHwVO bo);
	
	public int updatePost(SqlMapClient smc, BoardHwVO bo);
	
	public int deletePost(SqlMapClient smc, String title);
	
	public List<BoardHwVO> search(SqlMapClient smc, BoardHwVO bo);


}
