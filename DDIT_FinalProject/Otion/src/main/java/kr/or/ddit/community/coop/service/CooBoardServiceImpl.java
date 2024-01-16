package kr.or.ddit.community.coop.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.common.attach.dao.AttatchDAO;
import kr.or.ddit.common.vo.AttatchVO;
import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.community.coop.dao.CooBoardDAO;
import kr.or.ddit.community.coop.dao.CooFormDAO;
import kr.or.ddit.community.coop.vo.CooBoardVO;
import kr.or.ddit.enumpkg.ServiceResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class CooBoardServiceImpl implements CooBoardService{
	
	@Inject
	CooBoardDAO cooBoardDAO;
	
	@Inject
	CooFormDAO cooFormDAO;
	
	@Inject
	AttatchDAO attatchDAO;
	
	@Value("#{appInfo['attatchPath']}")
	private File saveFolder;
	
	@Override
	public List<CooBoardVO> retrieveCooBoardList(PagingVO<CooBoardVO> pagingVO) {
		int totalRecord = cooBoardDAO.selectCoo(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<CooBoardVO> dataList = cooBoardDAO.selectCooBoardList(pagingVO);
		pagingVO.setDataList(dataList);	
		return dataList;
	}	
	
	@Override
	public CooBoardVO retrieveCooBoardList(String cooNum) {
		CooBoardVO cooBoard = cooBoardDAO.selectCooBoard(cooNum);
		if(cooBoard == null)
			throw new RuntimeException(String.format("%s 의 글이 없습니다.",cooNum));
		cooBoardDAO.incrementHitCoo(cooNum);
		return cooBoard;
	}

	@Transactional
	@Override
	public ServiceResult createCooBoard(CooBoardVO cooBoard) {
		try {
			processAttatches(cooBoard);
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		int insertCooBoard = cooBoardDAO.insertCooBoard(cooBoard);
		ServiceResult result = null;
		if(insertCooBoard > 0) {
			result = ServiceResult.OK;
			
		}else {
			result = ServiceResult.FAIL;
		}
		
		return result;
		
	}
	
	@Transactional
	@Override
	public ServiceResult modifyCooBoard(Map<String, Object> map) {
		CooBoardVO cooBoard = (CooBoardVO) map.get("cooBoard");
		try {
			processAttatches(cooBoard);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		int updateCooBoard = cooBoardDAO.updateCooBoard(cooBoard);
		
		ServiceResult result = null;
		if(updateCooBoard > 0) {
			String[] attatchNum = (String[])map.get("attatchNum");
			int[] attatchOrder = (int[])map.get("attatchOrder");
			if(attatchNum != null) {
				AttatchVO attatchVO = new AttatchVO();
				attatchVO.setAttatchPlace(cooBoard.getCooNum());
				for(String num : attatchNum) {
					attatchVO.setAttatchNum(num);
					for(int order : attatchOrder) {
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
	private void processAttatches(CooBoardVO cooBoard) throws IOException {
		List<AttatchVO> attatchList = cooBoard.getAttatchList();
		if(attatchList == null || attatchList.isEmpty()) return;
		
		attatchDAO.insertCooBoardAttatches(cooBoard);
		for(AttatchVO attatch : attatchList) {
			MultipartFile file = attatch.getAttatchFile();
			attatch.saveTo(saveFolder);
		}
	}

	@Transactional
	@Override
	public ServiceResult removeCooBoard(String cooNum) {
		int deleteCoo = cooBoardDAO.deleteCooBoard(cooNum);
		ServiceResult result = null;
		if(deleteCoo > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAIL;
		}
		return result;
	}

	private void removeAttatches(CooBoardVO cooBoard) {
		String[] delAttNos = cooBoard.getDelAttNos();
		if(delAttNos == null || delAttNos.length == 0) return;
		
	}

	@Override
	public AttatchVO download(String attatchNum) {
		AttatchVO attatch = attatchDAO.selectDownload(attatchNum);
		if(attatch == null)
			throw new RuntimeException(String.format("%d의 첨부파일이 없습니다.",attatchNum));
		return attatch;
	}

	@Override
	public ServiceResult modifyCooState(CooBoardVO coo) {
		int cooState = cooBoardDAO.updateCoostate(coo);
		ServiceResult result = null;
		if(cooState > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAIL;
		}
		return result;
	}
	
}
