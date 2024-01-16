package kr.or.ddit.blog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.blog.dao.MyBlogDAO;
import kr.or.ddit.blog.vo.BlogCateVO;
import kr.or.ddit.blog.vo.MyBlogPostVO;
import kr.or.ddit.blog.vo.ShareVO;
import kr.or.ddit.blog.vo.TodoVO;
import kr.or.ddit.common.login.vo.MemberPrincipal;
import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.NotAuthorityException;
import kr.or.ddit.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 작성자명
 * @since 2022. 8. 20.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 20.      이아인       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Slf4j
@Service
public class MyBlogServiceImpl implements MyBlogService{
	@Inject
	MyBlogDAO myBlogDAO;
	
	/**
	 * 블로그 카테고리 조회
	 */
	@Override
	public List<BlogCateVO> retrieveCate(String memId) {
		List<BlogCateVO> blogCateList =myBlogDAO.selectCate(memId);
		return blogCateList;
	}
	/**
	 * 블로그 사용자 확인
	 */
	@Override
	public int checkMemId(String memId) {
		int cnt =myBlogDAO.selectMemId(memId);
		return cnt;
	}
	
	/**
	 * 블로그 리스트
	 * @throws ParseException 
	 */
	@Override
	public void retrievePostList(Map<String, Object> map, PagingVO<MyBlogPostVO> pagingVO) throws ParseException {
        pagingVO.setTotalRecord(myBlogDAO.selectTotalPostRecord(map));
		
        List<MyBlogPostVO> postList = myBlogDAO.selectPostList(map);
		
		JSONParser parser = new JSONParser();
		for(int i = 0; i <postList.size(); i++) {
			String strCt = postList.get(i).getPostContent();
			  
			JSONObject jsonObject = (JSONObject) parser.parse(strCt);
			 Object blocks = jsonObject.get("blocks");
			  
			  log.info("블록스~~ {}",blocks );
			  postList.get(i).setJsonContents(blocks);
		}
		
       	pagingVO.setDataList(postList);
        
	}
	
	@Override
	public void retrieveShareList(Map<String, Object> map, PagingVO<MyBlogPostVO> pagingVO) {
        pagingVO.setTotalRecord(myBlogDAO.selectTotalShareRecord(map));
       	List<MyBlogPostVO> postList = myBlogDAO.selectShareList(map);
       	pagingVO.setDataList(postList);
	}
	
	
	/**
	 * 블로그 게시글 조회
	 */
	@Override
	public MyBlogPostVO retrievePost(String memId, String postNum) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("memId", memId);
		map.put("postNum", postNum);
		
		MyBlogPostVO post = myBlogDAO.selectPost(map);
		
		if(post == null) {
			throw new NotFoundException();
		}
		
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated() && !SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            MemberVO mv=((MemberPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRealMember();
            
            if(mv.getMemId().equals(memId) || checkShareList(mv.getMemId(), postNum)) {
            	sendError(post);
        		
            } else {
            	sendError(post);
        		if(StringUtils.equals(post.getPostPublicYn(), "N")) {
        			throw new NotAuthorityException();
        		}
            }
            
         }else{
        	sendError(post);
     		if(StringUtils.equals(post.getPostPublicYn(), "N")) {
     			throw new NotAuthorityException();
     		}
            
         }
        
        myBlogDAO.incrementHit(postNum);
		return post;
	}
	
	public boolean checkShareList(String memId, String postNum) {
		Map<String, String> map = new HashMap<>();
		map.put("memId", memId);
		map.put("postNum", postNum);
		
		int checkId = myBlogDAO.checkShareYn(map);
		
		boolean shreYn = false;
		
		if(checkId > 0) {
			shreYn = true;
		}
		
		return shreYn;
	}
	
	public void sendError(MyBlogPostVO post) {
		if(StringUtils.equals(post.getPostBlindYn(), "Y")) {
			throw new NotFoundException(String.format("'%s' 게시글은 차단된 게시글입니다.", post.getPostTitle()));
		} else if(StringUtils.equals(post.getPostDelYn(), "Y")) {
			throw new NotFoundException(String.format("'%s' 게시글이 없습니다.", post.getPostTitle()));
		}
	}
	
	// 게시글 비공개 처리
	@Transactional
	@Override
	public ServiceResult postPrivate(Map<String, String> map) {
		
		int privatePost = myBlogDAO.privatePost(map);
		ServiceResult result = null;
		
		if(privatePost > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAIL;
		}
		
		return result;
	}
	
	
	@Transactional
	@Override
	public ServiceResult delPost(String memId, String postNum) {
		int delPost = myBlogDAO.delPost(postNum);
		ServiceResult result = null;
		
		if(delPost > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAIL;
		}
		
		return result;		
	}
	
	@Override
	public List<TodoVO> retrieveTodo(Map<String, String> map) {
		List<TodoVO> todoList = myBlogDAO.selectTodoList(map);
		return todoList;
	}
	
	@Transactional
	@Override
	public ServiceResult addTodo(TodoVO todo) {
		int insertTodo = myBlogDAO.insertTodo(todo);
		ServiceResult result = null;
		
		if(insertTodo > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAIL;
		}
		
		return result;	
	}
	
	@Transactional
	@Override
	public ServiceResult upTodo(TodoVO todo) {
		int upTodo = myBlogDAO.updateTodo(todo);
		ServiceResult result = null;
		
		if(upTodo > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAIL;
		}
		
		return result;	
	}
	
	@Transactional
	@Override
	public ServiceResult ynTodo(TodoVO todo) {
		int upYN = myBlogDAO.updateYn(todo);
		ServiceResult result = null;
		
		if(upYN > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAIL;
		}
		
		return result;	
	}
	
	@Transactional
	@Override
	public ServiceResult delTodo(String checkNum) {
		int delTodo = myBlogDAO.delTodo(checkNum);
		ServiceResult result = null;
		
		if(delTodo > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAIL;
		}
		
		return result;	
	}
	
	@Transactional
	@Override
	public ServiceResult postShare(ShareVO share) {
		int insertShare = myBlogDAO.insertShare(share);
		ServiceResult result = null;
		
		if(insertShare > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAIL;
		}
		
		return result;	
	}
	
	@Override
	public void postShareMemberList(String postNum, PagingVO<ShareVO> pagingVO) {
		Map<String, Object> map = new HashMap<>();
		map.put("postNum", postNum);
		map.put("pagingVO", pagingVO);
		
       	pagingVO.setTotalRecord(myBlogDAO.selectTotalShareMemberList(postNum));
       	List<ShareVO> shareList = myBlogDAO.selectShareMemberList(map);
       	pagingVO.setDataList(shareList);
		
	}
	
	@Transactional
	@Override
	public ServiceResult delShare(Map<String, String> map) {
		int delShare = myBlogDAO.delShare(map);
		ServiceResult result = null;
		
		if(delShare > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAIL;
		}
		
		return result;	
	}

	
}
