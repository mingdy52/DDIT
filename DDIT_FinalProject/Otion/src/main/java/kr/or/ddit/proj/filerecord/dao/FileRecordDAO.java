package kr.or.ddit.proj.filerecord.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

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
@Mapper
public interface FileRecordDAO {
	
	public void insertDownRecord(FileRecordVO record);

	public List<FileRecordVO> selectDownList(PagingVO<?> download);

	public int selectTotalRecord(PagingVO<?> upload);

	public List<AttatchVO> selectUploadList(PagingVO<?> upload);

	public int selectDownTotalRecord(PagingVO<FileRecordVO> download);

	public AttatchVO selectUploadView(AttatchVO attatch);

	public int selectTotalGitRecord(PagingVO<AttatchVO> upload);

	public List<AttatchVO> selectGitUploadList(PagingVO<AttatchVO> upload);

	public void insertGitDownRecord(FileRecordVO record);

}
