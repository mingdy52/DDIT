package kr.or.ddit.sample.view;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.sample.dao.SampleDAO;
import kr.or.ddit.sample.dao.SampleDAO_Oracle;
import kr.or.ddit.sample.service.SampleService;
import kr.or.ddit.sample.service.SampleServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SampleView {
	
	public static void main(String[] args) {
//		SampleDAO sampleDAO = new SampleDAO_Oracle();
//		SampleService service = new SampleServiceImpl(sampleDAO);
		
		ConfigurableApplicationContext container = new ClassPathXmlApplicationContext("kr/or/ddit/sample/conf/sample-context2.xml");
//		container.registerShutdownHook(); 
		SampleDAO oracleDB = container.getBean("sampleDAO_Oracle", SampleDAO_Oracle.class);
		
//		SampleService service1 = container.getBean(SampleService.class);
//		SampleService service2 = container.getBean(SampleService.class);
//		String code = "b001";
//		StringBuffer info = service.retreiveSampleData(code);
//		log.info("code : {}, information : {}", code, info);
		
//		log.info("service1 == service2 : {}", service1==service2);
		// 여기서 true 가 나오면 같은 객체인 것. 같은 객체
		
//		SampleDAO maria1 = container.getBean("sampleDAO_Maria", SampleDAO.class);
//		SampleDAO maria2 = container.getBean("sampleDAO_Maria_other", SampleDAO.class);
//		log.info("maria1 == maria2 : {}", maria1==maria2);
		// 이 둘은 다른 레퍼런스 주소를 가지고 있는 다른 객체임.
		
	}
	
}
