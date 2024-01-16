package kr.or.ddit.proj.filerecord.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import kr.or.ddit.common.vo.AttatchVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class FileRecordVO {
	private String rNum;
	@NotBlank
	private String downNum;
	@NotBlank
	private String pjId;
	private String downDate;
	@NotBlank
	private String attatchNum;
	@NotNull
	private Integer attatchOrder;
	private String downloaderId;
	
	private String workstorageNum;
	
	private AttatchVO attatch;
}
