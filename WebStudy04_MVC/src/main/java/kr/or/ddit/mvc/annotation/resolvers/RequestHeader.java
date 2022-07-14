package kr.or.ddit.mvc.annotation.resolvers;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(PARAMETER)
@Retention(RUNTIME)
public @interface RequestHeader {
	String value() default ""; 
	String name() default ""; 
	boolean required() default true; 
	String defaultValue() default "";
}
