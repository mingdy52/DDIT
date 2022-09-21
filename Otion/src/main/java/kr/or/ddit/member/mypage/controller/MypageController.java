package kr.or.ddit.member.mypage.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import kr.or.ddit.common.login.vo.MemberPrincipal;
import kr.or.ddit.common.validate.UpdateGroup;
import kr.or.ddit.common.vo.ExpertPaymentVO;
import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.common.vo.WorkPaymentVO;
import kr.or.ddit.exception.InvalidPasswordException;
import kr.or.ddit.member.mypage.dao.MypageDAO;
import kr.or.ddit.member.mypage.service.MyPageService;
import lombok.extern.slf4j.Slf4j;
/**
 * 
 * @author 이아인
 * @since 2022. 8. 4.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * 2022.8.5          이아인               내용추가
 * --------     --------    ----------------------
 * 2022. 8. 4.      이아인       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Slf4j
@Controller
@RequestMapping("/member")
public class MypageController {
   
   @Inject
   MyPageService myService;
   //회원조회
   @RequestMapping("mypage")
   public String mypage(
         Model model
         ) {
      //세션에서 회원정보 가져오기
	  MemberVO mv=((MemberPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRealMember();
	  String memId=mv.getMemId();

      MemberVO member =(MemberVO)myService.retrieveMypage(memId);
      model.addAttribute("member", member); 
      return "member/mypage";
   }
   
   //별명 중복 확인 
   @PostMapping("nickCheck")
   @ResponseBody
   public String nickCheck(
         @RequestParam("nick") String nick
         ) {
     int cnt =myService.nickCheck(nick);
     log.info(Integer.toString(cnt));
      return Integer.toString(cnt);
   }
   
    //별명 수정 
   @PostMapping("upNick")
   public String upNick(
		   Model model
		   ,@Validated(UpdateGroup.class)@ModelAttribute("member") MemberVO member
		   ,Errors errors
		   ,RedirectAttributes redirectAttributes
		   ,SessionStatus sessionStatus
		   ) {
	   log.info("nick{}",member.getMemNick());
	   String viewName=null;

	   if(!errors.hasErrors()) {
		   //나중에 세션에서 받아오기
		   MemberVO mv=((MemberPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRealMember();
		   String memId=mv.getMemId();
		   
		   member.setMemId(memId);
		   myService.modifynick(member);
		   redirectAttributes.addFlashAttribute("message", "별명이 수정되었습니다.");
		   viewName="redirect:/member/mypage";
		   
	   }else {
		   redirectAttributes.addFlashAttribute("message", "별명을 다시한번 확인하고 변경해주세요");
			viewName = "redirect:/member/mypage";
	   }
	   return viewName;
   }
  
   //비밀번호 수정
   @PostMapping("upPass")
   public String upPass( 
	   @RequestParam("newChPass") String newChPass
	  ,@RequestParam("checkPass") String checkPass
	  ,RedirectAttributes redirectAttributes
	  ,HttpServletRequest request
   ) {
		log.info("pass:{}",newChPass);
		log.info("확인해요:{}",checkPass);
		String viewName=null;
		//세션에서 정보가져오기
		MemberVO mv=((MemberPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRealMember();
		String memId=mv.getMemId();
		
		MemberVO member =new MemberVO();
		member.setMemId(memId);
		member.setMemPass(checkPass);
		member.setNewPass(newChPass);
		
		if(!StringUtils.isBlank(checkPass)&& !StringUtils.isBlank(newChPass)) {
			try {
				myService.modifyPass(member);
				FlashMap fm = RequestContextUtils.getOutputFlashMap(request);
	            fm.put("message", "비밀번호가 수정되었습니다.");
	            viewName="redirect:/logout";
			}catch (InvalidPasswordException e) {
				redirectAttributes.addFlashAttribute("message", "비밀번호가 일치하지않습니다.");
				viewName = "redirect:/member/mypage";
			}
	}else {
		redirectAttributes.addFlashAttribute("message", "비밀번호가 누락되었습니다.");
		viewName = "redirect:/member/mypage";
	}
	return viewName;
	}

   //탈퇴
   @PostMapping("delMem")
   public String delMem(
		    @RequestParam("delPass") String delPass
		   ,@RequestParam("delReason") String delReason
		   ,RedirectAttributes redirectAttributes
		   ,HttpServletRequest request
		   ) {
	   
	   log.info("delPass:{}",delPass);
	   log.info("delReason:{}",delReason);
	   String viewName=null;
	   //세션에서 정보 가져오기
	   MemberVO mv=((MemberPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRealMember();
	   String memId=mv.getMemId();
	   
	   MemberVO member =new MemberVO();
	   member.setMemId(memId);
	   member.setMemPass(delPass);
	   member.setDelReason(delReason);
	   
	   if(!StringUtils.isBlank(delPass) && !StringUtils.isBlank(delReason)) {
		   try {
				myService.removeMember(member);
				FlashMap fm = RequestContextUtils.getOutputFlashMap(request);
	            fm.put("message", "탈퇴처리가 되었습니다.");
	            viewName="redirect:/logout";
			}catch (InvalidPasswordException e) {
				redirectAttributes.addFlashAttribute("message", "비밀번호가 일치하지않습니다.");
				viewName = "redirect:/member/mypage";
			}
	   }else {
		   redirectAttributes.addFlashAttribute("message", "누락된정보가있습니다.다시 입력해주세요");
		   viewName = "redirect:/member/mypage";
	   }
	   
	   return viewName;
   }
      

    
   
}
