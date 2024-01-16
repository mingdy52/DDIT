package kr.or.ddit.member.web;

import javax.inject.Inject;
import javax.servlet.http.Cookie;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class MyPageController{
	@Inject
	MemberService service;
			
	@RequestMapping("/myPage.do")
	public String myPage(@SessionAttribute("authMember") MemberVO authMember
						, @CookieValue(value="JSESSIONID") String jsessionid
						, @CookieValue(value="JSESSIONID") Cookie sessionCookie
						, Model model) {
//		MemberVO authMember = (MemberVO) req.getSession().getAttribute("authMember");
		String memId = authMember.getMemId();
		MemberVO member = service.retrieveMember(memId);
		model.addAttribute("member", member);
		log.info("JSESSIONID : {}, {}", jsessionid, sessionCookie);
		
		return "member/myPage";
	}
}
