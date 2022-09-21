package kr.or.ddit.proj.colleague.vo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.common.validate.UpdateGroup;
import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.common.vo.SimpleSearchCondition;
import kr.or.ddit.common.vo.WorkCalSearchVO;
import kr.or.ddit.common.vo.WorkSearchVO;
import lombok.Data;

// 팀원에 대한 VO
@Data
public class ColleagueVO {
	@NotBlank(groups = { UpdateGroup.class })
	private String colNum;
	@NotBlank
	private String pjId;
	private String cooFormNum;
	@NotBlank
	private String memId;
	private String colDate;
	private String colResignYn;
	private String projAuthCode;
	private String workRoleCode;
	
	private String memName;

	private MultipartFile cooImage;
	private String cooProfile;
	
	private String crName;

	public void setCooImage(MultipartFile cooImage) {
		this.cooImage = cooImage;
		if (cooImage != null && !cooImage.isEmpty()) {
			this.cooProfile = UUID.randomUUID().toString();
		}
	}

	public void saveTo(File saveFolder) {
		if (cooImage != null)
			try {
				File newfile = new File(saveFolder, cooProfile);
				cooImage.transferTo(newfile);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	private MemberVO member;

	private WorkCalSearchVO searchVO;

	private WorkSearchVO workSearchVO;
	// 내 게시글 전용
	private SimpleSearchCondition simpleCondition;
}
