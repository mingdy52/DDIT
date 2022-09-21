package kr.or.ddit.blog.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import kr.or.ddit.common.validate.DeleteGroup;
import kr.or.ddit.common.validate.InsertGroup;
import kr.or.ddit.common.validate.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@EqualsAndHashCode(of= {"calNum"})
public class MyCalendarVO {
	@NotBlank(groups= {UpdateGroup.class, DeleteGroup.class})
	private String calNum;
	private String blogId;
	@NotBlank(groups= {InsertGroup.class})
	@Size(min=1,max=50)
	private String calTitle;
	@NotBlank(groups= {InsertGroup.class})
	private String calStart;
	@NotBlank(groups= {InsertGroup.class})
	private String calEnd;
	@NotBlank(groups= {InsertGroup.class})
	private String calContent;
	@NotBlank(groups= {InsertGroup.class})
	private String calColor;
	
	private String blogerId;

}
