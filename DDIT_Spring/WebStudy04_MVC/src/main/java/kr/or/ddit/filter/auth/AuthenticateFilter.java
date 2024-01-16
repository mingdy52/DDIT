package kr.or.ddit.filter.auth;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.security.Principal;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.vo.MemberVO;

/**
 * 
 * 보안 : Authentication(인증, 신원확인) + Authorization(인가, 권한확인)
 * 
 * 보호자원을 접근하려는 사용자가 인증된 사용자인지 확인.
 *
 */
public class AuthenticateFilter implements Filter {
	
	private Map<String, String[]> securedResources;
	private ServletContext application;
	public static final String SECUREDRESOURCENAME="securedResources";
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// 보호자원 정보 로딩.
		String location = "/kr/or/ddit/SecuredResources.properties";
		try(
				InputStream is = this.getClass().getResourceAsStream(location);
				// inputStream 프로퍼티스의 객체를 사용하겠다. 
		) {
			Properties props = new Properties();
			props.load(is);
			securedResources = new LinkedHashMap<>();
			application = filterConfig.getServletContext();
			application.setAttribute(SECUREDRESOURCENAME, securedResources);
			
			for (Entry<Object, Object> entry : props.entrySet()) {
				
				String uri = entry.getKey().toString();
				String values = entry.getValue().toString();
				String[] roles = values.split(",");
				Arrays.sort(roles); // roles 를 compareTo 를 이용해서 정렬
				securedResources.put(uri.trim(), roles);
				
			}
			
		} catch (IOException e) {
			throw new RuntimeException(e); 
		}
		
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
//		1. 사용자의 요청 자원이 보호 자원인지 여부 확인
		String uri = req.getRequestURI();
		uri = uri.substring(req.getContextPath().length());
//		/member/memberList.do;jsessionid=asdfasdf -- 매트릭스 스트링
		uri = uri.split(";")[0];
		
		boolean secureFlag = securedResources.containsKey(uri);
		boolean pass = false;
		if(secureFlag) {
			Principal principal = req.getUserPrincipal();
			//	2-1. 보호자원
			//		3. 사용자의 인증 여부확인
			if(principal != null) {
				//		4-1. 인증 : 통과
				pass = true;
			} else {
				//		4-2. 미인증 : 로그인폼으로 이동
				pass = false;
				String view = req.getContextPath() + "/login/loginForm.jsp";
				resp.sendRedirect(view); // 잘못된 요청을 남길 필요 없음.
				
			}
			
		} else {
//		2-2. 비보호자원 : 그냥 통과
			pass = true;
		}
		
		
		if(pass) {
			chain.doFilter(request, response);
			
		} else {
			
		}
			
	}

	@Override
	public void destroy() {
		
		
	}
	
	
}
