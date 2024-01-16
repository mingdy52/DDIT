package kr.or.ddit.expert.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.common.comcode.vo.ComCodeVO;
import kr.or.ddit.common.dao.NewsDAO;
import kr.or.ddit.common.login.vo.MemberPrincipal;
import kr.or.ddit.common.validate.InsertGroup;
import kr.or.ddit.common.validate.UpdateGroup;
import kr.or.ddit.common.vo.ExpertPaymentVO;
import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.common.vo.NewsVO;
import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.common.vo.ReportVO;
import kr.or.ddit.common.vo.SimpleSearchCondition;
import kr.or.ddit.exception.NotAuthorityException;
import kr.or.ddit.expert.dao.ExpertDAO;
import kr.or.ddit.expert.service.ExpertService;
import kr.or.ddit.expert.service.IexpertService;
import kr.or.ddit.expert.vo.EProdVO;
import kr.or.ddit.expert.vo.EprodRefundVO;
import kr.or.ddit.expert.vo.ExpFormVO;
import kr.or.ddit.expert.vo.ExpWishVO;
import kr.or.ddit.expert.vo.ExpertVO;
import kr.or.ddit.expert.vo.MyExpDetailVO;
import kr.or.ddit.expert.vo.MyExpertVO;
import kr.or.ddit.expert.vo.ReviewVO;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.proxy.annotation.Post;
/**
 * 
 * @author 박채록
 * @since 2022. 8. 5.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 5.      박채록       최초작성
 * 2022. 8. 17     박채록         구매하기작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */

@Slf4j
@Controller
@MultipartConfig
public class ExpertController {
	
	@Inject
	ExpertService expertservice;
	
	@Inject
	IexpertService iexpertservice;
	
	@Inject
	NewsDAO newsDao;
	
	@Inject
	ExpertDAO expertDAO;
	
//	전문가신청서
	@GetMapping("expert/form")
	public String expertForm(Authentication auth, Model model) {

		MemberVO member = null;
		String viewName = "";
		if (auth == null) {
			viewName = "redirect:/login";
		} else {
			member = ((MemberPrincipal) auth.getPrincipal()).getRealMember();
			String[] roleArr = member.getRoleListArray();
			boolean roleChk = Arrays.asList(roleArr).contains("ROLE_EXPERT");
			if (roleChk == true) {
				model.addAttribute("message", "해당 회원은 이미 전문가입니다.");
				viewName = "expert/expertprodlist";
			} else {
				model.addAttribute("member", member);
				viewName = "noleftMenu/expertForm";
			}
		}

		return viewName;
	}
	
	@RequestMapping(value="refundWhy", produces=MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.POST)
	@ResponseBody
	public  String refundReasion(
			@RequestParam String myEprod) {
		
		String refundWhy = expertservice.retrieveRefundWhy(myEprod);
		String result;
		if(refundWhy==null) {
			result = "{\"refundWhy\": \"없음.\"}";
		}else {
			result="{\"refundWhy\": \""+refundWhy+"\"}";
		}
		return result;
	}
	

	@PostMapping("expert/form")
	public String resistExpert(
			@Validated(InsertGroup.class) @ModelAttribute("expForm") ExpFormVO expForm
			, Errors errors
			, Model model) {
		
			String viewName="";
			if(!errors.hasErrors()) {
				expertservice.resistExpert(expForm);
				model.addAttribute("message", "신청이 완료되었습니다.");
				viewName="expert/expertprodlist";
			}else {
				model.addAttribute("message", "신청 중 오류발생!");
				viewName="noleftMenu/expertForm";
			}
			
		return viewName;
		
	}
	
