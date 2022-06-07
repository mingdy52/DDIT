package kr.or.ddit.servlet02;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/02/clock.tmpl")
public class ClockServlet extends AbstractUsingTmplServlet {

	@Override
	protected String getContentType() {
		// TODO Auto-generated method stub
		return "text/html;charset=UTF-8";
	}

	@Override
	protected Map<String, Object> getDataMap(HttpServletRequest req, HttpServletResponse resp) {
		Map<String, Object> dataMap = new LinkedHashMap<>();
		dataMap.put("time", new Date());
		return dataMap;
	}

}
