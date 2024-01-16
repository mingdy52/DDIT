package kr.or.ddit.common.vo;

import lombok.Data;

@Data
public class WorkRefundVO {
	private int rnum;
	private String wcomNm;
	private String cprodNum;
	private String cprodName;
	
	private String wpayNum;
	private String workRefCode;
	private String workRefDate;
	
	private String wpayAmount; //결제금액->환불금액
	private String comCodeNm; //결제수단 -> 환불수단
	private String pjCreatorId; //프로젝트 생성자 
}
