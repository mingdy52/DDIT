package kr.or.ddit.member.mypage.service;



import java.lang.reflect.Member;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;


import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.or.ddit.common.vo.ExpertPaymentVO;
import kr.or.ddit.common.vo.ExpertRefundVO;
import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.common.vo.WorkPaymentVO;
import kr.or.ddit.common.vo.WorkRefundVO;
import kr.or.ddit.community.coop.vo.CooBoardVO;
import kr.or.ddit.community.free.vo.FreeBoardVO;
import kr.or.ddit.exception.InvalidPasswordException;
import kr.or.ddit.exception.PKNotFoundException;
import kr.or.ddit.expert.vo.ExpertVO;
import kr.or.ddit.member.mypage.dao.MypageDAO;
import kr.or.ddit.notice.vo.BoardReplyVO;
import kr.or.ddit.question.vo.QNABoardVO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MyPageServiceImpl implements MyPageService{
   
   @Inject
   private MypageDAO myPageDAO;
   
   /*@Resource(name="passwordEncryptor")
   private PasswordEncryptor passwordEncryptor;*/
   
   @Inject
   PasswordEncoder passEncoder;
   
   /**
    * 마이페이지 회원 조회
    */
   @Override
   	public MemberVO retrieveMypage(String memId) {
      MemberVO member =myPageDAO.selectMypage(memId);
      
      if(member==null) {
         throw new PKNotFoundException(String.format("%s 아이디를 가진 회원이 없음.", memId));
      }
      return member;
   }
   /**
    * 별명 중복 체크
    */
	@Override
	public int nickCheck(String nick) {
		
		int cnt =myPageDAO.findNick(nick);
		return cnt;
	}
	/**
	 * 별명 수정
	 */
	@Override
	public void modifynick(MemberVO memberVO) {
		
		log.info("{}",memberVO);
		
		System.out.println(memberVO);
		myPageDAO.updateNick(memberVO);
		
	}
	
	
	/**
	 * 비밀번호 암호화
	 * @param board
	 */
	/*private void encryptPassword(MemberVO memberVO) {
		String plain=memberVO.getNewPass();
		String encoded = passwordEncryptor.encryptPassword(plain);
		memberVO.setNewPass(encoded);
	}*/
	
	
	
	/**
	 * 비밀번호 확인
	 * @param input
	 * @param saved
	 * @return
	 */
	/*private boolean Authenticate(MemberVO input, MemberVO saved) {
		String inputPassword = passwordEncryptor.encryptPassword(input.getMemPass());
		String savedPassword = saved.getMemPass();
		log.info("inputPassword:{}",inputPassword);
		log.info("savedPassword:{}",savedPassword);
		if(passwordEncryptor.checkPassword(inputPassword, savedPassword)) {
			return true;
		}else {
			throw new InvalidPasswordException("비밀번호 오류");
		}
	}*/
	
	
	/**
	 * 비밀번호 수정
	 */
	@Override
	public void modifyPass(MemberVO memberVO) {
		MemberVO saved =myPageDAO.selectPass(memberVO.getMemId());
		if(saved ==null)
			throw new RuntimeException(String.format("%d회원은 존재하지않음.", memberVO.getMemId()));
		
		//회원으로부터 받은 비밀번호와 db에서 가져온 회원의 비밀번호(saved) 같은지 확인
		//Authenticate(memberVO, saved);
		if(passEncoder.matches(memberVO.getMemPass(), saved.getMemPass())){
				//복호화한값과 입력받은 값이 일치할때
				memberVO.setNewPass(passEncoder.encode(memberVO.getNewPass()));
				myPageDAO.updatePass(memberVO);
			}else {
				throw new InvalidPasswordException("비밀번호 오류");
			}
		
		
		//바꿀 비밀번호 암호화
		//encryptPassword(memberVO);
		//passEncoder.encode(memberVO.getNewPass());
		//비밀번호 수정
		//myPageDAO.updatePass(memberVO);
		
		
	}
	
	/**
	 * 회원 탈퇴
	 */
	@Override
	public int removeMember(MemberVO memberVO) {
		int cnt=0;
		MemberVO saved =myPageDAO.selectPass(memberVO.getMemId());
		if(saved ==null)
			throw new RuntimeException(String.format("%d회원은 존재하지않음.", memberVO.getMemId()));
		
		//회원으로부터 받은 비밀번호와 db에서 가져온 회원의 비밀번호(saved) 같은지 확인
		//Authenticate(memberVO, saved);
		if(passEncoder.matches(memberVO.getMemPass(), saved.getMemPass())){
			// 비밀번호 맞으면 회원탈퇴진행
			cnt=myPageDAO.deleteMember(memberVO);
		}else {
			throw new InvalidPasswordException("비밀번호 오류");
		}
		
		return cnt;
	}
	/**
	 * expert결제내역조회
	 */
	@Override
	public List<ExpertPaymentVO> retrieveExpertPay(PagingVO<ExpertPaymentVO> expPagingVO) {
		
		expPagingVO.setTotalRecord(myPageDAO.expertTotalRecord((expPagingVO)));
		List<ExpertPaymentVO> expertPayList =myPageDAO.selectExpertPay(expPagingVO);
		expPagingVO.setDataList(expertPayList);
		return expertPayList;
	}
	
	/**
	 * 협업툴 결제내역 조회
	 */
	@Override
	public List<WorkPaymentVO> retrieveCoopPay(PagingVO<WorkPaymentVO> coopPagingVO) {
		
		coopPagingVO.setTotalRecord(myPageDAO.coopTotalRecord(coopPagingVO));
		List<WorkPaymentVO> coopPayList =myPageDAO.selectCoopPay(coopPagingVO);
		coopPagingVO.setDataList(coopPayList);
		return coopPayList;
		
	}
	/**
	 * expert 환불내역 조회
	 */
	@Override
	public List<ExpertRefundVO> retrieveExpRef(PagingVO<ExpertRefundVO> expRefPagingVO) {
		
		expRefPagingVO.setTotalRecord(myPageDAO.expRefTotalRecord(expRefPagingVO));
		List<ExpertRefundVO> expRefList =myPageDAO.selectExpRef(expRefPagingVO);
		expRefPagingVO.setDataList(expRefList);
		return expRefList;
	}
	/**
	 * 협업툴 환불내역 조회
	 */
	@Override
	public List<WorkRefundVO> retrieveCoopRef(PagingVO<WorkRefundVO> coopRefPagingVO) {
		coopRefPagingVO.setTotalRecord(myPageDAO.coopRefTotalRecord(coopRefPagingVO));
		List<WorkRefundVO> coopRefList =myPageDAO.selectCoopRef(coopRefPagingVO);
		coopRefPagingVO.setDataList(coopRefList);
		return coopRefList;
	}
	/**
	 * 자유게시판 글 조회
	 */
	@Override
	public List<FreeBoardVO> retrievefreeBoard(PagingVO<FreeBoardVO> freePagingVO) {
		freePagingVO.setTotalRecord(myPageDAO.freeBoardTotalRecord(freePagingVO));
		List<FreeBoardVO> freeBoardList =myPageDAO.selectFreeBoard(freePagingVO);
		freePagingVO.setDataList(freeBoardList);
		return freeBoardList;
	}
	/**
	 * 협업게시판 글 조회
	 */
	@Override
	public List<CooBoardVO> retrievecoopBoard(PagingVO<CooBoardVO> coopPagingVO) {
		coopPagingVO.setTotalRecord(myPageDAO.coopBoardTotalRecord(coopPagingVO));
		List<CooBoardVO> coopBoardList =myPageDAO.selectCoopBoard(coopPagingVO);
		coopPagingVO.setDataList(coopBoardList);
		return coopBoardList;
	}
	/**
	 * 댓글 조회
	 */
	@Override
	public List<BoardReplyVO> retrieveBoardReply(PagingVO<BoardReplyVO> commPagingVO) {
		commPagingVO.setTotalRecord(myPageDAO.replyTotalRecord(commPagingVO));
		List<BoardReplyVO> BoardReplyList =myPageDAO.selectBoardReply(commPagingVO);
		commPagingVO.setDataList(BoardReplyList);
		return BoardReplyList;
	}
	/**
	 * 문의 게시판 조회
	 */
	@Override
	public List<QNABoardVO> retrieveQna(PagingVO<QNABoardVO> qnaPagingVO) {
		qnaPagingVO.setTotalRecord(myPageDAO.qnaTotalRecord(qnaPagingVO));
		List<QNABoardVO> qnaBoardList =myPageDAO.selectQnaBoard(qnaPagingVO);
		qnaPagingVO.setDataList(qnaBoardList);
		return qnaBoardList;
	}
	/**
	 * 자유 게시판 삭제
	 */
	@Override
	public int removeFreeDel(FreeBoardVO freeBoardVO) {
		int cnt;
		cnt=myPageDAO.freeDelete(freeBoardVO);
		return cnt;
	}
	/**
	 * 협업 게시판 삭제
	 */
	@Override
	public int removeCoopDel(CooBoardVO coopBoardVO) {
		int cnt;
		cnt=myPageDAO.coopDelete(coopBoardVO);
		return cnt;
	}
	/**
	 * 댓글 석제
	 */
	@Override
	public int removeRepDel(BoardReplyVO replyVO) {
		int cnt;
		cnt=myPageDAO.repDelete(replyVO);
		return cnt;
	}
	/**
	 * 문의게시판 삭제
	 */
	@Override
	public int removeQnaDel(QNABoardVO qnaBoardVO) {
		int cnt;
		cnt=myPageDAO.qnaDelete(qnaBoardVO);
		return cnt;
	}
	
	
   
   
}

