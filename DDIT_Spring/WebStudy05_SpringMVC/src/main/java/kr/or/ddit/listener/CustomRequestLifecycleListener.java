package kr.or.ddit.listener;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

/**
 * Application Lifecycle Listener implementation class CustomRequestLifecycleListener
 *
 */
@WebListener
public class CustomRequestLifecycleListener implements ServletRequestListener {
	public static final String STARTARRT = "CustomRequestLifecycleListener.start";
	
	public void requestInitialized(ServletRequestEvent sre)  { 
		ServletRequest request = sre.getServletRequest();
		HttpServletRequest req = (HttpServletRequest) request;
		System.out.printf("%s 의 요청 발생\n", req.getRemoteAddr());
		long start = System.currentTimeMillis();
		req.setAttribute(STARTARRT, start /*자동으로 wrapping 해줌. 여기 타입이  Object 니까 오토 박싱*/);
//		req.setAttribute(STARTARRT, new Long(start));
	}
	
    public void requestDestroyed(ServletRequestEvent sre)  { 
    	ServletRequest request = sre.getServletRequest();
		HttpServletRequest req = (HttpServletRequest) request;
		System.out.printf("%s 의 요청 발생\n", req.getRemoteAddr());
		
		// 요청 처리 시간 계산하기.
		long end = System.currentTimeMillis();
		// 전역변수로 빼버리면 계산은 쉬어지지만 싱글턴이기 때문에 누구의 start인지 알 수 없음. 서블릿 스펙에서는 전역변수를 빼면 안돼
		
		long start = (long) req.getAttribute(STARTARRT); // 여기는 오토 언박싱, 자바 4버전이면 하나하나 캐스킹 해줘야함.
//		long start = ((Long) req.getAttribute(STARTARRT)).longValue();
		System.out.printf("소요시간 : %d\n", (end-start));
    }

}
