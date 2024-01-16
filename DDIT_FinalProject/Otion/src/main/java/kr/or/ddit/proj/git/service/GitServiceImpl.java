package kr.or.ddit.proj.git.service;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.inject.Inject;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.RemoteAddCommand;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.transport.URIish;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.proj.git.dao.GitDAO;
import kr.or.ddit.proj.git.vo.GitVO;

@Service
@Transactional
public class GitServiceImpl implements GitService {

	static String ID = "rhwjdgus234@gmail.com";
	static String token = "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";
	
	@Inject
	GitDAO gitDAO;
	
	@Override
	public GitVO retrieveGit(String pjId) {
		// TODO Auto-generated method stub
		return gitDAO.selectGit(pjId);
	}

	@Override
	public void insertGitStorage(GitVO gitVO) {
		// TODO Auto-generated method stub
		gitDAO.insertGitStorage(gitVO);
	}
}
