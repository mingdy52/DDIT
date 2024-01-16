package kr.or.ddit.sample.controller;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import kr.or.ddit.sample.service.SampleService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class SampleController {
	
	@Inject
	private ApplicationContext context;
	
	@PostConstruct // 모든 의존성 주입이 끝난 이후에 동작. 주입된 대상을 가지고 초기화 작업을 해야 할 때.
	public void init() {
		log.info("{} 객체 초기화", this.getClass());
		log.info("컨테이너 hashcode : {} , this.getClass()");
	}
	
	private SampleService service;
	
//	@Autowired
	@Inject
	public void setService(SampleService service) {
		this.service = service;
		log.info("주입된 객체 : {}, proxy 여부 : {}", service.getClass(), AopUtils.isAopProxy(service));
	}
	
	public void commandHandler(Map<String, Object> model, String code) {
		
		StringBuffer info = service.retreiveSampleData(code);
		
		// Model 객체 상위를 타고 올라가면 map 이 있음.
		model.put("info", info);
		
	}
	
}
