package kr.or.ddit.filter.auth;

import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.Map;

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
import kr.or.ddit.vo.MemberVOWrapper;

/**
 * 
 * 보호자원을 요청하고 있는 인증된 사용자의 인가 여부 확인.
 *
 */
public class AuthorizationFilter implements Filter {
	// 앞 단에서 프로퍼티스를 읽었으니까 여기선 읽어올 필요 없음.
	private ServletContext application;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		application = filterConfig.getServletContext();
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Map<String, String[]> securedResources = (Map<String, String[]>) 
				application.getAttribute(AuthenticateFilter.SECUREDRESOURCENAME);
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
//		1. 보호자원 여부
		String uri = req.getRequestURI();
		uri = uri.substring(req.getContextPath().length());
//		/member/memberList.do;jsessionid=asdfasdf -- 매트릭스 스트링
		uri = uri.split(";")[0];
		
		String[] values = securedResources.get(uri);
		boolean secureFlag = securedResources.containsKey(uri);
		boolean pass = false;
		if(secureFlag) {
//			2-1. 보호자원
				MemberVOWrapper wrapperPrincipal = (MemberVOWrapper) req.getUserPrincipal();
				MemberVO authMember = wrapperPrincipal.getRealMember();
				
				String role = authMember.getMemRole();
				String[] roles = securedResources.get(uri);
				
//				3. 인증객체 롤과 자원의 허가목록 을 비교
				int searched = Arrays.binarySearch(roles, role);
				// 이진탐색을 하려면 반드시 정렬이 되어 있어야함 -> AuthenticateFilter
				
				
				if(searched < 0) {
//					4. 불통 -> 400 권한없음.
					pass = false;
					
				} else {
//					5. 인증객체 허가목록 포함관계 -> 통과
					pass = true;
				}
			
			
		} else {
//			2-2. 비보호자원
			pass = true;
		}
		
		if(pass) {
			chain.doFilter(request, response);
		} else {
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "권한 없음"); 
			// 인가 받지 않은 클라이언트에 대한 상태 코드 . SC_FORBIDDEN(403)을 쓰기도함.
		}
	}
		
		

			

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
