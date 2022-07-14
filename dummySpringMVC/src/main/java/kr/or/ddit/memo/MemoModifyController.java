package kr.or.ddit.memo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.UpdateGroup;

@Controller
@RequestMapping("/memo")
public class MemoModifyController {
		
	@GetMapping("memoUpdate.do")
	public String updateForm(@RequestParam int code, Model model) {
		MemoVO memo = MemoController.memoTable.get(code);
		model.addAttribute("memo", memo);
		return "memo/memoForm";
	}
	
	@PostMapping("memoUpdate.do")
	public String updateProcess(@Validated(UpdateGroup.class) @ModelAttribute("memo") MemoVO memo
								, BindingResult errors // Errors 의 하위 클래스
								) {
		if(!errors.hasErrors()) {
			MemoController.memoTable.put(memo.getCode(), memo);
			return "redirect:/memo/list.do";
		} else {
			return "memo/memoForm";
			
		}
	}
	
}
