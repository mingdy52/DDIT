package kr.or.ddit.listener;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomEventListener {
	
	@EventListener(classes=ContextRefreshedEvent.class)
	public void firstListener(ContextRefreshedEvent event) {
		WebApplicationContext container = (WebApplicationContext) event.getApplicationContext();
		ServletContext application = container.getServletContext();
		application.setAttribute("cPath", application.getContextPath());
		
		log.info("컨테이너 초기화 완료=============================");
	}
	
}
