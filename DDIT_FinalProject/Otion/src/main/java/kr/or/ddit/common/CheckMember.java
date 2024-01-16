package kr.or.ddit.common;

import javax.inject.Inject;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import kr.or.ddit.admin.dao.MemberManagementDAO;
import kr.or.ddit.common.login.vo.MemberPrincipal;
import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.enumpkg.ComCode;
import kr.or.ddit.enumpkg.RoleGroup;
import kr.or.ddit.exception.NotAuthorityException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CheckMember {
	
	@Inject
	private MemberManagementDAO memberDAO;
	
	public MemberVO checkAdmin() {
		if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated() && !SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            MemberVO mv=((MemberPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRealMember();
            if(memberDAO.checkAdmin(mv.getMemId()) == 1) {
            	return mv;
            	
            } else {
            	throw new NotAuthorityException();
            }
            
         }else{
        	 return null;
        }
	}
	
	public RoleGroup checkBlog(String memId) {
		RoleGroup owner = null;
		
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated() && !SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            MemberVO mv=((MemberPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRealMember();
            
            if(mv.getMemId().equals(memId)) {
            	return owner = RoleGroup.OWNER;
            } 
            
            if(!mv.getMemId().equals(memId)) {
            	return owner = RoleGroup.NOTOWNER;
            }
            
         }else{
            //일반 사용자들도 볼 수 있는곳!!
            //주인블로그가 쓴 글 가져가기 (페에징처리필,검색필,공개여부로되어있는것만 가져가야함)
        	 return owner = RoleGroup.GUEST;
         }
        
		return owner;
	}
}
