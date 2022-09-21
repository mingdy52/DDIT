package kr.or.ddit.notice.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.ibatis.annotations.Update;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.common.validate.DeleteGroup;
import kr.or.ddit.common.validate.InsertGroup;
import kr.or.ddit.common.validate.UpdateGroup;
import kr.or.ddit.common.vo.AttatchVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 
 * @author 서효림
 * @since 2022. 8. 22.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 22.       서효림          최초작성
 * 2022. 8. 29.		 서효림		1차 수정
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */

@Data
@EqualsAndHashCode(of="notiNum")
@ToString(exclude= {"notiContent"})
public class NoticeVO implements Serializable{
	
	private int rnum;
	@NotNull(groups= {UpdateGroup.class, DeleteGroup.class})
	private String notiNum;
	@NotBlank
	private String writerId;
	private String notiRank;
	@NotBlank(groups= {UpdateGroup.class, InsertGroup.class}, message="제목을 입력하세요.")
	@Size(max=30)
	private String notiTitle;
	@NotBlank(groups={UpdateGroup.class, InsertGroup.class}, message="내용을 입력하세요.")
	private String notiContent;
	private String notiClassCode;
	private Integer viewNum;
	private String notiDate;
	private String attatchNum;
	private String pjId;
	
	private MultipartFile[] noticeFiles;
	public void setNoticeFiles(MultipartFile[] noticeFiles) {
		if(noticeFiles == null || noticeFiles.length == 0) return;
		this.noticeFiles = noticeFiles;
		this.attatchList = new ArrayList<>();
		for(MultipartFile single : noticeFiles) {
			if(single.isEmpty()) continue;
			attatchList.add(new AttatchVO(single));
		}
	}
	
	private transient List<AttatchVO> attatchList;
	
	private int startAttno;
	
	private transient List<AttatchVO> replyList;
	
	private String[] delAttNos;
	
	private List<String> notiDelNum;
			
}
