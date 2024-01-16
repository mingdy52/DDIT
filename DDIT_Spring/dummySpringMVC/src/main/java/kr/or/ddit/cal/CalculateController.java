package kr.or.ddit.cal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * /calculate_version1.do
 * 
 */
@Controller
@RequestMapping(value="/calculate_version1.do")
public class CalculateController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String getHandler() {
		return "cal/calForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String postHandler(
							@RequestParam long operand1
							, @RequestParam long operand2
							, @RequestParam OperatorType operator
							, Model model // req
							) {
		
		
//		String expression = operator.expression(operand1, operand2);
//		model.addAttribute("expression", expression);
		
		CalculateVO calVO = new CalculateVO(operand1, operand2, operator);
		model.addAttribute("calVO", calVO);
		
		return "cal/calForm";
	}
	
}
