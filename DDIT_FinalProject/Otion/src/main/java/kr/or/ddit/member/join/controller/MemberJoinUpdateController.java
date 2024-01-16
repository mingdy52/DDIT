package kr.or.ddit.member.join.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.common.validate.UpdateGroup;
import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.join.service.MemberJoinService;
import kr.or.ddit.member.vo.MemberVOWrapper;

@Controller
@RequestMapping("/member/memberUpdate.do")
public class MemberJoinUpdateController {
	@Inject
	MemberJoinService service;
	
	@GetMapping
	public String updateForm(
//		@SessionAttribute("authMember") MemberVO authMember
		MemberVOWrapper principal
		, Model model
	){
		MemberVO authMember = principal.getRealMember();
		// 초기값을 가지고있는 수정 양식제공. member/memberForm 재활용
//		현재 사용자의 개인 정보를 데이터베이스로 부터 조회.
		MemberVO member = service.retrieveMember(authMember.getMemId());
		model.addAttribute("member", member);
//		해당 정보를 초기값으로 수정UI 제공하기 위해 view layer로 이동
		return "join/memberJoin";
	}
	
	@PostMapping
	public String updateProcess(
		@Validated(UpdateGroup.class) @ModelAttribute("member") MemberVO member
		, BindingResult errors
		, Model model
	){
		
//		요청 데이터 검증
		String viewName = null;
		if(!errors.hasErrors()) {
//		검증을 통과하면 로직을 사용하여 수정.
			ServiceResult result = service.modifyMember(member);
			switch (result) {
			case INVALIDPASSWORD:
				model.addAttribute("message", "비밀번호 오류");
				viewName = "member/memberForm";
				break;
			case FAIL:
				model.addAttribute("message", "서버 오류");
				viewName = "join/memberJoin";
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
















