package kr.or.ddit.proj.post.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.common.vo.SimpleSearchCondition;
import kr.or.ddit.common.vo.WorkCalSearchVO;
import kr.or.ddit.common.vo.WorkSearchVO;
import kr.or.ddit.proj.calendar.service.CalendarService;
import kr.or.ddit.proj.calendar.vo.CalendarVO;
import kr.or.ddit.proj.colleague.service.ColleagueService;
import kr.or.ddit.proj.colleague.vo.ColleagueVO;
import kr.or.ddit.proj.main.service.ProjectService;
import kr.or.ddit.proj.main.vo.ProjectVO;
import kr.or.ddit.proj.msg.service.MessageService;
import kr.or.ddit.proj.msg.vo.MessageReceiverVO;
import kr.or.ddit.proj.msg.vo.MessageVO;
import kr.or.ddit.proj.post.service.ProjectMyPostService;
import kr.or.ddit.proj.post.vo.MyPostVO;
import kr.or.ddit.proj.prod.service.ProjectProdService;
import kr.or.ddit.proj.prod.vo.ProjectPaymentVO;
import kr.or.ddit.proj.reply.service.ProjectReplyService;
import kr.or.ddit.proj.work.service.WorkService;
import kr.or.ddit.proj.work.vo.ReplyVO;
import kr.or.ddit.proj.work.vo.WorkVO;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 고정현
 * @since 2022. 8. 5.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                     수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 5.     고정현               tiles 적용
 * 2022  8. 23.    고정현              내 게시글 리스트 가져오기
 * 2022  9. 1.     고정현              접근 제한 막기
 * Copyright (c) 2022 by DDIT All right reserved
 *      </pre>
 */
@Controller
@Slf4j
@RequestMapping("/project/{pjId}/myPost")
public class ProjectMyPostController {

	@Inject
	ProjectMyPostService projectMyPostService;

	@Inject
	CalendarService calendarService;

	@Inject
	ColleagueService colleagueService;

	@Inject
	MessageService messageService;

	@Inject
	WorkService workService;

	@Inject
	ProjectReplyService replyService;
	
	@Inject
	private ProjectService projectService;
	
	@Inject
	private ProjectProdService prodService;

