package kr.or.ddit;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullCommand;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.RemoteAddCommand;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.errors.RepositoryNotFoundException;
import org.eclipse.jgit.errors.UnsupportedCredentialItem;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Config;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.NullProgressMonitor;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ProgressMonitor;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.CredentialItem;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.RemoteConfig;
import org.eclipse.jgit.transport.URIish;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.junit.Test;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHCommitSearchBuilder;
import org.kohsuke.github.GHContent;
import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueComment;
import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHRepositoryCloneTraffic.DailyInfo;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.kohsuke.github.PagedSearchIterable;

public class gitAPITest {

	String repoName = "KoJungHyeon/practice";
	File file = new File("C:/gitpractice");
	static String ID = "rhwjdgus234@gmail.com";
	static String token = "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";
	private static final Logger LOG = Logger.getGlobal();
	private GitHub github;

	public void GHConnect() {
		try {// 깃허브 객체 생성
			this.github = new GitHubBuilder().withOAuthToken(token).build();
			LOG.info("깃 계정 연결 성공");
		} catch (Exception e) {
			LOG.info("깃 계정 연결 실패. 재 연결이 필요합니다.");
		}
	}

	public GitHub getConnection() {
		return github;
	}

	public Logger getLog() {
		return LOG;
	}
	

	@Test
	// 파일 리스트 가져오기
	public void test() throws IOException {
		Repository exist = new FileRepositoryBuilder().setGitDir(new File("C:/gitpractice/.git")).build();
		File file = exist.getWorkTree();
//		fileSearch("C:/gitpractice");ㄴ
	}

	public void fileSearch(String path) throws IOException {
		File file = new File(path);
		File[] list = file.listFiles();
		for (File single : list) {
			if (single.isFile()) {
				System.out.println("파일 : " + single.getName());
			} else {
				fileSearch(single.getCanonicalPath().toString());
			}
		}
	}

	@Test
	public void test3() throws IOException {
		String token = "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";
		String repoName = "KoJungHyeon/practice";
		GitHub github = new GitHubBuilder().withOAuthToken(token).build();
		github.checkApiUrlValidity();
		GHRepository res = github.getRepository(repoName);
		// 전송 URL
		System.out.println(res.getGitTransportUrl());
		System.out.println(res.getHttpTransportUrl());
		
		// 계정 경로랑 마스터 나옴
		System.out.println(res.getBranches());
		// 계정 이름
		System.out.println(res.getCollaboratorNames());
		// 계정 URL
		System.out.println(res.getUrl());

		System.out.println(res.getBranch("master").getName());
		System.out.println(res);
		// 사이즈
		System.out.println(res.getSize());
		// 다운로드 가능 여부
		System.out.println(res.hasDownloads());
		// 해당 유저에 대한 정보
		System.out.println(res.getCollaborators());
		// 해당 공유저장소 만든 일자
		System.out.println(res.getCreatedAt());


		/*
		 * GHCommitSearchBuilder builder = github.searchCommits()
		 * .author("rhwjdgus234@gmail.com")
		 * .sort(GHCommitSearchBuilder.Sort.AUTHOR_DATE);
		 * 
		 * PagedSearchIterable<GHCommit> commits = builder.list().withPageSize(7);
		 * System.out.println(commits._iterator(1));
		 */

	}

