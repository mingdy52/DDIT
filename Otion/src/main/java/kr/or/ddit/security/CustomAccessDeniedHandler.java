package kr.or.ddit.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.util.UrlPathHelper;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
	 
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        //스프링 시큐리티 로그인때 만든 객체
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //현재 접속 url를 확인
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        String originalURL = urlPathHelper.getOriginatingRequestUri(request);
        System.out.println("원래 경로" + originalURL);
        System.out.println("체킁: " + e.getMessage()); /* 요게 있어야 메세지를 보아용 */
        //로직을 짜서 상황에 따라 보내줄 주소를 설정해주면 됨
        response.sendRedirect(request.getContextPath()+"/");
    }
}
