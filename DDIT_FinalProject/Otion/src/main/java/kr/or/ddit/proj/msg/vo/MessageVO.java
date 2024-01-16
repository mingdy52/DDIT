package kr.or.ddit.proj.msg.vo;

import java.util.List;

import javax.validation.constraints.NotBlank;

import kr.or.ddit.common.validate.DeleteGroup;
import kr.or.ddit.common.validate.InsertGroup;
import kr.or.ddit.common.vo.SimpleSearchCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of = {"msgNum"})
@ToString
public class MessageVO {
	private String rNum;
	@NotBlank(groups= {DeleteGroup.class})
	private String msgNum;
	private String pjId;
	@NotBlank(groups= {InsertGroup.class})
	private String msgTitle;
	@NotBlank(groups= {InsertGroup.class})
	private String msgContent;
	private String msgDate;
	private String msgDelYn;
	private String msgWriterId;
	private String msgCode;
	private String msgImportant;
	
	private boolean delYn;
	
	private String sendName;
	private String receiveName;
	
	private String[] removeMsgNum;
	
	private SimpleSearchCondition simpleCondition;
		
	List<MessageReceiverVO> receiverList;
}
