package kr.or.ddit.servlet02;

import java.util.Calendar;
import java.util.LinkedHashMap;

import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/02/gugudan.tmpl")
public class GugudanServlet extends AbstractUsingTmplServlet {

	@Override
	protected String getContentType() {
		
		return "text/html;charset=UTF-8";
	}

	@Override
	protected Map<String, Object> getDataMap(HttpServletRequest req, HttpServletResponse resp) {
		StringBuffer data = new StringBuffer();
	      String pattern = "<td>%d*%d=%d</td>";
	      for(int dan = 2; dan <=9; dan++) {
	         data.append("<tr>");
	         for(int mul=1; mul <= 9; mul++) {
	            data.append(String.format(pattern, dan, mul, (dan*mul)));
	         }
	         data.append("</tr>");
	      }
	    
	      Calendar today = Calendar.getInstance();
	      String todayStr = String.format("%1$ty년 %1$tm월 %1$td일",today);
	      Map<String, Object> dataMap = new LinkedHashMap<>();
	      dataMap.put("gugudan",data);
	      dataMap.put("today",todayStr);
	      return dataMap;
	}

}
