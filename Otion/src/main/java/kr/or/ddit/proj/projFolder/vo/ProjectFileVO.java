package kr.or.ddit.proj.projFolder.vo;

import java.util.List;

import javax.validation.constraints.NotBlank;

import kr.or.ddit.common.validate.DeleteGroup;
import kr.or.ddit.common.validate.UpdateGroup;
import kr.or.ddit.common.vo.AttatchVO;
import kr.or.ddit.common.vo.SimpleSearchCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of= {"wfolderFileNum"})
@ToString
public class ProjectFileVO {
	@NotBlank(groups= {UpdateGroup.class, DeleteGroup.class})
	private String wfolderFileNum;
	@NotBlank
	private String wfolderNum;
	@NotBlank
	private String attatchNum;
	
	private List<AttatchVO> attatchVO;
	
	private SimpleSearchCondition searchCondition;
}
