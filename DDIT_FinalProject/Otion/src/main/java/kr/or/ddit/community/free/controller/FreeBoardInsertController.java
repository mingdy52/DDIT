package kr.or.ddit.community.free.controller;

import javax.inject.Inject;
import javax.servlet.annotation.MultipartConfig;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.common.CheckMember;
import kr.or.ddit.common.validate.InsertGroup;
import kr.or.ddit.community.free.service.FreeBoardService;
import kr.or.ddit.community.free.vo.FreeBoardVO;
import kr.or.ddit.enumpkg.ServiceResult;
import lombok.extern.slf4j.Slf4j;
/**
 * 
 * @author 서효림
 * @since 2022. 8. 16.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 16.       서효림          최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Slf4j
@Controller
@MultipartConfig
@RequestMapping("/freeBoard/form")
public class FreeBoardInsertController {
	
	@Inject
	private CheckMember check;
	
	@Inject
	FreeBoardService freeBoardService;
	
	@ModelAttribute("freeBoard")
	public FreeBoardVO freeBoard() {
		return new FreeBoardVO();
	}
	
	// 자유게시판 등록
	@GetMapping
	public String freeBoardInsert() {
		String viewName = null;

		viewName = "free/freeBoardForm";

		return viewName;
	}
	
	@PostMapping
	public String freeBoardInsert(
		@Validated(InsertGroup.class)@ModelAttribute("freeBoard") FreeBoardVO freeBoard
		, Errors errors
		, RedirectAttributes redirectAttributes
		) {

		String viewName = null;
		if(!errors.hasErrors()) {
			ServiceResult result = freeBoardService.create(freeBoard);
			switch(result) {
			case OK:
				redirectAttributes.addFlashAttribute("message", "등록되었습니다.");
				viewName = "redirect:/freeBoard";
				break;
			case FAIL:
				redirectAttributes.addFlashAttribute("message", "다시 시도해주세요.");
				viewName = "redirect:/freeBoard/form";
				break;
			}
		}else {
			String errorAttrName = BindingResult.MODEL_KEY_PREFIX+"freeBoard";
			redirectAttributes.addFlashAttribute(errorAttrName, errors);
			redirectAttributes.addFlashAttribute("freeBoard", freeBoard);
			viewName = "redirect:/freeBoard/form";
		}
		
		return viewName;
		
	}
}
