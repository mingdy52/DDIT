package kr.or.ddit.proj.chat.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.proj.chat.dao.ProjectChatDAO;
import kr.or.ddit.proj.chat.vo.ProjectChatRoomVO;
import kr.or.ddit.proj.chat.vo.ProjectChatVO;
import kr.or.ddit.proj.colleague.vo.ColleagueVO;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class ProjectChatServiceImpl implements ProjectChatService{

	@Inject
	ProjectChatDAO chatDAO;
	
	@Override
	public List<ProjectChatVO> retrieveChatList(ColleagueVO colleague) {
		// TODO Auto-generated method stub
		return chatDAO.selectChatList(colleague);
	}

	@Override
	public void createChat(ProjectChatVO chatVO) {
		// TODO Auto-generated method stub
		chatDAO.insertChat(chatVO);
	}

	@Override
	public ProjectChatVO retrieveChatRoom(ProjectChatVO chatVO) {
		// TODO Auto-generated method stub
		return chatDAO.selectChatRoom(chatVO);
	}

	@Override
	public void createChatting(ProjectChatRoomVO roomVO) {
		// TODO Auto-generated method stub
		int cnt = chatDAO.countChatting(roomVO);
		roomVO.setCrContentNum(cnt);
		log.info("####################{}", roomVO);
		chatDAO.insertChatting(roomVO);
	}

	@Override
	public ProjectChatVO retrieveChat(String crNum) {
		// TODO Auto-generated method stub
		return chatDAO.selectChat(crNum);
	}

}
