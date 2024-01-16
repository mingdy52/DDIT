package kr.or.ddit.proj.workmark.service;

import java.util.List;

import kr.or.ddit.proj.workmark.vo.WorkMarkVO;

public interface WorkMarkService {
	
	public void createWorkMark(WorkMarkVO markVO);
	
	public void removeWorkMark(WorkMarkVO markVO);

	public WorkMarkVO retrieveWorkMark(WorkMarkVO markVO);

	public List<WorkMarkVO> retrieveWorkMarkList(WorkMarkVO markVO);
}
