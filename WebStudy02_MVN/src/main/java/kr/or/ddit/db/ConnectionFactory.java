package kr.or.ddit.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Factory method[Object] pattern
 * 	: 하나의 객체에 대한 생성을 또다른 객체가 전담하는 구조
 *
 */
public class ConnectionFactory {
	private static String url;
	private static String user;
	private static String password;
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
			Class.forName(dbInfo.getProperty("driverClassName"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Connection getConnection() throws SQLException {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "smk96";
		String password = "java";
		Connection conn = DriverManager.getConnection(url, user, password); // 사용할지 알지만 어떻게 돌아가는지 모름
		return conn;
	}
}
