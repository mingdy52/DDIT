package kr.or.ddit.expert.controller;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.common.login.vo.MemberPrincipal;
import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.expert.service.ExpertService;
import kr.or.ddit.expert.vo.ExpWishVO;

@Controller
public class WishController {

	@Inject
	ExpertService expertservice;
	
	@RequestMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE, value="saveWish",method = RequestMethod.POST)
	@ResponseBody
	public String saveHeart(@RequestParam String eprodNum) {
		MemberVO member = ((MemberPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getRealMember();
		ExpWishVO wish = new ExpWishVO();
		wish.setEprodNum(eprodNum);
		wish.setMemId(member.getMemId());
		expertservice.createWish(wish);
		
		return "{\"msg\": \"추가.\"}";
	}
	
	@RequestMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE, value="deleteWish",method = RequestMethod.POST)
	@ResponseBody
	public String deleteHeart(@RequestParam String eprodNum) {
		MemberVO member = ((MemberPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getRealMember();
		ExpWishVO wish = new ExpWishVO();
		wish.setEprodNum(eprodNum);
		wish.setMemId(member.getMemId());
		expertservice.deleteWish(wish);
		
		return "{\"msg\": \"삭제.\"}";
	}
	
}
