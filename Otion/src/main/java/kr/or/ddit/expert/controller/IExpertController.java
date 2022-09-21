package kr.or.ddit.expert.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
import org.springframework.web.bind.annotation.RequestMapping;
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
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.admin.vo.RevenueVO;
import kr.or.ddit.common.comcode.vo.ComCodeVO;
import kr.or.ddit.common.dao.NewsDAO;
import kr.or.ddit.common.login.vo.MemberPrincipal;
import kr.or.ddit.common.validate.DeleteGroup;
import kr.or.ddit.common.validate.InsertGroup;
import kr.or.ddit.common.validate.UpdateGroup;
import kr.or.ddit.common.vo.ExpertPaymentVO;
import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.common.vo.NewsVO;
import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.common.vo.SimpleSearchCondition;
import kr.or.ddit.exception.NotAuthorityException;
import kr.or.ddit.expert.dao.IexpertDAO;
import kr.or.ddit.expert.service.ExpertService;
import kr.or.ddit.expert.service.IexpertService;
import kr.or.ddit.expert.vo.EProdVO;
import kr.or.ddit.expert.vo.EprodRefundVO;
import kr.or.ddit.expert.vo.ExpertVO;
import kr.or.ddit.expert.vo.MyExpertVO;
import lombok.extern.slf4j.Slf4j;
@Controller
@Slf4j
@RequestMapping("iexpert")
@MultipartConfig
public class IExpertController {
	@Inject
	IexpertService iexpertservice;
	
	@Inject
	IexpertDAO iexpertDAO;
	
	@Inject
	ExpertService expertservice;
	
