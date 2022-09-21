package kr.or.ddit.member.mypage.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.common.login.vo.MemberPrincipal;
import kr.or.ddit.common.vo.ExpertPaymentVO;
import kr.or.ddit.common.vo.ExpertRefundVO;
import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.common.vo.WorkPaymentVO;
import kr.or.ddit.common.vo.WorkRefundVO;
import kr.or.ddit.member.mypage.service.MyPageService;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author 이아인
 * @since 2022. 8. 13.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 13.      이아인       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */

@Slf4j
@Controller
@RequestMapping("/member")
public class MyPayController {
	@Inject
	MyPageService myService;
	
	// expert구매내역조회
	@GetMapping("memPay")
	public String memPay(@RequestParam(name = "exPage", required = false, defaultValue = "1") int currentPage
			,@RequestParam(name = "startDate", required = false) String startDate
			,@RequestParam(name = "endDate", required = false) String endDate
			,@RequestHeader(value = "accept", required = false, defaultValue = "text/html") String accept
			,Model model) {
		log.info("시작날짜:{}",startDate);
		log.info("끝날짜:{}",endDate);
		String viewName = null;
		 MemberVO mv=((MemberPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRealMember();
		 String memId=mv.getMemId();
		if (StringUtils.containsIgnoreCase(accept, "json")) {
			viewName = expertJsonData(currentPage,memId, model,startDate,endDate);
		} else {
			viewName = "member/memPay";
		}

		return viewName;
	}

	public String expertJsonData(int currentPage,String memId,Model model,String startDate,String endDate) {
		PagingVO<ExpertPaymentVO> expPagingVO = new PagingVO<>(8, 5);
		expPagingVO.setCurrentPage(currentPage);
		expPagingVO.setDetailCondition(new ExpertPaymentVO());
		expPagingVO.getDetailCondition().setBuyerId(memId);
		expPagingVO.setStartDate(startDate);
		expPagingVO.setEndDate(endDate);
		myService.retrieveExpertPay(expPagingVO);

		model.addAttribute("expPagingVO", expPagingVO);
		return "jsonView";
	}

	// 협업툴 결제내역
	@GetMapping("coopPay")
	public String coopPay(@RequestParam(name = "coopPage", required = false, defaultValue = "1") int coopPage
			,@RequestParam(name = "startDate", required = false) String startDate
			,@RequestParam(name = "endDate", required = false) String endDate
			,@RequestHeader(value = "accept", required = false, defaultValue = "text/html") String accept
			, Model model
			) {
		log.info("시작날짜:{}",startDate);
		log.info("끝날짜:{}",endDate);
		String viewName = null;
		 MemberVO mv=((MemberPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRealMember();
		 String memId=mv.getMemId();
		if (StringUtils.containsIgnoreCase(accept, "json")) {
			viewName = coopJsonData(coopPage,memId, model,startDate,endDate);
		} else {
			viewName = "member/memPay";
		}
		return viewName;
	}

	public String coopJsonData(int coopPage,String memId, Model model,String startDate,String endDate) {
		PagingVO<WorkPaymentVO> coopPagingVO = new PagingVO<>(8, 5);
		coopPagingVO.setCurrentPage(coopPage);
		coopPagingVO.setDetailCondition(new WorkPaymentVO());
		coopPagingVO.getDetailCondition().setPjCreatorId(memId);
		coopPagingVO.setStartDate(startDate);
		coopPagingVO.setEndDate(endDate);
		myService.retrieveCoopPay(coopPagingVO);
			
		model.addAttribute("coopPagingVO", coopPagingVO);
		return "jsonView";
	}

	@RequestMapping("memRefund")
	public String processHTML() {
		return "member/memRefund";
	}
	// expert환불
	@RequestMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE,value="expRefund",method=RequestMethod.GET)
	public String expRefund(
			@RequestParam(name = "expPage", required = false, defaultValue = "1") int expPage
			,@RequestParam(name = "startDate", required = false) String startDate
			,@RequestParam(name = "endDate", required = false) String endDate
			,@RequestHeader(value = "accept", required = false, defaultValue = "text/html") String accept
			, Model model
			) {

		String viewName = null;
		 MemberVO mv=((MemberPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRealMember();
		 String memId=mv.getMemId();
		log.info("시작날짜:{}",startDate);
		log.info("끝날짜:{}",endDate);
		if (StringUtils.containsIgnoreCase(accept, "json")) {
			viewName = exReJsonData(expPage,memId, model,startDate,endDate);
		} else {
			processHTML();
		}
		return viewName;
	}
	
	public String exReJsonData(int expPage,String memId,Model model,String startDate,String endDate		
			) {
		PagingVO<ExpertRefundVO> expRefPagingVO = new PagingVO<>(8, 5);
		expRefPagingVO.setCurrentPage(expPage);
		expRefPagingVO.setDetailCondition(new ExpertRefundVO());
		expRefPagingVO.getDetailCondition().setBuyerId(memId);
		expRefPagingVO.setStartDate(startDate);
		expRefPagingVO.setEndDate(endDate);
		
		myService.retrieveExpRef(expRefPagingVO);
		model.addAttribute("expRefPagingVO", expRefPagingVO);
		return "jsonView";
	} 
	// 협업환불
	@RequestMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE,value="coopRefund", method=RequestMethod.GET)
	public String coopRefund(
			@RequestParam(name = "coopPage", required = false, defaultValue = "1") int coopPage
			,@RequestParam(name = "startDate", required = false) String startDate
			,@RequestParam(name = "endDate", required = false) String endDate
			,@RequestHeader(value = "accept", required = false, defaultValue = "text/html") String accept
			, Model model
			) {

		String viewName = null;
		log.info("시작날짜:{}",startDate);
		log.info("끝날짜:{}",endDate);
		 MemberVO mv=((MemberPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRealMember();
		 String memId=mv.getMemId();
		if (StringUtils.containsIgnoreCase(accept, "json")) {
			viewName = coopReJsonData(coopPage,memId,model,startDate,endDate);
		} else {
			processHTML();
		}
		return viewName;
	}
	
	public String coopReJsonData(int coopPage,String memId,Model model,String startDate,String endDate			
			) {
		PagingVO<WorkRefundVO> coopRefPagingVO = new PagingVO<>(8, 5);
		coopRefPagingVO.setCurrentPage(coopPage);
		coopRefPagingVO.setDetailCondition(new WorkRefundVO());
		coopRefPagingVO.getDetailCondition().setPjCreatorId(memId);
		coopRefPagingVO.setStartDate(startDate);
		coopRefPagingVO.setEndDate(endDate);
		
		myService.retrieveCoopRef(coopRefPagingVO);
		model.addAttribute("coopRefPagingVO", coopRefPagingVO);
		return "jsonView";
	} 
	
	@RequestMapping("test")
	public String dateTest() {
		return "member/dateTest";
	}
	
	
}
