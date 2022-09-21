package kr.or.ddit.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.blog.vo.MyBlogPostVO;
import kr.or.ddit.community.coop.vo.CooBoardVO;
import kr.or.ddit.expert.vo.EProdVO;
import kr.or.ddit.expert.vo.ExpertVO;
import kr.or.ddit.notice.vo.NoticeVO;

@Mapper
public interface MainDAO {
	
	List<ExpertVO> expertList();
	
	List<EProdVO> eprodList();
	
	List<MyBlogPostVO> blogTrendList();
	
	List<CooBoardVO> cooBoardList();
	
	List<NoticeVO> noticeList();
}
