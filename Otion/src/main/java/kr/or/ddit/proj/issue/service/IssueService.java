package kr.or.ddit.proj.issue.service;

import java.util.List;

import org.aspectj.util.IStructureModel;

import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.proj.issue.vo.IssueReplyVO;
import kr.or.ddit.proj.issue.vo.IssueVO;
import kr.or.ddit.proj.work.vo.WorkVO;

public interface IssueService {

	public IssueVO insertIssue(IssueVO issue);
	
	public List<IssueVO> findAllIssue(IssueVO issue);
	
	public List<WorkVO> findAll(PagingVO<WorkVO> pagingVO);
	
	public IssueVO findIssue(IssueVO issue);
	
	public List<IssueReplyVO> allIssueReplyList(IssueReplyVO reply);
	
	public List<IssueReplyVO> insertReply(IssueReplyVO issueReplyVO);
	
	public List<IssueReplyVO> modifyReply(IssueReplyVO issueReply);
	
	public List<IssueReplyVO> removeReply(IssueReplyVO issueReply);
	
	public void deletIssue(IssueVO issue);
	
		
}
