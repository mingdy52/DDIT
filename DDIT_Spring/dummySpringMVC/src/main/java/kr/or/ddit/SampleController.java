package kr.or.ddit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * /sample/test.do 요청에 대한 처리.
 * post 요청이 발생하면, 시스템 콘솔에 사용자의 입력데이터를 출력하고,
 * 입력 데이터들을 가지고, sampleForm 으로 이동.(명령처리 완료됨을 가정함.)
 *
 */
@Controller
public class SampleController {
	private static final Logger log = LoggerFactory.getLogger(SampleController.class);
	
	@RequestMapping("/sample/test.do")
	public String getHandler() {
		String viewName = "sample/sampleForm";
		return viewName;
	}
	
	@RequestMapping(value="/sample/test.do", method=RequestMethod.POST)
	public String postHandler(@RequestParam String param1
							, @RequestParam(required=false, defaultValue="1") int param2
//							, HttpSession session
//							, Model model // req, resp, session 쓰지 말고 쓸 수 있는 다른 것을 찾는게 어댑터를 더 잘 사용할 수 있는 방법.
							, RedirectAttributes redirectAttributes// 리다이렉트로 속성을 전달할 때 사용. 즉, 세션을 대신할 객체임.
							) {
		
		log.info("param1 : {}, param2 : {} ", param1, param2);
		
		redirectAttributes.addFlashAttribute("param1", param1); // 속성을 보내고 지우고를 자동으로 해줌.
		redirectAttributes.addFlashAttribute("param2", param2);
		
//		session.setAttribute("param1", param1);
//		session.setAttribute("param2", param2);
		
//		model.addAttribute("param1", param1);
//		model.addAttribute("param2", param2);
		
//		req.setAttribute("param1", param1);
//		req.setAttribute("param2", param2);
		
		return "redirect:/sample/test.do";
//		return "sample/sampleForm";
	}
	
	
}
