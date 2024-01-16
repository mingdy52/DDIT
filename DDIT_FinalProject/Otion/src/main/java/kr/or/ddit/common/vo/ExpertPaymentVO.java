package kr.or.ddit.common.vo;

import lombok.Data;

/**
 * 
 * @author 이아인
 * @since 2022. 8. 11.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 11.      이아인       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Data
public class ExpertPaymentVO {
	private int rnum;
	private String epayMethodCode;
	private String epayNum;
	private String myEprod;
	private String amount;
	private String epayDate;
	
	private String eprodName;
	private String eprodNum;
	private String ecomNm;
	private String buyerId;
	private String epayYn;
	
	private int year;
	private int month;
	
	private String expertId;
	
	private String accumRevenue;
}
