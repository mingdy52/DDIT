package kr.or.ddit.notice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.common.validate.InsertGroup;
import kr.or.ddit.common.validate.UpdateGroup;
import kr.or.ddit.notice.service.BoardReplyService;
import kr.or.ddit.notice.vo.BoardReplyVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author 이아인
 * @since 2022. 9. 2.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 9. 2.      이아인       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Slf4j
@Controller
@RequestMapping("/board")
public class BoardReplyController {
	@Inject
	BoardReplyService replyService;
	
	/*@GetMapping("reply")
	public String test() {
		return "notice/boardReply";
	}*/

	//댓글조회
	@ResponseBody
	@RequestMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE,value="reply/{boardNum}",method=RequestMethod.GET)
	public Map<String,Object> BoardList(
			@PathVariable String boardNum
			){
		log.info("왔니?{}",boardNum);
		List<BoardReplyVO> boardReplyList=replyService.boardReplyList(boardNum);
		Map<String, Object> boardMap= new HashMap<String, Object>();
		boardMap.put("boardReplyList",boardReplyList);
		return boardMap;
	}
	
	//댓글 추가
	@ResponseBody
	@PostMapping(path="boWrite",produces = "application/text; charset=utf8")
	public String boWriteReply(
			@Validated(InsertGroup.class)@RequestBody BoardReplyVO boardReplyVO
			,Errors errors
			){
		log.info("vochch:{}",boardReplyVO);
			if(!errors.hasErrors()) {
				String msg=replyService.createBoReply(boardReplyVO);
				return msg;

			}else {
				return "부적절";
			}
		
	}
	//대댓글 추가
	@ResponseBody
	@PostMapping(path="boReWrite",produces = "application/text; charset=utf8")
	public String boreWriteReply(
			@Validated(InsertGroup.class)@RequestBody BoardReplyVO boardReplyVO
			,Errors errors
			){
		log.info("vo:{}",boardReplyVO);
			if(!errors.hasErrors()) {
				String msg=replyService.createBoreReply(boardReplyVO);
				return msg;

			}else {
				return "부적절";
			}
	}
	//댓글 수정
	@ResponseBody
	@PostMapping(value="upwrite",produces = "application/text; charset=utf8")
	public String upBoReply(
			@Validated(UpdateGroup.class)@RequestBody BoardReplyVO boardReplyVO
			,Errors errors
			){
		log.info("upvo:{}",boardReplyVO);
			if(!errors.hasErrors()) {
				String msg=replyService.modifyBoReply(boardReplyVO);
				return msg;

			}else {
				return "부적절";
			}
	}
	
	//댓글 삭제
	@ResponseBody
	@GetMapping(path="delReply",produces = "application/text; charset=utf8")
	public String delReply(
			@RequestParam(value="delNum")String delNum
			) {
		String msg =replyService.removeBoReply(delNum);
		return msg;
	}
}