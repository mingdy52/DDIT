package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * JDBC드라이버를 로딩하고 Connection 객체를 생성하는 메서드로 구성된 클래스
 * 방법2) ResourceBundle 객체를 이용하여 DB 연결?
 * @author 306-16
 *
 */
public class JDBCUtil3 {
	static ResourceBundle bundle; //객체변수 선언
	// ResourceBundle 클래스는 자원을 저장하고 반환하는 기능을 제공하는 클래스
	
	
	static {
		
//		getBundle: 주어진 값으로 해당 자원을 가지는 ResourceBundle객체를 만든다.
		bundle = ResourceBundle.getBundle("db");
//		이 클래스의 getBundle 메소드는 파일이나 클래스등으로 부터 
//		ResourceBundle 객체를 생성시켜 주어 key값을 통해 해당 value를 얻을 수 있다.
//			getObject(String key): 키값에 해당되는 객체를 반환한다.
//			getString(String key): 키값에 해당되는 문자열을 반환한다. 
		
		try {
			/*
			 * JAVA.LANG.CLASS클래스
			 *  : 클래스들의 정보(클래스의 필드, 메서드, 클래스의 종류(인터페이스 등))를 담는 메타 클래스.
			 *    JVM은 이 Class 클래스를 통해 클래스들에 대한 정보를 로드 한다.
			 *  	forName() : 물리적인 클래스 파일명을 인자로 넣어주면 이에 해당하는 클래스를 반환한다.
			 *  클래스를 조사하기 위한 클래스입니다.
			 */
			Class.forName(bundle.getString("driver"));
			System.out.println("드라이버 로딩 성공");
		} catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(
					bundle.getString("url"),
					bundle.getString("username"),
					bundle.getString("password"));
		} catch (SQLException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
			
			return null;
		} 
	}
	
	public static void close(Connection conn, Statement stmt, 
			PreparedStatement pstmt, ResultSet rs) {
		if(rs != null) try {rs.close();} catch(SQLException e) {}
		if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
		if(stmt != null) try {stmt.close();} catch(SQLException e) {}
		if(conn != null) try {conn.close();} catch(SQLException e) {}
	}
}