package kr.or.ddit.servlet02;

import java.io.File;
import java.io.FilenameFilter;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/02/imageForm.tmpl")
public class imageFormServlet extends AbstractUsingTmplServlet {
    //concreate class 의역할을 하고 있음 -hook method
	@Override
	protected String getContentType() {
		return "text/html;charset=UTF-8";
	}

	@Override
	protected Map<String, Object> getDataMap(HttpServletRequest req, HttpServletResponse resp) {
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
		Map<String, Object>dataMap =new LinkedHashMap<>();
		dataMap.put("options",options);
		return dataMap;
	}  

}
