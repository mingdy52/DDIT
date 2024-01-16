package basic;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MyHttpSessionListener implements HttpSessionAttributeListener, HttpSessionListener{

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		System.out.println("[MyHttpSessionListener]" 
				+ event.getName() + " 추가됨.");
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		System.out.println("[MyHttpSessionListener]" 
				+ event.getName() + " 삭제됨.");
		
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		System.out.println("[MyHttpSessionListener]" 
				+ event.getName() + " 대체됨.");
		
	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("[MyHttpSessionListener] 세션이 생성됨. 세션ID => " 
				+ se.getSession().getId());
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("[MyHttpSessionListener] 세션이 소멸됨. 세션ID => " 
				+ se.getSession().getId());
		
	}

}

