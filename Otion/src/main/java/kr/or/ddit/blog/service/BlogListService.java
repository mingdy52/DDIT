package kr.or.ddit.blog.service;

import java.util.List;

import kr.or.ddit.blog.vo.MyBlogPostVO;
import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.proj.post.vo.MyPostVO;

/**
 * 
 * @author 박채록
 * @since 2022. 8. 26.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 26.      박채록       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * 
 */
public interface BlogListService {
	
	public void retrieveLatestList(PagingVO<MyBlogPostVO> pagingVO);
	
	public List<MyBlogPostVO> retrieveTrendList();
	
	public void retrieveSearchList(PagingVO<MyBlogPostVO> pagingVO);
	
}
