package kr.or.ddit.exception;

/**
 * 예외(Thowable)
 *  Error : 시스템 폴트 상황으로 개발자가 직접 처리하지 않는 예외
 *  Exception : 필요에 의해 개발자가 직접 처리할 수 있는 예외
 *  	Checked(Exception) : 예외가 발생하면 반드시 직접 처리해야 하는 예외(처리하지 않으면 컴파일 에러 발생)
 *  		ex) IOException, SQLException
 *  	UnChecked(RuntimeException) : 예외가 발생하고, 직접 처리하지 않더라도, 예외에 대한 제어권이 호출구조를 타고 VM에게 전달되는 예외.
 *  		ex) NullPointerException
 *
 */
public class PKNotFoundException extends RuntimeException {
	
	public PKNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PKNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public PKNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public PKNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public PKNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
