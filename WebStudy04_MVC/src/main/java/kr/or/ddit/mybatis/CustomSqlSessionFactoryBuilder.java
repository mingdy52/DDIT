package kr.or.ddit.mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class CustomSqlSessionFactoryBuilder {
	private static SqlSessionFactory sqlSessionFactory; // 기본적으로 싱글톤. 한번 만들어짐.
									// sql 세션(=커넥션) 은 커넥션을 위해 사용된다.
	
	static {
		String confisXml="kr/or/ddit/mybatis/Configuration.xml";
		
		try(
			Reader reader = Resources.getResourceAsReader(confisXml);
		) {
			
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
	
}
