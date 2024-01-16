package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SampleFilter implements Filter {
//	이 메소드는 콜백 메소드임. was 가 호출하는 메소드 
	
//	private static final Logger log = LoggerFactory.getLogger(SampleFilter.class);
	// 로깅 프레임 워크를 쓸 때 이 코드는 반드시 들어감. -> 롬북이 이걸 가지고 있음.
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// 필터 객체가 생성되면 제일 먼저 돌아감
		log.info("{} 필터 초기화", this.getClass().getName());
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req =  (HttpServletRequest) request;
		// 어디까지가 요청이고 어디까지가 응답인지 그 경계선을 알려줘야 맞춰서 처리해줄 수 있음. -> FilterChain
		String uri = req.getRequestURI();
		log.info("{} 요청 필터링", uri);
		
		chain.doFilter(request, response); // 다음순서를 가진 필터로 그 제어권을 넘김. 마지막 체인에서 doFilter 사용 후 컨트롤러로 넘어감.
		
		log.info("{} 응답 필터링", response.getContentType());
	}

	@Override
	public void destroy() {
		// 필터 객체가 소멸되기 직전에 돌아감
		log.info("{} 필터 소멸", this.getClass().getName());
		
	}
	
}
