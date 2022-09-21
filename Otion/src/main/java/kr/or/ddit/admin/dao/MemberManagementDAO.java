package kr.or.ddit.admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.admin.vo.DelMemVO;
import kr.or.ddit.common.vo.ExpertPaymentVO;
import kr.or.ddit.common.vo.ExpertRefundVO;
import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.common.vo.ReportVO;
import kr.or.ddit.common.vo.WorkPaymentVO;
import kr.or.ddit.common.vo.WorkRefundVO;
import kr.or.ddit.expert.vo.ExpFormVO;
import kr.or.ddit.expert.vo.ExpertVO;

/**
 * 
 * @author 심민경
 * @since 2022. 8. 16.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 16. 심민경               최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Mapper
public interface MemberManagementDAO {
	
	/**
	 * 멤버 리스트 페이징 처리를 위한 레코드 수 조회
	 * @param map : 검색조건을 가진  map
	 * @return 검색 조건을 만족하는 데이터의 개수
	 */
	public int selectTotalRoleRecord(Map<String, Object> map);
	
	/**
	 * 블랙 리스프 페이징 처리를 위한 레코드 수
	 * @param map : 검색조건을 가진  map
	 * @return 검색 조건을 만족하는 데이터의 개수
	 */
	public int selectTotalBlackRecord(Map<String, Object> map);
	
	/**
	 * 회원 목록 조회
	 * @param map
	 * @return 존재하지 않는 경우, size()==0
	 */
	public List<MemberVO> selectRoleMemberList(Map<String, Object> map);

	/**
	 * 블랙리스트 조회
	 * @param map
	 * @return
	 */
	public List<MemberVO> selectBlackList(Map<String, Object> map);
	
	/**
	 * 회원 정보 상세 조회
	 * @param memId 조회할 회원 아이디
	 * @return MemberVO
	 */
	public MemberVO selectMember(String memId);
	
	/**
	 * 전문가 상품 결제 목록 페이징 처리를 위한 레코드 수 조회
	 * @param map : 검색조건을 가진  map
	 * @return 조건을 만족하는 데이터의 개수
	 */
	public int selectTotalExpertPayRecord(Map<String, Object> map);
	
	/**
	 * 전문가 상품 결제 목록 조회
	 * @param map
	 * @return 존재하지 않는 경우, size()==0
	 */
	public List<ExpertPaymentVO> selectExpertPayList(Map<String, Object> map);
	
	/**
	 * 협업 상품 결제 목록 페이징 처리를 위한 레코드 수 조회
	 * @param map : 검색조건을 가진  map
	 * @return 검색 조건을 만족하는 데이터의 개수
	 */
	public int selectTotalWorkPayRecord(Map<String, Object> map);
	
	/**
	 * 협업 상품 결제 목록 조회
	 * @param map
	 * @return 존재하지 않는 경우, size()==0
	 */
	public List<WorkPaymentVO> selectWorkPayList(Map<String, Object> map);
	
	/**
	 * 신고 목록 페이징 처리를 위한 레코드 수 조회
	 * @param map : 검색조건을 가진  map
	 * @return 검색 조건을 만족하는 데이터의 개수
	 */
	public int selectTotalReportRecord(Map<String, Object> map);
	
	/**
	 * 신고 목록 조회
	 * @param map
	 * @return 존재하지 않는 경우, size()==0
	 */
	public List<ReportVO> selectReportList(Map<String, Object> map);
	
	/**
	 * 전문가 상품 환불 목록 페이징 처리를 위한 레코드 수 조회
	 * @param map : 검색조건을 가진  map
	 * @return 검색 조건을 만족하는 데이터의 개수
	 */
	public int selectTotalExpertRefundRecord(Map<String, Object> map);
	
	/**
	 * 전문가 상품 환불 목록 조회
	 * @param map
	 * @return 존재하지 않는 경우, size()==0
	 */
	public List<ExpertRefundVO> selectExpertRefundList(Map<String, Object> map);
	
	/**
	 * 협업 상품 환불 목록 페이징 처리를 위한 레코드 수 조회
	 * @param map : 검색조건을 가진  map
	 * @return 검색 조건을 만족하는 데이터의 개수
	 */
	public int selectTotalWorkRefundRecord(Map<String, Object> map);
	
	/**
	 * 협업 상품 환불 목록 조회
	 * @param map
	 * @return 존재하지 않는 경우, size()==0
	 */
	public List<WorkRefundVO> selectWorkRefundList(Map<String, Object> map);
	
	/**
	 * 회원 파이 차트에 넣어줄 데이터
	 * @return 회원 권한별 이용자 수
	 */
	public MemberVO memberNumForChart();
	
	/**
	 * 전문가 신청 목록 페이징 처리를 위한 레코드 수 조회
	 * @param map : 검색조건을 가진  map
	 * @return 검색 조건을 만족하는 데이터의 개수
	 */
	public int selectTotalExpertFormRecord(Map<String, Object> map);
	
	/**
	 * 전문가 신청목록 상세 조회
	 * @param map
	 * @return 존재하지 않는 경우, size()==0
	 */
	public List<ExpFormVO> selectExpertFormList(Map<String, Object> map);
	
	/**
	 * 전문가 신청서 상세 조회
	 * @param applicantId 조회할 지원자 아이디
	 * @return ExpFormVO
	 */
	public ExpFormVO selectExpertForm(String applicantId);
	
	/**
	 * 전문가 권한 부여
	 * @param applicantId 지원자 아이디
	 * @return 성공 = 1, 실패 = 0
	 */
	public int insertMemberRoleExpert(String applicantId);
	
	/**
	 * 전문가 신청서 승인 상태 변경
	 * @param map 승인번호와 신청번호를 가지고 있는 map
	 * @return 성공 = 1, 실패 = 0
	 */
	public int updateMemberExpert(Map<String, String> map);
	
	/**
	 * 회원 권한별 회원 목록 엑셀 파일 다운로드
	 * @param role 회원 권한
	 * @return 존재하지 않는 경우, size()==0
	 */
	public List<MemberVO> selectMemberList(String role);
	
	/**
	 * expert신청 승인시 expert테이블에 insert 
	 * @param expert
	 * @return
	 */
	public int insertExpert(ExpertVO expert);
	
	/**
	 * 관리자 계정인지 확인
	 * @param memId 확인할 아이디
	 * @return
	 */
	public int checkAdmin(String memId);

	/**
	 * 탈퇴회원 통계 가져오기
	 * @return
	 */
	public DelMemVO getDelStatistics(String year);
	
}
