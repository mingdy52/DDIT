package kr.or.ddit.common.comcode.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.common.comcode.dao.ComCodeDAO;
import kr.or.ddit.common.comcode.vo.ComCodeVO;

@Service
@Transactional
public class ComCodeServiceImpl implements ComCodeService{

	@Inject
	ComCodeDAO comCodeDAO;
	
	@Override
	public List<ComCodeVO> retrieveComCode(String comCode) {
		// TODO Auto-generated method stub
		return comCodeDAO.selectComCodeList(comCode);
	}

}
