package kr.or.ddit.servlet02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 
 * Design Pattern
 * 1. Template Method Pattern
 *     :작업의 순서가 고정되어있으되, 그 순서내에서 세부 작업이 다양한 형태를 갖는 경우.
 *     1) template class (abstact)
 *      -template method :작업의 순서 정의  ex.doget
 *      -hook method(abstract) : 순서내에서의 세부 작업이 작업.
 *      
 *     2) concreate class (instance 생성 가능)
 *		-hook method 에 대한 구체적인 작업 정의.
 */
 
//@WebServlet("/02/gugudan.tmpl") //servlet path

public abstract class AbstractUsingTmplServlet extends HttpServlet {
   private ServletContext application;
   
   @Override
   public void init(ServletConfig config) throws ServletException {
	 
      super.init(config);
      this.application = getServletContext();
   } // 싱글톤이므로 한번만 실행
   
   protected abstract String getContentType();
   
   protected abstract Map<String,Object> getDataMap(HttpServletRequest req, HttpServletResponse resp);
   
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	  resp.setContentType(getContentType());
      
      Map<String, Object> dataMap = getDataMap(req,resp);
	  
      //String tmplPath = "/02/gugudan.tmpl"; // =URL  webapp: 가상폴더이기에 포함이 안된다. 그래서 바로 WebStudy01에서 바로 찾는다.
      String tmplPath = req.getServletPath();
      //IO ~~Stream : byte stream , ~~Reader/~~Writer : character stream
      InputStream is = application.getResourceAsStream(tmplPath); //1차 stream 바이트
      InputStreamReader isr = new InputStreamReader(is); //연결형 2차 stream
      BufferedReader reader = new BufferedReader(isr); // 2차 stream
      
      StringBuffer template = new StringBuffer();
      String line =null;
      while((line = reader.readLine())!=null) {
    	  template.append(line+"\n");
      }
      Pattern regex =Pattern.compile("<%=(\\w+)\\s*%>");  //정규식 패턴
      Matcher matcher = regex.matcher(template);
      StringBuffer html = new StringBuffer();
      while(matcher.find()) {
    	  String dataName = matcher.group(1);
    	  Object dataValue = dataMap.get(dataName);
    	 matcher.appendReplacement(html,dataValue.toString());
       //matcher.appendReplacement(html, Objects.toString(dataValue, ""));
      }
      matcher.appendTail(html);
  	  
      
      PrintWriter out = null;
      try {
         out = resp.getWriter();
         out.println(html);
      } finally {
         if(out!=null)
            out.close();
      }
   }
   

}