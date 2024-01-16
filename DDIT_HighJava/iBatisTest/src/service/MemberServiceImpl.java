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
				smc.endTransaction();// 롤백
			} catch (SQLException e1) {
				e1.printStackTrace();
			} 
			e.printStackTrace();
		} 
		
		return cnt;
	}

	@Override
	public int updateMember(MemberVO mv) {
		// TODO Auto-generated method stub
		return memDAO.updateMember(smc, mv);
	}

	@Override
	public int deleteMember(String memId) {
		// TODO Auto-generated method stub
		return memDAO.deleteMember(smc, memId);
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		// TODO Auto-generated method stub
		return memDAO.getAllMemberList(smc);
	}

	@Override
	public boolean checkMember(String memId) {
		// TODO Auto-generated method stub
		return memDAO.checkMember(smc, memId);
	}

	@Override
	public List<MemberVO> searchmemberList(MemberVO mv) {
		// TODO Auto-generated method stub
		return memDAO.searchmemberList(smc, mv);
	}

}
