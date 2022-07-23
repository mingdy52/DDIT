package kr.or.ddit.board.service;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;

import kr.or.ddit.AbstractTest;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.common.vo.PagingVO;

public class BoardServiceImplTest extends AbstractTest {
	
	@Inject
	BoardService service;
	
	@Test
	public void testCreateBoard() {
		fail("Not yet implemented");
	}

	@Test
	public void testRetrieveBoardList() {
		// given
		PagingVO<BoardVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(1);
		// when
		service.retrieveBoardList(pagingVO);
		// then
		assertNotNull(pagingVO);
		
	}

	@Test
	public void testRetrieveBoard() {
		fail("Not yet implemented");
	}

	@Test
	public void testModifyBoard() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveBoard() {
		fail("Not yet implemented");
	}

	@Test
	public void testBoardServiceImpl() {
		fail("Not yet implemented");
	}

}
