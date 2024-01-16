package kr.or.ddit.listener;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionIdListener;
import javax.servlet.http.HttpSessionListener;


public class CustomSessionListener implements HttpSessionListener{

   @Override
   public void sessionCreated(HttpSessionEvent se) {
      ServletContext application =se.getSession().getServletContext();
      int userCount =(int)application.getAttribute("userCount");      
      application.setAttribute("userCount", ++userCount);
      
   }

   @Override
   public void sessionDestroyed(HttpSessionEvent se) {
      
      
   }

   
}