package kr.or.ddit.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author 이아인
 * @since 2022. 8. 5.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 5.      이아인       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * 블로그 작성하는 컨트롤러
 */
@Slf4j
@Controller
@RequestMapping("/blog")
public class BlogPostController {
	
	
	@PostMapping
	public String insertPost() {
		return "myblog/blodPost";
	}
	@PutMapping
	public String updatePost() {
		return "myblog/blogPost";
	}
}
