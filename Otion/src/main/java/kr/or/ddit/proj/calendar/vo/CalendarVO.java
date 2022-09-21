package kr.or.ddit.proj.calendar.vo;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import kr.or.ddit.common.validate.DeleteGroup;
import kr.or.ddit.common.validate.InsertGroup;
import kr.or.ddit.common.validate.UpdateGroup;
import kr.or.ddit.proj.work.vo.WorkVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of= {"workCalNum"})
@ToString
public class CalendarVO {
	private String rNum;
	@NotBlank(groups= {UpdateGroup.class, DeleteGroup.class})
	private String workCalNum;
	private String colNum;
	@NotBlank(groups= {InsertGroup.class})
	private String workCalTitle;
	@NotBlank(groups= {InsertGroup.class})
	private String workCalStart;
	@NotBlank(groups= {InsertGroup.class})
	private String workCalEnd;
	@NotBlank(groups= {InsertGroup.class})
	private String workCalContent;
	@NotBlank(groups= {InsertGroup.class})
	private String workCalClass;
	@NotBlank(groups= {InsertGroup.class})
	private String workCalColor;
	
	// 내 업무 리스트 받아오기
	private List<WorkVO> workList;
}
