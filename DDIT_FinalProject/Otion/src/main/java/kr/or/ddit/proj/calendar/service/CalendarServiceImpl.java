package kr.or.ddit.proj.calendar.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.proj.calendar.dao.CalendarDAO;
import kr.or.ddit.proj.calendar.vo.CalendarVO;
import kr.or.ddit.proj.colleague.dao.ColleagueDAO;
import kr.or.ddit.proj.colleague.vo.ColleagueVO;
import kr.or.ddit.proj.msg.vo.MessageVO;

@Service
@Transactional
public class CalendarServiceImpl implements CalendarService{

	@Inject
	CalendarDAO calendarDAO;
	
	@Inject
	ColleagueDAO colleagDAO;
	
	@Override
	public List<CalendarVO> retrieveCalendarList(ColleagueVO colleague) {
		// TODO Auto-generated method stub
		return calendarDAO.selectCalendarList(colleague);
	}

	@Override
	public void createCalendar(CalendarVO calendar) {
		// TODO Auto-generated method stub
		calendar.setWorkCalStart(calendar.getWorkCalStart().replace("T", " "));
		calendar.setWorkCalEnd(calendar.getWorkCalEnd().replace("T", " "));
		calendarDAO.insertCalendar(calendar);
	}

	@Override
	public void modifyCalendar(CalendarVO calendar) {
		// TODO Auto-generated method stub
		calendar.setWorkCalStart(calendar.getWorkCalStart().replace("T", ""));
		calendar.setWorkCalEnd(calendar.getWorkCalEnd().replace("T", ""));
		calendarDAO.updateCalendar(calendar);
	}

	@Override
	public void removeCalendar(String workCalNum) {
		// TODO Auto-generated method stub
		// 해당 일정 지우기
		calendarDAO.deleteCalendar(workCalNum);
	}

	@Override
	public CalendarVO retrieveCalendarView(CalendarVO calendar) {
		// TODO Auto-generated method stub
		return calendarDAO.selectCalendarView(calendar);
	}

	// 페이징 처리용 캘린더 리스트
	@Override
	public List<CalendarVO> pagingCalendarList(PagingVO<CalendarVO> pagingVO) {
		// TODO Auto-generated method stub
		pagingVO.setTotalRecord(calendarDAO.selectTotalRecord(pagingVO));
		List<CalendarVO> dataList = calendarDAO.pagingCalendarList(pagingVO);
		pagingVO.setDataList(dataList);
		return dataList;
	}


	
}
