package kr.or.ddit.proj.msg.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.print.attribute.standard.Media;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.common.login.dao.LoginDAO;
import kr.or.ddit.common.login.vo.MemberPrincipal;
import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.common.vo.SimpleSearchCondition;
import kr.or.ddit.proj.colleague.service.ColleagueService;
import kr.or.ddit.proj.colleague.vo.ColleagueVO;
import kr.or.ddit.proj.main.service.ProjectService;
import kr.or.ddit.proj.main.vo.ProjectVO;
import kr.or.ddit.proj.msg.service.MessageService;
import kr.or.ddit.proj.msg.vo.MessageReceiverVO;
import kr.or.ddit.proj.msg.vo.MessageVO;
import kr.or.ddit.proj.prod.service.ProjectProdService;
import kr.or.ddit.proj.prod.vo.ProjectPaymentVO;
import kr.or.ddit.proj.work.service.WorkService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 고정현
 * @since 2022. 8. 4.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 4.      고정현       최초 작성(타일즈 미적용)
 * 2022. 8. 19.     고정현      메시지 리스트 작성 시작
 * 2022. 9. 1.      고정현      접근 제한 막기
 * Copyright (c) 2022 by DDIT All right reserved
 *      </pre>
 */
@Slf4j
@Controller
@RequestMapping("project/{pjId}/msg")
public class MessageController {

	@Inject
	private MessageService messageService;

	@Inject
	private ColleagueService colleagueService;
	
	@Inject
	private WorkService workService;
	
	@Inject
	private LoginDAO login;
	
	@Inject
	private ProjectService projectService;
	
	@Inject
	private ProjectProdService prodService;

	@ModelAttribute
	public MessageVO getMessageVO() {
		return new MessageVO();
	}

