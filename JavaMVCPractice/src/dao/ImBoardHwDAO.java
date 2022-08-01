package dao;

import java.util.List;

import vo.BoardHwVO;

public interface ImBoardHwDAO {
	
	public List<BoardHwVO> displayAll();
	
	public int insertNew(BoardHwVO bo);
	
	public int updateNew(BoardHwVO bo);
	
	public int deletePost(String title);
	
	public Boolean check(String title);

	public List<BoardHwVO> search(BoardHwVO bo);


}
