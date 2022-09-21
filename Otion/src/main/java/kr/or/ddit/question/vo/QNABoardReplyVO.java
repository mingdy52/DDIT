package kr.or.ddit.question.vo;

import java.util.List;

import lombok.Data;
import lombok.ToString;

/**
 * 
 * @author 서효림
 * @since 2022. 8. 24.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 24.       서효림          최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Data
@ToString
public class QNABoardReplyVO {
	private Integer rnum;
	private String delYn;
	private String boReplyNum;
	private String writerId;
	private String boardNum;
	private String boReplyContent;
	private String boReplyDate;
	private String parentBoReplyNum;
	
	private List<String> boReplyDelNum;
}
