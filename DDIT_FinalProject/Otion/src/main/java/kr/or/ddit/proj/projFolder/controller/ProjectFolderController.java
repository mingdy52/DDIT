package kr.or.ddit.proj.projFolder.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.annotation.MultipartConfig;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.common.attach.service.AttatchService;
import kr.or.ddit.common.vo.AttatchVO;
import kr.or.ddit.common.vo.SimpleSearchCondition;
import kr.or.ddit.proj.colleague.service.ColleagueService;
import kr.or.ddit.proj.colleague.vo.ColleagueVO;
import kr.or.ddit.proj.main.service.ProjectService;
import kr.or.ddit.proj.main.vo.ProjectVO;
import kr.or.ddit.proj.prod.service.ProjectProdService;
import kr.or.ddit.proj.prod.vo.ProjectPaymentVO;
import kr.or.ddit.proj.projFolder.service.ProjectFolderService;
import kr.or.ddit.proj.projFolder.vo.ProjectFileVO;
import kr.or.ddit.proj.projFolder.vo.ProjectFolderVO;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 고정형
 * @since 2022. 8. 5.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 5.      고정현       최초작성
 * 2022  8. 24.     고정현       폴더 리스트 가져오기
 * 2022. 9. 1.      고정현       접근 제한 막기
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */

@Controller
@Slf4j
@RequestMapping("/project/{pjId}/folder")
@MultipartConfig
public class ProjectFolderController {

	@Inject
	ColleagueService colleagueService;
	
	@Inject
	ProjectFolderService projectFolderService;
	
	@Inject
	AttatchService attatchService;
	
	@Inject
	private ProjectService projectService;
	
	@Inject
	private ProjectProdService prodService;
	
