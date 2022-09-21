package kr.or.ddit.proj.projFolder.vo;

import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of= {"wfolderNum"})
@ToString
public class ProjectFolderVO {
	@NotBlank
	private String wfolderNum;
	@NotBlank
	private String colNum;
	@NotBlank
	private String wfolderName;
	private String parentWfolderNum;
	private String wfolderDel;
	
	private List<ProjectFileVO> fileList;
}
