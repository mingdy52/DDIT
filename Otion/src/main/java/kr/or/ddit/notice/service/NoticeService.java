package kr.or.ddit.notice.service;

import java.util.Map;

import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.notice.vo.NoticeVO;

/**
 * 
 * @author 서효림
 * @since 2022. 8. 24.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 24.       서효림          최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
public interface NoticeService {
	/**
	 * 목록조회
	 * @param pagingVO
	 * @return
	 */
	public void findAllNotice(PagingVO<NoticeVO> pagingVO);
	/**
	 * 상세조회
	 * @param notiNum
	 * @return
	 */
	public NoticeVO findDetailNotice(String notiNum);
	/**
	 * 작성
	 * @param notice
	 */
	public ServiceResult createNotice(NoticeVO notice);
	/**
	 * 수정
	 * @param notice
	 */
	public ServiceResult modifyNotice(Map<String, Object> map);
	/**
	 * 삭제
	 * @param notice
	 */
	public ServiceResult removeNotice(String notiNum);
}
