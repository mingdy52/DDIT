package kr.or.ddit.member.service;

import java.util.List;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.jasypt.util.text.AES256TextEncryptor;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.PKNotFoundException;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.security.PasswordUtils;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

public class MemberServiceImpl implements MemberService {
	MemberDAO memberDAO = new MemberDAOImpl();
	AuthenticateService authService = new AuthenticateServiceImpl();
	String appPassword = "java";
	AES256TextEncryptor textEncryptor;
	{
		textEncryptor = new AES256TextEncryptor();
		textEncryptor.setPassword(appPassword);
	}
	

	private void encryptMember(MemberVO member) {
		String plain = member.getMemPass();
		String encoded = new PasswordUtils().encryptSha512(plain);
		member.setMemPass(encoded);
		
		String regno1 = member.getMemRegno1();
		String regno2 = member.getMemRegno2();
		String encodedRegno1 = textEncryptor.encrypt(regno1);
		String encodedRegno2 = textEncryptor.encrypt(regno2);
		member.setMemRegno1(encodedRegno1);
		member.setMemRegno2(encodedRegno2);
	}
	
	private void decryptMember(MemberVO member) {
		String encodedRegno1 = member.getMemRegno1();
		String encodedRegno2 = member.getMemRegno2();
		String regno1 = textEncryptor.decrypt(encodedRegno1);
		String regno2 = textEncryptor.decrypt(encodedRegno2);
		member.setMemRegno1(regno1);
		member.setMemRegno2(regno2);
	}
	
	@Override
	public List<MemberVO> retrieveMemberList(PagingVO<MemberVO> pagingVO) {
		pagingVO.setTotalRecord(memberDAO.selectTotalRecord(pagingVO));
		List<MemberVO> memberList = memberDAO.selectMemberList(pagingVO);
		pagingVO.setDataList(memberList);
		return memberList;
	}

	@Override
	public ServiceResult createMember(MemberVO member) {
		ServiceResult result = null;
		if(memberDAO.selectMemberForAuth(member)==null) {
			// 개인식별데이터 암호화
			encryptMember(member);
			
			int rowcnt = memberDAO.insertMember(member);
			if(rowcnt>0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAIL;
			}
		}else {
			result = ServiceResult.PKDUPLICATED;
		}
		return result;
	}

	@Override
	public MemberVO retrieveMember(String memId) {
		MemberVO member = memberDAO.selectMember(memId);
		// 개인식별데이터 복호화
		decryptMember(member);
		
		if (member == null) {
//			회원이 있을거라 들어왔는데 회원이 없다? -> exception
//			커스텀 예외 사용
			throw new PKNotFoundException(String.format("%s 아이디를 가진 회원이 없습니다.", memId));
		}
		return member;
		
	}

	@Override
	public ServiceResult modifyMember(MemberVO member) {
		MemberVO inputData = new MemberVO();
		inputData.setMemId(member.getMemId());
		inputData.setMemPass(member.getMemPass());
		
		ServiceResult result = authService.authenticate(inputData);
		
		switch (result) {
		case NOTEXIST:	
			throw new PKNotFoundException(String.format("%s 에 해당하는 회원이 없음.", member.getMemId()));
		
		case INVALIDPASSWORD:	
			
			break;

		default:
			int rowcnt = memberDAO.updateMember(member);
			result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
			
			break;
		}
		
		
		return result;
	}

	@Override
	public ServiceResult removeMember(MemberVO member) {
		ServiceResult result = authService.authenticate(member);
		
		switch (result) {
		case NOTEXIST:	
			throw new PKNotFoundException(String.format("%s 에 해당하는 회원이 없음.", member.getMemId()));
		
		case INVALIDPASSWORD:	
			
			break;

		default:
			int rowcnt = memberDAO.deleteMember(member.getMemId());
			result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
			
			break;
		}
		
		
		return result;
	}
	
}
