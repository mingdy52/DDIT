package kr.or.ddit.common.login.service;


import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.common.login.dao.LoginDAO;
import kr.or.ddit.common.login.vo.MemberPrincipal;
import kr.or.ddit.common.vo.MemberVO;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberServiceImpl implements UserDetailsService {
		
	@Inject
	private LoginDAO login;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String blackId = login.blackView(username);
		
		if(StringUtils.isNotBlank(blackId) && blackId.equals(username)) {
			throw new UsernameNotFoundException(username + "님은 신고누적으로 블랙리스트 입니다.");
		}else {
			MemberVO member = login.login(username);
			log.info("*************{} ", member);
			if(member == null) {
				throw new UsernameNotFoundException(username + "해당유저 없음");
			}
			return new MemberPrincipal(member);
		}
		
	}
	
}

