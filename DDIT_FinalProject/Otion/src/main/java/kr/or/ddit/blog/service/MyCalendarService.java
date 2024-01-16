package kr.or.ddit.blog.service;
/**
 * 
 * @author 작성자명
 * @since 2022. 8. 21.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 21.      이아인       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */

import java.util.List;

import kr.or.ddit.blog.vo.MyCalendarVO;

public interface MyCalendarService {
	/**
	 * 회원의 일정 리스트 조회 
	 * @param sessionId
	 * @return
	 */
	public List<MyCalendarVO> retriveMycalList(String sessionId);
	
	/**
	 * 일정 삽입
	 * @param myCalendarVO
	 */
	public int createMyCal(MyCalendarVO myCalendarVO);
	/**
	 * 일정 수정
	 * @param myCalendarVO
	 * @return
	 */
	public int modifyCalendar(MyCalendarVO myCalendarVO);
	/**
	 * 일정 삭제
	 * @param calNum
	 * @return
	 */
	public int removeCalendar(String calNum);
}
