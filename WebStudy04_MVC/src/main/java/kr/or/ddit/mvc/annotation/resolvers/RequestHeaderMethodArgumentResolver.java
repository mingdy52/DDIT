package kr.or.ddit.mvc.annotation.resolvers;

import java.io.IOException;
import java.lang.reflect.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;

public class RequestHeaderMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean isSupported(Parameter parameter) {
		RequestHeader annotation = parameter.getAnnotation(RequestHeader.class);
		Class<?> parameterType = parameter.getType();
		return annotation!=null && (
									ClassUtils.isPrimitiveOrWrapper(parameterType)
									|| String.class.equals(parameterType)
									);
	}

	@Override
	public Object argumentResolve(Parameter parameter, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestHeader annotation = parameter.getAnnotation(RequestHeader.class);
		Class<?> parameterType = parameter.getType();
		
		String requestHeaderName = annotation.name();
		if(requestHeaderName.isEmpty()) {
			requestHeaderName = annotation.value();
			
		}
		
		boolean required = annotation.required();
		String defaultValue = annotation.defaultValue();
		
		String requestHeader = req.getHeader(requestHeaderName);
		if(required && StringUtils.isBlank(requestHeader)) {
			throw new BadRequestException(String.format("%s 이름을 가진 필수 헤더 누락", requestHeaderName));
		}else if(StringUtils.isBlank(requestHeader)) {
			requestHeader = defaultValue;
		}
		Object parameterValue = null;
		if(int.class.equals(parameterType) || Integer.class.equals(parameterType)) {
			parameterValue = Integer.parseInt(requestHeader);
		}else if(boolean.class.equals(parameterType) || Boolean.class.equals(parameterType)) {
			parameterValue = Boolean.parseBoolean(requestHeader);
		}else if(String.class.equals(parameterType)) {
			parameterValue = requestHeader;
		}
		return parameterValue;
	}
}
