package kr.or.ddit.member.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.jasypt.util.text.AES256TextEncryptor;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.DelegatingViewResolver;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.ValidateUtils;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberInsert.do") // RESTful URI(x)
public class MemberInsertServlet extends HttpServlet {
	
	MemberService service = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		req.setAttribute("contents", "/WEB-INF/views/member/memberForm.jsp");
//		String view = "/WEB-INF/views/template.jsp";
//		req.getRequestDispatcher(view).forward(req, resp);
		
		String viewName = "/member/memberForm.tiles";
		new DelegatingViewResolver().viewResolve(viewName, req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberVO member = new MemberVO();
//		member.setMemId(req.getParameter("memId"));
		// setMemId / memId 이름이 같기 때문에 여기서 리플렉션을 사용할 수 있음.
		req.setAttribute("member", member);
		
		try {
			BeanUtils.populate(member, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new ServletException(e);
			// 위에 이미 예외를 던지고 있음. 꼭 런타임만 던지는건 아님.
		}
		

		
		Map<String,String> errors = new LinkedHashMap<>();
	    req.setAttribute("errors", errors); //실패한게 뭔지도 알려줘야하니까 가지고가기위해
	    boolean valid =ValidateUtils.validate(member,errors, InsertGroup.class);
	    String viewName =null;

		if(valid) {
			ServiceResult result = service.createMember(member);
			switch (result) {
			case PKDUPLICATED:
				req.setAttribute("message", "아이디 중복");
				viewName = "/member/memberForm.tiles";
				break;
			
			case FAIL:
				// 서버상 문제니까 에러메시지를 보내면 안돼. 클라이언트가 무슨 문제인지 모르게 폼으로 다리 돌려~!
				req.setAttribute("message", "서버 문제로 등록 못함. 나중에 다시 ㄱ");
				viewName = "/member/memberForm.tiles";
				break;
				
			default:
//				req.setAttribute("message", "등록 성공");
				// 리다이렉트라 못씀
				
				req.getSession().setAttribute("message", "등록 성공");
				viewName = "redirect:/login/loginForm.jsp";
				break;
				
			}
	     } else { //검증 실패 -> 정상인데이터가지고(memberVO안에존재)
	          viewName ="/member/memberForm.tiles";
	     }

		
		new DelegatingViewResolver().viewResolve(viewName, req, resp);
	}

/*
	private boolean validate(MemberVO member, Map<String, String> errors) {
		
		boolean valid = true;
		
		if(StringUtils.isBlank(member.getMemId())) {
			errors.put("memId", "회원아이디 누락");
			valid = false;
		}
		if(StringUtils.isBlank(member.getMemPass())) {
			errors.put("memPass", "비밀번호 누락");
			valid = false;
		}
		return valid;
	} -> hibernate validator 로 대체
	
*/
}
