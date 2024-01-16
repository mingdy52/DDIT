package kr.or.ddit.notice.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.notice.vo.NoticeVO;

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
public interface NoticeDAO {
	/**
	 * 공지작성
	 * @param notice
	 * @return
	 */
	public int insertNotice(NoticeVO notice);
	/**
	 * 검색 조건에 일치하는 공지 수 조회하기
	 * @param noticeVO
	 * @return
	 */
	public int selectNoticeCount(Map<String, Object> map);
	/**
	 * 검색 조건에 일치하는 공지 목록 조회하기
	 * @param pagingVO
	 * @return
	 */
	public List<NoticeVO> selectNoticeList(Map<String, Object> map);
	/**
	 * 조회수 증가
	 * @param notiNum
	 * @return
	 */
	public int incrementNotice(String notiNum);
	/**
	 * 상세 조회
	 * @param notiNum
	 * @return
	 */
	public NoticeVO selectNotice(String notiNum);
	/**
	 * 공지 수정
	 * @param noticeVO
	 * @return
	 */
	public int updateNotice(NoticeVO noticeVO);
	/**
	 * 공지삭제
	 * @param notiNum
	 * @return
	 */
	public int deleteNotice(String notiNum);
}
