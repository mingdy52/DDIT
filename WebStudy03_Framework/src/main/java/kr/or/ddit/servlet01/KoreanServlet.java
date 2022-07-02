package kr.or.ddit.servlet01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * MIME (Multi-purpose Internet Mail Extension)
 * : P2P 사이에서 전송되는 컨텐츠의 타입(형식)
 *  문법- mainType/subType[;charset=encoding]
 *  ex) text/javascript, text/css, text/html(문자열은 문자열인데 html소스를 구성한다.) text/plain;charset=utf-8(문자열외에 다른의미로 해석x 그냥 문자열이다.), 
 *  image/jpeg , video/mpeg
 */
@WebServlet("/Korean.do")
public class KoreanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mine ="text/html;charset=utf-8";
		response.setContentType(mine);
		
		String data ="한글 로우 데이터";
		StringBuffer html = new StringBuffer();
		html.append("<html>"); 
		html.append("<body>");
		html.append(String.format("<h4>%s</h4>",data));
		
		html.append("</body>");
		html.append("</html>");
		response.getWriter();
		PrintWriter out = response.getWriter();
		out.println(html);
		out.close();
	}

	

}
