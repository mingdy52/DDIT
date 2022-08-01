package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import util.JDBCUtil3;
import vo.BoardHwVO;

public class BoardHwDAO implements ImBoardHwDAO{
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	Scanner sc = new Scanner(System.in);
	
	@Override
	public List<BoardHwVO> displayAll () {
		
		List<BoardHwVO> boList = new ArrayList<BoardHwVO>();
		
		try {
			conn = JDBCUtil3.getConnection();
			String sql = "select * from jdbc_board";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				BoardHwVO bo = new BoardHwVO();
				
				bo.setBoNo(rs.getString("board_no"));
				bo.setBoTitle(rs.getString("board_title"));
				bo.setBoWriter(rs.getString("board_writer"));
				bo.setBoDate(rs.getString("board_date"));
				bo.setBoContent(rs.getString("board_content"));
				
				boList.add(bo);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("출력 실패");
			e.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		return boList;
	}

	@Override
	public int insertNew(BoardHwVO bo) {
		int cnt = 0;
		
		try {
			conn = JDBCUtil3.getConnection();
			String sql = "insert into jdbc_board "
					+ "values(board_seq.nextval,?,?,sysdate,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bo.getBoTitle());
			pstmt.setString(2, bo.getBoWriter());
			pstmt.setString(3, bo.getBoContent());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("등록 실패");
			e.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		return cnt;
	}

	@Override
	public int updateNew(BoardHwVO bo) {
		int cnt = 0;
		
		try {
			conn = JDBCUtil3.getConnection();
			String sql = "update jdbc_board set board_content = ? "
					+ "where board_title like '%'|| ? ||'%' ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bo.getBoContent());
			pstmt.setString(2, bo.getBoTitle());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		return cnt;
	}

	@Override
	public int deletePost(String bo) {
		int cnt = 0;
		
		try {
			conn = JDBCUtil3.getConnection();
			String sql = "delete from jdbc_board where board_title = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bo);
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		return cnt;
	}

	@Override
	public List<BoardHwVO> search(BoardHwVO bo) {
		
		List<BoardHwVO> searchList = new ArrayList<>();
		
		try {
			conn = JDBCUtil3.getConnection();
			String sql = "select * from jdbc_board where 1=1";
			
			
			if(bo.getBoTitle() != null && !bo.getBoTitle().equals("")) {
				sql += " and board_title like '%' || ? || '%' ";
			}
			if(bo.getBoWriter() != null && !bo.getBoWriter().equals("")) {
				sql += " and board_writer like '%' || ? || '%' ";
			}
			if(bo.getBoContent() != null && !bo.getBoContent().equals("")) {
				sql += " and board_content like '%' || ? || '%' ";
			}
			
			pstmt = conn.prepareStatement(sql);
			
			int index = 1;
			
			
			if(bo.getBoTitle() != null && !bo.getBoTitle().equals("")) {
				pstmt.setString(index++, bo.getBoTitle());
			}
			if(bo.getBoWriter() != null && !bo.getBoWriter().equals("")) {
				pstmt.setString(index++, bo.getBoWriter());
			}
			if(bo.getBoContent() != null && !bo.getBoContent().equals("")) {
				pstmt.setString(index++, bo.getBoContent());
			}
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardHwVO bo2 = new BoardHwVO();
				bo2.setBoNo(rs.getString("board_no"));
				bo2.setBoTitle(rs.getString("board_title"));
				bo2.setBoWriter(rs.getString("board_writer"));
				bo2.setBoDate(rs.getString("board_date"));
				bo2.setBoContent(rs.getString("board_content"));
				
				searchList.add(bo2);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		return searchList;
	}
	
	@Override
	public Boolean check(String title) {
		boolean chk = false;
		
		try {
			conn = JDBCUtil3.getConnection();
			String sql = "select count(*) as cnt from jdbc_board "
					+ "where board_title like '%'||?||'%' ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			while(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			if (cnt > 0) {
				chk = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		return chk;
	}


	
}
