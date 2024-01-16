package kr.or.ddit.mvc.annotation;

import javax.servlet.http.HttpServletRequest;

/**
 * 1. 어노테이션을 tracing 하고, 해당 정보를 수집하여, Map 형성.
 * 2. 수집된  정보를 가진 Map 을 기반으로, 하나의 요청을 처리할 수 있는 command handler(backend controller)를 검색.
 */
public interface HandlerMapping {
	/**
	 * 현재 요청을 처리할 수 있는 커맨드 핸들러 검색.
	 * @param req
	 * @return
	 */
	public RequestMappingInfo findCommandHandler(HttpServletRequest req);
}
