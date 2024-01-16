package basic;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * 서블릿 3.0부터 지원하는 Part 인터페이스를 이용한 파일 업로드 예제
 * 
 * multipart/form-data POST형식으로 전송된 데이터는 
 * request.getParameter와 같은 메서드로 접근할 수 없으며, 
 * servlet 3.0에 새롭게 추가된 Part 인터페이스를 사용해서 접근해야 한다.
 * 
 * @author 306-16
 *
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 3, 
maxFileSize = 1024 * 1024 * 40, maxRequestSize = 1024 * 1024 * 50)
@WebServlet("/fileupload2.do")
public class FileUploadServlet2 extends HttpServlet {
	
	private static final String UPLOAD_DIR = "upload_files";
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String uploadPath = getServletContext().getRealPath("/") + File.separator + UPLOAD_DIR;
		
		File uploadDir = new File(uploadPath);
		
		if(!uploadDir.exists()) { // 업로드 경로가 존재하지 않으면
			uploadDir.mkdir();
		}
		
		try {
			String fileName = "";
			
			for(Part part : req.getParts()) {
				System.out.println(part.getHeader("content-disposition"));
				
				fileName = getFileName(part);
				if(fileName != null && !fileName.equals("")) {
					// 폼필드가 아니거나  파일이 첨부된 경우
					
					// 파일 저장
					part.write(uploadPath + File.separator + fileName);
					System.out.println("파일명: " + fileName + " 업로드 완료");
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("파라미터 값: " + req.getParameter("sender"));
		
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().print("업로드 완료됨");
		
	}
	
	/**
	 * Part 객체를 파싱하여 파일이름 추출하기
	 * @param part
	 * @return 파일명: 파일명이 존재하지 않으면 널값 리턴함(폼필드인 경우)
	 */
	private String getFileName(Part part) {
		
		/*
		 	Content-Disposition 헤더에 대하여
		 	
		 	1. 응답헤더에 사용되는 경우 ex) 파일 다운로드
		 	Content-Disposition: inline(default)
		 	Content-Disposition: attachment		// 파일 다운로드
		 	Content-Disposition: attachment; filename="a.jpg" // a.jpg이름으로 다운로드
		 	
		 	2. multipart body를 위한 헤더 정보로 사용되는 경우 ex) 파일 업로드
		 	Content_Disposition: form-data
		 	Content_Disposition: form-data; name="fieldName"
		 	Content_Disposition: form-data; name="fieldName"; filename="a.jpg"
		 */
		
		for(String content : part.getHeader("Content-Disposition").split(";")) {
			System.out.println(content);
			if(content.trim().startsWith("filename")) {
					return content.substring(content.indexOf("=") + 1).trim().replace("\"", "");
			}
			System.out.println(content);
		}
		return null; // filename이 없는 경우 (폼필드)
	}
}
