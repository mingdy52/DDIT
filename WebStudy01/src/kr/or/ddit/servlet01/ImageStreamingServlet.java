package kr.or.ddit.servlet01;

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
 * D://contents/snow.jpg 파일을 중계하는 서블릿
 * 이미지 파일을 읽고(input),
 * stream copy
 * 스트리밍 컴텐츠로 출력(output) 
 * Content-Type(MIME) 설정 필요.
 * 1. source 확보 (D://contents/snow.jpg)
 * 2. source 대상 input 스트림 개방
 * 	   FileInputStream
 * 3. destination확보 (response)
 * 4. dest 대상 output 스트림 개방
 * 	  response.getOutputStream
 * 5. in/out stream copy
 *
 */
@WebServlet("/image.do")   // /image.do:절대  image.do :상대
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
		//File source = new File("D://contents/snow.jpg");
		
		//WebStudy01/res/kr/or/ddit/images/cat1.jpg 여기서 클래스패쓰 빼고 경로 사용(내가 쓴것)
		//String srcPath ="/kr/or/ddit/images/cat1.jpg";
		
		String webPath ="/WEB-INF/inner/cat1.jpg" ; //서버 백그라운드 서버 사이드에서 실행 컨텍스트생략함 ..  URL, URI ,상대, 절대
		
	// String filePath = this.getClass().getResource(srcPath).getFile();
		
	//	File source = new File(webPath);
		
				
		String mime =application.getMimeType(webPath);
		resp.setContentType(mime);
//		InputStream is = ImageStreamingServlet.class.getResourceAsStream(srcPath);
		InputStream is = application.getResourceAsStream(webPath);
		
//		FileInputStream fis = new FileInputStream(source);
			
		OutputStream os = resp.getOutputStream();
		byte[] buffer = new byte[1024];
		
		int length =-1;
		while((length =is.read(buffer))!=-1) {
			os.write(buffer , 0 , length);
		}
		is.close();
		os.close();
		
	}
}
