package kr.or.ddit.blog.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.blog.vo.BlogHeartVO;
import kr.or.ddit.blog.vo.MyBlogPostVO;
import kr.or.ddit.blog.vo.PostMarkVO;
import kr.or.ddit.common.vo.PagingVO;

public interface BlogWriteService {
	
	/**
	 * 블로그 글 작성 
	 * @param mypost
	 * @return
	 */
	public int createMypost(MyBlogPostVO mypost);
	
	/**
	 * 북마크리스트 
	 * @param pagingVO
	 * @return
	 */
	public List<PostMarkVO> retrieveMyMarkList(PagingVO<PostMarkVO> pagingVO);
	
	/**
	 * 북마크 추가 
	 * @param postmark
	 * @return
	 */
	public int createBookMark(PostMarkVO postmark);
	
	/**
	 * 북마크 삭제
	 * @param postmark
	 * @return
	 */
	public int deleteBookMark(PostMarkVO postmark);
	
	/**
	 * 북마크 존재여부 
	 * @param postmark
	 * @return
	 */
	public boolean MarkYn(PostMarkVO postmark);
	
	/**
	 * 블로그수정 
	 * @param postmark
	 */
	public int modifyPost(Map<String, Object> map);
	
	public boolean heartYn(BlogHeartVO heart);
	
	public int createHeart(BlogHeartVO heart);
	
	public int deleteHeart(BlogHeartVO heart);
	
	public int countHeart(String postNum);
	
	public int modifyHeartNo(String postNum);
}
