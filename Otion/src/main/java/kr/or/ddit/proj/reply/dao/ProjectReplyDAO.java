package kr.or.ddit.proj.reply.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.proj.colleague.vo.ColleagueVO;
import kr.or.ddit.proj.work.vo.ReplyVO;

@Mapper
public interface ProjectReplyDAO {
	
	public List<ReplyVO> pagingSelectReplyList(PagingVO<ReplyVO> pagingVO);

	public int selectTotalRecord(PagingVO<ReplyVO> pagingVO);

	public void insertReply(ReplyVO reply);

	public List<ReplyVO> selectReplyList(ColleagueVO colleagueVO);
}
