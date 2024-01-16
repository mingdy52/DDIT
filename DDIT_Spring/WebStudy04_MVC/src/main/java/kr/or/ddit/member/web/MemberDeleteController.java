package kr.or.ddit.member.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MemberDeleteController{
	MemberService service = new MemberServiceImpl();
	
	@RequestMapping(value="/member/memberDelete.do", method=RequestMethod.POST)
	public String memberDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String password = req.getParameter("password");
		HttpSession session = req.getSession();
		MemberVO authMember = (MemberVO)session.getAttribute("authMember");
		String memId = authMember.getMemId();
		
		ServiceResult result = service.removeMember(MemberVO.builder()
															.memId(memId)
															.memPass(password)
															.build());
		String viewName = "redirect:/myPage.do";
		switch (result) {
		case INVALIDPASSWORD:
			session.setAttribute("message", "비번 오류");
			break;
		case FAIL:
			session.setAttribute("message", "서버 오류");
			break;

		default:
//			session.invalidate();
			viewName = "forward:/login/logout.do";
			break;
		}
		return viewName;
	}
}





















