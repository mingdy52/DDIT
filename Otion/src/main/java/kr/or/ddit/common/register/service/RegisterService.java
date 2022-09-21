package kr.or.ddit.common.register.service;

import java.util.List;

import kr.or.ddit.blog.vo.BlogCateVO;
import kr.or.ddit.blog.vo.CreateBlogVO;
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
public interface RegisterService {
	
	public List<RegisterVO> retrieveMemberList(String memId);
	
	public void createMember(RegisterVO register);
	
	public void createBlog(CreateBlogVO createBlog);

	public void createBlogCategory(BlogCateVO blogCate);

	public List<RegisterVO> retrieveMemberNickList(String memNick);
}