	@Inject
	NewsDAO newsDAO;
	/**
	 * 전문가프로필조회
	 * @return
	 */
	@GetMapping("profile")
	public String iexpertProfile(
			 Model model
			,RedirectAttributes redirectAttributes
			) {
		MemberVO member = ((MemberPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getRealMember();
		log.info("회원아이디를 알아보자!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! -{}",member.getMemId());
//		String expertId = "rock";
		String viewName = "";
		if(!member.getMemId().isEmpty()) {
			ExpertVO expert = expertservice.retrieveExpert(member.getMemId());
			String assume = expert.getAssume();
			if(assume!=null) {
				String[] assumeArr = assume.split(",");
				expert.setAssumeArr(assumeArr);
			}else {
				String[] assumeArr = null;
				expert.setAssumeArr(assumeArr);
			}
			
			model.addAttribute("expert", expert);
			viewName= "expert/iexpert";
		}else {
//			String message = "로그인이 필요한 서비스입니다.";
//			redirectAttributes.addFlashAttribute("message", message);
//			viewName= "redirect:/login";
		}

		return viewName;
	}
	
	@PostMapping("profile/img")
	public String profileImgUpdate(
			@Validated(UpdateGroup.class)@ModelAttribute("expert") ExpertVO expert
			, Errors errors			
			) {
		MemberVO member = ((MemberPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getRealMember();

		String memId = member.getMemId();
		expert.setExpertId(memId);
		
		String viewName = "";
		if(!errors.hasErrors()) {
			iexpertservice.modifyProfileImg(expert);
			viewName = "redirect:/iexpert/profile";
		} else {
			viewName = "redirect:/iexpert/profile";
		}
		
	return viewName;
	}
	
	/**
	 * 전문가프로필 수정폼으로 이동 
	 * @param expert
	 * @param model
	 * @return
	 */
	@GetMapping("profileForm")
	public String eProfileForm(
			@ModelAttribute("expert") ExpertVO expert
			, Model model) {
		
		MemberVO member = ((MemberPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getRealMember();
		String expertId = member.getMemId();
		expert = expertservice.retrieveExpert(expertId);
		String assume = expert.getAssume();
		if(assume!=null) {
			String[] assumeArr = assume.split(",");
			expert.setAssumeArr(assumeArr);
		}else {
			String[] assumeArr = null;
			expert.setAssumeArr(assumeArr);
		}
		
		model.addAttribute("expert", expert);
		return "expert/profileForm";
	}
	

	
	/**
	 * 전문가 프로필을 수정 
	 * @return
	 */
	@PostMapping("profileForm")
	public String eProfileUpdate(
			@Validated(UpdateGroup.class)@ModelAttribute("expert") ExpertVO expert
			, Errors errors
			) {
		MemberVO member = ((MemberPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getRealMember();
		String expertId = member.getMemId();

		expert.setExpertId(expertId);
		log.info("**********************이력서 추가되었는지 확인  ***********{}", expert.getAssume());
//			expert.setAssume(AssArr);
		String viewName = "";
		if (!errors.hasErrors()) {
			iexpertservice.modifyProfile(expert);
			viewName = "redirect:/iexpert/profile";
		} else {
			viewName = "expert/profileForm";
		}
			
		return viewName;
	}
	
	/**
	 * 전문가의 상품을 조회
	 * @return
	 */
	@GetMapping("prod")
	public String iexpertProdList(
			@ModelAttribute("simpleCondition") SimpleSearchCondition simpleCondition
			, @RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, Model model
			) {
		
		MemberVO member = ((MemberPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getRealMember();
		EProdVO eprod = new EProdVO();
		//회원아이디를 가져오고 
		String expertId =member.getMemId();
		//회원아이디를  set
		eprod.setExpertId(expertId);
		PagingVO<EProdVO> pagingVO = new PagingVO<>(8,5);
		//detailCondition에 회워아이디를 set
		pagingVO.setDetailCondition(eprod);
		pagingVO.setSimpleCondition(simpleCondition);
		pagingVO.setCurrentPage(currentPage);
		iexpertservice.retrieveRegisteredEprodList(pagingVO);
		model.addAttribute("pagingVO", pagingVO);
		return "expert/iexpertprod";
	}
	/**
	 * 전문가 상품요청 조회
	 * @return
	 * @throws IOException 
	 */
	@GetMapping("request")
	public String iexpertRequest(
			 @RequestParam(value="page", required=false, defaultValue="1") int currentPage
			,@ModelAttribute("simpleCondition") SimpleSearchCondition simpleCondition
			 , Model model
			 , HttpServletResponse resp) throws IOException {
		MemberVO member = ((MemberPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getRealMember();
		
		String expertId = member.getMemId();
		String[] roleArr = member.getRoleListArray();
		
		String viewName= "";
		boolean roleChk = Arrays.asList(roleArr).contains("ROLE_EXPERT");
		
		if(roleChk==true) {
			MyExpertVO myExpert = new MyExpertVO();
			myExpert.setExpertId(expertId);
			PagingVO<MyExpertVO> pagingVO = new PagingVO<>(8,5);
			pagingVO.setDetailCondition(myExpert);
			pagingVO.setCurrentPage(currentPage);
			pagingVO.setSimpleCondition(simpleCondition);
			
			iexpertservice.retrieveReqeustList(pagingVO);
			
			List<ComCodeVO> comCode =  iexpertservice.retrieveRefundReason();
			
			model.addAttribute("pagingVO", pagingVO);
			model.addAttribute("comCode",comCode);
			viewName = "expert/iexpertrequest";
		}else {
			throw new NotAuthorityException();
		}

		return viewName;
	}
	
	/**
	 * 요청 진행상황 수정 
	 * @return
	 */
	@PostMapping("request")
	public String modifyProgress(
			@Validated(UpdateGroup.class)@ModelAttribute("myExpert") MyExpertVO myExpert
			, Errors error
			) {
		MemberVO member = ((MemberPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getRealMember();
		
		String[] roleArr = member.getRoleListArray();
		
		String viewName= "";
		boolean roleChk = Arrays.asList(roleArr).contains("ROLE_EXPERT");

		if (roleChk == true) {
			String ProgressCode = myExpert.getProgressCode();
			NewsVO news = new NewsVO();
			if (!error.hasErrors()) {
				if (ProgressCode.equals("PG04")) {
					EprodRefundVO refund = new EprodRefundVO();
					refund.setRefundReasonCode(myExpert.getRefundReasonCode());
					refund.setMyEprod(myExpert.getMyEprod());
					iexpertservice.modifyProcessState(myExpert);
					iexpertservice.refundEprod(refund);
					viewName = "redirect:/iexpert/request";
				} else {
					if (ProgressCode.equals("PG02")) {
						String buyerId = expertservice.getBuyerId(myExpert.getMyEprod());
						news.setReceiverId(buyerId);
						news.setNewsMsgCode("ENEW06");
						news.setNewsId("heehee");
						newsDAO.insertNews(news);

					}
					iexpertservice.modifyProcessState(myExpert);
					viewName = "redirect:/iexpert/request";
				}
			} else {
				viewName = "redirect:/iexpert/request";
			}
		} else {
			throw new NotAuthorityException();
		}

		return viewName;
	}
	/**
	 * 전문가 상품등록
	 * @return
	 */
	@GetMapping("eprodForm")
	public String iexpertProdForm(){
		MemberVO member = ((MemberPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getRealMember();

		String[] roleArr = member.getRoleListArray();

		String viewName = "";
		boolean roleChk = Arrays.asList(roleArr).contains("ROLE_EXPERT");

		if (roleChk == true) {
			viewName = "expert/ieprodForm";
		} else {
			throw new NotAuthorityException();
		}
		return viewName;
	}
	
	/**
	 * 전문가 상품을 등록 
	 * @param eprod
	 * @param errors
	 * @return
	 */
	@PostMapping("eprodForm")
	public String iexpertResistProd(
			@Validated(InsertGroup.class) @ModelAttribute("eprod") EProdVO eprod
			, Errors errors
			, RedirectAttributes redirectAttributes) {
		
		MemberVO member = ((MemberPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getRealMember();
		String[] roleArr = member.getRoleListArray();

		String viewName = "";
		boolean roleChk = Arrays.asList(roleArr).contains("ROLE_EXPERT");

		if (roleChk == true) {
			String memId = member.getMemId();
			eprod.setExpertId(memId);
			if (!errors.hasErrors()) {
				iexpertservice.resistEprod(eprod);
				redirectAttributes.addFlashAttribute("message", "상품등록이 완료되었습니다.");
				viewName = "redirect:/iexpert/prod";
			} else {
				viewName = "expert/ieprodForm";
			}
		} else {
			throw new NotAuthorityException();
		}
		
		return viewName;
	}
	
	@GetMapping("prod/{eprodNum}")
	public String iexpertUpdateForm(
			@PathVariable String eprodNum
			, Model model){
		
		MemberVO member = ((MemberPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getRealMember();
		String[] roleArr = member.getRoleListArray();

		String viewName = "";
		boolean roleChk = Arrays.asList(roleArr).contains("ROLE_EXPERT");

		if (roleChk == true) {
			EProdVO eprod = expertservice.retrieveEprod(eprodNum);
			model.addAttribute("eprod", eprod);
			viewName = "expert/ieprodForm";
		} else {
			throw new NotAuthorityException();
		}

		return viewName;
	}
	
	@PostMapping("prod/{eprodNum}")
	public String UpdateProd(
			@Validated(UpdateGroup.class)@ModelAttribute("eprod") EProdVO eprod
			, Errors errors) {
		MemberVO member = ((MemberPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getRealMember();
		String[] roleArr = member.getRoleListArray();

		String viewName = "";
		boolean roleChk = Arrays.asList(roleArr).contains("ROLE_EXPERT");

		if (roleChk == true) {
			if (!errors.hasErrors()) {
				iexpertservice.modifyEprod(eprod);
				viewName = "redirect:/iexpert/prod";
			} else {
				viewName = "expert/ieprodForm";
			}
		} else {
			throw new NotAuthorityException();
		}
		
		return viewName;
	}
	
	@DeleteMapping("prod/{eprodNum}")
	public String DeleteProd(
			  @PathVariable String eprodNum
			, @Validated(DeleteGroup.class)@ModelAttribute("eprod") EProdVO eprod
			, Errors errors
			) {
		MemberVO member = ((MemberPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getRealMember();
		
		String[] roleArr = member.getRoleListArray();

		String viewName = "";
		boolean roleChk = Arrays.asList(roleArr).contains("ROLE_EXPERT");

		if (roleChk == true) {
			eprod.setExpertId(member.getMemId());
			eprod.setEprodNum(eprodNum);
			
			if(!errors.hasErrors()) {
				iexpertservice.deleteEprod(eprod);
				viewName = "redirect:/iexpert/prod";
			}else {
				viewName="redirect:/iexpert/prod";
			}
		}else {
			throw new NotAuthorityException();
		}

		return viewName;
	}
	
	@DeleteMapping("profile")
	public String DeleteExpert(
			@Validated(DeleteGroup.class)@ModelAttribute("expert") ExpertVO expert
			,Errors errors
			) {
		MemberVO member = ((MemberPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getRealMember();
		String[] roleArr = member.getRoleListArray();

		String viewName = "";
		boolean roleChk = Arrays.asList(roleArr).contains("ROLE_EXPERT");

		if (roleChk == true) {
			if (!errors.hasErrors()) {
				iexpertservice.deleteExpert(expert);
				iexpertservice.deleteExpertRole(member.getMemId());
				iexpertservice.modifyDelExpProd(member.getMemId());
				
				viewName = "redirect:/logout";
			} else {
				viewName = "redirect:/iexpert/profile";
			}
		} else {
			throw new NotAuthorityException();
		}

		return viewName;
	}

	/**
	 * 전문가 소득조회
	 * @return
	 */
	@GetMapping("income")
	public String iexpertIncome(Model model) {
		MemberVO member = ((MemberPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getRealMember();
		String[] roleArr = member.getRoleListArray();

		String viewName = "";
		boolean roleChk = Arrays.asList(roleArr).contains("ROLE_EXPERT");

		if (roleChk == true) {
			String memId = member.getMemId();
			String memNick = member.getMemNick();
			List<ExpertPaymentVO> revenueList = iexpertservice.retrieveExpertRevenue(memId);
			
			RevenueVO revenue = new RevenueVO();
			revenue.setExpertId(member.getMemId());
			revenue.setYear("2022");
			
			RevenueVO monthRevenue = iexpertservice.retrieveIncomeList(revenue);
			model.addAttribute("monthRevenue", monthRevenue);
			model.addAttribute("revenueList", revenueList);
			model.addAttribute("memNick", memNick);
			model.addAttribute("year", "2022");

			viewName = "expert/iexpertincome";
		} else {
			throw new NotAuthorityException();
		}

		return viewName;
	}
	
	@PostMapping("income")
	public String iexpertMonthIncome(
			@RequestParam int year
			,@RequestParam int month
			,Model model) {
		MemberVO member = ((MemberPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getRealMember();
		String[] roleArr = member.getRoleListArray();

		String viewName = "";
		boolean roleChk = Arrays.asList(roleArr).contains("ROLE_EXPERT");
		if (roleChk == true) {
			String memNick = member.getMemNick();
			ExpertPaymentVO epay = new ExpertPaymentVO();
			epay.setYear(year);
			epay.setMonth(month);
			epay.setExpertId(member.getMemId());
			List<ExpertPaymentVO> revenueList = iexpertservice.retrieveMonthinCome(epay);
			RevenueVO revenue = new RevenueVO();
			revenue.setExpertId(member.getMemId());
			revenue.setYear(Integer.toString(year));
			RevenueVO monthRevenue = iexpertservice.retrieveIncomeList(revenue);
			model.addAttribute("monthRevenue", monthRevenue);
			model.addAttribute("revenueList", revenueList);
			model.addAttribute("memNick", memNick);
			model.addAttribute("year", year);
			viewName = "expert/iexpertincome";
		} else {
			throw new NotAuthorityException();
		}
		return viewName;
	}
	
	@PostMapping("/expertRevenueExcel")
	public void downloadExcel(
			@RequestParam(name="year") String year
			, HttpServletResponse resp
			, Model model
			) {
		MemberVO member = ((MemberPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getRealMember();
		Workbook workbook = new HSSFWorkbook();
		Sheet yearRevenueSheet = workbook.createSheet(year+"년 매출");
		ExpertPaymentVO epay = new ExpertPaymentVO();
		epay.setExpertId(member.getMemId());
		epay.setYear(Integer.parseInt(year));
		List<ExpertPaymentVO> expertRevenue = iexpertDAO.selectRevenueForExcel(epay);
		
		int rowNo = 0;
		Row expHeaderRow = yearRevenueSheet.createRow(rowNo++);
		expHeaderRow.createCell(0).setCellValue("날짜");
		expHeaderRow.createCell(1).setCellValue("상품명");
		expHeaderRow.createCell(2).setCellValue("상품가격");
		expHeaderRow.createCell(3).setCellValue("누적 가격");
		
		for (ExpertPaymentVO epv : expertRevenue) {
			Row row = yearRevenueSheet.createRow(rowNo++);
			row.createCell(0).setCellValue(epv.getEpayDate());
			row.createCell(1).setCellValue(epv.getEprodName());
			row.createCell(2).setCellValue(epv.getAmount());
			row.createCell(3).setCellValue(epv.getAccumRevenue());
		}
		
		
		resp.setContentType("ms-vnd/excel");
		resp.setHeader("Content-Disposition", "attachment;filename=revenue.xlsx");
		
		try {
			workbook.write(resp.getOutputStream());
			workbook.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
			
		
	}

}
