package kr.or.ddit.proj.main.service;

import java.util.List;

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
public interface ProjectService {
	
	public List<ProjectVO> retrieveList(String memId);
	
	public ProjectVO retrieveProject(String pjId);
	
	public ProjectVO retrieveCooFormList(ProjectVO project);
	
	public void createProject(ProjectVO project);
}
