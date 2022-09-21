package kr.or.ddit.common.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.common.dao.NewsDAO;
import kr.or.ddit.common.vo.NewsVO;

@Service
@Transactional
public class NewsServiceImpl implements NewsService {
	
	@Inject
	NewsDAO newsDAO;
	
	@Override
	public List<NewsVO> retrievemyNewsList(NewsVO news) {
		
		List<NewsVO> newsList = newsDAO.selectMyNews(news);
		return newsList;
	}

	@Override
	public void modifyNews(NewsVO single) {
		// TODO Auto-generated method stub
		newsDAO.updateNewsChk(single);
	}

}
