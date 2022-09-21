package kr.or.ddit.common.register.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.blog.vo.BlogCateVO;
import kr.or.ddit.blog.vo.CreateBlogVO;
import kr.or.ddit.common.register.service.RegisterService;
import kr.or.ddit.common.register.vo.RegisterVO;
import kr.or.ddit.common.validate.InsertGroup;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/register")
public class RegisterController {

	@Inject
	RegisterService registerService;
	
	@ModelAttribute("registerVO")
	public RegisterVO getRegisterVO() {
		
		return new RegisterVO();
	}
	
	@GetMapping
	public String getRegisterForm() {
		
		return "common/register/register";
	}
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<RegisterVO> getMemberIdList(
			@RequestParam String memId
			) {
		List<RegisterVO> memList = registerService.retrieveMemberList(memId);
		if(memList != null) {
			return memList;
		}else {
			
			return null;
		}
		
	}
	@GetMapping(value="nickCheck", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<RegisterVO> getMemberNickList(
			@RequestParam String memNick
			) {
		List<RegisterVO> memList = registerService.retrieveMemberNickList(memNick);
		if(memList != null) {
			return memList;
		}else {
			
			return null;
		}
		
	}
	
	@PostMapping
	public String insertRegister(
			@Validated(InsertGroup.class) @ModelAttribute RegisterVO registerVO,
			Errors errors,
			Model model,
			RedirectAttributes res
			) {
		
		if(!errors.hasErrors()) {
			// 회원가입
			registerService.createMember(registerVO);
			// 회원 가입한 회원의 블로그 생성
			CreateBlogVO createBlog = new CreateBlogVO();
			createBlog.setBlogerId(registerVO.getMemId());
			registerService.createBlog(createBlog);
			BlogCateVO blogCate = new BlogCateVO();
			blogCate.setBlogId(createBlog.getBlogId());
			
			registerService.createBlogCategory(blogCate);
			
			res.addFlashAttribute("message", "회원가입에 성공하셧습니다");
			return "redirect:/login";
		}else {
			model.addAttribute("message", "닉네임을 제외하고 모두 입력해주세요");
			return "common/register/register";
		}
	}
		
}
