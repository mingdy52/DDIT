package kr.or.ddit.board.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.validate.InsertGroup;
import lombok.extern.slf4j.Slf4j;

/**
 * /board GET
 * /board/23 GET
 * /board/form GET
 * /board POST
 * /board/23/form GET
 * /board/23 PUT
 * /board/23 DELETE
 * 피동기 페이징과 비동기 검색(제목, 작성자, 내용, 전체) 구조
 */
@Slf4j
@Controller
@RequestMapping("/board")
public class BoardInsertController {
	
	@Inject
	private BoardService service;
	
	@ModelAttribute("board")
	public BoardVO board() {
		return new BoardVO();
	}
	
	
	@GetMapping("form")
	public String getInsertForm () {
		return "board/boardform";
	}
	
	
	@PostMapping
	public String boardInsert(
		@Validated(InsertGroup.class) @ModelAttribute("board") BoardVO board
		, Errors errors
	) {
		String viewName = null;
		if(!errors.hasErrors()) {
			service.createBoard(board);
			viewName = "redirect:/board";
		}else {
			viewName = "board/boardForm";
		}
		return viewName;
	}
}
