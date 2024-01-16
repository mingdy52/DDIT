package kr.or.ddit.expert.controller;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.admin.vo.RevenueVO;
import kr.or.ddit.common.login.vo.MemberPrincipal;
import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.common.vo.SimpleSearchCondition;
import kr.or.ddit.expert.service.ExpertService;
import kr.or.ddit.expert.service.ExpertServiceImpl;
import kr.or.ddit.expert.service.IexpertService;
import kr.or.ddit.expert.vo.EProdVO;
import kr.or.ddit.expert.vo.ExpertVO;
import lombok.extern.slf4j.Slf4j;
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
 * </pre>
 */
@Slf4j
@Controller
@RequestMapping("/expert")
public class ExpertListController {
	
	@Inject
	ExpertService expertservice;
	
	@Inject
	IexpertService iexpertservice;
	
	@GetMapping
	public String EprodList(
			@RequestParam(name="eprodPage", required=false, defaultValue="1") int eprodPage,
			 @RequestHeader(value="accept", required=false, defaultValue="text/html") String accept,
			 @RequestParam(value="searchType", required=false) String searchType,
			 @RequestParam(value="searchWord", required=false) String searchWord,
			  @ModelAttribute("searchVO") SimpleSearchCondition searchVO,
			 Model model
			){
		String viewName=null;
		
		if(StringUtils.containsIgnoreCase(accept, "json")) {
			viewName = eprodJsonData(eprodPage, searchType, searchWord, searchVO, model);
		} else {
			viewName = "expert/expertprodlist";
		}
		return viewName;
	}
	
	
	@GetMapping("exp")
	public String expertList(
		  @RequestParam(name="expertPage", required=false, defaultValue="1") int expertPage
		 , @RequestHeader(value="accept", required=false, defaultValue="text/html") String accept
		 , @RequestParam(value="searchType", required=false) String searchType
		 , @RequestParam(value="searchWord", required=false) String searchWord
		 , @ModelAttribute("searchVO") SimpleSearchCondition searchVO
		 , Model model
		){
		String viewName=null;
		
		if(StringUtils.containsIgnoreCase(accept, "json")) {
			viewName = expertJsonData(expertPage, searchType, searchWord, searchVO, model);
		} else {
			viewName = "expert/expertprodlist";
		}
		return viewName;
	}
		
		
	
	public String eprodJsonData(
			int eprodPage
			, @RequestParam(value="searchType", required=false) String searchType
			, @RequestParam(value="searchWord", required=false) String searchWord
			, @ModelAttribute("searchVO") SimpleSearchCondition searchVO
			, Model model
			){
		PagingVO<EProdVO> eprodpagingVO = new PagingVO<>(6, 5);
		eprodpagingVO.setCurrentPage(eprodPage);
		eprodpagingVO.setSimpleCondition(searchVO);
		List<EProdVO> eprod = expertservice.retrieveEProdList(eprodpagingVO);
		
		for (int i = 0; i < eprod.size(); i++) {
			String eprodTag = eprod.get(i).getEprodTag();
			if (eprodTag != null) {
				eprod.get(i).setEprodTagArr(eprodTag.split(","));
			}else {
				eprod.get(i).setEprodTagArr(null);
			}

		}
		model.addAttribute("eprodpagingVO", eprodpagingVO);
		
		return  "jsonView";
	}
	
	
	public String expertJsonData(
			int expertPage
			, @RequestParam(value="searchType", required=false) String searchType
			, @RequestParam(value="searchWord", required=false) String searchWord
			, @ModelAttribute("searchVO") SimpleSearchCondition searchVO
			, Model model
			) {
		PagingVO<ExpertVO> expertpagingVO = new PagingVO<>(6,5);
		expertpagingVO.setCurrentPage(expertPage);
		expertpagingVO.setSimpleCondition(searchVO);
		expertservice.retrieveExpertList(expertpagingVO);
		model.addAttribute("expertpagingVO", expertpagingVO);
		return "jsonView";
		
	}
	
}
