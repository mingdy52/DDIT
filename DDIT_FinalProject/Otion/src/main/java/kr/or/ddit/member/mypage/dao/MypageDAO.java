package kr.or.ddit.member.mypage.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.common.vo.ExpertPaymentVO;
import kr.or.ddit.common.vo.ExpertRefundVO;
import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.common.vo.WorkPaymentVO;
import kr.or.ddit.common.vo.WorkRefundVO;
import kr.or.ddit.community.coop.vo.CooBoardVO;
import kr.or.ddit.community.free.vo.FreeBoardVO;
import kr.or.ddit.notice.vo.BoardReplyVO;
import kr.or.ddit.question.vo.QNABoardVO;

/**
 * 
 * @author 이아인
 * @since 2022. 8. 10.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 10.      이아인       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */

@Mapper
public interface MypageDAO {
   /**
    * 마이페이지 회원정보 조회
    * @param memId
    * @return
    */
   public MemberVO selectMypage(String memId);
   
   /**
    * 별명 중복 체크
    * @param nick
    * @return
    */
   public int findNick(String nick);
   
   /**
    * 별명 수정
    * @param member
    * @return
    */
   public void updateNick(MemberVO memberVO);
   
   /**
    * 비밀번호 수정
    * @param memberVO
    */
   public int updatePass(MemberVO memberVO);
   
   /**
    * 비밀번호 조회
    * @param memId
    * @return
    */
   public MemberVO selectPass(String memId);
   
   /**
    * 회원탈퇴
    * @param memberVO
    * @return
    */
   public int deleteMember(MemberVO memberVO);
   
   /**
    * expert결제내역 페이징 처리를 위한 레코드 수 조회
    * @param pagingVO
    * @return
    */
   public int expertTotalRecord(PagingVO<ExpertPaymentVO> expPagingVO);
   
   /**
    * expert 결제내역 상세조회
    * @param pagingVO
    * @return
    */
   public List<ExpertPaymentVO> selectExpertPay(PagingVO<ExpertPaymentVO> expPagingVO);
   
   /**
  * 협업툴 결제내역 페이징 처리를 위한 레코드 수 조회 
  * @param coopPagingVO
  * @return
  */
   public int coopTotalRecord(PagingVO<WorkPaymentVO> coopPagingVO);
   
   /**
    * 협업툴 결제내역 상세조회
    * @param coopPagigVO
    * @return
    */
   public List<WorkPaymentVO> selectCoopPay(PagingVO<WorkPaymentVO> coopPagigVO);
   
   /**
    * expert 환불내역 페이징 처리를 위한 레코드 수 조회 
    * @param expRefPagingVO
    * @return
    */
   public int expRefTotalRecord(PagingVO<ExpertRefundVO> expRefPagingVO);
   
   /**
    * expert 환불내역 상세조회
    * @param expRefPagingVO
    * @return
    */
   public List<ExpertRefundVO> selectExpRef(PagingVO<ExpertRefundVO> expRefPagingVO);
   
   /**
    * 협업툴 환불 내역 페이징 처리를 위한 레코드 수 조회
    * @param expRefPagingVO
    * @return
    */
   public int coopRefTotalRecord(PagingVO<WorkRefundVO> expRefPagingVO);
   
   /**
    *  협업툴 환불내역 상세조회
    * @param expRefPagingVO
    * @return
    */
   public List<WorkRefundVO> selectCoopRef(PagingVO<WorkRefundVO> expRefPagingVO);
   /**
    * 자유게시판 조회 페이징 처리를 위한 레코드 수 조회
    * @param freePahingVO
    * @return
    */
   public int freeBoardTotalRecord(PagingVO<FreeBoardVO> freePagingVO);
   /**
    * 자유게시판 글 상세조회
    * @param freePagingVO
    * @return
    */
   public List<FreeBoardVO> selectFreeBoard(PagingVO<FreeBoardVO> freePagingVO);
   /**
    * 협업게시판 조회 페이징 처리를 위한 레코드 수 조회
    * @param coopPagingVO
    * @return
    */
   public int coopBoardTotalRecord(PagingVO<CooBoardVO> coopPagingVO);
   /**
    * 협업게시판 글 상세조회
    * @param coopPagingVO
    * @return
    */
   public List<CooBoardVO> selectCoopBoard(PagingVO<CooBoardVO> coopPagingVO);
   /**
    * 댓글 조회 페이징 처리를 위한 레코드 수 조회
    * @param commPaingVO
    * @return
    */
   public int replyTotalRecord(PagingVO<BoardReplyVO> commPaingVO);
   /**
    * 댓글 상세 조회
    * @param commPaingVO
    * @return
    */
   public List<BoardReplyVO> selectBoardReply(PagingVO<BoardReplyVO> commPaingVO);
   /**
    * 문의게시판 조회 페이징 처리를 위한 레코드 수 조회
    * @param qnaPaingVO
    * @return
    */
   public int qnaTotalRecord(PagingVO<QNABoardVO> qnaPaingVO);
   /**
    * 문의게시판 조회
    * @param qnaPaingVO
    * @return
    */
   public List<QNABoardVO> selectQnaBoard(PagingVO<QNABoardVO> qnaPaingVO);
   /**
    * 자유게시판 삭제
    * @param freeBoardVO
    * @return
    */
   public int freeDelete(FreeBoardVO freeBoardVO);
   /**
    * 협업게시판 삭제
    * @param coopBoardVO
    * @return
    */
   public int coopDelete(CooBoardVO coopBoardVO);
   /**
    * 댓글 삭제
    * @param replyVO
    * @return
    */
   public int repDelete(BoardReplyVO replyVO);
   /**
    * 문의게시판 삭제
    * @param qnaBoardVO
    * @return
    */
   public int qnaDelete(QNABoardVO qnaBoardVO);
   
}