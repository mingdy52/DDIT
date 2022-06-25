package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.MemberVO;

public class MemberDAOImpl implements MemberDAO {
	
	@Override
	public MemberVO selectMemberForAuth(MemberVO inputData) {
		// 회원의 정보 조회 : id, password, name, hp, address
		// 해당 조건으로 검색시 존재하지 않으면 null 반환
		// 쿼리 객체 : Statement 객체 사용.
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT MEM_ID, MEM_PASS, MEM_NAME, MEM_HP, MEM_ADD1 ");
//		sql.append("FROM MEMBER WHERE MEM_ID='%s' AND MEM_PASS='%s'");
//		쿼리는 동적으로 사용하면 안돼.
		
//		sql.append("FROM MEMBER WHERE MEM_ID=? AND MEM_PASS=?");
		sql.append("FROM MEMBER WHERE MEM_ID=?");
		
//		sql.append("FROM MEMBER WHERE MEM_ID='%s' AND MEM_PASS='1' OR '1'='1'; delete from member where '1'='1'");
//		statement 는 런타임시 쿼리문이 실행되기 때문에 or가 문자가 아닌 연산자로 처리됌.
		
		try (
			Connection conn = ConnectionFactory.getConnection();
//			Statement stmt = conn.createStatement();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			// sql문
		) {
		
			pstmt.setString(1, inputData.getMemId());
//			pstmt.setString(2, inputData.getMemPass());
			// 쿼리 파라미터로 넘어가는 데이터를 문자로 제한해버림. 여기 들어가는 문자는 값으로만 사용되고 연산자로 사용될 수 없음.
			
//			ResultSet rs = stmt.executeQuery(String.format(sql.toString(), inputData.getMemId(), inputData.getMemPass()));
			ResultSet rs = pstmt.executeQuery();
			MemberVO member = null;
			if(rs.next()) { // rs의 커서는 데이터부터가 아니라 헤더부분부터 시작함.
				member = new MemberVO();
				member.setMemId(rs.getString("MEM_ID"));
				member.setMemPass(rs.getString("MEM_PASS"));
				member.setMemName(rs.getString("MEM_NAME"));
				member.setMemHp(rs.getString("MEM_HP"));
				member.setMemAdd1(rs.getString("MEM_ADD1"));
			}
				
			return member;
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<MemberVO> selectMemberList() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT MEM_ID, MEM_NAME, MEM_HP, MEM_ADD1, MEM_MAIL, MEM_MILEAGE FROM MEMBER");
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		
		try(
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql.toString());
		) {
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				MemberVO member = new MemberVO();
				memberList.add(member);
				member.setMemId(rs.getString("MEM_ID"));
				member.setMemName(rs.getString("MEM_NAME"));
				member.setMemHp(rs.getString("MEM_HP"));
				member.setMemAdd1(rs.getString("MEM_ADD1"));
				member.setMemMail(rs.getString("MEM_MAIL"));
				member.setMemMileage(rs.getInt("MEM_MILEAGE"));
				
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return memberList;

	}

	@Override
	public int insertMember(MemberVO member) {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO member (                                       ");
		sql.append("	    MEM_ID, MEM_PASS, MEM_NAME,                           ");
		sql.append("	    MEM_REGNO1, MEM_REGNO2,  MEM_BIR,                     ");
		sql.append("	    MEM_ZIP, MEM_ADD1, MEM_ADD2,                          ");
		sql.append("	    MEM_HOMETEL, MEM_COMTEL, MEM_HP,                      ");
		sql.append("	    MEM_MAIL, MEM_JOB, MEM_LIKE,                          ");
		sql.append("	    MEM_MEMORIAL, MEM_MEMORIALDAY, MEM_MILEAGE			  ");
		sql.append("	) VALUES (                                                ");
		sql.append("			?, ?, ?,                                          ");
		sql.append("		    ?, ?, TO_DATE(?, 'YYYY-MM-DD'),                   ");
		sql.append("		    ?, ?, ?,                                          ");
		sql.append("		    ?, ?, ?,                                          ");
		sql.append("		    ?, ?, ?,                                          ");
		sql.append("		    ?, TO_DATE(?, 'YYYY-MM-DD'), 1000                 ");
		sql.append("	)                                                         ");
		int rowcnt = 0;
		
		try (Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql.toString());
		) {
			int idx = 1;
			stmt.setString(idx++,member.getMemId());
			stmt.setString(idx++,member.getMemPass());
			stmt.setString(idx++,member.getMemName());
			stmt.setString(idx++,member.getMemRegno1());
			stmt.setString(idx++,member.getMemRegno2());
			stmt.setString(idx++,member.getMemBir());
			stmt.setString(idx++,member.getMemZip());
			stmt.setString(idx++,member.getMemAdd1());
			stmt.setString(idx++,member.getMemAdd2());
			stmt.setString(idx++,member.getMemHometel());
			stmt.setString(idx++,member.getMemComtel());
			stmt.setString(idx++,member.getMemHp());
			stmt.setString(idx++,member.getMemMail());
			stmt.setString(idx++,member.getMemJob());
			stmt.setString(idx++,member.getMemLike());
			stmt.setString(idx++,member.getMemMemorial());
			stmt.setString(idx++,member.getMemMemorialday());
			// 기본형데이터는 null 값을 가질 수 없음. 멤버가 비어있으니 mileage 에도 null 이 들어감
			
			rowcnt = stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return rowcnt;
	}
	
	
}
