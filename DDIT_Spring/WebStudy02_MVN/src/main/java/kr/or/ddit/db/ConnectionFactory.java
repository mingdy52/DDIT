package kr.or.ddit.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.managed.BasicManagedDataSource;

/**
 * Factory method[Object] pattern
 * 	: 하나의 객체에 대한 생성을 또다른 객체가 전담하는 구조
 *
 */
public class ConnectionFactory {
	private static String url;
	private static String user;
	private static String password;
	private static DataSource ds;
	// 얘를 쓰면 드라이버 로딩을 내부에서 알아서 함.
	
	static {
		String dbInfoPath = "kr/or/ddit/db/dbInfo.properties";
		try(
				InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(dbInfoPath);
		) {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
			Properties dbInfo = new Properties();
			dbInfo.load(is);
			url = dbInfo.getProperty("url");
			user = dbInfo.getProperty("user");
			password = dbInfo.getProperty("password");
			
			  BasicDataSource bbs = new BasicDataSource();
		      bbs.setDriverClassName(dbInfo.getProperty("driverClassName"));
		      bbs.setUrl(url);
		      bbs.setUsername(user);
		      bbs.setPassword(password);
		      
		      int initialSize = Integer.parseInt(dbInfo.getProperty("initialSize"));
		      long maxWait = Long.parseLong(dbInfo.getProperty("maxWait"));
		      int maxTotal = Integer.parseInt(dbInfo.getProperty("maxTotal"));
		      
		      bbs.setInitialSize(initialSize);
		      bbs.setMaxWaitMillis(maxWait);
		      bbs.setMaxTotal(maxTotal);
		      ds=bbs;
//		    커넥션 수를 제한. 커넥션 수가 0이 되면 정해진 시간동안 대기하게 만듬 -> 다 사용한 커넥션을 재활용할 수 있도록 pool로 반환. 
//		    => 일정 개수로 커넥션을 관리할 수 있음
		    
//			Class.forName(dbInfo.getProperty("driverClassName"));
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Connection getConnection() throws SQLException {
//		Connection conn = DriverManager.getConnection(url, user, password); // 사용할지 알지만 어떻게 돌아가는지 모름
		Connection conn = ds.getConnection();
		
		return conn;
	}
}
