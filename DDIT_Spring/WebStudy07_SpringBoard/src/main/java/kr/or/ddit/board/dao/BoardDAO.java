package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.common.vo.PagingVO;

@Mapper
public interface BoardDAO {
	
	/**
	 * 게시글 등록
	 * @param member
	 * @return 등록한 레코드 수 > 0 :  성공, else : 실패
	 */
	public int insertBoard(BoardVO board);
	
	/**
	 * 페이징 처리를 위한 레코드 수 조회
	 * @param pagingVO : 검색조건을 가진 VO
	 * @return
	 */
	public int selectTotalRecord(PagingVO<BoardVO> pagingVO);
	
	/**
	 * 게시글 목록 조회
	 * @return 존재하지 않는 경우, size()==0
	 */
	public List<BoardVO> selectBoardList(PagingVO<BoardVO> pagingVO);
	
	/**
	 * 게시글 조회
	 * @param memId 조회할 회원의 아이디
	 * @return 존재하지 않으면 null 값을 반환
	 */
	public BoardVO selectBoard(Integer boNo);
	
	/**
	 * 조회수 증가
	 * @param boNo
	 * @return
	 */
	public int incrementHit(int boNo);
	
	/**
	 * 게시글 수정
	 * @param member 수정할 회원의 정보를 가진 VO
	 * @return 수정한 레코드 수 if>0 : 성공, else : 실패
	 */
	public int updateBoard(BoardVO board);
	
	/**
	 * 게시글 삭제(???)
	 * @param memId 삭제할 회원의 아이디
	 * @return 삭제한 레코드 수 if>0 : 성공, else : 실패
	 */
	public int deleteBoard(Integer boNo);
}
