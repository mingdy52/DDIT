package kr.or.ddit.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.vo.AttatchVO;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.common.exception.InvalidPasswordException;
import kr.or.ddit.validate.DeleteGroup;
import kr.or.ddit.validate.UpdateGroup;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board")
//@SessionAttributes("board") // 뭔지 모르겠지만 세션으로 공유하겠다. 여기서 만들어지는 모든 보드 데이터는 세션 스코프를 통해서 공유한다.
public class BoardModifyController {
	
	@Inject
	private BoardService service;
	
	@GetMapping("{boNo}/form")
	public String boardEdit(
			@PathVariable int boNo
			, @RequestParam(required=false) int[] delAttNos
			, Model model
			) {
		
		BoardVO board = service.retrieveBoard(boNo);
		
		if(!model.containsAttribute("board")) {
			model.addAttribute("board", board);
		} else {
			List<AttatchVO> attatchList = board.getAttatchList();
		}
		
		return "board/boardEdit";
	}
	
	@PutMapping("{boNo}")
	public String boardUpdate(
		@Validated(UpdateGroup.class)@ModelAttribute("board") BoardVO board
		, Errors errors
		, RedirectAttributes redirectAttributes
//		, SessionStatus sessionStatus
	) {
		String viewName = null;
		if(!errors.hasErrors()) {
			try {
				service.modifyBoard(board);
				viewName = "redirect:/board/{boNo}";
//				sessionStatus.setComplete();
			}catch (InvalidPasswordException e) {
				// 
				redirectAttributes.addFlashAttribute("message", "비밀번호 오류");
				redirectAttributes.addFlashAttribute("board", board);
				viewName = "redirect:/board/{boNo}/form";
			}
		}else {
			// 
			String errorAttrName = BindingResult.MODEL_KEY_PREFIX + "board";
			redirectAttributes.addFlashAttribute(errorAttrName, errors);
			redirectAttributes.addFlashAttribute("board", board);
			viewName = "redirect:/board/{boNo}/form";
		}
		return viewName;
	}
	
	@DeleteMapping("{boNo}")
	public String deleteBoard(
			@Validated(DeleteGroup.class) BoardVO board
			, Errors error
			, RedirectAttributes redirectAttributes
			) {
		
		String viewName = null;
		String message = null;
		
		// 인증 처리는 모두 리다이렉트
		if(!error.hasErrors()) {
			try {
				service.removeBoard(board);
				viewName = "redirect:/board";
				message = "삭제 완료";
			} catch (InvalidPasswordException e) {
				message = "비밀번호 오류";
				viewName = "redirect:/board/{boNo}"; 
				// 경로변수를 사용하면 로지컬 뷰 네임에 경로변수 템플릿을 바로 사용할 수 있음.
				
			}
		} else {
			viewName = "redirect:/board/{boNo}";
			message = "비밀번호 누락";
		}
//		예외 발생하면 처리 구조 있어야함. 500불가
		
		redirectAttributes.addFlashAttribute("message", message);
		return viewName;
	}
	

	
}