	/**
	 * 전문가상품 상세조회
	 * @return
	 */
	@GetMapping("expert/prod/{eprodNum}")
	public String eprodView(
			Authentication auth
			, @PathVariable String eprodNum
			, Model model) {
		
		String viewName=null;
		MemberVO member = null;
		EProdVO eprod = expertservice.retrieveEprod(eprodNum);
		List<ReviewVO> rvList =  expertservice.retrieveEprodRVList(eprod.getEprodNum());
		log.info("auth********************  {}", auth);
		
		String[] eprodTagArr = eprod.getEprodTag().split(",");
		if (auth != null) {
			member = ((MemberPrincipal) auth.getPrincipal()).getRealMember();
				ExpWishVO wish = new ExpWishVO();
				wish.setEprodNum(eprodNum);
				wish.setMemId(member.getMemId());
				boolean WishYn = expertservice.WishYn(wish);
				model.addAttribute("eprod", eprod);
				model.addAttribute("WishYn", WishYn);
				model.addAttribute("rvList", rvList);
				model.addAttribute("eprodTagArr",eprodTagArr);
				viewName = "expert/eprodView";
		} else {
			model.addAttribute("eprod", eprod);
			model.addAttribute("rvList", rvList);
			model.addAttribute("eprodTagArr",eprodTagArr);
			viewName = "expert/eprodView";

		}
	
		return viewName;
	}

	
	
