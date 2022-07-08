package kr.or.ddit.mvc.annotation.resolvers;

import java.io.IOException;
import java.lang.reflect.Parameter;
import java.rmi.ServerException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ClassUtils;

/**
 * 핸들러 메소드의 파라미터 중 @ModelAttribute어노테이션을 가진 command object를 해결하기 위한 전략.
 * 
 * @author 306-25
 *
 */
public class ModelAttributeArgumentResolver implements HandlerMethodArgumentResolver {

   @Override
   public boolean isSupported(Parameter parameter) {
      // 모델어트리뷰트 어쩌구로 vo 판단
	   ModelAtrribute annotation = parameter.getAnnotation(ModelAtrribute.class);
      // 이게있으면 어노테이션 썻고 없으면 안쓴거
      Class<?> parameterType = parameter.getType();
      // 이거에따라 member vo 생성할지 prod vo 생성할지 달라짐
      return annotation != null && !ClassUtils.isPrimitiveOrWrapper(parameterType);// 기본형이면 안되니깐 not연산자 넣어야함. 기본형이나
                                                                  // 래퍼이면

   }

   @Override
   public Object argumentResolve(Parameter parameter, HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
	   ModelAtrribute annotation = parameter.getAnnotation(ModelAtrribute.class);
      Class<?> parameterType = parameter.getType();
      Object parameterValue;
      try {
         parameterValue = parameterType.newInstance();
         //모델 어노테이션에 이름을 숨겨놓음 어노테이션이 필요! 
         String attrName = annotation.value();
         request.setAttribute(attrName, parameterValue);
         BeanUtils.populate(parameterValue, request.getParameterMap());
         return parameterValue;
      } catch (Exception e) {
         throw new ServletException(e);
      } // 객체생성

   }


}