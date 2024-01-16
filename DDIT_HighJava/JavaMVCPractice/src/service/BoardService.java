package service;

import java.util.List;

import dao.BoardHwDAO;
import dao.ImBoardHwDAO;
import vo.BoardHwVO;

public class BoardService implements IBoardHwService {
	private ImBoardHwDAO ibo;
	
	
	
	public BoardService() {
		ibo = new BoardHwDAO();
	}

	@Override
	public List<BoardHwVO> displayAll() {
		// TODO Auto-generated method stub
		return ibo.displayAll();
	}

	@Override
	public int insertNew(BoardHwVO bo) {
		// TODO Auto-generated method stub
		return ibo.insertNew(bo);
	}

	@Override
	public int updateNew(BoardHwVO bo) {
		// TODO Auto-generated method stub
		return ibo.updateNew(bo);
	}

	@Override
	public int deletePost(String title) {
		// TODO Auto-generated method stub
		return ibo.deletePost(title);
	}

	@Override
	public List<BoardHwVO> search(BoardHwVO bo) {
		// TODO Auto-generated method stub
		return ibo.search(bo);
	}

	@Override
	public Boolean check(String title) {
		// TODO Auto-generated method stub
		return ibo.check(title);
	}

}
