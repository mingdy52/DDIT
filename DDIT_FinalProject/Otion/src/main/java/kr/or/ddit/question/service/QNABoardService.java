package kr.or.ddit.question.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.enumpkg.ServiceResult;
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
public interface QNABoardService {
	/**
	 * 작성
	 * @param qnaBoard
	 */
	public ServiceResult create(QNABoardVO qnaBoard);
	/**
	 * 목록조회
	 * @param pagingVO
	 * @return
	 */
	public void findAllQNA(Map<String, Object> map, PagingVO<QNABoardVO> pagingVO);
	/**
	 * 상세조회
	 * @param qnaNum
	 * @return
	 */
	public QNABoardVO findDetail(String qnaNum);
	
	/**
	 * 문의글 답변
	 * @param answer 답변정보
	 * @return
	 */
	public ServiceResult answerQna(QNABoardVO answer);
	/**
	 * 수정
	 * @param qnaBoard
	 * @return 
	 */
	public ServiceResult modify(QNABoardVO qnaBoard);
	
	/**
	 * 삭제
	 * @param qnaBoard
	 */
	public ServiceResult remove(QNABoardVO qnaBoard);
	
}
