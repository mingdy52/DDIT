package kr.or.ddit.servlet06;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.management.RuntimeErrorException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.vo.DDITStudentVO;

@WebServlet("/07/semdditProcess.do")
public class sem_DDITStudentRegistServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		DDITStudentVO vo = new DDITStudentVO();
		vo.setName(req.getParameter("name"));
		vo.setHp(req.getParameter("hp"));
		req.setAttribute("student", vo);
		
		// 1. reflection 을 몰라도 파라미터를 vo 로 바인딩 하려면??
		Map<String, String[]> parameterMap = req.getParameterMap();
		try {
			BeanUtils.populate(vo, parameterMap); // for문 이걸로 끝낼 수 있따! but lib 적용하기가 귀찮음. 파생 lib까지 추가해줘야함. --> maven으로 해결!
		} catch (IllegalAccessException/*외부 호출 불가*/ | InvocationTargetException/*정상적 Bean이 아님*/ e) {
			// reflection 기술은 불확실성을 기반으로 하기 때문에 여러 예외에 대한 처리가 필요함.
			// 예외의 두가지 종류(checked/unchecked) 중
			// 예외에 대한 제어권이 servlet container 로 전달된 후 500 에러로 처리
			// checked: 반드시 예외처리를 해줘야함
			// unchecked: 예외를 처리하지 않아도 예외가 넘어감 즉, 발생한 예외를 자동으로 톰캣으로 넘기기 위해 사용 ex)RuntimeException. 예외 제어권은 호출자가 가짐(톰캣)
			throw new RuntimeException(e);
		}
		
/*		
  			for(String parameterName : parameterMap.keySet()) {
			Class<? extends DDITStudentVO> type = vo.getClass();
//			Class? extends DDITStudentVO>: 클래스가 DDITStudentVO 서브 클래스임을 뜻함.
			try {
				Field field = type.getDeclaredField(parameterName);
				field.setAccessible(true);
				if(field.getType().equals(int.class)) {
					field.set(vo, Integer.parseInt(req.getParameter(parameterName)));
				} else if(field.getType().equals(String[].class)) {
					field.set(vo, req.getParameterValues(parameterName));
				} else {
					field.set(vo, req.getParameter(parameterName));
				}
				
			
				System.out.println(field);
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
				
			}
			
		}
*/		
		boolean valid =  validate(vo);
		
		String view = null;
		String message = null;
			
		if(valid) {
			message = "등록 완료";
			req.getSession().setAttribute("student", vo);
			req.getSession().setAttribute("message", message);
			view = "redirect:/07/sem_resultView.jsp";
			
		} else {
			message = "등록 실패";
			view = "/07/sem_registForm.jsp";
		}
		
		req.setAttribute("message", message);
		
		if(view.startsWith("redirect:")) {
			view = view.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + view);
			
		} else {
			req.getRequestDispatcher(view).forward(req, resp);
		}
		
		
	
	}
	
	boolean validate(DDITStudentVO vo) {
		boolean valid = true;
		// 2. 문자열 데이터의  empty 여부를 쉽게 확인하려면?
		if(StringUtils.isBlank(vo.getName())) {
			valid = false;
		}
		if(StringUtils.isBlank(vo.getHp())) {
//		if(vo.getHp() == null || vo.getHp().isEmpty()) {
			valid = false;
		}
		return valid;
	}
	
}
