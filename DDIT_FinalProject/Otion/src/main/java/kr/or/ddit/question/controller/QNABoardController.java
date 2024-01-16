package kr.or.ddit.question.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.annotation.MultipartConfig;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.common.CheckMember;
import kr.or.ddit.common.dao.NewsDAO;
import kr.or.ddit.common.login.vo.MemberPrincipal;
import kr.or.ddit.common.validate.DeleteGroup;
import kr.or.ddit.common.validate.UpdateGroup;
import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.common.vo.SimpleSearchCondition;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.NotAuthorityException;
import kr.or.ddit.question.dao.QNABoardDAO;
import kr.or.ddit.question.service.QNABoardService;
import kr.or.ddit.question.vo.QNABoardVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/**
 * 
 * @author 서효림
 * @since 2022. 8. 22.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 22.      서효림          최초작성
 * 2022. 8. 24		  서효림		1차 수정
 * 2022. 8. 31. 	서효림		2차 수정,페이징 적용 완료
 * 2022. 9. 02.		심민경		CRUD 완료
 * 2022. 9. 03.		심민경		비밀번호 적용
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Slf4j
@Controller
@MultipartConfig
@RequiredArgsConstructor
@RequestMapping("/qna")
public class QNABoardController {
	
	@Inject
	QNABoardService qnaBoardService;
	@Inject
	private CheckMember check;
	@Inject
	PasswordEncoder passEncoder;
	@Inject
	QNABoardDAO qnaBoardDAO;
	@Inject
	private NewsDAO newsDAO;
	
	// QNA 리스트
	@GetMapping
	public String qnaBoardListHTML(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			, Model model
			, @ModelAttribute("simpleCondition") SimpleSearchCondition simpleCondition
			, @RequestParam(value="answerYN", required=false) String answerYN
			) {
		
		PagingVO<QNABoardVO> pagingVO = new PagingVO<>(8,5);
		
		Map<String, Object> map = new HashMap<>();
		map.put("answerYN", answerYN);
		map.put("pagingVO", pagingVO);
		
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSimpleCondition(simpleCondition);
		qnaBoardService.findAllQNA(map, pagingVO);
		
		model.addAttribute("pagingVO", pagingVO);
		model.addAttribute("answerYN", answerYN);
		
		return "qna/qnaList";
	}
	
	// QNA 상세
	@GetMapping("{qnaNum}")
	public String qnaView(
			@PathVariable String qnaNum
			, Model model
			, RedirectAttributes redirectAttributes
			) {
		String viewName = "qna/qnaView";
		if(!model.containsAttribute("qna")) {
			QNABoardVO qna = qnaBoardService.findDetail(qnaNum);
			model.addAttribute("qna", qna);
			
			if(StringUtils.equals(qna.getQnaPublicYn(), "N")) {
				if(!model.containsAttribute("check")) {
					redirectAttributes.addFlashAttribute("message","비밀글입니다.");
					viewName = "redirect:/qna";
				}
			}
			
		}
		
		return viewName;
		
	}
	
	// 비밀글 비밀번호 체크
	@PostMapping("check")
	public String qnaView(
			@RequestParam("qnaNum") String qnaNum
			, @RequestParam("qnaPass") String qnaPass
			, RedirectAttributes redirectAttributes
			) {
		
		String viewName = null;
		QNABoardVO saved = qnaBoardDAO.selectQNAPass(qnaNum);
		if(passEncoder.matches(qnaPass, saved.getQnaPass())){
			//복호화한값과 입력받은 값이 일치할때
			QNABoardVO qna = qnaBoardService.findDetail(qnaNum);
			redirectAttributes.addFlashAttribute("qna", qna);
			redirectAttributes.addFlashAttribute("check", "check");
			viewName = "redirect:/qna/"+qnaNum;
		} else {
			redirectAttributes.addFlashAttribute("message","틀린 비밀번호 입니다.");
			viewName = "redirect:/qna";
		}
		
		return viewName;
	}
	
	// QNA 답변 등록
	@PostMapping("{qnaNum}")
	public String answerQNA(
			@Validated(UpdateGroup.class) @ModelAttribute("qna") QNABoardVO qna
			, Errors errors
			, RedirectAttributes redirectAttributes
			) {
		
		// 0. 폼 받아와서
		
		String viewName = null;
		String message = null;
		MemberVO checkAdmin = check.checkAdmin();
		
		// 1. 관리자 여부 체크
		if(checkAdmin != null) {
			if(!errors.hasErrors()) {
				qna.setAnswerId(checkAdmin.getMemId());
				// 2. update
				ServiceResult result = qnaBoardService.answerQna(qna);
				
				// 2-1 통과
				if(result.equals(ServiceResult.OK)) {
					
					viewName = "redirect:/qna/{qnaNum}";
					message = "완료되었습니다.";
					
				} else {
					// 2-2 실패
					viewName = "redirect:/qna/{qnaNum}";
					message = "다시 시도해주세요.";
					redirectAttributes.addFlashAttribute("qna", qna);
				}
			}else {
				message = "다시 시도해주세요.";
				viewName = "redirect:/qna/{qnaNum}";
				redirectAttributes.addFlashAttribute("qna", qna);
			}
			
			
		} else {
			viewName = "redirect:/login";
		}
		
		redirectAttributes.addFlashAttribute("message", message);
		
		return viewName;
		
	}
	
	@GetMapping("{qnaNum}/form")
	public String getModifyFormHTML(Model model) {
		if(!model.containsAttribute("qna")) {
			throw new NotAuthorityException();
		}
		return  "qna/qnaForm";
	}
	
	// 수정
	@PostMapping("{qnaNum}/form")
	public String qnaEdit(
			@ModelAttribute("qna") QNABoardVO qna
			, RedirectAttributes redirectAttributes
			) {
		String viewName = null;
		QNABoardVO saved = qnaBoardDAO.selectQNAPass(qna.getQnaNum());
		if(passEncoder.matches(qna.getQnaPass(), saved.getQnaPass())){
			//복호화한값과 입력받은 값이 일치할때
			qna = qnaBoardService.findDetail(qna.getQnaNum());
			redirectAttributes.addFlashAttribute("modify","modify");
			redirectAttributes.addFlashAttribute("qna",qna);
			viewName = "redirect:/qna/{qnaNum}/form";
		} else {
			
			redirectAttributes.addFlashAttribute("message","틀린 비밀번호 입니다.");
			viewName = "redirect:/qna/{qnaNum}";
		}
		
		return viewName;
	}
	
	@PostMapping("{qnaNum}/edit")
	public String qnaUpdate(
			@Validated(UpdateGroup.class)@ModelAttribute("qna") QNABoardVO qna
			, Errors errors
			, RedirectAttributes redirectAttributes
			, SessionStatus sessionStatus
		){
			String viewName = null;
			String message = null;
			if(!errors.hasErrors()) {
				ServiceResult result = qnaBoardService.modify(qna);
				switch (result) {
				case OK:
				 // 2-1 통과
				 message = "수정되었습니다.";
				 viewName = "redirect:/qna/{qnaNum}";
				 break;
				case FAIL:
				 // 2-2 실패
				 message = "다시 시도해주세요.";
				 viewName = "redirect:/qna/{qnaNum}/form";
				 break;
			}
				
			}else {
				String errorAttrName = BindingResult.MODEL_KEY_PREFIX + "qna";
				redirectAttributes.addFlashAttribute(errorAttrName,errors);
				redirectAttributes.addFlashAttribute("modify","modify");
				redirectAttributes.addFlashAttribute("qna",qna);
				viewName = "redirect:/qna/{qnaNum}/form";
			}
			redirectAttributes.addFlashAttribute("message",message);
			return viewName;
		}
	
	// 삭제
	@PostMapping("{qnaNum}/del")
	public String deleteQNA(
			@Validated(DeleteGroup.class) @ModelAttribute("qna") QNABoardVO qnaBoard
			, Errors errors
			, RedirectAttributes redirectAttributes
		) {
		// 1. 받아와서
		
		String viewName = null;
		String message = null;
		if(!errors.hasErrors()) {
			
			// 2.삭제
			ServiceResult result = qnaBoardService.remove(qnaBoard);
			switch (result) {
				case OK:
				 // 2-1 통과
				 message = "삭제되었습니다.";
				 viewName = "redirect:/qna";
				 break;
				case FAIL:
				 // 2-2 실패
				 message = "다시 시도해주세요.";
				 viewName = "redirect:/qna/{qnaNum}";
				 break;
				case INVALIDPASSWORD:
				 // 2-3 비밀번호 오류
				 message = "틀린 비밀번호입니다.";
				 viewName = "redirect:/qna/{qnaNum}";
				 break;
				case NOTEXIST:
				// 2-3 비밀번호 오류
				message = "존재하지 않는 게시글입니다.";
				viewName = "redirect:/qna";
				break;
			}
			
		}else {
			message = "다시 시도해주세요.";
			viewName = "redirect:/qna/{qnaNum}";
		}
		
		redirectAttributes.addFlashAttribute("message", message);
		return viewName;
	}

}

