package service;

import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import vo.MemberVO;

/**
 * Service객체는 DAO에 설정된 메서드를 원하는 작업에 맞게 호출하여 결과를 받아오고,
 * 받아온 자료를 Controller에게 보내주는 역할을 수행한다.
 * @author 306-16
 */

/**
 * MemberVO 객체에 담긴 회원 정보를 이용하여 회원을 검색하는 메서드
 * mv 검색할 자료가 들어 있는 MemberVO객체
 * 
 */

public interface IMemberService {
	
	public List<MemberVO> searchmemberList(MemberVO mv);
	
	/**
	 * MemberVO에 담겨진 자료를 DB에 insert하는 메서드
	 * @param mv DB에 insert할 자료가 저장된 MemberVO객체
	 * @return DB작업이 성공하면 1 이상의 값이 반환되고 실패하면 0이 반환된다.
	 */
	public int insertMember(MemberVO mv);
	
	/**
	 * 하나의 MemberVO 객체를 받아서 DB에 update하는 메서드
	 * @param mv update할 회원정보가 들어있는 Member객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int updateMember(MemberVO mv);
	
	/**
	 * 회원ID를 매개변수로 받아서 그 회원 정보를 삭제하는 메서드
	 * @param memId 삭제할 회원ID
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int deleteMember(String memId);
	
	/**
	 * DB의 mymember테이블에 있는 전체 레코드를 가져와서 List에 담아 반환하는 메서드
	 * @return MemberVO객체에 담고있는 List객체
	 */
	public List<MemberVO> getAllMemberList();
	
	/**
	 * 주어진 회원ID가 존재하는지 여부를 알아내는 메서드
	 * @param memId 검색할 회원ID
	 * @return 해당 회원ID가 있으면 true, 없으면 false
	 */
	public boolean checkMember(String memId);
	
	/**
	 * 회원 ID로 회원 정보 조회
	 * @param memId
	 * @return
	 */
	public MemberVO getMember(String memId);
	
}
