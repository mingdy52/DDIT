package kr.or.ddit.community.free.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.common.validate.InsertGroup;
import kr.or.ddit.common.validate.UpdateGroup;
import kr.or.ddit.common.vo.AttatchVO;
import kr.or.ddit.notice.vo.BoardReplyVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
/**
 * 
 * @author 이아인
 * @since 2022. 8. 17.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 17.       이아인          최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Data
@EqualsAndHashCode(of="freeNum")
@ToString(exclude= {"freeContent"})
public class FreeBoardVO implements Serializable {
	
	private int rnum;
//	@NotBlank(groups= {UpdateGroup.class, DeleteGroup.class})
	private String freeNum;
	@NotBlank
	private String writerId;
	@NotBlank(groups= {UpdateGroup.class, InsertGroup.class}, message="제목을 입력하세요.")
	@Size(max=30)
	private String freeTitle;
	@NotBlank(groups={UpdateGroup.class, InsertGroup.class}, message="내용을 입력하세요.")
	private String freeContent;
	private String freeDate;
	private Integer repNum;
	private Integer viewNum;
//	@NotBlank
	private String blindYn;
	private String attatchNum;
	private String delYn;
	
	private MultipartFile[] freeFiles;
	public void setFreeFiles(MultipartFile[] freeFiles) {
		if(freeFiles == null || freeFiles.length == 0) return;
		this.freeFiles = freeFiles;
		this.attatchList = new ArrayList<>();
		for(MultipartFile single : freeFiles) {
			if(single.isEmpty()) continue;
			attatchList.add(new AttatchVO(single));
		}
	}
	
	private transient List<AttatchVO> attatchList;
	
	private int startAttNo;
	
	private transient List<BoardReplyVO> replyList;
	
	private String[] delAttNos;
	
	private List<String> freeDelNum;
	
}
