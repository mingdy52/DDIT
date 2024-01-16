package kr.or.ddit.proj.work.controller;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import kr.or.ddit.proj.work.vo.WorkVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/project/{pjId}/work/{workNum}/reply")
public class WorkReplyController {

	@Inject
	WorkService service;

	@Inject
	AttatchService attatchService;

	@Inject
	ColleagueService colleagueService;
	
	@Inject
	ProjectReplyService replyService;

	@PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<ReplyVO> insertReply(@ModelAttribute ReplyVO reply,Authentication auth) {
		if(ObjectUtils.isNotEmpty(reply.getReplyAttatch())) {
			AttatchVO attatch = new AttatchVO(reply.getReplyAttatch());
			attatch.setUploaderId(auth.getName());
			attatch.setAttatchOrder(1);
			attatchService.createReplyAttatch(attatch);
		}
		
		return service.insertReply(reply);

	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<ReplyVO> selectReply(@ModelAttribute ReplyVO reply) {
	
		return service.selectReply(reply);

	}

	@PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<ReplyVO> updateReply(@ModelAttribute ReplyVO reply,Authentication auth) {
		
		log.info("issueReply {}",reply);
		if(ObjectUtils.isNotEmpty(reply.getReplyAttatch())&&StringUtils.isNotEmpty(reply.getAttatchNum())) {
		AttatchVO attatch = new AttatchVO(reply.getReplyAttatch());
		attatch.setAttatchNum(reply.getAttatchNum());
		attatchService.modifyAttatch(attatch);
		}else if(ObjectUtils.isEmpty(reply.getReplyAttatch())&&StringUtils.isNotEmpty(reply.getAttatchNum())){
			AttatchVO attatch = new AttatchVO();
			attatch.setAttatchNum(reply.getAttatchNum());
			attatch.setAttatchOrder(1);
			attatch.setAttatchPlace(reply.getWoReplyNum());
			attatchService.deleteAttatchFile(attatch);
			
		}else if(ObjectUtils.isNotEmpty(reply.getReplyAttatch())&&StringUtils.isEmpty(reply.getAttatchNum())){
			AttatchVO attatch = new AttatchVO(reply.getReplyAttatch());
			attatch.setUploaderId(auth.getName());
			attatch.setAttatchOrder(1);
			attatch.setAttatchPlace(reply.getWoReplyNum());
			attatchService.createAttachNumReplyAttatch(attatch);
			
		}
		return service.updateReply(reply);

	}

	@PostMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<ReplyVO> deleteReply(@ModelAttribute ReplyVO reply) {
		AttatchVO attatch = new AttatchVO();
		
		//첨부파일 한개 삭제
		if(StringUtils.isBlank(reply.getParentWoReplyNum())&&StringUtils.isNotBlank(reply.getAttatchNum())){
		attatch.setAttatchNum(reply.getAttatchNum());
		attatch.setAttatchOrder(1);
		attatch.setAttatchPlace(reply.getWoReplyNum());
		log.info("attatch@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+attatch);
		attatchService.deleteAttatchFile(attatch);
	}else if(StringUtils.isNotBlank(reply.getParentWoReplyNum())&&StringUtils.isNotBlank(reply.getAttatchNum())) {
			
			attatch.setAttatchNum(reply.getAttatchNum());
			attatch.setAttatchOrder(1);
			attatch.setAttatchPlace(reply.getWoReplyNum());
			log.info("attatch111111111111111111111@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+attatch);
			attatchService.deleteAttatchFile(attatch);
			if(StringUtils.isNotBlank(attatchService.findAttachNum(reply.getParentWoReplyNum()))) {
			attatch.setAttatchNum(attatchService.findAttachNum(reply.getParentWoReplyNum()));
			attatch.setAttatchOrder(1);
			attatch.setAttatchPlace(reply.getParentWoReplyNum());
			log.info("attatch222222222222222222222222@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+attatch);
			attatchService.deleteAttatchFile(attatch);
			}
	}
		return service.deleteReply(reply);

	}

	

}
