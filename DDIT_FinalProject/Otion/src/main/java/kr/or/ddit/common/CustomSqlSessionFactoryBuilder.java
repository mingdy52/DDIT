package kr.or.ddit.common;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class CustomSqlSessionFactoryBuilder {
	private static SqlSessionFactory sqlSessionFactory; // sessionFactory는 싱글톤임(DB가 3개면 여러개의 DB가 만들어져야함)
	// 메모리에 로딩될 때(상수 저장소에 들어가기 때문)
	static {
		String configXml = "kr/or/ddit/mybatis/ProjectChatConfiguration.xml";
		// xml파일이 문자로 되어있기 때문
		try (Reader reader = Resources.getResourceAsReader(configXml)) {
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			// tomcat한테 서버 오류를 보내줌
			throw new RuntimeException(e);
		}
	}

	public static SqlSessionFactory getSqlSessionFactory() {
		// DB에서는 session을 통로(connection 하나로 생각)라고 하므로 통로를 만드는 팩토리임
		return sqlSessionFactory;
	}

}
