package kr.or.ddit.proj.workmark.service;

import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.proj.workmark.dao.WorkMarkDAO;
import kr.or.ddit.proj.workmark.vo.WorkMarkVO;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class WorkMarkServiceImpl implements WorkMarkService {

	@Inject
	WorkMarkDAO markDAO;
	
	@Override
	public void createWorkMark(WorkMarkVO markVO) {
		// TODO Auto-generated method stub
		markDAO.insertWorkMark(markVO);
	}

	@Override
	public void removeWorkMark(WorkMarkVO markVO) {
		// TODO Auto-generated method stub
		markDAO.deleteWorkMark(markVO);
	}

	@Override
	public WorkMarkVO retrieveWorkMark(WorkMarkVO markVO) {
		// TODO Auto-generated method stub
		return markDAO.selectWorkMark(markVO);
	}

	@Override
	public List<WorkMarkVO> retrieveWorkMarkList(WorkMarkVO markVO) {
		// TODO Auto-generated method stub
		return markDAO.selectWorkMarkList(markVO);
	}

}
