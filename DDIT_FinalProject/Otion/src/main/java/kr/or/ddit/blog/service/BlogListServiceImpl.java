package kr.or.ddit.blog.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.blog.dao.BlogListDAO;
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
@Service
public class BlogListServiceImpl implements BlogListService {
	
	@Inject
	BlogListDAO blogDAO;

	@Override
	public void retrieveLatestList(PagingVO<MyBlogPostVO> pagingVO) {
		pagingVO.setTotalRecord(blogDAO.latestBlogRecord());
       	List<MyBlogPostVO> postList = blogDAO.latestBlogList(pagingVO);
       	pagingVO.setDataList(postList);
       	
	}
	
	@Override
	public List<MyBlogPostVO> retrieveTrendList() {
		List<MyBlogPostVO> trendList = blogDAO.trendBlogList();
		return trendList;
	}

	@Override
	public void retrieveSearchList(PagingVO<MyBlogPostVO> pagingVO) {
		pagingVO.setTotalRecord(blogDAO.searchBlogRecord(pagingVO));
       	List<MyBlogPostVO> postList = blogDAO.searchBlogList(pagingVO);
       	pagingVO.setDataList(postList);
	}
	
	
}
