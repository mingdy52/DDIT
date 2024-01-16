package kr.or.ddit.member.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MemberViewController{
	MemberService service = new MemberServiceImpl();
	
	@RequestMapping("/member/memberView.do")
	public String memberView(
			@RequestParam(value="who", required=true) String memId
			, @RequestParam(value="layout", required=false) String layout
			, HttpServletRequest req
	){
		MemberVO member = service.retrieveMember(memId);
		req.setAttribute("member", member);
		String viewName = null;
		if("grid".equals(layout)) {
			viewName = "/member/memberView.tiles";	
		}else {
			viewName = "member/memberView";
		}
		return viewName;
	}
}















