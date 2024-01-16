package kr.or.ddit.member.web;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAtrribute;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.validate.ValidateUtils;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MemberUpdateController {
	MemberService service = new MemberServiceImpl();
	
	@RequestMapping("/member/memberUpdate.do")
	public String updateForm(HttpSession session,  HttpServletRequest req){
		// 초기값을 가지고있는 수정 양식제공. member/memberForm 재활용
//		현재 사용자의 개인 정보를 데이터베이스로 부터 조회.
		MemberVO authMember = (MemberVO) session.getAttribute("authMember");
		MemberVO member = service.retrieveMember(authMember.getMemId());
		req.setAttribute("member", member);
//		해당 정보를 초기값으로 수정UI 제공하기 위해 view layer로 이동
		return "/member/memberForm.tiles";
	}
	
	@RequestMapping(value="/member/memberUpdate.do", method=RequestMethod.POST)
	public String updateProcess(@ModelAtrribute("member") MemberVO member, HttpServletRequest req){
//		요청 데이터 검증
		Map<String, String> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = ValidateUtils.validate(member, errors, UpdateGroup.class);
		String viewName = null;
		if(valid) {
//		검증을 통과하면 로직을 사용하여 수정.
			ServiceResult result = service.modifyMember(member);
			switch (result) {
			case INVALIDPASSWORD:
				req.setAttribute("message", "비밀번호 오류");
				viewName = "/member/memberForm.tiles";
				break;
			case FAIL:
				req.setAttribute("message", "서버 오류");
				viewName = "/member/memberForm.tiles";
				break;
			default:
				viewName = "redirect:/myPage.do";
				break;
			}
		}else {
//		통과하지 못하면, 기존 입력 데이터와 검증 결과 데이터를 가지고 view layer 로 이동.
			viewName = "/member/memberForm.tiles";
		}
		return viewName;
	}

}
















