package kr.or.ddit.proj.colleague.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.proj.colleague.dao.ColleagueDAO;
import kr.or.ddit.proj.colleague.vo.ColleagueVO;
import kr.or.ddit.proj.work.vo.WorkVO;
import lombok.extern.slf4j.Slf4j;
import sun.util.logging.resources.logging;

/**
 * @author 작성자명
 * @since 2022. 8. 16.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 11.      고정현       최초작성
 * 2022  8. 16.      고정현       협업 신청 승인 및 거절
 * 2022  8. 17.      고정현       팀원 조회
 * Copyright (c) 2022 by DDIT All right reserved
 *      </pre>
 */
@Service
@Transactional
@Slf4j
public class ColleagueServiceImpl implements ColleagueService {

	@Inject
	ColleagueDAO colleagueDAO;

	@Override
	public void createColleagueLeader(ColleagueVO colleague) {
		// TODO Auto-generated method stub
		colleagueDAO.insertColleagueLeader(colleague);
	}

	@Override
	public void createColleague(ColleagueVO colleagueVO) {
		// TODO Auto-generated method stub
		// 팀원 추가
		colleagueDAO.insertColleague(colleagueVO);
	}

	@Override
	public ColleagueVO retrieveColleague(ColleagueVO colleagueVO) {
		// TODO Auto-generated method stub
		// 해당 memId와 pjId를 통해 프로젝트에 등록된 팀원이 있는가를 확인
		ColleagueVO colleague = colleagueDAO.selectColleage(colleagueVO);
		if(colleague == null) {
			List<ColleagueVO> colList = colleagueDAO.selectAdminColleague(colleagueVO);
			if (colList.size() == 0) {
				throw new NullPointerException("해당회원은 해당 프로젝트 회원이 아닙니다.");
			}
		}
		log.info("##############{}", colleague);
		return colleague;

	}

	@Override
	public List<ColleagueVO> retrieveColleagueList(String pjId) {
		// TODO Auto-generated method stub
		return colleagueDAO.setColleagueList(pjId);
	}

	@Override
	public List<ColleagueVO> SearchColleagueList(PagingVO<ColleagueVO> pagingVO) {

		List<ColleagueVO> dataList = colleagueDAO.SearchColleagueList(pagingVO);
		pagingVO.setDataList(dataList);
		return dataList;

	}

	@Override
	public int removeColleague(String colNum) {

		return colleagueDAO.removeColleague(colNum);
	}

	@Override
	public void modifyProfile(ColleagueVO colleague) {
		// TODO Auto-generated method stub
		colleagueDAO.updateProfile(colleague);
	}

	@Override
	public void modifyCode(ColleagueVO colleague) {
		
		colleagueDAO.updateCode(colleague);
	}

}