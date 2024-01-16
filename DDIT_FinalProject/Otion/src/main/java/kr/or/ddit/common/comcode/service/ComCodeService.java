package kr.or.ddit.common.comcode.service;

import java.util.List;

import kr.or.ddit.common.comcode.vo.ComCodeVO;

public interface ComCodeService {
	
	public List<ComCodeVO> retrieveComCode(String comCode);
}
