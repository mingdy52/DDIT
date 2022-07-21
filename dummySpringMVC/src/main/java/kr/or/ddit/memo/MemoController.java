package kr.or.ddit.memo;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.InsertGroup;
import kr.or.ddit.memo.service.MemoService;

/**
 * /memo/write.do (작성)
 * /memo/list.do (전체 메모 조회)
 *
 */
@Controller
@RequestMapping("/memo")
public class MemoController {
	
	@Inject
	private MemoService service;
	
	@ModelAttribute("memo")
	public MemoVO memo() {
		return new MemoVO();
	}
	// 이렇게 사용하는 이유는 memo 객체를 재사용하기 위해서.
	// 이렇게 하면 우리가 호출하는게 아니고 핸들러가 호출함
	// 실행 순서 1
	
	// 실행 순서 2
	// 메모 작성 기능, 작성된 메모 조회
	@GetMapping("write.do")
	public String memoForm() {
		return "memo/memoForm";
	}
	
	// 실행 순서 2
	@PostMapping("write.do")
	public String memoWrite(@Validated(InsertGroup.class)/*그룹 검증 가능*/ @ModelAttribute("memo") MemoVO memo // command object
//									위에 만들어진 memoVO 를 가지고 옴. 이미 VO 안에는 필요한 데이터를 받은 VO 가 들어 있음.
									, Errors errors // MemoVO 의 모든 검증 결과를 가지고 있음.
									, RedirectAttributes redirectAttributes
								) {
		
		if(errors.hasErrors()) {
			return "memo/memoForm";
			
		} else {
			Map<Integer, MemoVO> memoList = service.createMemo(memo);
			redirectAttributes.addFlashAttribute("memoList", memoList);
			return "redirect:/memo/list.do";
		}
	}
	
	@GetMapping("list.do")
	public ModelAndView getListHandler() {
		Map<Integer, MemoVO> memo = service.retrieveMemoList();
		Collection<MemoVO> memoList = memo.values();
		ModelAndView mav = new ModelAndView();
		mav.addObject("memoList", memoList); // model.addAttribute
		mav.setViewName("memo/memoList");
		return mav;
	}
	
	
	
}
