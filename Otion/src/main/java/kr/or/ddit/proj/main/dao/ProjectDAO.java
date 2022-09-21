package kr.or.ddit.proj.main.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.community.coop.vo.CooFormVO;
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
@Mapper
public interface ProjectDAO {
	
	public List<ProjectVO> selectProjectList(String memId);
	
	public ProjectVO selectProject(String pjId);
	
	public ProjectVO selectCooFormList(ProjectVO project);
	
	public int insertProject(ProjectVO project);
}
