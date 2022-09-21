package kr.or.ddit.blog.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.blog.dao.MyCalendarDAO;
import kr.or.ddit.blog.vo.MyCalendarVO;

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
@Service
public class MyCalendarServiceImpl implements MyCalendarService {
	@Inject
	MyCalendarDAO myCalDAO;
	/**
	 * 회원의 일정 리스트 조회
	 */
	@Override
	public List<MyCalendarVO> retriveMycalList(String sessionId) {
		return myCalDAO.selectMyCalList(sessionId);
	}
	/**
	 * 일정 삽입
	 */
	@Override
	public int createMyCal(MyCalendarVO myCalendarVO) {
		
		myCalendarVO.setBlogId(myCalDAO.selectBlogId(myCalendarVO.getBlogerId()));
		myCalendarVO.setCalStart(myCalendarVO.getCalStart().replace("T",""));
		myCalendarVO.setCalEnd(myCalendarVO.getCalEnd().replace("T", ""));
		int cnt =myCalDAO.insertMyCal(myCalendarVO);
		return cnt;
	}
	/**
	 * 일정 수정
	 */
	@Override
	public int modifyCalendar(MyCalendarVO myCalendarVO) {
		myCalendarVO.setCalStart(myCalendarVO.getCalStart().replace("T",""));
		myCalendarVO.setCalEnd(myCalendarVO.getCalEnd().replace("T", ""));
		int cnt=myCalDAO.updateCalendar(myCalendarVO);
		return cnt;
	}
	/**
	 * 일정 삭제
	 */
	@Override
	public int removeCalendar(String calNum) {
		int cnt=myCalDAO.deleteCalendar(calNum);
		return cnt;
	}

}
