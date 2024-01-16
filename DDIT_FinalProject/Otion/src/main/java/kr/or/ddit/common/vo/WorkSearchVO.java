package kr.or.ddit.common.vo;

import lombok.Data;

@Data
public class WorkSearchVO {
	private String workTitle;
	private String workStatusCode;
	private String workStart;
	private String workEnd;
	private String workPriority;
}
