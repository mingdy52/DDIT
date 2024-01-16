package kr.or.ddit.validate;

import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;

import kr.or.ddit.vo.MemberVO;

public class ValidateUtils {
	static Validator validator;
//	1. 값에 대한 검증
//	2. 프로퍼티 전체에 대한 검증
	static {

		validator = Validation.byDefaultProvider()
				.configure()
			        .messageInterpolator(
			                new ResourceBundleMessageInterpolator(
			                        new PlatformResourceBundleLocator( "kr.or.ddit.msgs.errorMessage" )
			                )
			        )
			        .buildValidatorFactory()
			        .getValidator();
	}
	
	public static <T> boolean validate(T target, Map<String, String> errors, Class...groups) {
//		메소드 제네릭. 객체가 생성되지 않더라도 레퍼런스 구조 전달
		Set<ConstraintViolation<T>> viloations = validator.validate(target, groups);
		boolean valid = viloations == null || viloations.size()==0;
		for(ConstraintViolation<T> singleViolation : viloations) {
			String property = singleViolation.getPropertyPath().toString();
			String message = singleViolation.getMessage();
			 errors.put(property,message);
			
		}
		return valid;
	}
	
}
