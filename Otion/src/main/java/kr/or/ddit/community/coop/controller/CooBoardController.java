package kr.or.ddit.community.coop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.annotation.MultipartConfig;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.common.CheckMember;
import kr.or.ddit.common.login.vo.MemberPrincipal;
import kr.or.ddit.common.validate.UpdateGroup;
import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.common.vo.SimpleSearchCondition;
import kr.or.ddit.community.coop.dao.CooFormDAO;
import kr.or.ddit.community.coop.service.CooBoardService;
import kr.or.ddit.community.coop.vo.CooBoardVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.proj.main.vo.ProjectVO;
import lombok.extern.slf4j.Slf4j;



/**
 * @author 고정현
 * @since 2022. 8. 4.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 4.     고정현       최초작성(타일즈 미적용)
 * 2022. 8. 9.     고정현       협업 게시판 리스트 보여주기
 * 2022. 8. 25.     서효림      페이징 적용
 * 2022. 8. 31.		서효림	페이징 적용 이어서
 * 2022. 9.	01. 	서효림	검색기능
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Slf4j
@Controller
@MultipartConfig
@RequestMapping("/cooboard")
public class CooBoardController {

	@Inject
	CooFormDAO cooFormDao;
	
	@Inject
	CooBoardService cooBoardService;
	
	@Inject
	private CheckMember check;
	
	// 협업 게시판 리스트 가져오기
	@GetMapping
	public String getCoo(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			, Model model
			,  @ModelAttribute("simpleCondition") SimpleSearchCondition simpleCondition
			) {		
		PagingVO<CooBoardVO> pagingVO = new PagingVO<>(8,5);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSimpleCondition(simpleCondition);
		List<CooBoardVO> cooBoardList = cooBoardService.retrieveCooBoardList(pagingVO);
		log.info("cooBoardList"+cooBoardList);
		model.addAttribute("cooBoardList", cooBoardList);
		// 페이징 처리
		model.addAttribute("pagingVO", pagingVO);
		
		return "coop/cooList";
	}
	
	// 해당 게시글에 대한 상세정보 가져오기
	@GetMapping("{cooNum}")
	public String getCooForm(
			@PathVariable String cooNum,
			Model model
			) {
		CooBoardVO cooBoard = cooBoardService.retrieveCooBoardList(cooNum);
		model.addAttribute("coo", cooBoard);
		model.addAttribute("boardNum", cooBoard.getCooNum());
		model.addAttribute("boardWriter", cooBoard.getWriterId());
		return "coop/cooView";
	}
	
	// 수정
	@GetMapping("{cooNum}/form")
	public String cooEdit(
			@PathVariable String cooNum
			, Model model) {
		String viewName = null;
		
		MemberVO mv=((MemberPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRealMember();
		List<ProjectVO> project = cooFormDao.selectProject(mv.getMemId());
		model.addAttribute("project", project);
		if(!model.containsAttribute("cooboard")) {
			CooBoardVO cooBoard = cooBoardService.retrieveCooBoardList(cooNum);
			model.addAttribute("cooboard", cooBoard);
		}
		viewName = "coop/cooForm";
		return viewName;
	}
	
	@PostMapping("{cooNum}/form")
	public String cooUpdate(
			@Validated(UpdateGroup.class)@ModelAttribute("cooBoard") CooBoardVO cooBoardVO
			, Errors errors
			, @RequestParam(value="delAttatchNum", required=false) String[] delAttatchNum
			, @RequestParam(value="delAttatchOrder", required=false) int[] delAttatchOrder
			, RedirectAttributes redirectAttributes
		) {
		String viewName = null;
		if(!errors.hasErrors()) {
			Map<String, Object> map = new HashMap<>();
			map.put("cooBoard", cooBoardVO);
			map.put("attatchNum", delAttatchNum);
			map.put("attatchOrder", delAttatchOrder);
			ServiceResult result = cooBoardService.modifyCooBoard(map);
			switch(result) {
			case OK:
				redirectAttributes.addFlashAttribute("message", "수정 완료");
				viewName = "redirect:/cooboard/{cooNum}";
				break;
			case FAIL:
				redirectAttributes.addFlashAttribute("message", "비밀번호 오류");
				viewName = "redirect:/cooboard/{cooNum}/form";
				break;
			}
		}else {
			String errorAttrName = BindingResult.MODEL_KEY_PREFIX + "cooBoard";
			redirectAttributes.addFlashAttribute(errorAttrName, errors);
			redirectAttributes.addFlashAttribute("cooBoard",cooBoardVO);
			viewName = "redirect:/cooboard/{cooNum}/form";
		}
		return viewName;
	}
	// 삭제
	@GetMapping("{cooNum}/del")
	public String deleteCoo(
		@PathVariable String cooNum
		, RedirectAttributes redirectAttributes
	) {
		String viewName = null;
		ServiceResult result = cooBoardService.removeCooBoard(cooNum);
		switch(result) {
		case OK:
			redirectAttributes.addFlashAttribute("message", "삭제되었습니다.");
			break;
		case FAIL:
			redirectAttributes.addFlashAttribute("message", "다시 시도행!");
			break;
		}
		viewName = "redirect:/cooboard";
		return viewName;
	}
	
	@PostMapping("{cooNum}")
	public String updateDoneState(
			@PathVariable String cooNum
			, @RequestParam String cooDoneYn
			, RedirectAttributes redirectAttributes){
			
		CooBoardVO coo = new CooBoardVO();
		coo.setCooNum(cooNum);
		coo.setCooDoneYn(cooDoneYn);

		ServiceResult result =cooBoardService.modifyCooState(coo);
		switch(result) {
			case OK:
				redirectAttributes.addFlashAttribute("message", "성공적으로 반영되었습니다.");
				break;
			case FAIL:
				redirectAttributes.addFlashAttribute("message", "다시 시도해주세요.");
				break;
			}
		return "redirect:/cooboard/{cooNum}";
	}
}

