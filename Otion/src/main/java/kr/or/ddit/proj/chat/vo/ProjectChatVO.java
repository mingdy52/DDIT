package kr.or.ddit.proj.chat.vo;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.proj.colleague.vo.ColleagueVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class ProjectChatVO {
	private String crNum;
	@NotBlank
	private String colNum;
	private String crCreateDate;
	@NotBlank
	private String crName;
	private String crUpdate;
	private MultipartFile crImage;
	
	private String crImg;
	
	private List<ColleagueVO> collList;
	
	public void setCrImage(MultipartFile crImage) {
		this.crImage = crImage;
		if(crImage!=null && !crImage.isEmpty()) {
			this.crImg = UUID.randomUUID().toString();
		}
	}
	
	public void saveTo(File saveFolder) {
		if(crImage!=null)
			try {
				crImage.transferTo(new File(saveFolder, crImg));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
}
	
	@NotBlank
	private String crColleague;

	private List<ProjectChatRoomVO> chatList;
}
