package kr.or.ddit.filter;

import java.io.IOException;
import java.io.InputStream;

import java.security.Principal;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;

import lombok.extern.slf4j.Slf4j;





/**
 * 
 * 보안 : Authentication(인증,신원확인 구조) + Authorization(인가,권한을 확인구조)
 * 
 * 보호자원을 접근하려는 사용자가 인증된 사용자인지 확인.
 * SecuredResources.properties에서 정보 알 수 있잖아!!
 */
@Slf4j
public class AuthenticateFilter implements Filter {  //인증구조를 이필터를 통해서 확인하겠다.
	
	private Map<String, String[]> securedResources; //properties안에 있는 내용읽어서 여기에로딩
	private ServletContext aplication;
	public static final String SECUREDRESOURCENAME ="securedResources";
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//보호자원 정보 로딩.
		String location ="/kr/or/ddit/SecuredResources.properties";
		
		try (
			InputStream is = this.getClass().getResourceAsStream(location);
			
		){
			Properties props =new Properties();
			props.load(is);
			securedResources =new LinkedHashMap<>();
			aplication= filterConfig.getServletContext();
			aplication.setAttribute(SECUREDRESOURCENAME, securedResources);
			for(Entry<Object, Object> entry:props.entrySet()) {
				String uri =entry.getKey().toString();
				String values =entry.getValue().toString();
				String[] roles =values.split(","); //허가목록 
				Arrays.sort(roles);//나중에 arrays쓰기 위해 미리 정렬하자 
				securedResources.put(uri.trim(),roles);
			}
			
		} catch (IOException e) {
			throw new RuntimeException();
		}
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req =(HttpServletRequest)request;
		HttpServletResponse resp =(HttpServletResponse)response;
		
		// 1.사용자의 요청 자원이 보호자원인지 여부 확인 
			String uri =req.getRequestURI(); // uri가 key가됨  
			//uri가공 필요 ex)/member/memberList.do얘처럼
			uri =uri.substring(req.getContextPath().length()); //컨텍스트 패쓰 제거
			//jessionid =asasaf붙을 수도 있으니까 
			uri=uri.split(":")[0];
			boolean secureFlag =securedResources.containsKey(uri);
			boolean pass =false;
			if(secureFlag) {
				//Principal principal =req.getUserPrincipal(); //인터페이스이며 로그인하면 멤버브이오의래퍼가 들어와

				Object principal =SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				log.info("들어있나용:{}",principal);
				//2-1 보호자원 : 이제 통과를 시킬것인지 말것인지 인증된 사용자 여부인지에따라 			
				//	3.사용자 인증 여부 확인 
				if(principal!=null) { //인증이 된 케이스
					//		4-1. 인증 :통과
					pass=true;
				}else {
//		            4-2  미인증:로그인 페이지로 이동
					pass=false;
				}				
			}else {			
				//2-2 비보호자원 :통과 (그다음필터로 넘긴다)
				pass =true;
			}
			
			if(pass) {
				chain.doFilter(request, response);
				
			}else {
				String goPage = "/login";
				resp.sendRedirect(req.getContextPath() + goPage);				
			}
			
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	} 

}
