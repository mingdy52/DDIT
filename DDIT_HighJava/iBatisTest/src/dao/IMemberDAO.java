package dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import vo.MemberVO;

/**
 * 실제 DB와 연결해서 SQL문을 수행 후 결과를 작성하여 Service에 전달하는  DAO의 Interface
 * @author 306-16
 */

public interface IMemberDAO {
	
	/**
	 * MemberVO에 담겨진 자료를 DB에 insert하는 메서드
	 * @param mv DB에 insert할 자료가 저장된 MemberVO객체
	 * @return DB작업이 성공하면 1 이상의 값이 반환되고 실패하면 0이 반환된다.
	 */
	public int insertMember(SqlMapClient smc, MemberVO mv);
	
	/**
	 * 하나의 MemberVO 객체를 받아서 DB에 update하는 메서드
	 * @param mv update할 회원정보가 들어있는 Member객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int updateMember(SqlMapClient smc, MemberVO mv);
	
	/**
	 * 회원ID를 매개변수로 받아서 그 회원 정보를 삭제하는 메서드
	 * @param memId 삭제할 회원ID
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int deleteMember(SqlMapClient smc, String memId);
	
	/**
	 * DB의 mymember테이블에 있는 전체 레코드를 가져와서 List에 담아 반환하는 메서드
	 * @return MemberVO객체에 담고있는 List객체
	 */
	public List<MemberVO> getAllMemberList(SqlMapClient smc);
	
	/**
	 * 주어진 회원ID가 존재하는지 여부를 알아내는 메서드
	 * @param memId 검색할 회원ID
	 * @return 해당 회원ID가 있으면 true, 없으면 false
	 */
	public boolean checkMember(SqlMapClient smc, String memId);
	
	public List<MemberVO> searchmemberList(SqlMapClient smc, MemberVO mv);
	
	public List<MemberVO> searchMember(SqlMapClient smc, MemberVO mv);
	
}
