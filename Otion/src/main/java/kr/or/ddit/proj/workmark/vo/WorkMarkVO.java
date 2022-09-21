package kr.or.ddit.proj.workmark.vo;

import kr.or.ddit.proj.work.vo.WorkVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class WorkMarkVO {
	private String wmNum;
	private String colNum;
	private String workNum;
	
	private WorkVO work;
}
