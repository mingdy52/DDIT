package kr.or.ddit.proj.chat.service;

import java.util.List;

import kr.or.ddit.proj.chat.vo.ProjectChatRoomVO;
import kr.or.ddit.proj.chat.vo.ProjectChatVO;
import kr.or.ddit.proj.colleague.vo.ColleagueVO;

public interface ProjectChatService {
	
	public List<ProjectChatVO> retrieveChatList(ColleagueVO colleague);
	
	public void createChat(ProjectChatVO chatVO);

	public ProjectChatVO retrieveChatRoom(ProjectChatVO chatVO);

	public void createChatting(ProjectChatRoomVO roomVO);

	/**
	 * 해당 하는 채팅방을 정보 가져옴
	 * @param crNum
	 * @return
	 */
	public ProjectChatVO retrieveChat(String crNum);
}
