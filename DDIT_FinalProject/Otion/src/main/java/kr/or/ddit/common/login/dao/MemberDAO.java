package kr.or.ddit.common.login.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.admin.vo.DelMemVO;
import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.common.vo.PagingVO;
/**
 * 
 * @author 서효림
 * @since 2022. 8. 9.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 9.       서효림          최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Mapper
public interface MemberDAO {
	public MemberVO selectMemberForAuth(MemberVO inputData);
	/**
	 * 회원 정보 등록
	 * @param member
	 * @return 등록한 레코드 수 if >0 : 성공, else : 실패
	 */
	public int insertMember(MemberVO member);
	
	/**
	 * 페이징 처리를 위한 레코드 수 조회
	 * @param pagingVO : 검색조건을 가진 VO
	 * @return
	 */
	public int selectTotalRecord(PagingVO<MemberVO> pagingVO);
	/**
	 * 회원 목록 조회
	 * @param pagingVO TODO
	 * @return 존재하지 않는 경우, size()==0
	 */
	public List<MemberVO> selectMemberList(PagingVO<MemberVO> pagingVO);
	
	/**
	 * 회원 상세 조회
	 * @param memId 조회할 회원의 아이디
	 * @return 존재하지 않는 경우, null 반환
	 */
	public MemberVO selectMember(String memId);
	/**
	 * 회원 정보 수정
	 * @param member 수정할 회원의 정보를 가진 VO
	 * @return 수정한 레코드 수 if >0 : 성공, else : 실패
	 */
	public int updateMember(MemberVO member);
	/**
	 * 회원 정보 삭제(???)
	 * @param memId 삭제할 회원의 아이디
	 * @return 삭제한 레코드 수 if >0 : 성공, else : 실패
	 */
	public int deleteMember(String memId);
	

}