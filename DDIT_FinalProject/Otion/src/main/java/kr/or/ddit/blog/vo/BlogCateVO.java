package kr.or.ddit.blog.vo;

import java.util.List;

import lombok.Data;
import lombok.ToString;
/**
 * 
 * @author 이아인
 * @since 2022. 8. 20.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 20.      이아인       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Data
@ToString
public class BlogCateVO {
	private Integer rnum;
	private String cateDelYn;
	private String cateNum;
	private String blogId;
	private String cateNm;
	private String cateDate;
	
	private String blogerId;
	private List<String>cateNmList;
}
