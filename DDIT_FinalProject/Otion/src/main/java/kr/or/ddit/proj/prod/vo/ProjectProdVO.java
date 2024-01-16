package kr.or.ddit.proj.prod.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import kr.or.ddit.common.validate.InsertGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class ProjectProdVO {
	@NotBlank(groups = InsertGroup.class)
	private String cprodNum;
	private String cprodName;
	private Integer cprodPrice;
	private Integer cprodPeopleNum;
}
