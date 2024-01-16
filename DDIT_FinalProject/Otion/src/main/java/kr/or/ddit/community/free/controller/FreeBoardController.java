package kr.or.ddit.community.free.controller;

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
import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.common.vo.SimpleSearchCondition;
import kr.or.ddit.community.free.service.FreeBoardService;
import kr.or.ddit.community.free.vo.FreeBoardVO;
import kr.or.ddit.enumpkg.ServiceResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author 서효림
 * @since 2022. 8. 5.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 5.      서효림          최초작성
 * 2022. 8. 16.		서효림	  1차수정		
 * 2022. 8. 17.		서효림	  2차수정
 * 2022. 8. 18.		서효림	  3차 수정
 * 2022. 8. 31.		서효림	  4차 수정(페이징)
 * 2022. 9. 01. 	서효림	  5차 수정(검색기능)
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */

@Slf4j
@Controller
@MultipartConfig
@RequiredArgsConstructor
@RequestMapping("/freeBoard")
public class FreeBoardController {
	
	@Inject
	FreeBoardService freeBoardService;
	@Inject
	private CheckMember check;
	
	// 리스트 가져오기
	@GetMapping
	public String boardListHTML(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			, Model model
			,  @ModelAttribute("simpleCondition") SimpleSearchCondition simpleCondition
			) {
		// 일단 resultMap 설정하는 거를 공부한 뒤에 다시 수정(refactoring) 하는 걸
		PagingVO<FreeBoardVO> pagingVO = new PagingVO<>(8,5);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSimpleCondition(simpleCondition);
		freeBoardService.findAll(pagingVO);;
		log.info("pagingVO{}",pagingVO);
		model.addAttribute("pagingVO",pagingVO);
		
		return "free/freeBoardList";
	}
	
	// 상세정보 가져오기
	@GetMapping("{freeNum}")
	public String boardView(
			@PathVariable String freeNum, 
			Model model 
			) {
		log.info("freeNum{}",freeNum);
		FreeBoardVO freeBoard = freeBoardService.find(freeNum);
		model.addAttribute("boardNum", freeBoard.getFreeNum());
		model.addAttribute("freeBoard", freeBoard);
		model.addAttribute("boardWriter", freeBoard.getWriterId());
		return "free/freeBoardView";
	}
	
	// 수정
	@GetMapping("{freeNum}/form")
	public String boardEdit(
			@PathVariable String freeNum
			, Model model) {
		String viewName = null;
//		String checkAdmin = check.checkAdmin();
//		if(StringUtils.equals(checkAdmin, "ok")) {
		if(!model.containsAttribute("freeboard")) {
			FreeBoardVO freeBoard = freeBoardService.find(freeNum);
			model.addAttribute("freeBoard", freeBoard);
		}
		viewName = "free/freeBoardForm";
//		}else {
//			viewName = checkAdmin;
//		}
		return viewName;
	}
	
	
	@PostMapping("{freeNum}/form")
	public String boardUpdate(
			@Validated(UpdateGroup.class)@ModelAttribute("freeBoard") FreeBoardVO freeBoard
			, Errors errors
			, @RequestParam(value="delAttatchNum", required=false) String[] delAttatchNum
			, @RequestParam(value="delAttatchOrder", required=false) int[] delAttatchOrder
			, RedirectAttributes redirectAttributes
		) {
		
		String viewName = null;
		if(!errors.hasErrors()) {
			Map<String, Object> map = new HashMap<>();
			map.put("freeBoard",freeBoard);
			map.put("attatchNum", delAttatchNum);
			map.put("attatchOrder", delAttatchOrder);
			
			ServiceResult result = freeBoardService.modify(map);
			switch (result) {
			case OK:
				redirectAttributes.addFlashAttribute("message", "수정 완료");
				viewName = "redirect:/freeBoard/{freeNum}";
				break;
			case FAIL:
				redirectAttributes.addFlashAttribute("message", "비밀번호 오류");
				viewName = "redirect:/freeBoard/{freeNum}/form";
				break;
			}
		}else {
			String errorAttrName = BindingResult.MODEL_KEY_PREFIX + "freeBoard";
			redirectAttributes.addFlashAttribute(errorAttrName, errors);
			redirectAttributes.addFlashAttribute("freeBoard",freeBoard);
			viewName = "redirect:/freeBoard/{freeNum}/form";
		}
		return viewName;
	}
	
	// 삭제
	@GetMapping("{freeNum}/del")
	public String deleteBoard(
		@PathVariable String freeNum
		, RedirectAttributes redirectAttributes
	) {
		String viewName = null;
//		String checkAdmin = check.checkAdmin();
		
//		if(StringUtils.equals(checkAdmin, "ok")) {
			ServiceResult result = freeBoardService.remove(freeNum);
			
			switch (result) {
			case OK:
				redirectAttributes.addFlashAttribute("message", "삭제되었습니다.");
				break;
			case FAIL:
				redirectAttributes.addFlashAttribute("message", "다시 시도해주세요.");
				break;
			}
			
			viewName = "redirect:/freeBoard";
//		}else {
//			viewName = checkAdmin;
//		}
		return viewName;
	}
}
