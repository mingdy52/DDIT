package kr.or.ddit.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DelegatingViewResolver implements ViewResolver{
	private ViewResolver defaultResolver;
	private ViewResolver[] resolvers;

	public DelegatingViewResolver() {
		this(new TilesViewResolver());
	}

	public DelegatingViewResolver(ViewResolver...resolvers) {
		super();
		this.resolvers = resolvers;
		this.defaultResolver = new InternalResourceViewResolver();
		((InternalResourceViewResolver)defaultResolver).setPrefix("/WEB-INF/views/");
		((InternalResourceViewResolver)defaultResolver).setSuffix(".jsp");
	}
	
	@Override
	public boolean supported(String viewName) {
		return true;
	}
	
	private ViewResolver findViewResolver(String viewName) {
		ViewResolver findedResolver = defaultResolver;
		for(ViewResolver tmp :resolvers) {
			if(tmp.supported(viewName)) {
				findedResolver = tmp;
			}
		}
		return findedResolver;
	}
	
	@Override
	public void viewResolve(String viewName, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(viewName.startsWith("redirect:")) {
			viewName = viewName.substring("redirect:".length());
			response.sendRedirect(request.getContextPath() + viewName);
		}else if(viewName.startsWith("forward:")) {
//			forward:/login/logout.do
			viewName = viewName.substring("forward:".length());
			request.getRequestDispatcher(viewName).forward(request, response);
		}else {
			// forward 에서 사용.
			ViewResolver finded = findViewResolver(viewName);
			finded.viewResolve(viewName, request, response);
		}
		
	}
}
















