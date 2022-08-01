package service;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import dao.IMemberDAO;
import dao.MemberDAOImpl;
import util.SqlMapClientFactory;
import vo.MemberVO;

public class MemberServiceImpl implements IMemberService {
	private IMemberDAO memDAO;
	private SqlMapClient smc;
	
	private static IMemberService memSer;
	
	private MemberServiceImpl() {
		memDAO = MemberDAOImpl.getInstance();
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IMemberService getInstance() {
		if(memSer == null) {
			memSer = new MemberServiceImpl();
		}
		return memSer;
	}

	
	@Override
	public int insertMember(MemberVO mv) {
		// TODO Auto-generated method stub
		int cnt = 0;
		
		try {
			smc.startTransaction(); // 트랜잭션 시작
			cnt = memDAO.insertMember(smc, mv);
			
			smc.commitTransaction(); // 커밋
			
		} catch (Exception e) {
			try {
				smc.endTransaction();// 롤백(커밋 없이 엔드)
			} catch (SQLException e1) {
				e1.printStackTrace();
			} 
			e.printStackTrace();
		} finally {
			try {
				smc.endTransaction(); // 트랜잭션 끝
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return cnt;
	}

	
	@Override
	public int updateMember(MemberVO mv) {
		
		return memDAO.updateMember(smc, mv);
	}

	@Override
	public int deleteMember(String memId) {
		
		return memDAO.deleteMember(smc, memId);
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		
		return memDAO.getAllMemberList(smc);
	}

	@Override
	public boolean checkMember(String memId) {
		
		return memDAO.checkMember(smc, memId);
	}

	@Override
	public List<MemberVO> searchmemberList(MemberVO mv) {
		
		return memDAO.searchmemberList(smc, mv);
	}

	@Override
	public MemberVO getMember(String memId) {
		
		return memDAO.getMember(smc, memId);
	}

}
