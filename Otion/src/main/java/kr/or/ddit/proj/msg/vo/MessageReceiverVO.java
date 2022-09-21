package kr.or.ddit.proj.msg.vo;

import javax.validation.constraints.NotBlank;

import kr.or.ddit.common.vo.SimpleSearchCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of = {"receiverNum"})
@ToString
public class MessageReceiverVO {
	private String rNum;
	@NotBlank
	private String receiverNum;
	@NotBlank
	private String msgNum;
	private String readDate;
	private String msgRecDel;
	@NotBlank
	private String pjId;
	private String msgRecIm;
	
	private boolean delYn;
	
	private String sender;
	private String receiver;
	
	private SimpleSearchCondition simpleCondition;
	
	private MessageVO message;
}
