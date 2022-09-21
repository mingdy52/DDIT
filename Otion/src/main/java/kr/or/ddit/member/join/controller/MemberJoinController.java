package kr.or.ddit.member.join.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.common.validate.InsertGroup;
import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.join.service.MemberJoinService;

/**
 * 
 * @author 서효림
 * @since 2022. 8. 5.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 5.       서효림          최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */

@Controller
@RequestMapping("/member/memberInsert.do")
public class MemberJoinController {
	@Inject
	MemberJoinService service;
	
	@ModelAttribute("admin")
	public MemberVO member() {
		return new MemberVO();
	}
	
	@GetMapping
	public String memberForm(){
		return "join/memberJoin";
	}
	
	@PostMapping
	public String insertProcess(
			@Validated(InsertGroup.class) @ModelAttribute("member") MemberVO member, 
			Errors errors, Model model, RedirectAttributes redirectAttributes
	){

		boolean valid =  !errors.hasErrors();
		String viewName = null;
		if(valid) {
			ServiceResult result = service.createMember(member);	
			switch (result) {
			case PKDUPLICATED:
				model.addAttribute("message", "아이디 중복");
				viewName = "join/memberJoin";
				break;
			case FAIL:
				model.addAttribute("message", "서버의 문제로 등록을 못했음. 쫌따 다시하셈.");
				viewName = "join/memberJoin";
				break;	
			default:
				redirectAttributes.addFlashAttribute("message", "등록 성공");
				viewName = "redirect:/login/loginForm.jsp";
				break;
			}
		}else {
			viewName = "join/memberJoin";
		}
		return viewName;
	}

	
}