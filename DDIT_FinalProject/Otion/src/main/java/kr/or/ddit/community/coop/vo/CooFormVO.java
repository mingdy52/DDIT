package kr.or.ddit.community.coop.vo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.common.validate.InsertGroup;
import kr.or.ddit.common.validate.UpdateGroup;
import kr.or.ddit.common.vo.AttatchVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of= {"cooFormNum"})
public class CooFormVO {
	private String cooFormNum;
	@NotBlank(groups= {InsertGroup.class})
	private String applicantId;
	private String cooNum;
	@NotBlank(groups={UpdateGroup.class, InsertGroup.class}, message="간단한 자기소개를 작성해 주세요.")
	private String cooFormContent;
	private MultipartFile originFile;
	@NotBlank(groups={UpdateGroup.class, InsertGroup.class}, message="이력서를 첨부해 주세요.")
	private String cooAssumeAttatch;
	private String cooFromDate;
	private String cooApprCode;
	@NotBlank
	private String cooTitle;
	@NotBlank
	private String writerId;
	private String memName;
	
	private AttatchVO attatch;
	
	private transient List<AttatchVO> attatchList;
	
	private MultipartFile[] cooFiles;
	public void setCooFiles(MultipartFile[] cooFiles) {
		if(cooFiles == null || cooFiles.length == 0) return;
		this.cooFiles = cooFiles;
		this.attatchList = new ArrayList<>();
		for(MultipartFile single : cooFiles) {
			if(single.isEmpty()) continue;
			attatchList.add(new AttatchVO(single));
		}
	}
}
