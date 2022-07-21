package kr.or.ddit.board.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.validate.DeleteGroup;
import kr.or.ddit.validate.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of="boNo")
@ToString(exclude= {"boPass", "boContent", "attatchList", "replyList", "boFiles"})
public class BoardVO implements Serializable {
	private int rnum;
	
	@NotNull(groups= {UpdateGroup.class, DeleteGroup.class})
	private Integer boNo;
	@NotBlank
	private String boTitle;
	@NotBlank
	private String boWriter;
	@NotBlank
	private String boIp;
	private String boMail;
	@NotBlank(groups= {Default.class, DeleteGroup.class})
	private String boPass;
	private String boContent;
	private String boDate;
	private Integer boRep;
	private Integer boHit;
	private Integer boRec;
	private Integer boParent;
	
	private MultipartFile[] boFiles;
	public void setBoFiles(MultipartFile[] boFiles) {
		if(boFiles==null || boFiles.length == 0) return;
		this.boFiles = boFiles;
	 	this.attatchList  = new ArrayList<>();
	 	for(MultipartFile single  : boFiles) {
	 		if(single.isEmpty()) continue;
 	 		attatchList.add( new AttatchVO(single) );
	 	}
	}
	
	private transient List<AttatchVO> attatchList;
	private int startAttNo;
	
	private transient List<ReplyVO> replyList;
	
	private int[] delAttNos;
}
