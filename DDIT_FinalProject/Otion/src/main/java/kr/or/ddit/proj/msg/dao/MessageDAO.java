package kr.or.ddit.proj.msg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

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
 * 2022. 8. 19.      고정현       메시지 리스트, 메시지 작성, 메시지 삭제(개인만)
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Mapper
public interface MessageDAO {

	public int selectTotalRecord(PagingVO<MessageVO> pagingVO);
	
	/**
	 * 메시지 리스트 가져오기
	 * @param memId
	 * @return
	 */
	public List<MessageVO> messageList(PagingVO<MessageVO> pagingVO);
	
	/**
	 * 보낸 메시지 리스트
	 * @param pagingVO
	 * @return
	 */
	public List<MessageVO> selectMessageSenderList(PagingVO<MessageVO> pagingVO);
	
	/**
	 * 받은 메시지 리스트
	 * @param message
	 * @return
	 */
	public List<MessageReceiverVO> selectMessageReceiverList(PagingVO<MessageReceiverVO> pagingVO);
	
	
	/**
	 * 메시지 작성
	 * @param message
	 */
	public void insertMessage(MessageVO message);
	
	/**
	 * 메시지 수신자 삽입
	 * @param messageReceiver
	 */
	public void insertMessageReceiver(MessageReceiverVO messageReceiver);
	
	
	/**
	 * 송신자 메시지 삭제
	 * @param message
	 */
	public void deleteMessage(MessageVO message);
	
	/**
	 * 수신자 메시지 삭제
	 * @param messageReceiver
	 */
	public void deleteMessageReceiver(MessageReceiverVO messageReceiver);

	/**
	 * 해당 수신된 메시지 중요 체크
	 * @param messageRecevier
	 */
	public void updateReceiveMessageImportant(MessageReceiverVO messageRecevier);

	/**
	 * 해당 송신한 메시지 중요 체크
	 * @param message
	 */
	public void updateSendMessageImportant(MessageVO message);

	/**
	 * 해당 중요체크된 수신메시지 중요도 해제
	 * @param messageRecevier
	 */
	public void deleteReceiveMessageImportant(MessageReceiverVO messageRecevier);

	/**
	 * 해당 중요체크된 송신 메시지 중요도 해제
	 * @param message
	 */
	public void deleteSendMessageImportant(MessageVO message);

	public int selectTotalSendRecord(PagingVO<MessageVO> pagingVO);

	public int selectTotalReceiveRecord(PagingVO<MessageReceiverVO> pagingVO);

	public String selectMember(ColleagueVO coll);

	public List<MessageVO> selectMsgSenderList(ColleagueVO colleagueVO);

	public List<MessageReceiverVO> selectMsgReceiveList(ColleagueVO colleagueVO);

	public MessageReceiverVO selectMessageReceiveDetail(MessageReceiverVO receiver);

	public MessageVO selectMessageSendDetail(MessageVO message);
}
