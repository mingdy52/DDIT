import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import util.JDBCUtil;

public class Member {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		Member mem = new Member();
		mem.menu();
	}

	private void menu() {
		while (true) {
			System.out.println("----------------------");
			System.out.println("== 작업 선택 ==");
			System.out.println("1. 자료 입력");
			System.out.println("2. 자료 삭제");
			System.out.println("3. 자료 수정");
			System.out.println("4. 전체 자료 출력");
			System.out.println("5. 작업 끝.");
			System.out.println("----------------------");
			System.out.print(" => ");
			int num = sc.nextInt();

			switch (num) {
			case 1:
				insertMem();
				break;

			case 2:
				deleteMem();
				break;

			case 3:
				updateMem();
				break;

		case 4:
			displayAllMem();
			break;
			
		case 5:
			close();
			return;

			default:
				System.out.println("다시 입력하세요.");
				break;
			}

		}
	}

	private void close() {
		System.out.println("작업 종료");
	}

	private void displayAllMem() {
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select * from mymember";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String memId = rs.getString("mem_id");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");
				
				System.out.println("ID: " + memId);
				System.out.println("Name: " + memName);
				System.out.println("Tel: " + memTel);
				System.out.println("Addr: " + memAddr);
				System.out.println();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("출력 실패");
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
	}

	private void updateMem() {
		boolean chk = false;
		String memId = "";

		do {
			System.out.println("수정할 회원의 ID를 입력하세요");
			System.out.print(" => ");
			memId = sc.next();

			chk = checkMember(memId);

			if (chk == false)
				System.out.println(memId + "회원은 존재하지 않습니다.");
		} while (chk == false);

		System.out.print("회원 이름 >> ");
		String memName = sc.next();

		System.out.print("회원 전화번호 >> ");
		String memTel = sc.next();

		sc.nextLine();

		System.out.print("회원 주소 >> ");
		String memAddr = sc.nextLine();

		try {
			conn = JDBCUtil.getConnection();
			String sql = "update mymember " 
					+ "set mem_name = ?, mem_tel = ?, mem_addr = ? where mem_id = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(4, memId);
			pstmt.setString(1, memName);
			pstmt.setString(2, memTel);
			pstmt.setString(3, memAddr);

			int cnt = pstmt.executeUpdate();
			if (cnt > 0) {
				System.out.println("수정 성공");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("수정 실패");
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}

	}

	private void deleteMem() {
		System.out.println("삭제할 회원의 ID를 입력하세요");
		System.out.print(" => ");
		String memId = sc.next();

		try {
			conn = JDBCUtil.getConnection();
			String sql = "delete from mymember where mem_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);

			int cnt = pstmt.executeUpdate();
			if (cnt > 0) {
				System.out.println("삭제 성공");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("삭제 실패");
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
	}

	private void insertMem() {
		boolean chk = false;
		String memId = "";

		do {
			System.out.println("추가할 회원 정보를 입력하세요.");
			System.out.print("ID >> ");
			memId = sc.next();

			chk = checkMember(memId);
			if (chk == true) {
				System.out.println(memId + "회원은 이미 존재합니다.");
			}
		} while (chk == true);

		System.out.print("Name >> ");
		String memName = sc.next();

		System.out.print("Tel >> ");
		String memTel = sc.next();

		System.out.print("Addr >> ");
		String memAddr = sc.next();

		conn = JDBCUtil.getConnection();
		String sql = "insert into mymember values(?,?,?,?,sysdate) ";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setString(2, memName);
			pstmt.setString(3, memTel);
			pstmt.setString(4, memAddr);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("등록 성공");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("등록 실패");
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
	}

	private boolean checkMember(String memId) {
		boolean chk = false;

		try {
			conn = JDBCUtil.getConnection();
			String sql = "select count(*) as cnt from MYMEMBER " + "where mem_Id = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);

			rs = pstmt.executeQuery();

			int cnt = 0;
			if (rs.next()) {
				cnt = rs.getInt("cnt");
			}
			if (cnt > 0) {
				chk = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return chk;
	}
}
