package kr.or.ddit.proj.calendar.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.common.login.vo.MemberPrincipal;
import kr.or.ddit.common.validate.DeleteGroup;
import kr.or.ddit.common.validate.InsertGroup;
import kr.or.ddit.common.validate.UpdateGroup;
import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.common.vo.WorkCalSearchVO;
import kr.or.ddit.proj.calendar.service.CalendarService;
import kr.or.ddit.proj.calendar.vo.CalendarVO;
import kr.or.ddit.proj.colleague.service.ColleagueService;
import kr.or.ddit.proj.colleague.vo.ColleagueVO;
import kr.or.ddit.proj.main.service.ProjectService;
import kr.or.ddit.proj.main.vo.ProjectVO;
import kr.or.ddit.proj.prod.service.ProjectProdService;
import kr.or.ddit.proj.prod.vo.ProjectPaymentVO;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 고정현
 * @since 2022. 8. 5.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 5.      고정현       최초작성
 * 2022. 8. 17.     고정현       캘린더 조회, 일정 삽입
 * 2022. 8. 18.     고정현       캘린더 일정 수정, 일정 삭제
 * 2022. 9. 1.      고정현       접근 제한 막기
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Controller
@Slf4j
@RequestMapping("/project/{pjId}/calendar")
public class CalendarController {

	@Inject
	CalendarService calendarService;
	
	@Inject
	ColleagueService colleagueService;
	
	@Inject
	private ProjectService projectService;
	
	@Inject
	private ProjectProdService prodService;
	
	@ModelAttribute("calendarVO")
	public CalendarVO getCalendarVO() {
		return new CalendarVO();
	}
	
	@GetMapping
	public String getMyCalList(
			@PathVariable String pjId,
			Authentication auth,
			Model model,
			RedirectAttributes res
			) {
		if("none".equals(pjId) || StringUtils.isBlank(pjId)) {
			res.addFlashAttribute("message", "프로젝트를 선택해야 볼 수 있습니다.");
			return "redirect:/project";
		}
		
		if(auth == null) {
			res.addFlashAttribute("message", "로그인 후 이용하실수 있습니다.");
			return "redirect:/";
		}
		String memId = auth.getName();
		
		ColleagueVO colleagueVO = new ColleagueVO();
		colleagueVO.setMemId(memId);
		colleagueVO.setPjId(pjId);
		
		try {
			res.addFlashAttribute("message", "로그인 후 이용하실수 있습니다.");
			colleagueVO = colleagueService.retrieveColleague(colleagueVO);
		}catch(Exception e) {
			return "redirect:/";
		}
		
		ProjectVO project = new ProjectVO();
		project = projectService.retrieveProject(pjId);
		// 해당 프로젝트가 결제 되었는지 확인 유무
		ProjectPaymentVO payVO = prodService.retrievePayment(pjId);
		if (payVO == null) {
			return "project2/projectHome";
		}

		model.addAttribute("project", project);
		
		return "project2/cal";
	}
	
