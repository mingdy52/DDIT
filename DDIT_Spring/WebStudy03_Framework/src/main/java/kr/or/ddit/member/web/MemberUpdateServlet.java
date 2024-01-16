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
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.validate.ValidateUtils;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberUpdate.do")
public class MemberUpdateServlet extends HttpServlet {
	MemberService service = new MemberServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 초기 값을 가지고 있는 수정 양식 제공. member/memberForm 재활용
		// 현재 사용자의 개인 정보를 데이터베이스로부터 조회
		MemberVO authMember = (MemberVO) req.getSession().getAttribute("authMember");
		MemberVO member = service.retrieveMember(authMember.getMemId());
		req.setAttribute("member", member);
		
		// 해당 정보를 초기값으로 수정 UI 제공하기 위해 view layer로 이동
		String viewName = "/member/memberForm.tiles";
		new DelegatingViewResolver().viewResolve(viewName, req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		요청 파리미터에 포함된 특수문자에 대한 디코딩 방식 설정.
//		req.setCharacterEncoding("UTF-8"); --> filter
		
//		전달되는 여러개의 파라미터를 Domain layer 를 이용하여 바인딩.
		Map<String, String[]> parameterMap = req.getParameterMap();
		MemberVO member = new MemberVO();
//		해당 VO 는 명령 처리가 완료되기 전까지 공유해야함.
		req.setAttribute("member", member);
		try {
			BeanUtils.populate(member, parameterMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		요청 데이터 검증
		Map<String, String> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = ValidateUtils.validate(member, errors, UpdateGroup.class);
		String viewName = null;
		if(valid) {
//		검증을 통과하면 로직을 사용하여 수정.
			ServiceResult result = service.modifyMember(member);
			switch (result) {
			case INVALIDPASSWORD:
				req.setAttribute("message", "비밀번호 오류");
				viewName = "/member/memberForm.tiles";
				break;
			case FAIL:
				req.setAttribute("message", "서버 오류");
				viewName = "/member/memberForm.tiles";
				break;
			default:
				req.getSession().setAttribute("message", "수정되었습니다.");
				viewName = "redirect:/myPage.do";
				break;
			}
		}else {
//		통과하지 못하면, 기존 입력 데이터와 검증 결과 데이터를 가지고 view layer 로 이동.
			viewName = "/member/memberForm.tiles";
		}
		
		new DelegatingViewResolver().viewResolve(viewName, req, resp);
	}

//	private boolean validate(MemberVO member, Map<String, String> errors) {
//		boolean valid = true;
//		if (StringUtils.isBlank(member.getMemId())) {
//			errors.put("memId", "회원아이디 누락");
//			valid = false;
//		}
//		if (StringUtils.isBlank(member.getMemPass())) {
//			errors.put("memPass", "비밀번호 누락");
//			valid = false;
//		}
//		if (StringUtils.isBlank(member.getMemName())) {
//			errors.put("memName", "회원명 누락");
//			valid = false;
//		}
//		if (StringUtils.isBlank(member.getMemBir())) {
//			errors.put("memBir", "생일 누락");
//			valid = false;
//		}
//		if (StringUtils.isBlank(member.getMemZip())) {
//			errors.put("memZip", "우편번호 누락");
//			valid = false;
//		}
//		if (StringUtils.isBlank(member.getMemAdd1())) {
//			errors.put("memAdd1", "주소1 누락");
//			valid = false;
//		}
//		if (StringUtils.isBlank(member.getMemAdd2())) {
//			errors.put("memAdd2", "주소2 누락");
//			valid = false;
//		}
//		if (StringUtils.isBlank(member.getMemHp())) {
//			errors.put("memHp", "휴대폰 누락");
//			valid = false;
//		}
//		if (StringUtils.isBlank(member.getMemMail())) {
//			errors.put("memMail", "이메일 누락");
//			valid = false;
//		}
//		return valid;
//	}
}