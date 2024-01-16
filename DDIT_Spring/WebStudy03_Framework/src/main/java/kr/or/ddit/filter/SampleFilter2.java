package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SampleFilter2 implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("{} 필터 초기화", this.getClass().getName());
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String uri = ((HttpServletRequest) request).getRequestURI();
		log.info("{} 에서 {} 요청을 필터링", this.getClass().getSimpleName(), uri);
		chain.doFilter(request, response);
		log.info("{} 에서 {} 요청을 필터링", this.getClass().getSimpleName(), response.getContentType());
		
	}

	@Override
	public void destroy() {
		log.info("{} 필터 소멸", this.getClass().getName());
		
	}

}
