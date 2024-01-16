package kr.or.ddit.expert.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author 박채록
 * @since 2022. 8. 17.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 17.      박채록       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 *      </pre>
 */

@Data
@NoArgsConstructor
public class MyExpertVO implements Serializable {
	private int rnum; //번호조회
	@NotBlank
	private String myEprod;
	private String buyerId;

	private String eprodNum;
	@NotBlank
	private String progressCode;
	private String eprodName;
	private String eprodSummary;
	private String eprodPrice;
	private String comCodeNm;
	private String epayDate;
	private String reviewYn;
	
	private String expertId;
	private String refundReasonCode;
}
