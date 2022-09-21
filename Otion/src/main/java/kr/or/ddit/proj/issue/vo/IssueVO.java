package kr.or.ddit.proj.issue.vo;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

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
public class IssueVO {
	private String rNum;
	private String issueNum;
	private String workNum;
	private String issueTitle;
	private String issueContent;
	private String issueDate;	
	private String issueReq;
	private String workReq;	
	private MultipartFile issueAttatch;
	private AttatchVO attatch;
	private String issueImg;
	private String filePath;
	private ColleagueVO colleague;
	
	private String cooProfile;

	
	public void setIssueAttatch(MultipartFile issueAttatch) {
		this.issueAttatch = issueAttatch;
		if(issueAttatch!=null && !issueAttatch.isEmpty()) {
			this.issueImg = UUID.randomUUID().toString();
		}
	}
	
	public void saveTo(File saveFolder) {
		if(issueAttatch!=null)
			try {
				issueAttatch.transferTo(new File(saveFolder, issueImg));
				filePath = new File(saveFolder, issueImg).getCanonicalPath();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
}
}
