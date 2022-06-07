package kr.or.ddit.servlet04;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/04/factorial.do")
public class FactorialServlet extends HttpServlet{
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String accept = req.getHeader("Accept");
	   
      //클라이언트 보내는 모든 데이터는 검증이 필요함!!!!
      String param = req.getParameter("number");
      if(param!=null && param.matches("\\d+")){
         String pattern ="%d! = %d";
         int number =Integer.parseInt(param);
         //재귀 호출
         long result= factorial(number);
         String expression = String.format(pattern, number, result);
         Map<String, Object> resultMap = new HashMap<>();
         resultMap.put("expression", expression);
         resultMap.put("result", result);
         
         if(accept.contains("json")) {
        	 // 응답 데이터를 JSON으로 표현 방식 변경. - marshalling(native 언어로 표현된 데이터를 범용 표현 방시의 언어로 바꾸는 과정)
//        	 JSON(JavaScript Object Notation) : 제이슨은 객체를 표현하기 위해 존재
//        	 expression을 JSON 하려면 객체화 해야함.
//        	 {"expression":123123} // 마샬링을 하나 할 경우 이렇게 하드코딩으로 가능
//        	 {"expression":"6! = 720"}
        	 
        	 StringBuffer json = new StringBuffer();
        	 json.append("{"); 	 
        	 for(Entry<String, Object> entry : resultMap.entrySet()/*맵을 셋으로 바꿈*/) {
        		 String key = entry.getKey();
        		 Object value = entry.getValue();
        		 
        		 if("expression".equals(key)) {
        			 json.append("\"" + key.toString() + "\" : \"" + value.toString() + "\"");
        		 }
        	 }
        	 json.append("}");
        	 PrintWriter out = resp.getWriter();
        	 out.println(json);
        	 
         } else {
        	 
        	 // request(Map존재) scope사용
        	 req.setAttribute("expression", expression);
        	 //서버사이드 방식임 그래서 /WebStudy01없어야함
        	 String view ="/WEB-INF/views/FactorialView.jsp";
        	 req.getRequestDispatcher(view).forward(req, resp);
         }
               
      }else if(param!=null && !param.matches("\\d+")){
          //클라이언트에게 잘못됐다는것을 에러메세지 
          resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "펙토리얼 연산은 양수와 숫자만으로 처리");
          return;
       }
      
   }
   
   public long factorial(int number) {
      if(number<0)
         throw new IllegalArgumentException("음수에 대해서는 연산 불가");
      if(number ==0){
         return 1;
      }else{
               return number*factorial(number-1);
      }
      
   }
   
}