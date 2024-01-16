package kr.or.ddit;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//@WebServlet("/index.do")
@Controller
public class IndexController {
	
	@RequestMapping("/")
	public String indexPage() {
		String viewName = "index";
		return viewName;
				
	}

}
