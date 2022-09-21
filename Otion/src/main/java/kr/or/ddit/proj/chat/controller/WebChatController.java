package kr.or.ddit.proj.chat.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.common.login.vo.MemberPrincipal;
import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.proj.chat.dao.ProjectChatDAO;
import kr.or.ddit.proj.chat.dao.ProjectChatDAOImpl;
import kr.or.ddit.proj.chat.service.ProjectChatService;
import kr.or.ddit.proj.chat.vo.ProjectChatRoomVO;
import kr.or.ddit.proj.chat.vo.ProjectChatVO;
import kr.or.ddit.proj.colleague.vo.ColleagueVO;
import lombok.extern.slf4j.Slf4j;

@ServerEndpoint("/websocket")
@Slf4j
@Controller
@RequestMapping
public class WebChatController {

	static List<Session> sessionList = Collections.synchronizedList(new ArrayList<>());
	
	ProjectChatDAO chatDAO = new ProjectChatDAOImpl();
	
	@OnOpen
	public void onOpen(Session session) {
		log.info("웹소켓 연결 {}" , session);
		sessionList.add(session);
	}
	
	@OnClose
	public void onClose(Session session) {
		log.info("웹소켓 연결해제 {}" , session);
		sessionList.remove(session);
	}
	
	@OnMessage
	public void onMessage(String message, Session session) {
		log.info("넘어온 메시지 {}" , message);
		String crNum = message.split(",")[0];
		String memId = message.split(",")[1];
		String msg = message.split(",")[2];
		
		ProjectChatRoomVO chatVO =new ProjectChatRoomVO();
		chatVO.setCrNum(crNum);
		chatVO.setMemId(memId);
		chatVO.setCrContent(msg);
		int cnt = chatDAO.countChatting(chatVO);
		chatVO.setCrContentNum(cnt);
		chatDAO.insertChatting(chatVO);
		
		synchronized (sessionList) {
			for(Session single : sessionList) {
				if(!single.equals(session)) {
					try {
						single.getBasicRemote().sendText(msg);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
}
