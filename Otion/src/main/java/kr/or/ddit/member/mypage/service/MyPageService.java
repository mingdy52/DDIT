package kr.or.ddit.member.mypage.service;

import java.util.List;

import kr.or.ddit.common.vo.ExpertPaymentVO;
import kr.or.ddit.common.vo.ExpertRefundVO;
import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.common.vo.WorkPaymentVO;
import kr.or.ddit.common.vo.WorkRefundVO;
import kr.or.ddit.community.coop.vo.CooBoardVO;
import kr.or.ddit.community.free.vo.FreeBoardVO;
import kr.or.ddit.expert.vo.ExpertVO;
import kr.or.ddit.notice.vo.BoardReplyVO;
/**
 * 
 * @author 이아인
 * @since 2022. 8. 5.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 5.      이아인       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
import kr.or.ddit.question.vo.QNABoardVO;
public interface MyPageService {
   /**
    * 마이페이지 회원 조회
    * @param memId
    * @return
    */
   public MemberVO retrieveMypage(String memId);
   
   /**
    * 별명 중복 체크
    * @param nick
    * @return
    */
   public int nickCheck(String nick);
   
   /**
    * 별명 수정
    * @param member
    * @return
    */
   public void modifynick(MemberVO memberVO);
   
   /**
    * 비밀번호 수정(비밀번호 인증 필)
    * @param memberVO
    */
   public void modifyPass(MemberVO memberVO);
   
   /**
    * 회원 탈퇴(비밀번호 인증 필)
    * @param memberVO
    * @return
    */
   public int  removeMember(MemberVO memberVO);
   
   /**
    * expert결제내역 조회
    * @param memId
    * @return
    */
   public List<ExpertPaymentVO> retrieveExpertPay(PagingVO<ExpertPaymentVO> expPagingVO);
   
   /**
    * 협업툴 결제내역 조회
    * @param coopPagingVO
    * @return
    */
   public List<WorkPaymentVO> retrieveCoopPay(PagingVO<WorkPaymentVO> coopPagingVO);
   
   /**
    * expert환불내역 조회 
    * @param expRefPagingVO
    * @return
    */
   public List<ExpertRefundVO> retrieveExpRef(PagingVO<ExpertRefundVO> expRefPagingVO);
   
   /**
    * 협업툴 환불내역 조회 
    * @param coopRefPagingVO
    * @return
    */
   public List<WorkRefundVO> retrieveCoopRef(PagingVO<WorkRefundVO> coopRefPagingVO);
   
   /**
    * 자유게시판 작성글 조회
    * @param freePagingVO
    * @return
    */
   public List<FreeBoardVO> retrievefreeBoard(PagingVO<FreeBoardVO> freePagingVO);
   
   /**
    * 협업게시판 작성글 조회
    * @param coopPagingVO
    * @return
    */
   public List<CooBoardVO> retrievecoopBoard(PagingVO<CooBoardVO> coopPagingVO);
   /**
    * 댓글 조회
    * @param commPagingVO
    * @return
    */
   public List<BoardReplyVO> retrieveBoardReply(PagingVO<BoardReplyVO> commPagingVO);
   /**
    * 문의 게시판 조회
    * @param qnaPagingVO
    * @return
    */
   public List<QNABoardVO> retrieveQna(PagingVO<QNABoardVO> qnaPagingVO);
   
   /**
    * 자유게시판 삭제
    * @param freeBoardVO
    * @return
    */
   public int removeFreeDel(FreeBoardVO freeBoardVO);
   /**
    * 협업 게시판 삭제
    * @param coopBoardVO
    * @return
    */
   public int removeCoopDel(CooBoardVO coopBoardVO);
   /**
    * 댓글 삭제
    * @param replyVO
    * @return
    */
   public int removeRepDel(BoardReplyVO replyVO);
   /**
    * 문의게시판 삭제
    * @param qnaVO
    * @return
    */
   public int removeQnaDel(QNABoardVO qnaBoardVO);
}