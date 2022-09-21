package kr.or.ddit.blog.service;

import java.util.List;

import kr.or.ddit.blog.vo.BlogReplyVO;

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
public interface BlogReplyService {
	/**
	 * 해당 게시글에 있는 댓글 조회
	 * @param postNum
	 * @return
	 */
	public List<BlogReplyVO> retrieveReplyList(String postNum);
	/**
	 * 댓글 추가
	 * @param blogReplyVO
	 * @return
	 */
	public String createReply(BlogReplyVO blogReplyVO);
	/**
	 * 대댓글 추가
	 * @param blogReplyVO
	 * @return
	 */
	public String createReReply(BlogReplyVO blogReplyVO);
	/**
	 * 댓글 수정
	 * @param blogReplyVO
	 * @return
	 */
	public String modifyReply(BlogReplyVO blogReplyVO);
	/**
	 * 댓글 삭제
	 * @param delNum
	 * @return
	 */
	public String removeReply(String delNum);
}
