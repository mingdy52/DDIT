package kr.or.ddit.proj.reply.service;

import java.util.List;

import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.proj.colleague.vo.ColleagueVO;
import kr.or.ddit.proj.work.vo.ReplyVO;

public interface ProjectReplyService {
	
	public List<ReplyVO> pagingRetrieveList(PagingVO<ReplyVO> pagingVO);

	public void createReply(ReplyVO reply);

	public List<ReplyVO> retrieveReplyList(ColleagueVO colleagueVO);
}
