package kr.or.ddit.common.vo;

import lombok.Data;

@Data
public class ExpertRefundVO {
	private int rnum;
	private String ecomNm;
	private String eprodNum;
	private String eprodName;
	
	private String refundNum;
	private String refundReasonCode;
	private String refundDate;
	private String epayNum;
	
	private String comCodeNm; //결제수단->환불수단
	private String eprodPrice;
	private String buyerId;

}

