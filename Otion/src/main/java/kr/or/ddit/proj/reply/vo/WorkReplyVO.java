package kr.or.ddit.proj.reply.vo;

import javax.validation.constraints.NotBlank;

import kr.or.ddit.common.validate.DeleteGroup;
import kr.or.ddit.common.validate.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of= {"woReplyNum"})
@ToString
public class WorkReplyVO {
	@NotBlank(groups= {UpdateGroup.class, DeleteGroup.class})
	private String woReplyNum;
	private String writerNum;
	@NotBlank
	private String workNum;
	@NotBlank
	private String woReplyContent;
	private String woReplyDate;
	private String parentWoReplyNum;
}
