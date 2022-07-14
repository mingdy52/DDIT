package kr.or.ddit.sample.dao;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SampleDAO_Maria implements SampleDAO {

	public SampleDAO_Maria() {
		log.info("{}객체 생성", this.getClass());
	}
	
	public void init() {
		log.info("{}객체 생성 후 초기화", this.getClass());
		
	}
	
	public void destroy() {
		log.info("{}객체 소멸", this.getClass());
		
	}
	
	private Map<String, String> MARIADB = new LinkedHashMap<>();
	{
		MARIADB.put("a001", "김은대-MARIADB");
		MARIADB.put("b001", "이쁜이-MARIADB");
		MARIADB.put("c001", "신용환-MARIADB");
	}
	
	@Override
	public String selectSampleData(String code) {
		return MARIADB.get(code);
	}

}
