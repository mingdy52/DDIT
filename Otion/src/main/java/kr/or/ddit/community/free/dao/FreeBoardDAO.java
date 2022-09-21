package kr.or.ddit.community.free.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.community.free.vo.FreeBoardVO;

/**
 * 
 * @author 서효림
 * @since 2022. 8. 16.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 16.       서효림          최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Mapper
public interface FreeBoardDAO {
	/**
	 * 게시글 작성
	 * @param freeBoard
	 * @return
	 */
	public int insertFreeBoard(FreeBoardVO freeBoard);
	/**
	 * 검색 조건에 일치하는 게시글의 수 조회
	 * @param pagingVO
	 * @return
	 */
	public int selectBoardCount(Map<String, Object>map);
	/**
	 * 검색 조건에 일치하는 게시글의 목록 조회
	 * @param pagingVO
	 * @return
	 */
	public List<FreeBoardVO> selectFreeBoardList(Map<String, Object> map);
	/**
	 * 게시글 상세 조회
	 * @param pagingVO
	 * @return
	 */
	public FreeBoardVO selectFreeBoard(String freeNum);
	/**
	 * 조회수 증가
	 * @param freeNum
	 * @return
	 */
	public int incrementHit(String freeNum);
	/**
	 * 게시글 수정
	 * @param freeBoardVO
	 * @return
	 */
	public int updateBoard(FreeBoardVO freeBoardVO);
	/**
	 * 게시글 삭제
	 * @param freeNum
	 * @return
	 */
	public int deleteBoard(String freeNum);
}
