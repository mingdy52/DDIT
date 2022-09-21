package kr.or.ddit.proj.workmark.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.proj.work.vo.WorkVO;
import kr.or.ddit.proj.workmark.vo.WorkMarkVO;

@Mapper
public interface WorkMarkDAO {

	 public void insertWorkMark(WorkMarkVO markVO);
	 
	 public void deleteWorkMark(WorkMarkVO markVO);

	public WorkMarkVO selectWorkMark(WorkMarkVO markVO);

	public List<WorkMarkVO> selectWorkMarkList(WorkMarkVO markVO);
	
	public void deleteWorkNumMark(WorkVO work);
}
