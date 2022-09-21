package kr.or.ddit.blog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.blog.dao.ManageDAO;
import kr.or.ddit.blog.vo.BlogCateVO;
import kr.or.ddit.blog.vo.BlogReplyVO;
import kr.or.ddit.blog.vo.MyBlogPostVO;
import kr.or.ddit.common.vo.PagingVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author 이아인
 * @since 2022. 8. 23.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 23.      이아인       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Slf4j
@Service
public class ManageServiceImpl implements ManageService{
	@Inject
	ManageDAO manageDAO;
	/**
	 * 카테고리 검색 및 페이징 적용 
	 */
	@Override
	public List<BlogCateVO> retrieveJsonCate(PagingVO<BlogCateVO> catePagingVO) {
		catePagingVO.setTotalRecord(manageDAO.cateTotalRecord((catePagingVO)));
		List<BlogCateVO> cateJsonList =manageDAO.selectJsonCate(catePagingVO);
		catePagingVO.setDataList(cateJsonList);
		return cateJsonList;
	}
	/**
	 * 카테고리 이름 수정
	 */
	@Override
	public int modifyCateNm(Map<String, String> updateMap) {
		String memId=updateMap.get("memId");
		String blogId=manageDAO.selectBlogId(memId);
		updateMap.put("blogId",blogId);
		int checkCnt=manageDAO.checkNm(updateMap);
		int cnt=0;
		if(checkCnt>0) {
			return cnt;
		}else {
			cnt=manageDAO.updateCateNm(updateMap);
			return cnt;
		}
	}
	/**
	 * 카레고리 삭제
	 */
	@Override
	public int removeCateNm(String delNum) {
		//카테고리 삭제시 해당 게시글도 삭제
		int cnt=manageDAO.deleteCateNm(delNum);
		if(cnt>0) {
			manageDAO.deletePost(delNum);
			return cnt;
		}else {
			return cnt;
		}
		
	}
	/**
	 * 카테고리 추가
	 */
	@Override
	public int createCateNm(String inputData,String memId) {
		String blogId=manageDAO.selectBlogId(memId);
		Map<String, String> createMap=new HashMap<>();
		createMap.put("blogId",blogId);
		createMap.put("upName",inputData);
		
		int checkCnt=manageDAO.checkNm(createMap);
		int cnt=0;
		if(checkCnt>0) {
			return cnt;
		}else {
			cnt=manageDAO.insertCate(createMap);
			return cnt;
		}
		
	}
	/**
	 * 게시글 관리, 게시글 조회 및 페이징 ,검색
	 */
	@Override
	public void retrievePostList(PagingVO<MyBlogPostVO> postPagingVO) {
		postPagingVO.setTotalRecord(manageDAO.postTotalRecord(postPagingVO));
		List<MyBlogPostVO> postList =manageDAO.selectPostList(postPagingVO);
		postPagingVO.setDataList(postList);
	}
	/**
	 * 게시글 삭제처리
	 */
	@Override
	public String removePost(MyBlogPostVO myBlogPostVO) {
		int cnt =manageDAO.deletePostList(myBlogPostVO);
		String msg=null;
		if(cnt>0) {
			msg="삭제처리완료";
		}else {
			msg="삭제처리실패";
		}
		return msg;
	}
	/**
	 * 댓글관리, 뎃글조회 및 페이징, 검색
	 */
	@Override
	public void retrieveReplyList(PagingVO<BlogReplyVO> replyPagingVO) {
		replyPagingVO.setTotalRecord(manageDAO.replyTotalRecord(replyPagingVO));
		List<BlogReplyVO> postList =manageDAO.selectReplyList(replyPagingVO);
		replyPagingVO.setDataList(postList);
		
	}
	@Override
	public String removeReply(BlogReplyVO blogReplyVO) {
		int cnt =manageDAO.deleteReplyList(blogReplyVO);
		String msg=null;
		if(cnt>0) {
			msg="삭제처리완료";
		}else {
			msg="삭제처리실패";
		}
		return msg;
	}

}
