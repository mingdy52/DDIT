package kr.or.ddit.proj.work.service;

import java.util.List;

import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.proj.colleague.vo.ColleagueVO;
import kr.or.ddit.proj.work.vo.ReplyVO;
import kr.or.ddit.proj.work.vo.WorkVO;

public interface WorkService {

	public List<WorkVO> selectAll(PagingVO<WorkVO> paging);

	public List<WorkVO> findAll(PagingVO<WorkVO> pagingVO);

	public List<WorkVO> selectOne(WorkVO work);

	public List<ColleagueVO> selectAllMemList(String pjId);

	public int modifyWork(WorkVO work);

	public int insertWork(WorkVO work);

	public List<WorkVO> selectAllWorkList(PagingVO<WorkVO> paging);

	public List<WorkVO> retrieveMyWorkList(PagingVO<WorkVO> paging);

	public List<WorkVO> retriverMyWork(ColleagueVO colleague);
	
	public int deleteWork(WorkVO work);
	
	public List<ReplyVO> insertReply(ReplyVO reply);
	
	public List<ReplyVO> selectReply(ReplyVO reply);
	
	public List<ReplyVO> updateReply(ReplyVO reply);
	
	public List<ReplyVO> deleteReply(ReplyVO reply);
	public void deleteParentWork(WorkVO work);
}
