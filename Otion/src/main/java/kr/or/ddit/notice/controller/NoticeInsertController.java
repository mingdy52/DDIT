package kr.or.ddit.notice.controller;

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
import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.notice.service.NoticeService;
import kr.or.ddit.notice.vo.NoticeVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@MultipartConfig
@RequiredArgsConstructor
@RequestMapping("/notice/form")
public class NoticeInsertController {
	
	@Inject
	private CheckMember check;
	
	@Inject
	NoticeService noticeService;
	
	@ModelAttribute("notice")
	public NoticeVO notice() {
		return new NoticeVO();
	}
	
	// 공지사항 등록
	@GetMapping
	public String noticeView() {
		String viewName = null;
		MemberVO checkAdmin = check.checkAdmin();
		
		if(checkAdmin != null) {
			viewName = "notice/noticeForm";
		} else {
			viewName = "redirect:/login";
		}
		
		return viewName;
	}
	
	@PostMapping
	public String noticeView(
		@Validated(InsertGroup.class) @ModelAttribute("notice") NoticeVO notice
		, Errors errors
		, RedirectAttributes redirectAttributes
		) {
	
		String viewName = null;
		if(!errors.hasErrors()) {
			ServiceResult result = noticeService.createNotice(notice);
			switch (result) {
			case OK:
				redirectAttributes.addFlashAttribute("message", "등록되었습니다.");
				viewName = "redirect:/notice";
				break;
			case FAIL:
				redirectAttributes.addFlashAttribute("message", "다시 시도해주세요.");
				viewName = "redirect:/notice/form";
				break;
			}
			
		}else {
			String errorAttrName = BindingResult.MODEL_KEY_PREFIX+"notice";
			redirectAttributes.addFlashAttribute(errorAttrName, errors);
			redirectAttributes.addFlashAttribute("notice", notice);
			viewName = "redirect:/notice/form";
		}
		return viewName;
	}
}
