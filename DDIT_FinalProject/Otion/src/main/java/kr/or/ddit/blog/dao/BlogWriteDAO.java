package kr.or.ddit.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.blog.vo.BlogHeartVO;
import kr.or.ddit.blog.vo.MyBlogPostVO;
import kr.or.ddit.blog.vo.PostMarkVO;
import kr.or.ddit.common.vo.PagingVO;

/**
 * 
 * @author 박채록
 * @since 2022. 8. 24.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 24.      박채록       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * 
 */

@Mapper
public interface BlogWriteDAO {
	/**
	 * 블로그 글 작성
	 * @return
	 */
	public int insertMyPost(MyBlogPostVO mypost);
	
	/**
	 * 북마크 토탈 수 
	 * @param memId
	 * @return
	 */
	public int selectmyMarkTotal(PagingVO<PostMarkVO> pagingVO);
	/**
	 * 북마크리스트 
	 * @param pagingVO
	 * @return
	 */
	public List<PostMarkVO> selectmyMarkList(PagingVO<PostMarkVO> pagingVO);
	/**
	 * 북마크 추가 
	 * @param postmark
	 * @return
	 */
	public int insertBookMK(PostMarkVO postmark);
	/**
	 * 북마크삭제
	 * @param postmark
	 * @return
	 */
	public int deleteBookMK(PostMarkVO postmark);
	
	/**
	 * 북마크에 추가여부 
	 * @param postmark
	 * @return
	 */
	public PostMarkVO selectMarkYn(PostMarkVO postmark);
	
	/**
	 * 블로그게시글 수정 
	 * @param mypost
	 * @return
	 */
	public int updatePost(MyBlogPostVO mypost);
	
	/**
	 * 하트수 
	 * @param postNum
	 * @return
	 */
	
	public BlogHeartVO selectHeartYn(BlogHeartVO heart);
	
	public int selectTotalHeart(String postNum);
	
	public int insertHeart(BlogHeartVO heart);
	
	public int deleteHeart(BlogHeartVO heart);
	
	public int updateHeartNo(String postNum);
}
