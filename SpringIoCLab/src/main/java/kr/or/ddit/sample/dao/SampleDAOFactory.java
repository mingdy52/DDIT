package kr.or.ddit.sample.dao;

public class SampleDAOFactory {

	public SampleDAO getSampleDAO() {
		
		return new SampleDAO_Oracle();
	}
	
}
