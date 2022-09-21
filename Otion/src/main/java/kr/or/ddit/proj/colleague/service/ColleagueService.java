package kr.or.ddit.proj.colleague.service;

import java.util.List;

import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.proj.colleague.vo.ColleagueVO;

/**
 * @author 작성자명
 * @since 2022. 8. 22.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 22.      고정현       팀원 전체 조회 추가 + 최초 작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
public interface ColleagueService {
	public void createColleagueLeader(ColleagueVO colleague);

	public void createColleague(ColleagueVO colleagueVO);
	
	public ColleagueVO retrieveColleague(ColleagueVO colleagueVO);

	public List<ColleagueVO> retrieveColleagueList(String pjId);
	
	public List<ColleagueVO> SearchColleagueList(PagingVO<ColleagueVO> pagingVO);
	
	public int removeColleague(String colNum);

	public void modifyProfile(ColleagueVO colleague);
	
	public void modifyCode(ColleagueVO colleague);
}
