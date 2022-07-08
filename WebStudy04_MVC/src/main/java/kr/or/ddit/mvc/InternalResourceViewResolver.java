package kr.or.ddit.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 하나의 JSP 로 응답 페이지를 구성하기 위한 전략 객체
 *
 */
public class InternalResourceViewResolver implements ViewResolver {

	private String prefix;
	private String suffix;
	
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	@Override
	public boolean supported(String viewName) {
		return true;
	}
	
	@Override
	public void viewResolve(String viewName, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String resolveName = prefix+viewName+suffix;
		request.getRequestDispatcher(resolveName).forward(request, response);

	}

}






