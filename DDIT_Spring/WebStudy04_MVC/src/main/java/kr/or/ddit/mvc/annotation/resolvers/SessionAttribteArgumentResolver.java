package kr.or.ddit.mvc.annotation.resolvers;

import java.io.IOException;
import java.lang.reflect.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ClassUtils;


public class SessionAttribteArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean isSupported(Parameter parameter) {
		SessionAttribute annotation = parameter.getAnnotation(SessionAttribute.class);
		Class<?> parameterType = parameter.getType();
		return annotation!=null
				&& !parameterType.isPrimitive(); // 기본형 데이터는 사용할 수 없음. 세션은 없으면 null 이니까.
				// && !ClassUtils.isPrimitiveOrWrapper(parameterType) -- 세션안에 데이터가 있을 수 도 있으니까 빼는게 맞음.;
	}

	@Override
	public Object argumentResolve(Parameter parameter, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Class<?> parameterType = parameter.getType();
		Object parameterValue;
	//	parameterValue = parameterType.newInstance(); // 세션은 없으면 null을 반환. 
		SessionAttribute annotation = parameter.getAnnotation(SessionAttribute.class);
		String attrName = annotation.name();
		if(attrName.isEmpty()) {
			attrName = annotation.value();
		}
		
		Object attribute = req.getSession().getAttribute(attrName);
		if(annotation.required() && attribute == null) {
			throw new BadRequestException("세션 영역에 해당 속성이 없음.");
		}
		
		return attribute;
	}
}

