package service;

import java.util.List;

import vo.BoardHwVO;


public interface IBoardHwService {
	public List<BoardHwVO> displayAll();
	
	public int writeNew(BoardHwVO bo);
	
	public int updatePost(BoardHwVO bo);
	
	public int deletePost(String bo);
	
	public List<BoardHwVO> search(BoardHwVO bo);


}
