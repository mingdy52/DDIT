package kr.or.ddit.proj.git.dao;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.proj.git.vo.GitVO;

@Mapper
public interface GitDAO {

	public GitVO selectGit(String pjId);

	public void insertGitStorage(GitVO gitVO);
}
