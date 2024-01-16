package comm.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import comm.vo.AtchFileVO;

public interface IAtchFileService {
	
	/**
	 * 첨부파일 목록을 저장하기 위한 메서드
	 * @param req Part 정보를 꺼내기 위한 요청 객체
	 * @return AtchFileVO 저장한 첨부파일정보
	 */
	public AtchFileVO saveAtchFileList(HttpServletRequest req);
	
	/**
	 * 첨부파일 목록 조회
	 * @param atchFileVO
	 * @return
	 */
	public List<AtchFileVO> getAtchFileList(AtchFileVO atchFileVO);
	
	/**
	 * 첨부파일 세부정보 조회하기
	 * @param atFileVO
	 * @return
	 */
	public AtchFileVO getAtchFileDetail(AtchFileVO atFileVO);
	
}
