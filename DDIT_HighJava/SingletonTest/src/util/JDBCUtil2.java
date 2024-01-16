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

/**
 * JDBC드라이버를 로딩하고 Connection 객체를 생성하는 메서드로 구성된 클래스
 * @author 306-16
 *
 */
public class JDBCUtil2 {
	static Properties prop; //객체변수 선언
	
	static {
		
		prop = new Properties();
		
		try {
			// 파일 읽기를 수행할 FileInputStream 객체 생성하기
			FileInputStream fis = new FileInputStream("res/db.properties");
			
			
			//Properties 객체로 파일 내용 읽기
			//파일 내용을 읽어와 key와 value값으로 분류한 후 Properties객체에 담아준다.
			prop.load(fis);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		try {
			Class.forName(prop.getProperty("driver"));
			System.out.println("드라이버 로딩 성공");
		} catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(
					prop.getProperty("url"),
					prop.getProperty("username"),
					prop.getProperty("password"));
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
