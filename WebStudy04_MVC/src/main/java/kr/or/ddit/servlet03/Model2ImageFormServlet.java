package kr.or.ddit.servlet03;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Model2 +MVC
 * controller 역할 
 *  1)request 수령. (command 수령 ,즉 명령을 받음)
 *  2)처리 -> 결과 데이터(Model이라고도함)
 *  3)model 을 view로 전달(공유, controller와 view 가 같은 model을 공유함)
 *  4)view 로 이동. (forward방식 사용)
 *
 */

//웹리소스 처럼 사용하기 위해 매핑 작업
@WebServlet("/03/model2ImageForm.do")
public class Model2ImageFormServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext application =  getServletContext();
		String folderPath="D:\\contents";  //파일시스템 리소스 url가지고 있지 않음 그래서 컨트롤러 역할을 하는 서블릿 만들고있음
		File folder = new File(folderPath);
		File[] children = folder.listFiles((dir,name)->{
			
			String mime =application.getMimeType(name);  //톰켓의 mine-mapping 이용
			return mime !=null && mime.startsWith("image/");
		});
		String pattern = "<option>%s</option>";
		StringBuffer options = new StringBuffer();
		for(File tmp:children) {
			options.append(String.format(pattern,tmp.getName()));
		}
		
		req.setAttribute("options",options);
		//String view ="/WebStudy01/webapp/WEB-INF/views/ImageStreamingView.jsp"; 원래는 이것인데 필요없는것 지우기
		String view ="/WEB-INF/views/ImageStreamingView.jsp";
		req.getRequestDispatcher(view).forward(req, resp);
		
	}
}
