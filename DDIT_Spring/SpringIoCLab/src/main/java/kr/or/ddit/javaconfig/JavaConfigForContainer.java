package kr.or.ddit.javaconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import kr.or.ddit.sample.dao.SampleDAO;
import kr.or.ddit.sample.dao.SampleDAO_Maria;
import kr.or.ddit.sample.service.SampleService;
import kr.or.ddit.sample.service.SampleServiceImpl;
import kr.or.ddit.sample.stereotype.ViewLayer;

@Configuration
@Lazy
@ComponentScan(basePackages="kr.or.ddit.sample", useDefaultFilters=false
, includeFilters= {
		@Filter(Component.class)
}, excludeFilters= {
		@Filter(classes=Controller.class), @Filter(classes=ViewLayer.class)
})
public class JavaConfigForContainer {

	@Bean
//	@Scope("prototype")
	public SampleDAO sampleDAOMaria() {
		return new SampleDAO_Maria();
	}
	
//	@Bean("sampleService") // 아이디를 안줄경우 메소드명이 아이디
	public SampleService sampleService(SampleDAO dao) {
		return new SampleServiceImpl(dao);
	}
	
}
