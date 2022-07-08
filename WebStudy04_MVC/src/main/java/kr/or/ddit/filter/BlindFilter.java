package kr.or.ddit.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 블라인드 처리의 대상이 되는 사용자가 접속한 경우,
 * 해당 요청에 대해 직접 처리하지 않고, 접근 불가 메시지 페이지로 연결함.
 *
 */
// @WebFilter("/*") // 필터를 등록하고 맵핑하는 과정을 이걸로 끝냄. 근데 쓰지 마라. 필터 체인 순서를 장담할 수 없다.
@Slf4j
public class BlindFilter implements Filter {

	private Map<String, String> blindTarget;
	
	public void init(FilterConfig fConfig) throws ServletException {
		blindTarget = new LinkedHashMap<String, String>();
		//IP, reason
		blindTarget.put("192.168.36.30","나임.");
		blindTarget.put("192.168.36.21","아인이니까 블라인드.");
		blindTarget.put("192.168.36.31","효림이니까 블라인드.");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		
		//		1. 현wo 클라이언트의 아이피 잡기
		String ip = request.getRemoteAddr();
		String uri = req.getRequestURI();
		boolean pass = uri.endsWith("/errors/blindError.jsp");
		
//		2. 블라인드 타겟에 포함되는지 검증함.
		boolean blind = !pass && blindTarget.containsKey(ip);
		if(blind) {
			// 대상자
			String reason = blindTarget.get(ip);
			Map<String, String> target = new HashMap<String, String>();
			target.put("IP", ip);
			target.put("reason", reason);
			req.getSession().setAttribute("Target", target);
			String goPage = "/errors/blindError.jsp";
			((HttpServletResponse) response).sendRedirect(req.getContextPath() + goPage);
			
			
		} else {
			// 비대상자
			chain.doFilter(request, response); 
			// 다음 필터에게 제어권을 넘기거나, 필터가 끝났다면 컨트롤러에 제어권을 넘김.
			// 위 타켓에 포함되지 확인함.
		}
		
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}
}