	@Test
	public void test8() throws Exception {
		File localPath = File.createTempFile("gitpractice", "");
		// delete repository before running this
		System.out.println(localPath.toPath().toString());

		Files.delete(localPath.toPath());

		// This code would allow to access an existing repository
//      try (Git git = Git.open(new File("/home/vogella/git/eclipse.platform.ui"))) {
//          Repository repository = git.getRepository();
//
//      }

		// Create the git repository with init
		try (Git git = Git.init().setDirectory(localPath).call()) {
			System.out.println("Created repository: " + git.getRepository().getDirectory());
			File myFile = new File(git.getRepository().getDirectory().getParent(), "testfile");
			if (!myFile.createNewFile()) {
				throw new IOException("Could not create file " + myFile);
			}

			// run the add-call
			git.add().addFilepattern("testfile").call();

			git.commit().setMessage("Initial commit").call();
			System.out.println("Committed file " + myFile + " to repository at " + git.getRepository().getDirectory());
			// Create a few branches for testing
			for (int i = 0; i < 10; i++) {
				git.checkout().setCreateBranch(true).setName("new-branch" + i).call();
			}
			// List all branches
			List<Ref> call = git.branchList().call();
			for (Ref ref : call) {
				System.out.println("Branch: " + ref + " " + ref.getName() + " " + ref.getObjectId().getName());
			}

			// Create a few new files
			for (int i = 0; i < 10; i++) {
				File f = new File(git.getRepository().getDirectory().getParent(), "testfile" + i);
				f.createNewFile();
				if (i % 2 == 0) {
					git.add().addFilepattern("testfile" + i).call();
				}
			}

			Status status = git.status().call();

			Set<String> added = status.getAdded();
			for (String add : added) {
				System.out.println("Added: " + add);
			}
			Set<String> uncommittedChanges = status.getUncommittedChanges();
			for (String uncommitted : uncommittedChanges) {
				System.out.println("Uncommitted: " + uncommitted);
			}

			Set<String> untracked = status.getUntracked();
			for (String untrack : untracked) {
				System.out.println("Untracked: " + untrack);
			}

			// Find the head for the repository
			ObjectId lastCommitId = git.getRepository().resolve(Constants.HEAD);
			System.out.println("Head points to the following commit :" + lastCommitId.getName());
		}

	}

	// 깃 내려받기
	@Test
	public void test4() throws Exception {
		String token = "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";
		String repoName = "KoJungHyeon/practice";
		String path = "C:/gitpractice";
		File file = new File(path);

		GitHub github = new GitHubBuilder().withOAuthToken(token).build();
		github.checkApiUrlValidity();
		GHRepository res = github.getRepository(repoName);
		Git gitRepo = Git.cloneRepository().setURI(res.getHttpTransportUrl()) // remote 주소 // 해당 저장소를 복사하여 가져옴

				.setDirectory(file.getCanonicalFile()) // 다운받을 로컬의 위치

				.setCredentialsProvider(new UsernamePasswordCredentialsProvider("rhwjdgus234@gmail.com", token)) // 인증
																													// 정보

				.call();

		gitRepo.checkout().setStartPoint(res.getBranch("master").getName()) // origin/branch_name

				.addPath(path).call();

		gitRepo.getRepository().close();

	}
	
	@Test
	public void pushGit() throws Exception {
		String token = "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";
		Path repoPath = Paths.get("C:/gitpractice");
		repoPath.toFile().createNewFile();
		System.out.println(repoPath.toAbsolutePath().toString());
		Git git = null;
		if (!new File("C:/gitpractice").exists()) {
			git = Git.init().setDirectory(repoPath.toFile()).setGitDir(repoPath.toFile()).call();
		} else {
			git = Git.open(repoPath.toFile());
		}   
//		System.out.println("폴더가 삭제되었습니다.");

		git.pull().setCredentialsProvider(new UsernamePasswordCredentialsProvider("rhwjdgus234@gmail.com", token)).call();
		git.branchCreate().setForce(true).setName("origin/master").setStartPoint("origin/master").call();
		git.checkout().
        setName("origin/master").
        call();
	}

	@Test
	public void remote() throws Exception {
		String token = "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";
		Git git = Git.open(file);

		/*
		 * PullCommand pull = git.pull(); pull.call();
		 */

		// remoteAdd
		RemoteAddCommand remoteAddCommand = git.remoteAdd();

		remoteAddCommand.setName("origin/master2");

		remoteAddCommand.setUri(new URIish("https://github.com/KoJungHyeon/practice.git"));

		// you can add more settings here if needed

		System.out.println(remoteAddCommand.getRepository().getRemoteNames());

		remoteAddCommand.call();
	}
	
	@Test
	public void createfolder() throws IOException {
		String token = "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";
		GitHub github = new GitHubBuilder().withOAuthToken(token).build();
		github.checkApiUrlValidity();
		GHRepository res = github.getRepository(repoName);
		res.createTree().add("C:/gitpractice/테스트 폴더", "테스트용 폴더", true).create();
	}
	
