package kr.or.ddit.common.login.controller;

import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.common.login.service.MemberServiceImpl;
import kr.or.ddit.common.vo.MemberVO;

/**
 * 
 * @author 서효림
 * @since 2022. 8. 9.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 5.       서효림          최초작성
 * 2022. 8. 12.      고정현       로그인 작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */

@Controller
@RequestMapping("/login")
public class LoginProcessController{
	
	@Inject
	MemberServiceImpl memberService;
	
	@ModelAttribute("memberVO")
	public MemberVO getMemberVO() {
		return new MemberVO();
	}
	
	@GetMapping
	public String loginForm() {	
		return "common/login/loginForm";
	}	
}
