package kr.or.ddit.proj.colleague.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.proj.colleague.vo.ColleagueVO;

/**
 * @author 작성자명
 * @since 2022. 8. 17.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 12.      고정현       프로젝트 리더추가
 * 2022. 8. 16.      고정현       프로젝트 승인된 팀원 추가
 * 2022. 8. 17.      고정현       프로젝트 일정 추가를 위한 select
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Mapper
public interface ColleagueDAO {
	
	public void insertColleagueLeader(ColleagueVO colleague);

	public void insertColleague(ColleagueVO colleague);
	
	/**
	 * 해당 프로젝트의 해당 회원의 PK를 가져오기 위한 select
	 * @param colleagueVO
	 * @return
	 */
	public ColleagueVO selectColleage(ColleagueVO colleagueVO);

	/**
	 * 해당 프로젝트 전체 팀원 리스트 가져오기
	 * @param pjId
	 * @return
	 */
	public List<ColleagueVO> setColleagueList(String pjId);
	
	public List<ColleagueVO> SearchColleagueList(PagingVO<ColleagueVO> pagingVO);
	
	public int removeColleague(String colNum);

	public List<ColleagueVO> selectAdminColleague(ColleagueVO colleagueVO);

	public void updateProfile(ColleagueVO colleague);
	
	public void updateCode(ColleagueVO colleague);
}

