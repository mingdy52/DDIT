package kr.or.ddit.advice;

import java.util.Date;

import org.apache.logging.log4j.core.appender.SyslogAppender;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;

import lombok.extern.slf4j.Slf4j;

/**
 * 모든 비즈니스 로직에 대해 시스템 로그(실행 시점, 전달된 인자, 소요시간)를 출력
 * 1. 관심사의 분리
 * 	core concern : 모든 비즈니스 로직 -> target(Business logic layer)
 * 	cross cutting concern : 시스템 로그 출력 -> advice(LoggingAdvice)
 * 
 * 	advice 를 기준으로 weaving 할 대상 target 은 pointcut 을 통해 선택. -> runtime joinpoint
 *
 */

@Slf4j
public class LoggingAdvice {
	
	public void beforeAdvice(JoinPoint joinPoint/*타겟의 모든 정보를 가지고 있음.*/) { // 타겟 메소드 호출 전 위빙
		Object target = joinPoint.getTarget();
		String targetName = target.getClass().getSimpleName();
		Signature signature = joinPoint.getSignature();
		String targetMethodName = signature.getName();
		Object[] args = joinPoint.getArgs();
		
		log.info("{}.{}({}) 호출됨-{}", targetName, targetMethodName, args, new Date());
	}
	
	public void afterReturnAdvice(JoinPoint joinPoint, Object retValue) { // 타겟 메소드 호출 후 위빙 - 만들어진 모델 확인. 리턴값이 있을 때
		Object target = joinPoint.getTarget();
		String targetName = target.getClass().getSimpleName();
		Signature signature = joinPoint.getSignature();
		String targetMethodName = signature.getName();
		Object[] args = joinPoint.getArgs();
		
		log.info("{}.{}({}) 호출 후 반환값 : {}", targetName, targetMethodName, args, retValue);
	}
	
	public void afterThrowAdvice(JoinPoint joinPoint, Throwable e) { // 타겟 메소드에서 예외가 던져질 때
		Object target = joinPoint.getTarget();
		String targetName = target.getClass().getSimpleName();
		Signature signature = joinPoint.getSignature();
		String targetMethodName = signature.getName();
		Object[] args = joinPoint.getArgs();
		
		log.error(String.format("%s.%s(%s) 호출 중 예외 발생", targetName, targetMethodName, args), e);
		
	}
	
	public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		Object target = joinPoint.getTarget();
		String targetName = target.getClass().getSimpleName();
		Signature signature = joinPoint.getSignature();
		String targetMethodName = signature.getName();
		Object[] args = joinPoint.getArgs();
		
		log.info("{}.{}({}) 호출됨-{}", targetName, targetMethodName, args, new Date());
		try {
			long start = System.currentTimeMillis();
			Object retValue = joinPoint.proceed(args); // 타겟 메소드 호출
			long end = System.currentTimeMillis();
			log.info("{}.{}({}) 호출 후 반환값 : {}, 소요시간 : {}ms", targetName, targetMethodName, args, retValue, (end-start));
			return retValue;
		} catch (Throwable e) {
			log.error(String.format("%s.%s(%s) 호출 중 예외 발생", targetName, targetMethodName, args), e);
			throw e;
		} 
	}
	
}
