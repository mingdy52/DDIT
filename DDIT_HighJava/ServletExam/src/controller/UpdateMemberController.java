package controller;

import java.io.IOException;
import java.util.List;

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

@WebServlet("/member/update.do")/*서블릿 구현과 실행*/
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 3,
				maxFileSize = 1024 * 1024 * 40, maxRequestSize = 1024 * 1024 * 50)
public class UpdateMemberController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String memId = req.getParameter("memId");
		
		// 1. 서비스 객체 생성하기
		IMemberService memService = MemberServiceImpl.getInstance();
		IAtchFileService fileService = AtchFileServiceImpl.getInstance();
		
		// 2. 회원정보 조회
		MemberVO mv = memService.getMember(memId);
		
		if(mv.getAtchFileId() > 0) { // 첨부파일 존재하는 경우
			// 2-1. 첨부파일 목록 조회
			AtchFileVO fileVO = new AtchFileVO();
			fileVO.setAtchFileId(mv.getAtchFileId());
			
			List<AtchFileVO> atchFileList = fileService.getAtchFileList(fileVO);
			
			req.setAttribute("atchFileList", atchFileList);
		}
		
		req.setAttribute("mv", mv);
		// 다른 곳으로 정보를 넘겨주기 위해서 request 객체의 속성(attribute)을 사용
		// setAttribute(), getAttribute()에서 속성 값으로 사용하는 타입은 Object
		
		req.getRequestDispatcher("/view/member/updateForm.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		// 1. 요청 파라미터 정보 가져오기
		String memId = req.getParameter("memId");
		String memName = req.getParameter("memName");
		String memTel = req.getParameter("memTel");
		String memAddr = req.getParameter("memAddr");

		// 기존 첨부파일에 대한 ID값
		long atchFileId = Long.parseLong(req.getParameter("atchFileId"));
		
		// 2.서비스 객체 생성
		IMemberService memService = MemberServiceImpl.getInstance();
		IAtchFileService fileService = AtchFileServiceImpl.getInstance();
		
		AtchFileVO atchFileVO = new AtchFileVO();
		
		// 첨부파일 목록 저장(공통기능)
		atchFileVO = fileService.saveAtchFileList(req);
		
		
		// 3. 회원정보 변경
		MemberVO mv = new MemberVO();
		mv.setMemId(memId);
		mv.setMemName(memName);
		mv.setMemTel(memTel);
		mv.setMemAddr(memAddr);
		if(atchFileVO != null) {
			mv.setAtchFileId(atchFileVO.getAtchFileId());
		} else {
			mv.setAtchFileId(atchFileId);
		}
		
		int cnt = memService.updateMember(mv);
		
		String msg = "";
		
		if(cnt > 0) {
			msg = "성공";
		} else {
			msg = "실패";
		}
		
//		req.getRequestDispatcher("/member/list.do").forward(req, resp);
		
		String redirctUrl = req.getContextPath() + "/member/list.do";
		resp.sendRedirect(redirctUrl);
		
	}
}
