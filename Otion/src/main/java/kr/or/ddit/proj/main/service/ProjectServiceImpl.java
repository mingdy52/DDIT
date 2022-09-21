package kr.or.ddit.proj.main.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.proj.colleague.dao.ColleagueDAO;
import kr.or.ddit.proj.main.dao.ProjectDAO;
import kr.or.ddit.proj.main.vo.ProjectVO;

/**
 * @author 작성자명
 * @since 2022. 9. 1.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 9. 1.      고정현       접근 제한 막기
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Service
@Transactional
public class ProjectServiceImpl implements ProjectService{

	@Inject
	ProjectDAO projectDAO;
			
	@Override
	public List<ProjectVO> retrieveList(String memId) {
		// TODO Auto-generated method stub
		// 해당 프로젝트 리스트를 받아옴
		List<ProjectVO> projectList = projectDAO.selectProjectList(memId);
		return projectList;
	}
	
	@Override
	public ProjectVO retrieveCooFormList(ProjectVO project) {
		// TODO Auto-generated method stub
		ProjectVO CooFormProject = projectDAO.selectCooFormList(project);
		
		return CooFormProject;
	}

	@Override
	public void createProject(ProjectVO project) {
		// TODO Auto-generated method stub
		int cnt = projectDAO.insertProject(project);
		if(cnt == 0) {
			throw new RuntimeException();
		}
	}

	@Override
	public ProjectVO retrieveProject(String pjId) {
		// TODO Auto-generated method stub
		return projectDAO.selectProject(pjId);
	}


}
