package kr.or.ddit.commons;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/jsonView.do")
public class JsonMarshallingServlet extends HttpServlet{
   @Override
   protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      resp.setContentType("application/json;charset=UTF-8");
      //1.마셜링의 타켓 : scope
      Enumeration<String> attrNames =req.getAttributeNames();
      Map<String, Object> target = new HashMap<>();
      while(attrNames.hasMoreElements()) {
         String attName = (String)attrNames.nextElement(); //key
         Object value = req.getAttribute(attName); //value
         target.put(attName,value);
      }
      //2.마샬링
      //3.직혈화
      ObjectMapper mapper = new ObjectMapper();
      // ObjectMapper 라이브러리 객체
      // 맵 데이터를 {key : value , key2 : value2 , ...} 형식으로 자동으로 만들어줌
//      try~with~resource 문법 구조(1.7)
      try(
            //closable 객체 생성 코드 -> 자동 close됨.
            PrintWriter out = resp.getWriter();      
      ){   
         mapper.writeValue(out, target);
         //writeValue : 타겟을 제이슨 형태로 출력해라~~~~
      }
       
   }
}