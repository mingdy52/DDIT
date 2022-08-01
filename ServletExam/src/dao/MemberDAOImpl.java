package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.JDBCUtil3;
import util.SqlMapClientFactory;
import vo.MemberVO;

public class MemberDAOImpl implements IMemberDAO {
	
	private static IMemberDAO memDAO;
	
	private MemberDAOImpl() {
		
	}
	public static IMemberDAO getInstance() {
		if(memDAO == null) {
			memDAO = new MemberDAOImpl();
		}
		return memDAO;
	}
	
	@Override
	public int insertMember(SqlMapClient smc, MemberVO mv) {
		int cnt = 0;
		
		try {
			
			cnt = smc.update("member.insertMember",mv);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("회원정보 입력 중 예외 발생", e);
			// Runtime-- 은 try-catch 안해줘도 괜찮
		}
		
		return cnt;

	}

	@Override
	public int updateMember(SqlMapClient smc, MemberVO mv) {
		// TODO Auto-generated method stub
		int cnt = 0;
		
		try {
			cnt = smc.update("member.updateMember", mv);

		}catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("회원정보 수정 중 예외 발생", e);
		}
		
		return cnt;
	}

	@Override
	public int deleteMember(SqlMapClient smc, String memId) {
		// TODO Auto-generated method stub
		int cnt = 0;
		
		try {
			cnt = smc.delete("member.deleteMember", memId);

		}catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("회원정보 삭제 중 예외 발생", e);
		}
		
		return cnt;
	}



	
	@Override
	public List<MemberVO> getAllMemberList(SqlMapClient smc) {
		// TODO Auto-generated method stub
		
		
		/**
		 * MemberVO 객체에 담긴 회원 정보를 이용하여 회원을 검색하는 메서드
		 * mv 검색할 자료가 들어 있는 MemberVO객체
		 * 
		 */
		List<MemberVO> memList = new ArrayList<MemberVO>();
		
		try {
			memList = smc.queryForList("member.getMemberAll");

		}catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("회원정보 검색 중 예외 발생", e);
		}
		
		return memList;
	}
	
	/**
	 * 주어진 회원id가 존재하는지 여부를 알아내는 메서드
	 * 
	 */
	@Override
	public boolean checkMember(SqlMapClient smc, String memId) {
		
		boolean chk = false; // 회원 존재여부 체크
		
		try {
			int cnt = (int)smc.queryForObject("member.checkMember", memId);
			
			if(cnt > 0) { // 중복이 존재함
				chk = true;
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("회원정보 중복 체크 중 예외 발생", e);
		} 
		
		return chk;
	}

	@Override
	public List<MemberVO> searchmemberList(SqlMapClient smc, MemberVO mv) {
		
		List<MemberVO> memList = new ArrayList<MemberVO>();
		
		try {
			
			memList = smc.queryForList("member.searchMember", mv);
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException("회원정부 검색 중 예외 발생", e);
		} 
		
		return memList;
	}
	@Override
	public List<MemberVO> searchMember(SqlMapClient smc, MemberVO mv) {
		return memDAO.searchmemberList(smc, mv);
	}
	
	@Override
	public MemberVO getMember(SqlMapClient smc, String memId) {
		MemberVO mv = null;
		
		try {
			
			mv = (MemberVO)smc.queryForObject("member.getMember", memId);
					
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("회원정보 조회 중 예외 발생", e);
		} 
		
		return mv;
	}
	

}
