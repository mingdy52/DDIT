package kr.or.ddit.blog.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import kr.or.ddit.common.validate.DeleteGroup;
import kr.or.ddit.common.validate.UpdateGroup;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TodoVO {
	
	@NotNull(groups= {UpdateGroup.class, DeleteGroup.class})
	private String checkNum;
	private String blogId;
	@NotBlank
	private String checkContent;
	private String checkYn;
	private String checkDate;
	
	
}
