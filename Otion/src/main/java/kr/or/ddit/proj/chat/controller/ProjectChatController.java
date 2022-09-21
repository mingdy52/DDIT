package kr.or.ddit.proj.chat.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.common.login.dao.LoginDAO;
import kr.or.ddit.proj.chat.service.ProjectChatService;
import kr.or.ddit.proj.chat.vo.ProjectChatRoomVO;
import kr.or.ddit.proj.chat.vo.ProjectChatVO;
import kr.or.ddit.proj.colleague.service.ColleagueService;
import kr.or.ddit.proj.colleague.vo.ColleagueVO;
import kr.or.ddit.proj.main.service.ProjectService;
import kr.or.ddit.proj.main.vo.ProjectVO;
import kr.or.ddit.proj.prod.service.ProjectProdService;
import kr.or.ddit.proj.prod.vo.ProjectPaymentVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("project/{pjId}/chatting")
public class ProjectChatController {

	@Inject
	ColleagueService colleagueService;

	@Inject
	ProjectChatService chatService;

	@Inject
	WebApplicationContext root;

	@Inject
	private ProjectProdService prodService;
	
	@Inject
	ProjectService projectService;

	@Inject
	private LoginDAO login;

	@GetMapping
	public String getChatRoom(Authentication auth, @PathVariable String pjId, RedirectAttributes res, Model model) {

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
			colleagueVO = colleagueService.retrieveColleague(colleagueVO);
		} catch (Exception e) {
			res.addFlashAttribute("message", "로그인 후 이용하실수 있습니다.");
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

		List<ProjectChatVO> chatList = chatService.retrieveChatList(colleagueVO);
		for (ProjectChatVO single : chatList) {
			List<ColleagueVO> list = new ArrayList<>();
			for (String colNum : single.getCrColleague().split(",")) {
				ColleagueVO singleColleague = new ColleagueVO();
				singleColleague.setPjId(pjId);
				singleColleague.setMemId(memId);
				singleColleague.setColNum(colNum);
				singleColleague = colleagueService.retrieveColleague(singleColleague);
				list.add(singleColleague);
			}
			single.setCrUpdate(single.getCrUpdate().substring(0, 10));
			single.setCollList(list);
		}

		List<ColleagueVO> colleagueList = colleagueService.retrieveColleagueList(pjId);
		List<ColleagueVO> noblack = colleagueList;
		if(noblack.size() != 0) {			
			for(ColleagueVO single : colleagueList) {			
				if(login.blackView(single.getMemId()) != null) {
					noblack.remove(single);
				}
			}
			
			model.addAttribute("colleagueList", noblack);
		}


		model.addAttribute("colleagueVO", colleagueVO);
		model.addAttribute("chatList", chatList);
		model.addAttribute("colleagueList", colleagueList);
		return "project2/chat";
	}

	@PostMapping
	public String insertChat(@ModelAttribute ProjectChatVO chatVO, @PathVariable String pjId, Authentication auth,
			RedirectAttributes res) {
		log.info("#################{}", chatVO);

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
			colleagueVO = colleagueService.retrieveColleague(colleagueVO);
		} catch (Exception e) {
			res.addFlashAttribute("message", "로그인 후 이용하실수 있습니다.");
			return "redirect:/";
		}
		chatVO.setColNum(colleagueVO.getColNum());
		chatVO.setCrColleague(chatVO.getCrColleague() + "," + colleagueVO.getColNum());

