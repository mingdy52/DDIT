package kr.or.ddit.commons;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/jsonView.do")
public class JsonMarshallingServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// marshalling
	   	 resp.setContentType("application/json;charset=UTF-8");
	   	 
	   	 // 응답 데이터를 JSON으로 표현 방식 변경. - marshalling(native 언어로 표현된 데이터를 범용 표현 방시의 언어로 바꾸는 과정)
	//   	 JSON(JavaScript Object Notation) : 제이슨은 객체를 표현하기 위해 존재
	//   	 expression을 JSON 하려면 객체화 해야함.
	//   	 {"expression":123123} // 마샬링을 하나 할 경우 이렇게 하드코딩으로 가능
	//   	 {"expression":"6! = 720"}
	   	 
	   	 StringBuffer json = new StringBuffer();
	   	 String jsonPtrn = "\"%s\":\"%s\" , ";
	   	 json.append("{"); 	 
	   	 
	   	 Enumeration<String> attrNames =  req.getAttributeNames();
	   	 //Enumeration: iterator와 사용방법 같음
	   	 // getAttributeNames: Attribute 내의 보든 이름을 가져옴
	   	 
	   	 while (attrNames.hasMoreElements()) {
			String attrName = (String) attrNames.nextElement();
			Object attrValue = req.getAttribute(attrName);
			json.append(String.format(jsonPtrn, attrName, attrValue));
		}
	   	 
//	   	 for(Entry<String, Object> entry : resultMap.entrySet()/*맵을 셋으로 바꿈*/) {
//	   		 String key = entry.getKey();
//	   		 Object value = entry.getValue();
//	//   		 value.getClass(); // 모든 밸류의 타입을 체크하기엔 시간이 너무 많이 걸림
//	   		 json.append(String.format(jsonPtrn, key, value));
//	   		 
//	   	 }
	   	 // while문과 다를게 없음.
	   	 
	   	 int lastIndex = json.lastIndexOf(","); // 마지막 ,의 위치. 없으면 -1
	   	 if(lastIndex >= 0) {
	   		 json.deleteCharAt(lastIndex);
	   	 }
	   	 json.append("}");
	   	 PrintWriter out = resp.getWriter();
	   	 out.println(json);
	}
	
}
