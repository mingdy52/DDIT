package kr.or.ddit.filter.multipart;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 
 * multipart 원본 요청을 MultipartFile 객체들을 가진 Request Wrapper 로 변경하기 위한 필터
 *
 */
public class MultipartFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String encType = request.getContentType();
		if(encType != null && encType.startsWith("multipart/")) {
			StandardMultipartHttpServletRequest wrapper = 
					new StandardMultipartHttpServletRequest((HttpServletRequest)request);
			chain.doFilter(wrapper, response);
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		
	}

}
