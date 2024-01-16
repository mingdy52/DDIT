package kr.or.ddit.servlet02;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 
 * 서버측에서 클라이언트의 요청을 처리할때 주의할 점.
 * ** 클라이언트의 모든 요청 데이터에 대해 검증이 필요함
 * 1) 요청 파라미터 (46줄)
 * 2) 요청 헤더
 * 3) 요청데이터에 의해 파생되는 데이터(52줄)
 *
 */

@WebServlet("/02/streaming.do")   // /image.do:절대  image.do :상대
public class ImageStreamingServlet extends HttpServlet{
	//servletContext는 한 어플리케이션 내애서 싱글턴으로 관리됨.
	 private ServletContext application;
	
	@Override
		public void init(ServletConfig config) throws ServletException {
			
			super.init(config);
			application =getServletContext();
		}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String folderPath ="d:\\contents";
		File folder = new File(folderPath);
		
		String imageName = req.getParameter("image");	
		if(imageName==null || imageName.isEmpty()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST,"필수 파라미터 누락");
			return;
		}
		File image = new File(folder,imageName);
		//추가
		if(!image.exists()) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, "해당 파일은 존재하지 않음");
			return;
		}
		
		String mime =application.getMimeType(image.getName());
		resp.setContentType(mime);
		
	   FileInputStream fis = new FileInputStream(image);
			
		OutputStream os = resp.getOutputStream();
		byte[] buffer = new byte[1024];
		
		int length =-1;
		while((length =fis.read(buffer))!=-1) {
			os.write(buffer , 0 , length);
		}
		fis.close();
		os.close();
		
	}
}
