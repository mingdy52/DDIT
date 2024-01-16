package service;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import dao.BoardHwDAO;
import dao.ImBoardHwDAO;
import util.SqlMapClientFactory;
import vo.BoardHwVO;

public class BoardService implements IBoardHwService {
	private ImBoardHwDAO boDAO;
	private SqlMapClient smc;
	
	private static IBoardHwService boSer;
	
	private BoardService() {
		boDAO = BoardHwDAO.getInstance();
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IBoardHwService getInstance() {
		if(boSer==null) {
			boSer = new BoardService();
		}
		return boSer;
	}
	

	@Override
	public List<BoardHwVO> displayAll() {
		// TODO Auto-generated method stub
		return boDAO.displayAll(smc);
	}

	@Override
	public int writeNew(BoardHwVO bo) {
		
		return boDAO.writeNew(smc, bo);
	}

	@Override
	public int updatePost(BoardHwVO bo) {
		// TODO Auto-generated method stub
		return boDAO.updatePost(smc, bo);
	}

	@Override
	public int deletePost(String title) {
		// TODO Auto-generated method stub
		return boDAO.deletePost(smc, title);
	}

	@Override
	public List<BoardHwVO> search(BoardHwVO bo) {
		// TODO Auto-generated method stub
		return boDAO.search(smc, bo);
	}



	

}
