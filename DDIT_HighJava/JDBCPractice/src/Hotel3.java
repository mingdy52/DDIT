


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.JButton;

import util.JDBCUtil;

public class Hotel3 {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		
		Hotel3 h3 = new Hotel3();
		h3.start();
	}

	private void start() {
		System.out.println("쓰기 작업 완료");
		
		System.out.println("**************************");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("**************************\n");

		while (true) {
			System.out.println("*******************************************");
			System.out.println("어떤 업무를 하시겠습니까?");
			System.out.println("1.체크인  2.체크아웃 3.객실상태 4.업무종료");
			System.out.println("*******************************************");
			System.out.print("메뉴선택 => ");
			int num = sc.nextInt();

			switch (num) {
			case 1:
				checkIn();
				break;
				
			case 2:
				checkOut();
				break;

			case 3:
				roomState();
				break;

			case 4:
				close();
				return;
				
			default:
				System.out.println("잘못된 입력입니다.\n");
				break;
				
			}

		}		
	}



	private void close() {
		System.out.println();
		System.out.println("**************************");
		System.out.println("호텔 문을 닫았습니다.");
		System.out.println("**************************");
		
	}

	private void roomState() {
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select * from myhotel";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String roomNum = rs.getString("room_num");
				String name = rs.getString("cust_name");
				
				System.out.println("방번호 : " + roomNum + ", 투숙객 : " + name);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		
	}

	private void checkOut() {
		System.out.println("\n어느방을 체크아웃 하시겠습니까?");
		System.out.print("방번호 입력 => ");
		String roomNum = sc.next();
		
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "delete from myhotel where room_num = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, roomNum);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("체크아웃 되었습니다.");
			} else {
				System.out.println(roomNum + "방에는 체크인한 사람이 없습니다.");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
	}

	private void checkIn() {
		boolean chk = false;
		String roomNum = "";
		String name = "";
		
		do {
			System.out.println("\n어느방에 체크인 하시겠습니까?");
			System.out.print("방번호 입력 => ");
			roomNum = sc.next();
			
			
			chk = checkRoom(roomNum);
			
			if(chk == true) {
				System.out.println(roomNum + "방에는 이미 사람이 있습니다.");
				break;
			}
		} while(chk == true);
		
		System.out.println();
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력 => ");
		name = sc.next();
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "insert into myhotel(room_num, cust_name) values(?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, roomNum);
			pstmt.setString(2, name);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("입실 완료");
			} else {
				System.out.println("입실 불가");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		
	}

	private boolean checkRoom(String roomNum) {
		boolean chk = false; // 회원 존재여부 체크
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "select count(*) as cnt from myhotel where room_num = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, roomNum);
			
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			while(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			
			if(cnt > 0) {
				chk = true;
			}
					
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		return chk;
	}
}
