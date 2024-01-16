package kr.or.ddit.member.service;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.security.PasswordUtils;
import kr.or.ddit.vo.MemberVO;

public class AuthenticateServiceImpl implements AuthenticateService {
	
	MemberDAO memberDAO = new MemberDAOImpl();
	
	@Override
	public ServiceResult authenticate(MemberVO inputData) {
		ServiceResult result = null;
		MemberVO member = memberDAO.selectMemberForAuth(inputData);
	
		if(member != null) {
			String inputPass = inputData.getMemPass();
			String savedPass = member.getMemPass();
			
			if(PasswordUtils.matche(inputPass, savedPass)) {
				try {
					BeanUtils.copyProperties(inputData, member);
					// call by reference 이용
				} catch (IllegalAccessException | InvocationTargetException e) {
					throw new RuntimeException(e);
					//톰캣한테 알아서하라고 넘김. web.xml에서 에러 페이지 찾아서 보내라고 알려줌. 클라이언트한테는 어떤 서버 오류가 났는지 알려주면 안됌!
				}
				
				result = ServiceResult.OK;
			} else {
				result = ServiceResult.INVALIDPASSWORD;
				
			}
			
		} else {
			result = ServiceResult.NOTEXIST;
			
		}
		
		return result;
	}

}
