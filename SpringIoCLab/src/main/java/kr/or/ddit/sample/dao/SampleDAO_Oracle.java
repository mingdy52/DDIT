package kr.or.ddit.sample.dao;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class SampleDAO_Oracle implements SampleDAO {
	
	public SampleDAO_Oracle() {
		log.info("{}객체 생성", this.getClass());
	}
	
	private Map<String, String> ORACLEDB = new LinkedHashMap<>();
	{
		ORACLEDB.put("a001", "김은대-ORACLEDB");
		ORACLEDB.put("b001", "이쁜이-ORACLEDB");
		ORACLEDB.put("c001", "신용환-ORACLEDB");
	}
	
	@Override
	public String selectSampleData(String code) {
		return ORACLEDB.get(code);
	}

}
