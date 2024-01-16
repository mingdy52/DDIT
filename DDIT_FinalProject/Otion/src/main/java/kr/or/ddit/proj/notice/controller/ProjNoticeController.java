package kr.or.ddit.proj.notice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 작성자명
 * @since 2022. 8. 5.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 5.      윤수웅       2022. 8. 5.
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Controller
@RequestMapping("/project/notice")
public class ProjNoticeController {

	@PutMapping
	public String modify() {
		return "/proj/form/createNotice";
	}
	@PostMapping
	public String post() {
		return "/project/projectMain";
	}
	
	@DeleteMapping
	public String delte() {
		return "/project/projectMain";
	}
}
