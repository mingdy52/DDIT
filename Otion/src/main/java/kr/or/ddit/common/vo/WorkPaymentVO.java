package kr.or.ddit.common.vo;

import lombok.Data;

@Data
public class WorkPaymentVO {
	private int rnum;
	private String pjCreatorId;
	private String pjName;
	private String cprodName;
	
	private String wpayNum;
	private String cprodNum;
	private String pjId;
	private String wpayDate;
	private String wpayAmount;
	//private String wpay; 
	private String wpayEnd;
	private String wpayMethodCode;
	private String wcomNm;
	private String wpayYn;
}
