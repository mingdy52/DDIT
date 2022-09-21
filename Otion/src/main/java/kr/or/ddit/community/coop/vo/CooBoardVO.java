package kr.or.ddit.community.coop.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.common.validate.InsertGroup;
import kr.or.ddit.common.validate.UpdateGroup;
import kr.or.ddit.common.vo.AttatchVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
/**
 * 
 * @author 고정현
 * @since 2022. 8. 17.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 17.       고정현          최초작성
 * 2022. 8. 31.		  서효림		1차 수정
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Data
@EqualsAndHashCode(of="cooNum")
@ToString(exclude= {"cooContent"})
public class CooBoardVO implements Serializable {
	private int rnum;
//	@NotBlank
	private String cooNum;
	@NotBlank
	private String writerId;
	@NotBlank(groups= {UpdateGroup.class, InsertGroup.class}, message="제목을 입력하세요.")
	@Size(max=30)
	private String cooTitle;
	@NotBlank(groups={UpdateGroup.class, InsertGroup.class}, message="내용을 입력하세요.")
	private String cooContent;
	
	@NotNull(message="최소 2명, 최대 20명까지 가능합니다.")
	@Range(min = 2, max = 20, message="최소 2명, 최대 20명 까지 가능합니다.") 
	private Integer cooPeopleNum;
	
	@NotBlank
	private String cooStart;
	@NotBlank
	private String cooEnd;
	
//	@NotBlank
	private String cooDoneYn;
	private Integer repNum;
	private Integer viewNum;
//	@NotBlank
	private String blindYn;
	private String attatchNum;
	private String delYn;
	@NotBlank(groups={UpdateGroup.class, InsertGroup.class}, message="프로젝트를 선택하세요.")
	private String pjId;
	private String pjName;
	
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
	
	private String cooDate;
	
	private List<String> cooDelNum;

	public String[] delAttNos;

	private transient List<AttatchVO> attatchList;
	
	private int startAttNo;
}
