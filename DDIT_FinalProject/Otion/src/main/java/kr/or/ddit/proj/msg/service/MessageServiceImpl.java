package kr.or.ddit.proj.msg.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.proj.colleague.vo.ColleagueVO;
import kr.or.ddit.proj.msg.dao.MessageDAO;
import kr.or.ddit.proj.msg.vo.MessageReceiverVO;
import kr.or.ddit.proj.msg.vo.MessageVO;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

	@Inject
	MessageDAO messageDAO;
	
	@Override
	public List<MessageVO> retrieveMessageList(PagingVO<MessageVO> pagingVO) {
		// TODO Auto-generated method stub
		pagingVO.setTotalRecord(messageDAO.selectTotalRecord(pagingVO));
		List<MessageVO> dataList = messageDAO.messageList(pagingVO);
		pagingVO.setDataList(dataList);
		return dataList;
	}

	@Override
	public List<MessageVO> retrieveMessageSenderList(PagingVO<MessageVO> pagingVO) {
		// TODO Auto-generated method stub
		pagingVO.setTotalRecord(messageDAO.selectTotalSendRecord(pagingVO));
		List<MessageVO> dataList = messageDAO.selectMessageSenderList(pagingVO);
		pagingVO.setDataList(dataList);
		return null;
	}

	
	@Override
	public List<MessageReceiverVO> retrieveMessageReceiverList(PagingVO<MessageReceiverVO> pagingVO) {
		// TODO Auto-generated method stub
		pagingVO.setTotalRecord(messageDAO.selectTotalReceiveRecord(pagingVO));
		List<MessageReceiverVO> dataList = messageDAO.selectMessageReceiverList(pagingVO);
		pagingVO.setDataList(dataList);
		return null;
	}

	@Override
	public void createMessage(MessageVO message) {
		// TODO Auto-generated method stub
		// 메시지 삽입
		messageDAO.insertMessage(message);
		// 메시지 수신자 삽입
		List<MessageReceiverVO> receiverList = message.getReceiverList();
		for(MessageReceiverVO messageReceiver : receiverList) {	
			// 해당 메시지 PK를 넘겨줌
			messageReceiver.setMsgNum(message.getMsgNum());
			messageDAO.insertMessageReceiver(messageReceiver);
		}
	}

	@Override
	public void createMessageReceiver(MessageReceiverVO messageReceiver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeMessage(MessageVO message) {
		// TODO Auto-generated method stub
		// 메시지 삭제가 아닌 삭제 처리하는 update 구문임
		messageDAO.deleteMessage(message);
	}

	@Override
	public void removeMessageReceiver(MessageReceiverVO messageReceiver) {
		// TODO Auto-generated method stub
		// 메시지 삭제가 아닌 삭제 처리하는 update 구문임
		messageDAO.deleteMessageReceiver(messageReceiver);
	}

	@Override
	public void updateReceiveMessageImportant(MessageReceiverVO messageRecevier) {
		// TODO Auto-generated method stub
		if(messageRecevier.isDelYn()) {
			// 중요메시지 제거시
			messageDAO.deleteReceiveMessageImportant(messageRecevier);
		}else {
			// 중요 메시지 추가시 
			messageDAO.updateReceiveMessageImportant(messageRecevier);
		}
	}

	@Override
	public void updateSendMessageImportant(MessageVO message) {
		// TODO Auto-generated method stub
		if(message.isDelYn()) {
			// 중요 메시지 제거시
			messageDAO.deleteSendMessageImportant(message);
		}else {
			// 중요 메시지 추가시
			messageDAO.updateSendMessageImportant(message);
		}
	}

	@Override
	public String retrieveMember(ColleagueVO coll) {
		// TODO Auto-generated method stub
		return messageDAO.selectMember(coll);
	}

	@Override
	public List<MessageVO> retrieveMessageReceiveList(ColleagueVO colleagueVO) {
		// TODO Auto-generated method stub
		return messageDAO.selectMsgSenderList(colleagueVO);
	}

	@Override
	public List<MessageReceiverVO> retrieveMessageSenderList(ColleagueVO colleagueVO) {
		// TODO Auto-generated method stub
		return messageDAO.selectMsgReceiveList(colleagueVO);
	}

	@Override
	public MessageReceiverVO retrieveMessageReceiveDetail(MessageReceiverVO receiver) {
		// TODO Auto-generated method stub
		return messageDAO.selectMessageReceiveDetail(receiver);
	}

	@Override
	public MessageVO retrieveMessageSendDetail(MessageVO message) {
		// TODO Auto-generated method stub
		return messageDAO.selectMessageSendDetail(message);
	}

}
