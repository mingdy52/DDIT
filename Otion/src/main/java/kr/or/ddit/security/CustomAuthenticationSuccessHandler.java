package kr.or.ddit.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import kr.or.ddit.common.login.vo.MemberPrincipal;
import kr.or.ddit.common.vo.MemberVO;

@Component
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler

 {

    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//    	SecurityContext securityContext = new SecurityContextImpl();
//    	securityContext.setAuthentication(authentication);
//    	System.out.println("시큐리트 컨텍스트 " + securityContext);
//    	SecurityContextHolder.setContext(securityContext);
//    	System.out.println("시큐리트 컨텍스트 홀더 " + SecurityContextHolder.getContext());
    	
//    	MemberPrincipal userPrincipal = (MemberPrincipal) authentication.getPrincipal();
//    	System.out.println("유저 프린시퍼" + userPrincipal);
//    	System.out.println("인증객체" + authentication);
//    	System.out.println("bean에 등록된 것 : " + SecurityContextHolder.getContext().getAuthentication());
//    	
//		MemberVO realUser = userPrincipal.getRealMember();
//		
//		
//		HttpSession session = request.getSession();
//		session.setMaxInactiveInterval(60*60);
//		session.setAttribute("LOGIN_USER", realUser);
    	super.onAuthenticationSuccess(request, response, authentication);
    	
//        setDefaultTargetUrl("/");
//        SavedRequest savedRequest = requestCache.getRequest(request,response);
//        if(savedRequest != null){
//            // 인증 받기 전 url로 이동하기
//            redirectStrategy.sendRedirect(request,response,"/");
//        }
    }
}