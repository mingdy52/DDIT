package kr.or.ddit.mvc.annotation.resolvers;

/**
 * 응답 상태 코드 400 에러를 발생시키기 위한 예외
 *
 */
public class BadRequestException extends RuntimeException{

	public BadRequestException(String message) {
		super(message);
	}
	
}
