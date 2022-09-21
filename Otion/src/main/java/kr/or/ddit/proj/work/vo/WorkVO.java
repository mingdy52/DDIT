package kr.or.ddit.proj.work.vo;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.groups.Default;

import kr.or.ddit.common.validate.DeleteGroup;
import kr.or.ddit.common.validate.InsertGroup;
import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.proj.colleague.vo.ColleagueVO;
import kr.or.ddit.proj.issue.vo.IssueVO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor //도메인 레이어에 기본 생성자가 없으면 프레임워크를 사용할 수 없음.
@AllArgsConstructor(access=AccessLevel.PRIVATE)
@Builder
public class WorkVO {

	private String rNum;
	private String parentWorkNum;
	private String workNum;
	private String pjId;
	private String workReq;
	@NotBlank(groups= {Default.class, InsertGroup.class})
	private String workTitle;
	@NotBlank(groups= {Default.class, InsertGroup.class})
	private String workContent;
	private String workStatusCode;
	private String memId;
	@NotBlank(groups= {Default.class, InsertGroup.class})
	private String workStart;
	@NotBlank(groups= {Default.class, InsertGroup.class})
	private String workEnd;
	@NotBlank
	private String workPriority;
	
	private int duration;
	
	private WorkVO parentWork;
	private List<WorkVO> childWork;
	
	private List<String> memberList;
	private List<ReplyVO> replyList;
	private List<IssueVO> issueList;
	
	private List<Integer> workList;
	
	
}
