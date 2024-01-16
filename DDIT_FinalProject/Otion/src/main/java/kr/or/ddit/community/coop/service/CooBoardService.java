package kr.or.ddit.community.coop.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.common.vo.AttatchVO;
import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.community.coop.vo.CooBoardVO;
import kr.or.ddit.enumpkg.ServiceResult;

public interface CooBoardService {
	/**
	 * 협업게시글 작성
	 * @param cooBoard
	 * @return 
	 */
	public ServiceResult createCooBoard(CooBoardVO cooBoard);
	/**
	 * 협업게시글 목록 조회
	 * @param pagingVO
	 * @return
	 */
	public List<CooBoardVO> retrieveCooBoardList(PagingVO<CooBoardVO>pagingVO);
	/**
	 * 협업게시글 상세 조회
	 * @param cooNum
	 * @return
	 */
	public CooBoardVO retrieveCooBoardList(String cooNum);
	/**
	 * 협업게시글 수정
	 * @param cooBoard
	 */
	public ServiceResult modifyCooBoard(Map<String, Object> map);
	/**
	 * 협업게시글 삭제
	 * @param cooBoard
	 */
	public ServiceResult removeCooBoard(String cooNum);
	/**
	 * 파일 다운로드
	 * @param attatchNum
	 * @return
	 */
	public AttatchVO download(String attatchNum);
	
	/**
	 * 협업모집상태변경
	 * @param coo
	 * @return
	 */
	public ServiceResult modifyCooState(CooBoardVO coo);
}
