package kr.or.ddit.proj.reply.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.proj.colleague.vo.ColleagueVO;
import kr.or.ddit.proj.reply.dao.ProjectReplyDAO;
import kr.or.ddit.proj.work.vo.ReplyVO;
import kr.or.ddit.proj.work.vo.WorkVO;

@Service
@Transactional
public class ProjectReplyServiceImpl implements ProjectReplyService{

	@Inject
	ProjectReplyDAO projectReplyDAO;
	
	@Override
	public List<ReplyVO> pagingRetrieveList(PagingVO<ReplyVO> pagingVO) {
		// TODO Auto-generated method stub
		pagingVO.setTotalRecord(projectReplyDAO.selectTotalRecord(pagingVO));
		List<ReplyVO> dataList = projectReplyDAO.pagingSelectReplyList(pagingVO);
		pagingVO.setDataList(dataList);
		return null;
	}

	@Override
	public void createReply(ReplyVO reply) {
		// TODO Auto-generated method stub
		projectReplyDAO.insertReply(reply);
	}

	@Override
	public List<ReplyVO> retrieveReplyList(ColleagueVO colleagueVO) {
		// TODO Auto-generated method stub
		return projectReplyDAO.selectReplyList(colleagueVO);
	}
	
}