	@GetMapping
	public String getMyFolder(
			@PathVariable String pjId,
			Model model,
			Authentication auth,
			RedirectAttributes res
			) {
		if("none".equals(pjId) || StringUtils.isBlank(pjId)) {
			res.addFlashAttribute("message", "프로젝트를 선택해야 볼 수 있습니다.");
			return "redirect:/project";
		}
		
		if(auth == null) {
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
		}catch(Exception e) {
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
		
		// 해당 회원의 폴더 리스트 최상위만 가져오기
		List<ProjectFolderVO> folderList = projectFolderService.retrieveFolderList(colleagueVO);
		// 해당 회원의 업로드한 파일 리스트 가져오기
		List<AttatchVO> single = projectFolderService.retrieveUploadFileList(colleagueVO);
		// 해당 회원의 모든 폴더 리스트를 가져옴(해당 프로젝트의)
		List<ProjectFolderVO> allFolderList = projectFolderService.retrieveFolderAllList(colleagueVO);
		
		model.addAttribute("myColleague", colleagueVO);
		model.addAttribute("folderList", folderList);
		model.addAttribute("single", single);
		model.addAttribute("allFolderList", allFolderList);
		
		return "project2/MyFolder";
	}
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<ProjectFolderVO> selectChildFolder(
			@RequestParam String id,
			@RequestParam String colNum
			){
		log.info("&************{} : {}",id, colNum);
		ProjectFolderVO folder = new ProjectFolderVO();
		folder.setColNum(colNum);
		folder.setWfolderNum(id);
		
		List<ProjectFolderVO> folderList = projectFolderService.retrieveChildFolderList(folder);
		
		return folderList;
	}
	
	@GetMapping(value="/fileList", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<ProjectFileVO> selectFileList(
			@RequestParam String id,
			@RequestParam String searchWord
			){
		log.info("&************{}",id);
		
		SimpleSearchCondition searchCondition = new SimpleSearchCondition();
		searchCondition.setSearchWord(searchWord);
		ProjectFileVO fileVO = new ProjectFileVO();
		fileVO.setWfolderNum(id);
		fileVO.setSearchCondition(searchCondition);
		
		List<ProjectFileVO> fileList = projectFolderService.retrieveFileList(fileVO);
		
		return fileList;
	}
	
	@PostMapping
	public String  insertFolder(
			@RequestParam String id,
			@RequestParam String colNum,
			@RequestParam String folderName,
			Model model
			) {
		log.info("&************{} : {} : {}",id, colNum,folderName);
		if(StringUtils.isBlank(id) ||StringUtils.isBlank(colNum) || StringUtils.isBlank(folderName)) {
			model.addAttribute("message", "폴더 추가시에는 이름을 입력하셔야합니다.");
			return "project/MyFolder";
		}
		String[] folderArray = folderName.split(",");
		if("default".equals(id)) {
			for(String singleName : folderArray) {				
				ProjectFolderVO folder = new ProjectFolderVO();
				// 상위 폴더가 없는 최상위 폴더임
				folder.setColNum(colNum);
				folder.setWfolderName(singleName);
				projectFolderService.createNoParentFolder(folder);
			}
		}else {
			for(String singleName : folderArray) {				
				ProjectFolderVO folder = new ProjectFolderVO();
				// 상위 폴더가 없는 최상위 폴더임
				folder.setColNum(colNum);
				folder.setWfolderName(singleName);
				folder.setParentWfolderNum(id);
				projectFolderService.createYesParentFolder(folder);
			}
		}
		
		return "redirect:/project/{pjId}/folder";
	}
	
	@PostMapping("upload")
	public String fileUpload(
			@RequestParam("fileList") MultipartFile[] multiFile,
			@RequestParam String wfolderNum,
			RedirectAttributes res,
			Authentication auth
			) {
		String memId = auth.getName();
		
		log.info("************{}", multiFile[0].getOriginalFilename());
		if(StringUtils.isBlank(wfolderNum)) {
			res.addFlashAttribute("message", "파일 올릴곳을 선택해주세요");
			return "redirect:/project/{pjId}/folder";
		}
		AttatchVO attatch = null;
		// selectKey로 받아온것을 미리 저장
		String attatchNum = null;
		String attatchPlace = null;
		for(int i=0; i<multiFile.length; i++) {
			MultipartFile single = multiFile[i];
			attatch = new AttatchVO(single);
			// 여러개 일경우 순서를 넣어줌
			attatch.setAttatchOrder(i+1);
			attatch.setUploaderId(memId);
			if(StringUtils.isNotBlank(attatchNum)) {
				attatch.setAttatchNum(attatchNum);
			}
			if(StringUtils.isNotBlank(attatchPlace)) {
				attatch.setAttatchPlace(attatchPlace);
			}
			// 해당 올라가는 폴더의 번호를 알려줌
			log.info("파일 올라간거 목록 : {}", attatch);
			log.info("파일 올려야할 폴더번호 : {}", wfolderNum);
			attatchService.createFolderAttatch(attatch);
			log.info("파일 insert된 목록 : {}", attatch);
			attatchNum = attatch.getAttatchNum();
			attatchPlace = attatch.getAttatchPlace();
		}
		ProjectFileVO fileVO = new ProjectFileVO();
		fileVO.setAttatchNum(attatchNum);
		fileVO.setWfolderFileNum(attatchPlace);
		fileVO.setWfolderNum(wfolderNum);
		// 포로젝트 폴더 DB에 넣기
		projectFolderService.createFolderFile(fileVO);
		
		return "redirect:/project/{pjId}/folder";
	}
	
	@DeleteMapping(value="delFile", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> deleteFile(
			@RequestParam String[] attatchNum,
			@RequestParam int[] attatchOrder,
			@RequestParam String[] attatchPlace,
			Model model
			) {
		log.info("########## 값 넘어온거 {} : {} : {}",attatchNum,attatchOrder,attatchPlace);
		for(int i=0; i<attatchNum.length; i++) {
			AttatchVO attatch = new AttatchVO();
			attatch.setAttatchNum(attatchNum[i]);
			attatch.setAttatchOrder(attatchOrder[i]);
			attatch.setAttatchPlace(attatchPlace[i]);
			attatchService.deleteAttatchFile(attatch);
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("message", "삭제가 완료 되었습니다");
		
		return map;
	}
	@DeleteMapping("delFolder")
	public String deleteFolder(
			@RequestParam String[] wfolderNum,
			Model model
			) {
		log.info("########## 값 넘어온거 {} ", wfolderNum);
		for(String single : wfolderNum) {
			// 각 폴더에 대한 vo객체 생성
			ProjectFolderVO folder = new ProjectFolderVO();
			folder.setWfolderNum(single);
			// 해당 폴더 번호에 대한 모든 것을 지우기(delete)
			projectFolderService.removeFolder(folder);
		}
		
		model.addAttribute("message", "삭제가 완료 되었습니다");
		
		return "redirect:/project/{pjId}/folder";
	}
	
}
