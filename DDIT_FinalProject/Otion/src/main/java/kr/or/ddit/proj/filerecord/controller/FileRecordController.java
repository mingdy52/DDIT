package kr.or.ddit.proj.filerecord.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.common.vo.AttatchVO;
import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.common.vo.SimpleSearchCondition;
import kr.or.ddit.proj.colleague.service.ColleagueService;
import kr.or.ddit.proj.colleague.vo.ColleagueVO;
import kr.or.ddit.proj.filerecord.service.FileRecordService;
import kr.or.ddit.proj.filerecord.vo.FileRecordVO;
import kr.or.ddit.proj.main.service.ProjectService;
import kr.or.ddit.proj.main.vo.ProjectVO;
import kr.or.ddit.proj.prod.service.ProjectProdService;
import kr.or.ddit.proj.prod.vo.ProjectPaymentVO;
import kr.or.ddit.proj.projFolder.service.ProjectFolderService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/project/{pjId}/record")
public class FileRecordController {

	@Inject
	ColleagueService colleagueService;

	@Inject
	ProjectFolderService projectFolderService;

	@Inject
	FileRecordService recordService;
	
	@Inject
	private ProjectService projectService;
	
	@Inject
	private ProjectProdService prodService;

	@GetMapping
	public String getFileRecordList(@PathVariable String pjId, Model model, Authentication auth,
			RedirectAttributes res) {
		if ("none".equals(pjId) || StringUtils.isBlank(pjId)) {
			res.addFlashAttribute("message", "프로젝트를 선택해야 볼 수 있습니다.");
			return "redirect:/project";
		}

		if (auth == null) {
			res.addFlashAttribute("message", "로그인 후 이용하실수 있습니다.");
			return "redirect:/";
		}
		String memId = auth.getName();

		ColleagueVO colleagueVO = new ColleagueVO();
		colleagueVO.setMemId(memId);
		colleagueVO.setPjId(pjId);

		try {
			res.addFlashAttribute("message", "로그인 후 이용하실수 있습니다.");
			colleagueVO = colleagueService.retrieveColleague(colleagueVO);
		} catch (Exception e) {
			return "redirect:/";
		}
		
		ProjectVO project = new ProjectVO();
		project = projectService.retrieveProject(pjId);
		// 해당 프로젝트가 결제 되었는지 확인 유무
		ProjectPaymentVO payVO = prodService.retrievePayment(pjId);
		if (payVO == null) {
			return "project2/projectHome";
		}

		model.addAttribute("project", project);
		
		return "project2/record/projectFileRecordList";
	}

	// 파일 다운로드 기록 insert
	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public FileRecordVO setFileDownRecord(@PathVariable String pjId, @ModelAttribute FileRecordVO record
			, Authentication auth) {
		log.info("########### {} ", record);
		String memId = auth.getName();

		record.setDownloaderId(memId);
		record.setPjId(pjId);

		recordService.createDownRecord(record);

		return record;
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<?> getSearchRecordList(@PathVariable String pjId,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@ModelAttribute SimpleSearchCondition simpleCondition, @RequestParam String searchPlace) {
		log.info("%%%%%%%%%%%%%%% {}", (simpleCondition == null ? "null" : "null 아님"));
		String memId = SecurityContextHolder.getContext().getAuthentication().getName();

		ColleagueVO colleagueVO = new ColleagueVO();
		colleagueVO.setPjId(pjId);
		colleagueVO.setMemId(memId);
		ColleagueVO myColleague = colleagueService.retrieveColleague(colleagueVO);

		// 페이징 처리 하지 말고, 여기서 searchType에 따라서 리턴 값을 변경함(리턴값은 누구든 될 수 있게 Object가 되게)
		if (simpleCondition.getSearchType().equals("upload")) {
			PagingVO<AttatchVO> upload = new PagingVO<>(7, 3);
			upload.setCurrentPage(page);
			upload.setSimpleCondition(simpleCondition);
			upload.setColleague(myColleague);
			if (searchPlace.equals("folder")) {
				// 해당 회원의 업로드한 파일 리스트 가져오기
				recordService.retrieveUploadFileList(upload);
			} else {
				recordService.retrieveGitUploadList(upload);
			}
			return upload;
		} else if (simpleCondition.getSearchType().equals("download")) {
			PagingVO<FileRecordVO> download = new PagingVO<>(7, 3);
			download.setCurrentPage(page);
			download.setSimpleCondition(simpleCondition);
			download.setColleague(myColleague);
			// 해당 회원이 해당 프로젝트에서 다운로드한 내역에 대한 기록을 조회
			recordService.retrieveDownList(download);
			return download;
		}
		return null;
	}

	@GetMapping(value = "uploadView", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public AttatchVO getListView(@PathVariable String pjId, @ModelAttribute AttatchVO attatch) {
		AttatchVO attatchView = recordService.retrieveUploadFileView(attatch);
		return attatchView;
	}
}
