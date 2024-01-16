package kr.or.ddit.proj.post.service;

import java.util.List;

import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.proj.colleague.vo.ColleagueVO;
import kr.or.ddit.proj.post.vo.MyPostVO;

/**
 * @author 작성자명
 * @since 2022. 8. 23.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 23.     고정현       최초작성(리스트 받아오기)
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
public interface ProjectMyPostService {
	
	/**
	 * 해당 회원이 프로젝트내에서 작성한 모든 리스트 가져오기
	 * @param colleague
	 * @return
	 */
	public PagingVO<?> retrieveMyPostList(PagingVO<?> pagingVO);
}
