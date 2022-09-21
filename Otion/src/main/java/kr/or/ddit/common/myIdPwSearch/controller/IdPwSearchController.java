package kr.or.ddit.common.myIdPwSearch.controller;

import java.util.Random;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.common.myIdPwSearch.service.IdPwSearchService;
import kr.or.ddit.common.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/myIdPwSearch")
public class IdPwSearchController {

	@Inject
	IdPwSearchService idPwSearchService;
	
	@GetMapping
	public String getSearchForm() {
		
		return "common/IdPwSearch/IdPwSearchForm";
	}
	@PostMapping(value = "idSearch", produces=MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String getIdSearch(
			@RequestParam String memMail,
			@RequestParam String memName
			) {
		MemberVO member = new MemberVO();
		if(StringUtils.isBlank(memName) || StringUtils.isBlank(memMail)) {
			return "이름과 이메일을 둘다 입력해주세요";
		}
		member.setMemName(memName);
		member.setMemMail(memMail);
		member=idPwSearchService.getMemberId(member);
		
		return member.getMemId();
	}
	@PostMapping(value = "passSearch", produces=MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String getPassSearch(
			@RequestParam String memId,
			@RequestParam String memMail,
			@RequestParam String memName
			) {
		MemberVO member = new MemberVO();
		if(StringUtils.isBlank(memName) || StringUtils.isBlank(memMail) || StringUtils.isBlank(memId)) {
			return "이름과 이메일을 둘다 입력해주세요";
		}
		member.setMemId(memId);
		member.setMemName(memName);
		member.setMemMail(memMail);
		
		// 해당 유저에 대한 정보 유무
		if(idPwSearchService.getMemberPass(member) != null) {
			member=idPwSearchService.getMemberPass(member);
			String password = getPassword();
			member.setNewPass(password);
			idPwSearchService.modifyMemberPass(member);
			// 해당 service 로직에서 인코딩하여 넣기 때문에 새로 다시 넣어줘야함
			member.setNewPass(password);
			idPwSearchService.sendMail(member);
			return memMail;
		}else {
			// 없음
			return null;
		}
		
	}
	
	public String getPassword() {
		StringBuffer temp = new StringBuffer();
		Random rnd = new Random();
		for(int i=0; i<8;i++) {
			int idx = rnd.nextInt(2);
			switch(idx) {
			case 0:
				// a-z
				temp.append((char)((int)(rnd.nextInt(26))+97));
				break;
			case 1:
				// A-Z
				temp.append((char)((int)(rnd.nextInt(26))+65));
				break;
			}
		}
		return temp.toString().trim();
	}
}
