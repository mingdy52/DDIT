package kr.or.ddit.proj.git.vo;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class GitVO {

	@NotBlank
	private String workStorageNum;
	@NotBlank
	private String pjId;
	@NotBlank
	private String workStoragePath;
}
