package kr.or.ddit.cal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * /calculate_version2.do
 * @return
 */
@Controller
@RequestMapping(value="/calculate_version2.do")
public class CalculateControllerVersion2 {
	
	@RequestMapping(method=RequestMethod.GET)
	public String getHandler() {
		return "cal/calForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String postHandler(
							@ModelAttribute("calVO") CalculateVO calVO 
							// command object. 여러개의 데이터를 가진 객체를 한번에 받을 때
							// 단, 넘어오는 속성명과 파라미터명이 일치할 때 사용가능 -> BeanUtils
							
							, RedirectAttributes redirectAttributes
							) {
		
		
//		String expression = operator.expression(operand1, operand2);
//		model.addAttribute("expression", expression);
		
//		calVO = new CalculateVO(operand1, operand2, operator);
//		model.addAttribute("calVO", calVO);
		
//		redirectAttributes.addAttribute("calVO", calVO); // 쿼리 스트링으로 바꿈. 캐스팅 불가라 터짐.
		redirectAttributes.addAttribute("test", "324"); // 쿼리 스트링으로 반영되서 파라미터 사용. 근데 클라이언트가 지우기 전까지 지울 수 없음.
		redirectAttributes.addFlashAttribute("calVO", calVO); // 세션 스코프를 사용함.
//		redirect 에 왜 두 가지 전달 방법이 있나? addAttribute 를 사용하면 세션에 계속 데이터가 쌓이고 무거워짐. 쿼리스트링을 사용하면 그렇지 않음.
		
		return "redirect:/calculate_version2.do";
	}
	
}
