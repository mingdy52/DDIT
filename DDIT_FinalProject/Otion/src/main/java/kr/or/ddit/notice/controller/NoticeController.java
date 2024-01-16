package kr.or.ddit.notice.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.annotation.MultipartConfig;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.common.CheckMember;
import kr.or.ddit.common.validate.UpdateGroup;
import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.common.vo.SimpleSearchCondition;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.notice.service.NoticeService;
import kr.or.ddit.notice.vo.NoticeVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/**
 * 
 * @author 박채록
 * @since 2022. 8. 4.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 4.      박채록       최초작성
 * 2022. 8. 24.		서효림	1차 수정
 * 2022. 9. 01.      심민경      CRUD 수정
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Slf4j
@Controller
@MultipartConfig
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {
	
	@Inject
	NoticeService noticeService;
	@Inject
	private CheckMember check;
	
	@GetMapping
	public String noticeListHTML(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			, Model model
			,  @ModelAttribute("simpleCondition") SimpleSearchCondition simpleCondition
			) {
		PagingVO<NoticeVO> pagingVO = new PagingVO<>(8,5);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSimpleCondition(simpleCondition);
		noticeService.findAllNotice(pagingVO);
		model.addAttribute("pagingVO",pagingVO);
		
		return "notice/noticelist";
	}
	
	// 상세정보 가져오기
	@GetMapping("{notiNum}")
	public String noticeView(
			@PathVariable String notiNum,
			Model model
			) {
		NoticeVO notice = noticeService.findDetailNotice(notiNum);
		model.addAttribute("notice", notice);
		model.addAttribute("boardNum", notice.getNotiNum());
		model.addAttribute("boardWriter", notice.getWriterId());
		return "notice/noticeView";
	}
	
	//수정
	@GetMapping("{notiNum}/form")
	public String noticeEdit(
			@PathVariable String notiNum
			, Model model) {
		
		String viewName = null;
		MemberVO checkAdmin = check.checkAdmin();
		
		if(checkAdmin != null) {
			if(!model.containsAttribute("notice")) {
				NoticeVO notice = noticeService.findDetailNotice(notiNum);
				model.addAttribute("notice",notice);
			}
			
			viewName = "notice/noticeForm";
		} else {
			viewName = "redirect:/login";
		}
		
		return viewName;
	}
	
	@PostMapping("{notiNum}/form")
	public String noticeUpdate(
			@Validated(UpdateGroup.class) @ModelAttribute("notice") NoticeVO notice
			, Errors errors
			, @RequestParam(value="delAttatchNum", required=false) String[] delAttatchNum
			, @RequestParam(value="delAttatchOrder", required=false) int[] delAttatchOrder
			, RedirectAttributes redirectAttributes
		) {
		
		String viewName = null;
		if(!errors.hasErrors()) {
			Map<String, Object> map = new HashMap<>();
			map.put("notice", notice);
			map.put("attatchNum", delAttatchNum);
			map.put("attatchOrder", delAttatchOrder);
			
			ServiceResult result = noticeService.modifyNotice(map);
			switch (result) {
			case OK:
				redirectAttributes.addFlashAttribute("message", "수정되었습니다.");
				viewName = "redirect:/notice/{notiNum}";
				break;
			case FAIL:
				redirectAttributes.addFlashAttribute("message", "다시 시도해주세요.");
				viewName = "redirect:/notice/{notiNum}/form";
				break;
			}
		}else {
			String errorAttrName = BindingResult.MODEL_KEY_PREFIX+"notice";
			redirectAttributes.addFlashAttribute(errorAttrName, errors);
			redirectAttributes.addFlashAttribute("notice", notice);
			viewName = "redirect:/notice/{notiNum}/form";
		}
		
		return viewName;
		
	}
	
	// 삭제
	@GetMapping("{notiNum}/del")
	public String deleteNotice(
			@PathVariable String notiNum
			, RedirectAttributes redirectAttributes
	) {
		String viewName = null;
		MemberVO checkAdmin = check.checkAdmin();
		
		if(checkAdmin != null) {
			ServiceResult result = noticeService.removeNotice(notiNum);
			
			switch (result) {
			case OK:
				redirectAttributes.addFlashAttribute("message", "삭제되었습니다.");
				break;
			case FAIL:
				redirectAttributes.addFlashAttribute("message", "다시 시도해주세요.");
				break;
			}
			
			viewName = "redirect:/notice";
		} else {
			viewName = "redirect:/login";
		}
		
		
		return viewName;
	}
}
