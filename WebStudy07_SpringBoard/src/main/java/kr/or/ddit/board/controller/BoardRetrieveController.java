package kr.or.ddit.board.controller;


import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.PagingVO;
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
public class BoardRetrieveController {
	
	@Inject
	private BoardService service;
	
	@GetMapping
	public String getView() {
		return "board/boardList";
	}
	
	@ModelAttribute("board")
	public BoardVO board() {
		return new BoardVO();
	}
	
	@ResponseBody // 모지컬 뷰네임 필요 없음.
	@GetMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PagingVO<BoardVO> getBoardList(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
//						, @ModelAttribute("simpleCondition") SimpleSearchCondition simpleCondition
			) {
		log.info("currentPage: {}", currentPage);
		PagingVO<BoardVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
//		pagingVO.setSimpleCondition(simpleCondition);
		List<BoardVO> boardList = service.retrieveBoardList(pagingVO);
		pagingVO.setDataList(boardList);
		return pagingVO;
	}
	
	@RequestMapping("{boNo}")
	public String getView(@PathVariable int boNo, Model model) {
		log.info("boNo : {}", boNo);
		BoardVO boardVO = service.retrieveBoard(boNo);
		
		model.addAttribute("boardVO", boardVO);
		return "board/boardView";
	}
	

}

	
	
	
	

