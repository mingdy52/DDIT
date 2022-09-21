package kr.or.ddit.proj.msg.service;

import java.util.List;

import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.proj.colleague.vo.ColleagueVO;
import kr.or.ddit.proj.msg.vo.MessageReceiverVO;
import kr.or.ddit.proj.msg.vo.MessageVO;

/**
 * @author 작성자명
 * @since 2022. 8. 19.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 19.      고정현       CRUD작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
public interface MessageService {
	/**
	 * 메시지 리스트 가져오기
	 * 
	 * @param memId
	 * @return
	 */
	public List<MessageVO> retrieveMessageList(PagingVO<MessageVO> pagingVO);

	/**
	 * 보낸 메시지 리스트 가져오기
	 * @param pagingVO
	 * @return
	 */
	public List<MessageVO> retrieveMessageSenderList(PagingVO<MessageVO> pagingVO);
	
	/**
	 * 받은 메시지 리스트 가져오기
	 * @param messageReceiver
	 * @return
	 */
	public List<MessageReceiverVO> retrieveMessageReceiverList(PagingVO<MessageReceiverVO> pagingVO);
	
	/**
	 * 메시지 작성
	 * 
	 * @param message
	 */
	public void createMessage(MessageVO message);

	/**
	 * 메시지 수신자 삽입
	 * 
	 * @param messageReceiver
	 */
	public void createMessageReceiver(MessageReceiverVO messageReceiver);

	/**
	 * 송신자 메시지 삭제
	 * 
	 * @param message
	 */
	public void removeMessage(MessageVO message);

	/**
	 * 수신자 메시지 삭제
	 * 
	 * @param messageReceiver
	 */
	public void removeMessageReceiver(MessageReceiverVO messageReceiver);

	
	/**
	 * 해당 수신자 메시지 중요 체크
	 * @param messageRecevier
	 */
	public void updateReceiveMessageImportant(MessageReceiverVO messageRecevier);

	/**
	 * 해당 송신된 메시지 중요 체크
	 * @param message
	 */
	public void updateSendMessageImportant(MessageVO message);

	public String retrieveMember(ColleagueVO coll);

	public List<MessageVO> retrieveMessageReceiveList(ColleagueVO colleagueVO);

	public List<MessageReceiverVO> retrieveMessageSenderList(ColleagueVO colleagueVO);

	public MessageReceiverVO retrieveMessageReceiveDetail(MessageReceiverVO receiver);

	public MessageVO retrieveMessageSendDetail(MessageVO message);
}
