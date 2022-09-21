package kr.or.ddit.proj.post.service;

import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.common.vo.SimpleSearchCondition;
import kr.or.ddit.proj.calendar.dao.CalendarDAO;
import kr.or.ddit.proj.calendar.vo.CalendarVO;
import kr.or.ddit.proj.colleague.dao.ColleagueDAO;
import kr.or.ddit.proj.colleague.vo.ColleagueVO;
import kr.or.ddit.proj.msg.dao.MessageDAO;
import kr.or.ddit.proj.msg.vo.MessageReceiverVO;
import kr.or.ddit.proj.msg.vo.MessageVO;
import kr.or.ddit.proj.post.dao.ProjectMyPostDAO;
import kr.or.ddit.proj.post.vo.MyPostVO;
import kr.or.ddit.proj.reply.vo.WorkReplyVO;
import kr.or.ddit.proj.work.dao.WorkDAO;
import kr.or.ddit.proj.work.vo.WorkVO;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

/**
 * @author 작성자명
 * @since 2022. 8. 24.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 24.      고정현       해당 자신의 워크리스트 가져오기
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Service
@Transactional
public class ProjectMyPostServiceImpl implements ProjectMyPostService{

	@Inject
	ProjectMyPostDAO projectMyPostDAO;
	
	@Inject
	ColleagueDAO colleagueDAO;
	
	@Inject
	CalendarDAO calendarDAO;
	
	@Inject
	MessageDAO messageDAO;
	
	@Inject
	WorkDAO workDAO;
	
	@Override
	public PagingVO<?> retrieveMyPostList(PagingVO<?> pagingVO) {
		// TODO Auto-generated method stub
		// 로그인한 회원에 대한 팀원 정보 가져오기
		MyPostVO myPost = new MyPostVO();
		// 입력한 데이터 값
		ColleagueVO colleague = pagingVO.getColleague();
		// 해당 회원의 정보
		ColleagueVO selectColleague = colleagueDAO.selectColleage(pagingVO.getColleague());
		SimpleSearchCondition simpleCondition = pagingVO.getSimpleCondition();
		
		if(colleague.getSimpleCondition().getSearchType().equals("reply")) {
			// 댓글 가져오기
		}
		myPost.setColleagueVO(selectColleague);
		
		
		
		
		
		return null;
	}

}
