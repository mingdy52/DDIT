package kr.or.ddit.mvc.annotation;

import java.lang.reflect.Method;

/**
 * 핸들러가 처리할 수 있는 요청에 대한 정보(RequestMappingCondition)와
 * 해당 요청을 처리할 수 있는 핸들러에 대한 정보를 가진 객체
 *
 */
public class RequestMappingInfo {
	private RequestMappingCondition mappingCondition;
	private Object handler;
	private Method handlerMethod;
	public RequestMappingInfo(RequestMappingCondition mappingCondition, Object handler, Method handlerMethod) {
		super();
		this.mappingCondition = mappingCondition;
		this.handler = handler;
		this.handlerMethod = handlerMethod;
	}
	public RequestMappingCondition getMappingCondition() {
		return mappingCondition;
	}
	public Object getHandler() {
		return handler;
	}
	public Method getHandlerMethod() {
		return handlerMethod;
	}
	@Override
	public String toString() {
		return "핸들러(Controller) 정보 [handler=" + handler + ", handlerMethod=" + handlerMethod + "]";
	}
}






