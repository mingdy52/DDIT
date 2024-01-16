

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.print.DocFlavor.STRING;

import util.JDBCUtil;

public class Board {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		Board bo = new Board();
		bo.start();
	}

	private void start() {
		while (true) {
			System.out.println("------------------------------------------");
			System.out.println("1. 전체 목록 출력  2. 새글작성  3. 수정  4.삭제  5.검색 ");
			System.out.println("------------------------------------------");

			System.out.print("메뉴 선택 => ");
			int num = sc.nextInt();

			switch (num) {
			case 1:
				displayAll();
				break;

			case 2:
				writeNew();
				break;

			case 3:
				modify();
				break;

			case 4:
				del();
				break;

			case 5:
				search();
				break;

			default:
				System.out.println("다시 입력하세요.");
				break;
			}
		}
	}

	private void search() {
		while(true) {
			System.out.println("-------------------------");
			System.out.println("1. 제목 검색	2. 내용 검색 ");
			System.out.println("-------------------------");
			int num = sc.nextInt();
			
			switch (num) {
			case 1:
				searchT();
				break;
				
			case 2:
				searchC();
				break;

			default:
				System.out.println("다시 입력하세요.");
				break;
			}
			
		}
		
		
	}

	private void searchC() {
		System.out.println("검색할 내용을 입력하세요.");
		System.out.print("내용 =>");
		String content = sc.next();
		
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select * from JDBC_BOARD where BOARD_CONTENT like '%'|| ? ||'%' ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String boNum = rs.getString("board_no");
				String boTitle = rs.getString("board_title");
				String boWriter = rs.getString("board_writer");
				String bodate = rs.getString("board_date");
				String bocont = rs.getString("board_content");
				
				System.out.println("글번호: " + boNum);
				System.out.println("글제목: " + boTitle);
				System.out.println("작가: " + boWriter);
				System.out.println("날짜: " + bodate);
				System.out.println("내용: " + bocont + "\n");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void searchT() {
		System.out.println("검색할 글제목을 입력하세요.");
		System.out.print("제목 =>");
		String title = sc.next();
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select * from JDBC_BOARD where BOARD_TITLE = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String boNum = rs.getString("board_no");
				String boTitle = rs.getString("board_title");
				String boWriter = rs.getString("board_writer");
				String bodate = rs.getString("board_date");
				String bocont = rs.getString("board_content");
				
				System.out.println("글번호: " + boNum);
				System.out.println("글제목: " + boTitle);
				System.out.println("작가: " + boWriter);
				System.out.println("날짜: " + bodate);
				System.out.println("내용: " + bocont + "\n");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void del() {
		System.out.println("삭제할 글제목을 입력하세요.");
		System.out.print("제목 =>");
		String title = sc.next();
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "delete from JDBC_BOARD where BOARD_TITLE = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("삭제 완료");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		
		
		
	}

	private void modify() {
		System.out.println("작가명을 입력하세요");
		System.out.print("작가 =>");
		String writer = sc.next();
		
		System.out.println("수정할 글제목을 입력하세요.");
		System.out.print("제목 =>");
		String title = sc.next();
		
		System.out.println("수정할 내용을 입력하세요.");
		System.out.print("=>");
		String content = sc.next();
		
		String sql = "update JDBC_BOARD set BOARD_CONTENT = ? "
				+ "where BOARD_TITLE = ? and BOARD_WRITER = ?";
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content);
			pstmt.setString(2, title);
			pstmt.setString(3, writer);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("수정 완료");
			} else {
				System.out.println("수정 실패");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		
	}

	private void writeNew() {
		System.out.print("작가 : ");
		String writer = sc.next();
		System.out.print("제목 : ");
		String title = sc.next();
		System.out.print("내용 : ");
		String content = sc.next();
		
		String sql = "insert into JDBC_BOARD values (board_seq.nextval, ? , ? , sysdate , ?)";
				
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, writer);
			pstmt.setString(3, content);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("등록완료");
			} else {
				System.out.println("등록실패");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
	}

	private void displayAll() {
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select * from jdbc_board ";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String boNum = rs.getString("board_no");
				String boTitle = rs.getString("board_title");
				String boWriter = rs.getString("board_writer");
				String bodate = rs.getString("board_date");
				String bocont = rs.getString("board_content");
				
				System.out.println("글번호: " + boNum);
				System.out.println("글제목: " + boTitle);
				System.out.println("작가: " + boWriter);
				System.out.println("날짜: " + bodate);
				System.out.println("내용: " + bocont + "\n");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("출력실패");
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
				
	}
}
