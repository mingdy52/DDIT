package kr.or.ddit.proj.reply.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.annotation.MultipartConfig;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.common.attach.service.AttatchService;
import kr.or.ddit.common.vo.AttatchVO;
import kr.or.ddit.proj.colleague.service.ColleagueService;
import kr.or.ddit.proj.colleague.vo.ColleagueVO;
import kr.or.ddit.proj.reply.service.ProjectReplyService;
import kr.or.ddit.proj.work.service.WorkService;
import kr.or.ddit.proj.work.vo.ReplyVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@MultipartConfig
@RequestMapping("/project/{pjId}/reply")
public class ProjectReplyController {
	
	@Inject
	ColleagueService colleagueService;
	
	@Inject
	AttatchService attatchService;
	
	@Inject
	ProjectReplyService replyService;
	
	@PostMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> insertReplyAttatch(
			@RequestParam("replyAttatch") MultipartFile replyAttatch,
			@PathVariable String pjId,
			Authentication auth
			) {
		log.info("############# {} : {}", replyAttatch.getOriginalFilename());
		
		String memId= auth.getName();
		ColleagueVO colleague = new ColleagueVO();
		colleague.setPjId(pjId);
		colleague.setMemId(memId);
		colleague = colleagueService.retrieveColleague(colleague);
		
		AttatchVO attatch = new AttatchVO(replyAttatch);
		attatch.setAttatchOrder(1);
		attatch.setUploaderId(memId);
		
		attatchService.createReplyAttatch(attatch);
		
		log.info("################ {}",attatch);
//		if(StringUtils.isNotBlank(parentWoReplyNum)) {
//			reply.setParentWoReplyNum(parentWoReplyNum);
//		}
		Map<String, Object> map = new HashMap<>();
		map.put("woReplyNum", attatch.getAttatchPlace());
		
		return map;
	}
}
