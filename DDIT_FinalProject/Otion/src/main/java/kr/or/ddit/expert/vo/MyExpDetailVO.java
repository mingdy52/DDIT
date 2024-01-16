package kr.or.ddit.expert.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MyExpDetailVO {
	
	private String answerNum;
	private int myexpOrder;
	private String myEprod;
	private String myexpWriter;
	private String myexpContent;
	private String myexpDate;
	
	private String comCodeNm;
}
