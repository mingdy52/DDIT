package kr.or.ddit.member.service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.MemberVO;

/**
 * 
 * 인증구조를 위한 Business Logic Layer
 *
 */
public interface AuthenticateService {

	/**
	 * id 와 password 기반의 인증 로직
	 * @param inputData id 와 password를 가진 VO
	 * @return 존재하지 않을 때(NOTEXIST), 비번 오류(INVALIDPASSWORD), 성공(OK)
	 */
	public ServiceResult authenticate(MemberVO inputData);
	
}
