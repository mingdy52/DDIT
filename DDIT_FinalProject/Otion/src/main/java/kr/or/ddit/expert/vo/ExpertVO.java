package kr.or.ddit.expert.vo;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.servlet.annotation.MultipartConfig;

import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.common.vo.MemberVO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@MultipartConfig
public class ExpertVO implements Serializable{
	private int rnum; //번호조회
	private String expertId;
//	private String profileImage;
	private String profileImg;
	private String presentation;
	private String assume;
	private int accumRep;
	private String exLink;
	private String exTag;
	
	
//	private MultipartFile profileImg;
	private MultipartFile profileImage;
	
	
	public void setProfileImage(MultipartFile profileImage) {
		if(profileImage!=null && !profileImage.isEmpty()&&profileImage.getContentType().startsWith("image/")) {
			this.profileImage = profileImage;
			this.profileImg = UUID.randomUUID().toString();//저장명 
		}
	}
	
	



	private List<EProdVO> eprodList;
	private List<MemberVO> memList;
	private String[] assumeArr;
	
}
