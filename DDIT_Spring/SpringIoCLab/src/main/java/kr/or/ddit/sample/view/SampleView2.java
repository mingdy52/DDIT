package kr.or.ddit.sample.view;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.or.ddit.sample.dao.SampleDAO;
import kr.or.ddit.sample.dao.SampleDAO_Oracle;
import kr.or.ddit.sample.service.SampleService;
import kr.or.ddit.sample.service.SampleServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SampleView2 {
	
	public static void main(String[] args) {
		
//		entry point 에서 컨테이너 객체 생성.
		ConfigurableApplicationContext context 
				= new GenericXmlApplicationContext("classpath:kr/or/ddit/sample/conf/sample-context2.xml");
				// 실행 시점에 설정 파일이 결정됌 : 동적 -> 제너릭. 자원을 찾기 위해 prefix 를 붙여줌.
				// classpath: 클래스 패스에서 설정파일을 찾음.
		SampleService service1 = context.getBean(SampleService.class);
		log.info("service1 : {}", service1);
		SampleService service2 = context.getBean(SampleService.class);
		log.info("service2 : {}", service2);
	}
	
}
