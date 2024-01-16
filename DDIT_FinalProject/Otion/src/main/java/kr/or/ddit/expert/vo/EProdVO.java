package kr.or.ddit.expert.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.sun.istack.internal.NotNull;

import kr.or.ddit.common.validate.DeleteGroup;
import kr.or.ddit.common.validate.InsertGroup;
import kr.or.ddit.common.validate.UpdateGroup;
import lombok.Data;
import lombok.NoArgsConstructor;

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

@Data
@NoArgsConstructor
public class EProdVO implements Serializable{
	
	private int rnum; //번호조회
	private String eprodNum;
	private String expertId;
	@NotEmpty(groups= {InsertGroup.class, UpdateGroup.class}, message="상품명은 필수 입력값입니다.")
	private String eprodName;
	@NotBlank(groups= {InsertGroup.class, UpdateGroup.class}, message="하나 이상의 태그를 선택해야합니다.")
	private String eprodTag;
	@NotBlank(groups= {InsertGroup.class, UpdateGroup.class}, message="상품가격은 필수 입력값입니다.")
	private String eprodPrice;
	private String eprodDate;
	private int accumNum;
	private String eprodLangCode;
	private String eprodSummary;
	private String eprodDel;
	private String profileImg;
	
	private String[] eprodTagArr;
//	private List<ExpertVO> expertList;
	
}
