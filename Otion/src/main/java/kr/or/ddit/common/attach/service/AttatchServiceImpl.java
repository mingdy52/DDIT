package kr.or.ddit.common.attach.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.common.attach.dao.AttatchDAO;
import kr.or.ddit.common.vo.AttatchVO;
import kr.or.ddit.proj.projFolder.dao.ProjectFolderDAO;

/**
 * @author 작성자명
 * @since 2022. 8. 26.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 26.      고정현       로직 수정 및 멀티 다운로드
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Service
@Transactional
public class AttatchServiceImpl implements AttatchService {

	@Inject
	AttatchDAO attatchDAO;
	
	@Inject
	ProjectFolderDAO projectFolderDAO;

	@Override
	public void createAttatch(AttatchVO attatch) {
		// TODO Auto-generated method stub
		attatchDAO.insertAttatch(attatch);
	}

	@Override
	public AttatchVO download(String attatchNum) {
		// TODO Auto-generated method stub
		return attatchDAO.selectDownload(attatchNum);
	}

	@Override
	public void createFolderAttatch(AttatchVO attatch) {
		// TODO Auto-generated method stub
		if(StringUtils.isBlank(attatch.getAttatchNum()) && StringUtils.isBlank(attatch.getAttatchPlace())) {
			AttatchVO newAttatch = attatchDAO.createFolderNum();
			attatch.setAttatchNum(newAttatch.getAttatchNum());
			attatch.setAttatchPlace(newAttatch.getAttatchPlace());
		}
		
		attatchDAO.insertFolderAttatch(attatch);
		try {
			File file = new File("D:/FinalProject/uploadFile");
			if (!file.exists()) {
				try {
					file.mkdir(); // 폴더 생성합니다. ("새폴더"만 생성)
				} catch (Exception e) {
					e.getStackTrace();
				}
			}
			attatch.saveTo(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteAttatchFile(AttatchVO attatch) {
		// TODO Auto-generated method stub
		// 지우기 전 해당 파일에 대한 정보를 받아오기
		AttatchVO selectAttatch = attatchDAO.selectAttatchFile(attatch);
		// 업로드된 파일 삭제
		File file = new File("D:/FinalProject/uploadFile");
		selectAttatch.removeTo(file);
		
		attatchDAO.deleteAttatchFile(attatch);
		
		List<AttatchVO> removeAttatch = attatchDAO.selectAttatchFileList(attatch);
		if(removeAttatch.size() == 0) {
			// 해당 attatch 번호에 대한 파일이 다 제거 되었으므로 폴더기록에서도 삭제함
			projectFolderDAO.deleteFile(attatch.getAttatchNum());
		}
	}

	@Override
	public AttatchVO multiDownload(AttatchVO attatch) {
		// TODO Auto-generated method stub
		// 다운로드 시 조회수 1추가
		attatchDAO.updateAttatch(attatch);
		return attatchDAO.multiDownload(attatch);
	}

	@Override
	public void createGitAttatch(AttatchVO attatch) {
		// TODO Auto-generated method stub
		attatchDAO.insertGitAttatch(attatch);
	}

	@Override
	public void createReplyAttatch(AttatchVO attatch) {
		// TODO Auto-generated method stub
		attatchDAO.insertReplyAttatch(attatch);
		
		File saveFolder = new File("D:/FinalProject/uploadFile");
		try {
			attatch.saveTo(saveFolder);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void createIssueReplyAttatch(AttatchVO attatch) {
		// TODO Auto-generated method stub
		attatchDAO.insertIssueReplyAttatch(attatch);
		
		File saveFolder = new File("D:/FinalProject/uploadFile");
		try {
			attatch.saveTo(saveFolder);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void modifyAttatch(AttatchVO attatch) {
		attatchDAO.modifyIssueReplyAttatch(attatch);
		
	}

	@Override
	public void createAttachNumReplyAttatch(AttatchVO attatch) {
	    attatchDAO.createAttachNumReplyAttatch(attatch);
		
	}

	@Override
	public String findAttachNum(String parentWoReplyNum) {
		return attatchDAO.findAttachNum(parentWoReplyNum);
	}

	@Override
	public AttatchVO retrieveAttatch(AttatchVO attatch) {
		// TODO Auto-generated method stub
		
		return attatchDAO.selectAttatch(attatch);
		
	}
	

}
