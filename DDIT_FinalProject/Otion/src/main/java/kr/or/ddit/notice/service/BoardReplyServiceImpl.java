package kr.or.ddit.notice.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.common.dao.NewsDAO;
import kr.or.ddit.common.vo.NewsVO;
import kr.or.ddit.enumpkg.ComCode;
import kr.or.ddit.notice.dao.BoardReplyDAO;
import kr.or.ddit.notice.vo.BoardReplyVO;

/**
 * 
 * @author 이아인
 * @since 2022. 9. 2.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 9. 2.      이아인       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Service
public class BoardReplyServiceImpl implements BoardReplyService {
	@Inject
	BoardReplyDAO replyDAO;
	
	@Inject
	private NewsDAO newsDAO;
	
	//댓글조회
	@Override
	public List<BoardReplyVO> boardReplyList(String boardNum) {
		List<BoardReplyVO> notiReplyList=replyDAO.selectBoardReply(boardNum);
		return notiReplyList;
	}
	//댓글작성
	@Override
	public String createBoReply(BoardReplyVO boardReplyVO) {
		int cnt=replyDAO.insertBoReply(boardReplyVO);
		String msg=null;
		if(cnt>0) {
			NewsVO newsVO= new NewsVO();
			newsVO.setReceiverId(boardReplyVO.getBoardWriter());
			newsVO.setNewsMsgCode(ComCode.NEW05.toString());
			newsVO.setNewsId(boardReplyVO.getBoardNum());
			newsDAO.insertNews(newsVO);
			msg="성공";
		}else {
			msg="실패";
		}
		return msg;

	}
	//대댓글 작성
	@Override
	public String createBoreReply(BoardReplyVO boardReplyVO) {
		int cnt=replyDAO.insertBoReReply(boardReplyVO);
		String msg=null;
		if(cnt>0) {
			NewsVO newsVO= new NewsVO();
			newsVO.setReceiverId(boardReplyVO.getOwnerWriter());
			newsVO.setNewsMsgCode(ComCode.NEW06.toString());
			newsVO.setNewsId(boardReplyVO.getBoardNum());
			newsDAO.insertNews(newsVO);
			msg="성공";
		}else {
			msg="실패";
		}
		return msg;
	}
	//댓글 수정
	@Override
	public String modifyBoReply(BoardReplyVO boardReplyVO) {
		int cnt=replyDAO.updateBoReply(boardReplyVO);
		String msg=null;
		if(cnt>0) {
			msg="성공";
		}else {
			msg="실패";
		}
		return msg;
	}
	@Override
	public String removeBoReply(String delNum) {
		int cnt=replyDAO.deleteBoReply(delNum);
		String msg=null;
		if(cnt>0) {
			msg="성공";
		}else {
			msg="실패";
		}
		return msg;
	}
}