package kr.or.ddit.proj.git.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.RemoteAddCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.transport.URIish;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.common.attach.service.AttatchService;
import kr.or.ddit.common.login.vo.MemberPrincipal;
import kr.or.ddit.common.vo.AttatchVO;
import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.proj.colleague.service.ColleagueService;
import kr.or.ddit.proj.colleague.vo.ColleagueVO;
import kr.or.ddit.proj.filerecord.service.FileRecordService;
import kr.or.ddit.proj.filerecord.vo.FileRecordVO;
import kr.or.ddit.proj.git.service.GitService;
import kr.or.ddit.proj.git.vo.GitVO;
import kr.or.ddit.proj.main.service.ProjectService;
import kr.or.ddit.proj.main.vo.ProjectVO;
import kr.or.ddit.proj.prod.service.ProjectProdService;
import kr.or.ddit.proj.prod.vo.ProjectPaymentVO;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 고정현
 * @since 2022. 8. 5.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 5.      고정현       최초 작성
 * Copyright (c) 2022 by DDIT All right reserved
 *      </pre>
 */
@Controller
@Slf4j
@MultipartConfig
@RequestMapping("/project/{pjId}/git")
public class GitController {

	static String ID = "rhwjdgus234@gmail.com";
	static String token = "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";
	
	@Inject
	GitService gitService;
	
	@Inject
	AttatchService attatchService;

	@Inject
	ColleagueService colleagueService;
	
	@Inject
	FileRecordService fileRecordService;
	
	@Inject
	private ProjectService projectService;
	
	@Inject
	private ProjectProdService prodService;

