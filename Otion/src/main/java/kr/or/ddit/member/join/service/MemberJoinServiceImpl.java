package kr.or.ddit.member.join.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.enumpkg.ServiceResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberJoinServiceImpl implements MemberJoinService {
	
//	@Autowired
//    private MemberRepository memberRepository;
//
//    // 아이디 중복 체크
//    @Override
//    public int idCheck(String id) {
//        int cnt = memberRepository.idCheck(id);
//        return cnt;
//    }

	@Override
	public ServiceResult createMember(MemberVO member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MemberVO> retrieveMemberList(PagingVO<MemberVO> pagingVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberVO retrieveMember(String memId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult modifyMember(MemberVO member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult removeMember(MemberVO member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int idCheck(String id) {
		// TODO Auto-generated method stub
		return 0;
	}
}