		chatService.createChat(chatVO);
		String path = root.getServletContext().getRealPath("/resources/chatImage/");
		File saveFolder = new File(path);
		if (!saveFolder.exists()) {
			saveFolder.mkdirs();
		}
		try {
			log.info("##################{}", saveFolder.getCanonicalPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		chatVO.saveTo(saveFolder);

		return "redirect:/project/{pjId}/chatting";
	}

	@GetMapping(value = "search", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<ProjectChatVO> searchChat(@ModelAttribute ProjectChatVO chatVO, @PathVariable String pjId,
			Authentication auth) {
		log.info("###################{}", chatVO);

		if (chatVO == null) {
			return null;
		}

		ColleagueVO colleague = new ColleagueVO();
		colleague.setMemId(auth.getName());
		colleague.setPjId(pjId);

		colleague = colleagueService.retrieveColleague(colleague);

		if (StringUtils.isNotBlank(chatVO.getCrName()))
			colleague.setCrName(chatVO.getCrName());

		log.info("####################{}", colleague);
		List<ProjectChatVO> chatList = chatService.retrieveChatList(colleague);
		for (ProjectChatVO single : chatList) {
			List<ColleagueVO> list = new ArrayList<>();
			for (String colNum : single.getCrColleague().split(",")) {
				ColleagueVO singleColleague = new ColleagueVO();
				singleColleague.setColNum(colNum);
				singleColleague.setPjId(pjId);
				singleColleague.setMemId(auth.getName());
				singleColleague = colleagueService.retrieveColleague(singleColleague);
				list.add(singleColleague);
			}
			single.setCrUpdate(single.getCrUpdate().substring(0, 10));
			single.setCollList(list);
		}

		return chatList;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ProjectChatVO getChattingRoom(@ModelAttribute ProjectChatVO chatVO, @PathVariable String pjId) {
		log.info("###################{}", chatVO);

		if (chatVO == null) {
			return null;
		}

		ProjectChatVO chatList = chatService.retrieveChatRoom(chatVO);

		// 받아온 해당 메시지 리스트에서 해당 하는 유저들에 대한 정보를 가져옴
		if (chatList != null && chatList.getChatList().size() > 0) {
			List<ColleagueVO> list = new ArrayList<>();
			for (String colNum : chatList.getCrColleague().split(",")) {
				ColleagueVO singleColleague = new ColleagueVO();
				singleColleague.setColNum(colNum);
				singleColleague = colleagueService.retrieveColleague(singleColleague);
				list.add(singleColleague);
			}
			chatList.setCollList(list);
			for (int i = 0; i < chatList.getChatList().size(); i++) {
				String memId = chatList.getChatList().get(i).getMemId();
				ColleagueVO colleague = new ColleagueVO();
				colleague.setMemId(memId);
				colleague.setPjId(pjId);
				colleague = colleagueService.retrieveColleague(colleague);
				chatList.getChatList().get(i).setColleague(colleague);
			}
			return chatList;
		} else {
			// 리스트가 없을 경우 해당 채팅방에 대한 정보만 가져옴
			chatVO = chatService.retrieveChat(chatVO.getCrNum());
			List<ColleagueVO> list = new ArrayList<>();
			for (String colNum : chatVO.getCrColleague().split(",")) {
				ColleagueVO singleColleague = new ColleagueVO();
				singleColleague.setColNum(colNum);
				singleColleague = colleagueService.retrieveColleague(singleColleague);
				list.add(singleColleague);
			}
			chatVO.setCollList(list);
			return chatVO;
		}

	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> sendChatting(@RequestParam String crNum, @RequestParam String crContent,
			Authentication auth) {
		if (StringUtils.isBlank(crNum) || StringUtils.isBlank(crContent)) {
			return null;
		}

		ProjectChatRoomVO roomVO = new ProjectChatRoomVO();
		roomVO.setCrNum(crNum);
		roomVO.setMemId(auth.getName());
		roomVO.setCrContent(crContent);

		chatService.createChatting(roomVO);

		Map<String, Object> map = new HashMap<>();
		map.put("message", "성공");

		return map;
	}

	@PostMapping(value = "profile", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> updateProfile(@RequestParam MultipartFile cooImage, Authentication auth,
			@PathVariable String pjId) {
		log.info("##################{}", cooImage);

		ColleagueVO colleague = new ColleagueVO();
		colleague.setCooImage(cooImage);
		colleague.setMemId(auth.getName());
		colleague.setPjId(pjId);
		String path = root.getServletContext().getRealPath("/resources/colleagueProfile");

		ColleagueVO selectColleague = colleagueService.retrieveColleague(colleague);
		if (StringUtils.isNotBlank(selectColleague.getCooProfile())) {
			File delete = new File(path, selectColleague.getCooProfile());
			delete.delete();
		}
		log.info("###################{}", colleague.getCooProfile());

		colleagueService.modifyProfile(colleague);
		File saveFolder = new File(path);
		if (!saveFolder.exists()) {
			saveFolder.mkdirs();
		}
		colleague.saveTo(saveFolder);

		Map<String, Object> map = new HashMap<>();
		map.put("message", "성공");

		return map;
	}

}
