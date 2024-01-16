package kr.or.ddit.javaconfig;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.or.ddit.sample.dao.SampleDAO;
import kr.or.ddit.sample.service.SampleService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JavaConfigEntryPoint {
	
	public static void main(String[] args) {
		
		ConfigurableApplicationContext context = 
				new AnnotationConfigApplicationContext(JavaConfigForContainer.class);
		
//		SampleDAO dao1 = context.getBean(SampleDAO.class);
//		SampleDAO dao2 = context.getBean(SampleDAO.class);
//		
//		log.info("dao1==dao2 : {}", dao1==dao2);
		
		// 사용하지 않더라도 컨테이너가 초기화될 때 모든 객체가 생성된다.
		
		SampleService service = context.getBean(SampleService.class);
		log.info("info : {}", service.retreiveSampleData("a001"));
	}
	
}
