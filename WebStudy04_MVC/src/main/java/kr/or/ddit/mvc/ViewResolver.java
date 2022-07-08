package kr.or.ddit.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ViewResolver {
	public boolean supported(String viewName);
	public void viewResolve(String viewName, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
