package kr.or.ddit.member.join.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.member.join.service.MemberJoinService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member")
@Slf4j
public class MemberIdNicknameCheckController {
	 @Autowired
	    private MemberJoinService userService;

	    // 아이디 체크
	    @PostMapping("/idCheck")
	    @ResponseBody
	    public int idCheck(@RequestParam("id") String id){
	        log.info("userIdCheck 진입");
	        log.info("전달받은 id:"+id);
	        int cnt = userService.idCheck(id);
	        log.info("확인 결과:"+cnt);
	        return cnt;
	    } public int nicknameCheck(@RequestParam("nickname") String id){
	        log.info("userIdCheck 진입");
	        log.info("전달받은 id:"+id);
	        int cnt = userService.idCheck(id);
	        log.info("확인 결과:"+cnt);
	        return cnt;
	    }
	}
