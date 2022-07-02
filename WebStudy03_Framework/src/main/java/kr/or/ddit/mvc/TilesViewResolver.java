package kr.or.ddit.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TilesViewResolver implements ViewResolver {
	public static final String TILESSUFFIX = ".tiles";
	
	@Override
	public boolean supported(String viewName) {
		
		return viewName.endsWith(TILESSUFFIX);
	}

	@Override
	public void viewResolve(String viewName, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(viewName).forward(request, response);
	}

}
