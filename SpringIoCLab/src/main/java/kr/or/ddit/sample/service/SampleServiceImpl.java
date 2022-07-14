package kr.or.ddit.sample.service;

import kr.or.ddit.sample.dao.SampleDAO;
import kr.or.ddit.sample.dao.SampleDAOFactory;
import kr.or.ddit.sample.dao.SampleDAO_Maria;
import kr.or.ddit.sample.dao.SampleDAO_Oracle;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SampleServiceImpl implements SampleService {
	
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
	
	public SampleServiceImpl(SampleDAO sampleDAO) {
		super();
		this.sampleDAO = sampleDAO;
		log.info("{}객체 생성_dao 주입생성자", this.getClass());
	}

	public void setSampleDAO(SampleDAO sampleDAO) {
		this.sampleDAO = sampleDAO;
	}
	
	@Override
	public StringBuffer retreiveSampleData(String code) {
		String rawData = sampleDAO.selectSampleData(code);
		StringBuffer info = new StringBuffer(rawData);
		
		return info;
	}

}
