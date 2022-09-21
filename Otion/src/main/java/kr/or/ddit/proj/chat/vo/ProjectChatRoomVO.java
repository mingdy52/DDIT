package kr.or.ddit.proj.chat.vo;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import kr.or.ddit.proj.colleague.vo.ColleagueVO;
import lombok.Data;

@Data
@EqualsAndHashCode
@ToString
public class ProjectChatRoomVO {
	private String crContent;
	@NotBlank
	private String crDate;
	@NotNull
	private Integer crContentNum;
	private String attatchNum;
	@NotBlank
	private String memId;
	@NotBlank
	private String crNum;
	
	private ColleagueVO colleague;
}
