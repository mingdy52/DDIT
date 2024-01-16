package kr.or.ddit.common.service;

import java.util.List;

import kr.or.ddit.common.vo.NewsVO;

public interface NewsService {
	
	public List<NewsVO> retrievemyNewsList(NewsVO news);

	public void modifyNews(NewsVO single);
}
