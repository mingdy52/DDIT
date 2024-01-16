package kr.or.ddit.blog.vo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.poi.ss.formula.functions.T;
import org.json.simple.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.common.validate.DeleteGroup;
import kr.or.ddit.common.validate.UpdateGroup;
import kr.or.ddit.common.vo.AttatchVO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(of="postNum")
@ToString(exclude= {"postContent"})
public class MyBlogPostVO {  
	
	@NotNull(groups= {UpdateGroup.class, DeleteGroup.class})
	private Integer rnum;
	private String postNum;
	@NotBlank(message="카테고리는 필수 선택값입니다.")
	private String postCateNum;
	private String blogerId;
	@NotBlank(message="제목은 필수 입력값입니다.")
	private String postTitle;
	@NotBlank
	private String postContent;
	private int postView;
	private int postHeart;
	private String postDate;
	private String postBlindYn;
	private String postPublicYn;
	private String postDelYn;
	private String postTag;
	private String attatchNum;
	private String thumnail;
	private String cateNum;
	private String cateNm;
	
	private MultipartFile[] postFiles;
	public void setPostFiles(MultipartFile[] postFiles) {
		if(postFiles==null || postFiles.length == 0) return;
		this.postFiles = postFiles;
	 	this.attatchList  = new ArrayList<>();
	 	for(MultipartFile single  : postFiles) {
	 		if(single.isEmpty()) continue;
 	 		attatchList.add( new AttatchVO(single) );
	 	}
	}
	
	private transient List<AttatchVO> attatchList;
	
	private List<String> postNumList;
	private List<AttatchVO> attatchNums;
	
	private Object jsonContents;
}
