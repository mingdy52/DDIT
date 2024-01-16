package kr.or.ddit.blog.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
/**
 * 
 * @author 작성자명
 * @since 2022. 8. 28.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 28.      이아인       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */

import kr.or.ddit.blog.dao.BlogReplyDAO;
import kr.or.ddit.blog.vo.BlogReplyVO;
import kr.or.ddit.common.dao.NewsDAO;
import kr.or.ddit.common.vo.NewsVO;
import kr.or.ddit.enumpkg.ComCode;
@Service
public class BlogReplyServiceImpl implements BlogReplyService {
	@Inject
	BlogReplyDAO replyDAO;
	
	@Inject
	private NewsDAO newsDAO;
	
	//해당게시글에 있는 댓글 조회
	@Override
	public List<BlogReplyVO> retrieveReplyList(String postNum) {
		List<BlogReplyVO> replyList =replyDAO.selectReplyList(postNum);
		return replyList;
	}
	//댓글 추가
	@Override
	public String createReply(BlogReplyVO blogReplyVO) {
		int cnt=replyDAO.insertReply(blogReplyVO);
		String msg=null;
		if(cnt>0) {
			NewsVO newsVO= new NewsVO();
			newsVO.setReceiverId(blogReplyVO.getBlogerId());
			newsVO.setNewsMsgCode(ComCode.NEW05.toString());
			newsVO.setNewsId(blogReplyVO.getPostNum());
			newsDAO.insertNews(newsVO);
			msg="성공";
		}else {
			msg="실패";
		}
		return msg;
	}
	//대댓글 추가
	@Override
	public String createReReply(BlogReplyVO blogReplyVO) {
		int cnt=replyDAO.insertReReply(blogReplyVO);
		String msg=null;
		if(cnt>0) {
			NewsVO newsVO= new NewsVO();
			newsVO.setPostId(blogReplyVO.getBlogerId());
			newsVO.setReceiverId(blogReplyVO.getOwnerWriter());
			newsVO.setNewsMsgCode(ComCode.NEW06.toString());
			newsVO.setNewsId(blogReplyVO.getPostNum());
			newsDAO.insertNews(newsVO);
			msg="성공";
		}else {
			msg="실패";
		}
		return msg;
	}
	//댓글 수정
	@Override
	public String modifyReply(BlogReplyVO blogReplyVO) {
		int cnt=replyDAO.updateReply(blogReplyVO);
		String msg=null;
		if(cnt>0) {
			msg="성공";
		}else {
			msg="실패";
		}
		return msg;
	}
	//댓글 삭제
	@Override
	public String removeReply(String delNum) {
		int cnt=replyDAO.deleteReply(delNum);
		String msg=null;
		if(cnt>0) {
			msg="성공";
		}else {
			msg="실패";
		}
		return msg;
	}
}