	@GetMapping
	public String listMsg(
			@PathVariable String pjId,
			Model model,
			Authentication auth,
			RedirectAttributes res
			) {
		if("none".equals(pjId) || StringUtils.isBlank(pjId)) {
			res.addFlashAttribute("message", "프로젝트를 선택해야 볼 수 있습니다.");
			return "redirect:/project";
		}
		
		if(auth == null) {
			res.addFlashAttribute("message", "로그인 후 이용하실수 있습니다.");
			return "redirect:/";
		}
		String memId = auth.getName();
		
		ColleagueVO colleagueVO = new ColleagueVO();
		colleagueVO.setMemId(memId);
		colleagueVO.setPjId(pjId);
		
		try {
			res.addFlashAttribute("message", "로그인 후 이용하실수 있습니다.");
			colleagueVO = colleagueService.retrieveColleague(colleagueVO);
		}catch(Exception e) {
			return "redirect:/";
		}
		
		ProjectVO project = new ProjectVO();
		project = projectService.retrieveProject(pjId);
		// 해당 프로젝트가 결제 되었는지 확인 유무
		ProjectPaymentVO payVO = prodService.retrievePayment(pjId);
		if (payVO == null) {
			return "project2/projectHome";
		}

		model.addAttribute("project", project);
		
		// 해당 프로젝트에 참가중인 모든 팀원들을 가져옴(해당 팀원이 블락 당했는지 여부를 확인해야함)
		List<ColleagueVO> colleagueList = colleagueService.retrieveColleagueList(pjId);
		
		// 블랙 리스트 가져오기
		List<ColleagueVO> noblack = colleagueList;
		if(noblack.size() != 0) {			
			for(ColleagueVO single : colleagueList) {			
				if(login.blackView(single.getMemId()) != null) {
					noblack.remove(single);
				}
			}
			
			model.addAttribute("colleagueList", noblack);
		}

		return "project2/msg/msgList";
	}
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public MessageReceiverVO getMessageDetail(
			@RequestParam String msgNum,
			@RequestParam String type,
			@PathVariable String pjId,
			Authentication auth
			) {
		StringBuffer sb = new StringBuffer();
		
		if("send".equals(type)) {
			MessageVO message = new MessageVO();
			message.setMsgNum(msgNum);
			message.setMsgWriterId(auth.getName());
			message = messageService.retrieveMessageSendDetail(message);
			
			ColleagueVO colleagueVO = new ColleagueVO();
			colleagueVO.setMemId(message.getMsgWriterId());
			colleagueVO.setPjId(pjId);
			
			colleagueVO = colleagueService.retrieveColleague(colleagueVO);
			message.setSendName(colleagueVO.getMemName());
			
			for(int i=0; i<message.getReceiverList().size(); i++) {
				MessageReceiverVO single = message.getReceiverList().get(i);
				colleagueVO.setMemId(single.getReceiverNum());
				colleagueVO = colleagueService.retrieveColleague(colleagueVO);
				sb.append(colleagueVO.getMemName());
				if(i != message.getReceiverList().size() -1) {
					sb.append(",");
				}
			}
			message.setReceiveName(sb.toString());
			MessageReceiverVO receiver = new MessageReceiverVO();
			receiver.setMessage(message);
			return receiver;
			
		}else if("receive".equals(type)) {
			MessageReceiverVO receiver = new MessageReceiverVO();
			receiver.setMsgNum(msgNum);
			receiver.setReceiverNum(auth.getName());
			// 해당 메시지에 대한 상세를 가져옴
			receiver = messageService.retrieveMessageReceiveDetail(receiver);
			ColleagueVO colleagueVO = new ColleagueVO();
			colleagueVO.setMemId(receiver.getMessage().getMsgWriterId());
			colleagueVO.setPjId(pjId);
			
			colleagueVO = colleagueService.retrieveColleague(colleagueVO);
			receiver.setSender(colleagueVO.getMemName());
			
			colleagueVO.setMemId(receiver.getReceiverNum());
			colleagueVO = colleagueService.retrieveColleague(colleagueVO);
			receiver.setReceiver(colleagueVO.getMemName());
			
			return receiver;
		}
		return null;
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<?> getAllMessageList(@PathVariable String pjId,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@ModelAttribute("simpleSearchCondition") SimpleSearchCondition searchVO) {

		String memId = SecurityContextHolder.getContext().getAuthentication().getName();

		ColleagueVO colleague = new ColleagueVO();
		colleague.setPjId(pjId);
		colleague.setMemId(memId);
		colleague = colleagueService.retrieveColleague(colleague);

		if (searchVO.getSearchType().equals("send")) {
			PagingVO<MessageVO> pagingVO = new PagingVO<>(5, 3);
			MessageVO messageVO = new MessageVO();
			messageVO.setMsgWriterId(memId);
			pagingVO.setDetailCondition(messageVO);

			pagingVO.setCurrentPage(page);
			pagingVO.setSimpleCondition(searchVO);
			pagingVO.setColleague(colleague);
			// 일정 정보 가져오기
			// 해당 일정정보 총 페이징 가져오기
			messageService.retrieveMessageSenderList(pagingVO);
			return pagingVO;
		} else if (searchVO.getSearchType().equals("receive")) {
			PagingVO<MessageReceiverVO> pagingVO = new PagingVO<>(5, 3);
			MessageReceiverVO receiverVO = new MessageReceiverVO();
			receiverVO.setReceiverNum(memId);

			pagingVO.setDetailCondition(receiverVO);

			pagingVO.setCurrentPage(page);
			pagingVO.setSimpleCondition(searchVO);
			pagingVO.setColleague(colleague);
			// 일정 정보 가져오기
			// 해당 일정정보 총 페이징 가져오기
			messageService.retrieveMessageReceiverList(pagingVO);
			return pagingVO;
		}else if(searchVO.getSearchType().equals("important") || searchVO.getSearchType().equals("delete")) {
			PagingVO<MessageVO> pagingVO = new PagingVO<>(5, 3);
			MessageVO messageVO = new MessageVO();
			messageVO.setMsgWriterId(memId);
			messageVO.setPjId(pjId);
			pagingVO.setDetailCondition(messageVO);

			pagingVO.setCurrentPage(page);
			pagingVO.setSimpleCondition(searchVO);
			pagingVO.setColleague(colleague);
			// 일정 정보 가져오기
			// 해당 일정정보 총 페이징 가져오기
			messageService.retrieveMessageList(pagingVO);
			return pagingVO;
		}

		return null;
	}

	@GetMapping(value = "important", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, String> setImportantMessage(
			@PathVariable String pjId, 
			@RequestParam String msgNum,
			@RequestParam String who, 
			@RequestParam boolean del,
			Authentication auth) {
		String memId = auth.getName();
		log.info("####### {} {} {}", msgNum, who, del);
		
		if ("sender".equals(who)) {
			// 보낸 메시지 중요처리
			MessageVO message = new MessageVO();
			// 삭제 여부 표시
			message.setDelYn(del);
			message.setMsgNum(msgNum);
			message.setPjId(pjId);
			message.setMsgWriterId(memId);
			messageService.updateSendMessageImportant(message);
		} else {
			// 받은 메시지 중요 처리
			MessageReceiverVO messageReceiver = new MessageReceiverVO();
			messageReceiver.setDelYn(del);
			messageReceiver.setPjId(pjId);
			messageReceiver.setMsgNum(msgNum);
			messageReceiver.setReceiverNum(memId);
			messageService.updateReceiveMessageImportant(messageReceiver);

		}
		Map<String, String> map = new HashMap<>();
		map.put("message", "성공");
		return map;
	}

	@PostMapping("/form")
	public String formMsg(@PathVariable String pjId, @ModelAttribute MessageVO messageVO, Errors errors,
			Authentication auth, @RequestParam String... receiverNum
			) {
		log.info("**********{}", receiverNum);
		if (!errors.hasErrors() && receiverNum.length != 0) {
			// 해당 회원 이름으로 아이디 받아오기
			List<String> receiverId = new ArrayList<>();
			for(String single : receiverNum) {
				ColleagueVO coll = new ColleagueVO();
				coll.setPjId(pjId);
				coll.setMemName(single);
				String id = messageService.retrieveMember(coll);
				if(id != null) {
					receiverId.add(id);
				}
			}
			log.info("**********{}", receiverId);
			
			// 프로젝트 아이디 넣기
			messageVO.setPjId(pjId);
			// 로그인한 유저 아이디 받아오기
			MemberVO memberVO = ((MemberPrincipal) auth.getPrincipal()).getRealMember();
			messageVO.setMsgWriterId(memberVO.getMemId());
			List<MessageReceiverVO> receiverList = new ArrayList<>();
			// 수신자 내역 받아오기
			for (int i = 0; i < receiverNum.length; i++) {
				// 수신자 객체 생성
				MessageReceiverVO receiverVO = new MessageReceiverVO();
				// 수신자 아이디 등록
				receiverVO.setReceiverNum(receiverId.get(i));
				// 해당 프로젝트 ID 등록
				receiverVO.setPjId(pjId);
				receiverList.add(receiverVO);
			}
			messageVO.setReceiverList(receiverList);
			// 해당 메시지 내용 들고 가기
			messageService.createMessage(messageVO);
		} else {
			return "project2/msg/msgList";
		}

		return "redirect:/project/{pjId}/msg";
	}

	@DeleteMapping
	public String messageDelete(@PathVariable String pjId,
			@RequestParam String who, 
			Authentication auth ,
			RedirectAttributes res,
			@RequestParam String... msgNum) {
		// 회원 아이디 받아오기
		String memId = auth.getName();

		log.info("###########{}", msgNum);
		
		if (msgNum.length == 1) {
			if ("sender".equals(who)) {
				MessageVO message = new MessageVO();
				message.setMsgNum(msgNum[0]);
				messageService.removeMessage(message);
			} else {
				MessageReceiverVO messageReceiver = new MessageReceiverVO();
				messageReceiver.setPjId(pjId);
				messageReceiver.setMsgNum(msgNum[0]);
				messageReceiver.setReceiverNum(memId);
				messageService.removeMessageReceiver(messageReceiver);
			}
		} else {
			if ("sender".equals(who)) {
				for (int i = 0; i < msgNum.length; i++) {
					MessageVO message = new MessageVO();
					message.setMsgNum(msgNum[i]);
					messageService.removeMessage(message);
				}
			} else {
				for (int i = 0; i < msgNum.length; i++) {
					MessageReceiverVO messageReceiver = new MessageReceiverVO();
					messageReceiver.setPjId(pjId);
					messageReceiver.setMsgNum(msgNum[i]);
					messageReceiver.setReceiverNum(memId);
					messageService.removeMessageReceiver(messageReceiver);
				}
			}

		}
		res.addFlashAttribute("message", "삭제 성공");
		return "redirect:/project/{pjId}/msg";
	}
}
