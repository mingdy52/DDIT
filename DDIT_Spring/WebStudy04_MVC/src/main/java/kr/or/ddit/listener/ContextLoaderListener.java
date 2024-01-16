package kr.or.ddit.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class ContextLoaderListener
 *
 */
@WebListener
public class ContextLoaderListener implements ServletContextListener {
	int visitor;
	
	public void contextInitialized(ServletContextEvent sce)  { 
		ServletContext application = sce.getServletContext();
		application.setAttribute("cPath", application.getContextPath());
		application.setAttribute("userCount", 0);
		System.out.printf("%s 로딩 완료\n", application.getContextPath());
	}
	
    public void contextDestroyed(ServletContextEvent sce)  { 
    	ServletContext application = sce.getServletContext();
    	System.out.printf("%s 언로딩\n", application.getContextPath());
    }

}
