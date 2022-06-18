package kr.or.ddit.servlet06;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/07/dditProcess.do")
public class DDITStudentRegistServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/*
		1. 요청 분석 - controller
			1) 요청 검증
				- 파라미터 확보
				- 검증
				- 통과
					2) 요청 컨텐츠(model) 생성
						-> 등록 완료 메시지를 가지고 (/07/resultView.jsp 로 이동)
				- 불통
					3) 입력 UI 로 복귀 (메시지, 기존 클라이언트 입력 값.) 

		2. 응답 생성 - view
*/		
		req.setCharacterEncoding("UTF-8");
		Enumeration<String> nameParam =  req.getParameterNames();
		while(nameParam.hasMoreElements()) {
			String name = nameParam.nextElement();
			String[] value = req.getParameterValues(name);
			req.setAttribute(name, value);
		}
		
		req.getRequestDispatcher("/07/resultView.jsp").forward(req, resp);
		
	}
	
}
