package kr.or.ddit.member.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.stax2.validation.Validatable;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.DelegatingViewResolver;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberDelete.do")
public class MemberDeleteServlet extends HttpServlet {
	MemberService service = new MemberServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		// 초기 값을 가지고 있는 수정 양식 제공. member/memberForm 재활용
		String password = req.getParameter("password");
		HttpSession session = req.getSession();
		MemberVO authMember = (MemberVO) session.getAttribute("authMember");
		String memId = authMember.getMemId();
		ServiceResult result = service.removeMember(MemberVO.builder().memId(memId).memPass(password).build());
		
		String viewName = "redirect:/myPage.do";
		switch (result) { // 인증에서 사용하는 방법은 모두 redirect
			case INVALIDPASSWORD:
				session.setAttribute("message", "비밀번호 오류");
				break;
			case FAIL:
				session.setAttribute("message", "서버 오류"); // 리다이렉트니까 리퀘스트 안돼
				break;
			default:
//				session.invalidate();
				viewName = "forward:/login/logout.do";
				break;
		}
			
		new DelegatingViewResolver().viewResolve(viewName, req, resp);
		
	}
	

//	1. 탈퇴 누르면 이중 인증하려고 한번더 비번 인증이 필요함. - 비밀번호 입력 UI
//	2. 서버쪽으로 가져가야할 데이터는 회원 아이디(세션)랑 비밀번호 - 포스트 방식으로 넘기기 - 모달 사용하기
//	3. 비밀번호 누락 - 400
//	4. 실시간 서비스와 분리된 배치처리 해주기. 멀티스레드 
}
