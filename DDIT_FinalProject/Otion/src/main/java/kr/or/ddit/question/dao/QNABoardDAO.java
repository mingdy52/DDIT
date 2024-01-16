package kr.or.ddit.question.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.question.vo.QNABoardVO;

/**
 * 
 * @author 서효림
 * @since 2022. 8. 22.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 22.       서효림          최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */

@Mapper
public interface QNABoardDAO {
	/**
	 * 게시글 작성
	 * @param qnaBoard
	 * @return
	 */
	public int insertQNABoard(QNABoardVO qnaBoard);
	/**
	 * 검색 조건과 일치하는 게시글의 수 조회
	 * @param pagingVO
	 * @return
	 */
	public int selectQNABoardCount(Map<String, Object> ma);
	/**
	 * 검색 조건에 일치하는 게시글의 목록 조회
	 * @param pagingVO
	 * @return
	 */
	public List<QNABoardVO> selectQNABoardList(Map<String, Object> ma);
	
	/**
	 * 
	 * @param answer 답변 정보
	 * @return
	 */
	public int updateAnswer(QNABoardVO answer);
	
	/**
	 * 문의글 회원 확인
	 * @param memNick
	 * @return
	 */
	public MemberVO checkMember(String memNick);
	
	/**
	 * 조회수 증가
	 * @param qnaNum
	 * @return
	 */
	public int incrementHitQNA(String qnaNum);
	/**
	 * 상세 조회
	 * @param qnaNum
	 * @return
	 */
	public QNABoardVO selectQNABoard(String qnaNum);
	
	/**
	 * 본인확인을 위한 비밀번호
	 * @param qnaNum
	 * @return
	 */
	public QNABoardVO selectQNAPass(String qnaNum);
	/**
	 * 게시글 수정
	 * @param qnaBoardVO
	 * @return
	 */
	public int updateQNABoard(QNABoardVO qnaBoardVO);
	/**
	 * 게시글 삭제
	 * @param qnaNum
	 * @return
	 */
	public int deleteQNABoard(String qnaNum);
	
}