	@PostMapping("expert/prod/{eprodNum}")
	String eprodBuy(
			@PathVariable String eprodNum
		  , @ModelAttribute("myExpert") MyExpertVO myExpert
		  , Errors errors) {
		MemberVO member = ((MemberPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getRealMember();
		
		String buyerId = member.getMemId();
		String amount = myExpert.getEprodPrice();
		
		ExpertPaymentVO expertPayment = new ExpertPaymentVO();
		expertPayment.setAmount(amount);
		expertPayment.setMyEprod(myExpert.getEprodNum());
		expertPayment.setBuyerId(buyerId);
		String viewName = "";
		log.info("**********expertpay*****{}", expertPayment);
		log.info("**********myExpert*****{}", myExpert);
		
		if(!errors.hasErrors()) {
			myExpert.setBuyerId(buyerId);
			expertservice.insertMyexpert(myExpert);
			expertservice.purchaseEprod(expertPayment);
		
			viewName="redirect:/member/memPay";
		}else {
			viewName="redirect:/expert";
		}
		return viewName;
	}
	
	
	@GetMapping("expert/{expertId}")
	public String expertView(
			@PathVariable String expertId
			, Model model) {
		ExpertVO expert = expertservice.retrieveExpert(expertId);
		List<ComCodeVO> reportList = expertDAO.selectReportList();
		
		
		String exTag = expert.getExTag();
		String[] TagArr = exTag.split(","); 
		String assume = expert.getAssume();
		if(assume!=null) {
			String[] assumeArr = assume.split(",");
			expert.setAssumeArr(assumeArr);
		}else {
			String[] assumeArr = null;
			expert.setAssumeArr(assumeArr);
		}
		List<EProdVO> expertprod =  expert.getEprodList();
		String[] eprodTags = null;
		for (int i = 0; i < expertprod.size(); i++) {
			if (expertprod.get(i).getEprodTag() != null) {
				eprodTags = expertprod.get(i).getEprodTag().split(",");
				expertprod.get(i).setEprodTagArr(eprodTags);
			}
		}
		model.addAttribute("expert", expert);
		model.addAttribute("TagArr", TagArr);
		model.addAttribute("reportList", reportList);
		return "expert/expertView";
	}
	
	
	
	@GetMapping("myexpert")
	public String myexpert(
			@ModelAttribute("simpleCondition") SimpleSearchCondition simpleCondition,
			 @RequestParam(value="page", required=false, defaultValue="1") int currentPage,
				 @RequestParam(value="searchType", required=false) String searchType
				, @RequestParam(value="searchWord", required=false) String searchWord, 
			 Model model) {
		
		MemberVO member = ((MemberPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getRealMember();
		String memId = member.getMemId();
		
		MyExpertVO myExpert = new MyExpertVO();		
		myExpert.setBuyerId(memId);
		PagingVO<MyExpertVO> pagingVO = new PagingVO<>(8,5);
		pagingVO.setDetailCondition(myExpert);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSimpleCondition(simpleCondition);

		expertservice.retrieveMyexpert(pagingVO);
//		pagingVO.setDataList(myexpertList);
		
		model.addAttribute("pagingVO", pagingVO);	
//		model.addAttribute("myexpertList", pagingVO.getDataList());
		
		return "expert/myexpert";
	}
	
	
	
	@GetMapping("myexpert/{myEprod}")
	public String myexpertDetail(
			@PathVariable String myEprod
			, Model model) {
		
		MemberVO member = ((MemberPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getRealMember();
		
		String buyerId = expertservice.getBuyerId(myEprod);
		String expertId = expertservice.getExpertId(myEprod);
		log.info("buyerId : memberId = {} : {} ", buyerId, member.getMemId());
		if(buyerId.equals(member.getMemId())||expertId.equals(member.getMemId())) {
			List<MyExpDetailVO> detailList = expertservice.retrieveDetailMyexp(myEprod);
			String prodName = expertservice.selectEprodName(myEprod);
			
			model.addAttribute("detailList", detailList);
			model.addAttribute("member",member);
			model.addAttribute("myEprod",myEprod);
			model.addAttribute("buyerId",buyerId);
			model.addAttribute("expertId",expertId);
			model.addAttribute("prodName",prodName);
		} else {
			throw new NotAuthorityException();
		}
		
		
		return "expert/useView";
	}
	
	
	@PostMapping("myexpert/{myEprod}")
	public String myexpRequest(
			@PathVariable String myEprod
			,HttpServletRequest request
			,@RequestParam String myexpContent) {
		
		MemberVO member = ((MemberPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getRealMember();
		String viewName = "";
		MyExpDetailVO myExpDetail = new MyExpDetailVO();
		myExpDetail.setMyEprod(myEprod);
		myExpDetail.setMyexpWriter(member.getMemId());
		myExpDetail.setMyexpContent(myexpContent);
		expertservice.createMyexpDetail(myExpDetail);
		
		viewName = "redirect:/myexpert/{myEprod}";
		
		NewsVO news = new NewsVO();
		
		if (request.isUserInRole("ROLE_EXPERT")) {
			String buyerId = expertservice.getBuyerId(myEprod);
			news.setReceiverId(buyerId);
			news.setNewsMsgCode("ENEW04");
			news.setNewsId("heehee");
			newsDao.insertNews(news);
		}else {
			String expertId = expertservice.getExpertId(myEprod);
			news.setReceiverId(expertId);
			news.setNewsMsgCode("ENEW02");
			news.setNewsId("heehee");
			newsDao.insertNews(news);
		}

		
		return viewName;
		
	}
	
	@PutMapping("myexpert/{myEprod}")
	public String requestCompletion(
			@PathVariable String myEprod
			, @RequestParam String progressCode) {
		
		MyExpertVO myExpert = new MyExpertVO();
		myExpert.setMyEprod(myEprod);
		myExpert.setProgressCode(progressCode);

		iexpertservice.modifyProcessState(myExpert);
		
		return "redirect:/myexpert";
	}

	
	@GetMapping("myexpert/wish")
	public String expertWish(
			@ModelAttribute("simpleCondition") SimpleSearchCondition simpleCondition,
			 @RequestParam(value="page", required=false, defaultValue="1") int currentPage
			 , Model model) {
			
		MemberVO member = ((MemberPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getRealMember();
		PagingVO<ExpWishVO> pagingVO = new PagingVO<>(8,5);
		ExpWishVO mywish = new ExpWishVO();
		mywish.setMemId(member.getMemId());
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSimpleCondition(simpleCondition);
		pagingVO.setDetailCondition(mywish);
		List<ExpWishVO> myWishList =  expertservice.retrieveMyWishList(pagingVO);
		pagingVO.setDataList(myWishList);
		
		model.addAttribute("pagingVO", pagingVO);
		return "expert/expertwish";
	}
	
	@GetMapping("myexpert/review")
	public String expertReview(
			Model model) {
		MemberVO member = ((MemberPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getRealMember();
		String buyerId = member.getMemId();
		List<MyExpertVO> reviewList = expertservice.retrieveWritableRieview(buyerId);
		List<ReviewVO> myreview =  expertservice.retrieveMyreviewList(buyerId);
		model.addAttribute("reviewList", reviewList);
		model.addAttribute("myreview", myreview);
		
		return "expert/expertreview";
	}
	
	@GetMapping("myexpert/review/{myEprod}")
	String reviewForm(
			@PathVariable String myEprod
			,Model model) {
		
		MemberVO member = ((MemberPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getRealMember();
		MyExpertVO myexpert = new MyExpertVO();
		myexpert.setReviewYn("N");
		myexpert.setMyEprod(myEprod);
		String rvBuyerId = expertservice.retrieveRVBuyerId(myexpert);
		
		if(rvBuyerId.equals(member.getMemId())) {
			ReviewVO review = new ReviewVO();
			review.setMyEprod(myEprod);
			
			model.addAttribute("review", review);
		}else {
			throw new NotAuthorityException();
		}

		return "expert/reviewForm";
	}
	
	@PostMapping("myexpert/review/{myEprod}")
	String reviewWrite(
			@Validated(InsertGroup.class) @ModelAttribute("review") ReviewVO review
			, Errors error
			, Model model) {
		MemberVO member = ((MemberPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getRealMember();
		String reviewWriter = member.getMemId();
		review.setReviewWriter(reviewWriter);
		String viewName="";
		if (!error.hasErrors()) {
			expertservice.writeMyreview(review);
			viewName="redirect:/myexpert/review";
		}else {
			viewName="redirect:/myexpert/review/{myEprod}";
		}
		return viewName;
	}
	
	@GetMapping("myexpert/myreview/{reviewNum}")
	String reviewUpdateForm( 
			@PathVariable String reviewNum
			, Model model) {
		MemberVO member = ((MemberPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getRealMember();
		
		ReviewVO myreview = expertservice.retrieveMyreivew(reviewNum);
		String myEprod = myreview.getMyEprod();
		MyExpertVO myexpert = new MyExpertVO();
		myexpert.setReviewYn("Y");
		myexpert.setMyEprod(myEprod);
		String rvBuyerId = expertservice.retrieveRVBuyerId(myexpert);
		
		if(rvBuyerId.equals(member.getMemId())) {
			model.addAttribute("myreview", myreview);
		}else {
			throw new NotAuthorityException();
		}
		
		return "expert/UpdateReview";
	}
	
	@PostMapping("myexpert/myreview/{reviewNum}")
	String reviewUpdate(
			@PathVariable String reviewNum
			,@Validated(UpdateGroup.class)@ModelAttribute("myreview") ReviewVO myreview
			,Errors error) {
		
		String viewName = "";
		if(!error.hasErrors()) {
			expertservice.modifyMyreview(myreview);
			viewName = "redirect:/myexpert/review";
			
		}else {
			viewName = "redirect:/myexpert/myreview/{reviewNum}";
		}
		return viewName;
	}
	
	@PostMapping("myexpert/review/delete")
	String reviewDelete(
			@RequestParam String reviewNum) {
		
		expertservice.deleteReview(reviewNum);
		
		return "redirect:/myexpert/review";
	}
	
	@PostMapping("expert/report")
	String reportExpert(
			@RequestParam String targetId,
			@RequestParam String repContent,
			Authentication auth,
			Model model,
			RedirectAttributes attribute){
		
		MemberVO member = null;
		String viewName = "";
		String message = "";
		if (auth == null) {
			viewName = "redirect:/login";
		} else {
			member = ((MemberPrincipal) auth.getPrincipal()).getRealMember();
			if (member.getMemId().equals(targetId)) {
				message = "본인은 신고가 불가합니다.";
				attribute.addFlashAttribute("message", message);
				viewName = "redirect:/expert/" + targetId;
			} else {
				ReportVO report = new ReportVO();
				report.setTargetId(targetId);
				report.setRepContent(repContent);
				report.setReporterId(member.getMemId());
				expertservice.createReportExpert(report);
				message = "신고가 완료되었습니다.";
				attribute.addFlashAttribute("message", message);
				viewName = "redirect:/expert/" + targetId;
			}

		}

		return viewName;
	}

	
	@GetMapping("expert/formdetails")
	String ExpertFormList(			
			@ModelAttribute("simpleCondition") SimpleSearchCondition simpleCondition,
			 @RequestParam(value="page", required=false, defaultValue="1") int currentPage,
			 Model model) {
		MemberVO member = ((MemberPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getRealMember();
		String memId = member.getMemId();
		PagingVO<ExpFormVO> pagingVO = new PagingVO<>(8,5);
		
		ExpFormVO myExpForm = new ExpFormVO();
		myExpForm.setApplicantId(memId);
		pagingVO.setDetailCondition(myExpForm);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSimpleCondition(simpleCondition);
		expertservice.retrieveExpFormList(pagingVO);
		
		model.addAttribute("pagingVO", pagingVO);
		
		return "expert/ExpertFormdetails";
	}
}
