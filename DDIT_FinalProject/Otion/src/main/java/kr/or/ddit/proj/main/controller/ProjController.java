package kr.or.ddit.proj.main.controller;

import java.io.File;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.common.login.dao.LoginDAO;
import kr.or.ddit.common.login.vo.MemberPrincipal;
import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.proj.colleague.service.ColleagueService;
import kr.or.ddit.proj.colleague.vo.ColleagueVO;
import kr.or.ddit.proj.git.service.GitService;
import kr.or.ddit.proj.git.vo.GitVO;
import kr.or.ddit.proj.main.service.ProjectService;
import kr.or.ddit.proj.main.vo.ProjectVO;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 고정현
 * @since 2022. 8. 4.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 2.      박채록       최초작성(tiles만 사용)
 * 2022. 8. 4.      고정현	타일즈 controller 얹음
 * 2022. 8. 8.      고정현       프로젝트 메인에서 프로젝트 생성 추가
 * 2022  9. 1.      고정현       접근 제한 막기
 * Copyright (c) 2022 by DDIT All right reserved
 *      </pre>
 */
@Controller
@Slf4j
@RequestMapping("/project")
public class ProjController {

	@Inject
	ProjectService projectService;

	@Inject
	ColleagueService colleagueService;

	@Inject
	GitService gitService;
	
	@Inject
	private LoginDAO login;
	
	@GetMapping
	public String projMain(Model model, Authentication auth, RedirectAttributes res, HttpSession session) {
		session.removeAttribute("project");
		session.removeAttribute("projectProdList");
		session.removeAttribute("memberList");
		// 로그인한 사람인가?
		if (auth == null) {
			res.addFlashAttribute("message", "로그인 해야 프로젝트를 이용할 수 있습니다.");
			return "redirect:/login";
		}
		String memId = auth.getName();
		List<ProjectVO> projectList = projectService.retrieveList(memId);
		for (ProjectVO single : projectList) {
			if (single.getPjName().length() > 7) {
				single.setPjName(single.getPjName().substring(0, 8) + "...");
			}
			// 해당 프로젝트에 참가중인 모든 팀원들을 가져옴(해당 팀원이 블락 당했는지 여부를 확인해야함)
			List<ColleagueVO> colleagueList = colleagueService.retrieveColleagueList(single.getPjId());

			// 블랙 리스트 가져오기
			List<ColleagueVO> noblack = colleagueList;
			if (noblack.size() != 0) {
				for (ColleagueVO colleague : colleagueList) {
					if (login.blackView(colleague.getMemId()) != null) {
						noblack.remove(single);
					}
				}
			}
			single.setColleagueList(noblack);
		}
		

		session.setAttribute("home", "N");
		session.setAttribute("projectList", projectList);
		return "project2/project";
	}

	// 해당 프로젝트를 신청한 사람에 대해서 받아오기
	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ProjectVO selectCooFormList(@ModelAttribute ProjectVO project, Model model, Authentication auth) {
		log.info("************* 프로젝트 아이디 : {}", project.getPjId());

		if (StringUtils.isBlank(project.getPjId())) {
			return null;
		}
		MemberVO member = ((MemberPrincipal) auth.getPrincipal()).getRealMember();

		project.setPjAdminId(member.getMemId());

		ProjectVO cooFormProject = projectService.retrieveCooFormList(project);

		return cooFormProject;
	}

	// 프로젝트 생성(생성시 github repository도 생성되게 추가해야함)
	@PostMapping
	public String createProject(@ModelAttribute ProjectVO project, Errors errors, Authentication auth) {
		MemberVO member = ((MemberPrincipal) auth.getPrincipal()).getRealMember();
		project.setPjCreatorId(member.getMemId());
		log.info("{} : 프로젝트", project);
		projectService.createProject(project);

		ColleagueVO colleague = new ColleagueVO();
		colleague.setPjId(project.getPjId());
		colleague.setMemId(member.getMemId());

		colleagueService.createColleagueLeader(colleague);

		log.info("#############{}", colleague);
		// 깃허브 레파지토리 추가되게 변경
		GitVO gitVO = new GitVO();
		gitVO.setPjId(project.getPjId());
		gitVO.setWorkStoragePath("C:/" + project.getPjName());
		gitService.insertGitStorage(gitVO);

		File folder = new File(gitVO.getWorkStoragePath());
		if (!folder.exists()) {
			folder.mkdirs();
		}

		return "redirect:/project";
	}

}
