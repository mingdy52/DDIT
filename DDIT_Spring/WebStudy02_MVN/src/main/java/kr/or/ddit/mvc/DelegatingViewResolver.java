package kr.or.ddit.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DelegatingViewResolver implements ViewResolver {
	
	private ViewResolver defaultResolver;
	private ViewResolver[] resolvers;
	
	public DelegatingViewResolver() {
		this/*아래 생성자 생성할랭*/(new GridTemplateViewResolver());
	}

	public DelegatingViewResolver(ViewResolver... resolvers /*받은 파라미터로 뷰 리절브를 몇개든 만들겠따*/) {
		super();
		this.resolvers = resolvers;
		this.defaultResolver = new InternalResourceViewResolver();
		((InternalResourceViewResolver) defaultResolver).setPrefix("/WEB-INF/views/");
		((InternalResourceViewResolver) defaultResolver).setSuffix(".jsp");
	}
	
	private ViewResolver findViewResolver (String viewName) {
		ViewResolver findedResolver = defaultResolver;
		for(ViewResolver tmp : resolvers) {
			if (tmp.supported(viewName)) {
				// 배열이 인터널보다 그리드가 먼저 있어야 작동할 수 있음.
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
		} else {
			
			// forward 에서 사용.
			ViewResolver finded = findViewResolver(viewName);
			finded.viewResolve(viewName, request, response);
		}
		
		
	}

	@Override
	public boolean supported(String viewName) {
		return true;
	}

	
}
