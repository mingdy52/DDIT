package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comm.service.AtchFileServiceImpl;
import comm.service.IAtchFileService;
import comm.vo.AtchFileVO;
import service.IMemberService;
import service.MemberServiceImpl;
import vo.MemberVO;

@WebServlet("/member/insert.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 3,
		maxFileSize = 1024 * 1024 * 40, maxRequestSize = 1024 * 1024 * 50)
public class InsertMemberController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/view/member/insertForm.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 1. 요청 파라미터 정보 가져오기
		String memId = req.getParameter("memId");
		String memName = req.getParameter("memName");
		String memTel = req.getParameter("memTel");
		String memAddr = req.getParameter("memAddr");
		// HTTP 요청의 파라미터 값을 얻기 위해 사용하는 것이 request.getParameter() 메소드
		
		
		// 2.서비스 객체 생성
		IMemberService memService = MemberServiceImpl.getInstance();
		IAtchFileService fileService = AtchFileServiceImpl.getInstance();
		
		AtchFileVO atchFileVO = new AtchFileVO();
		
		// 첨부파일 목록 저장(공통기능)
		atchFileVO = fileService.saveAtchFileList(req);
		
		// 3. 회원정보 등록
		MemberVO mv = new MemberVO();
		mv.setMemId(memId);
		mv.setMemName(memName);;
		mv.setMemTel(memTel);
		mv.setMemAddr(memAddr);
		mv.setAtchFileId(atchFileVO.getAtchFileId());
		
		
		int cnt = memService.insertMember(mv);
		
		String msg = "";
		
		if(cnt > 0) {
			msg = "성공";
		} else {
			msg = "실패";
		}
		
//		req.getRequestDispatcher("/member/list.do").forward(req, resp); 
//		jsp에 (req, resp[뷰정보]) 전달
		
		String redirctUrl = req.getContextPath() + "/member/list.do";
		resp.sendRedirect(redirctUrl);
		// url 주소 바뀜(insert.co => list.do)
		// 두 번 요청됌
		
	}
}
