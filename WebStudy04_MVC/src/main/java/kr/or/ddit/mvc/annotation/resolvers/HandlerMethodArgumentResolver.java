package kr.or.ddit.mvc.annotation.resolvers;

import java.io.IOException;
import java.lang.reflect.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 핸들러메소드의 인자(파라미터) 하나를 해결하기 위한 전략.
 *
 */
public interface HandlerMethodArgumentResolver {
	/**
	 * 현재 인자(파라미터) 를 처리할 수 있는지 여부
	 * @param parameter
	 * @return
	 */
	public boolean isSupported(Parameter parameter);
	
	public Object argumentResolve(Parameter parameter, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
