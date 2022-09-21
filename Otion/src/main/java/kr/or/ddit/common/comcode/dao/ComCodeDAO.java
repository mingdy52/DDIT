package kr.or.ddit.common.comcode.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.common.comcode.vo.ComCodeVO;

@Mapper
public interface ComCodeDAO {

	public List<ComCodeVO> selectComCodeList(String comCode);
	
	public void insertMemberRoleAssignment(String memId);
}
