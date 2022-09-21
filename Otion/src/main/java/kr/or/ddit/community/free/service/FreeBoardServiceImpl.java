package kr.or.ddit.community.free.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.common.attach.dao.AttatchDAO;
import kr.or.ddit.common.vo.AttatchVO;
import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.community.free.dao.FreeBoardDAO;
import kr.or.ddit.community.free.vo.FreeBoardVO;
import kr.or.ddit.enumpkg.ComCode;
import kr.or.ddit.enumpkg.ServiceResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author 서효림
 * @since 2022. 8. 16.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 16.       서효림          최초작성
 * 2022. 8. 17.		  서효림		1차 수정
 * 2022. 9. 02. 	 서효림		2차 수정
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FreeBoardServiceImpl implements FreeBoardService{
	@Inject
	FreeBoardDAO freeBoardDAO;
	@Inject
	AttatchDAO attatchDAO;
	
	@Value("#{appInfo['attatchPath']}")
	private File saveFolder;
	
	@Override
	public void findAll(PagingVO<FreeBoardVO> pagingVO) {
		Map<String, Object> map = new HashMap<>();
		map.put("pagingVO", pagingVO);
		map.put("notiClass", ComCode.NC01);
		
		int totalRecord = freeBoardDAO.selectBoardCount(map);
		pagingVO.setTotalRecord(totalRecord);
		List<FreeBoardVO> dataList = freeBoardDAO.selectFreeBoardList(map);
		pagingVO.setDataList(dataList);		
		
	}

	@Override
	public FreeBoardVO find(String freeNum) {
		FreeBoardVO freeBoard = freeBoardDAO.selectFreeBoard(freeNum);
		if(freeBoard==null)
			throw new RuntimeException();
		freeBoardDAO.incrementHit(freeNum);
		return freeBoard;
	}
	
	private void processAttatches(FreeBoardVO freeBoard) throws IOException {
		List<AttatchVO> attatchList = freeBoard.getAttatchList();
		if(attatchList == null || attatchList.isEmpty()) {
			return;
		}
		attatchDAO.insertFreeBoardAttatches(freeBoard);
		
		for(AttatchVO attatch : attatchList) {
			MultipartFile file = attatch.getAttatchFile();
			attatch.saveTo(saveFolder);
		}
	}
	
	
	
	
	@Transactional
	@Override
	public ServiceResult create(FreeBoardVO freeBoard) {
		try {
			processAttatches(freeBoard);
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		int insertFreeBoard = freeBoardDAO.insertFreeBoard(freeBoard);
		ServiceResult result = null;
		if(insertFreeBoard > 0) {
			result = ServiceResult.OK;
			
		}else {
			result = ServiceResult.FAIL;
		}
		
		return result;
	}

	

	@Transactional
	@Override
	public ServiceResult modify(Map<String, Object> map) {
		FreeBoardVO freeBoard = (FreeBoardVO) map.get("freeBoard");
		try {
			processAttatches(freeBoard); // 첨부파일 처리.
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		int updateFreeBoard = freeBoardDAO.updateBoard(freeBoard);
		
		ServiceResult result = null;
		if(updateFreeBoard > 0) {
			
			String[] attatchNum = (String[]) map.get("attatchNum");
			int[] attatchOrder = (int[]) map.get("attatchOrder");
			if(attatchNum != null) {
				AttatchVO attatchVO = new AttatchVO();
				attatchVO.setAttatchPlace(freeBoard.getFreeNum());
				for (String num : attatchNum) {
					attatchVO.setAttatchNum(num);
					for (int order : attatchOrder) {
						attatchVO.setAttatchOrder(order);
						attatchDAO.deleteAttatchFile(attatchVO);
						
					}
					
				}
			}
			
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAIL;
		}
		
		return result;
	}
	
	@Transactional
	@Override
	public ServiceResult remove(String freeNum) {
		int deleteFree = freeBoardDAO.deleteBoard(freeNum);
		ServiceResult result = null;
		if(deleteFree > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAIL;
		}
		
		return result;
	}
	
//	@Override
//	public AttatchVO download(String attatchNum) {
//		AttatchVO attatch = attatchDAO.selectDownload(attatchNum);
//		if(attatch == null)
//			throw new RuntimeException(String.format("첨부파일이 없습니다.", attatchNum));
//		
//		return attatch;
//	}
//	
//	private void removeAttatches(FreeBoardVO freeBoard) {
//		String[] delAttNos = freeBoard.getDelAttNos();
//		if(delAttNos == null || delAttNos.length == 0) return;
//		
//
//	}
//	@PostConstruct
//	public void init() throws IOException{
//		log.info("주입된 저장 경로 : {}", saveFolder.getCanonicalPath());
//	}
	
}





