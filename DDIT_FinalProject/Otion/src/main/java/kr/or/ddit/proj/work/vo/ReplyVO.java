package kr.or.ddit.proj.work.vo;

import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.common.AttatchFileDownloadController;
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
public class ReplyVO {

	private String woReplyNum;
	private String writerNum;
	private String workNum;
	private String woReplyContent;
	private String workReq;
	private String pjId;
	private String memId;
	private String woReplyDate;
	private String parentWoReplyNum;
	
	private WorkVO work;
	private MultipartFile replyAttatch;
	private AttatchVO attatch;
	private String attatchNum;
	private ColleagueVO colleague;
}
