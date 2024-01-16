package kr.or.ddit.proj.prod.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.ToString;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@ToString
public class ProjectPaymentVO {
	@NotBlank
	private String wpayNum;
	@NotBlank
	private String cprodNum;
	@NotBlank
	private String pjId;
	@NotBlank
	private String wpayDate;
	@NotNull
	private Integer wpayAmount;
	@NotBlank
	private String wpayEnd;
	@NotBlank
	private String wpayMethodCode;
	@NotBlank
	private String wpayYn;
}
