package kr.or.ddit.mvc.annotation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * HandlerMapping에 의해 검색된 현재 요청의 핸들러를 직접 호출하는 역할 수행.
 *
 */
public interface HandlerAdapter {
	/**
	 * 요청 처리 핸들러 호출.
	 * @param mappingInfo
	 * @param req
	 * @param resp
	 * @return 논리적인 뷰 네임.
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public String invokeHandler(RequestMappingInfo mappingInfo, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
