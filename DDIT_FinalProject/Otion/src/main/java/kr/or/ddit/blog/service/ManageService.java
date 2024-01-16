package kr.or.ddit.blog.service;
/**
 * 
 * @author 이아인
 * @since 2022. 8. 23.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 23.      이아인       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */

import java.util.List;
import java.util.Map;

import kr.or.ddit.blog.vo.BlogCateVO;
import kr.or.ddit.blog.vo.BlogReplyVO;
import kr.or.ddit.blog.vo.MyBlogPostVO;
import kr.or.ddit.common.vo.PagingVO;

public interface ManageService {
	/**
	 * 카테고리 페이징 처리 및 검색 조회
	 * @param catePaingVO
	 * @return
	 */
	public List<BlogCateVO> retrieveJsonCate(PagingVO<BlogCateVO> catePaingVO);
	
	/**
	 * 카테고리 이름 수정
	 * @param upName
	 * @param cateNum
	 * @return
	 */
	public int modifyCateNm(Map<String, String> updateMap);
	/**
	 * 카테고리 삭제
	 * @param delNum
	 * @return
	 */
	public int removeCateNm(String delNum);
	
	/**
	 * 카테고리 추가
	 * @param aa
	 * @return
	 */
	public int createCateNm(String inputData,String memId);
	/**
	 * 게시글 관리, 게시글 조회 및 페이징 ,검색
	 * @param postPagingVO
	 */
	public void retrievePostList(PagingVO<MyBlogPostVO> postPagingVO);
	/**
	 * 게시글 삭제처리
	 * @param myBlogPostVO
	 * @return
	 */
	public String removePost(MyBlogPostVO myBlogPostVO);
	/**
	 * 댓글관리, 뎃글조회 및 페이징, 검색
	 * @param replyPagingVO
	 */
	public void retrieveReplyList(PagingVO<BlogReplyVO> replyPagingVO);
	/**
	 * 댓글삭제
	 * @param blogReplyVO
	 * @return
	 */
	public String removeReply(BlogReplyVO blogReplyVO);
}
