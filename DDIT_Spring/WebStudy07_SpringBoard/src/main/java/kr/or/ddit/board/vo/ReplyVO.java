package kr.or.ddit.board.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ReplyVO {
	@NotNull
	private Integer repNo;
	@NotNull
	private Integer boNo;
	private String repContent;
	@NotBlank
	private String repWriter;
	private String repMail;
	private String repPass;
	private String repDate;
	private Integer repParent;
	
}
