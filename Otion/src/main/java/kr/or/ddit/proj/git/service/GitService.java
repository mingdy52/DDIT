package kr.or.ddit.proj.git.service;

import kr.or.ddit.proj.git.vo.GitVO;

public interface GitService {

	public GitVO retrieveGit(String pjId);
	
	
	public void insertGitStorage(GitVO gitVO);
}
