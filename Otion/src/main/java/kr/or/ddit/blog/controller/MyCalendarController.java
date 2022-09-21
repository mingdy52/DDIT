package kr.or.ddit.blog.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;


import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.blog.service.MyBlogService;
import kr.or.ddit.blog.service.MyCalendarService;
import kr.or.ddit.blog.vo.BlogCateVO;
import kr.or.ddit.blog.vo.MyCalendarVO;
import kr.or.ddit.common.login.vo.MemberPrincipal;
import kr.or.ddit.common.validate.DeleteGroup;
import kr.or.ddit.common.validate.InsertGroup;
import kr.or.ddit.common.validate.UpdateGroup;
import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.proj.calendar.vo.CalendarVO;
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
 * </pre>
 */
@Slf4j
@Controller
@RequestMapping("/blog/{memId}/calendar")
public class MyCalendarController {
	@Inject
	MyCalendarService calendarService;
	
	@Inject
	MyBlogService blogService;
	
	
	@ModelAttribute("myCalendarVO")
	public MyCalendarVO getCalendarVO() {
		return new MyCalendarVO();
	}
	//사이드메뉴
	@ModelAttribute("blogCateList")
	public List<BlogCateVO> blogCateList(
			@PathVariable("memId") String memId
			) {
		List<BlogCateVO> blogCateList = blogService.retrieveCate(memId);
		return blogCateList;
	}
	
	@GetMapping
	public String getMyCalList(
			@PathVariable String memId
			) {
		String viewName=null;
		if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated() && !SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
			MemberVO mv=((MemberPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRealMember();
			if(memId.equals(mv.getMemId())) {
				viewName="myblog/calendar";		
			}else {
				 viewName="myblog/check";
			}
		}else {
			viewName="redirect:/login"; 
		}
		return viewName;
	}
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<Map<String,Object>> getMyCalLJsonList(
			@PathVariable("memId")String memId
			,Model model
			){
		//로그인한 회원의 정보 가져오기
		MemberVO mv=((MemberPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRealMember();
		String sessionId=mv.getMemId();
		
		//회원의 캘린더 가져오기
		List<MyCalendarVO> MycalList =calendarService.retriveMycalList(sessionId);
		Calendar cal=Calendar.getInstance();
		
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
		List<Map<String, Object>> list = new ArrayList<>();
		
		for(MyCalendarVO single : MycalList) {
			Map<String, Object> map = new HashMap<>();
			map.put("calId", single.getCalNum());
			map.put("title", single.getCalTitle());
			map.put("content",single.getCalContent());
			Date startDay=null;
			try {
				startDay = sdf.parse(single.getCalStart());
				cal.setTime(startDay);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(sdf.format(cal.getTime()).toString().equals(single.getCalEnd())) {
				map.put("textColor", "#000000" );			
			}else {
				map.put("color", single.getCalColor());			
			}
			map.put("start", single.getCalStart());
			map.put("end", single.getCalEnd());
			map.put("calNum", single.getCalNum());
			list.add(map);
		}
		log.info("{}", list);
		return list;
	}
	//일정등록
	@PostMapping
	public String insertMyCalList(
			@Validated(InsertGroup.class) @ModelAttribute MyCalendarVO myCalendarVO
			,Errors errors
			,@PathVariable String memId
			,SessionStatus sessionStatus
			,RedirectAttributes redirectAttributes
			) {
		MemberVO mv = ((MemberPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRealMember();
		if(!errors.hasErrors()) {
			String sessionId=mv.getMemId();
			myCalendarVO.setBlogerId(sessionId);
			int cnt=calendarService.createMyCal(myCalendarVO);
			if(cnt>0) {
				redirectAttributes.addFlashAttribute("message", "일정등록이 되었습니다.");
				
			}else {
				 redirectAttributes.addFlashAttribute("message", "일정등록에 실패했습니다. 다시 해주세요");
			}
		}else {
			 redirectAttributes.addFlashAttribute("message", "입력이 누락되었거나 형식에 맞지않습니다.");
			
		}
		
		return "redirect:/blog/{memId}/calendar";
	}
	//일정수정
	@PutMapping
	public String updateCalList(
			@Validated(UpdateGroup.class)@ModelAttribute MyCalendarVO myCalendarVO
			,Errors errors
			,Model model
			,RedirectAttributes redirectAttributes
			) {
		log.info("vo:{}",myCalendarVO);
		String viewName="";
		if(!errors.hasErrors()) {
			int cnt=calendarService.modifyCalendar(myCalendarVO);
			log.info("숫자숫자:{}",cnt);
			if(cnt>0) {
				redirectAttributes.addFlashAttribute("message", "수정 되었습니다.");
				
			}else {
				redirectAttributes.addFlashAttribute("message", "일정수정 실패, 다시 해주세요");	
			}
			
		}else {
			redirectAttributes.addFlashAttribute("message", "입력이 누락되었거나 형식에 맞지않습니다.");
			
		}
		log.info("ckk: "+viewName);
		return "redirect:/blog/{memId}/calendar";
	}
	//일정 삭제
	@DeleteMapping
	public String deleteCalendar(
			@Validated(DeleteGroup.class) @ModelAttribute MyCalendarVO myCalendarVO
			,Errors errors
			,RedirectAttributes redirectAttributes
			) {
		String viewName=null;
		log.info("삭제{}",myCalendarVO.getCalNum());
		if(!errors.hasErrors()) {
			int cnt=calendarService.removeCalendar(myCalendarVO.getCalNum());
			if(cnt>0) {
				redirectAttributes.addFlashAttribute("message", "삭제 되었습니다");
				
				
			}else {
				redirectAttributes.addFlashAttribute("message", "일정삭제 실패, 다시 해주세요");	
				
			}
		}else {
			redirectAttributes.addFlashAttribute("message", "삭제할 일정이 없습니다.");	
			
		}
		return "redirect:/blog/{memId}/calendar";
		
	}
}