	@GetMapping
	public String getGitList(@PathVariable String pjId, Authentication auth, Model model) {
		
		ColleagueVO colleagueVO = new ColleagueVO();
		if (auth == null) {
			return "redirect:/";
		}
		MemberVO member = ((MemberPrincipal) auth.getPrincipal()).getRealMember();
		colleagueVO.setPjId(pjId);
		colleagueVO.setMemId(member.getMemId());
		try {
			colleagueVO = colleagueService.retrieveColleague(colleagueVO);
		} catch (NullPointerException e) {
			return "redirect:/";
		}
		GitVO gitVO = gitService.retrieveGit(pjId);
	
		String url = gitVO.getWorkStoragePath();
		
		Path repoPath = Paths.get(url);
		try {
			repoPath.toFile().createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		
		ProjectVO project = new ProjectVO();
		project = projectService.retrieveProject(pjId);
		// 해당 프로젝트가 결제 되었는지 확인 유무
		ProjectPaymentVO payVO = prodService.retrievePayment(pjId);
		if (payVO == null) {
			return "project2/projectHome";
		}

		model.addAttribute("project", project);
		
		Git git = null;
		try {
			if (!new File(gitVO.getWorkStoragePath()).exists()) {
				git = Git.init().setDirectory(repoPath.toFile()).setGitDir(repoPath.toFile()).call();
			} else {
				try {
					git = Git.open(repoPath.toFile());
				} catch (Exception e) {
					File file = new File(repoPath.toFile().getCanonicalFile().toString());
					
					if(!file.exists()) {
						file.mkdirs();
					}

					GitHub github = new GitHubBuilder().withOAuthToken(token).build();
					github.checkApiUrlValidity();
					GHRepository res = github.getRepository("KoJungHyeon/practice");
					Git gitRepo = Git.cloneRepository().setURI(res.getHttpTransportUrl()) // remote 주소 // 해당 저장소를 복사하여
																							// 가져옴

							.setDirectory(file.getCanonicalFile()) // 다운받을 로컬의 위치

							.setCredentialsProvider(
									new UsernamePasswordCredentialsProvider("rhwjdgus234@gmail.com", token)) // 인증
																												// 정보

							.call();

					gitRepo.checkout().setStartPoint(res.getBranch("master").getName()) // origin/branch_name

							.addPath(url).call();

					gitRepo.getRepository().close();
				}
			}

			git.pull().setCredentialsProvider(new UsernamePasswordCredentialsProvider("rhwjdgus234@gmail.com", token))
					.call();
			git.branchCreate().setForce(true).setName("master").setStartPoint("master").call();
			git.checkout().setName("master").call();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		
		File file = new File(gitVO.getWorkStoragePath());
		List<Map<String, Object>> fileList = new ArrayList<>();
		if(file.listFiles() != null) {
			for (File single : file.listFiles()) {
				Map<String, Object> map = new HashMap<String, Object>();
				if (!single.getName().contains(".git") && !single.getName().contains(".md")
						&& !single.getName().contains(".md") && !single.getName().contains(".classpath")
						&& !single.getName().contains(".project") && !single.getName().contains(".springBeans")) {
					Path files;
					Map<String, Object> attributes = null;
					try {
						files = Paths.get(single.getCanonicalPath());
						attributes = Files.readAttributes(files, "*");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					FileTime creationTime = (FileTime) attributes.get("creationTime");
					if (single.isFile()) {
						map.put("isFile", true);
						map.put("fileName", single.getName());
						map.put("date", creationTime.toString().split("T")[0]);
					} else {
						map.put("isFile", false);
						map.put("fileName", single.getName());
						map.put("date", creationTime.toString().split("T")[0]);
					}
					fileList.add(map);
				}
			}
		}
		model.addAttribute("fileList", fileList);

		return "project2/git";
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<Map<String, Object>> fileSearch(@RequestParam String parent, @PathVariable String pjId) {
		// accept안에 json이 포함되있는지에 대한 유무를 확인(데이터 요구 방식에 대해서 확인)
		// 현재 클릭한 파일의 위치를 선택(
		String currentRoot = null;
		GitVO gitVO = gitService.retrieveGit(pjId);
		currentRoot = gitVO.getWorkStoragePath() + "/" + parent;
		File file = new File(currentRoot);
		List<Map<String, Object>> fileList = new ArrayList<>();
		for (File single : file.listFiles()) {
			Map<String, Object> map = new HashMap<String, Object>();
			if (!single.getName().contains(".git") && !single.getName().contains(".md")
					&& !single.getName().contains(".md") && !single.getName().contains(".classpath")
					&& !single.getName().contains(".project") && !single.getName().contains(".springBeans")) {
				if (single.isFile()) {
					map.put("isFile", true);
					map.put("fileName", single.getName());
				} else {
					map.put("isFile", false);
					map.put("fileName", single.getName());
				}
				fileList.add(map);
			}
		}

		// 파일 하나하나를 adaptee 형태로 변환
		// sorted는 comparable하는 객체가 상속이나 구현이 되지 않으면 예외발생
//			List<FancyTreeNode> adapterList = fileList.stream().map((file) -> {
//				return new FancyTreeNodeFileAdapter(file);
//			}).sorted().collect(Collectors.toList());

		return fileList;
	}

	// 깃 파일 업로드
	@PostMapping
	public String gitUpload(
			@RequestParam MultipartFile[] gitUpload, 
			@PathVariable String pjId, 
			Model model,
			RedirectAttributes res,
			Authentication auth) {
		log.info(gitUpload[0].getOriginalFilename());
		
		for (MultipartFile single : gitUpload) {
			log.info("###############{}", single.getOriginalFilename());
		}

		GitVO gitVO = gitService.retrieveGit(pjId);
		if (gitVO == null) {
			model.addAttribute("message", "해당 프로젝트는 공유 저장소가 존재 하지 않습니다.");
			return "redirect:/project";
		}
		
		String memId = auth.getName();
		// 해당 프로젝트의 깃 프로젝트를 만들어줌
		File git = new File(gitVO.getWorkStoragePath());
		int idx = 1;
		for (MultipartFile single : gitUpload) {
			AttatchVO attatch = new AttatchVO(single);
			// 해당 파일을 DB에 넣기
			attatch.setAttatchOrder(idx);
			attatch.setAttatchPlace(gitVO.getWorkStorageNum());
			attatch.setUploaderId(memId);
			
			log.info("###############{}",attatch);
			
			attatchService.createGitAttatch(attatch);
			try {
				// 해당파일을 업로드 함
				attatch.saveGit(git);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			idx++;
		}
		
		for(MultipartFile single : gitUpload) {
			try {
				beforPush(single.getOriginalFilename(), gitVO.getWorkStoragePath());
				gitPush(single.getOriginalFilename(), gitVO.getWorkStoragePath());
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		res.addFlashAttribute("upload", "해당 파일이 업로드 되었습니다.");

		return "redirect:/project/{pjId}/git";
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> gitDown(
			@PathVariable String pjId,
			Authentication auth) {
		String token = "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";
		String memId = auth.getName();
		
		GitVO gitVO = gitService.retrieveGit(pjId);
		Map<String, Object> map = new HashMap<>();
		String message = null;
		if (StringUtils.isBlank(gitVO.getWorkStoragePath())) {
			message = "해당 프로젝트에는 공유저장소가 존재하지 않습니다.";
			map.put("message", message);
			return map;
		}
		String url = gitVO.getWorkStoragePath();

		File create = new File(gitVO.getWorkStoragePath());
		if(!create.exists()) {
			create.mkdirs();
		}
		
		Path repoPath = Paths.get(url);
		try {
			repoPath.toFile().createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("#####################{}", repoPath.toAbsolutePath().toString());
		Git git = null;
		
		try {
			if (!new File(url).exists()) {
				git = Git.init().setDirectory(repoPath.toFile()).setGitDir(repoPath.toFile()).call();
			} else {
				try {
					git = Git.open(repoPath.toFile());
				} catch (Exception e) {
					File file = new File(url);

					GitHub github = new GitHubBuilder().withOAuthToken(token).build();
					github.checkApiUrlValidity();
					GHRepository res = github.getRepository("KoJungHyeon/practice");
					Git gitRepo = Git.cloneRepository().setURI(res.getHttpTransportUrl()) // remote 주소 // 해당 저장소를 복사하여
																							// 가져옴

							.setDirectory(file.getCanonicalFile()) // 다운받을 로컬의 위치

							.setCredentialsProvider(
									new UsernamePasswordCredentialsProvider("rhwjdgus234@gmail.com", token)) // 인증
																												// 정보

							.call();

					gitRepo.checkout().setStartPoint(res.getBranch("master").getName()) // origin/branch_name

							.addPath(url).call();

					gitRepo.getRepository().close();
					message = "최초 내려받기에 성공했습니다.";
					
					FileRecordVO record = new FileRecordVO();
					record.setDownloaderId(memId);
					record.setWorkstorageNum(gitVO.getWorkStorageNum());
					fileRecordService.createDownRecord(record);
					map.put("message", message);
					
					return map;
				}
			}

			git.pull().setCredentialsProvider(new UsernamePasswordCredentialsProvider("rhwjdgus234@gmail.com", token))
					.call();
			git.branchCreate().setForce(true).setName("master").setStartPoint("master").call();
			git.checkout().setName("master").call();
			message = "해당 파일을 모두 내려받았습니다.";
			FileRecordVO record = new FileRecordVO();
			record.setPjId(gitVO.getPjId());
			record.setDownloaderId(memId);
			record.setWorkstorageNum(gitVO.getWorkStorageNum());
			
			fileRecordService.createGitDownRecord(record);
			
			
			map.put("message", message);
			
			return map;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			message = "실패";
			map.put("message", message);
			return map;
		}
	}
	
	public void beforPush(String name, String path) throws Exception {
		Path repoPath = Paths.get("C:/gitpractice2");
		System.out.println(repoPath.toAbsolutePath().toString());
		Git git = null;
		if (!new File("C:/gitpractice2").exists()) {
			git = Git.init().setDirectory(repoPath.toFile()).call();
		} else {
			git = Git.open(repoPath.toFile());
		}
		git.add().addFilepattern(name).call();
		git.commit().setMessage("업로드 테스트 중입니다.").setAuthor(ID, token).call();

	}
	
	public void gitPush(String name , String path) throws Exception {
		String token = "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";
		Path repoPath = Paths.get("C:/gitpractice2");
		repoPath.toFile().createNewFile();
		System.out.println(repoPath.toAbsolutePath().toString());
		Git git = null;
		if (!new File("C:/gitpractice2").exists()) {
			git = Git.init().setDirectory(repoPath.toFile()).setGitDir(repoPath.toFile()).call();
		} else {
			git = Git.open(repoPath.toFile());
		}
		// remoteAdd
		RemoteAddCommand remoteAddCommand = git.remoteAdd();

		remoteAddCommand.setName("master");

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

	}
	
	
}
