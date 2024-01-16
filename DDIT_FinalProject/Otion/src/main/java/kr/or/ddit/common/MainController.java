package kr.or.ddit.common;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import kr.or.ddit.blog.service.BlogListService;
import kr.or.ddit.blog.vo.MyBlogPostVO;
import kr.or.ddit.common.dao.MainDAO;
import kr.or.ddit.common.login.vo.MemberPrincipal;
import kr.or.ddit.common.service.NewsService;
import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.common.vo.NewsVO;
import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.community.coop.vo.CooBoardVO;
import kr.or.ddit.expert.service.ExpertService;
import kr.or.ddit.expert.vo.EProdVO;
import kr.or.ddit.expert.vo.ExpertVO;
import kr.or.ddit.notice.service.NoticeService;
import kr.or.ddit.notice.vo.NoticeVO;
import lombok.extern.slf4j.Slf4j;

@Controller
public class MainController {
	
	@Inject
	NewsService newsService;
	
	@Inject
	MainDAO mainDAO;
	
	@RequestMapping("/")
	public String test(
			Model model) {
		
		List<MyBlogPostVO> blogList =  mainDAO.blogTrendList();
		List<ExpertVO> expertList = mainDAO.expertList();
		List<EProdVO> eprodList = mainDAO.eprodList();
		List<CooBoardVO> cooList = mainDAO.cooBoardList();
		List<NoticeVO> noticeList = mainDAO.noticeList();
		
		model.addAttribute("blogList",blogList);
		model.addAttribute("expertList",expertList);
		model.addAttribute("eprodList",eprodList);
		model.addAttribute("cooList",cooList);
		model.addAttribute("noticeList",noticeList);
		
		
		return "main";
	}
	
	@RequestMapping("/news")
	public String newsList(Model model,Authentication auth) {
		String viewName=null;
		if(auth!=null) {
			MemberVO member = ((MemberPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
					.getRealMember();
			NewsVO news = new NewsVO();
			
			news.setReceiverId(member.getMemId());
			
			List<NewsVO> newsList =  newsService.retrievemyNewsList(news);
			
			model.addAttribute("newsList", newsList);
			viewName="noleftMenu/newsList";
		}else {
			viewName="redirect:/login";
		}
		
		return viewName;
	}
	
	@ResponseBody
	@RequestMapping(value="myNews", produces=MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.GET)
	public List<NewsVO> HeaderNews(
			Authentication auth) {
		
		if(auth != null) {
			String memId = auth.getName();
			NewsVO news = new NewsVO();
			news.setReceiverId(memId);
			List<NewsVO> newsList = newsService.retrievemyNewsList(news);
			return newsList;
		}
		return null;
		
	}
	
	@RequestMapping(value="readAllNews",produces=MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String ReadNews(
			Authentication auth) {
		String memId = auth.getName();
		NewsVO news = new NewsVO();
		news.setReceiverId(memId);
		// 뉴스 리스트 받아오기
		List<NewsVO> newsList = newsService.retrievemyNewsList(news);
		for(NewsVO single : newsList) {
			newsService.modifyNews(single);
		}
		
		return "성공";
	}
}
