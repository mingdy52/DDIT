package kr.or.ddit.notice.service;

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
import kr.or.ddit.enumpkg.ComCode;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.NotFoundException;
import kr.or.ddit.notice.dao.NoticeDAO;
import kr.or.ddit.notice.vo.NoticeVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author 서효림
 * @since 2022. 8. 24.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 24.       서효림          최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService{
	@Inject
	NoticeDAO noticeDAO; 
	@Inject
	AttatchDAO attatchDAO;
	@Value("#{appInfo['attatchPath']}")
	private File saveFolder;
	

	@Override
	public void findAllNotice(PagingVO<NoticeVO> pagingVO) {
		Map<String, Object> map = new HashMap<>();
		map.put("pagingVO", pagingVO);
		map.put("notiClass", ComCode.NC01);
		
		int totalRecord = noticeDAO.selectNoticeCount(map);
		pagingVO.setTotalRecord(totalRecord);
		List<NoticeVO> dataList = noticeDAO.selectNoticeList(map);
		pagingVO.setDataList(dataList);
	}

	@Override
	public NoticeVO findDetailNotice(String notiNum) {
		NoticeVO notice = noticeDAO.selectNotice(notiNum);
		if(notice == null)
			throw new NotFoundException();
		noticeDAO.incrementNotice(notiNum);
		return notice;
	}
	
	private void processAttatches(NoticeVO notice) throws IOException {
		List<AttatchVO> attatchList = notice.getAttatchList();
		if(attatchList == null || attatchList.isEmpty()) {
			return;
		}
		attatchDAO.insertNoticeAttatches(notice);
		// 2진 데이터(파일 자체) 저장 -> d:/saveFiles
		
		for (AttatchVO attatch : attatchList) {
			MultipartFile file = attatch.getAttatchFile();
			attatch.saveTo(saveFolder);
			
		}
	}
	
	@Transactional
	@Override
	public ServiceResult createNotice(NoticeVO notice) {
		try {
			processAttatches(notice); // 첨부파일 처리.
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		int insertNotice = noticeDAO.insertNotice(notice);
		
		ServiceResult result = null;
		if(insertNotice > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAIL;
		}
		
		return result;
	}

	@Transactional
	@Override
	public ServiceResult modifyNotice(Map<String, Object> map) {
		NoticeVO notice = (NoticeVO) map.get("notice");
		int updateNotice = noticeDAO.updateNotice(notice);
		
		ServiceResult result = null;
		if(updateNotice > 0) {
			try {
				processAttatches(notice); // 첨부파일 처리.
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			
			String[] attatchNum = (String[]) map.get("attatchNum");
			int[] attatchOrder = (int[]) map.get("attatchOrder");
			if(attatchNum != null) {
				AttatchVO attatchVO = new AttatchVO();
				attatchVO.setAttatchPlace(notice.getNotiNum());
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
	public ServiceResult removeNotice(String notiNum) {
		int deleteNotice = noticeDAO.deleteNotice(notiNum);
		ServiceResult result = null;
		if(deleteNotice > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAIL;
		}
		
		return result;
	}
}
