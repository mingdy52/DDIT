package kr.or.ddit.cal;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * /calculate_version2.do
 * @return
 */
@Controller
@RequestMapping(value="/calculate_version3.do")
public class CalculateControllerVersion3 {
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView getHandler() {
			// 이 객체로 모델과 뷰를 한번에 보낼 수 잇음.
//		return "cal/calForm";
			
		ModelAndView mav = new ModelAndView();
		mav.setViewName("cal/calForm");
		
		Date today = new Date();
		mav.addObject("today", today);
		
		return mav;
	}
	
	@ResponseBody // 마셜링 해야하고 말해줌.
	@RequestMapping(method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	// produces -> Accept request header, Content-Type response header
	// 동기 비동기를 Accept request header 가 구분해줌.
	// 핸들러는 요청이 들어왔을 때 마임을 체크함. 
	public CalculateVO postJsonHandler(CalculateVO calVO/*@ModelAttribute("calculateVO") 가 있는 것처럼 동작함.*/) {
		// 마셜링, 직렬화 핸들러가 대신 해줌
		
		return calVO;
	}
	
	@ResponseBody // String 은 로지컬 뷰 네임의 리턴 타입과 같음. 같은 리턴타입을 가지고 있더라도 이 어노테이션의 유무에 따라 사용 방식이 다름. 없으면 로지컬, 있으면 응답데이터 구성.
	@RequestMapping(method=RequestMethod.POST, produces=MediaType.TEXT_HTML_VALUE)
	// method 설정을 안해주면 get 방식과 같은 핸들러임.
	public String postHtmlHandler(CalculateVO calVO) {
		String expression = calVO.getExpression();
		return expression;
	}
	
//	@RequestMapping(method=RequestMethod.POST)
	public void postHandler_bak(
							@ModelAttribute("calVO") CalculateVO calVO 
							, RedirectAttributes redirectAttributes
							, HttpServletResponse resp
							) throws StreamWriteException, DatabindException, IOException {
		
		resp.setContentType("application/json;charset=UTF-8"); // 제이슨으로 내보낼거라고 알려줌.
		
		// marshalling -> serialization : 출력을 통해 결과를 내보내야 함.: writeValue 안할거면 asstream.
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(resp.getWriter(), calVO);
	}
	
}
