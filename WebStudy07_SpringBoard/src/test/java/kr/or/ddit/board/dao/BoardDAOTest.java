package kr.or.ddit.board.dao;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import javax.inject.Inject;

import org.junit.Test;

import kr.or.ddit.AbstractTest;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.common.vo.PagingVO;


public class BoardDAOTest extends AbstractTest {
	
	@Inject // 컨테이너를 생성하는 방법만 다를 뿐 사용하는 방법은 같다.
	BoardDAO boardDAO;
	
	@Test
	public void testInsertBoard() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectTotalRecord() {
		// given
		PagingVO<BoardVO> pagingVO = new PagingVO<>();
		// when
		int cnt = boardDAO.selectTotalRecord(pagingVO);
		// then
		assertNotEquals(0, cnt);
	}

	@Test
	public void testSelectBoardList() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectBoard() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateBoard() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteBoard() {
		fail("Not yet implemented");
	}

}