	public void test9(String name) throws Exception {
		Path repoPath = Paths.get("C:/gitpractice");
		System.out.println(repoPath.toAbsolutePath().toString());
		Git git = null;
		if (!new File("C:/gitpractice").exists()) {
			git = Git.init().setDirectory(repoPath.toFile()).call();
		} else {
			git = Git.open(repoPath.toFile());
		}
		git.add().addFilepattern(name).call();
		git.commit().setMessage("업로드 테스트 중입니다.").setAuthor(ID, token).call();

//		Iterable<RevCommit> logs = git.log().all().call();
//		for (RevCommit rev : logs) {
//			System.out.print(Instant.ofEpochSecond(rev.getCommitTime()));
//			System.out.print(": ");
//			System.out.print(rev.getFullMessage());
//			System.out.println();
//			System.out.println(rev.getId().getName());
//			System.out.print(rev.getAuthorIdent().getName());
//			System.out.println(rev.getAuthorIdent().getEmailAddress());
//			System.out.println("-------------------------");
//		}
//		logs = git.log().addPath("file1.md").call();
	}

	@Test
	public void multiPush() throws Exception {
		String arr[] = { "코드조각.txt"};
		for(String single : arr) {
			System.out.println(single);
			test9(single);
			push(single);
		}
	}
	
	
	public void push(String name) throws Exception {
		String token = "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";
		Path repoPath = Paths.get("C:/gitpractice");
		repoPath.toFile().createNewFile();
		System.out.println(repoPath.toAbsolutePath().toString());
		Git git = null;
		if (!new File("C:/gitpractice").exists()) {
			git = Git.init().setDirectory(repoPath.toFile()).setGitDir(repoPath.toFile()).call();
		} else {
			git = Git.open(repoPath.toFile());
		}
//		git.clean();
		
//		Git git = Git.init().setDirectory(gitFile).call(); // 빈 저장소를 만들어줌
//
//		ObjectId head = git.getRepository().resolve("HEAD");
//		RevWalk walk = new RevWalk(git.getRepository());
//		RevCommit commit = walk.parseCommit(head);

		// remoteAdd
		RemoteAddCommand remoteAddCommand = git.remoteAdd();

		remoteAddCommand.setName("origin/master");

		remoteAddCommand.setUri(new URIish("https://github.com/KoJungHyeon/practice.git"));

		
		// you can add more settings here if needed

		remoteAddCommand.call();
		// add
//			AddCommand add = git.add();
		git.add().addFilepattern(name).setUpdate(true).call();
		git.commit().setMessage("업로드 테스트 중입니다.").setAuthor(ID, token).call();

		
		// commit
		RevCommit commit = git.commit()//

				.setAuthor(ID, token)//

				.setMessage("커밋됫나?")//

				.call();
		System.out.println(git.push().getRepository());

		PushCommand pushCommand = git.push();

		pushCommand.setCredentialsProvider(new UsernamePasswordCredentialsProvider("rhwjdgus234@gmail.com", token))
				.setPushAll().call();

		// you can add more settings here if needed

		/*
		 * Git gitrepo =
		 * Git.cloneRepository().setURI(res.getHttpTransportUrl()).setDirectory(new
		 * File("C:/gitpractice")) .setCredentialsProvider(new
		 * UsernamePasswordCredentialsProvider("rhwjdgus234@gmail.com", token)).call();
		 */
//		Iterable<RevCommit> log = git.log().call();
//		
//		while(log.iterator().hasNext()) {
//			System.out.println(log.iterator().next());
//		}
//		Status status = git.status().call();
//
//		Set<String> added = status.getAdded();
//		for (String adds : added) {
//			System.out.println("Added: " + adds);
//		}
//		Set<String> uncommittedChanges = status.getUncommittedChanges();
//		for (String uncommitted : uncommittedChanges) {
//			System.out.println("Uncommitted: " + uncommitted);
//		}
//
//		Set<String> untracked = status.getUntracked();
//		for (String untrack : untracked) {
//			System.out.println("Untracked: " + untrack);
//		}
//
//		// Find the head for the repository
//		ObjectId lastCommitId = git.getRepository().resolve(Constants.HEAD);
//		System.out.println("Head points to the following commit :" + lastCommitId.getName());

	}

}

