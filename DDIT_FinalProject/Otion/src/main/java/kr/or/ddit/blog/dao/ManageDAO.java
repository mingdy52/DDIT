package kr.or.ddit.blog.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.blog.vo.BlogCateVO;
import kr.or.ddit.blog.vo.BlogReplyVO;
import kr.or.ddit.blog.vo.MyBlogPostVO;
import kr.or.ddit.common.vo.PagingVO;

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
@Mapper
public interface ManageDAO {
	/**
	 * 카테고리 페이징처리를 위한 레코드 수 조회
	 * @param catePagingVO
	 * @return
	 */
	public int cateTotalRecord(PagingVO<BlogCateVO> catePagingVO);
	
	/**
	 * 카테고리 페이징 검색 조회
	 * @param catePagingVO
	 * @return
	 */
	public List<BlogCateVO> selectJsonCate(PagingVO<BlogCateVO> catePagingVO);
	/**
	 * 카테고리 이름 중복 확인
	 * @param updateMap
	 * @return
	 */
	public int checkNm(Map<String, String> updateMap);
	/**
	 * 카테고리 이름 수정
	 * @param upName
	 * @param cateNum
	 * @return
	 */
	public int updateCateNm(Map<String, String> updateMap);
	/**
	 * 카테고리 삭제
	 * @param delNum
	 * @return
	 */
	public int deleteCateNm(String delNum);
	/**
	 * 카테고리 관련 게시글 삭제
	 * @param delNum
	 */
	public void deletePost(String delNum);
	/**
	 * 블로그 Id가져오기
	 * @param memId
	 * @return
	 */
	public String selectBlogId(String memId);
	/**
	 * 블로그 카테고리 추가
	 * @param createMap
	 * @return
	 */
	public int insertCate(Map<String, String> createMap);
	
	/**
	 * 블로그 게시글 페이징처리를 위한 레코드 수 조회 
	 * @param postPagingVO
	 * @return
	 */
	public int postTotalRecord(PagingVO<MyBlogPostVO> postPagingVO);
	/**
	 * 블로그 게시글 페이징 처리 및 검색
	 * @param postPagingVO
	 * @return
	 */
	public List<MyBlogPostVO> selectPostList(PagingVO<MyBlogPostVO> postPagingVO);
	/**
	 * 블로그 게시글 삭제처리
	 * @param myBlogPostVO
	 * @return
	 */
	public int deletePostList(MyBlogPostVO myBlogPostVO);
	/**
	 * 댓글관리 페이징처리를 위한 레코드 수 조회
	 * @param replyPagingVO
	 * @return
	 */
	public int replyTotalRecord(PagingVO<BlogReplyVO> replyPagingVO);
	/**
	 * 댓글관리 페이징 처리 및 검색
	 * @param replyPagingVO
	 * @return
	 */
	public List<BlogReplyVO> selectReplyList(PagingVO<BlogReplyVO> replyPagingVO);
	/**
	 * 블로그 댓글 삭제처리
	 * @param blogReply
	 * @return
	 */
	public int deleteReplyList(BlogReplyVO blogReplyVO);
}
