package kr.or.ddit.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.annotation.HandlerAdapter;
import kr.or.ddit.mvc.annotation.HandlerMapping;
import kr.or.ddit.mvc.annotation.RequestMappingHandlerAdapter;
import kr.or.ddit.mvc.annotation.RequestMappingHandlerMapping;
import kr.or.ddit.mvc.annotation.RequestMappingInfo;
import kr.or.ddit.mvc.annotation.resolvers.BadRequestException;

/**
 * Front Controller Pattern 을 적용하고, 모든 요청에 대한 사전 처리를 담당.
 * /member/memberList.do
 * /member/memberInsert.do
 */
public class FrontController extends HttpServlet{
	private HandlerMapping handlerMapping;
	private HandlerAdapter handlerAdapter;
	private ViewResolver viewResolver;
	
	@Override
	public void init() throws ServletException {
		super.init();
		handlerMapping = new RequestMappingHandlerMapping("kr.or.ddit");
		handlerAdapter = new RequestMappingHandlerAdapter();
		viewResolver = new DelegatingViewResolver();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestMappingInfo mappingInfo = handlerMapping.findCommandHandler(req);
		int sc = 200;
		String message = null;
		if(mappingInfo!=null) {
			try {
				String viewName = handlerAdapter.invokeHandler(mappingInfo, req, resp);
				if(viewName!=null) {
					viewResolver.viewResolve(viewName, req, resp);
				}else {
					if(!resp.isCommitted()) {
						sc = 500;
						message = "요청을 처리하는 과정에서 문제가 발생했습니다.(LVN이 없습니다.)";							
					}
				}
			}catch (BadRequestException e) {
				sc = 400;
				message = e.getMessage();
			}	
		}else {
			sc = 404;
			message = "현재 요청을 제공하지 않는 서비스입니다.";
		}
		if(sc!=200) {
			resp.sendError(sc, message);
		}
	}
}











