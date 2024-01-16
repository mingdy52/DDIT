package service;

import java.util.List;

import dao.IMemberDAO;
import dao.MemberDAOImpl;
import vo.MemberVO;

public class MemberServiceImpl implements IMemberService {
	private IMemberDAO memDAO;
	
	
	public MemberServiceImpl() {
		memDAO = new MemberDAOImpl();
	}

	@Override
	public int insertMember(MemberVO mv) {
		// TODO Auto-generated method stub
		return memDAO.insertMember(mv);
	}

	@Override
	public int updateMember(MemberVO mv) {
		// TODO Auto-generated method stub
		return memDAO.updateMember(mv);
	}

	@Override
	public int deleteMember(String memId) {
		// TODO Auto-generated method stub
		return memDAO.deleteMember(memId);
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		// TODO Auto-generated method stub
		return memDAO.getAllMemberList();
	}

	@Override
	public boolean checkMember(String memId) {
		// TODO Auto-generated method stub
		return memDAO.checkMember(memId);
	}

	@Override
	public List<MemberVO> searchmemberList(MemberVO mv) {
		// TODO Auto-generated method stub
		return memDAO.searchmemberList(mv);
	}

}
