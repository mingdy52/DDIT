package kr.or.ddit.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

/**
 * 
 * 회원관리(CRUD) 및 인증구조를 위한 Persistence Layer
 *
 */
@Mapper
public interface MemberDAO {
//	프록시가 있으려면 인터페이스가 있어야함.
	
	public MemberVO selectMemberForAuth(MemberVO inputData);
	
	/**
	 * 회원 정보 등록
	 * @param member
	 * @return 등록한 레코드 수 > 0 :  성공, else : 실패
	 */
	public int insertMember(MemberVO member);
	
	/**
	 * 페이징 처리를 위한 레코드 수 조회
	 * @param pagingVO : 검색조건을 가진 VO
	 * @return
	 */
	public int selectTotalRecord(PagingVO<MemberVO> pagingVO);
//	여러가지 검색 조건을 커러하기 위해 pagingVO를 넣음. 아니면 simplecondition만 넣어두..
	
	/**
	 * 회원 목록 조회
	 * @return 존재하지 않는 경우, size()==0
	 */
	public List<MemberVO> selectMemberList(PagingVO<MemberVO> pagingVO);
	
	/**
	 * 회원 상세 조회
	 * @param memId 조회할 회원의 아이디
	 * @return 존재하지 않으면 null 값을 반환
	 */
	public MemberVO selectMember(String memId);
	
	/**
	 * 회원 정보 수정
	 * @param member 수정할 회원의 정보를 가진 VO
	 * @return 수정한 레코드 수 if>0 : 성공, else : 실패
	 */
	public int updateMember(MemberVO member);
	
	/**
	 * 회원 정보 삭제(???)
	 * @param memId 삭제할 회원의 아이디
	 * @return 삭제한 레코드 수 if>0 : 성공, else : 실패
	 */
	public int deleteMember(String memId);
	
}
