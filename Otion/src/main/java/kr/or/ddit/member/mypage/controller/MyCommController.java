package kr.or.ddit.member.mypage.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.or.ddit.common.login.vo.MemberPrincipal;
import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.community.coop.vo.CooBoardVO;
import kr.or.ddit.community.free.vo.FreeBoardVO;
import kr.or.ddit.member.mypage.service.MyPageService;
import kr.or.ddit.notice.vo.BoardReplyVO;
import kr.or.ddit.question.vo.QNABoardVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member")
public class MyCommController {
	
	@Inject
	private MyPageService myService;
	
	@RequestMapping("memComm")
	public String processHTML() {
		return "member/memComm";
	}
	
	//자유게시판
	@RequestMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE,value="freeBo",method=RequestMethod.GET)
	public String freeBoard(
			 @RequestParam(name ="freePage", required = false, defaultValue = "1") int freePage
			,@RequestHeader(value = "accept", required = false, defaultValue = "text/html") String accept
			,Model model
			) {
		String viewName=null;
		String free="free";
		MemberVO mv=((MemberPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRealMember();
		String memId=mv.getMemId();
		if(StringUtils.containsIgnoreCase(accept, "json")) {
			viewName=jsonData(free,freePage,memId,model);
		}else {
			viewName="member/memComm";
		}
		return viewName;
	}
	//협업게시판
	@RequestMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE,value="coopBo",method=RequestMethod.GET)
	public String coopBoard(
			 @RequestParam(name ="coopPage", required = false, defaultValue = "1") int coopPage
			,@RequestHeader(value = "accept", required = false, defaultValue = "text/html") String accept
			,Model model
			) {
		String viewName=null;
		String coop="coop";
		MemberVO mv=((MemberPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRealMember();
		String memId=mv.getMemId();
		if(StringUtils.containsIgnoreCase(accept, "json")) {
			viewName=jsonData(coop,coopPage,memId,model);
		}else {
			viewName="member/memComm";
		}
		return viewName;
	}
	//댓글
	@RequestMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE,value="reply",method=RequestMethod.GET)
	public String comment(
			 @RequestParam(name ="commPage", required = false, defaultValue = "1") int commPage
			,@RequestHeader(value = "accept", required = false, defaultValue = "text/html") String accept
			,Model model
			) {
		String viewName=null;
		String comm="comm";
		MemberVO mv=((MemberPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRealMember();
		String memId=mv.getMemId();
		if(StringUtils.containsIgnoreCase(accept, "json")) {
			viewName=jsonData(comm,commPage,memId,model);
		}else {
			viewName="member/memComm";
		}
		return viewName;
	}
	//문의게시판
		@RequestMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE,value="qna",method=RequestMethod.GET)
		public String qnaBoard(
				 @RequestParam(name ="qnaPage", required = false, defaultValue = "1") int qnaPage
				,@RequestHeader(value = "accept", required = false, defaultValue = "text/html") String accept
				,Model model
				) {
			String viewName=null;
			String qna="qna";
			MemberVO mv=((MemberPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRealMember();
			String memNick=mv.getMemNick();
			if(StringUtils.containsIgnoreCase(accept, "json")) {
				viewName=jsonData(qna,qnaPage,memNick,model);
			}else {
				viewName="member/memComm";
			}
			return viewName;
		}
	public String jsonData(
			String index
			,int currentPage
			,String memId
			,Model model
			) {
		
		if(index.equals("free")) { //자유게시판
			PagingVO<FreeBoardVO> freePagingVO =new PagingVO<>(8,5);
			freePagingVO.setCurrentPage(currentPage);
			freePagingVO.setDetailCondition(new FreeBoardVO());
			freePagingVO.getDetailCondition().setWriterId(memId);
			
			myService.retrievefreeBoard(freePagingVO);
			model.addAttribute("freePagingVO",freePagingVO);
		}else if(index.equals("coop")) { //협업게시판
			PagingVO<CooBoardVO> coopPagingVO =new PagingVO<>(8,5);
			coopPagingVO.setCurrentPage(currentPage);
			coopPagingVO.setDetailCondition(new CooBoardVO());
			coopPagingVO.getDetailCondition().setWriterId(memId);
			
			myService.retrievecoopBoard(coopPagingVO);
			model.addAttribute("coopPagingVO",coopPagingVO);
		}else if(index.equals("comm")) { //댓글
			PagingVO<BoardReplyVO> commPagingVO =new PagingVO<>(8,5);
			commPagingVO.setCurrentPage(currentPage);
			commPagingVO.setDetailCondition(new BoardReplyVO());
			commPagingVO.getDetailCondition().setWriterId(memId);
			
			myService.retrieveBoardReply(commPagingVO);
			model.addAttribute("commPagingVO",commPagingVO);
		}else { //문의게시판
			PagingVO<QNABoardVO> qnaPagingVO =new PagingVO<>(8,5);
			qnaPagingVO.setCurrentPage(currentPage);
			qnaPagingVO.setDetailCondition(new QNABoardVO());
			qnaPagingVO.getDetailCondition().setWriterId(memId);
			
			myService.retrieveQna(qnaPagingVO);
			model.addAttribute("qnaPagingVO",qnaPagingVO);
		}
		return "jsonView";
	}
	
	//자유게시판 삭제
	@RequestMapping(value="freeDel",method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String freeBoardDel(
			@RequestParam(value="freeBoxArr[]") List<String> freeBoxArr
			) {
			MemberVO mv=((MemberPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRealMember();
			String memId=mv.getMemId();
			//log.info("야야야{}",freeBoxArr);
			FreeBoardVO freeBoardVO=new FreeBoardVO();
			freeBoardVO.setWriterId(memId);
			freeBoardVO.setFreeDelNum(freeBoxArr);
			int cnt=myService.removeFreeDel(freeBoardVO);
			String msg=null;
			log.info("cnt값은:{}",cnt);
			if(cnt>0) {		
				 msg="{\"msg\": \"삭제되었습니다.\"}";
			}else {
				 msg="{\"msg\": \"다시삭제해주세요.\"}";
			}
		return msg;
	}
	//협업게시판 삭제
	@RequestMapping(value="coopDel",method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String coopBoardDel(
			@RequestParam(value="coopBoxArr[]") List<String> coopBoxArr
			) {
			MemberVO mv=((MemberPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRealMember();
			String memId=mv.getMemId();
			//log.info("야야야{}",coopBoxArr);
			CooBoardVO cooBoardVO=new CooBoardVO();
			cooBoardVO.setCooDelNum(coopBoxArr);
			cooBoardVO.setWriterId(memId);
			int cnt=myService.removeCoopDel(cooBoardVO);	
			String msg=null;
			if(cnt>0) {		
				 msg="{\"msg\": \"삭제되었습니다.\"}";
			}else {
				 msg="{\"msg\": \"다시삭제해주세요.\"}";
			}
		return msg;
		
	}	

	//댓글 삭제
		@RequestMapping(value="reDel",method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
		@ResponseBody
		public String replyDel(
				@RequestParam(value="reBoxArr[]") List<String> reBoxArr
				) {
				MemberVO mv=((MemberPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRealMember();
				String memId=mv.getMemId();
				//log.info("야야야{}",reBoxArr);
				BoardReplyVO replyVO=new BoardReplyVO();
				replyVO.setBoReplyDelNum(reBoxArr);
				replyVO.setWriterId(memId);
				int cnt=myService.removeRepDel(replyVO);
				String msg=null;
				if(cnt>0) {		
					 msg="{\"msg\": \"삭제되었습니다.\"}";
				}else {
					 msg="{\"msg\": \"다시삭제해주세요.\"}";
				}
			return msg;
		}
	//문의게시판 삭제
	@RequestMapping(value = "qnaDel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String qnaDel(
			@RequestParam(value = "qnaBoxArr[]") List<String> qnaBoxArr
			) {
		MemberVO mv=((MemberPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRealMember();
		String memId=mv.getMemId();
		//log.info("야야야{}", qnaBoxArr );
		QNABoardVO qnaBoardVO=new QNABoardVO();
		qnaBoardVO.setQnaDelNum(qnaBoxArr);
		qnaBoardVO.setWriterId(memId);
		int cnt = myService.removeQnaDel(qnaBoardVO);
		String msg = null;
		if (cnt > 0) {
			msg = "{\"msg\": \"삭제되었습니다.\"}";
		} else {
			msg = "{\"msg\": \"다시삭제해주세요.\"}";
		}
		return msg;
	}
}
