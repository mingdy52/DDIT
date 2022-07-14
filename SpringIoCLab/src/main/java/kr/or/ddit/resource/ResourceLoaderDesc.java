package kr.or.ddit.resource;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResourceLoaderDesc {
	
	public static void main(String[] args) throws URISyntaxException {
		
		ConfigurableApplicationContext context 
					= new ClassPathXmlApplicationContext();
//		File file = new File("d:/contents/a.png");
//		URL file2 = ResourceLoaderDesc.class.getResource("/log4j2.xml");
//		URI file3 = new URI("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png");
		// 자원을 사용하는 방식이 자원의 위치에 따라서 다 다름. 
		// 이 혼선을 해결하려면 자원의 위치와 무관하게 사용하는 방법이 같아야함. -> Resource
		
		Resource file1 = context.getResource("file:d:/contents/a.png");
		Resource file2 = context.getResource("classpath:/log4j2.xml");
		Resource file3 = context.getResource("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png");
		// 프로토콜이 prefix 역할을 해줌.
		
		log.info("file1 : {}", file1.getClass().getName());
		log.info("file2 : {}", file2.getClass().getName());
		log.info("file3 : {}", file3.getClass().getName());
		
	}
	
}
