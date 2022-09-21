package kr.or.ddit.proj.gantt.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.proj.colleague.vo.ColleagueVO;
import kr.or.ddit.proj.gantt.dao.ProjectGanttDAO;
import kr.or.ddit.proj.work.vo.WorkVO;

@Service
@Transactional
public class ProjectGanttServiceImpl implements ProjectGanttService {

	@Inject
	ProjectGanttDAO projectGanttDAO;
	
	@Override
	public List<WorkVO> retrieveGanttList(WorkVO work) {
		// TODO Auto-generated method stub
		// 해당 프로젝트에 대한 부모값이 없는 workVO를 가져옴
		List<WorkVO> workList = projectGanttDAO.selectGanttList(work);
		for(int i=0; i<workList.size(); i++) {
			List<WorkVO> childList = projectGanttDAO.selectChildGanttList(workList.get(i));
			int duration = 0;
			if(childList.size() > 0) {
				for(WorkVO child  : childList) {
					if(child.getWorkPriority().equals("완료")) {
						duration++;
					}
				}
				workList.get(i).setDuration(duration/childList.size() * 100);				
				workList.get(i).setChildWork(childList);
			}else {
				if(workList.get(i).getWorkPriority().equals("완료")) {
					workList.get(i).setDuration(100);
				}else {
					workList.get(i).setDuration(0);
				}
			}
		}
		return workList;
	}

	@Override
	public WorkVO retrieveGanttView(WorkVO work) {
		// TODO Auto-generated method stub
		WorkVO workView =projectGanttDAO.selectGanttView(work);
		List<WorkVO> childList = projectGanttDAO.selectChildGanttList(workView);
		workView.setChildWork(childList);
		if(workView.getParentWorkNum() != null) {
			// 해당 업무의 부모 객체를 가져옴
			WorkVO parent = projectGanttDAO.selctParentGantt(workView);
			workView.setParentWork(parent);
		}
		
		
		return workView; 
	}

}
