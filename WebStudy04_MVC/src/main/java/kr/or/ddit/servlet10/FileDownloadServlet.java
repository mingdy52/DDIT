package kr.or.ddit.servlet10;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

@WebServlet("/file/download.do")
public class FileDownloadServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String filePath = req.getParameter("file");
		
		if(StringUtils.isBlank(filePath)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "필수 파라미터 누락");
			return;
		}
		
		File file = new File(filePath);
		
		if(!file.exists()) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, "해당 파일은 없음.");
			return;
		}
		
		String fileName = file.getName();
		fileName = URLEncoder.encode(fileName, "UTF-8").replace("+", "");
//		한글 깨지니까 인코딩 해주고, 공백이 들어갈 경우 공백에 '+'가 추가되는데 그거 화이트스페이스로 바꿔줌
		
		resp.setHeader("Content-Disposition", "attachment;filename=\""+ fileName+"\"");
//		// attachment: 브라우저가 바로 열 수 있는 파일인 경우 다운로드를 누르면 저장되는게 아니고 바로 파일을 열어버림. 
		// attachment 넣어주면 바로열지 않고 다운로드 받음.
		resp.setContentLengthLong(file.length()); // 파일의 길이 가져오기
		
		// commons-compress : 폴더를 압축
		
//		FileInputStream fis = new FileInputStream(file);
//		ServletOutputStream os = resp.getOutputStream(); -- 파일 유틸스로 대체할거임
		
		try(
				OutputStream os = resp.getOutputStream();
			){
			FileUtils.copyFile(file, os);
		}
		
		
	}
	
}
