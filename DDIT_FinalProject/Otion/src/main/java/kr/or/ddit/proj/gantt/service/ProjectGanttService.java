package kr.or.ddit.proj.gantt.service;

import java.util.List;

import kr.or.ddit.proj.colleague.vo.ColleagueVO;
import kr.or.ddit.proj.work.vo.WorkVO;

public interface ProjectGanttService {
	
	public List<WorkVO> retrieveGanttList(WorkVO work);

	public WorkVO retrieveGanttView(WorkVO work);
}
