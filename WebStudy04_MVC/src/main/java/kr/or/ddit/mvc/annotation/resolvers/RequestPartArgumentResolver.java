package kr.or.ddit.mvc.annotation.resolvers;

import java.io.IOException;
import java.lang.reflect.Parameter;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.filter.multipart.MultipartFile;
import kr.or.ddit.filter.multipart.StandardMultipartHttpServletRequest;

public class RequestPartArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean isSupported(Parameter parameter) {
		RequestPart annotation = parameter.getAnnotation(RequestPart.class);
		Class<?> parameterType = parameter.getType();
		return annotation!=null && 
				(
				MultipartFile.class.equals(parameterType)
				|| (parameterType.isArray() && parameterType.getComponentType()/*배열의 엘리먼트 타입*/.equals(MultipartFile.class))
				);
	}

	@Override
	public Object argumentResolve(Parameter parameter, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestPart annotation = parameter.getAnnotation(RequestPart.class);
		Class<?> parameterType = parameter.getType();
		
		String requestPartName = annotation.value();
		boolean required = annotation.required();
		
		if(!(req instanceof StandardMultipartHttpServletRequest)) {
			throw new BadRequestException("멀티 파트 요청이 아님.");
		} 
		
		StandardMultipartHttpServletRequest wrapper = (StandardMultipartHttpServletRequest) req;
		List<MultipartFile> files = wrapper.getFiles(requestPartName);
		
		Object parameterValue = null;
		if(required && (files==null || files.isEmpty())) {
			throw new BadRequestException(String.format("%s 이름을 가진 필수 파트 누락", requestPartName));
//			resp.sendError(400);
//			return null;
		}else if(files==null || files.isEmpty()) {
			parameterValue = null;
		} else {
			if(parameterType.isArray()) {
				parameterValue = files.toArray(new MultipartFile[files.size()]);
			} else {
				parameterValue = files.get(0);
			}
		}
		
		return parameterValue;
	}
}
