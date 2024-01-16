package kr.or.ddit.resource;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResourceTestView {
	
	public static void main(String[] args) {
		
		ConfigurableApplicationContext context = 
				new GenericXmlApplicationContext("classpath:kr/or/ddit/resource/conf/VariousDI-Context.xml");
		
		context.registerShutdownHook();
		VariousDI vo1 = context.getBean(VariousDI.class);
		log.info("vo1 : {}", vo1);
	}
	
}
