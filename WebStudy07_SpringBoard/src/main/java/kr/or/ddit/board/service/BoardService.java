package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.vo.AttatchVO;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.common.vo.PagingVO;

/**
 * 
 * 자유 관리를 위한 Business Logic Layer
 *
 */
public interface BoardService {
	/**
	 * 게시판 등록 로직
	 * @param board 등록할 게시물의 정보를 가진 vo
	 * @return OK, FAIL, PKDUPLICATED
	 */
	public void createBoard(BoardVO board);
	
	/**
	 * 게시글 목록 조회
	 * 
	 */
	public List<BoardVO> retrieveBoardList(PagingVO<BoardVO> pagingVO);
	
	/**
	 * 게시글 상세 조회
	 * @param boNo 조회할 게시글의 번호
	 * @return 
	 */
	public BoardVO retrieveBoard(Integer boNo);
	
	/**
	 * 게시글 수정
	 * @param board
	 */
	public void modifyBoard(BoardVO board);
	
	/**
	 * 게시글 삭제
	 * @param 삭제할 게시글 번호와 비밀번호
	 */
	public void removeBoard(BoardVO board);
	
	/**
	 * 파일 다운로드를 위한 메타데이터 조회
	 * @param attNo
	 * @return
	 */
	public AttatchVO download(int attNo);
}
