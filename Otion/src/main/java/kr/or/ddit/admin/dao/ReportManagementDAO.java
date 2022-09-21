package kr.or.ddit.admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.common.vo.ReportVO;
import kr.or.ddit.community.coop.vo.CooBoardVO;
import kr.or.ddit.community.free.vo.FreeBoardVO;
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
public interface ReportManagementDAO {
	
	/**
	 * 
	 * @return 신고 횟수가 10이상인 회원수
	 */
	public int selectTotalMemRecord();
	
	/**
	 * 
	 * @return 신고 횟수가 10이상인 전문가 회원수
	 */
	public int selectTotalExpRecord();
	
	/**
	 * 
	 * @return 신고 횟수가 10이상인 협업 게시글
	 */
	public int selectTotalCooRecord();
	
	/**
	 * 
	 * @return 신고 횟수가 10이상인 자유 게시글
	 */
	public int selectTotalFreeRecord();
	
	/**
	 * 신고 횟수가 10이상인 회원 목록
	 * @param pagingVO
	 * @return 존재하지 않는 경우, size()==0
	 */
	public List<MemberVO> selectMemberReportList(PagingVO<MemberVO> pagingVO);
	
	/**
	 * 신고 횟수가 10이상인 전문가 목록
	 * @param pagingVO
	 * @return 존재하지 않는 경우, size()==0
	 */
	public List<MemberVO> selectExpertReportList(PagingVO<MemberVO> pagingVO);
	
	/**
	 * 신고 횟수가 10이상인 협업 게시글 목록
	 * @param pagingVO
	 * @return 존재하지 않는 경우, size()==0
	 */
	public List<CooBoardVO> selectCooBoardReportList(PagingVO<CooBoardVO> pagingVO);
	
	/**
	 * 신고 횟수가 10이상인 자유 게시글 목록
	 * @param pagingVO
	 * @return 존재하지 않는 경우, size()==0
	 */
	public List<FreeBoardVO> selectFreeBoardReportList(PagingVO<FreeBoardVO> pagingVO);
	
	/**
	 * 신고 내역 상세보기
	 * @param map 신고 내역을 가져올 회원 아이디 또는 게시글 일련번호, 페이징 번호
	 * @return 존재하지 않는 경우, size()==0
	 */
	public List<ReportVO> selectReport(Map<String,Object> map);
	
	/**
	 * 블랙리스트에 회원 추가
	 * @param map 블랙리스트로 추가할 회원아이디와 검열 사유
	 * @return 성공 = 1, 실패 = 0
	 */
	public int insertBlackMember(Map<String,String> map);
	
	/**
	 * 전문가 권한 삭제
	 * @param map 권한을 삭제할 전문가 아이디
	 * @return 성공 = 1, 실패 = 0
	 */
	public int deleteExpertRole(Map<String,String> map);
	
	/**
	 * 전문가 삭제여부 변경
	 * @param map
	 * @return
	 */
	public int updateExpDel(Map<String,String> map);
	
	/**
	 * 협업 게시글 블라인드
	 * @param map 블라인드 처리할 협업 게시판 아이디
	 * @return성공 = 1, 실패 = 0
	 */
	public int updateCooBlind(Map<String,String> map);
	
	/**
	 * 협업 게시판 작성자 불러오기
	 * @param map
	 * @return
	 */
	public String selectCooWriterId(Map<String,String> map);
	
	/**
	 * 자유 게시글 블라인드
	 * @param map 블라인드 처리할 협업 게시판 아이디
	 * @return 성공 = 1, 실패 = 0
	 */
	public int updateFreeBlind(Map<String,String> map);
	
	/**
	 * 자유게시판 작성자 불러오기
	 * @param map
	 * @return
	 */
	public String selectFreeWriterId(Map<String,String> map);
	
}
