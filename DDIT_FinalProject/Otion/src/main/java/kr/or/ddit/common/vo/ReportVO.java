package kr.or.ddit.common.vo;

import javax.validation.constraints.NotBlank;

import kr.or.ddit.common.validate.InsertGroup;
import lombok.Data;

@Data
public class ReportVO {
	private int rnum;
	private String repNum;
	private String reporterId;
	private String targetId;
	private String repDate;
	@NotBlank(groups= {InsertGroup.class}, message="신고 내용을 입력해주세요.")
	private String repContent;
}

