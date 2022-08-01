package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.IMemberService;
import service.MemberServiceImpl;
import vo.MemberVO;

@WebServlet(value = "/member/list.do")/*어노테이션(편함 but 한눈에 보기 어려움) 또는 web.xml(한눈에 보기 쉬움) 둘 중 한 곳에만 설정해 줘야함*/
public class ListMemberController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 1. 서비스 객체 생성
		IMemberService memService = MemberServiceImpl.getInstance();
		
		// 2. 회원정보 조회
		List<MemberVO> memList = memService.getAllMemberList();
		
		req.setAttribute("memList", memList);
		
		RequestDispatcher rd =  
			// RequestDispatcher: new가 아닌 메소드를 이용해서 객체를 생산한다
				req.getRequestDispatcher("/view/member/list.jsp");
					// 객체 생성 메소드. getRequestDispatcher(이동할 페이지 경로(jsp(뷰페이지))
		
		rd.forward(req, resp);
			// RequestDispatcher의 메소드. 페이지를 이동시킴
			// 이동하면서 HttpServletRequest, HttpServletResponse 객체를 함께 넘겨줌.
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
