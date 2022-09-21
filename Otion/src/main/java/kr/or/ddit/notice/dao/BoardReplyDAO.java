package kr.or.ddit.notice.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.notice.vo.BoardReplyVO;

/**
 * 
 * @author 이아인
 * @since 2022. 9. 2.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 9. 2.      이아인       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Mapper
public interface BoardReplyDAO {
	/**
	 * 댓글 조회
	 * @param notiNum
	 * @return
	 */
	public List<BoardReplyVO> selectBoardReply(String boardNum);
	/**
	 * 댓글 등록
	 * @param boardReplyVO
	 * @return
	 */
	public int insertBoReply(BoardReplyVO boardReplyVO);
	/**
	 * 대댓글 등록
	 * @param boardReplyVO
	 * @return
	 */
	public int insertBoReReply(BoardReplyVO boardReplyVO);
	/**
	 * 댓글 수정
	 * @param boardReplyVO
	 * @return
	 */
	public int updateBoReply(BoardReplyVO boardReplyVO);
	/**
	 * 댓글 삭제
	 * @param delNum
	 * @return
	 */
	public int deleteBoReply(String delNum);
}
