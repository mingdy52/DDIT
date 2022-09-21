package kr.or.ddit.proj.issue.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.common.vo.AttatchVO;
import kr.or.ddit.proj.colleague.vo.ColleagueVO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@ToString
@NoArgsConstructor //도메인 레이어에 기본 생성자가 없으면 프레임워크를 사용할 수 없음.
@AllArgsConstructor(access=AccessLevel.PRIVATE)
@Builder
public class IssueReplyVO {

	private String issueReplyNum;
	private String issueNum;
	private String solveWay;
	private String solverNum;
	private String solveDate;
	private String workReq;
	private String pjId;
	private MultipartFile replyAttatch;
	private AttatchVO attach;
	private String attatchNum;
	
	private String cooProfile;
}
