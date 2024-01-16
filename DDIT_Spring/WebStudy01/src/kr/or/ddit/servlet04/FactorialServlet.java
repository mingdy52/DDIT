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
	   protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	   	System.out.println("요청메소드 : " + req.getMethod());
	   	
	    String accept = req.getHeader("Accept");
		   
	    //클라이언트 보내는 모든 데이터는 검증이 필요함!!!!
	    String param = req.getParameter("number");
	    if(param!=null && param.matches("\\d+")){
	       String pattern ="%d! = %d";
	       int number =Integer.parseInt(param);
	       //재귀 호출
	       long result= factorial(number);
	       String expression = String.format(pattern, number, result);
	       req.setAttribute("expression", expression);
	       req.setAttribute("result", result);
	       req.setAttribute("test", "한글데이터");
	       String view = null;
	       
	       
	       if(accept.contains("json")) {
	       	 view = "/jsonView.do";
	       	 
	       } else {
	        	 
	//        	 // request(Map존재) scope사용
	//        	 req.setAttribute("expression", expression);
	//        	 req.setAttribute("result", result);
	//        	 req.setAttribute("test", "한글데이터");
	//        	 // 중복 데이터. 없애는게 좋음
	        	 
	        	 //서버사이드 방식임 그래서 /WebStudy01없어야함
	        	 view ="/WEB-INF/views/factorialVeiw.jsp";
	       }
	       req.getRequestDispatcher(view).forward(req, resp);
	             
	    }else if(param!=null && !param.matches("\\d+")){
	        //클라이언트에게 잘못됐다는것을 에러메세지 
	        resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "펙토리얼 연산은 양수와 숫자만으로 처리");
	        return;
	     }
	      
	 }
   
   
   // JSON으로 처리되는 if문은 모델 1방식
   
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