package kr.or.ddit.proj.filerecord.service;

import java.util.List;

import kr.or.ddit.common.vo.AttatchVO;
import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.proj.colleague.vo.ColleagueVO;
import kr.or.ddit.proj.filerecord.vo.FileRecordVO;

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
public interface FileRecordService {
	
	public void createDownRecord(FileRecordVO record);

	public List<FileRecordVO> retrieveDownList(PagingVO<FileRecordVO> download);

	/**
	 * 해당 첨부파일에 대한 것(업로드)
	 * @param upload
	 * @return
	 */
	public List<AttatchVO> retrieveUploadFileList(PagingVO<AttatchVO> upload);

	/**
	 * 해당 업로드 첨부파일 데이터 가져옴
	 * @param attatch
	 * @return
	 */
	public AttatchVO retrieveUploadFileView(AttatchVO attatch);

	public void retrieveGitUploadList(PagingVO<AttatchVO> upload);

	public void createGitDownRecord(FileRecordVO record);

}
