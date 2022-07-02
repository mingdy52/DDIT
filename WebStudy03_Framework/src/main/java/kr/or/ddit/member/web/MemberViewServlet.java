package kr.or.ddit.member.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.DelegatingViewResolver;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberView.do")
public class MemberViewServlet extends HttpServlet {
	
	MemberService service = new MemberServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String memId = req.getParameter("who");
		String layout = req.getParameter("layout");
		
		if(StringUtils.isBlank(memId)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "필수 파라미터 누락");
			return;
		}
			
		MemberVO member = service.retrieveMember(memId);
		req.setAttribute("member", member);
		String viewName = null;
		if("grid".equals(layout)) {
			viewName = "/member/memberView.tiles";
			
		} else {
			viewName = "member/memberView";
			
		}
		new DelegatingViewResolver().viewResolve(viewName, req, resp);
	}
	
}
