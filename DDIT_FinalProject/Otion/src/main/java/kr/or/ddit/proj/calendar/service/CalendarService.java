package kr.or.ddit.proj.calendar.service;

import java.util.List;

import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.proj.calendar.vo.CalendarVO;
import kr.or.ddit.proj.colleague.vo.ColleagueVO;
import kr.or.ddit.proj.work.vo.WorkVO;

/**
 * @author 작성자명
 * @since 2022. 8. 17.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 16.      고정현       캘린더 CRUD 작성완료
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
public interface CalendarService {
	
	public List<CalendarVO> pagingCalendarList(PagingVO<CalendarVO> paging);
	
	/**
	 * 해당 회원의 일정 리스트 조회
	 * @param memId 
	 * @return
	 */
	public List<CalendarVO> retrieveCalendarList(ColleagueVO colleague);
	
	/**
	 * 일정 삽입
	 * @param calendar
	 */
	public void createCalendar(CalendarVO calendar);
	
	/**
	 * 일정 수정
	 * @param calendar
	 */
	public void modifyCalendar(CalendarVO calendar);
	
	/**
	 * 일정 삭제
	 * @param workCalNum
	 */
	public void removeCalendar(String workCalNum);

	public CalendarVO retrieveCalendarView(CalendarVO calendar);
}
