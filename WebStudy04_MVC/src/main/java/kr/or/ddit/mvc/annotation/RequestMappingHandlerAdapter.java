package kr.or.ddit.mvc.annotation;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.annotation.resolvers.HandlerMethodArgumentResolver;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribteArgumentResolver;
import kr.or.ddit.mvc.annotation.resolvers.RequestParamArgumentResolver;
import kr.or.ddit.mvc.annotation.resolvers.ServletSpecArgumentResolver;

public class RequestMappingHandlerAdapter implements HandlerAdapter {
	private List<HandlerMethodArgumentResolver> argumentResolvers;
	{
		argumentResolvers = new ArrayList<>();
		argumentResolvers.add(new ServletSpecArgumentResolver());
		argumentResolvers.add(new ModelAttribteArgumentResolver());
		argumentResolvers.add(new RequestParamArgumentResolver());
	}
	
	private HandlerMethodArgumentResolver findHandlerArgumentResolver(Parameter parameter) {
		HandlerMethodArgumentResolver finded = null;
		for(HandlerMethodArgumentResolver resolver : argumentResolvers) {
			if(resolver.isSupported(parameter)) {
				finded = resolver;
				break;
			}
		}
		return finded;
	}
	

	@Override
	public String invokeHandler(RequestMappingInfo mappingInfo, HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		Object handler = mappingInfo.getHandler();
		Method handlerMethod = mappingInfo.getHandlerMethod();
//		new MemberListController().memberList(req, resp);
		int parameterCount = handlerMethod.getParameterCount();
		try {
			String viewName = null;
			if(parameterCount > 0) {
				Parameter[] parameters = handlerMethod.getParameters();
				Object[] parameterValues = new Object[parameters.length];
				for(int i=0; i<parameters.length; i++) {
					Parameter singleParameter = parameters[i];
					HandlerMethodArgumentResolver resolver = findHandlerArgumentResolver(singleParameter);
					if(resolver==null) {
						throw new ServletException(String.format("%s 를 처리할 수 있는 argument resolver 가 없음.", singleParameter));
					}
					Object singleValue = resolver.argumentResolve(singleParameter, req, resp);
					parameterValues[i] = singleValue;
				}
				viewName = (String) handlerMethod.invoke(handler, parameterValues);
			}else {
				viewName = (String) handlerMethod.invoke(handler);
			}
			return viewName;
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new ServletException(e);
		}
	}

}













