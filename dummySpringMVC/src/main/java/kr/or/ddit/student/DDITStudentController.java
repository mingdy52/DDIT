package kr.or.ddit.student;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.InsertGroup;



@Controller
@RequestMapping("/student")
public class DDITStudentController {
	
	@RequestMapping("resultView.do") // 이렇게 하면 "/student/resultView.do" 이렇게 됌.
	public String resultView() {
		return "student/resultView";
	}
	
	
	@RequestMapping(value="regist.do", method=RequestMethod.GET)
	public String getHandler() {
//		model.addAttribute("gradeMap", makeGradeList());
//		model.addAttribute("licenseMap", makelicenseList());
//		@ModelAttribute 의 타겟은 파라미터와 메소드. 이 속성은 값을 잘못 입력했을 때를 대비해 post에도 넣어줘야 하는데 그럼 중복 코드가 발생.
//		메소드 위에 이 어노테이션을 사용하면 해결. modelattribute로 메소드를 실행했기때문에.  단, 포워드할 경우에만. 
		return "student/registForm";
	}
	
	@RequestMapping(value="regist.do", method=RequestMethod.POST)
	public String postHandler(@Validated(InsertGroup.class)/*어댑터야 이녀석 insert 그룹으로 검증해*/ @ModelAttribute("student") DDITStudentVO studentVO // command object
							, Errors errors // 검증에 대한 모든 정보를 가지고 있음. 검증 정보와 바인딩 정보.
//							, @RequestPart MultipartFile photo // vo 에 알아서 세팅되어 들어옴
							, HttpSession session, Model model
							, RedirectAttributes redirectAttributes) throws IOException {
		
//	    boolean valid = validate(studentVO);
		boolean valid = !errors.hasErrors();
	    
	    String message = null;
	    String view =null;
		if(valid) {
			File saveFolder = new File("d:/contents");
			studentVO.saveTo(saveFolder);
			
			message = "등록 완료";
//			session.setAttribute("student", studentVO);
//			session.setAttribute("message", message); // 리다이렉트니까 세션
			
	        redirectAttributes.addFlashAttribute("student", studentVO);
	        redirectAttributes.addFlashAttribute("message", message);
	        view = "redirect:/student/resultView.do";//redierct는 req가 사라지기때문에 session을 씀
	         //또다른 컨트롤러를이용해서 모델2방식 

			
		} else {
		    message = "등록 실패, 검증 실패";
		    model.addAttribute("message", message); //포워드하기때문에 model이 필요 . 
		    view = "student/registForm";
		}
		   return view;

	}
	
	
	@ModelAttribute("gradeMap")
	public Map<String, String[]> makeGradeList() {
		Map<String, String[]> gradeMap = new LinkedHashMap<>();
		gradeMap.put("G001", new String[]{"G001", "고졸"});
		gradeMap.put("G002", new String[]{"G002", "초대졸"});
		gradeMap.put("G003", new String[]{"G003", "대졸"});
		gradeMap.put("G004", new String[]{"G004", "석사"});
		gradeMap.put("G005", new String[]{"G005", "박사"});
		return gradeMap;
	}
	
	@ModelAttribute("licenseMap")
	public Map<String, String[]> makelicenseList() {
		Map<String, String[]> licenseMap = new LinkedHashMap<>();
		licenseMap.put("L001", new String[]{"L001", "정보처리산업기사"});
		licenseMap.put("L002", new String[]{"L002", "정보처리기사"});
		licenseMap.put("L003", new String[]{"L003", "정보보안산업기사"});
		licenseMap.put("L004", new String[]{"L004", "정보보안기사"});
		licenseMap.put("L005", new String[]{"L005", "SQLD"});
		licenseMap.put("L006", new String[]{"L006", "SQLP"});
		return licenseMap;
	}
}
