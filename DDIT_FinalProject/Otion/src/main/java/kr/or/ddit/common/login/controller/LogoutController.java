package kr.or.ddit.common.login.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
/**
 * 
 * @author 서효림
 * @since 2022. 8. 5.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 5.       서효림          최초작성
 * 2022. 8. 12.      고정현         로그아웃 프로세스 적용
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/logout")
public class LogoutController {
	
	@GetMapping
	public String logout(
			HttpServletRequest request,
			RedirectAttributes redirectAttributes

			) {
		SecurityContextHolder.clearContext();
		Map<String, ?> fm = RequestContextUtils.getInputFlashMap(request);
		if(fm != null) {
			request.getSession().invalidate();
			redirectAttributes.addFlashAttribute("message", (String)fm.get("message"));
			log.info("*****{}",redirectAttributes.getFlashAttributes());
		}	        

		
		return "redirect:/";
	}
	
}
