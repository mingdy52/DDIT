package kr.or.ddit.proj.main.vo;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.ibatis.annotations.Insert;

import kr.or.ddit.common.validate.InsertGroup;
import kr.or.ddit.common.validate.UpdateGroup;
import kr.or.ddit.common.vo.AttatchVO;
import kr.or.ddit.community.coop.vo.CooBoardVO;
import kr.or.ddit.community.coop.vo.CooFormVO;
import kr.or.ddit.proj.colleague.vo.ColleagueVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of={"pjId"})
public class ProjectVO {
	@NotBlank(groups = {UpdateGroup.class})
	private String pjId;
	@NotBlank
	private String pjCreatorId;
	@NotBlank(groups = {InsertGroup.class, UpdateGroup.class})
	private String pjName;
	@NotNull(groups = {InsertGroup.class, UpdateGroup.class})
	private Integer pjPnum;
	@NotBlank(groups = {InsertGroup.class, UpdateGroup.class})
	private String pjStart;
	@NotBlank(groups = {InsertGroup.class, UpdateGroup.class})
	private String pjEnd;
	@NotBlank(groups = {InsertGroup.class, UpdateGroup.class})
	private String pjObjTitle;
	@NotBlank(groups = {InsertGroup.class, UpdateGroup.class})
	private String pjObjSummary;
	private String pjCreateDate;
	private String pjAdminId;
	private String pjAdminUpdate;
	private String pjStatusCode;
	
	// 협업 게시글을 받기 위한 객체
	private CooBoardVO cooBoard;
	
	// 팀원 정보 받아오기
	private List<ColleagueVO> colleagueList;
	
	// 협업 신청 받아오기(해당 프로젝트에 대한 기록)
	private List<CooFormVO> cooFormList;
	
}
