package kr.or.ddit.proj.projFolder.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.common.vo.AttatchVO;
import kr.or.ddit.proj.colleague.vo.ColleagueVO;
import kr.or.ddit.proj.main.dao.ProjectDAO;
import kr.or.ddit.proj.projFolder.dao.ProjectFolderDAO;
import kr.or.ddit.proj.projFolder.vo.ProjectFileVO;
import kr.or.ddit.proj.projFolder.vo.ProjectFolderVO;

/**
 * @author 작성자명
 * @since 2022. 8. 24.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 24.      고정현       폴더 리스트 가져오기
 * 2022. 8. 25.      고정현       폴더에 올라간 파일 리스트 가져오기, 폴더 생성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Service
@Transactional
public class ProjectFolderServiceImpl implements ProjectFolderService{

	@Inject
	ProjectFolderDAO projectFolderDAO;
	
	@Override
	public List<ProjectFolderVO> retrieveFolderList(ColleagueVO colleague) {
		// TODO Auto-generated method stub
		return projectFolderDAO.selectProjectFolderList(colleague);
	}

	@Override
	public List<ProjectFolderVO> retrieveChildFolderList(ProjectFolderVO folder) {
		// TODO Auto-generated method stub
		return projectFolderDAO.selectProjectChildFolderList(folder);
	}

	@Override
	public void createNoParentFolder(ProjectFolderVO folder) {
		// TODO Auto-generated method stub
		projectFolderDAO.insertNoParentFolder(folder);
	}

	@Override
	public void createYesParentFolder(ProjectFolderVO folder) {
		// TODO Auto-generated method stub
		projectFolderDAO.insertYesParentFolder(folder);
	}

	@Override
	public List<ProjectFileVO> retrieveFileList(ProjectFileVO fileVO) {
		// TODO Auto-generated method stub
		return projectFolderDAO.selectFileList(fileVO);
	}

	@Override
	public void createFolderFile(ProjectFileVO fileVO) {
		// TODO Auto-generated method stub
		projectFolderDAO.insertFolderFile(fileVO);
	}

	@Override
	public List<AttatchVO> retrieveUploadFileList(ColleagueVO myColleague) {
		// TODO Auto-generated method stub
		List<ProjectFolderVO> fileList = projectFolderDAO.selectUploadFileList(myColleague);
		List<AttatchVO> single = new ArrayList<>();
		for(int i=0; i<fileList.size(); i++) {
			for(int j=0; j<fileList.get(i).getFileList().size(); j++) {
				single.addAll(fileList.get(i).getFileList().get(j).getAttatchVO());
			}
		}
		return single;
	}

	@Override
	public List<ProjectFolderVO> retrieveFolderAllList(ColleagueVO colleague) {
		// TODO Auto-generated method stub
		return projectFolderDAO.selectProjectFolderAllList(colleague);
	}

	@Override
	public void removeFolder(ProjectFolderVO folder) {
		// TODO Auto-generated method stub
		// 해당 폴더의 데이터 중 삭제 여부를 업데이트함
		projectFolderDAO.deleteFolder(folder);
	}

}
