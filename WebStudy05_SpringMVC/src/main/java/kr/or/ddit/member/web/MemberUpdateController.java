package kr.or.ddit.member.web;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemberVOWrapper;

@Controller
@RequestMapping("/member/memberUpdate.do")
public class MemberUpdateController {
	
	@Inject
	MemberService service;
	
	@GetMapping
	public String updateForm(HttpSession session
//							, @SessionAttribute("authMember") MemberVO authMember
							, MemberVOWrapper principle // 앞에 필터가 있어야만 사용 가능.
							, Model model){
		MemberVO authMember = principle.getRealMember();
		// 초기값을 가지고있는 수정 양식제공. member/memberForm 재활용
//		현재 사용자의 개인 정보를 데이터베이스로 부터 조회.
		MemberVO member = service.retrieveMember(authMember.getMemId());
		model.addAttribute("member", member);
//		해당 정보를 초기값으로 수정UI 제공하기 위해 view layer로 이동
		return "member/memberForm";
	}
	
	@PostMapping
	public String updateProcess(@Validated(UpdateGroup.class) @ModelAttribute("member") MemberVO member
								, BindingResult error
								, Model model
								){
//		요청 데이터 검증
		String viewName = null;
		if(!error.hasErrors()) {
//		검증을 통과하면 로직을 사용하여 수정.
			ServiceResult result = service.modifyMember(member);
			switch (result) {
			case INVALIDPASSWORD:
				model.addAttribute("message", "비밀번호 오류");
				viewName = "member/memberForm";
				break;
			case FAIL:
				model.addAttribute("message", "서버 오류");
				viewName = "member/memberForm";
				break;
			default:
				viewName = "redirect:/myPage.do";
				break;
			}
		}else {
//		통과하지 못하면, 기존 입력 데이터와 검증 결과 데이터를 가지고 view layer 로 이동.
			viewName = "member/memberForm";
		}
		return viewName;
	}

}
















