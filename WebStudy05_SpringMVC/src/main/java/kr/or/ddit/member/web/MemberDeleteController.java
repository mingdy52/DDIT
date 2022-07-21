package kr.or.ddit.member.web;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemberVOWrapper;

@Controller
public class MemberDeleteController{
	@Inject
	MemberService service;
	
	@RequestMapping(value="/member/memberDelete.do", method=RequestMethod.POST)
	public String memberDelete(
							@RequestParam String password
//							, @SessionAttribute("authMember") MemberVO authMember
							, MemberVOWrapper principle
							, RedirectAttributes redirectAttributes) {
		
//		String memId = authMember.getMemId();
		String memId = principle.getName();
		ServiceResult result = service.removeMember(MemberVO.builder()
															.memId(memId)
															.memPass(password)
															.build());
		String viewName = "redirect:/myPage.do";
		switch (result) {
		case INVALIDPASSWORD:
			redirectAttributes.addAttribute("message", "비번 오류");
			break;
		case FAIL:
			redirectAttributes.addAttribute("message", "서버 오류");
			break;

		default:
//			session.invalidate();
			viewName = "login/logout";
			break;
		}
		return viewName;
	}
}





















