package kr.or.ddit.filter;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemberVOWrapper;

/**
 * 
 * 이미 인증된 사용자의 Principal(인증객체)를 가진 원본요청을 Wrapping 하고 있는 요청 객체를 생성하기 위한 필터.
 *
 */
public class MakePrincipalFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	   @Override
	   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	         throws IOException, ServletException {
	      HttpServletRequest req = (HttpServletRequest)request;
	      MemberVO authMember = (MemberVO)req.getSession().getAttribute("authMember");
	      // 익명 클래스의 상속 구조(로그인이 되어있을 때 필요한 것)
	      if(authMember != null) {
	         HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(req) {
	            @Override
	            public Principal getUserPrincipal() {
	               return new MemberVOWrapper(authMember);
	            }
	         };
	         chain.doFilter(wrapper, response);
	      }else {
	         chain.doFilter(request, response);
	      }
	      
	   }

		
	@Override
	public void destroy() {
		
	}

}
