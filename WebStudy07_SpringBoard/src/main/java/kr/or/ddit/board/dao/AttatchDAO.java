package kr.or.ddit.board.dao;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.board.vo.AttatchVO;
import kr.or.ddit.board.vo.BoardVO;

/**
 * 첨부파일용 persistence Layer
 *
 */
@Mapper
public interface AttatchDAO {
	
	/**
	 * 게시글의 첨부파일 등록
	 * @param board
	 * @return
	 */
	public int insertAttatches(BoardVO board);
	
	/**
	 * 첨부파일 한건의 메타데이터 조회
	 * @param attNo
	 * @return
	 */
	public AttatchVO selectAttatch(int attNo);
	
	/**
	 * 다운로드수 증가
	 * @return
	 */
	public int incrementDowncount(int attNo);
	
	/**
	 * 여러개의 첨부파일 메타데이터 삭제
	 * @param board
	 * @return
	 */
	public int deleteAttatch(int attNo);
	public int deleteAttatches(BoardVO board);
	
//	업데이트랑 전체 목록 가져오기는 필요 없어~!
	
}
