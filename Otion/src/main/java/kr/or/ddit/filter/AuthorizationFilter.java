package kr.or.ddit.filter;

import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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

import org.hibernate.validator.spi.properties.GetterPropertySelectionStrategy;
import org.springframework.security.core.context.SecurityContextHolder;

import kr.or.ddit.common.vo.RoleVO;
import kr.or.ddit.common.login.vo.MemberPrincipal;
import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.member.vo.MemberVOWrapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 보호자원을 요청하고 있는 인증된 사용자의 인가 여부 확인.
 *
 */
@Slf4j
public class AuthorizationFilter implements Filter {
	private ServletContext application;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		application=filterConfig.getServletContext();
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		Map<String, String[]> securedResources =(Map<String, String[]>)
														application.getAttribute(AuthenticateFilter.SECUREDRESOURCENAME);
		
		
		HttpServletRequest req =(HttpServletRequest)request;
		HttpServletResponse resp =(HttpServletResponse)response;
		
		String uri =req.getRequestURI(); // uri가 key가됨  		
		uri =uri.substring(req.getContextPath().length()); //컨텍스트 패쓰 제거
		uri=uri.split(":")[0];
//      1. 보호 자원인지 확인
		boolean secutrFlag = securedResources.containsKey(uri);
		boolean pass = false;
//      1-1 보호자원이면 검사
        if (secutrFlag) {
//      2. role꺼내서 확인
	        //MemberVOWrapper wrapperPrincipal =(MemberVOWrapper)req.getUserPrincipal();
        	if(((MemberPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRealMember().equals("anonymousUser")) {
        		resp.sendRedirect(req.getContextPath() + "/login");
        	}
        	MemberVO principal =((MemberPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRealMember();
        	log.info("안에있나요2:{}",principal);
        	//MemberVO authMember = wrapperPrincipal.getRealMember();
	        //String role =authMember.getMemRole();
        	String[] getRoles=new String[4];
        	
        	List<RoleVO> roleCodeList =principal.getRoleList();
        	for(int i=0; i<roleCodeList.size(); i++) {
        		String roleCode=roleCodeList.get(i).getRoleCode();
        		getRoles[i]=roleCode;
        	}
        	log.info("배열안에?{}",getRoles);
        	String user="ROLE_PROJECT";  //지금 하드코딩됨
	        String[] roles =securedResources.get(uri);  //자원에 설정되어있는 허가목록
	        
	     
	        int searched =Arrays.binarySearch(roles, user);  //Arrays : 배열 불편한점 해결 ,특정조건맞는거 찾으려고  
	        if(searched<0) {
	        	//인가가 안됐다!!
	        	pass = false;
	        }else {
	        	//인가가 됐다 role이 roles안에 포함되어있다.
	        	pass = true;
	        }         
//         1-1보호자원이 아니면 넘어감
      } else {
         pass = true;
      }
      
      if (pass) {
         chain.doFilter(request, response);
      } else {//SC_UNAUTHORIZED :401 ,또는 SC_FORBIDDEN 403 
         resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "권한이 없는 요청입니다.");
      }
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
}
