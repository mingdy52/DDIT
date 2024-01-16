package kr.or.ddit.proj.calendar.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.proj.calendar.vo.CalendarVO;
import kr.or.ddit.proj.colleague.vo.ColleagueVO;

/**
 * @author 작성자명
 * @since 2022. 8. 17.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 17.      고정현       캘린더 CRUD 작성완료
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Mapper
public interface CalendarDAO {

	public List<CalendarVO> selectCalendarList(ColleagueVO collegue);

	public void insertCalendar(CalendarVO calendar);

	public void updateCalendar(CalendarVO calendar);

	public void deleteCalendar(String workCalNum);

	public CalendarVO selectCalendarView(CalendarVO calendar);

	public int selectTotalRecord(PagingVO<CalendarVO> pagingVO);

	public List<CalendarVO> pagingCalendarList(PagingVO<CalendarVO> pagingVO);
}
