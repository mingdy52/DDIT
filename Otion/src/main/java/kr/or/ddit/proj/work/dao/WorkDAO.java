package kr.or.ddit.proj.work.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.proj.colleague.vo.ColleagueVO;
import kr.or.ddit.proj.issue.vo.IssueVO;
import kr.or.ddit.proj.work.vo.ReplyVO;
import kr.or.ddit.proj.work.vo.WorkVO;

@Mapper
public interface WorkDAO {

	public List<WorkVO> selectWorkList(PagingVO<WorkVO> paging);

	public int selectWorkCount(PagingVO<WorkVO> pagingVO);

	public WorkVO selectWork(String workNum);

	public List<WorkVO> selectOne(String workNum);

	public List<String> selectMemList(PagingVO<WorkVO> paging);
	
	public List<IssueVO> selectIssueList(WorkVO work);

	public List<ColleagueVO> selectAllMemList(String pjId);

	public void deleteAssignment(WorkVO work);

	public void insertdeleteAssignment(Map<String, String> param);

	public int modifyWork(WorkVO work);

	public int insertWork(WorkVO work);

	public WorkVO selectChildWork(PagingVO<WorkVO> paging);

	public List<WorkVO> selectAllWorkList(PagingVO<WorkVO> paging);
	
	public List<WorkVO> selectMyWorkList(PagingVO<WorkVO> pagingVO);

	public int selectMyWorkTotalRecord(PagingVO<WorkVO> pagingVO);

	public List<WorkVO> selectMyWork(ColleagueVO colleague);
	
	public int deleteWork(WorkVO work);
	
	public int insertReply(ReplyVO reply);
	
	public String selectColNum(ReplyVO reply);
	
	public List<ReplyVO> selectAllReplyList(ReplyVO reply);
	
	public void updateReply(ReplyVO reply);
	
	public void deleteReply(ReplyVO reply);
	
	public void deleteParentWoReplyNum(ReplyVO reply);
	public void deleteIssue(WorkVO work);
	public void deleteWorkNumReply(WorkVO work);
	public void deleteIssueReply(IssueVO issue);
	public List<WorkVO> selectParentWork(WorkVO work);
}
