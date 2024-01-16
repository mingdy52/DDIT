package kr.or.ddit.proj.post.vo;

import java.util.List;

import javax.validation.constraints.NotBlank;

import kr.or.ddit.common.validate.DeleteGroup;
import kr.or.ddit.common.validate.UpdateGroup;
import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.proj.calendar.vo.CalendarVO;
import kr.or.ddit.proj.colleague.vo.ColleagueVO;
import kr.or.ddit.proj.msg.vo.MessageReceiverVO;
import kr.or.ddit.proj.msg.vo.MessageVO;
import kr.or.ddit.proj.reply.vo.WorkReplyVO;
import kr.or.ddit.proj.work.vo.WorkVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class MyPostVO {
	
	// 팀원 정보에 대한 정보
	private ColleagueVO colleagueVO;
	// 사용자에 대한 인적정보
	private List<CalendarVO> calendarList;
	// 송신 메시지 리스트
	private List<MessageVO> messageList;
	// 수신 메시지 리스트
	private List<MessageReceiverVO> messageReceiveList;
	// 댓글 리스트
	private List<WorkReplyVO> replyList;
	// 자신의 업무 리스트
	private List<WorkVO> workList;
}
