package kr.or.ddit.mvc.annotation.resolvers;

import java.io.IOException;
import java.lang.reflect.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ClassUtils;

/**
 * 핸들러 메소드의 파라미터 중 @ModelAttribute 어노테이션을 가진 command object 를 해결하기 위한 전략.
 *
 */
public class ModelAttribteArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean isSupported(Parameter parameter) {
		ModelAttribute annotation = parameter.getAnnotation(ModelAttribute.class);
		Class<?> parameterType = parameter.getType();
		return annotation!=null && !ClassUtils.isPrimitiveOrWrapper(parameterType);
	}

	@Override
	public Object argumentResolve(Parameter parameter, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ModelAttribute annotation = parameter.getAnnotation(ModelAttribute.class);
		Class<?> parameterType = parameter.getType();
		Object parameterValue;
		try {
			parameterValue = parameterType.newInstance();
			String attrName = annotation.value();
			req.setAttribute(attrName, parameterValue);
			
			BeanUtils.populate(parameterValue, req.getParameterMap());
				
			return parameterValue;
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}

