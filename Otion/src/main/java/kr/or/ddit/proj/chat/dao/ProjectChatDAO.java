package kr.or.ddit.proj.chat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.proj.chat.vo.ProjectChatRoomVO;
import kr.or.ddit.proj.chat.vo.ProjectChatVO;
import kr.or.ddit.proj.colleague.vo.ColleagueVO;

/**
 * @author 작성자명
 * @since 2022. 9. 1.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 9. 1.      작성자명       채팅방 리스트 받아오기
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Mapper
public interface ProjectChatDAO {

	public List<ProjectChatVO> selectChatList(ColleagueVO colleague);

	public void insertChat(ProjectChatVO chatVO);

	public ProjectChatVO selectChatRoom(ProjectChatVO chatVO);

	public void insertChatting(ProjectChatRoomVO roomVO);

	public int countChatting(ProjectChatRoomVO roomVO);

	public ProjectChatVO selectChat(String crNum);
}
