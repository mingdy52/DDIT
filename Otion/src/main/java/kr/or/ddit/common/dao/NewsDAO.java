package kr.or.ddit.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.common.vo.NewsVO;

/**
 * 
 * @author 심민경
 * @since 2022. 8. 16.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 16. 심민경               최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Mapper
public interface NewsDAO {
	
	public int insertNews(NewsVO newsVO);
	
	public List<NewsVO> selectMyNews(NewsVO news);
	
	public int updateNewsChk(NewsVO news);
}
