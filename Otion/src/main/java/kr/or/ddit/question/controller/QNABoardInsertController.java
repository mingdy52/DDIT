package kr.or.ddit.question.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.common.validate.InsertGroup;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.question.service.QNABoardService;
import kr.or.ddit.question.vo.QNABoardVO;

/**
 * 
 * @author 서효림
 * @since 2022. 8. 26.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 26.       서효림          최초작성
 * 2022. 8. 27.		  서효림		1차 수정
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */

@Controller
@RequestMapping("/qna/form")
public class QNABoardInsertController {
	
	@Inject
	private QNABoardService qnaBoardService;
	
	@ModelAttribute("qna")
	public QNABoardVO qnaBoard() {
		return new QNABoardVO();
	}
	
	//QNA 등록
	@GetMapping
	public String createQna() {
		return "qna/qnaForm";
	}
	
	@PostMapping
	public String qnaBoardInsert(
			@Validated(InsertGroup.class)@ModelAttribute("qna") QNABoardVO qna
			, Errors errors
			, RedirectAttributes redirectAttributes
		) {
		
		String viewName = null;
		String message = null;
		if(!errors.hasErrors()) {
			ServiceResult result = qnaBoardService.create(qna);
			
			switch (result) {
			case OK:
			 // 2-1 통과
			 message = "등록되었습니다.";
			 viewName = "redirect:/qna";
			 break;
			case FAIL:
			 // 2-2 실패
			 message = "다시 시도해주세요.";
			 viewName = "redirect:/qna/form";
			 redirectAttributes.addFlashAttribute("qna", qna);
			 break;
			}
			
		}else {
			String errorAttrName = BindingResult.MODEL_KEY_PREFIX+"qna";
			redirectAttributes.addFlashAttribute(errorAttrName, errors);
			redirectAttributes.addFlashAttribute("qna", qna);
			viewName = "redirect:/qna/form";
		}
		
		redirectAttributes.addFlashAttribute("message", message);
		return viewName;
	}
}
