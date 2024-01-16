package kr.or.ddit.question.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.common.attach.dao.AttatchDAO;
import kr.or.ddit.common.dao.NewsDAO;
import kr.or.ddit.common.vo.AttatchVO;
import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.common.vo.NewsVO;
import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.enumpkg.ComCode;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.InvalidPasswordException;
import kr.or.ddit.exception.NotFoundException;
import kr.or.ddit.question.dao.QNABoardDAO;
import kr.or.ddit.question.vo.QNABoardVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author 서효림
 * @since 2022. 8. 22.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 22.      서효림           최초작성
 * 2022. 8. 24.		  서효림		1차수정
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Slf4j
@Service
public class QNABoardServiceImpl implements QNABoardService {
	@Inject
	QNABoardDAO qnaBoardDAO;
	@Inject
	AttatchDAO attatchDAO;
	@Inject
	PasswordEncoder passEncoder;
	@Inject
	NewsDAO newsDao;
	
	@Value("#{appInfo['attatchPath']}")
	private File saveFolder;
	
	@PostConstruct
	public void init() throws IOException{
		log.info("주입된 저장 경로 : {}",saveFolder.getCanonicalPath());
	}
	
	@Override
	public ServiceResult create(QNABoardVO qnaBoard) {
		String plain=qnaBoard.getQnaPass();
		String encoded = passEncoder.encode(plain);
		qnaBoard.setQnaPass(encoded);
		int insertQNA = qnaBoardDAO.insertQNABoard(qnaBoard);
		
		ServiceResult result = null;
		if(insertQNA > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAIL;
		}
		
		return result;
	}

	@Override
	public void findAllQNA(Map<String, Object> map, PagingVO<QNABoardVO> pagingVO) {
		pagingVO.setTotalRecord(qnaBoardDAO.selectQNABoardCount(map));
		List<QNABoardVO> dataList = qnaBoardDAO.selectQNABoardList(map);
		pagingVO.setDataList(dataList);
	}

	@Override
	public QNABoardVO findDetail(String qnaNum) {
		QNABoardVO qnaBoard = qnaBoardDAO.selectQNABoard(qnaNum);
		if(qnaBoard==null) {
			throw new NotFoundException();
		}
		qnaBoardDAO.incrementHitQNA(qnaNum);
		return qnaBoard;
	}
	
	
	@Transactional
	@Override
	public ServiceResult answerQna(QNABoardVO answer) {
		MemberVO checkMember = qnaBoardDAO.checkMember(answer.getWriterId());
		int updateAnswer = qnaBoardDAO.updateAnswer(answer);
		
		ServiceResult result = null;
		
		if(updateAnswer > 0) {
			result = ServiceResult.OK;
			if(checkMember != null) {
				NewsVO news = new NewsVO();
				news.setReceiverId(checkMember.getMemId());
				news.setNewsMsgCode(ComCode.NEW09.toString());
				news.setNewsId(answer.getAnswerId());
				newsDao.insertNews(news);
			}
			
		} else {
			result = ServiceResult.FAIL;
		}
		
		return result;
	}
	
	@Transactional
	@Override
	public ServiceResult modify(QNABoardVO qnaBoard) {
		String plain=qnaBoard.getQnaPass();
		String encoded = passEncoder.encode(plain);
		qnaBoard.setQnaPass(encoded);
		int updateQNA = qnaBoardDAO.updateQNABoard(qnaBoard);
		
		ServiceResult result = null;
		
		if(updateQNA > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAIL;
		}
		
		return result;
		
	}
	
	@Transactional
	@Override
	public ServiceResult remove(QNABoardVO qnaBoard) {
		
		ServiceResult result = null;
		
		// 게시글 존재 확인
		QNABoardVO saved = qnaBoardDAO.selectQNAPass(qnaBoard.getQnaNum());
		if(saved == null) {
			result = ServiceResult.NOTEXIST;
		}
			
		if(passEncoder.matches(qnaBoard.getQnaPass(), saved.getQnaPass())){
			//복호화한값과 입력받은 값이 일치할때
			int deleteQNA = qnaBoardDAO.deleteQNABoard(qnaBoard.getQnaNum());
			
			if(deleteQNA > 0) {
				result = ServiceResult.OK;
			} else {
				result = ServiceResult.FAIL;
			}
			
		}else {
			result = ServiceResult.INVALIDPASSWORD;
		}
			
		return result;
		
	}

}

	
