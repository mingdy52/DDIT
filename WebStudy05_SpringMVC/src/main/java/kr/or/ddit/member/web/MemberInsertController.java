package kr.or.ddit.member.web;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.MemberVO;

@Controller
@RequestMapping("/member/memberInsert.do")
public class MemberInsertController{
	
	@Inject
	MemberService service;
	
	@ModelAttribute("member")
	public MemberVO member() {
		return new MemberVO();
	}
	
	@GetMapping
	public String memberForm(){
		return "member/memberForm";
	}
	
	@PostMapping
	public String insertProcess(@Validated(InsertGroup.class) @ModelAttribute("member") MemberVO member
								, Errors error
								, Model model
								, RedirectAttributes redirectAttributes){

		Map<String, String> errors = new LinkedHashMap<>();
		model.addAttribute("errors", errors);
		boolean valid =  !error.hasErrors();
		String viewName = null;
		if(valid) {
			ServiceResult result = service.createMember(member);	
			switch (result) {
			case PKDUPLICATED:
				model.addAttribute("message", "아이디 중복");
				viewName = "member/memberForm";
				break;
			case FAIL:
				model.addAttribute("message", "서버의 문제로 등록을 못했음. 쫌따 다시하셈.");
				viewName = "member/memberForm";
				break;	
			default:
				redirectAttributes.addAttribute("message", "등록 성공");
				viewName = "redirect:/login/loginForm.jsp";
				break;
			}
		}else {
			viewName = "member/memberForm";
		}
		return viewName;
	}

	
}























