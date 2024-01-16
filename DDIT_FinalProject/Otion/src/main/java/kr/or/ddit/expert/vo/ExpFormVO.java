package kr.or.ddit.expert.vo;
/**
 * 
 * @author 박채록
 * @since 2022. 8. 5.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 5.      박채록       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * 
 */

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ExpFormVO {
	private String rnum;
	
	//전문가신청VO
	private String exFormNum;
	private String applicantId;
	private String exFormContent;
	private String exFormAssume;
	private String exApprCode;
	
	private MultipartFile exFormFiles;
	private String comCodeNm;
	
	private String exRefuse;
	
	private String refuseReason;
	
//	private List<MemberVO> member;
	

}
