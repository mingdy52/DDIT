package kr.or.ddit.admin.service;

import java.util.Map;

import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.common.vo.ReportVO;
import kr.or.ddit.community.coop.vo.CooBoardVO;
import kr.or.ddit.community.free.vo.FreeBoardVO;
import kr.or.ddit.enumpkg.ServiceResult;

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
public interface ReportManagementService {
	
	/**
	 * 신고 수가 10 이상인 회원 목록
	 * @param pagingVO
	 */
	public void retrieveMemberReportList(PagingVO<MemberVO> pagingVO);
	
	/**
	 * 신고 수가 10 이상인 전문가 목록
	 * @param pagingVO
	 */
	public void retrieveExpertReportList(PagingVO<MemberVO> pagingVO);
	
	/**
	 * 신고 수가 10 이상인 협업글 목록
	 * @param pagingVO
	 */
	public void retrieveCooBoardReportList(PagingVO<CooBoardVO> pagingVO);
	
	/**
	 * 신고 수가 10 이상인 자유게시글 목록
	 * @param pagingVO
	 */
	public void retrieveFreeBoardReportList(PagingVO<FreeBoardVO> pagingVO);
	
	/**
	 * 신고 내역 상세 목록
	 * @param target 회원 아이디 또는 게시길 일련번호
	 * @return
	 */
	public void retrieveReport(Map<String, Object> map, PagingVO<ReportVO> pagingVO);
	
	/**
	 * 신고 처리
	 * @param map
	 * @param targetClass member, expert, board
	 * @return
	 */
	public ServiceResult reportProcess(Map<String, String> map, String targetClass);
}
