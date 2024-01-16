package kr.or.ddit.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.blog.vo.MyBlogPostVO;
import kr.or.ddit.common.vo.PagingVO;
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
@Mapper
public interface BlogListDAO {
	
	public int latestBlogRecord();
	public List<MyBlogPostVO> latestBlogList(PagingVO<MyBlogPostVO> pagingVO);
	public List<MyBlogPostVO> trendBlogList();
	
	public int searchBlogRecord(PagingVO<MyBlogPostVO> pagingVO);
	public List<MyBlogPostVO> searchBlogList(PagingVO<MyBlogPostVO> pagingVO);
}
