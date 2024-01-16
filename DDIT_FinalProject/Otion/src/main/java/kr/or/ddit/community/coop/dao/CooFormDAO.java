package kr.or.ddit.community.coop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.community.coop.vo.CooFormVO;
import kr.or.ddit.proj.main.vo.ProjectVO;

@Mapper
public interface CooFormDAO {
	
	public void insertCooForm(CooFormVO cooForm);

	public void cancelForm(String cooNum);

	public void correctForm(String cooNum);

	public List<ProjectVO> selectProject(String memId);
	
	public List<ProjectVO> updateProject(String memId);
}
