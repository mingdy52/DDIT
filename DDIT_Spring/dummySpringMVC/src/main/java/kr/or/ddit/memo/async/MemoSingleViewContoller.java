package kr.or.ddit.memo.async;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemoSingleViewContoller {
	
	@RequestMapping("/memo/async/view.do")
	public String singleView() {
		
		return "memo/memoSingleView";
	}
	
}
