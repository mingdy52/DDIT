package kr.or.ddit.proj.filerecord.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.common.vo.AttatchVO;
import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.proj.colleague.vo.ColleagueVO;
import kr.or.ddit.proj.filerecord.dao.FileRecordDAO;
import kr.or.ddit.proj.filerecord.vo.FileRecordVO;
import kr.or.ddit.proj.msg.vo.MessageVO;

/**
 * @author 작성자명
 * @since 2022. 8. 27.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 27.      고정현       다운기록 생성 및 조회
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Service
@Transactional
public class FileRecordServiceImpl implements FileRecordService {

	@Inject
	FileRecordDAO recordDAO;
	
	@Override
	public void createDownRecord(FileRecordVO record) {
		// TODO Auto-generated method stub
		recordDAO.insertDownRecord(record);
	}

	@Override
	public List<FileRecordVO> retrieveDownList(PagingVO<FileRecordVO> download) {
		download.setTotalRecord(recordDAO.selectDownTotalRecord(download));
		List<FileRecordVO> dataList = recordDAO.selectDownList(download);
		download.setDataList(dataList);
		return null;
	}

	@Override
	public List<AttatchVO> retrieveUploadFileList(PagingVO<AttatchVO> upload) {
		upload.setTotalRecord(recordDAO.selectTotalRecord(upload));
		List<AttatchVO> dataList = recordDAO.selectUploadList(upload);
		upload.setDataList(dataList);
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AttatchVO retrieveUploadFileView(AttatchVO attatch) {
		// TODO Auto-generated method stub
		return recordDAO.selectUploadView(attatch);
	}

	@Override
	public void retrieveGitUploadList(PagingVO<AttatchVO> upload) {
		// TODO Auto-generated method stub
		upload.setTotalRecord(recordDAO.selectTotalGitRecord(upload));
		List<AttatchVO> dataList = recordDAO.selectGitUploadList(upload);
		upload.setDataList(dataList);
	}

	@Override
	public void createGitDownRecord(FileRecordVO record) {
		// TODO Auto-generated method stub
		recordDAO.insertGitDownRecord(record);
	}

}
