package kr.or.ddit.sample.service;


import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Qualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import kr.or.ddit.sample.dao.SampleDAO;
import kr.or.ddit.sample.dao.SampleDAOFactory;
import kr.or.ddit.sample.dao.SampleDAO_Maria;
import kr.or.ddit.sample.dao.SampleDAO_Oracle;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SampleServiceImpl implements SampleService {
	
	@Inject // 생성자랑 세터 없지만 스프링에서는 가능.
	private ApplicationContext context;
	
//	1. new instance 를 직접 생성하고, 의존관계 형성. - 결합력 최상.
//	private SampleDAO sampleDAO = new SampleDAO_Oracle();
//	private SampleDAO sampleDAO = new SampleDAO_Maria();
	// 디비가 바뀌는 서비스도 바꿔줘야 함. -> 구현체에 대한 종속성을 없애야 한다.
	
//	2. Factory Object Pattern : 결합력 잔존(공객객체와 서비스)
//	private SampleDAO sampleDAO = new SampleDAOFactory().getSampleDAO();
	
//	3. Stragegy Pattern : 필요한 데이터를 입력받지 않고 주입받아 사용 - 서비스 안의 모든 결합력은 해결
//	: 전략 주입자가 모든 결합력을 갖게됨. 전략의 주입자가 어플리케이션 안에 있다 = 어플리케이션은 결합력을 가지고 있다. 
//		-> 전략 주입자를 외부로 꺼내버리자! -> container(dependency injecion)
	
	private SampleDAO sampleDAO;
	
	public SampleServiceImpl() {
		super();
		log.info("{}객체 생성_기본 생성자", this.getClass());
	}
	
	@PostConstruct // 모든 의존성 주입이 끝난 이후에 동작. 주입된 대상을 가지고 초기화 작업을 해야 할 때.
	public void init() {
		log.info("{} 객체 초기화", this.getClass());
		log.info("컨테이너 hashcode : {} , this.getClass()");
		log.info("setter 주입을 통해 {}를 주입함.", context.hashCode());
	}
	
	//1
//	@Autowired // 클래스 기준으로만 검색함. 중복은 모름.
	
	//3
//	@Inject
//	@Named("sampleDAO_Maria") // 둘이 Resource 하나의 역할을 함. Resource를 쓸 수 없는 곳에서는 이 두가지를 조합해서 사용.
	public SampleServiceImpl(SampleDAO sampleDAO) {
		super();
		this.sampleDAO = sampleDAO;
		log.info("{}객체 생성_dao 주입생성자", this.getClass());
	}
	
	//2
	@Required
	@Resource(name="sampleDAO_Maria")
	public void setSampleDAO(SampleDAO sampleDAO) {
		this.sampleDAO = sampleDAO;
	}
	
	@Override
	public StringBuffer retreiveSampleData(String code) {
		String rawData = sampleDAO.selectSampleData(code);
//		if(1==1) throw new RuntimeException("강제 발생 예외");		
		StringBuffer info = new StringBuffer(rawData);
		
		
		return info;
	}

}
