package kr.or.ddit.common.attach.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.blog.vo.MyBlogPostVO;
import kr.or.ddit.common.vo.AttatchVO;
import kr.or.ddit.community.coop.vo.CooBoardVO;
import kr.or.ddit.community.free.vo.FreeBoardVO;
import kr.or.ddit.notice.vo.NoticeVO;

/**
 * @author 작성자명
 * @since 2022. 8. 18.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 8.      작성자명       고정현
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Mapper
public interface AttatchDAO {
	
	/**
	 * 파일 업로드
	 * @param attatch
	 */
	public void insertAttatch(AttatchVO attatch);
	
	public void insertFolderAttatch(AttatchVO attatch);
	
	public void updateAttatch(AttatchVO attatch);
	
	/**
	 * 파일 다운로드
	 * @param attatchNum
	 * @return
	 */
	public AttatchVO selectDownload(String attatchNum);
	
	/**
	 * 블로그 게시글의 첨부파일 등록
	 * @param postVO
	 * @return
	 */
	public int insertBlogAttatches(MyBlogPostVO postVO);
	
	/**
	 * 공지사항 게시글 첨부파일 등록
	 * @param notice
	 * @return
	 */
	public int insertNoticeAttatches(NoticeVO notice);
	/**
	 * 자유게시판 게시글 첨부파일 등록
	 * @param freeBoard
	 * @return
	 */
	public int insertFreeBoardAttatches(FreeBoardVO freeBoard);
	/**
	 * 자유게시판 게시글 첨부파일 등록
	 * @param freeBoard
	 * @return
	 */
	public int insertCooBoardAttatches(CooBoardVO cooboard);
	
	public List<AttatchVO> selectAttatchNum(String attatchPlace);

	/**
	 * 해당 파일에 대한 정보 가져오기
	 * @param attatch
	 * @return
	 */
	public AttatchVO selectAttatchFile(AttatchVO attatch);
	
	/**
	 * 첨부파일에 올라간 파일 지우기
	 * @param attatch
	 */
	public void deleteAttatchFile(AttatchVO attatch);

	/**
	 * 파일 여러개 다운로드
	 * @param attatch
	 * @return
	 */
	public AttatchVO multiDownload(AttatchVO attatch);

	/**
	 * 깃 파일 업로드 기록
	 * @param attatch
	 */
	public void insertGitAttatch(AttatchVO attatch);

	public AttatchVO createFolderNum();

	public List<AttatchVO> selectAttatchFileList(AttatchVO attatch);

	public void insertReplyAttatch(AttatchVO attatch);
	
	public void insertIssueReplyAttatch(AttatchVO attatch);
	
	public void modifyIssueReplyAttatch(AttatchVO attatch);
	
	public void  createAttachNumReplyAttatch(AttatchVO attatch);
	
	public String findAttachNum(String parentWoReplyNum);

	public AttatchVO selectAttatch(AttatchVO attatch);
	
	public void deleteAttatchReplaceFile(String attatchPlace);
}
