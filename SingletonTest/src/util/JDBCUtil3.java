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
	
	static {
		
		bundle = ResourceBundle.getBundle("db");
		
		try {
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