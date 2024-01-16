package kr.or.ddit.community.coop.service;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.common.attach.dao.AttatchDAO;
import kr.or.ddit.common.vo.AttatchVO;
import kr.or.ddit.community.coop.dao.CooFormDAO;
import kr.or.ddit.community.coop.vo.CooFormVO;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class CooFormServiceImpl implements CooFormService{

	@Inject
	CooFormDAO cooFormDAO;
	
	@Inject
	AttatchDAO attatchDAO;
	
	@Override
	public void createCooForm(CooFormVO cooForm) {
		// 1. 첨부 파일을 첨부파일 vo에 삽입한다.
		AttatchVO attatch = new AttatchVO(cooForm.getOriginFile());
		// 신청자 번호
		attatch.setUploaderId(cooForm.getApplicantId());
		// 2. 해당 첨부파일을 insert한다.
		if(attatch != null) {			
			attatchDAO.insertAttatch(attatch);
		}
		try {
			File file = new File("D:/FinalProject/uploadFile");
			if (!file.exists()) {
				try{
					file.mkdir(); //폴더 생성합니다. ("새폴더"만 생성)
			    } 
			    catch(Exception e){
				    e.getStackTrace();
				}   
			}
			attatch.saveTo(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.info("************* {}", attatch);
		// 3. 업로드할 때 사용한 pk값을 받아옴
		cooForm.setCooFormNum(attatch.getAttatchPlace());
		cooForm.setCooAssumeAttatch(attatch.getAttatchNum());
		// 1. 신청양식을 insert한다(이때 selectkey를 이용하여 pk와 첨부파일 테이블의 pk를 가져온다)
		
		
		cooFormDAO.insertCooForm(cooForm);
		log.info("************** {}",cooForm);
		
		
	}

	@Override
	public void updateCancelCooForm(String cooNum) {
		// 협업 팀 신청 거절
		cooFormDAO.cancelForm(cooNum);
		
		// 신청 거절됫다는 알림 추가
		
	}

	@Override
	public void updateCorrectCooForm(String cooNum) {
		cooFormDAO.correctForm(cooNum);
	}

	
}
