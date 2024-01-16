package basic;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener, ServletContextAttributeListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("[ServletContextListener] 소멸자 호출됨.");
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("[ServletContextListener] 생성자 호출됨.");
		
	}

	@Override
	public void attributeAdded(ServletContextAttributeEvent event) {
		System.out.println("[ServletContextListener] 속성 추가됨 => " + event.getName());
		
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent event) {
		System.out.println("[ServletContextListener] 속성 삭제됨 => " + event.getName());
		
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent event) {
		System.out.println("[ServletContextListener] 속성 변경됨 => " + event.getName());
		
	}

}