	@GetMapping
	public String getPostList(@PathVariable String pjId, Authentication auth, RedirectAttributes res, Model model) {
		if ("none".equals(pjId) || StringUtils.isBlank(pjId)) {
			res.addFlashAttribute("message", "프로젝트를 선택해야 볼 수 있습니다.");
			return "redirect:/project";
		}

		if (auth == null) {
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
		} catch (Exception e) {
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
		
		// 캘린더 리스트 가져오기
		List<CalendarVO> calList = calendarService.retrieveCalendarList(colleagueVO);
		if(calList.size() != 0) {			
			for (CalendarVO cal : calList) {
				cal.setWorkCalStart(cal.getWorkCalStart().substring(0, 10));
				cal.setWorkCalEnd(cal.getWorkCalEnd().substring(0, 10));
			}
		}
		// 받은 메시지 가져오기
		List<MessageVO> sendList = messageService.retrieveMessageReceiveList(colleagueVO);
		
		log.info("#########{}",sendList);

		if(sendList.size() != 0) {			
			StringBuffer names = new StringBuffer();
			for (int i = 0; i < sendList.size(); i++) {
				MessageVO single = sendList.get(i);
				for (int j = 0; j < single.getReceiverList().size(); j++) {
					ColleagueVO receiveColleague = new ColleagueVO();
					MessageReceiverVO receive = single.getReceiverList().get(j);
					receiveColleague.setPjId(pjId);
					receiveColleague.setMemId(receive.getReceiverNum());
					receiveColleague = colleagueService.retrieveColleague(receiveColleague);
					names.append(receiveColleague.getMemName());
					if (j != single.getReceiverList().size() - 1) {
						names.append(",");
					}
				}
				if(single.getMsgTitle().length() >= 8)
					single.setMsgTitle(single.getMsgTitle().substring(0,8) + "...");
				if(single.getMsgContent().length() >=8)
					single.setMsgContent(single.getMsgContent().substring(0, 8) + "...");
				single.setReceiveName(names.toString());
			}
		}
		// 보낸 메시지 가져오기
		List<MessageReceiverVO> receiveList = messageService.retrieveMessageSenderList(colleagueVO);
		if(receiveList.size() != 0) {
			for (MessageReceiverVO receive : receiveList) {
				ColleagueVO sendColleague = new ColleagueVO();
				sendColleague.setPjId(pjId);
				sendColleague.setMemId(receive.getMessage().getMsgWriterId());
				sendColleague = colleagueService.retrieveColleague(sendColleague);
				receive.setSender(sendColleague.getMemName());
				if(receive.getMessage().getMsgTitle().length() >= 8)
					receive.getMessage().setMsgTitle(receive.getMessage().getMsgTitle().substring(0,8) + "...");
				if(receive.getMessage().getMsgContent().length()>=8)
					receive.getMessage().setMsgContent(receive.getMessage().getMsgContent().substring(0,8) + "...");
			}
		}
		// 내 업무 가져오기
		List<WorkVO> workList = workService.retriverMyWork(colleagueVO);
		// 내 댓글 가져오기
		List<ReplyVO> replyList = replyService.retrieveReplyList(colleagueVO);

		model.addAttribute("calList", calList);
		model.addAttribute("sendList", sendList);
		model.addAttribute("receiveList", receiveList);
		model.addAttribute("workList", workList);
		model.addAttribute("replyList", replyList);

		return "project2/myPost/myPostList";
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> getSearchPostList(@PathVariable String pjId, @RequestParam(defaultValue = "1") int page,
			@ModelAttribute SimpleSearchCondition simpleCondition, Authentication auth) {

		String memId = auth.getName();

		Map<String, Object> map = new HashedMap<>();
		
		WorkCalSearchVO searchVO = new WorkCalSearchVO();
		searchVO.setWorkCalTitle(simpleCondition.getSearchWord());
		WorkSearchVO workSearchVO = new WorkSearchVO();
		workSearchVO.setWorkTitle(simpleCondition.getSearchWord());
		
		
		ColleagueVO colleague = new ColleagueVO();
		colleague.setPjId(pjId);
		colleague.setMemId(memId);
		colleague = colleagueService.retrieveColleague(colleague);
		colleague.setSearchVO(searchVO);
		colleague.setSimpleCondition(simpleCondition);
		colleague.setWorkSearchVO(workSearchVO);
		
		// 캘린더 리스트 가져오기
		List<CalendarVO> calList = calendarService.retrieveCalendarList(colleague);
		if(calList.size() != 0) {			
			for (CalendarVO cal : calList) {
				cal.setWorkCalStart(cal.getWorkCalStart().substring(0, 10));
				cal.setWorkCalEnd(cal.getWorkCalEnd().substring(0, 10));
			}
		}
		map.put("calList", calList);
		// 받은 메시지 가져오기
		List<MessageVO> sendList = messageService.retrieveMessageReceiveList(colleague);

		if(sendList.size() != 0) {			
			StringBuffer names = new StringBuffer();
			for (int i = 0; i < sendList.size(); i++) {
				MessageVO single = sendList.get(i);
				for (int j = 0; j < single.getReceiverList().size(); j++) {
					ColleagueVO receiveColleague = new ColleagueVO();
					MessageReceiverVO receive = single.getReceiverList().get(j);
					receiveColleague.setPjId(pjId);
					receiveColleague.setMemId(receive.getReceiverNum());
					receiveColleague = colleagueService.retrieveColleague(receiveColleague);
					names.append(receiveColleague.getMemName());
					if (j != single.getReceiverList().size() - 1) {
						names.append(",");
					}
				}
				if(single.getMsgTitle().length() >= 8)
					single.setMsgTitle(single.getMsgTitle().substring(0,8) + "...");
				if(single.getMsgContent().length() >=8)
					single.setMsgContent(single.getMsgContent().substring(0, 8) + "...");
				single.setReceiveName(names.toString());
			}
		}
		map.put("sendList", sendList);
		// 보낸 메시지 가져오기
		List<MessageReceiverVO> receiveList = messageService.retrieveMessageSenderList(colleague);
		if(receiveList.size() != 0) {
			for (MessageReceiverVO receive : receiveList) {
				ColleagueVO sendColleague = new ColleagueVO();
				sendColleague.setPjId(pjId);
				sendColleague.setMemId(receive.getMessage().getMsgWriterId());
				sendColleague = colleagueService.retrieveColleague(sendColleague);
				receive.setSender(sendColleague.getMemName());
				if(receive.getMessage().getMsgTitle().length() >= 8)
					receive.getMessage().setMsgTitle(receive.getMessage().getMsgTitle().substring(0,8) + "...");
				if(receive.getMessage().getMsgContent().length()>=8)
					receive.getMessage().setMsgContent(receive.getMessage().getMsgContent().substring(0,8) + "...");
			}
		}
		map.put("receiveList",receiveList);
		// 내 업무 가져오기
		List<WorkVO> workList = workService.retriverMyWork(colleague);
		map.put("workList",workList);
		// 내 댓글 가져오기
		List<ReplyVO> replyList = replyService.retrieveReplyList(colleague);
		map.put("replyList",replyList);

		return map;
	}
}
