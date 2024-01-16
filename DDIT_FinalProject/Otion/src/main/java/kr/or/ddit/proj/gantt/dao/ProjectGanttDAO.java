package kr.or.ddit.proj.gantt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.proj.colleague.vo.ColleagueVO;
import kr.or.ddit.proj.work.vo.WorkVO;

/**
 * @author 작성자명
 * @since 2022. 8. 29.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 29.      고정현       간트 리스트 및 하위 업무 리스트
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Mapper
public interface ProjectGanttDAO {

	public List<WorkVO> selectGanttList(WorkVO work);

	public List<WorkVO> selectChildGanttList(WorkVO parent);

	public WorkVO selectGanttView(WorkVO work);

	public WorkVO selctParentGantt(WorkVO workView);
}
