package kr.or.ddit.notice.vo;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import kr.or.ddit.common.validate.InsertGroup;
import kr.or.ddit.common.validate.UpdateGroup;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * 
 * @author 이아인
 * @since 2022. 9. 2.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 9. 2.      이아인       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Setter
@Getter
@ToString
public class BoardReplyVO {
    private Integer rnum;
	private String delYn;
	@NotBlank(groups= {UpdateGroup.class})
	private String boReplyNum;
	private String writerId;
	@NotBlank(groups= {InsertGroup.class})
	private String boardNum;
	@NotBlank(groups= {InsertGroup.class,UpdateGroup.class})
	@Size(min = 1, max = 150)
	private String boReplyContent;
	private String boReplyDate;
	private String parentBoReplyNum;
	
	private List<String> boReplyDelNum;
	//알림,게시판 글쓴이 Id
	private String boardWriter;
	//알림,댓글 글쓴이Id
	private String ownerWriter;
}