	// 캘린더 그릴때 사용하는 json
	@GetMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<Map<String, Object>> getMyCalJsonList(
			@PathVariable("pjId") String pjId,
			@ModelAttribute WorkCalSearchVO SearchVO,
			Authentication auth,
			Model model
			) {
		log.info("############{}",SearchVO);
		
		if(pjId == null) {
			model.addAttribute("message", "해당 프로젝트트 존재하지 않습니다.");
			return null;
		}
		// 로그인한 회원의 정보 가져오기
		MemberVO member = ((MemberPrincipal)auth.getPrincipal()).getRealMember();
		// 팀원 정보 유무 찾기
		ColleagueVO colleagueVO = new ColleagueVO();
		colleagueVO.setMemId(member.getMemId());
		colleagueVO.setPjId(pjId);
		colleagueVO = colleagueService.retrieveColleague(colleagueVO);
		if(colleagueVO == null) {
			model.addAttribute("message", "해당 회원이 프로젝트에 없습니다.");
			return null;
		}
		// 검색 조건 넣기
		colleagueVO.setSearchVO(SearchVO);
		
		// 해당 팀원의 업무에 대한 캘린더를 가져옴
		List<CalendarVO> calendarList = calendarService.retrieveCalendarList(colleagueVO);
		Calendar cal = Calendar.getInstance();
		
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
		List<Map<String, Object>> list = new ArrayList<>();
		// map형태로 받아서 list에 저장하여 JSON데이터 형태를 만들어줌
		for(CalendarVO single : calendarList) {
			Map<String, Object> map = new HashMap<>();
			map.put("publicId", single.getWorkCalNum());
			map.put("title", single.getWorkCalTitle());
			Date startDay = null;
			try {
				startDay = sdf.parse(single.getWorkCalStart());
				cal.setTime(startDay);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(sdf.format(cal.getTime()).toString().equals(single.getWorkCalEnd())) {
				map.put("textColor", "#000000" );			
			}else {
				map.put("color", single.getWorkCalColor());			
			}
			map.put("start", single.getWorkCalStart());
			map.put("end", single.getWorkCalEnd());
			list.add(map);
		}
		log.info("{}", list);
		return list;
	}
	
	@GetMapping(value="view",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public CalendarVO getCalendarView(
			@PathVariable("pjId") String pjId,
			@RequestParam String workCalNum
			) {
		if(pjId == null) {
			return null;
		}
		// 로그인한 회원의 정보 가져오기
		MemberVO member = ((MemberPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRealMember();
		// 팀원 정보 유무 찾기
		ColleagueVO colleagueVO = new ColleagueVO();
		colleagueVO.setMemId(member.getMemId());
		colleagueVO.setPjId(pjId);
		
		ColleagueVO myColleague = colleagueService.retrieveColleague(colleagueVO);
		
		CalendarVO calendarVO = new CalendarVO();
		calendarVO.setColNum(myColleague.getColNum());
		calendarVO.setWorkCalNum(workCalNum);
		
		// 해당 팀원의 업무에 대한 캘린더를 가져옴
		CalendarVO calendarView = calendarService.retrieveCalendarView(calendarVO);
		
		return calendarView;
	}
	
	@PostMapping 
	public String insertCalList(
			@Validated(InsertGroup.class) @ModelAttribute CalendarVO calendar,
			Errors errors,
			@PathVariable String pjId
			) {
		// 로그인 유저의 정보가 있나 확인
		MemberVO member = ((MemberPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRealMember();
		// 0. insert한 값이 모두잘 넘어왓는가?
		if(!errors.hasErrors()) {
			// 1. 로그인한 유저가 해당 프로젝트에 대한 정보가 있는가?
			ColleagueVO colleagueVO = new ColleagueVO();
			colleagueVO.setPjId(pjId);
			colleagueVO.setMemId(member.getMemId());
			log.info("*************{} : {}",pjId, colleagueVO);
			try {
				colleagueVO = colleagueService.retrieveColleague(colleagueVO);			
			}catch(NullPointerException e) {
				return "project/cal";
			}
			// 해당 프로젝트에 유저가 팀원으로 들어가있다면 해당 일정을 넣어줌
			calendar.setColNum(colleagueVO.getColNum());
			calendarService.createCalendar(calendar);
			
			return "redirect:/project/{pjId}/calendar";
		}else {
			// 에러가 존재할 시 다시 원래jsp로 이동
			return "project/cal";
		}
		
	}
	
	@PutMapping
	public String updateCalList(
			@Validated(UpdateGroup.class) @ModelAttribute CalendarVO calendar,
			Errors errors,
			Model model
			) {
		
		// 에러 유무를 통해 원하는 값 다들어왓나 파악
		if(!errors.hasErrors()) {
			log.info("*****************{}", calendar);
			calendarService.modifyCalendar(calendar);
			return "redirect:/project/{pjId}/calendar";
		}else {
			model.addAttribute("message", "필수 파라미터가 빠졌습니다.");
			return "project/cal";
		}
		
	}
	
	@DeleteMapping
	public String deleteCalendar(
			@Validated(DeleteGroup.class) @ModelAttribute CalendarVO calendar,
			Errors errors,
			Model model,
			RedirectAttributes redirectAttributes
			) {
		if(!errors.hasErrors()) {
			calendarService.removeCalendar(calendar.getWorkCalNum());
			redirectAttributes.addFlashAttribute("message", "일정 삭제가 되었습니다");
			return "redirect:/project/{pjId}/calendar";
		}else {
			model.addAttribute("message", "해당 일정이 존재하지 않습니다.");
			return "project/cal";
		}
		
	}
}
