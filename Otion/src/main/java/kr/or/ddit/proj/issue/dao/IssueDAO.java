package kr.or.ddit.proj.issue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.proj.issue.vo.IssueReplyVO;
import kr.or.ddit.proj.issue.vo.IssueVO;
import kr.or.ddit.proj.work.vo.WorkVO;

@Mapper
public interface IssueDAO {

	public void insertIssue(IssueVO issue);
	
	public IssueVO findIssue(IssueVO issue);
	
	public List<IssueVO> findAllIssue(IssueVO issue);
	
	public List<WorkVO> selectIssueList(PagingVO<WorkVO> paging);
	
	public int selectIssueCount(PagingVO<WorkVO> pagingVO);
	
	public List<IssueReplyVO> allIssueReplyList(IssueReplyVO reply);
	
	public void insertReply(IssueReplyVO issueReply);
	
	public IssueReplyVO selectOneReply(IssueReplyVO issueReply);
	
	public void modifyReply(IssueReplyVO issueReply);
	
	public void removeReply(IssueReplyVO issueReply);
	
	public void deleteIssue(IssueVO issue);

}
