package kr.or.ddit.common.register.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.blog.vo.BlogCateVO;
import kr.or.ddit.blog.vo.CreateBlogVO;
import kr.or.ddit.common.comcode.dao.ComCodeDAO;
import kr.or.ddit.common.register.dao.RegisterDAO;
import kr.or.ddit.common.register.vo.RegisterVO;

/**
 * @author 작성자명
 * @since 2022. 8. 20.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 17.      고정현      회원가입
 * 2022. 8. 20.      고정현      회원가입 후 블로그 생성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Service
@Transactional
public class RegisterServiceImpl implements RegisterService{

	 @Inject
	 PasswordEncoder passEncoder;
	
	@Inject
	RegisterDAO registerDAO;
	
	@Inject
	ComCodeDAO comCodeDAO;
	
	@Override
	public List<RegisterVO> retrieveMemberList(String memId) {
		// TODO Auto-generated method stub
		return registerDAO.selectMemberList(memId);
	}

	@Override
	public void createMember(RegisterVO register) {
		// TODO Auto-generated method stub
		// 닉네임이 비어있는가 확인
		if(StringUtils.isBlank(register.getMemNick())) {
			register.setMemNick(register.getMemName());
		}
		register.setMemPass(passEncoder.encode(register.getMemPass()));
		registerDAO.insertMember(register);
		comCodeDAO.insertMemberRoleAssignment(register.getMemId());
	}

	@Override
	public void createBlog(CreateBlogVO createBlog) {
		// TODO Auto-generated method stub
		registerDAO.createBlog(createBlog);
	}

	@Override
	public void createBlogCategory(BlogCateVO blogCate) {
		// TODO Auto-generated method stub
		registerDAO.createBlogCategory(blogCate);
	}

	@Override
	public List<RegisterVO> retrieveMemberNickList(String memNick) {
		// TODO Auto-generated method stub
		return registerDAO.selectMemberNickList(memNick);
	}
	
}
