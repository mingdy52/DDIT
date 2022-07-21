package kr.or.ddit.validate.contraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 
 * 어노테이션 : 사람과 시스템에 동시에 정보를 전달하기 위한 주석의 한 형태.
 * 		커스텀 어노테이션의 필수 정책
 * 		1. @Targer : 어노테이션의 사용 위치 설정.
 * 		2. @Retention : 어노테이션의 사용(생존) 범위 설정.(SOURCE, CLASS, RUNTIME)
 * 
 * 		어노테이션의 3가지 사용형태
 * 		1. Marker annotation 		
 * 			ex) @TelNumber - 필수 속성이 없는 어노테이션
 * 		2. Single value annotation	
 * 			ex) @TelNumber("text") - value 라는 이름을 가진 속성에 한해 사용되는 형태
 * 		3. Multi value annotation	
 * 			ex) @TelNumber(value="text", message="text") - value 이외의 모든 속성은 이름이 반드시 필요함.
 *
 */
@Target(ElementType.FIELD)// 어노테이션을 어디에 사용할거냐~~
//@Retention(RetentionPolicy.SOURCE) // 사람한테만 정보 전달
@Retention(RetentionPolicy.RUNTIME) 
// 컴파일러에 의해 기록. 런타임시 무시. 메타 어노테이션을 이용해서 행위를 완료하려면 런타임시까지 살아있어야함.
//@Retention(RetentionPolicy.CLASS) // 컴파일러에 의해 기록. 런타임시 무시 안당함.
@Constraint(validatedBy=TelNumberValidator.class)// @Telnumber 을 사용하면 검증은 TelNumberValidator 여기가 할거야.
public @interface TelNumber {
	String value() default "\\d{2,3}-\\d{3,4}-\\d{4}";
	
    String message() default "{kr.or.ddit.validate.contraints.TelNumber.message}"; 
    // 메시지 속성이 지정되지 않으면 기본값으로 뒤에 값을 적용한다.
    // default 는 필수 속성. default 유무에 따라 필수 속성이냐 아니냐가 갈림.

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
