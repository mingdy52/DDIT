package kr.or.ddit.community.free.service;

import java.util.Map;

import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.community.free.vo.FreeBoardVO;
import kr.or.ddit.enumpkg.ServiceResult;

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

public interface FreeBoardService {
	/**
	 * 게시글 목록 조회
	 * @param pagingVO
	 * @return
	 */
	public void findAll(PagingVO<FreeBoardVO> pagingVO);
	/**
	 * 게시글 상세 조회
	 * @param freeNum
	 * @return
	 */
	public FreeBoardVO find(String freeNum);
	/**
	 * 게시글 작성
	 * @param freeboard
	 */
	public ServiceResult create(FreeBoardVO freeBoard);
	/**
	 * 게시글 수정
	 * @param freeBoard
	 */
	public ServiceResult modify(Map<String, Object> map);
	/**
	 * 게시글 삭제
	 * @param freeBoard
	 */
	public ServiceResult remove(String freeNum);
}
