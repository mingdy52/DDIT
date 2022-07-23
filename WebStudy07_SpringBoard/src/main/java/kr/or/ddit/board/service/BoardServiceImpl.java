package kr.or.ddit.board.service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.board.dao.AttatchDAO;
import kr.or.ddit.board.dao.BoardDAO;
import kr.or.ddit.board.vo.AttatchVO;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.common.exception.InvalidPasswordException;
import kr.or.ddit.common.vo.PagingVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService {
	
	private final BoardDAO boardDAO;
	private final AttatchDAO attatchDAO;
	
	@Resource(name="passwordEncryptor")
	private PasswordEncryptor passwordEncryptor;
	
	@Value("#{appInfo['attatchPath']}")
	private File saveFolder;
	
	@PostConstruct
	public void init() throws IOException {
		log.info("주입된 저장 경로 : {}", saveFolder.getCanonicalPath());
	}
	
	
	
	private int processAttatches(BoardVO board) throws IOException {
		List<AttatchVO> attatchList = board.getAttatchList();
		if(attatchList == null || attatchList.isEmpty()) {
			return 0;
		}
		
		int attatchCnt = attatchDAO.insertAttatches(board);
		// 2진 데이터(파일 자체) 저장 -> d:/saveFiles
		
		for (AttatchVO attatch : attatchList) {
			MultipartFile file = attatch.getAtchFile();
			attatch.saveTo(saveFolder);
			
		}
		
		return attatchCnt;
	}
		
	/**
	 * 비밀번호 암호화
	 * @param board
	 */
	private void encryptPassword(BoardVO board) {
		String plain = board.getBoPass();
		String encoded = passwordEncryptor.encryptPassword(plain);
		board.setBoPass(encoded);
	}
	
	/**
	 * 비밀번호 확인
	 * @param input
	 * @param saved
	 * @return
	 */
	private boolean boardAuthenticate(BoardVO input, BoardVO saved) {
		String inputPassword = input.getBoPass();
		String savedPassword = saved.getBoPass();
		if(passwordEncryptor.checkPassword(inputPassword, savedPassword)) {
			return true;
		}else {
			throw new InvalidPasswordException("비밀번호 오류");
		}
	}
	

	
	@Transactional
	@Override
	public void createBoard(BoardVO board) {
		encryptPassword(board); // 비밀번호 암호화
		
		boardDAO.insertBoard(board); // FREEBOARD
		try {
			processAttatches(board); // 첨부파일 처리.
		}catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<BoardVO> retrieveBoardList(PagingVO<BoardVO> pagingVO) {
		pagingVO.setTotalRecord(boardDAO.selectTotalRecord(pagingVO));
		List<BoardVO> boardList = boardDAO.selectBoardList(pagingVO);
		pagingVO.setDataList(boardList);
		return boardList;
	}

	@Override
	public BoardVO retrieveBoard(Integer boNo) {
		BoardVO boardVO = boardDAO.selectBoard(boNo);
		if(boardVO == null) {
			throw new RuntimeException(String.format("%d 게시글 없음.", boNo));
		}
		boardDAO.incrementHit(boNo);
		
		return boardVO;
	}
	
	private void checkPass(BoardVO save, BoardVO input) {
		String savePass = save.getBoPass();
		String inputPass = input.getBoPass();
		
		if(!savePass.equals(inputPass)) {
			throw new InvalidPasswordException(String.format("비밀번호 오류")); 
		} 
	}
	
	
	@Transactional
	@Override
	public void modifyBoard(BoardVO board) {
		BoardVO saved = boardDAO.selectBoard(board.getBoNo());
		if(saved==null)
			throw new RuntimeException(String.format("%d 번 글이 없음.", board.getBoNo()));
		
		boardAuthenticate(board, saved);
		boardDAO.updateBoard(board);
		
		try {
			processAttatches(board);
			removeAttatches(board);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	@Transactional
	@Override
	public void removeBoard(BoardVO board) {
//		1. 게시글 확인
		BoardVO save = boardDAO.selectBoard(board.getBoNo());
		if(save == null) {
			throw new RuntimeException(String.format("%d 번 글 없음.", board.getBoNo()));
		}
		
//		2. 패스워드 인증구조(단방향으로 암호화시킨 후 암호화 시킨걸로 확인)
		
//		3. 인증 통과 못하면 비밀번호 오류 알려줘. -> 반환타입이 void 니까 custom exception(invalid password exception)
		
		checkPass(save, board);
		
//		5. 인증 완료하면 삭제 처리. 근데 자식 테이블이 있으니까 첨부파일 먼저 지워야함. (메타데이터, 이진데이터, savefiles 삭제)
		int[] delAttNos = save.getAttatchList().stream()
										.mapToInt((attatch)-> attatch.getAttNo()).toArray();
		
		board.setDelAttNos(delAttNos);
		
		removeAttatches(board);
		boardDAO.deleteBoard(board.getBoNo());
	}
	
	/**
	 * 첨부파일 삭제 (meta + binary)
	 * @param board
	 */
	private void removeAttatches(BoardVO board) {
		int[] delAttNos = board.getDelAttNos();
		if(delAttNos==null || delAttNos.length == 0) return;
		
		List<String> saveNames = Arrays.stream(delAttNos).mapToObj((attNo)->{
											AttatchVO attatch = attatchDAO.selectAttatch(attNo);
											return attatch.getAttSavename();
										}).collect(Collectors.toList());
		
		attatchDAO.deleteAttatches(board);
		
		for(String saveName : saveNames) {
			File saveFile = new File(saveFolder, saveName);
			FileUtils.deleteQuietly(saveFile);
		}
	}


	@Override
	public AttatchVO download(int attNo) {
		AttatchVO attatch = attatchDAO.selectAttatch(attNo);
		if(attatch==null)
			throw new RuntimeException(String.format("%d 첨부파일이 없음.", attNo));
		attatchDAO.incrementDowncount(attNo);
		return attatch;
	}

}
