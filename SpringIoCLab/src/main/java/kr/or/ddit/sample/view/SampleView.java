package kr.or.ddit.sample.view;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import kr.or.ddit.sample.dao.SampleDAO;
import kr.or.ddit.sample.dao.SampleDAO_Oracle;
import kr.or.ddit.sample.service.SampleService;
import kr.or.ddit.sample.service.SampleServiceImpl;
import kr.or.ddit.sample.stereotype.ViewLayer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ViewLayer
public class SampleView {
	
	public void rendering(Map<String, Object> model) {
		StringBuffer info = (StringBuffer) model.get("info");
		log.info("모델 : {}", info);
	}
	
}
