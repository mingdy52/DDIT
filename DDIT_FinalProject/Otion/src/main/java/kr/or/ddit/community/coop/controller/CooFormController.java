package kr.or.ddit.community.coop.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.annotation.MultipartConfig;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.common.CheckMember;
import kr.or.ddit.common.attach.service.AttatchService;
import kr.or.ddit.common.dao.NewsDAO;
import kr.or.ddit.common.login.vo.MemberPrincipal;
import kr.or.ddit.common.service.NewsService;
import kr.or.ddit.common.validate.InsertGroup;
import kr.or.ddit.common.vo.AttatchVO;
import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.common.vo.NewsVO;
import kr.or.ddit.community.coop.dao.CooFormDAO;
import kr.or.ddit.community.coop.service.CooBoardService;
import kr.or.ddit.community.coop.service.CooFormService;
import kr.or.ddit.community.coop.vo.CooBoardVO;
import kr.or.ddit.community.coop.vo.CooFormVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.proj.colleague.service.ColleagueService;
import kr.or.ddit.proj.colleague.vo.ColleagueVO;
import kr.or.ddit.proj.main.vo.ProjectVO;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 작성자명
 * @since 2022. 8. 16.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 11.      고정현       최초작성
 * 2022. 8. 16.      고정현	 협업 신청 승인 및 거절
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Slf4j
@Controller
@RequestMapping("/cooboard/form")
@MultipartConfig
public class CooFormController {
	
	@Inject
	private CheckMember check;	
	
	@Inject
	CooBoardService cooBoardService;
	
	@Inject
	CooFormService cooFormService;
	
	@Inject
	CooFormDAO cooFormDao;
	
	@Inject
	ColleagueService cooleagueService;
	
	@Inject
	AttatchService attatchService;
	
	@Inject
	NewsDAO newsDAO;
	
	@ModelAttribute("cooboard")
	public CooBoardVO cooBoard() {
		return new CooBoardVO();
	}
	
	@GetMapping
	public String cooBoardInsert(Model model) {
		String viewName = null;
		MemberVO mv=((MemberPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRealMember();
		List<ProjectVO> project = cooFormDao.selectProject(mv.getMemId());
		model.addAttribute("project", project);
		viewName = "coop/cooForm";
		return viewName;
	}
	
	@PostMapping
	public String insertForm(
			@Validated(InsertGroup.class) @ModelAttribute("cooboard") CooBoardVO cooBoard, 
			Errors errors,
			RedirectAttributes redirectAttributes
			) {
		
			log.info("cooboard{}", cooBoard);
//			log.info("date", cooBoard.getCooEnd());
			
			String viewName = null;
			// 단순 글쓰기로 list에만 가면 됨		
			if (!errors.hasErrors()) {
				log.info("msg", "message");
				ServiceResult result = cooBoardService.createCooBoard(cooBoard);				
				switch(result) {
				case OK:
					redirectAttributes.addFlashAttribute("message", "등록되었습니다.");
					viewName = "redirect:/cooboard";
					break;
				case FAIL:
					redirectAttributes.addFlashAttribute("message", "다시 시도해주세요.");
					viewName = "redirect:/cooboard/form";
					break;
				}
			}else {
				String errorAttrName = BindingResult.MODEL_KEY_PREFIX+"cooboard";
				log.info("m{}",errors.getAllErrors());
				redirectAttributes.addFlashAttribute(errorAttrName, errors);
				redirectAttributes.addFlashAttribute("cooboard", cooBoard);
				viewName = "redirect:/cooboard/form";
			}
			
			return viewName;
	}
	
	// 협업 신청
	@PostMapping("check")
	public String applyCoop(
			@RequestParam MultipartFile originFile,
			@ModelAttribute CooFormVO cooForm,
			Authentication auth
			,RedirectAttributes redirectAttributes
			) {
		log.info("##########{}",originFile);
		log.info("##########{}",cooForm);
		cooForm.setOriginFile(originFile);
		
		cooForm.setApplicantId(auth.getName());
		cooFormService.createCooForm(cooForm);
		redirectAttributes.addFlashAttribute("message", "신청되었습니다.");
		
		return "redirect:/cooboard";
	}
	 
	// 협업 승인 및 거절
	@PutMapping("{cooNum}/{status}")
	public String cooFormStatus(
			@PathVariable String cooNum,
			@PathVariable String status,
			@ModelAttribute ColleagueVO colleagueVO,
			Model model
			) {
		log.info("********{}", colleagueVO);
		
		
		if(StringUtils.isNotBlank(cooNum) && StringUtils.isNotBlank(status)) {
			NewsVO newsVO = new NewsVO();
			newsVO.setReceiverId(colleagueVO.getMemId());
			newsVO.setNewsId(colleagueVO.getPjId());
	
			if("cancel".equals(status)) {
				// 협업 신청 거절
				cooFormService.updateCancelCooForm(cooNum);
				newsVO.setNewsMsgCode("NEW08");
			}else if("correct".equals(status)) {
				// 팀원 협업 신청 승인
				cooFormService.updateCorrectCooForm(cooNum);
				// 팀원으로 등록 
				colleagueVO.setCooFormNum(cooNum);
				cooleagueService.createColleague(colleagueVO);
				newsVO.setNewsMsgCode("NEW07");
				newsDAO.insertNews(newsVO);
			}
		}else {
			model.addAttribute("message","신청내역이 존재 하지 않습니다.");
		}
		
		return "redirect:/project/" + colleagueVO.getPjId() + "/home";
	}
}
