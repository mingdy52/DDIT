package kr.or.ddit.memo;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.UpdateGroup;
import kr.or.ddit.memo.service.MemoService;

@Controller
@RequestMapping("/memo")
public class MemoModifyController {
	@Inject
	MemoService service;
		
	@GetMapping("memoUpdate.do")
	public String updateForm(@RequestParam int code, Model model) {
		MemoVO memo = service.retrieveMemo(code);
		model.addAttribute("memo", memo);
		return "memo/memoForm";
	}
	
	@PostMapping("memoUpdate.do")
	public String updateProcess(@Validated(UpdateGroup.class) @ModelAttribute("memo") MemoVO memo
								, BindingResult errors // Errors 의 하위 클래스
								, RedirectAttributes redirectAttributes
								) {
		if(!errors.hasErrors()) {
			Map<Integer, MemoVO> memoList = service.modifyMemo(memo);
			redirectAttributes.addFlashAttribute("memoList", memoList);
			return "redirect:/memo/list.do";
		} else {
			return "memo/memoForm";
			
		}
	}
	
}
