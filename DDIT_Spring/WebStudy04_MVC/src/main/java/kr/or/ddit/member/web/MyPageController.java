package kr.or.ddit.member.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.resolvers.CookieValue;
import kr.or.ddit.mvc.annotation.resolvers.SessionAttribute;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class MyPageController{
	MemberService service = new MemberServiceImpl();
			
	@RequestMapping("/myPage.do")
	public String myPage(@SessionAttribute("authMember") MemberVO authMember
						, @CookieValue(value="JSESSIONID") String jsessionid
						, @CookieValue(value="JSESSIONID") Cookie sessionCookie
						, HttpServletRequest req) {
//		MemberVO authMember = (MemberVO) req.getSession().getAttribute("authMember");
		String memId = authMember.getMemId();
		MemberVO member = service.retrieveMember(memId);
		req.setAttribute("member", member);
		log.info("JSESSIONID : {}, {}", jsessionid, sessionCookie);
		
		return "/member/myPage.tiles";
	}
}
