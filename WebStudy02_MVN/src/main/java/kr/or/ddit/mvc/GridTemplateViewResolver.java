package kr.or.ddit.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * logicalViewName 이 ".grid" 라는 접미어를 가지고 있을 때 동작.
 *
 */
public class GridTemplateViewResolver implements ViewResolver {
	
	public static final String GRIDSUFFIX = ".grid";
	
	@Override
	public boolean supported(String viewName) {
		
		return viewName.endsWith(GRIDSUFFIX);
	}

	@Override
	public void viewResolve(String viewName, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idx = viewName.lastIndexOf(GRIDSUFFIX);
		viewName = viewName.substring(0, idx);
		
		request.setAttribute("contents", viewName);
		String view = "/WEB-INF/views/template.jsp";
		request.getRequestDispatcher(view).forward(request, response);

	}


}
