package kr.or.ddit.mvc.annotation.resolvers;

import java.io.IOException;
import java.lang.reflect.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;

/**
 *  @CookieValue 어노테이션을 가진 파라미터가 기본형이고 wrapper 형태이며, 하나의 요청 쿠키 값을 받고 있는 경우, 사용할 전략.
 *
 */
public class ServletCookieValueArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean isSupported(Parameter parameter) {
		CookieValue annotation = parameter.getAnnotation(CookieValue.class);
		Class<?> parameterType = parameter.getType();
		return annotation!=null && (
									parameterType.equals(Cookie.class)
									|| String.class.equals(parameterType)
									|| ClassUtils.isPrimitiveOrWrapper(parameterType)
									);
	}

	@Override
	public Object argumentResolve(Parameter parameter, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		CookieValue annotation = parameter.getAnnotation(CookieValue.class);
		Class<?> parameterType = parameter.getType();
		String cookieName = annotation.value();
		boolean required = annotation.required();
		String defaultValue = annotation.defaultValue();
		
		Cookie cookie = getCookie(cookieName, req);
		if(required && cookie==null) {
			throw new BadRequestException(String.format("%s 이름을 가진 필수 쿠키 누락", cookieName));
//			resp.sendError(400);
//			return null;
		}else if(cookie == null) {
			cookie = new Cookie(cookieName, defaultValue);
		}
		
		Object parameterValue = null;
		if(Cookie.class.equals(parameterType)) {
			parameterValue = cookie;
		}else if(int.class.equals(parameterType) || Integer.class.equals(parameterType)) {
			parameterValue = Integer.parseInt(cookie.getValue());
		}else if(boolean.class.equals(parameterType) || Boolean.class.equals(parameterType)) {
			parameterValue = Boolean.parseBoolean(cookie.getValue());
		}else if(String.class.equals(parameterType)) {
			parameterValue = cookie.getValue();
		}
		return parameterValue;
	}

	private Cookie getCookie(String cookieName, HttpServletRequest req) {
		Cookie[] cookies = req.getCookies();
		Cookie finded = null;
		if(cookies!=null) {
			for(Cookie single : cookies) {
				if(cookieName.equals(single.getName())) {
					finded = single;
					break;
				}
			}
		}
		return finded;
	}

}
