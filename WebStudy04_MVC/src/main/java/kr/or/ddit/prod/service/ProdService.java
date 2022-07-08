package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.PKNotFoundException;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

/**
 * 상품 관리(CRUD) Business Logic Layer
 *
 */
public interface ProdService {
	/**
	 * 상품 등록
	 * @param prod
	 * @return OK, FAIL
	 * 
	 */
	public ServiceResult createProd(ProdVO prod);
	/**
	 * 상품 목록 조회
	 * @param pagingVO TODO
	 * @return
	 */
	public List<ProdVO> retrieveProdList(PagingVO<ProdVO> pagingVO);
	/**
	 * 상품 상세 조회
	 * @param prodId
	 * @return 존재하지 않으면, {@link PKNotFoundException} 발생
	 */
	public ProdVO retrieveProd(String prodId);
	/**
	 * 상품 수정
	 * @param prod
	 * @return {@link PKNotFoundException}, OK, FAIL
	 */
	public ServiceResult modifyProd(ProdVO prod);
}













