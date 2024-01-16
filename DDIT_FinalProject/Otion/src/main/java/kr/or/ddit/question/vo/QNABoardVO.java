package kr.or.ddit.question.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import kr.or.ddit.common.validate.DeleteGroup;
import kr.or.ddit.common.validate.InsertGroup;
import kr.or.ddit.common.validate.UpdateGroup;
import kr.or.ddit.common.vo.AttatchVO;
import kr.or.ddit.notice.vo.BoardReplyVO;
import lombok.Data;
/**
 * 
 * @author 작성자명
 * @since 2022. 8. 18.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 18.      이아인       최초작성
 * 2022. 8. 24		 서효림	 1차수정
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */

@Data
public class QNABoardVO implements Serializable {

	private Integer rnum;
	private String qnaPublicYn;
	private String answerTitle;
	private String answerContent;
	private Integer viewNum;
	private String attatchNum;
	private String answerId;
	private String delYn;
	private String qnaDate;
	
	@NotNull(groups= {UpdateGroup.class, DeleteGroup.class})
	private String qnaNum;
	@NotBlank(groups={UpdateGroup.class,InsertGroup.class}, message="이름을 입력하세요.")
	@Size(max=8, message="8자 이내로 입력해주세요.")
	private String writerId;
	@NotBlank(groups={UpdateGroup.class, InsertGroup.class}, message="제목을 입력하세요.")
	@Size(max=20, message="20자 이내로 입력해주세요.")
	private String qnaTitle;
	@NotBlank(groups={UpdateGroup.class,InsertGroup.class}, message="내용을 입력하세요.")
	private String qnaContent;
	@NotBlank(groups={InsertGroup.class}, message="비밀번호를 입력하세요.")
	@Size(min=4, max=12)
	private String qnaPass;
	
	private String answerYn;
	private String answerDate;
	
	private List<String> qnaDelNum;
	
	private transient List<BoardReplyVO> replyList;
	
	private String[] delAttNos;	
	
	private transient List<AttatchVO> attatchList;
	
}
